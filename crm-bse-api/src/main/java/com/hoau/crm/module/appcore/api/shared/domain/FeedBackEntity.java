package com.hoau.crm.module.appcore.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 用户反馈信息实体
 * 
 * @author 277610
 * @Date 2014-01-15
 */
public class FeedBackEntity extends BaseEntity {

	private static final long serialVersionUID = 7064780058404116927L;

	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户名字
	 */
	private String userName;

	/**
	 * 反馈标题
	 */
	private String fbTitle;

	/**
	 * 反馈类型
	 */
	private String fbType;

	/**
	 * 反馈内容
	 */
	private String fbContent;

	/**
	 * 反馈时间
	 */
	private Date fbTime;
	
	/**
	 * 
	 * 反馈结束时间
	 */
	private Date fbEndTime;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFbContent() {
		return fbContent;
	}

	public void setFbContent(String fbContent) {
		this.fbContent = fbContent;
	}

	public String getFbTitle() {
		return fbTitle;
	}

	public void setFbTitle(String fbTitle) {
		this.fbTitle = fbTitle;
	}

	public String getFbType() {
		return fbType;
	}

	public void setFbType(String fbType) {
		this.fbType = fbType;
	}

	public Date getFbTime() {
		return fbTime;
	}

	public void setFbTime(Date fbTime) {
		this.fbTime = fbTime;
	}

	public Date getFbEndTime() {
		return fbEndTime;
	}

	public void setFbEndTime(Date fbEndTime) {
		this.fbEndTime = fbEndTime;
	}

	@Override
	public String toString() {
		return "FeedBackEntity [userName=" + userName + ", fbTitle=" + fbTitle
				+ ", fbType=" + fbType + ", fbContent=" + fbContent
				+ ", fbTime=" + fbTime + ", fbEndTime=" + fbEndTime + "]";
	}
}
