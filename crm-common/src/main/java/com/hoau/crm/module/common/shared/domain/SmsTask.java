package com.hoau.crm.module.common.shared.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 短信TASK
 * 
 * @author 蒋落琛
 * @date 2015-12-2
 */
@XmlRootElement(name = "Task")
public class SmsTask {

	/**
	 * @Fields recivePhoneNumber : 接收消息的手机号码
	 */
	private String recivePhoneNumber;

	/**
	 * @Fields content : 短信内容
	 */
	private String content;

	/**
	 * @Fields searchID : 每一条短信的唯一标示，通过此标示获取短信发送状态
	 */
	private String searchID;

	public String getRecivePhoneNumber() {
		return recivePhoneNumber;
	}

	@XmlElement(name = "Recive_Phone_Number")
	public void setRecivePhoneNumber(String recivePhoneNumber) {
		this.recivePhoneNumber = recivePhoneNumber;
	}

	public String getContent() {
		return content;
	}

	@XmlElement(name = "Content")
	public void setContent(String content) {
		this.content = content;
	}

	public String getSearchID() {
		return searchID;
	}

	@XmlElement(name = "Search_ID")
	public void setSearchID(String searchID) {
		this.searchID = searchID;
	}

}
