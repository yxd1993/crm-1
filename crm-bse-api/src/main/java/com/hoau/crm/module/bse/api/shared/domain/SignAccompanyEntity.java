/**
 * 
 */
package com.hoau.crm.module.bse.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 签到扫描陪同人实体
 * @author 丁勇
 * @date 2015年11月4日
 */
public class SignAccompanyEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -763566360899725030L;
	
	/**
	 *邀请签到人编号
	 */
	private String signId;
	/**
	 *被扫描人工号
	 */
	private String scanAccompanyEmpCode;
	/**
	 *被扫描人姓名
	 */
	private String scanAccompanyEmpName;
	/**
	 *被扫描陪同人岗位编号
	 */
	private String scanAccompanyJobCode;
	/**
	 *被扫描陪同人岗位名称
	 */
	private String scanAccompanyJobName;
	/**
	 *被扫描陪同人部门编号
	 */
	private String scanAccompanyDeptCode;
	/**
	 *被扫描陪同人部门名称
	 */
	private String scanAccompanyDeptName;
	/**
	 *是否有效
	 */
	private String active;
	
	public String getSignId() {
		return signId;
	}
	public void setSignId(String signId) {
		this.signId = signId;
	}
	public String getScanAccompanyEmpCode() {
		return scanAccompanyEmpCode;
	}
	public void setScanAccompanyEmpCode(String scanAccompanyEmpCode) {
		this.scanAccompanyEmpCode = scanAccompanyEmpCode;
	}
	public String getScanAccompanyEmpName() {
		return scanAccompanyEmpName;
	}
	public void setScanAccompanyEmpName(String scanAccompanyEmpName) {
		this.scanAccompanyEmpName = scanAccompanyEmpName;
	}
	public String getScanAccompanyJobCode() {
		return scanAccompanyJobCode;
	}
	public void setScanAccompanyJobCode(String scanAccompanyJobCode) {
		this.scanAccompanyJobCode = scanAccompanyJobCode;
	}
	public String getScanAccompanyJobName() {
		return scanAccompanyJobName;
	}
	public void setScanAccompanyJobName(String scanAccompanyJobName) {
		this.scanAccompanyJobName = scanAccompanyJobName;
	}
	public String getScanAccompanyDeptCode() {
		return scanAccompanyDeptCode;
	}
	public void setScanAccompanyDeptCode(String scanAccompanyDeptCode) {
		this.scanAccompanyDeptCode = scanAccompanyDeptCode;
	}
	public String getScanAccompanyDeptName() {
		return scanAccompanyDeptName;
	}
	public void setScanAccompanyDeptName(String scanAccompanyDeptName) {
		this.scanAccompanyDeptName = scanAccompanyDeptName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
