package com.hoau.crm.module.sales.api.shared.domain;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;

/**
 * 销售报表收入分析实体
 * 
 * @author: 何斌
 * @create: 2015年7月21日 下午1:57:44
 */
public class SaleIncomeAnalysisEntity {
	
	/**
	 * 奖金分布集合
	 */
	List<ReportDataEntity> bonusDistributionLists;
	
	/**
	 * 运输类型分布集合
	 */
	List<ReportDataEntity> transportTypeDistributionLists;

	public List<ReportDataEntity> getBonusDistributionLists() {
		return bonusDistributionLists;
	}

	public void setBonusDistributionLists(
			List<ReportDataEntity> bonusDistributionLists) {
		this.bonusDistributionLists = bonusDistributionLists;
	}

	public List<ReportDataEntity> getTransportTypeDistributionLists() {
		return transportTypeDistributionLists;
	}

	public void setTransportTypeDistributionLists(
			List<ReportDataEntity> transportTypeDistributionLists) {
		this.transportTypeDistributionLists = transportTypeDistributionLists;
	}
	
}
