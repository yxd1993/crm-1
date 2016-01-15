package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IReportDataCountService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 报表数据处理JOB
 * 
 * @author: 何斌
 * @create: 2015年8月1日 下午2:04:08
 */
public class ReportDataCountJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		// 从ApplicationContext中取得Spring配置的service
		IReportDataCountService reportDataCountService = getBean("reportDataCountServiceImpl",
				IReportDataCountService.class);
		
		// 刷新销售数据
		reportDataCountService.executeSaleReportProc();
		
		// 刷新客户统计数据
		reportDataCountService.executeCustomerCountProc();
		
		//刷新自定义报表数据
		reportDataCountService.executeReportAnalysisProc();
	}

}
