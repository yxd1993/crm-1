/**
 * 
 */
package com.hoau.crm.module.sales.api.shared.domain;

/**
 * 个人工作列表实体
 * 
 * @author 丁勇
 * @date 2015年7月17日
 */
public class ReportEmpWorkEntity {
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 员工姓名
	 */
	private String empName;
	/**
	 * 工号
	 */
	private String account;
	/**
	 * 当月新增预约次数
	 */
	private String reserveCount;
	/**
	 * 当月新增洽谈次数
	 */
	private String chatsCount;
	/**
	 * 当月新增意向次数
	 */
	private String intentionCount;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getReserveCount() {
		return reserveCount;
	}

	public void setReserveCount(String reserveCount) {
		this.reserveCount = reserveCount;
	}

	public String getChatsCount() {
		return chatsCount;
	}

	public void setChatsCount(String chatsCount) {
		this.chatsCount = chatsCount;
	}

	public String getIntentionCount() {
		return intentionCount;
	}

	public void setIntentionCount(String intentionCount) {
		this.intentionCount = intentionCount;
	}
}
