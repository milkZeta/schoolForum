package ItCForum.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ItCForum.domain.Relation;
import ItCForum.domain.User;
import ItCForum.service.fensService;

@WebServlet("/fens")
public class fensServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public fensServlet() {
        super();
    }

    //处理图像路径的思路：先把图像路径存储到数据库中，再根据用户名取出
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session=request.getSession();
       User role=(User) session.getAttribute("role");
       User user=(User)session.getAttribute("user");
       String username=role.getUsername();
       fensService service=new fensService();
       String contextType=request.getParameter("contexttype");//0-点击粉丝页面，1-点击关注页面
       List<Relation> fens = null;
       List<Relation> fellow = null ;
       if(contextType.contains("0")) {
	       try {
			fens=service.getFens(username);
			fellow=(List<Relation>)session.getAttribute("fellow");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }
       else if(contextType.contains("1")) {
	       try {
			fellow=service.getFellow(username);
			fens=(List<Relation>)session.getAttribute("fens");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }
       else
       {
    	   //都是登录用户作为粉丝去加关注
    	  int result=addFellow(user.getUsername(),request.getParameter("fellowObj"),request.getParameter("avatarPath"),((User)session.getAttribute("user")).getAvatarPath());
    	  RequestDispatcher dispatcher=request.getRequestDispatcher("/mypage.jsp");  
          dispatcher.forward(request,response);
          response.getWriter().write(result);
       }
       session.setAttribute("contexttype", contextType);//返回页面进行判断
       session.setAttribute("fens", fens);
       session.setAttribute("fellow", fellow);
       response.getWriter().write("success");
       
	   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public int addFellow(String username,String fellowObj,String fellowAvatarPath,String fensAvatarPath)
	{
	  fensService service=new fensService();
	  int result = 0;
	  try {
		result=service.addFellow(username,fellowObj,fellowAvatarPath,fensAvatarPath);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	  return result;
	}
	

}
