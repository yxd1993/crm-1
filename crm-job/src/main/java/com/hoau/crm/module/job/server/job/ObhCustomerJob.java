package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IObhCustomerService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 官网客户数据同步
 * 
 * @author: 何斌
 * @create: 2015年12月21日 下午4:45:24
 */
public class ObhCustomerJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			IObhCustomerService obhCustomerService = getBean("obhCustomerServiceImpl", IObhCustomerService.class);
			obhCustomerService.customerDataSync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
