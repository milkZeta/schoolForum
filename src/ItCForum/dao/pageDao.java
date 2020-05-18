package ItCForum.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ItCForum.domain.Mypage;
import ItCForum.utils.DatasourceUtils;

public class pageDao {

	public List<Mypage> getEssayList(int currentPage, int pageCount) throws SQLException {
		String sql="select * from mypage limit ?,?";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Mypage>(Mypage.class),currentPage,pageCount);
	}

}
