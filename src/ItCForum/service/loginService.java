package ItCForum.service;

import java.sql.SQLException;
import java.util.List;

import ItCForum.dao.loginDao;
import ItCForum.dao.managerDao;
import ItCForum.dao.searchMypostDao;
import ItCForum.domain.Images;
import ItCForum.domain.Message;
import ItCForum.domain.Mypage;
import ItCForum.domain.User;

public class loginService {
	public User checkUser(String username,String password) throws SQLException 
	{
		loginDao dao = new loginDao();
		return dao.checkUser(username,password);
	}

	public List<Mypage> getMypage() throws SQLException {
		loginDao dao = new loginDao();
		return dao.getMypage();
	}

	public List<Mypage> getMypageModule() throws SQLException {
		loginDao dao = new loginDao();
		return dao.getMypageModule();
	}

	public List<Mypage> getTopic(String module) throws SQLException {
		loginDao dao = new loginDao();
		return dao.getTopic(module);
	}
	public List<Images> getRecentImage() throws SQLException {
		searchMypostDao dao=new searchMypostDao();
		return dao.getRecentImage();
	}

	public List<User> searchMaxVisit() throws SQLException {
		loginDao dao = new loginDao();
		return dao.searchMaxVisit();
	}

	public List<Mypage> getTodayTopics() throws SQLException {
		loginDao dao = new loginDao();
		return dao.getTodayTopics();
	}

	public void recordLogOutTime(User user) throws SQLException {
		loginDao dao = new loginDao();
		dao.recordLogOutTime(user);
		
	}

	public List<User> getAllUser() throws SQLException {
		loginDao dao = new loginDao();
		return dao.getAllUser();
	}
	public List<Message> getMessage(int count) throws SQLException {
		loginDao dao=new loginDao();
		return dao.getMessage(count);
	}
}
