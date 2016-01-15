package com.hoau.crm.module.appcore.api.shared.domain;

import java.util.Date;
import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 消息信息实体
 *
 * @author 蒋落琛
 * @date 2015-6-29
 */
public class MessageInfoEntity extends BaseEntity{

	private static final long serialVersionUID = 8771869333835646871L;
	
	/**
	 * DC账号
	 */
	private String dcAccount;
	
	/**
	 * CRM账号
	 */
	private String crmAccount;

	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	
	/**
	 * 业务类型
	 */
	private String busType;
	
	/**
	 * 消息类型
	 */
	private String messageType;
	
	/**
	 * 消息类型名称
	 */
	private String messageTypeName;
	
	/**
	 * 消息推送方式
	 */
	private String pushType;
	
	/**
	 * 消息标题
	 */
	private String messageTitle;
	
	/**
	 * 消息内容
	 */
	private String messageContent;
	
	/**
	 * 消息超链接地址
	 */
	private String messageUrl;
	
	/**
	 * 消息发送人
	 */
	private String sendUserId;
	
	/**
	 * 消息接收人
	 */
	private String receiveUserId;
	
	/**
	 * 匹配条件
	 */
	private String condition;
	
	/**
	 * 允许的发送时间
	 */
	private Date allowSendTime;
	
	/**
	 * 消息是否已经推送，0，未发送  1，已发送  2，无需推送
	 */
	private String isSend;
	
	/**
	 * 消息是否已经阅读，0：未读  1：已读
	 */
	private String isRead;
	
	/**
	 * 消息ID
	 */
	private String msgId;
	
	/**
	 * 发送时间
	 */
	private Date sendTime;
	
	/**
	 * 推送账号信息
	 */
	private List<PushUserEntity> pushUserEntityList;

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageUrl() {
		return messageUrl;
	}

	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}

	public List<PushUserEntity> getPushUserEntityList() {
		return pushUserEntityList;
	}

	public void setPushUserEntityList(List<PushUserEntity> pushUserEntityList) {
		this.pushUserEntityList = pushUserEntityList;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getDcAccount() {
		return dcAccount;
	}

	public void setDcAccount(String dcAccount) {
		this.dcAccount = dcAccount;
	}

	public String getCrmAccount() {
		return crmAccount;
	}

	public void setCrmAccount(String crmAccount) {
		this.crmAccount = crmAccount;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Date getAllowSendTime() {
		return allowSendTime;
	}

	public void setAllowSendTime(Date allowSendTime) {
		this.allowSendTime = allowSendTime;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getMessageTypeName() {
		return messageTypeName;
	}

	public void setMessageTypeName(String messageTypeName) {
		this.messageTypeName = messageTypeName;
	}
}
