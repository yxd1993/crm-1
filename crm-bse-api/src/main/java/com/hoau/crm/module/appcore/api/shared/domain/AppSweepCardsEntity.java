package com.hoau.crm.module.appcore.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 *
 * @author 丁勇
 * @date 2015年11月23日
 * app扫描名片记录
 */
public class AppSweepCardsEntity extends BaseEntity  {

	private static final long serialVersionUID = -6198998569016149039L;
	
	/**
	 *公司名称
	 */
	private String enterpriseName;
	/**
	 *地址
	 */
	private String address;
	/**
	 *姓名
	 */
	private String empName;
	/**
	 *职位名称
	 */
	private String empDeptName;
	/**
	 *电话
	 */
	private String telephone;
	/**
	 *手机
	 */
	private String mobilephone;
	/**
	 *邮箱
	 */
	private String email;
	
	/**
	 *是否有效
	 */
	private String active;
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDeptName() {
		return empDeptName;
	}
	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
}
