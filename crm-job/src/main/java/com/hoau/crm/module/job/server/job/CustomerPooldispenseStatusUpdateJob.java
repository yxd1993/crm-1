package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.ICustomerPooldispenseStatusUpdate;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 超过7天客户没有转为潜在的自动销毁
 * 275636
 */
public class CustomerPooldispenseStatusUpdateJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			ICustomerPooldispenseStatusUpdate iCustomerPooldispenseStatusUpdate = getBean(
					"custDispenseStatusUpdateImpl", ICustomerPooldispenseStatusUpdate.class);
			
			// 超过7天客户没有转为潜在的自动销毁
			iCustomerPooldispenseStatusUpdate.updateCustDspenseStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}