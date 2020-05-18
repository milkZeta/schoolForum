package ItCForum.common;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import java.sql.Connection;
public class dblayer {
   /*  @Test
     public void test()
     {
    	 Connection conn=connect("jdbc:mysql://localhost:3306/web08","root","199511");
    	 String username="hehahalo";
    	 String upassword="12334";
    	 String sql="insert into user values('"+2+"','"+username+"','"+upassword+"','"+12+"');";
    	  excuteUpdate(conn,sql);
     }*/
	

     //建立驱动、连接
     public Connection connect(String user,String password)
     {
    	 try
    	 {
    		 String url="jdbc:mysql://localhost:3306/web08";
    		 Class.forName("com.mysql.jdbc.Driver");
    		 Connection conn=DriverManager.getConnection(url, user, password);
    		 return conn;
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 return null;
     }
     //执行查询sql
     public ResultSet executeSelect(Connection conn,String sql)
     {
    	try {
			Statement stmt=conn.createStatement();
			ResultSet result=stmt.executeQuery(sql);
			return result;
		    } 
    	catch (SQLException e) {
			e.printStackTrace();
		    }
    	System.out.print(sql+"没有执行成功");
    	return null;
     }
   //执行更新sql
     public void executeUpdate(Connection conn,String sql)
     {
    	try {
			Statement stmt=conn.createStatement();
			boolean result=stmt.execute(sql);
			System.out.print("update执行完");
			return ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	System.out.print(sql+"没有执行成功");
     }
}














