package action;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.*;
import lombok.Data;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.protocol.HTTP;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("serial")
@Data
public class AllPostAction extends ActionSupport{
	final String SUCCESSM = "successM";
	@Autowired
	ForumDao forumDao;
	private List<Post> allPost;
	private int totalPage;
	private int pageNumber;
	private int pageSize;


	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String pageNumberStr = request.getParameter("pageNumber");
		if(pageNumberStr == null || "".equals(pageNumberStr.trim())){
			pageNumberStr = "1";
		}
		
		pageSize = 10;
		int postAmount = forumDao.postAmount();
		int[] paging = new int[2];
		paging = forumDao.pageNumber(postAmount, pageNumberStr, pageSize);
		pageNumber = paging[0];
		totalPage = paging[1];
		allPost = forumDao.allPost(pageNumber, pageSize);
		
		if(session.getAttribute("listTop5") == null || session.getAttribute("listTop5").toString().equals("")){
			List<HotSearch> list;
			list = forumDao.hotSearchTop5();
			List<String> listTop5 = new ArrayList<String>();
			for(int i = 0;i<list.size();i++){
				listTop5.add(list.get(i).getHotSearchContent());
			}
			session.setAttribute("listTop5", listTop5);
		}
		
		if(session.getAttribute("loginManager") != null)
			return SUCCESSM;
		else
			return SUCCESS;
	}
		
}
