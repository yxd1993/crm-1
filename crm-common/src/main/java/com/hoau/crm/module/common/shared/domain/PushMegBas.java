package com.hoau.crm.module.common.shared.domain;

/**
 * 消息通知推送基础类
 * 
 * @author 蒋落琛
 * @date 2015-6-16
 */
public class PushMegBas {

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
