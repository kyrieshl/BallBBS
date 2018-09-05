package dao;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import entity.*;

public class ForumDao extends HibernateDaoSupport{
	

	
	/**����Ϊ�û���ط���*/	
	
	//���һ���û�
	public void saveUser(User user){
		getHibernateTemplate().save(user);		
	}
	
	//�޸�һ���û�
	public void updateUser(User user){
		getHibernateTemplate().update(user);
	}
	
	//��ѯһ���û���Ϣ
	public User findUser(int userId){
		return getHibernateTemplate().get(User.class, userId);
	}
	
	//��ѯһ���û����е�����
	@SuppressWarnings("unchecked")
	public List<Post> personalPost(User user,int pageNumber,int pageSize){
		List<Post> personalPost;
		String hql = "from Post as post where post.user.userId = ? order by post.postTime desc";
		personalPost = getHibernateTemplate().find(hql,user.getUserId());
		return personalPost;
	}
	
	
	//��ȡ�����û�������������
	@SuppressWarnings("null")
	public int personalPostAmount(User user){
		int personalPostAmount = 0;
		String hql = "select count(*) from Post as post where post.user.userId = ?";
		List list = getHibernateTemplate().find(hql,user.getUserId());
		if(list != null || !list.isEmpty() || list.size() != 0)
			personalPostAmount = (int) (long)list.get(0);
		return personalPostAmount;
	}
	
	//�û���¼��飬�����û����������ȡ�û�
	@SuppressWarnings("unchecked")
	public User judgeUser(User user){
		List<User> list;
		String hql = "from User as user where user.userName = ? and user.userPassword = ?";
		list = getHibernateTemplate().find(hql,user.getUserName(),user.getUserPassword());
		if(list == null || list.isEmpty() || list.size() == 0){
			return null;
		}
		else return list.get(0);
	}
	
	//����Ա��¼��飬�����û����������ȡ����Ա
	@SuppressWarnings("unchecked")
	public Manager judgeManager(Manager manager){
		List<Manager> list;
		String hql = "from Manager as manager where manager.managerName = ? and manager.managerPassword = ?";
		list = getHibernateTemplate().find(hql,manager.getManagerName(),manager.getManagerPassword());
		if(list == null || list.isEmpty() || list.size() == 0){
			return null;
		}
		else return list.get(0);
	}

	//����û����Ƿ��Ѿ�����
	@SuppressWarnings("unchecked")
	public boolean judgeUserName(String userName){
		List<User> list;
		String hql = "from User as user where user.userName = ?";
		list = getHibernateTemplate().find(hql,userName);
		if(list == null || list.isEmpty() || list.size() == 0)
			return true;
		else
			return false;
	}
	
	/**����Ϊ������ط���*/	
	
	//��ѯһ��������Ϣ
	public Post findPost(int postId){
		return getHibernateTemplate().get(Post.class, postId);
	}
	
	//���һ������
	public void savePost(Post post){
		getHibernateTemplate().save(post);	
	}
	
	//ɾ��һ������
	public void deletePost(Post post){
		getHibernateTemplate().delete(post);
	}
	
	//����һ������
	public void updatePost(Post post){
		getHibernateTemplate().update(post);
	}
	
	//��ȡ������������
	public int postAmount(){
		String hql = "select count(*) from Post";
		List list = getHibernateTemplate().find(hql);
		if (list == null || list.isEmpty() || list.size() == 0) {
			return 0;
		}else {
			return (int)((long)list.get(0));
		}
	}
	
