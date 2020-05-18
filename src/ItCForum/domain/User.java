package ItCForum.domain;

import java.util.List;

public class User {
	public int length=0;
    private String username;//用户名
	private String password;//密码
    private int status=1;//使用状态 0作废 1使用
    private String school;//所在学校
    private String sex;//性别
    private String birthday;//生日
	private String lastLogin;//上次登录时间
    private String lastIp;//上次登录IP
    private String showLoginStatus;//登录状态
    private String avatarPath;//用户头像
    


	public String getShowLoginStatus() {
		return showLoginStatus;
	}
	public void setShowLoginStatus(String showLoginStatus) {
		this.showLoginStatus = showLoginStatus;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
		length++;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	
}
