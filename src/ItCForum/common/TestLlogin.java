package ItCForum.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class TestLlogin {
	
	@Test
	public void testL() {
		try {
		login1("liming","1234");
	}catch(Exception e)
		{
		e.printStackTrace();
		}
	}
    public void login(String username,String password) throws ClassNotFoundException, SQLException
    {
    	//这侧驱动
    	Class.forName("com.mysql.jdbc.Driver");
    	//建立连接
    	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/web08","root","199511");
    	//创建SQL对象
    	Statement stmt=conn.createStatement();
    	//创建sql
    	String sql="select *  from user where "+"uname='"+username+"' and upassword='"+password+"'";
    	//执行sql
    	ResultSet result=stmt.executeQuery(sql);
    	//对结果集进行处理
    	if(result.next())
    	{
    		System.out.println("恭喜您"+username+"登陆成功");
    		System.out.println(sql);
    	}
    	else System.out.println("不存在的用户名");
    	//释放资源
    	if(result!=null)result.close();
    	if(stmt!=null)stmt.close();
    	if(conn!=null)conn.close();
    }
    
      
       public void login1(String username,String upassword) throws ClassNotFoundException, SQLException
       {
    	   Class.forName("com.mysql.jdbc.Driver");
    	   //创建连接
    	   Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/web08","user","199511");
    	   //编写sql语句
    	   String sql="select * from user where uname=? and upassword=?";
    	   //创建预处理对象
    	   PreparedStatement pstmt=conn.prepareStatement(sql);
    	   //设置参数
    	   pstmt.setString(1, username);
    	   pstmt.setString(2,upassword);
    	   ResultSet res=pstmt.executeQuery();
    	   if(res.next())
    	   {
    		   System.out.println("恭喜您"+username+"登陆成功！");
    		   System.out.println(sql);
    	   }
    	   else System.out.println("登陆失败！");
    	   if(res!=null)res.close();
    	   if(pstmt!=null)pstmt.close();
    	   if(conn!=null)conn.close();
    	   
       }
}



