package com.hoau.crm.module.job.server.service;

/**
 * @author 275636
 *客户分布和产值热力图
 */
public interface ICustomerHeatMapService {
	
	/**
	 * 客户分布
	 */
	void refreshCustomerHeatMap();
	/**
	 * 客户产值
	 */
	void refreshCUstomerHeatMapOutPut();
}
