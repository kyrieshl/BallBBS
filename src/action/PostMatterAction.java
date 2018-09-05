package action;

import java.util.ArrayList;
import java.util.List;

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
public class PostMatterAction extends ActionSupport{
	@Autowired
	private List<PostReply> allPostReply;
	private ForumDao forumDao;
	private User user;
	private Post post;
	private int totalPage;
	private int pageNumber;
	private int pageSize;
	private int postReplyAmount;
	
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String pageNumberStr = request.getParameter("pageNumber");
		user = (User) session.getAttribute("loginUser");
		if (pageNumberStr == null || "".equals(pageNumberStr.trim())) {
			pageNumberStr = "1";
		}
		if(request.getParameter("postId")!=null){
			post = forumDao.findPost(Integer.parseInt(request.getParameter("postId")));
			session.setAttribute("currentPost",post);
		}
		else {
			post=(Post)session.getAttribute("currentPost");
		}
		pageSize = 10;
		postReplyAmount = forumDao.postReplyAmount(post);
		int[] paging = new int[2];
		paging = forumDao.pageNumber(postReplyAmount, pageNumberStr,pageSize);
		pageNumber = paging[0];
		totalPage = paging[1];
		allPostReply = forumDao.allPostReply(post, pageNumber, pageSize);
		
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
