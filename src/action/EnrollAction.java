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
import entity.*;
import lombok.Data;
import dao.*;
@Data
@SuppressWarnings("serial")
public class EnrollAction extends ActionSupport{
	@Autowired
	ForumDao forumDao;
	private User user;
	private String userRePassword;
	private File userImage;
	private String userImageFileName;//文件名
	private String userImagecontentType;//文件类型

	@Override
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
		if(user != null){
			String userImagePath = userImageRealPath + "/" + getUserImageFileName();
			user.setUserImage(userImagePath);
			user.setUserPoints(0);
			forumDao.saveUser(user);
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
	
	public void validate(){
//		System.out.println("111");
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		boolean isImg = false;
		if(user.getUserName().equals("")){
			addFieldError(user.getUserName(), "用户名不能为空！");
		} 
		else if(!forumDao.judgeUserName(user.getUserName())){
			addFieldError(user.getUserName(), "用户名已经存在！");
		}
		else if(user.getUserName().length() < 3){
			addFieldError(user.getUserName(),"用户名长度请大于3！");
		}
		else if(user.getUserName().length() < 3){
			addFieldError(user.getUserName(),"用户名长度请小于10！");
		}
		else if(user.getUserPassword().equals("")){
			addFieldError(user.getUserPassword(),"密码不能为空！" );
		}
		else if(user.getUserPassword().length() < 6 || user.getUserPassword().length() > 16){
			addFieldError(user.getUserPassword(), "密码长度至少应为5-16位！");
		}
		else if(!userRePassword.equals(user.getUserPassword()) ){
			addFieldError("userRePassword", "两次密码不一致！");
		}
//		if(user.getUserBirthday() == null){
//			addFieldError(sdf.format(user.getUserBirthday()), "出生日期不能为空！");
//		}
		else if(user.getUserEmail().equals(""))
		{
			addFieldError(user.getUserEmail(), "邮箱不能为空！");
		}
		else if(getUserImage() == null){
			addFieldError(user.getUserImage(),"用户头像不能为空！");
			
		}else{
			String[] imgPostfix = {".gif",".jpg",".jpeg",".bmp",".png"};
			for(String inst : imgPostfix){
				if(getUserImageFileName().toLowerCase().endsWith(inst)){
					isImg = true;
					break;
				}
			}
			if(!isImg){
				addFieldError(getUserImagecontentType(),"用户头像必须为图片格式！");
			}
		}
	}
}
