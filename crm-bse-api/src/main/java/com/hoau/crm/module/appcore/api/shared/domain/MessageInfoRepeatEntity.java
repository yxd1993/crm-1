package com.hoau.crm.module.appcore.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 消息信息重发实体
 *
 * @author 蒋落琛
 * @date 2015-8-8
 */
public class MessageInfoRepeatEntity extends BaseEntity{

	private static final long serialVersionUID = 515398931170010463L;

	/**
	 * 推送用户ID
	 */
	private String pushuserId;
	
	/**
	 * 消息ID
	 */
	private String messageId;
	
	/**
	 * 重发次数
	 */
	private int repeatNum;
	
	/**
	 * 重发状态  1：成功   2：失败
	 */
	private String repeatStatus;
	
	/**
	 * 消息信息实体
	 */
	private MessageInfoEntity messageInfoEntity;
	
	/**
	 * 推送账号信息实体
	 */
	private PushUserEntity pushUserEntity;

	public String getPushuserId() {
		return pushuserId;
	}

	public void setPushuserId(String pushuserId) {
		this.pushuserId = pushuserId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public int getRepeatNum() {
		return repeatNum;
	}

	public void setRepeatNum(int repeatNum) {
		this.repeatNum = repeatNum;
	}

	public String getRepeatStatus() {
		return repeatStatus;
	}

	public void setRepeatStatus(String repeatStatus) {
		this.repeatStatus = repeatStatus;
	}

	public MessageInfoEntity getMessageInfoEntity() {
		return messageInfoEntity;
	}

	public void setMessageInfoEntity(MessageInfoEntity messageInfoEntity) {
		this.messageInfoEntity = messageInfoEntity;
	}

	public PushUserEntity getPushUserEntity() {
		return pushUserEntity;
	}

	public void setPushUserEntity(PushUserEntity pushUserEntity) {
		this.pushUserEntity = pushUserEntity;
	}
}
