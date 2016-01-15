package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IStatusChangeService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 用户运单时间更新与对应状态更新
 *
 * @author 蒋落琛
 * @date 2015-7-27
 */
public class CustomerWayBillTimeJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		//从ApplicationContext中取得Spring配置的Service
        IStatusChangeService statusChangeService 
        	= getBean("statusChangeServiceImpl", IStatusChangeService.class);
        
        // 更新客户的状态：更新客户的第1单、最后1单发货以及根据发货改变客户状态
        statusChangeService.refreshCustomerWayBillTime();
        
        // 更新客户的发货日志
        statusChangeService.refreshCustomerWayBillLog();
	}

}
