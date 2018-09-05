package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.*;
import lombok.Data;
@Data
@SuppressWarnings("serial")
public class DeletePostAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private String pageNumber;
	
	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String postIdStr = request.getParameter("postId");
		pageNumber = request.getParameter("pageNumber");
		if(pageNumber == null || "".equals(pageNumber.trim())){
			pageNumber = "1";
		}
		int postId = Integer.parseInt(postIdStr);
		Post post = forumDao.findPost(postId);
		User user = post.getUser();
		user.setUserPoints(user.getUserPoints()-3);
		forumDao.deleteAllReply(post);
		forumDao.deletePost(post);
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
}
