package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.customer.api.server.ICustomerTotalService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 客户统计Job
 * 
 * @author: 何斌
 * @create: 2015年12月29日 下午3:33:20
 */
public class CustomerTotalJob extends GridJob implements StatefulJob{

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		ICustomerTotalService iCustomerTotalService = getBean(
				"customerTotalService", ICustomerTotalService.class);
		//执行
		iCustomerTotalService.execJob();
	}

}
