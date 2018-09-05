package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import dao.MediaDao;
import entity.HotSearch;
import entity.Media;

@SuppressWarnings("serial")
public class PlayVedioAction extends ActionSupport {
	@Autowired
	private ForumDao forumDao;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context =  new ClassPathXmlApplicationContext("../applicationContext.xml");
		MediaDao mediaDao = (MediaDao) context.getBean("mediaDao");
		HttpServletRequest request = ServletActionContext.getRequest();
		String idStr = request.getParameter("vedioId");
		int vedioId = Integer.parseInt(idStr);
		Media media = mediaDao.getMediaByMediaId(vedioId);
		HttpSession session = request.getSession();
		session.setAttribute("media", media);
		
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
