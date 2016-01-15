package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;


/**
 * 销售收入明细实体 
 * 
 * @author: 何斌
 * @create: 2015年7月21日 下午3:47:01
 */
	
public class SaleIncomeDetailEntity {
	
	/**
	 * 开单时间
	 */
	private Date billingDate;
	
	/**
	 * 企业名称
	 */
	private String enterpriseName;
	
	/**
	 * 运输类型
	 */
	private String transportType;
	
	/**
	 * 奖金
	 */
	private double bonus;

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
}
