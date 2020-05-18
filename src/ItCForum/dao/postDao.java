package ItCForum.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ItCForum.domain.Comment;
import ItCForum.domain.CommentReply;
import ItCForum.domain.Mypage;
import ItCForum.domain.User;
import ItCForum.utils.DatasourceUtils;

public class postDao {

	public Mypage getPostContext(String title) throws SQLException {
		String sql="select * from mypage where myTitle= '"+title+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		 return (Mypage) runner.query(sql, new BeanHandler<Mypage>(Mypage.class));
	}

	public List<Comment> getComment(String title) throws SQLException {
		String sql="select * from comment where commentTitle= ?";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return (List<Comment>) runner.query(sql, new BeanListHandler<Comment>(Comment.class),title);
	}

	public List<User> getAvatar(List<Comment> comment) throws SQLException {
		String sql=null;
		List<User> result = new ArrayList<>();
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
	   for(int i=0;i<comment.size();i++) {
        sql="select * from user where username='"+comment.get(i).getUsername()+"'";		   
        result.add(runner.query(sql,new BeanHandler<User>(User.class)));
	   }
	   return result;
	}
    //查询评论的回复信息
	public List<CommentReply> showCommentReply(Comment comment) throws SQLException {
		String sql="select * from commentreply where postname='"+comment.getCommentTitle()+"' and commentFloor='"+comment.getCommentFloor()+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<CommentReply>(CommentReply.class));
	}

	public int deleteComment(Comment comment) throws SQLException {
       String sql="delete from comment where commentTitle='"+comment.getCommentTitle()+"' and commentFloor='"+comment.getCommentFloor()+"'";
       String sql1="update comment set commentFloor=commentFloor-1 where commentFloor>'"+comment.getCommentFloor()+"' and commentTitle='"+comment.getCommentTitle()+"'";
       QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
       int result=runner.update(sql);
        runner.update(sql1);
        return result;
	}
    
}
