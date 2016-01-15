package com.hoau.crm.module.sales.shared.domain;


/**
 * OA-CRM销售合同审批流程需要的字段
 */
public class OaSalBean{
	/**
	 * 部门名称
	 */
	private String departmentname;//部门名称
	/**
	 * 工作岗位
	 */
	private String jobtitlename;//工作岗位
	/**
	 * 姓名
	 */
	private String lastname;//姓名
	/**
	 * 工号
	 */
	private String workcode;
	/**
	 * 电话
	 */
	private String mobile;
	
	
	public String getDepartmentname() {
		return departmentname;
	}

	private String workflowcode;//工作流水号
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}


	public String getJobtitlename() {
		return jobtitlename;
	}


	public void setJobtitlename(String jobtitlename) {
		this.jobtitlename = jobtitlename;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getWorkcode() {
		return workcode;
	}


	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getWorkflowcode() {
		return workflowcode;
	}


	public void setWorkflowcode(String workflowcode) {
		this.workflowcode = workflowcode;
	}

}
