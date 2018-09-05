package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.*;
import lombok.Data;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.id.insert.IdentifierGeneratingInsert;
import org.springframework.beans.factory.annotation.Autowired;
@Data
@SuppressWarnings("serial")
public class PersonalPostAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private List<Post> personalPost;
	private int totalPage;
	private int pageNumber;
	private int pageSize;
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public List<Post> getPersonalPost() {
		return personalPost;
	}

	public void setPersonalPost(List<Post> personalPost) {
		this.personalPost = personalPost;
	}

	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("loginUser");
		User user = forumDao.findUser(user1.getUserId());
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim())){
			pageNumberStr = "1";
		}
		pageSize = 10;
		int postAmount = forumDao.personalPostAmount(user);
		int[] paging = new int[2];
		paging = forumDao.pageNumber(postAmount, pageNumberStr, pageSize);
		
		pageNumber = paging[0];
		totalPage = paging[1];
		personalPost = forumDao.personalPost(user,pageNumber,pageSize);
		
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
	
	@Override
	public void validate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null || session.getAttribute("loginUser").equals("")){
			this.addFieldError("pleaseLoginFirst", "ÇëÏÈµÇÂ¼£¡");
		}
		// TODO Auto-generated method stub
	}
		
}
