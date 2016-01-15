package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 上传客户信息实体
 * @author: 何斌
 * @create: 2015年5月25日 下午2:47:59
 */
public class CustomerInfoPoolEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 793293300082093298L;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 联系方式
	 */
	private String contactWay;
	/**
	 * 所属事业部
	 */
	private String business;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 省编码
	 */
	private String provinceCode;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 市编码
	 */
	private String cityCode;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 区编码
	 */
	private String areaCode;
	/**
	 * 公司地址
	 */
	private String companyAddress;
	/**
	 * 负责人
	 */
	private String managePerson;
	/**
	 * 负责人工号
	 */
	private String manageEmpCode;
	/**
	 * 分发状态
	 */
	private String dispenseStatus;
	/**
	 * 退回原因
	 */
	private String backReason;
	
	/**
	 * 上传人
	 */
	private EmployeeEntity employeeEntity;
	/**
	 * 是否有效
	 */
	private String active;
	
	/**
	 * 客户地址所在大区
	 */
	private String regions;
	
	/**
	 *  客户地址所在大区编码
	 */
	private String regionsCode;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getManagePerson() {
		return managePerson;
	}
	public void setManagePerson(String managePerson) {
		this.managePerson = managePerson;
	}
	public String getManageEmpCode() {
		return manageEmpCode;
	}
	public void setManageEmpCode(String manageEmpCode) {
		this.manageEmpCode = manageEmpCode;
	}
	public String getDispenseStatus() {
		return dispenseStatus;
	}
	public void setDispenseStatus(String dispenseStatus) {
		this.dispenseStatus = dispenseStatus;
	}
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}
	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getRegions() {
		return regions;
	}
	public void setRegions(String regions) {
		this.regions = regions;
	}
	public String getRegionsCode() {
		return regionsCode;
	}
	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode;
	}
}
