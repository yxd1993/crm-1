package com.hoau.crm.module.sales.api.shared.vo;

import java.util.List;

import com.hoau.crm.module.sales.api.shared.domain.SaleIncomeDetailEntity;

/**
 * 销售收入明细VO
 * 
 * @author: 何斌
 * @create: 2015年7月21日 下午3:51:42
 */
public class SaleIncomeDetailVo {
	
	/**
	 * 当前月份
	 */
	private String currentMonth;
	
	/**
	 * 总收入
	 */
	private double totalIncome;
	
	/**
	 * 收入明细
	 */
	private List<SaleIncomeDetailEntity> saleIncomeDetails;

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public List<SaleIncomeDetailEntity> getSaleIncomeDetails() {
		return saleIncomeDetails;
	}

	public void setSaleIncomeDetails(List<SaleIncomeDetailEntity> saleIncomeDetails) {
		this.saleIncomeDetails = saleIncomeDetails;
	}
	
}
