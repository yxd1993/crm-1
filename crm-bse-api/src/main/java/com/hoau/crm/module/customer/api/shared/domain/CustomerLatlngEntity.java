package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author 275636
 *客户经纬度
 */
public class CustomerLatlngEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6180141447923322165L;
	
	/**维度*/
	private double lat;
	/**经度*/
	private double lng;
	/**客户类型*/
	private String type;
	/**客户ID*/
	private String customerId;
	/**客户实体*/
	//private CustomerEntity customerEntity;
	
	/**
	 * 是否精确查找 1为精确查找，0为不精确
	 */
	private String precise;
	/**
	 * 可信度
	 */
	private String confidence;
	/**
	 * 负责人
	 */
	private String managePerson;
	/**
	 * 企业全称
	 */
	private String enterpriseName;
	/**
	 * 详细地址
	 */
	private String detailedAddress;
	/**
	 * 手机
	 */
	private String cellphone;
	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 区号
	 */
	private String districtNumber;
	/**
	 * 联系人
	 */
	private String contactName;
	
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
	public String getManagePerson() {
		return managePerson;
	}
	public void setManagePerson(String managePerson) {
		this.managePerson = managePerson;
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
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
//	public CustomerEntity getCustomerEntity() {
//		return customerEntity;
//	}
//	public void setCustomerEntity(CustomerEntity customerEntity) {
//		this.customerEntity = customerEntity;
//	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getPrecise() {
		return precise;
	}
	public void setPrecise(String precise) {
		this.precise = precise;
	}
	public String getConfidence() {
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

}
