package com.hoau.crm.module.customer.api.shared.domain;

import java.util.Date;

/**
 * 联系人实体--发货人
 * 
 * @author: 何斌
 * @create: 2015年12月14日 下午3:14:06
 */
public class ShipperEntity{

	/**
	 * 发货人主键id
	 */
	private int shipperId;
	/**
	 * 用户id
	 */
	private int userId;
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
	 * 区号
	 */
	private String districtNumber;
	/**
	 * 发货门店
	 */
	private String tierCode;
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
	private String crmGuid;
    /**
	 * 是否CRM客户联系人
	 */
	private String isCrmCustomerContact;
	/**
	 * CRM联系人主键
	 */
	private String crmContactGuid;
	
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
	public String getDistrictNumber() {
		return districtNumber;
	}
	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getShipperId() {
		return shipperId;
	}
	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTierCode() {
		return tierCode;
	}
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}
	public String getIsCrmCustomerContact() {
		return isCrmCustomerContact;
	}
	public void setIsCrmCustomerContact(String isCrmCustomerContact) {
		this.isCrmCustomerContact = isCrmCustomerContact;
	}
	public String getCrmContactGuid() {
		return crmContactGuid;
	}
	public void setCrmContactGuid(String crmContactGuid) {
		this.crmContactGuid = crmContactGuid;
	}
	public String getIsCrmCustomer() {
		return isCrmCustomer;
	}
	public void setIsCrmCustomer(String isCrmCustomer) {
		this.isCrmCustomer = isCrmCustomer;
	}
	public String getCrmGuid() {
		return crmGuid;
	}
	public void setCrmGuid(String crmGuid) {
		this.crmGuid = crmGuid;
	}
	
}
