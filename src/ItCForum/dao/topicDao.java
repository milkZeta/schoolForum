package ItCForum.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ItCForum.domain.Mypage;
import ItCForum.utils.DatasourceUtils;

public class topicDao {

	public List<Mypage> getTopicDetails(String module) throws SQLException {
		String sql="select myTitle from mypage where module= ?";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return (List<Mypage>) runner.query(sql, new BeanListHandler<Mypage>(Mypage.class),module);
	}
	public List<Mypage> getTopic(String module) throws SQLException {
		String sql="select * from mypage where module='"+module+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return (List<Mypage>)runner.query(sql, new BeanListHandler<Mypage>(Mypage.class));
	}

}
