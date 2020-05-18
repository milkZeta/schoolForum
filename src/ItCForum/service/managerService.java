package ItCForum.service;

import java.sql.SQLException;
import java.util.List;

import ItCForum.dao.managerDao;
import ItCForum.domain.Message;
import ItCForum.domain.User;

public class managerService {

	public int saveUserInfo(User user) throws SQLException {
		managerDao dao=new managerDao();
		return dao.saveUserInfo(user);
	}
	public int saveMessage(String message,String messageType) throws SQLException {
		managerDao dao=new managerDao();
		return dao.saveMessage(message,messageType);
	}
	public List<Message> getMessage() throws SQLException {
		managerDao dao=new managerDao();
		return dao.getMessage();
	}
	public int deleteMessage(String messageFloor) throws SQLException {
		managerDao dao=new managerDao();
		return dao.deleteMessage(messageFloor);
	}
	public int deleteUser(String userFloor) throws SQLException {
		managerDao dao=new managerDao();
		return dao.userFloor(userFloor);
	}
}
