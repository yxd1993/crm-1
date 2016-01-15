package com.hoau.crm.module.customer.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 客户资源池实体
 * 
 * @author: 何斌
 * @create: 2015年10月20日 上午10:33:11
 */
public class CustomerResourcePoolEntity extends BaseEntity{

	private static final long serialVersionUID = -6262227112831144925L;

	/**
	 * 客户企业全称
	 */
	private String enterpriseName;
	/**
	 * 所属行业
	 */
	private String industryCode;
	/**
	 * 所属大区
	 */
	private String regions;
	/**
	 * 联系人
	 */
	private String contactName;
	/**
	 * 联系人手机
	 */
	private String cellphone;
	/**
	 * 座机
	 */
	private String telephone;
	/**
	 * 地址
	 */
	private String address;
	/**
	 *客户性质
	 */
	private String accountType;
	/**
	 *最后发货时间
	 */
	private Date LastShipmentsTime;
	/**
	 * 流入时间
	 */
	private Date flowDate;
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getRegions() {
		return regions;
	}
	public void setRegions(String regions) {
		this.regions = regions;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getFlowDate() {
		return flowDate;
	}
	public void setFlowDate(Date flowDate) {
		this.flowDate = flowDate;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getLastShipmentsTime() {
		return LastShipmentsTime;
	}
	public void setLastShipmentsTime(Date lastShipmentsTime) {
		LastShipmentsTime = lastShipmentsTime;
	}
}
