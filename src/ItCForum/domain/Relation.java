package ItCForum.domain;

public class Relation {
	/** 
	 * 粉丝和关注之间的关系 若是点击关注 必定产生两条数据
	 */
	 private String fens;//用户名
	 private String fellow;//关注的人
	 private String fensBasicMsg;//基本信息
	 private String fellowBasicMsg;//基本信息
	 private String fensAvatar;//图像路径
	 private String fellowAvatar;//图像路径
	public String getFens() {
		return fens;
	}
	public void setFens(String fens) {
		this.fens = fens;
	}
	public String getFellow() {
		return fellow;
	}
	public void setFellow(String fellow) {
		this.fellow = fellow;
	}
	public String getFensBasicMsg() {
		return fensBasicMsg;
	}
	public void setFensBasicMsg(String fensBasicMsg) {
		this.fensBasicMsg = fensBasicMsg;
	}
	public String getFellowBasicMsg() {
		return fellowBasicMsg;
	}
	public void setFellowBasicMsg(String fellowBasicMsg) {
		this.fellowBasicMsg = fellowBasicMsg;
	}
	public String getFensAvatar() {
		return fensAvatar;
	}
	public void setFensAvatar(String fensAvatar) {
		this.fensAvatar = fensAvatar;
	}
	public String getFellowAvatar() {
		return fellowAvatar;
	}
	public void setFellowAvatar(String fellowAvatar) {
		this.fellowAvatar = fellowAvatar;
	}
	 
}
