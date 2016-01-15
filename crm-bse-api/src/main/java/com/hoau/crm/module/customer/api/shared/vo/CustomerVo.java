package com.hoau.crm.module.customer.api.shared.vo;

import java.io.Serializable;
import java.util.Date;

import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;

/**
 * 部门信息VO
 * 
 * @author 蒋落琛
 * @date 2015-5-22
 */
public class CustomerVo implements Serializable {

	private static final long serialVersionUID = -2572973427066807735L;
	
	/**
	 * 客户信息公共选择器查询参数
	 */
	private String selectorParam;
	
	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	
	/**
	 * 开始时间
	 */
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;

	public String getSelectorParam() {
		return selectorParam;
	}

	public void setSelectorParam(String selectorParam) {
		this.selectorParam = selectorParam;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
