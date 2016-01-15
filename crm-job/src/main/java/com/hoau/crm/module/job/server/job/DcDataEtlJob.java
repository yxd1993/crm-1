package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IDcDataEtlService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 * DC数据初始化
 * 
 * @author: 何斌
 * @create: 2015年7月28日 上午10:47:14
 */
public class DcDataEtlJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {

		try {
			// 从ApplicationContext中取得Spring配置的service
			IDcDataEtlService dcDataEtlService = getBean("dcDataEtlServiceImpl",
					IDcDataEtlService.class);
			
			//更新开始时间
			dcDataEtlService.updateBeginTime();
			
			//执行转换
			dcDataEtlService.executeEtlFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
