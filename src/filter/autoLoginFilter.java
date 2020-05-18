package filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ItCForum.domain.User;
import ItCForum.service.loginService;

public class autoLoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		   HttpServletRequest req=(HttpServletRequest)request;
		   HttpServletResponse resp=(HttpServletResponse)response;
            //获得cookie中的用户名和密码去进行登录
		    Cookie[] cookies=req.getCookies();
		    String username=null;
		    String password=null;
		    loginService service=new loginService();
		    HttpSession session=req .getSession();
		    User result=null;
		    if(cookies!=null)
		    {
		    	for(Cookie cookie:cookies)
		    	{
		    		if(cookie.getName().equals("username"))
		    		{
		    			username=cookie.getValue();
		    		}
		    		if(cookie.getName().equals("password"))
		    		{
		    			password=cookie.getValue();
		    		}
		    	}
		    }
		    if(username!=null&&password!=null)
		    {
		    	try {
					result =service.checkUser(username, password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
		    if(result!=null)
		    {
		    	session.setAttribute("user", result);
		    }
		    chain.doFilter(req, resp);
	}

}
