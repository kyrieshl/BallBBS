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
     * ��Ƶת��
     * @param ffmpegPath    ת�빤�ߵĴ��·��
     * @param upFilePath    ����ָ��Ҫת����ʽ���ļ�,Ҫ��ͼ����ƵԴ�ļ�
     * @param codcFilePath    ��ʽת����ĵ��ļ�����·��
     * @param mediaPicPath    ��ͼ����·��
     * @return
     * @throws Exception
     */
	public boolean executeCodecs(String ffmpegPath, String upFilePath, String codcFilePath,
            String mediaPicPath) throws Exception {
        // ����һ��List����������ת����Ƶ�ļ�Ϊflv��ʽ������
        List<String> convert = new ArrayList<String>();
        convert.add(ffmpegPath); // ���ת������·��
        convert.add("-i"); // ��Ӳ�����-i�����ò���ָ��Ҫת�����ļ�
        convert.add(upFilePath); // ���Ҫת����ʽ����Ƶ�ļ���·��
        convert.add("-qscale");     //ָ��ת��������
        convert.add("6");
        convert.add("-ab");        //������Ƶ����
        convert.add("64");
        convert.add("-ac");        //����������
        convert.add("2");
        convert.add("-ar");        //���������Ĳ���Ƶ��
        convert.add("22050");
        convert.add("-r");        //����֡Ƶ
        convert.add("24");
        convert.add("-y"); // ��Ӳ�����-y�����ò���ָ���������Ѵ��ڵ��ļ�
        convert.add(codcFilePath);

        // ����һ��List�������������Ƶ�н�ȡͼƬ������
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegPath);
        cutpic.add("-i");
        cutpic.add(upFilePath); // ͬ�ϣ�ָ�����ļ���������ת��Ϊflv��ʽ֮ǰ���ļ���Ҳ������ת����flv�ļ���
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("mjpeg");
        cutpic.add("-ss"); // ��Ӳ�����-ss�����ò���ָ����ȡ����ʼʱ��
        cutpic.add("3"); // �����ʼʱ��Ϊ��3��
        cutpic.add("-t"); // ��Ӳ�����-t�����ò���ָ������ʱ��
        cutpic.add("0.001"); // ��ӳ���ʱ��Ϊ1����
        cutpic.add("-s"); // ��Ӳ�����-s�����ò���ָ����ȡ��ͼƬ��С
        cutpic.add("800*280"); // ��ӽ�ȡ��ͼƬ��С
        cutpic.add(mediaPicPath); // ��ӽ�ȡ��ͼƬ�ı���·��
        
        

        boolean mark = true;
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.redirectErrorStream(true);
            builder.start();
            
            builder.command(cutpic);
            builder.redirectErrorStream(true);
            // ���������Ϊ true�����κ���ͨ���˶���� start() ���������ĺ����ӽ������ɵĴ�������������׼����ϲ���
            //������߾���ʹ�� Process.getInputStream() ������ȡ����ʹ�ù���������Ϣ����Ӧ�������ø�����
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
