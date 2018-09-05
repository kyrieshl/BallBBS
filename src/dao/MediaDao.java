package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.helpers.ValidationEventImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.Media;


public class MediaDao extends HibernateDaoSupport{
	

	/**
     * 视频转码
     * @param ffmpegPath    转码工具的存放路径
     * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件
     * @param codcFilePath    格式转换后的的文件保存路径
     * @param mediaPicPath    截图保存路径
     * @return
     * @throws Exception
     */
	public boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
            String mediaPicPath) throws Exception {
        // 创建一个List集合来保存转换视频文件为flv格式的命令
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // 添加转换工具路径
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add(upFilePath); // 添加要转换格式的视频文件的路径
        convert.add("-qscale");     //指定转换的质量
        convert.add("6");
        convert.add("-ab");        //设置音频码率
        convert.add("64");
        convert.add("-ac");        //设置声道数
        convert.add("2");
        convert.add("-ar");        //设置声音的采样频率
        convert.add("22050");
        convert.add("-r");        //设置帧频
        convert.add("24");
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        convert.add(codcFilePath);

        // 创建一个List集合来保存从视频中截取图片的命令
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("mjpeg");
        cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
        cutpic.add("3"); // 添加起始时间为第3秒
        cutpic.add("-t"); // 添加参数＂-t＂，该参数指定持续时间
        cutpic.add("0.001"); // 添加持续时间为1毫秒
        cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
        cutpic.add("800*280"); // 添加截取的图片大小
        cutpic.add(mediaPicPath); // 添加截取的图片的保存路径
        
        

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            builder.start();
            
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // 如果此属性为 true，则任何由通过此对象的 start() 方法启动的后续子进程生成的错误输出都将与标准输出合并，
            //因此两者均可使用 Process.getInputStream() 方法读取。这使得关联错误消息和相应的输出变得更容易
            builder.start();
        } catch (Exception e) {
            mark = false;
            System.out.println(e);
            e.printStackTrace();
        }
        System.out.println(upFilePath);
        System.out.println(cutpic);
        System.out.println(codcFilePath);
        System.out.println(mediaPicPath);
        System.out.println("mark:"+mark);
        return mark;
    }

	

	@SuppressWarnings("null")
	public int getMediaAmount(){
		int mediaAmount = 0;
		String hql = "select count(*) from Media";
		List list = getHibernateTemplate().find(hql);
		if(list != null || !list.isEmpty() || list.size() != 0)
			mediaAmount = (int) (long)list.get(0);
		return mediaAmount;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Media> getAllMedia(final int pageNumber,final int pageSize){
		final String hql = "from Media order by uptime desc";
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(hql).setFirstResult((pageNumber-1)*pageSize).setMaxResults(pageSize).list();
			}
		});
	}

	
	@SuppressWarnings("unchecked")
	public Media getMediaByMediaId(int mediaId){
		String hql = "from Media where vedioId = ?";
		List<Media> list;
		list = getHibernateTemplate().find(hql,mediaId);
		if(list == null || list.isEmpty() || list.size() == 0)
			return null;
		else 
			return list.get(0);
	}

	public void saveMedia(Media media){
		getHibernateTemplate().save(media);
	}
	
	public void deleteMedia(Media media){
		getHibernateTemplate().delete(media);
	}
	
	public int[] pageNumber(int vedioAmount,String pageNumberStr,int pageSize){
		int[] paging = new int[2];
		int pageNumber = 1;
		pageNumber = Integer.parseInt(pageNumberStr);
		if(pageNumber <= 0){
			pageNumber = 1;
		}
		int totalPage = 1;
		if(vedioAmount > 0){
			totalPage = vedioAmount%pageSize==0?(vedioAmount/pageSize):(vedioAmount/pageSize+1);
		}
		if(pageNumber > totalPage){
			pageNumber = totalPage;
		}
		paging[0] = pageNumber;
		paging[1] = totalPage;
		return paging;
	}
	
}
