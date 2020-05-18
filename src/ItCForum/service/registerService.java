package ItCForum.service;

import java.sql.SQLException;

import ItCForum.dao.registerDao;
import ItCForum.domain.User;

public class registerService {

	public int registerInfo(User user) throws SQLException {
		  registerDao dao=new registerDao();
	      return dao.registerInfo(user);
	}

	public User checkUser(String username) throws SQLException {
		 registerDao dao=new registerDao();
	     return dao.checkUser(username);
	}

}
