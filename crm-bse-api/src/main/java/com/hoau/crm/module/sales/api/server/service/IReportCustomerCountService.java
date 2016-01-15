/**
 * 
 */
package com.hoau.crm.module.sales.api.server.service;

import com.hoau.crm.module.sales.api.shared.domain.ReportCustomerCountEntity;


/**
 * 模块二统计报表
 * 
 * @author: 何斌
 * @create: 2015年7月18日 上午11:17:00
 */
public interface IReportCustomerCountService {
	/**
	 * 客户统计
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月20日
	 * @update 
	 */
	ReportCustomerCountEntity queryCustomerCountData(String deptCode);
	
}
