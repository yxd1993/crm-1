package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 个人客户实体
 * 
 * @author: 何斌
 * @create: 2015年6月19日 下午5:19:32
 */
public class PersonalCustomerEntity extends BaseEntity {

	private static final long serialVersionUID = -8773187058431265127L;

	/**
	 * 来源渠道主键id
	 */
	private int sourceId;
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
	 * 来源渠道
	 */
	private String source;
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 是否转为企业客户
	 */
	private String isTurnCustomer;
	/**
	 * 负责人
	 */
	private String managePerson;
	
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getIsTurnCustomer() {
		return isTurnCustomer;
	}
	public void setIsTurnCustomer(String isTurnCustomer) {
		this.isTurnCustomer = isTurnCustomer;
	}
	public String getManagePerson() {
		return managePerson;
	}
	public void setManagePerson(String managePerson) {
		this.managePerson = managePerson;
	}
	
}
