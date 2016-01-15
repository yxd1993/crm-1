package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.ICustExecProcService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * @author 275636
 *自定义存储过程执行
 */
public class CustExecProcJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			ICustExecProcService iCustExecProcService = getBean(
					"custExecProcServiceImpl", ICustExecProcService.class);
			iCustExecProcService.execProc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
