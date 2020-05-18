package ItCForum.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ItCForum.domain.Message;
import ItCForum.domain.Mypage;
import ItCForum.domain.User;
import ItCForum.utils.DatasourceUtils;

public class loginDao {
	
	public User checkUser(String username,String password) throws SQLException 
	{
		User user=new User();
		String sql="select * from user where username=? and password=?";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
			//BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中
			user=runner.query(sql,new BeanHandler<User>(User.class), username,password);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Mypage> getMypage() throws SQLException {
		String sql="select * from mypage";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return (List<Mypage>)runner.query(sql, new BeanListHandler<Mypage>(Mypage.class));
		
	}
   
	//获得话题模块内容
	@SuppressWarnings("unchecked")
	public List<Mypage> getMypageModule() throws SQLException {
		List<Mypage> module=null;
		String sql="select distinct(module) from mypage";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
			//BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中
			module=(List<Mypage>) runner.query(sql,new BeanListHandler<Mypage>(Mypage.class));
		return module;
	}

	public List<Mypage> getTopic(String module) throws SQLException {
		String sql="select * from mypage where module='"+module+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return (List<Mypage>)runner.query(sql, new BeanListHandler<Mypage>(Mypage.class));
	}

	public List<User> searchMaxVisit() throws SQLException {
		String sql="select * from user order by id desc limit 6" ;
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<User>(User.class));
	}

	public List<Mypage> getTodayTopics() throws SQLException {
		String sql="select * from mypage order by submitTime desc limit 10" ;
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Mypage>(Mypage.class));
	}

	public void recordLogOutTime(User user) throws SQLException {
		SimpleDateFormat sdf=new SimpleDateFormat();
		String lastLogOutTime=sdf.format(new Date());
		String sql="update user set lastLogin='"+lastLogOutTime+"' where username='"+user.getUsername()+"'" ;
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		runner.update(sql);
	}

	public List<User> getAllUser() throws SQLException {
		String sql="select * from user" ;
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<User>(User.class));
	}

	public List<Message> getMessage(int count) throws SQLException {
	   String sql="select * from message order by publishTime desc limit 0,"+count;
       QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
       return runner.query(sql,new BeanListHandler<Message>(Message.class));
	}

}