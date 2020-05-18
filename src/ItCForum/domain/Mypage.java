package ItCForum.domain;

public class Mypage {
	private String username;
	private String myTitle;
	private String myPost;//帖子内容
	private String submitTime;//发表时间
	private int result;//发布成功标志 0失败 1 成功
	private int count=0;//发表文章的数量
	private String module; //发表话题分类
	private int moduleCount=6;//发表话题的数量
	private String topics;//模块中的话题
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMyTitle() {
		return myTitle;
	}
	public void setMyTitle(String myTitle) {
		this.myTitle = myTitle;
	}
	public String getMyPost() {
		return myPost;
	}
	public void setMyPost(String myPost) {
		this.myPost = myPost;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count++;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getModuleCount() {
		return moduleCount;
	}
	public void setModuleCount(int moduleCount) {
		this.moduleCount = moduleCount;
	}
	 

}
