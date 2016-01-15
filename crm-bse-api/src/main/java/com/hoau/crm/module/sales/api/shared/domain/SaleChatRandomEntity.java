package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 随机洽谈记录实体
 * 
 * @author: 何斌
 * @create: 2015年8月17日 上午11:11:27
 */
public class SaleChatRandomEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7775850277650473414L;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 联系人电话
	 */
	private String cellphone;
	/**
	 * 洽谈开始时间
	 */
	private Date chatStartTime;
	/**
	 * 拜访方式
	 */
	private String chatType;
	/**
	 * 拜访内容
	 */
	private String chatContent;
	/**
	 * 创建人工号
	 */
	private String createUserEmp;
	/**
	 * 创建人姓名
	 */
	private String createUserName;
	/**
	 * 创建人部门
	 */
	private String createUserDept;
	/**
	 * 一级单位
	 */
	private String oneLevelUnit;
	/**
	 * 二级单位
	 */
	private String twoLevelUnit;
	/**
	 * 三级单位
	 */
	private String threeLevelUnit;
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 状态  0 -- 未抽查  1 -- 已抽查
	 */
	private String status;
	/**
	 * 抽查结果
	 */
	private String checkResult;
	
	/**
	 * 是否有效
	 */
	private String active;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public Date getChatStartTime() {
		return chatStartTime;
	}
	public void setChatStartTime(Date chatStartTime) {
		this.chatStartTime = chatStartTime;
	}
	public String getChatType() {
		return chatType;
	}
	public void setChatType(String chatType) {
		this.chatType = chatType;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public String getCreateUserEmp() {
		return createUserEmp;
	}
	public void setCreateUserEmp(String createUserEmp) {
		this.createUserEmp = createUserEmp;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getCreateUserDept() {
		return createUserDept;
	}
	public void setCreateUserDept(String createUserDept) {
		this.createUserDept = createUserDept;
	}
	public String getOneLevelUnit() {
		return oneLevelUnit;
	}
	public void setOneLevelUnit(String oneLevelUnit) {
		this.oneLevelUnit = oneLevelUnit;
	}
	public String getTwoLevelUnit() {
		return twoLevelUnit;
	}
	public void setTwoLevelUnit(String twoLevelUnit) {
		this.twoLevelUnit = twoLevelUnit;
	}
	public String getThreeLevelUnit() {
		return threeLevelUnit;
	}
	public void setThreeLevelUnit(String threeLevelUnit) {
		this.threeLevelUnit = threeLevelUnit;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
