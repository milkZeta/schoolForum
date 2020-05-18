package ItCForum.domain;

public class Comment {
	private int id;
	private String commentTitle;
	private String commentContext;
	private String username;
	private String commentUsername;
	private String commentTime;
	private int commentFloor;//评论所在楼层
	private int commentReplyCount;//评论的回复数量
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommentTitle() {
		return commentTitle;
	}
	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}
	public String getCommentContext() {
		return commentContext;
	}
	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCommentUsername() {
		return commentUsername;
	}
	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public int getCommentFloor() {
		return commentFloor;
	}
	public void setCommentFloor(int commentFloor) {
		this.commentFloor = commentFloor;
	}
	public int getCommentReplyCount() {
		return commentReplyCount;
	}
	public void setCommentReplyCount(int commentReplyCount) {
		this.commentReplyCount = commentReplyCount;
	}
	

}
