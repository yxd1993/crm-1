/**
 * 
 */
package com.hoau.crm.module.job.server.job;

import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IServerStatusJobService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * 定时调度nginx服务器状态
 * @author 丁勇
 * @date 2015年8月12日
 */
public class ServerStatusSynJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 取得静态参数配置
			Properties pro = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties");
			String url = pro.getProperty("nginxStatusInfo.url");
			// 从ApplicationContext中取得Spring配置的service
			IServerStatusJobService serverStatusJobService = getBean("serverStatusJobService",IServerStatusJobService.class);
			
			// 服务器NGINX状态监控
			serverStatusJobService.synStatusService(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
