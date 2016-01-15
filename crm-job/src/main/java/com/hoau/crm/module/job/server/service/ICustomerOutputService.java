package com.hoau.crm.module.job.server.service;

/**
 * 客户产值信息更新SERVICE
 *
 * @author 蒋落琛
 * @date 2015-7-8
 */
public interface ICustomerOutputService {
	
	/**
	 * 更新用户产值信息
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-8
	 * @update
	 */
	void refreshCustomerOutput();
	
	/**
	 * 按客户负责人统计产值
	 * 
	 * @author 275636
	 * @date 2015-7-30
	 * @update
	 */
	void refreshManageEmpOutput();
	
	/**
	 * 刷新客户表近三个月产值
	 * 
	 * @author: 何斌
	 * @date: 2015年12月9日
	 * @update 
	 */
	void refreshCustomerProductValueOfThreeMonth();
}
