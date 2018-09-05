package action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.ForumDao;
import dao.MediaDao;
import entity.HotSearch;
import entity.Media;
import entity.User;
import lombok.Data;

@Data
@SuppressWarnings("serial")
public class UploadMediaAction extends HttpServlet {
	private List<Media> mediaList;
	private ForumDao forumDao;
	private MediaDao mediaDao;
	private Media media;
	private int totalPage;
	private int pageNumber;
	private int pageSize;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpServletRequest request2 = ServletActionContext.getRequest();
		HttpSession session2 = request2.getSession();
		String message = new String();
		if(session2.getAttribute("loginUser") == null){
			message = "���ȵ�¼";
			/*ActionSupport actionSupport = new ActionSupport();
			actionSupport.addActionMessage("���ȵ�¼");*/
			session2.setAttribute("pleaseLoginFirst", message);
			request.getRequestDispatcher("login.jsp").forward(
					request, response);
		}
		ApplicationContext ctx=new ClassPathXmlApplicationContext("../applicationContext.xml");
		MediaDao mediaDao  = (MediaDao)ctx.getBean("mediaDao");
		ForumDao forumDao  = (ForumDao)ctx.getBean("forumDao");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String uri = request.getRequestURI();
		System.out.println("uri>>"+uri);
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));

		if ("/uploadFile".equals(path)) {
			// �ṩ����ʱ��һЩȱʡ����
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// ����һ��������,����InputStream,�ý������Ὣ�����Ľ����װ��һ��FileItem����ļ���
			// һ��FileItem�����Ӧһ������
			ServletFileUpload sfu = new ServletFileUpload(factory);

			try {
				Media media = new Media();
				String mediaTitle= "";
				String mediaDescript ="";
				List<FileItem> items = sfu.parseRequest(request);
				boolean flag = false; // ת��ɹ����ı��
				for (int i = 0; i < items.size(); i++) {
					FileItem item = items.get(i);
					// Ҫ�������ϴ��ļ�������ͨ�ı���
					if (item.isFormField()) {// isFormField()Ϊtrue,��ʾ�ⲻ���ļ��ϴ�����
						// ��ͨ����
						String paramName = item.getFieldName();
						
						 String paramValue = item.getString();
						  System.out.println("��������Ϊ:" + paramName +
						 ", ��Ӧ�Ĳ���ֵΪ: " + paramValue);
						 
						if (paramName.equals("title")) {
							media.setTitle(new String(item.getString()
									.getBytes("ISO8859-1"), "UTF-8"));
						}
						if (paramName.equals("descript")) {
							media.setDescript(new String(item.getString()
									.getBytes("ISO8859-1"), "UTF-8"));
						}

					} else {
						// �ϴ��ļ�
						// System.out.println("�ϴ��ļ�" + item.getName());
						ServletContext sctx = this.getServletContext();
						// ��ñ����ļ���·��
						String basePath = sctx.getRealPath("videos");
						// ����ļ���
						String fileUrl = item.getName();
						// ��ĳЩ����ϵͳ��,item.getName()�����᷵���ļ�����������,������·��
						String fileType = fileUrl.substring(fileUrl
								.lastIndexOf(".")); // ��ȡ�ļ���ʽ
						// �Զ��巽ʽ�����ļ���
						String serialName = String.valueOf(System
								.currentTimeMillis());
						// ��ת����ļ�
						File uploadFile = new File(basePath + "/temp/"
								+ serialName + fileType);
						item.write(uploadFile);

						if (item.getSize() > 500 * 1024 * 1024) {
							message = "<li>�ϴ�ʧ�ܣ����ϴ����ļ�̫��,ϵͳ��������ļ�500M</li>";
						}
						String codcFilePath = basePath + "/" + serialName
								+ ".flv"; // ����ת��Ϊflv��ʽ���ļ��ı���·��
						String mediaPicPath = "H:\\javaFiles\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\BallBBS\\videos\\images"
								+ "/" + serialName + ".jpg"; // �����ϴ���Ƶ��ͼ�ı���·��

						// ��ȡ���õ�ת�����ߣ�ffmpeg.exe���Ĵ��·��
						String ffmpegPath = getServletContext().getRealPath(
								"/tools/ffmpeg.exe");
						
					    HttpSession session = request.getSession();
					    User user = (User) session.getAttribute("loginUser");
					    media.setUser(user);
					    media.setBeLikedAmount(0);
						media.setSrc("videos/" + serialName + ".flv");
						media.setPicture("videos/images/" + serialName + ".jpg");
						String now = System.currentTimeMillis()+"";
						media.setUptime(new Timestamp(new Date().getTime()));

						// ת��
						flag = mediaDao.executeCodecs(ffmpegPath,
								uploadFile.getAbsolutePath(), codcFilePath,
								mediaPicPath);
					}
				}
				if (flag) {
					// ת��ɹ�,�����ݱ�����Ӹ���Ƶ��Ϣ
					mediaDao.saveMedia(media);
					System.out.println("ת��ɹ�");
					message = "�ϴ��ɹ�!";
					session2.setAttribute("message", message);
				}
				
				
				if(session2.getAttribute("listTop5") == null || session2.getAttribute("listTop5").toString().equals("")){
					List<HotSearch> list;
					list = forumDao.hotSearchTop5();
					List<String> listTop5 = new ArrayList<String>();
					for(int i = 0;i<list.size();i++){
						listTop5.add(list.get(i).getHotSearchContent());
					}
					session2.setAttribute("listTop5", listTop5);
				}
				
				
				request.getRequestDispatcher("media_upload.jsp").forward(
						request, response);

			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}

		/*if ("/queryAll".equals(path)) {
			try {
				String pageNumberStr = request.getParameter("pageNumber");
				if(pageNumberStr == null || "".equals(pageNumberStr.trim())){
					pageNumberStr = "1";
				}
				
				pageSize = 10;
				int postAmount = mediaDao.getMediaAmount();
				int[] paging = new int[2];
				paging = mediaDao.pageNumber(postAmount, pageNumberStr, pageSize);
				pageNumber = paging[0];
				totalPage = paging[1];
				mediaList = mediaDao.getAllMedia(pageNumber, pageSize);
				request.setAttribute("mediaList", mediaList);
				request.getRequestDispatcher("media_list.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("/play".equals(path)) {
			String idstr = request.getParameter("id");
			int mediaId = -1;
			Media media = null;
			if (null != idstr) {
				mediaId = Integer.parseInt(idstr);
			}
			try {
				media = mediaDao.getMediaByMediaId(mediaId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("media", media);
			request.getRequestDispatcher("media_player.jsp").forward(request,
					response);
		}*/
	}
	
}
