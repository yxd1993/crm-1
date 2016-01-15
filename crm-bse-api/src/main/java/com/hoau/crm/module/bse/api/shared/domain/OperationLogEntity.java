package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 操作日志实体
 * @author: 何斌
 * @create: 2015年6月10日 上午9:06:43
 */
public class OperationLogEntity extends BaseEntity {

	private static final long serialVersionUID = 7663149260700039408L;
	
	/**
	 * 操作类型(定义在数据字典)
	 */
	private String operationType;
	/**
	 * 操作人
	 */
	private String operationUser;
	/**
	 * 授权人
	 */
	private String wasAuthorizedPerson;
	/**
	 * 操作时间
	 */
	private Date operationTime;
	/**
	 * 操作人IP
	 */
	private String operationIp;
	/**
	 * 是否有效
	 */
	private String active;
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationUser() {
		return operationUser;
	}
	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public String getOperationIp() {
		return operationIp;
	}
	public void setOperationIp(String operationIp) {
		this.operationIp = operationIp;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getWasAuthorizedPerson() {
		return wasAuthorizedPerson;
	}
	public void setWasAuthorizedPerson(String wasAuthorizedPerson) {
		this.wasAuthorizedPerson = wasAuthorizedPerson;
	}
}
