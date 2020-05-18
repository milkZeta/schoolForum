package ItCForum.service;

import java.sql.SQLException;
import java.util.List;

import ItCForum.dao.postDao;
import ItCForum.domain.Comment;
import ItCForum.domain.CommentReply;
import ItCForum.domain.Mypage;
import ItCForum.domain.User;

public class postService {
	 public Mypage getPostContext(String title) throws SQLException
	 {
		 postDao dao=new postDao();
		return dao.getPostContext(title);
	 }

	public List<Comment> getComment(String title) throws SQLException {
		postDao dao=new postDao();
		return dao.getComment(title);
	}

	public List<User> getAvatar(List<Comment> comment) throws SQLException {
		postDao dao=new postDao();
		return dao.getAvatar(comment);
	}

	public List<CommentReply> showCommentReply(Comment comment) throws SQLException {
		postDao dao=new postDao();
		return dao.showCommentReply(comment);
	}

	public int deleteComment(Comment comment) throws SQLException {
		postDao dao=new postDao();
		return dao.deleteComment(comment);
	}

}
