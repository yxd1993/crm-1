package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IPushMessageService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 消息重发定时器
 *
 * @author 蒋落琛
 * @date 2015-8-8
 */
public class PushMessageRepeatJob extends GridJob implements StatefulJob {
	
	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			IPushMessageService iPushMessageService = getBean(
					"pushMessageServiceImpl", IPushMessageService.class);
			// 消息发送条数
			int pushNum = 60;
			if(!StringUtil.isEmpty((String) getProperty(context, "pushNum"))){
				pushNum = Integer.parseInt(getProperty(context, "pushNum").toString());
			}
			
			// 执行消息重发推送
			iPushMessageService.pushMessageRepeatJob(pushNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}