package com.hoau.crm.module.job.shared.domain;

import java.util.Date;

/**
 * 个人客户
 * 
 * @author: 何斌
 * @create: 2015年12月21日 上午9:31:04
 */
public class PersonalCustomerBean{

	/**
	 * 用户id
	 */
	private int userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 客户名称
	 */
	private String enterpriseName;
	/**
	 * 客户地址
	 */
	private String detailedAddress;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 手机号
	 */
	private String cellphone;
	/**
	 * 座机号
	 */
	private String telephone;
	/**
	 * 邮箱
	 */
	private String email;
    /**
     * 创建日期 
     */
    private Date createDate;
    /**
	 * 是否CRM客户 
	 */
	private String isCrmCustomer;
	/**
	 * CRM主键
	 */
	private String crmguid;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsCrmCustomer() {
		return isCrmCustomer;
	}

	public void setIsCrmCustomer(String isCrmCustomer) {
		this.isCrmCustomer = isCrmCustomer;
	}

	public String getCrmguid() {
		return crmguid;
	}

	public void setCrmguid(String crmguid) {
		this.crmguid = crmguid;
	}
	
}
