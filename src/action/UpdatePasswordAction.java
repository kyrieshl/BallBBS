package action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import entity.*;
import lombok.Data;
import dao.*;
@Data
@SuppressWarnings("serial")
public class UpdatePasswordAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private String userPassword;
	private String userNewPassword;
	private String userRePassword;
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNewPassword() {
		return userNewPassword;
	}
	public void setUserNewPassword(String userNewPassword) {
		this.userNewPassword = userNewPassword;
	}
	public String getUserRePassword() {
		return userRePassword;
	}
	public void setUserRePassword(String userRePassword) {
		this.userRePassword = userRePassword;
	}
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		user.setUserPassword(userNewPassword);
		forumDao.updateUser(user);
		
		if(session.getAttribute("listTop5") == null || session.getAttribute("listTop5").toString().equals("")){
			List<HotSearch> list;
			list = forumDao.hotSearchTop5();
			List<String> listTop5 = new ArrayList<String>();
			for(int i = 0;i<list.size();i++){
				listTop5.add(list.get(i).getHotSearchContent());
			}
			session.setAttribute("listTop5", listTop5);
		}
		
		return SUCCESS;
	}
	public void validate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		if(!userPassword.equals(user.getUserPassword())){
			addFieldError("userPassword", "原始密码错误！");
		}
		if(!userRePassword.equals(userNewPassword)){
			addFieldError("userRePassword", "两次密码不一致！");
		}
	}
}
