package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ShowMediaList extends ActionSupport {
	@Autowired
	private List<Media> mediaList;
	private ForumDao forumDao;
	private MediaDao mediaDao;
	private int totalPage;
	private int pageNumber;
	private int pageSize;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			String pageNumberStr = request.getParameter("pageNumber");
			if(pageNumberStr == null || "".equals(pageNumberStr.trim())){
				pageNumberStr = "1";
			}
			
			pageSize = 20;
			int mediaAmount = mediaDao.getMediaAmount();
			int[] paging = new int[2];
			paging = mediaDao.pageNumber(mediaAmount, pageNumberStr, pageSize);
			pageNumber = paging[0];
			totalPage = paging[1];
			mediaList = mediaDao.getAllMedia(pageNumber, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("../applicationContext.xml");
		MediaDao mediaDao  = (MediaDao)ctx.getBean("mediaDao");
		try {
			String pageNumberStr = request.getParameter("pageNumber");
			if(pageNumberStr == null || "".equals(pageNumberStr.trim())){
				pageNumberStr = "1";
			}
			
			pageSize = 10;
			int mediaAmount = mediaDao.getMediaAmount();
			int[] paging = new int[2];
			paging = mediaDao.pageNumber(mediaAmount, pageNumberStr, pageSize);
			pageNumber = paging[0];
			totalPage = paging[1];
			mediaList = mediaDao.getAllMedia(pageNumber, pageSize);
			request.setAttribute("mediaList", mediaList);
			request.getRequestDispatcher("media_list.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
