package ItCForum.service;

import java.sql.SQLException;
import java.util.List;

import ItCForum.dao.fensDao;
import ItCForum.domain.Relation;

public class fensService {

	public List<Relation> getFens(String username) throws SQLException {
		fensDao dao=new fensDao();
		return dao.getFens(username);
	}

	public List<Relation> getFellow(String username) throws SQLException {
		fensDao dao=new fensDao();
		return dao.getFellow(username);
	}

	public int addFellow(String username, String fellowObj,String fellowAvatarPath,String fensAvatarPath) throws SQLException {
		fensDao dao=new fensDao();
	    return dao.addFellow(username,fellowObj,fellowAvatarPath,fensAvatarPath);
	}


}
