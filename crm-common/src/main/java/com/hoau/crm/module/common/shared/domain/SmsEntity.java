package com.hoau.crm.module.common.shared.domain;

import java.util.Date;

/**
 * 短信实体
 * 
 * @author 蒋落琛
 * @date 2015-12-2
 */
public class SmsEntity {

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 短信内容
	 */
	private String content;

	/**
	 * 唯一ID
	 */
	private String searchID;

	/**
	 * 发送时间
	 */
	private Date sendTime;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSearchID() {
		return searchID;
	}

	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
}
