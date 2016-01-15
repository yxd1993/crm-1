package com.hoau.crm.module.appcore.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

public class UserOperationEntity extends BaseEntity {

	/**
	 * 客户操作信息实体
	 * 
	 * @author 潘强
	 * @Date 2015-07-18
	 */
	private static final long serialVersionUID = 3707083120165701696L;
	/**
	 * 客户操作类型
	 */
	private String userOperationType;
	/**
	 * 客户姓名
	 */
	private String userOperationName;
	/**
	 * 客户IP
	 */
	private String userOperationIp;
	/**
	 * 客户操作开始时间
	 */
	private Date userOperationTime;
	/**
	 * 客户操作结束时间
	 */
	private Date userOperationEndTime;

	public String getUserOperationType() {
		return userOperationType;
	}

	public void setUserOperationType(String userOperationType) {
		this.userOperationType = userOperationType;
	}

	public String getUserOperationName() {
		return userOperationName;
	}

	public void setUserOperationName(String userOperationName) {
		this.userOperationName = userOperationName;
	}

	public String getUserOperationIp() {
		return userOperationIp;
	}

	public void setUserOperationIp(String userOperationIp) {
		this.userOperationIp = userOperationIp;
	}

	public Date getUserOperationTime() {
		return userOperationTime;
	}

	public void setUserOperationTime(Date userOperationTime) {
		this.userOperationTime = userOperationTime;
	}

	public Date getUserOperationEndTime() {
		return userOperationEndTime;
	}

	public void setUserOperationEndTime(Date userOperationEndTime) {
		this.userOperationEndTime = userOperationEndTime;
	}

	@Override
	public String toString() {
		return "UserOperationEntity [userOperationType=" + userOperationType
				+ ", userOperationName=" + userOperationName
				+ ", userOperationIp=" + userOperationIp
				+ ", userOperationTime=" + userOperationTime
				+ ", userOperationEndTime=" + userOperationEndTime + "]";
	}

}
