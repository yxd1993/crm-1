package com.hoau.crm.module.customer.api.shared.vo;

import java.io.Serializable;
import java.util.Date;

import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;

/**
 * 客户信息VO
 *
 * @author 蒋落琛
 * @date 2015-5-26
 */
public class CustomerInfoPoolVo implements Serializable {

	private static final long serialVersionUID = -8919090263329007738L;
	
	/**
	 * 查询开始时间
	 */
	private Date startDate;
	
	/**
	 * 查询结束时间
	 */
	private Date endDate;
	
	/**
	 * 客户信息实体
	 */
	private CustomerInfoPoolEntity customerInfoPoolEntity;
	
	/**
	 * 排序方式
	 */
	private String sortType;

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

	public CustomerInfoPoolEntity getCustomerInfoPoolEntity() {
		return customerInfoPoolEntity;
	}

	public void setCustomerInfoPoolEntity(
			CustomerInfoPoolEntity customerInfoPoolEntity) {
		this.customerInfoPoolEntity = customerInfoPoolEntity;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
}