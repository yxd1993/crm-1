package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.ICustomerCutProductionService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 客户减产信息查询查询JOB
 *
 * @author 蒋落琛
 * @date 2015-7-16
 */
public class CustomerCutProductionJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			ICustomerCutProductionService iCustomerCutProductionService = getBean(
					"customerCutProductionServiceImpl", ICustomerCutProductionService.class);
			
			// 执行客户减产信息查询
			iCustomerCutProductionService.refreshCutProductionInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}