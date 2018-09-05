package action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.HotSearch;
import entity.Post;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class SearchPostAction extends ActionSupport{
	@Autowired
	private HotSearch hotSearch;
	private ForumDao forumDao;
	private List<Post> matchedPost;
	private int totalPage;
	private int pageNumber;
	private int pageSize;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		pageNumber = 1;
		pageSize = 10;
		String searchContent = hotSearch.getHotSearchContent();				
		int matchedPostAmount = forumDao.matchedPostAmount(searchContent);
		int[] paging = new int[2];
		forumDao.pageNumber(matchedPostAmount, "1", pageSize);
		totalPage = paging[1] == 0 ? 1 : 0;
		matchedPost = forumDao.searchPost(searchContent, pageNumber, pageSize);
		if(matchedPost == null || matchedPost.isEmpty() || matchedPost.size() == 0){
			clearMessages();
			addActionMessage("ÎÞËÑË÷½á¹û£¡");
		}
		hotSearch = forumDao.getHotSearchBySearchContent(searchContent);
		if(hotSearch == null){
			HotSearch hotSearch1 = new HotSearch();
			hotSearch1.setHotSearchContent(searchContent);
			hotSearch1.setCount(1);
			forumDao.saveHotSearch(hotSearch1);
		}
		else{
			int count = forumDao.beSearchedAmount(searchContent);
			HotSearch hotSearch2 = forumDao.getHotSearchBySearchContent(searchContent);
			hotSearch2.setCount(count + 1);
			forumDao.updateHotSearch(hotSearch2);
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
		else{
			session.removeAttribute("listTop5");
			List<HotSearch> list;
			list = forumDao.hotSearchTop5();
			List<String> listTop5 = new ArrayList<String>();
			for(int i = 0;i<list.size();i++){
				listTop5.add(list.get(i).getHotSearchContent());
			}
			session.setAttribute("listTop5", listTop5);
			System.out.println(listTop5);
		}
		// TODO Auto-generated method stub
		return SUCCESS;		
	}
}
