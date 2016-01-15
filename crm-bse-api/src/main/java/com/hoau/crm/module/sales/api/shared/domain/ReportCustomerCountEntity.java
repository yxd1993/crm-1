package com.hoau.crm.module.sales.api.shared.domain;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;

/**
 * 客户统计实体
 * 
 * @author: 何斌
 * @create: 2015年7月18日 上午11:20:19
 */
public class ReportCustomerCountEntity {
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 客户总数
	 */
	private long totalCustomer;
	
	/**
	 * 客户性质分布
	 */
	private List<ReportDataEntity> customerNatureDistributions;
	
	/**
	 * 客户详情
	 */
	private List<ReportDataEntity> customerDetails;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<ReportDataEntity> getCustomerNatureDistributions() {
		return customerNatureDistributions;
	}

	public void setCustomerNatureDistributions(
			List<ReportDataEntity> customerNatureDistributions) {
		this.customerNatureDistributions = customerNatureDistributions;
	}

	public List<ReportDataEntity> getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(List<ReportDataEntity> customerDetails) {
		this.customerDetails = customerDetails;
	}

	public long getTotalCustomer() {
		return totalCustomer;
	}

	public void setTotalCustomer(long totalCustomer) {
		this.totalCustomer = totalCustomer;
	}

}
