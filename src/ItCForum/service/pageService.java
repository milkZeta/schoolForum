package ItCForum.service;

import java.sql.SQLException;
import java.util.List;

import ItCForum.dao.pageDao;
import ItCForum.domain.Mypage;

public class pageService {

	public List<Mypage> getEssayList(int currentPage, int pageCount) throws SQLException {
             pageDao dao=new pageDao();
             return dao.getEssayList(currentPage,pageCount);
	}

}
