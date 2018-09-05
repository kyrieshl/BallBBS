package action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.HotSearch;
import entity.Post;
import entity.PostReply;
import entity.User;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class AddReplyAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private PostReply postReply;
	
	public String execute(){
		this.clearErrorsAndMessages();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		Post post = (Post)session.getAttribute("currentPost");
		int currentFloor;
		postReply.setReplyTime(new Timestamp(new Date().getTime()));
		postReply.setUser(user);
		boolean flag = forumDao.postHasReplyOrNot(post);
		if(flag)
			currentFloor = forumDao.currentFloor(post);
		else
			currentFloor = 0;
		post.setPostReplyAmount(currentFloor + 1);
		postReply.setPost(post);
		postReply.setFloor(currentFloor + 1);
		postReply.setParentFloor(0);
		forumDao.updatePost(post);
		forumDao.savePostReply(postReply);
		addActionMessage("回复成功");
		
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
		if(postReply.getReplyContent().equals("")){
			addFieldError("postReply.replyContent","回复不能为空！");
		}
		else if(postReply.getReplyContent().length() < 3)
		{
			addFieldError("postReply.replyContent","回复长度请大于5！");
		}
		
	}

}
