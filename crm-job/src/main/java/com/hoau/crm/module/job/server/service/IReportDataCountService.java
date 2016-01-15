package com.hoau.crm.module.job.server.service;

/**
 * 报表数据处理SERVICE
 * 
 * @author: 何斌
 * @create: 2015年8月1日 下午2:05:56
 */
public interface IReportDataCountService {
	
	/**
	 * 执行销售报表数据处理过程
	 * 
	 * @author: 何斌
	 * @date: 2015年8月1日
	 * @update 
	 */
	void executeSaleReportProc();
	
	/**
	 * 执行客户统计数据处理过程
	 * 
	 * @author: 何斌
	 * @date: 2015年8月3日
	 * @update 
	 */
	void executeCustomerCountProc();
	
	/**
	 * 执行自定义报表处理过程
	 * 
	 * @author: 何斌
	 * @date: 2015年9月16日
	 * @update 
	 */
	void executeReportAnalysisProc();
}
