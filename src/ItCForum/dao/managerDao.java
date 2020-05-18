package ItCForum.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ItCForum.domain.Message;
import ItCForum.domain.User;
import ItCForum.utils.DatasourceUtils;

public class managerDao {

	public int saveUserInfo(User user) throws SQLException {
       String sql="update user set password='"+user.getPassword()+"',lastLogin='"+user.getLastLogin()+"', birthday='"+user.getBirthday()+"',sex='"+user.getSex()+"' where username='"+user.getUsername()+"'";
       QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
       return runner.update(sql);
	}
    public int saveMessage(String message,String messageType) throws SQLException
    {
    	SimpleDateFormat sdf=new SimpleDateFormat();
		String publishTime=sdf.format(new Date());
		String sql="insert into message (message,publishtime,messageType,messageFloor) values (?,?,?,?)";
		String sql1="select count(*) from message";
        QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
        long messageFloor=(long)(runner.query(sql1, new ScalarHandler()))+1;
        return runner.update(sql,message,publishTime,messageType,messageFloor);
    }
	public List<Message> getMessage() throws SQLException {
	   String sql="select * from message order by publishTime desc";
       QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
       return runner.query(sql,new BeanListHandler<Message>(Message.class));
	}
	public int deleteMessage(String messageFloor) throws SQLException {
		String sql="delete from message where messageFloor='"+messageFloor+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		int result= runner.update(sql);
		if(messageFloor!="1") {
		String sql1="update message set messageFloor=messageFloor-1 where messageFloor>'"+messageFloor+"'";
	    runner.update(sql1);}
	    return result;
	}
	public int userFloor(String userFloor) throws SQLException {
		int floor=Integer.parseInt(userFloor)-1;
		String sql1="select username from user order by id asc limit "+floor+",1";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		String username=(String) runner.query(sql1,new ScalarHandler());
		String sql="delete from user where username='"+username+"'";
	    int result= runner.update(sql);
	    return result;
	}

}
