package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import dao.MediaDao;
import entity.HotSearch;
import entity.Media;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class DeleteMediaAction extends ActionSupport{
	@Autowired
	private ForumDao  forumDao;
	private MediaDao mediaDao;
	private Media media;
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idStr = request.getParameter("vedioId");
		int vedioId = Integer.parseInt(idStr);
		try {
			media = mediaDao.getMediaByMediaId(vedioId);
			mediaDao.deleteMedia(media);
		} catch (Exception e) {
			// TODO: handle exception
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
	
}
