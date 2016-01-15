/**
 * 
 */
package com.hoau.crm.module.job.server.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IConvertResourceCustomerService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 *
 * @author 丁勇
 * @date 2015年10月12日
 */
public class ConvertResourceCustomerJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			IConvertResourceCustomerService iConvertResourceCustomerService = getBean("convertResourceCustomerService",IConvertResourceCustomerService.class);
			iConvertResourceCustomerService.convertResourceCustomerMapper();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
