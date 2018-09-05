package action;

import java.io.IOException;

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
public class ShowNewsListAction extends ActionSupport{
	@Autowired
	private ForumDao forumDao;
	private static List<String> list1;
	private static List<String> list2;
	private static List<String> list3;
	private static List<String> list4;
	private static List<String> list5;
	private static List<String> list6;
	
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			list1 = new ArrayList<String>();
			list2 = new ArrayList<String>();
			list3 = new ArrayList<String>();
			list4 = new ArrayList<String>();
			list5 = new ArrayList<String>();
			list6 = new ArrayList<String>();
			Document doc = Jsoup.connect("https://voice.hupu.com/nba").get();
			Elements link1 = doc.getElementsByClass("list-hd");
			Elements link2 = doc.getElementsByClass("time");
			Elements link3 = doc.getElementsByClass("comeFrom");
			Elements link4 = doc.getElementsByClass("list").select("li");
			for(Element e:link1){
				list1.add(e.select("a").attr("href"));
				list2.add(e.select("a").text());
			}
			for(Element e:link2){
				list3.add(e.text());
			}
			for(Element e:link3){
				list4.add(e.select("a").text());
			}
			for(Element e:link4){
				list5.add(e.select("a").attr("href"));
				list6.add(e.select("a").text());
			}
			System.out.println(list5);
			System.out.println(list5.size());
			System.out.println(list6);
			System.out.println(list6.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("list1", list1);
		session.setAttribute("list2", list2);
		session.setAttribute("list3", list3);
		session.setAttribute("list4", list4);
		session.setAttribute("list5", list5);
		session.setAttribute("list6", list6);
		
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
