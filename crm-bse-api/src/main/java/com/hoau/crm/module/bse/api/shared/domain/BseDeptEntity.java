package com.hoau.crm.module.bse.api.shared.domain;

/**
 * 部门实体
 * 
 * @author: 何斌
 * @create: 2015年11月19日 上午9:18:09
 */
public class BseDeptEntity {

	/**
	 * 部门编码 
	 */
	private String deptCode;
	/**
	 * 部门名称
	 */
	private String deptName;
	
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
