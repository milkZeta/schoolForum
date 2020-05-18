package ItCForum.domain;

public class Message {
	private String message;
	private String publishTime;//发布消息时间
	private String messageType;//消息类型 0 通知1 失物招领
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
    
}
