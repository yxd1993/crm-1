package com.hoau.crm.module.customer.api.shared.domain;

/**
 * @author: 何斌
 * @create: 2015年12月24日 下午4:32:28
 */
public class ObhUserEntity {
	/**
	 * crm主键
	 */
	private String crmguid;
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
	 * 座机号
	 */
	private String telephone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 用户id
	 */
	private int userId;
	public String getCrmguid() {
		return crmguid;
	}
	public void setCrmguid(String crmguid) {
		this.crmguid = crmguid;
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
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
