/**
 * 
 */
package com.hoau.crm.module.job.server.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import com.hoau.crm.module.job.server.service.ISaleManagerWorkService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;

/**
 *
 * @author 丁勇
 * @date 2015年10月12日
 */
public class PushSaleManagerJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			ISaleManagerWorkService iSaleManagerService = getBean("saleManagerWorkService",ISaleManagerWorkService.class);
			iSaleManagerService.pushSaleManagerWork();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
