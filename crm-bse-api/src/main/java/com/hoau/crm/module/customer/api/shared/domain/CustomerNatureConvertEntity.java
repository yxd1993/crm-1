package com.hoau.crm.module.customer.api.shared.domain;

import java.util.Date;

/**
 * 客户性质转换实体
 * 
 * @author: 何斌
 * @create: 2015年8月15日 下午2:01:21
 */
public class CustomerNatureConvertEntity {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 客户id
	 */
	private String accountId;
	/**
	 * 原状态
	 */
	private String oldStatus;
	/**
	 * 新状态
	 */
	private String newStatus;
	/**
	 * 转换人
	 */
	private String convertUser;
	/**
	 * 转换时间
	 */
	private Date convertDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	public String getConvertUser() {
		return convertUser;
	}
	public void setConvertUser(String convertUser) {
		this.convertUser = convertUser;
	}
	public Date getConvertDate() {
		return convertDate;
	}
	public void setConvertDate(Date convertDate) {
		this.convertDate = convertDate;
	}
}
