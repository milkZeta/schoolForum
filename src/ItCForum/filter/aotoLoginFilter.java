package ItCForum.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ItCForum.domain.User;
import ItCForum.service.loginService;

/**
 * Servlet Filter implementation class aotoLoginFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/aotoLogin" })
public class aotoLoginFilter implements Filter {

    public aotoLoginFilter() {
    }


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		Cookie[] cookies=httpRequest.getCookies();
		String cookie_username=null;
		String cookie_password=null;
		User user=null;
		HttpSession session=httpRequest.getSession();
		if(cookies!=null) {
		for(Cookie cookie:cookies)
		{
			if("cookie_username".equals(cookie.getName()))
			{
				cookie_username=cookie.getValue();
			}
			if("cookie_password".equals(cookie.getName()))
			{
		        cookie_password=cookie.getValue();
			}
	    }
	  }
		if(cookie_username!=null&&cookie_password!=null)
		{
			loginService service=new loginService();
			try {
			 user=service.checkUser(cookie_username, cookie_password);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			session.setAttribute("user", user);
		}
		System.out.print("haha");
		chain.doFilter(httpRequest, httpResponse);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
