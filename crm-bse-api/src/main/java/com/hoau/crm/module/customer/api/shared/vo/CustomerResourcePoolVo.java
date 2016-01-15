package com.hoau.crm.module.customer.api.shared.vo;

import java.util.Date;

import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;

/**
 * 客户资源池VO
 * 
 * @author: 何斌
 * @create: 2015年10月20日 上午10:41:37
 */
public class CustomerResourcePoolVo {

	/**
	 * 客户资源池
	 */
	private CustomerResourcePoolEntity customerResourcePoolEntity;
	/**
	 * 开始时间
	 */
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;

	public CustomerResourcePoolEntity getCustomerResourcePoolEntity() {
		return customerResourcePoolEntity;
	}

	public void setCustomerResourcePoolEntity(
			CustomerResourcePoolEntity customerResourcePoolEntity) {
		this.customerResourcePoolEntity = customerResourcePoolEntity;
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
