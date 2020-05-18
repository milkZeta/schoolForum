package ItCForum.service;

import java.sql.SQLException;

import ItCForum.dao.mypageDao;
import ItCForum.domain.Mypage;
import ItCForum.domain.Comment;
import ItCForum.domain.CommentReply;
public class mypageService {
	
	public int addPage(Mypage mypage) throws SQLException
	{
		mypageDao dao=new mypageDao();
		return dao.addPage(mypage);
	}
	public int deletePage(String username,String myTitle) throws SQLException
	{
		mypageDao dao=new mypageDao();
		return dao.deletePage(username,myTitle);
	}
	public int addComment(Comment comment) throws SQLException {
		mypageDao dao=new mypageDao();
		return dao.addComment(comment);
	}
	public int addCommentReply(CommentReply commentReply) throws SQLException {
        mypageDao dao=new mypageDao();
        return dao.addCommentReply(commentReply);
	}

}
