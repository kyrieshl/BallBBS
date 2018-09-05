package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.HotSearch;
import lombok.Data;
@SuppressWarnings("serial")
@Data
public class ShowPopularNewsDetailAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private List<String> list1;
	private List<String> list2;
	@Override
	public String execute() throws Exception {
		String hrefString;
		list1 = new ArrayList<String>();
		list2 = new ArrayList<String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		hrefString = "https://voice.hupu.com" + request.getParameter("popularHrefString");
		try {
			Document doc = Jsoup.connect(hrefString).get();
			Elements link1 = doc.getElementsByClass("voice-main");
			Elements link2 = doc.getElementsByClass("list").select("li");
			for(Element e:link2){
				list1.add(e.select("a").attr("href"));
				list2.add(e.select("a").text());
			}
			if(session.getAttribute("currentLink") != null){
				session.removeAttribute("currentLink");
				session.setAttribute("currentLink", link1);
			}else{
				session.setAttribute("currentLink", link1);
			}
			
			if(session.getAttribute("currentList1") != null){
				session.removeAttribute("currentList1");
				session.setAttribute("currentList1", list1);
			}else{
				session.setAttribute("currentList1", list1);
			}
			
			if(session.getAttribute("currentList2") != null){
				session.removeAttribute("currentList2");
				session.setAttribute("currentList2", list2);
			}else{
				session.setAttribute("currentList2", list2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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
		
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}
