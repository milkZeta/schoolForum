package ItCForum.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ItCForum.domain.Images;
import ItCForum.domain.Mypage;
import ItCForum.domain.Page;
import ItCForum.domain.Relation;
import ItCForum.domain.User;
import ItCForum.utils.DatasourceUtils;

public class searchMypostDao {
	 //根据相关用户信息找到数据库里的数据，点击第page页搜索第page页的内容
		public List<Mypage> getPostData(String role,Page page) throws SQLException {
			 int currentPage=page.getCurrentPage();
			 int pageCount=page.getPageCount();
			 int begin=(currentPage-1)*pageCount;//起始索引
			 String sql="select * from mypage where username='"+role+"' limit "+begin+","+page.getPageCount();
			 QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
			  return (List<Mypage>)runner.query(sql, new BeanListHandler<Mypage>(Mypage.class));
		}

	@SuppressWarnings("unchecked")
	public int getTotalEssay(String role) throws SQLException {
		String sql="select count(*) from mypage where username='"+role+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		@SuppressWarnings("rawtypes")
		long query= (long) runner.query(sql,new ScalarHandler());
		return (int)query;
	}

	public User getUserInfo(String role) throws SQLException {
		String sql="select * from user where username='"+role+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql,new BeanHandler<User>(User.class));
	}
	public List<Images> getRecentImage() throws SQLException {
		String sql="select * from images order by id desc limit 0,3";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql,new BeanListHandler<Images>(Images.class));
	}

	public int queryRelation(String role, User user) throws SQLException {
		String sql="select * from relation where fellow='"+role+"' and fens='"+user.getUsername()+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		if( runner.query(sql,new BeanHandler<Relation>(Relation.class))!=null)return 1;
		else return 0;
	}


}
