package ItCForum.service;

import java.sql.SQLException;
import java.util.List;

import ItCForum.dao.loginDao;
import ItCForum.dao.topicDao;
import ItCForum.domain.Mypage;

public class topicService {

	public List<Mypage> getTopicDetails(String module) throws SQLException {
		topicDao dao=new topicDao();
		return dao.getTopicDetails(module);
		
	}
	public List<Mypage> getTopic(String module) throws SQLException {
		loginDao dao = new loginDao();
		return dao.getTopic(module);
	}


}
