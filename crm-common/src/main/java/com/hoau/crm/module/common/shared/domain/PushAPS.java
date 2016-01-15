package com.hoau.crm.module.common.shared.domain;

import java.io.Serializable;

/**
 * 消息推送 IOS 自定义音频对象
 * 
 * @author 蒋落琛
 * @date 2015-6-16
 */
public class PushAPS implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 具体内容
	 */
	private String alert;

	/**
	 * 声音
	 */
	private String sound;

	private int badge;

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public int getBadge() {
		return badge;
	}

	public void setBadge(int badge) {
		this.badge = badge;
	}

}
