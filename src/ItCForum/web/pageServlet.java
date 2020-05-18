package ItCForum.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import ItCForum.domain.Mypage;
import ItCForum.domain.Page;
import ItCForum.service.pageService;


@WebServlet("/page")
public class pageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public pageServlet() {
        super();
    }
    //当点击页数时跳转进来，第一次访问交给searchMypost处理
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           Page page=new Page();
           //获得每页显示的数量
           int pageCount=page.getPageCount();
           int totalPages=0;
           List<Mypage> pageContext = null;
           Map<String, String[]> properties=request.getParameterMap();
           try {
			BeanUtils.populate("page", properties);
		    } catch (IllegalAccessException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		   }
           pageService service=new pageService();
           try {
			pageContext=service.getEssayList(page.getCurrentPage(),pageCount);
		   } catch (SQLException e) {
			e.printStackTrace();
		  }
           HttpSession session=request.getSession();
           session.setAttribute("mypages", pageContext);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
