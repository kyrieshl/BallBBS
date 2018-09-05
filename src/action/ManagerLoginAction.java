package action;

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
public class ManagerLoginAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private Manager manager;
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String execute(){
			return SUCCESS;
	}
	
	public void validate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Manager manager2 = forumDao.judgeManager(manager);
		if(manager2 != null){
			if(session.getAttribute("loginUser") != null){
				session.removeAttribute("loginUser");
			}
			if(session.getAttribute("loginManager") != null){
				session.removeAttribute("loginManager");
			}
			session.setAttribute("loginManager", manager2);
		}else{
			addFieldError("manager.managerName", "用户名或密码错误！");
		}
	}
	
}
