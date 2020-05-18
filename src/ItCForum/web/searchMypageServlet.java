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

import ItCForum.dao.fensDao;
import ItCForum.domain.Mypage;
import ItCForum.domain.Page;
import ItCForum.domain.Relation;
import ItCForum.domain.User;
import ItCForum.service.searchMypostService;

@WebServlet("/searchMypage")
public class searchMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public searchMypageServlet() {
        super();
    }
    
    //访问页面时，查询数据数据,将page传给request
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 searchMypostService service =new searchMypostService();
	
		//如果是点击用户的名字，跳转到用户页面
		 Page page=new Page();
		 int totalpages = 0;
		 int totalEssay=0;
		 int currentPage=1;
		 fensDao dao=new fensDao();
		 List<Relation> fens = new ArrayList<>();
		 List<Relation> fellow = new ArrayList<>();
		 String str=request.getParameter("currentPage");
		 
		 if(str!=null)currentPage=Integer.parseInt(str);
		 page.setCurrentPage(currentPage);
		 
		 String role=request.getParameter("role");//如果页面传来的不是正在登陆的用户名，则不显示编写诶帖子的页面
		 if(role==null) {
			 if((User)session.getAttribute("role")!=null)
			   role=((User)session.getAttribute("role")).getUsername();
			 else role=((User)session.getAttribute("user")).getUsername();
		 }
         User userInfo=null;//返回点击去访问的用户的信息，不是登陆的用户
           //存储查询返回的结果
         List<Mypage> pages = null;
         int relation=0;
         try {
        	 totalEssay=service.getTotalEssay(role);
        	 pages=service.getPageData(role,page);
        	 userInfo=service.getUserInfo(role);
        	 fens=dao.getFens(role);
        	 fellow=dao.getFellow(role);
        	 relation=service.queryRelation(role,(User)session.getAttribute("user"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
       //将当前路径放置到集合集合中
 		
         totalpages=(int) Math.ceil(1.0*totalEssay/page.getPageCount());
         page.setTotalEssay(totalEssay);
         page.setTotalPages(totalpages);
        session.setAttribute("relation",relation);
        session.setAttribute("role",userInfo);
		session.setAttribute("mypages", pages);
		session.setAttribute("pages", page);
    	session.setAttribute("contexttype", null);
    	session.setAttribute("fens",fens);
    	session.setAttribute("fellow",fellow);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/mypage.jsp");  
        dispatcher.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
