package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IStatusChangeService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * 状态转换JOB
 * 
 * @author: 何斌
 * @create: 2015年7月13日 上午10:26:47
 */
public class StatusChangeJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		//从ApplicationContext中取得Spring配置的Service
        IStatusChangeService statusChangeService 
        	= getBean("statusChangeServiceImpl", IStatusChangeService.class);
        // 合同状态
        statusChangeService.statusChangeOfContract();
        // 合同过期提前提醒
        statusChangeService.contractOutOfTimeRemind();
        // 合同归档,签约客户
        statusChangeService.statusChangeOfCustomerToSign();
        // 更新客户的状态：签约、发货置为流失
        statusChangeService.refreshCustomerStatus();
        //随机洽谈记录抽取
        statusChangeService.randomChatInfos();
	}

}
