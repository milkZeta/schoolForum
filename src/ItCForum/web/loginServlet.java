package ItCForum.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ItCForum.domain.Images;
import ItCForum.domain.Message;
import ItCForum.domain.Mypage;
import ItCForum.domain.User;
import ItCForum.service.loginService;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Mypage> page=null;
        HttpSession session=request.getSession();
        loginService service=new loginService();
		List<User> recommendUser=new ArrayList<>();//选出访问量最大的6组用户
		List<Message> messages=new ArrayList<>();
		getTopic(request,service);//
		getRecentImage(request,service);
		try {
			recommendUser=service.searchMaxVisit();
			messages=service.getMessage(8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 session.setAttribute("recommendUser", recommendUser);//
		 session.setAttribute("messages", messages);
		  
		 
		 int i=0;
			request.setCharacterEncoding("UTF-8");
			User result=null;
			String login_status=request.getParameter("login_status");//0-退出,1-用户登录,2-游客登录,3-管理员登录
			
		if(login_status.contains("0")) {
		 try {
			 System.out.print((User)session.getAttribute("user"));
			service.recordLogOutTime((User)session.getAttribute("user"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     session.setAttribute("user",null);
		 RequestDispatcher disptcher=request.getRequestDispatcher("index.jsp");
		 disptcher.forward(request,response);
		}
		if(login_status.contains("1")) {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			try {
				result =service.checkUser(username, password);
				page=service.getMypage();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(page!=null)session.setAttribute("mypages",page);
			if(result!=null)
			{
				//判断是否是管理员登录
				List<User> users=new ArrayList<>();
				if(username.contains("admin")) {
					try {
						users =service.getAllUser();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					session.setAttribute("users", users);
					response.getWriter().write("admin");
				}
				else {
					String autoLogin=request.getParameter("autologin");
					//如果自动登录被选择
					if(autoLogin!=null) {
						if(autoLogin.contains("autologin"))
						{
							Cookie cookie_username=new Cookie("cookie_username",username);
							Cookie cookie_password=new Cookie("cookie_password",password);
							//设置持久化时间
							cookie_username.setMaxAge(60*60);
							cookie_password.setMaxAge(60*60);
							//设置cookie的携带路径
							cookie_username.setPath(request.getContextPath());
							cookie_password.setPath(request.getContextPath());
							response.addCookie(cookie_username);
							response.addCookie(cookie_password);
						}
						session.setAttribute("user", result);
						response.getWriter().write("success");
					}
				}
			}
			else
			{   
				User user=new User();
				user.setShowLoginStatus("账号密码错误，请重新输入！");
				session.setAttribute("user", user);
			}
	      }
		//游客状态登录
	    if(login_status.contains("2")) 
	    {
	    	 RequestDispatcher disptcher=request.getRequestDispatcher("index.jsp");
			 disptcher.forward(request,response);
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	//获取话题、模块的信息
	public void getTopic(HttpServletRequest request,loginService service)
	{
		List<Mypage> modules=null;
		List<List<Mypage>> topics = new ArrayList<List<Mypage>>();
		List<Mypage> todayTopics = new ArrayList<>();
		List<Mypage> t;
		try {
			modules=service.getMypageModule();
			for(Mypage mo : modules)
			{
			t=service.getTopic(mo.getModule());
			if(t!=null)topics.add(t);
			}
			todayTopics=service.getTodayTopics();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 HttpSession session=request.getSession();
		if(modules!=null)session.setAttribute("modules",modules);
		if(topics!=null)session.setAttribute("topics", topics);
		if(todayTopics!=null)session.setAttribute("todayTopics", todayTopics);
  }
	public void getRecentImage(HttpServletRequest request,loginService service)
	{
		 List<Images> recentImage=new ArrayList<>();//获取最新的三张图片路径
		 try {
			recentImage=service.getRecentImage();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 HttpSession session=request.getSession();
		 session.setAttribute("recentImage",recentImage);
	}
}
