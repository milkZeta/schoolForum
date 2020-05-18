package ItCForum.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import ItCForum.dao.handleImageDao;
import ItCForum.domain.Comment;
import ItCForum.domain.CommentReply;
import ItCForum.domain.Mypage;
import ItCForum.domain.User;
import ItCForum.service.mypageService;

@WebServlet("/mypage")
public class mypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public mypageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		//mypage提交过来数据后
		User user=(User) session.getAttribute("user");
		String opration=request.getParameter("opration");//操作类型，添加页面，添加评论。添加评论回复
		Mypage mypage=new Mypage();
		//这里必须先登录，否则user为空
		String username=user.getUsername();
		mypage.setUsername(username);
		int result = 0;
		
         if(opration.contains("addPage"))
         {
			 result=addPage(request,mypage,session);
         }
		
         if(opration.contains("delete"))
         {
			 result=deletePage(request,username);
         }
         if(opration.contains("addComment"))
         {
        	 result=addComment(request,session);
         }
         //增加评论的回复
         if(opration.contains("addReply"))
         {
        	 result=addReply(request,session);
         }
         if(result!=0)
		    {
			 RequestDispatcher dispatcher=request.getRequestDispatcher("searchMypage");
			 dispatcher.forward(request, response);
		    }
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//添加文章
	public int addPage(HttpServletRequest request,Mypage mypage,HttpSession session)
	{
	   //获取系统当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		mypage.setSubmitTime(sdf.format(new Date()));
		Map<String,String[]> properties=request.getParameterMap();
		try {
			BeanUtils.populate(mypage, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//替换空格和换行字符,textArea和html中的不一样
		String myPost=null;
		if(mypage.getMyPost()!=null) {
		  myPost=replaceStr(mypage.getMyPost());
		}
		if(myPost!=null)mypage.setMyPost(myPost);
		int result=0;
	    mypageService service=new mypageService();
	    try {
			result=service.addPage(mypage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    mypage.setResult(result);
	    session.setAttribute("mypage", mypage);
	   return result;
	}
	//删除帖子
	public int deletePage(HttpServletRequest request,String username)
	{
		mypageService service=new mypageService();
		String myTitle=request.getParameter("myTitle");
		int result=0;
		try {
			result=service.deletePage(username,myTitle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//增加评论
	public int addComment(HttpServletRequest request,HttpSession session)
	{
		mypageService service=new mypageService();
        Map<String,String[]> properties=request.getParameterMap();
        User user=(User) session.getAttribute("user");
        Comment comment = new Comment();
        comment.setUsername(user.getUsername());
        int result=0;
		try {
			BeanUtils.populate(comment, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			result=service.addComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//增加评论的回复
	public int addReply(HttpServletRequest request,HttpSession session)
	{
		CommentReply commentReply=new CommentReply();
		mypageService service=new mypageService();
		Map<String,String[]> properties=request.getParameterMap();
		User user=(User)session.getAttribute("user");
		commentReply.setReplyUser(user.getUsername());
		int result=0;
		try {
			BeanUtils.populate(commentReply, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		try {
			 result=service.addCommentReply(commentReply);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	
	//若是有空格则进行替换
	public String replaceStr(String myPost)
	{
		myPost.replace("&nbsp", " ").replace("\r", "<br/>");
		return myPost;
	}
	
}
