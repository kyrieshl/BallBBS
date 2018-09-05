package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import entity.HotSearch;
import entity.User;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class UpdateImageAction extends ActionSupport{
	@Autowired
	private User user;
	private ForumDao forumDao;
	private File userImage;
	private String userImageFileName;//文件名
	private String userImagecontentType;//文件类型
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String path = ServletActionContext.getRequest().getContextPath();
		String basePath = ServletActionContext.getRequest().getScheme() + "://"
				+ ServletActionContext.getRequest().getServerName() + ":"
				+ ServletActionContext.getRequest().getServerPort() + path + "/";
		String root = ServletActionContext.getServletContext().getRealPath("/upload");
		String userImageRealPath = basePath + "upload";
		File file = new File(root + "/" + getUserImageFileName());
		System.out.println(file.getAbsolutePath());
		try {
			OutputStream out = new FileOutputStream(file);
			InputStream inputStream = new FileInputStream(userImage);
			byte[] buffer = new byte[400];
			int length1 = 0;
			while ((length1 = inputStream.read(buffer)) > 0) {
				out.write(buffer, 0, length1);
			}
			inputStream.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user = (User)session.getAttribute("loginUser");
		if(user != null){
			String userImagePath = userImageRealPath + "/" + getUserImageFileName();
			user.setUserImage(userImagePath);
			forumDao.updateUser(user);
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
