package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.ICustomerCutProductionService;
import com.hoau.crm.module.job.server.service.ICustomerHeatMapService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

public class CustomerHeatMapJob extends GridJob implements StatefulJob{

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			ICustomerHeatMapService iCustomerHeatMapService = getBean(
					"customerHeatMapServiceImpl", ICustomerHeatMapService.class);
			
			iCustomerHeatMapService.refreshCustomerHeatMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
