package ItCForum.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import ItCForum.domain.User;
import ItCForum.service.registerService;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public registerServlet() {
        super();
    }

	//设置注册头像
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //接收注册页面的数据，放到数据库中
	        User user=new User();
	        String ip=InetAddress.getLocalHost().getHostAddress();
	        HttpSession session=request.getSession();
	        registerService service=new registerService();
	        if(request.getParameter("opration").contains("check"))
	        {
	        String username=null;
	        username=request.getParameter("username");
	        User u=null;
	        try {
		    	 //更新成功
				 u=service.checkUser(username);
				} catch (SQLException e) {
			 	 // 更新失败
				 e.printStackTrace();
				}
	        if(u!=null)
	        {
	        	response.getWriter().write("exist");
	        }
	        }
	        else {
		        int s=0;
		        user.setLastIp(ip);
		        Map<String, String[]> properties=request.getParameterMap();
		        try {
					BeanUtils.populate(user, properties);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			   try {
		    	 //更新成功
				 s=service.registerInfo(user);
				} catch (SQLException e) {
			 	 // 更新失败
				 e.printStackTrace();
				}
			   if(s!=0) {
			    //重定向到登录页面
				session.setAttribute("user", user);
	            RequestDispatcher dispatcher=request.getRequestDispatcher("avatar.jsp");
	            dispatcher.forward(request, response);
			   }
	        }
		   
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
