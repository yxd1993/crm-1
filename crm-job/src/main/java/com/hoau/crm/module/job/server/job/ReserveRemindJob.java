package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IStatusChangeService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 预约提醒消息生成JOB
 *
 * @author 蒋落琛
 * @date 2015-8-24
 */
public class ReserveRemindJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		//从ApplicationContext中取得Spring配置的Service
        IStatusChangeService statusChangeService 
        	= getBean("statusChangeServiceImpl", IStatusChangeService.class);
        
        // 未完成指标提醒
        statusChangeService.indexMessageRemind();
	}

}
