package ItCForum.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import ItCForum.domain.Message;
import ItCForum.domain.User;
import ItCForum.service.loginService;
import ItCForum.service.managerService;

@WebServlet("/messageManager")
public class messageManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public messageManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> properties=request.getParameterMap();
        managerService service=new managerService();
        User user=new User();
        int result=0;
        try {
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        
       if(request.getParameter("opration").contains("saveUser")) {
	       try {
				result=service.saveUserInfo(user);
			} catch (SQLException e) {
				e.printStackTrace();
		    }
	       if(result!=0)response.getWriter().write("success");
	       else response.getWriter().write("fail");
       }
      if(request.getParameter("opration").contains("getMessage")) {
    	   HttpSession session=request.getSession();
    	   List<Message> messages=new ArrayList<>();
    	   try {
			messages=service.getMessage();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	   session.setAttribute("messages",messages);
    	   RequestDispatcher disptcher=request.getRequestDispatcher("messageManager.jsp");
    	   disptcher.forward(request, response);
       }
       if(request.getParameter("opration").contains("saveMessage")) {
    	   String message=request.getParameter("message");
    	   String messageType=request.getParameter("messageType");
	       try {
				result=service.saveMessage(message,messageType);
			} catch (SQLException e) {
				e.printStackTrace();
		    }
	       if(result!=0)response.getWriter().write("成功");
	       else response.getWriter().write("失败");
       }
       
       if(request.getParameter("opration").contains("deleteMessage")) {
    	   String messageFloor=request.getParameter("messageFloor");
	       try {
				result=service.deleteMessage(messageFloor);
			} catch (SQLException e) {
				e.printStackTrace();
		    }
	       if(result!=0) response.getWriter().write("成功");
	       else response.getWriter().write("失败");
	      
       }
       
       if(request.getParameter("opration").contains("deleteUser")) {
    	   String userFloor=request.getParameter("userFloor");
	       try {
				result=service.deleteUser(userFloor);
			} catch (SQLException e) {
				e.printStackTrace();
		    }
	       if(result!=0)response.getWriter().write("成功");
	       else response.getWriter().write("失败");
       }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
