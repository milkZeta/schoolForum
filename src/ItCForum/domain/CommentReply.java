package ItCForum.domain;

public class CommentReply {
    private String commentUser;//评论的用户
    private int commentFloor;//评论所在的楼层
    private String replyUser;//回复评论的用户
    private String postName;//帖子的名字
    private String replyContent;//评论的回复的内容
	public String getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	public int getCommentFloor() {
		return commentFloor;
	}
	public void setCommentFloor(int commentFloor) {
		this.commentFloor = commentFloor;
	}
	public String getReplyUser() {
		return replyUser;
	}
	public void setReplyUser(String replyUser) {
		this.replyUser = replyUser;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
    
}
