package ItCForum.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import ItCForum.domain.Comment;
import ItCForum.domain.CommentReply;
import ItCForum.domain.Mypage;
import ItCForum.utils.DatasourceUtils;

public class mypageDao {

	public int  addPage(Mypage mypage) throws SQLException {
	    //将发表的帖子更新到数据库
		//注意表名和values后面有一个空格
		String sql="insert into mypage (username,submittime,mytitle,mypost,module) values (?,?,?,?,?)";
	    QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
	    return runner.update(sql, mypage.getUsername(),mypage.getSubmitTime(),mypage.getMyTitle(),mypage.getMyPost(),mypage.getModule());
     	
	}
	public int  deletePage(String username,String myTitle) throws SQLException {
	    //将发表的帖子更新到数据库
		//注意表名和values后面有一个空格
		String sql="delete from mypage where username='"+username+ "' and myTitle="+myTitle;
	    QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
	    return runner.update(sql);
     	
	}
	//插入一条评论则更新层数
	public int addComment(Comment comment) throws SQLException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		comment.setCommentTime(sdf.format(new Date()));
	    String sql="insert into comment (username,commentTitle,commentContext,commentUsername,commenttime,commentFloor,commentReplyCount) values (?,?,?,?,?,?,?)";
	    String sql1="select count(*) from comment where commentTitle='"+comment.getCommentTitle()+"'";
	    QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		long floor=(long) runner.query(sql1, new ScalarHandler())+1;
	    return runner.update(sql, comment.getUsername(),comment.getCommentTitle(),comment.getCommentContext(),comment.getCommentUsername(),comment.getCommentTime(),floor,0);
	  
	}
	//更新一条回复则更新评论中的评论回复数
	public int addCommentReply(CommentReply commentReply) throws SQLException {
		String sql="insert into commentreply (postname,commentuser,commentfloor,replyuser,replycontent) values (?,?,?,?,?)";
		String sql1="select count(*) from commentreply where postname='"+commentReply.getPostName()+"' and commentFloor='"+commentReply.getCommentFloor()+"'";
		QueryRunner runner=new QueryRunner(DatasourceUtils.getDataSource());
		long count=(long) runner.query(sql1,new ScalarHandler())+1;
		String sql2="update comment set commentReplyCount='"+count+"' where commenttitle='"+commentReply.getPostName()+"' and commentfloor='"+commentReply.getCommentFloor()+"'";   
	    
	    if(runner.update(sql2)!=0&&runner.update(sql,commentReply.getPostName(),commentReply.getCommentUser(),commentReply.getCommentFloor(),commentReply.getReplyUser(),commentReply.getReplyContent())!=0)
	      return 1;
	    else return 0;
	}

}
