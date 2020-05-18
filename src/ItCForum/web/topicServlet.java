package ItCForum.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ItCForum.domain.Mypage;
import ItCForum.service.loginService;
import ItCForum.web.loginServlet;
import ItCForum.service.topicService;

@WebServlet("/topic")
public class topicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public topicServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String refer=request.getHeader("Referer");
		String opration=request.getParameter("opration");
		if(refer.contains("searchMypage")||refer.contains("mypage")||opration!=null) {
			loginServlet serv=new loginServlet();
			loginService service=new loginService();
		    serv.getTopic(request,service);
		    RequestDispatcher disptcher=request.getRequestDispatcher("index.jsp");
			 disptcher.forward(request,response);
	}
		else acrodeModuleFind(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    protected void acrodeModuleFind(HttpServletRequest request, HttpServletResponse response)
    {
    	 String module=request.getParameter("module");
         List<Mypage> topics=new ArrayList<>();
         topicService service=new topicService();
         try {
 			topics=service.getTopicDetails(module);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
        HttpSession session=request.getSession();
        session.setAttribute("topics",topics);
        session.setAttribute("module",module);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/topics.jsp");
        try {
			dispatcher.forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
