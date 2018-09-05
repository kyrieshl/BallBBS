package action;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import entity.*;
import lombok.Data;
import dao.*;
@Data
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{
	@Autowired
	ForumDao forumDao;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	public void validate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(user != null){
			User user2 = forumDao.judgeUser(user);
			if(user2 != null){
				if(session.getAttribute("loginUser") != null){
					session.removeAttribute("loginUser");
				}
				session.setAttribute("loginUser", user2);
			}else{
				addFieldError("user.userName", "用户名或密码错误！");
			}
		}
	}
	
}

