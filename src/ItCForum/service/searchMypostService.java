package ItCForum.service;

import java.sql.SQLException;
import java.util.List;

import ItCForum.dao.searchMypostDao;
import ItCForum.domain.Images;
import ItCForum.domain.Mypage;
import ItCForum.domain.Page;
import ItCForum.domain.User;

public class searchMypostService {
//	//第一页访问时取得数据
//	public List<Mypage> getFirstPage(User user) throws SQLException
//	{
//		searchMypostDao dao=new searchMypostDao();
//		return dao.getFirstPage(user);
//	}

	public int getTotalEssay(String role) throws SQLException {
		searchMypostDao dao=new searchMypostDao();
		return dao.getTotalEssay(role);
	}
	//不是第一页时取得的数据
	public List<Mypage> getPageData(String role,Page page) throws SQLException
	{
		searchMypostDao dao=new searchMypostDao();
		return dao.getPostData(role,page);
	}
	public User getUserInfo(String role) throws SQLException {
		searchMypostDao dao=new searchMypostDao();
		return dao.getUserInfo(role);
	}
	//查询是否存在登录用户是否已经关注被访问的用户
	public int queryRelation(String role, User user) throws SQLException {
		searchMypostDao dao=new searchMypostDao();
		return dao.queryRelation(role,user);
	}
	

}