	//��ѯ��������
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Post> allPost(final int pageNumber ,final int pageSize){
		final String hql = "from Post as post order by post.postTime desc";
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(hql).setFirstResult((pageNumber-1)*pageSize).setMaxResults(pageSize).list();
			}
		});
	}
	
	//��ӻظ�
	public void savePostReply(PostReply postReply){
		getHibernateTemplate().save(postReply);
	}
	
	//��ѯ���лظ�
	@SuppressWarnings("unchecked")
	public List<PostReply> allPostReply(Post post,int pageNumber,int pageSize){
		Session session = getSession();
		List<PostReply> allPostReply;
		String hql = "from PostReply as postReply where postReply.post.postId = ? order by postReply.floor asc";
		Query query = session.createQuery(hql);
		query.setString(0, post.getPostId().toString());
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		allPostReply = query.list();
		session.close();
		return allPostReply;
	}
	//��ѯ�����Ƿ��лظ�
	@SuppressWarnings("unchecked")
	public boolean postHasReplyOrNot(Post post){
		String hql = "from PostReply as postreply where postreply.post.postId = ?";
		List<PostReply> list;
		list = getHibernateTemplate().find(hql,post.getPostId());
		if(list == null || list.isEmpty() || list.size() == 0){
			return false;
		}
		else
			return true;
	}
	//��ѯ��ǰ���¥��
	public int currentFloor(Post post){
		String hql = "select max(postreply.floor) from PostReply as postreply where postreply.post.postId = ?";
		List list;
		list = getHibernateTemplate().find(hql,post.getPostId());
		if(list == null || list.isEmpty() || list.size() == 0){
			return 0;
		}
		else
			return (int) list.get(0);
	}
	/*@SuppressWarnings("unchecked")
	public int currentFloor(Post post){
		String hql = "from PostReply as postReply where postReply.post.postId = ? and postReply.floor = (select max(postReply.floor) from PostReply as postReply)";
		List<PostReply> list;
		list = getHibernateTemplate().find(hql,post.getPostId());
		if(list == null || list.isEmpty() || list.size() == 0){
			return 0;
		}
		else
			return list.get(0).getFloor();
	}*/
	
	//��ѯ���ӻظ���
	
	public int postReplyAmount(Post post) {
		// TODO Auto-generated method stub
		List list;
		String hql = "select count(*) from PostReply as postreply where postreply.post.postId = ?";
		list = getHibernateTemplate().find(hql,post.getPostId());
		if (list == null || list.isEmpty() || list.size() == 0) {
			return 0;
		}else {
			return (int)((long)list.get(0));
		}
	}
	
	//ɾ������ǰ������ӻظ�
	@SuppressWarnings("unchecked")
	public void deleteAllReply(Post post){
		String hql = "from PostReply as postreply where postreply.post.postId = ?";
		List<PostReply> list;
		list = getHibernateTemplate().find(hql,post.getPostId());
		getHibernateTemplate().deleteAll(list);
	}
	//��������ƥ���������
	public int matchedPostAmount(String searchContent){
		List list;
		String hql = "select count(*) from Post as post where post.topic like '%" +searchContent+ "%'";
		list = getHibernateTemplate().find(hql);
		if(list == null || list.size() == 0 || list.isEmpty())
			return 0;
		else
			return (int) ((long)list.get(0));
	}
	//��������
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Post> searchPost(final String searchContent,final int pageNumber ,final int pageSize){
		final String hql = "from Post as post where post.topic like '%"+searchContent+"%' order by post.postTime desc";
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(hql).setFirstResult((pageNumber-1)*pageSize).setMaxResults(pageSize).list();
			}
		});
	}
	
	//ͨ���������ݲ�ѯ���ѱ�
	@SuppressWarnings("unchecked")
	public HotSearch getHotSearchBySearchContent(String searchContent){
		List<HotSearch> list;
		String hql = "from HotSearch as hotsearch where hotsearch.hotSearchContent = ?";
		list = getHibernateTemplate().find(hql,searchContent);
		if(list == null || list.isEmpty() || list.size() == 0){
			return null;
		}
		else return list.get(0);
		
	}
	//�������������Ӵ������ѱ�
	public void saveHotSearch(HotSearch hotSearch){
		getHibernateTemplate().save(hotSearch);
	}
	//�������ѱ�
	public void updateHotSearch(HotSearch hotSearch){
		getHibernateTemplate().update(hotSearch);
	}
	//��ѯ���ѱ���ĳ�������ݵ�ǰ����������
	@SuppressWarnings("unchecked")
	public int beSearchedAmount(String searchContent){
		HotSearch hotSearch;
		hotSearch = getHotSearchBySearchContent(searchContent);
		if(hotSearch == null){
			return 0;
		}
		else
			return hotSearch.getCount();
	}
	//���ѱ�ǰ��
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HotSearch> hotSearchTop5(){
		List<HotSearch> list;
		final String hql = "from HotSearch as hotsearch order by hotsearch.count desc";
		list = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public List<HotSearch> doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				return session.createQuery(hql).setMaxResults(5).list();
			}
		});
		return list;
	}
	/**��ҳ,���ص�ǰҳ������ҳ��*/
	public int[] pageNumber(int postAmount,String pageNumberStr,int pageSize){
		int[] paging = new int[2];
		int pageNumber = 1;
		pageNumber = Integer.parseInt(pageNumberStr);
		if(pageNumber <= 0){
			pageNumber = 1;
		}
		int totalPage = 1;
		if(postAmount > 0){
			totalPage = postAmount%pageSize==0?(postAmount/pageSize):(postAmount/pageSize+1);
		}
		if(pageNumber > totalPage){
			pageNumber = totalPage;
		}
		paging[0] = pageNumber;
		paging[1] = totalPage;
		return paging;
	}

}
