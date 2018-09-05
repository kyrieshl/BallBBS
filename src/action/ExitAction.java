package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.HotSearch;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class ExitAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	public String userExit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null){
			session.removeAttribute("loginUser");
		}
		
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
	public String managerExit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("loginManager") != null){
			session.removeAttribute("loginManager");
		}
		
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
}
