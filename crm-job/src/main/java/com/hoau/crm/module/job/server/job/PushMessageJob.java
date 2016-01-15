package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IPushMessageService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 消息同步定时器
 *
 * @author 蒋落琛
 * @date 2015-5-18
 */

public class PushMessageJob extends GridJob implements StatefulJob {
	
	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			IPushMessageService iPushMessageService = getBean(
					"pushMessageServiceImpl", IPushMessageService.class);
			// 消息同步条数
			int synNum = 60;
			// 消息发送条数
			int pushNum = 60;
			if(!StringUtil.isEmpty((String) getProperty(context, "synNum"))){
				synNum = Integer.parseInt(getProperty(context, "synNum").toString());
			}
			if(!StringUtil.isEmpty((String) getProperty(context, "pushNum"))){
				pushNum = Integer.parseInt(getProperty(context, "pushNum").toString());
			}
			
			// 新增消息
			iPushMessageService.synMessage(synNum);
			
			// 执行消息推送
			iPushMessageService.pushMessageJob(pushNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}