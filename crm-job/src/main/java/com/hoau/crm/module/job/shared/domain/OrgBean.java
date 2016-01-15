package com.hoau.crm.module.job.shared.domain;

/**
 * OA组织信息实体
 *
 * @author 蒋落琛
 * @date 2015-5-18
 */
public class OrgBean {

	/**
	 * 部门名称
	 */
	private String deptname;

	/**
	 * 部门编号
	 */
	private String deptcode;

	/**
	 * 部门状态 0 正常，1 取消
	 */
	private String canceled;

	/**
	 * 上级部门编号
	 */
	private String supdeptcode;

	/**
	 * 上级部门名称
	 */
	private String supdeptname;

	/**
	 * 部门性质
	 */
	private String deptnature;

	/**
	 * 部门代码
	 */
	private String logistcode;

	/**
	 * 部门负责人
	 */
	private String managerid;

	/**
	 * 部门负责人姓名
	 */
	private String lastname;

	/**
	 * 所属分部编码
	 */
	private String subcompanyid;

	/**
	 * 所属分部名称
	 */
	private String subcompanyname;
	
	/**
	 * 是否门店
	 */
	private String isStore;
	
	public String getIsStore() {
		return isStore;
	}

	public void setIsStore(String isStore) {
		this.isStore = isStore;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getSupdeptcode() {
		return supdeptcode;
	}

	public void setSupdeptcode(String supdeptcode) {
		this.supdeptcode = supdeptcode;
	}

	public String getSupdeptname() {
		return supdeptname;
	}

	public void setSupdeptname(String supdeptname) {
		this.supdeptname = supdeptname;
	}

	public String getDeptnature() {
		return deptnature;
	}

	public void setDeptnature(String deptnature) {
		this.deptnature = deptnature;
	}

	public String getLogistcode() {
		return logistcode;
	}

	public void setLogistcode(String logistcode) {
		this.logistcode = logistcode;
	}

	public String getManagerid() {
		return managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSubcompanyid() {
		return subcompanyid;
	}

	public void setSubcompanyid(String subcompanyid) {
		this.subcompanyid = subcompanyid;
	}

	public String getSubcompanyname() {
		return subcompanyname;
	}

	public void setSubcompanyname(String subcompanyname) {
		this.subcompanyname = subcompanyname;
	}
}