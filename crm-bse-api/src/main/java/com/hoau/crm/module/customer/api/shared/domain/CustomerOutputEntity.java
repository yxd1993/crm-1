package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 客户产值信息实体
 * 
 * @author 蒋落琛
 * @date 2015-7-8
 */
public class CustomerOutputEntity extends BaseEntity {

	private static final long serialVersionUID = 6543357072826839731L;

	/**
	 * 年
	 */
	private String year;

	/**
	 * 月
	 */
	private String month;

	/**
	 * 产值数据
	 */
	private double outputNum;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getOutputNum() {
		return outputNum;
	}

	public void setOutputNum(double outputNum) {
		this.outputNum = outputNum;
	}
}