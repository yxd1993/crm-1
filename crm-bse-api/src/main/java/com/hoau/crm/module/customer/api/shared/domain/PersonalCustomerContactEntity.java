package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 个人客户联系人实体
 * 
 * @author: 何斌
 * @create: 2015年12月21日 上午9:38:19
 */
public class PersonalCustomerContactEntity extends BaseEntity {

	private static final long serialVersionUID = 2897732298010484861L;
	
	/**
	 * 来源渠道主键id
	 */
	private int sourceId;
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
	 * 关联联系人id
	 */
	private String customerContactId;
	/**
	 * 是否有效
	 */
	private String active;
	
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
	public String getTierCode() {
		return tierCode;
	}
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public int getSourceId() {
		return sourceId;
	}
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCustomerContactId() {
		return customerContactId;
	}
	public void setCustomerContactId(String customerContactId) {
		this.customerContactId = customerContactId;
	}
	
}
