package com.hoau.crm.module.customer.api.shared.domain;

/**
 * 各客户总数实体
 * 
 * @author: 何斌
 * @create: 2015年12月29日 下午2:45:33
 */
public class CustomerTotalEntity {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 工号
	 */
	private String userCode;
	/**
	 * 客户列表总数
	 */
	private long customerTotal;
	/**
	 * 客户池总数
	 */
	private long customerResourceTotal;
	/**
	 * 共享客户总数
	 */
	private long customerPoolTotal;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public long getCustomerTotal() {
		return customerTotal;
	}
	public void setCustomerTotal(long customerTotal) {
		this.customerTotal = customerTotal;
	}
	public long getCustomerResourceTotal() {
		return customerResourceTotal;
	}
	public void setCustomerResourceTotal(long customerResourceTotal) {
		this.customerResourceTotal = customerResourceTotal;
	}
	public long getCustomerPoolTotal() {
		return customerPoolTotal;
	}
	public void setCustomerPoolTotal(long customerPoolTotal) {
		this.customerPoolTotal = customerPoolTotal;
	}
	
}
