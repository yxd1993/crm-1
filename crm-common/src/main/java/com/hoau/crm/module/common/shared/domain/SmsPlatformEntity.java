package com.hoau.crm.module.common.shared.domain;


/**
 * 短信平台信息实体
 *
 * @author 蒋落琛
 * @date 2015-12-17
 */
public class SmsPlatformEntity {
	
	/**
	 * SMSID
	 */
	private String smsId;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 短信内容
	 */
	private String content;

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

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
}
