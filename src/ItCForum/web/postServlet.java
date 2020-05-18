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

import com.mysql.fabric.Response;

import ItCForum.domain.Comment;
import ItCForum.domain.CommentReply;
import ItCForum.domain.Mypage;
import ItCForum.domain.User;
import ItCForum.service.postService;
@WebServlet("/post")
public class postServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public postServlet() {
        super();
    }
    //页面点击话题连接时，跳转到这个servlet 实现通过话题文字查询具体模块的话题内容 查询成功后到topics页面显示出来
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session=request.getSession();
	    postService service=new postService();
		String opration=request.getParameter("opration");
		String title;
        if(opration!=null) {
	         //操作内容：增加评论的回复
        	title=request.getParameter("commentTitle");
	        if(opration.contains("showCommentReply"))
	        {
	        	showCommentReply(request,service,session);
	        }
	        if(opration.contains("delete"))
	        {
	        	deleteComment(request,service,session);
	        }
	        
        }
        else title=request.getParameter("title");
			 
	        Mypage post=new Mypage();
	        List<Comment> comments=null;
	        List<User> commentUser=null;
	        try {
				post=service.getPostContext(title);
				comments=service.getComment(title);
				commentUser=service.getAvatar(comments);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	       
	       session.setAttribute("post",post);
	       session.setAttribute("comment",comments);
	       session.setAttribute("commentUser",commentUser);
	       
	       RequestDispatcher dispatcher=request.getRequestDispatcher("/post.jsp");
		   dispatcher.forward(request, response);
       
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    protected void showCommentReply(HttpServletRequest request,postService service,HttpSession session)
    {
    	List<CommentReply> commentReply=new ArrayList<>();
    	Map<String,String[]> properties=request.getParameterMap();
		Comment comment=new Comment();
		try {
			BeanUtils.populate(comment, properties);
		} catch (IllegalAccessException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
    	 try {
    		 commentReply=service.showCommentReply(comment);
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
    	 session.setAttribute("commentReply", commentReply);
    }
    protected int deleteComment(HttpServletRequest request,postService service,HttpSession session)
    {
    	Map<String,String[]> properties=request.getParameterMap();
    	Comment comment=new Comment();
    	int result=0;
    	try {
			BeanUtils.populate(comment, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    	try {
			result=service.deleteComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
}
