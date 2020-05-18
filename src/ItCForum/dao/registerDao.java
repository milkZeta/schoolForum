package ItCForum.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import ItCForum.domain.User;
import ItCForum.utils.DatasourceUtils;

public class registerDao {

	public int registerInfo(User user) throws SQLException {
				SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
				int id=10;
				//注意表名和values后面有一个空格
        		String sql="insert into user (username,password,birthday,sex) values (?,?,?,?)";
        		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
        		//数据库里的时间格式是DATE为什么可以String类型可以直接传输到数据库里
        		return runner.update(sql,user.getUsername(),user.getPassword(),user.getBirthday(),user.getSex());
	}

	public User checkUser(String username) throws SQLException {
		String sql="select * from user where username='"+username+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql,new BeanHandler<User>(User.class));
	}

}
