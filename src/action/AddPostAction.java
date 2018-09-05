package action;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.*;
import entity.*;
import lombok.Data;
@Data
@SuppressWarnings("serial")
public class AddPostAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private Post post;
	
	@Override
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		post.setPostTime(new Timestamp(new Date().getTime()));
		post.setUser(user);
		post.setPostReplyAmount(0);
		forumDao.savePost(post);
		session.setAttribute("currentPost", post);
		user.setUserPoints(user.getUserPoints()+3);
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
	
	
	@Override
	public void validate(){
		// TODO Auto-generated method stub
		super.validate();
		if(post.getTopic().equals(""))
			addFieldError("post.topic", "主题不能为空！");
		else if(post.getTopic().length() < 5)
			addFieldError("post.topic", "主题长度请大于5！");
		else if(post.getMatter().equals(""))
			addFieldError("post.matter", "内容不能为空！");
		else if(post.getMatter().length() < 10)
			addFieldError("post.matter", "内容长度请大于10！");
	}
	
}
