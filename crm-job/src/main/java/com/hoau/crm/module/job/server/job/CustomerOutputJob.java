package com.hoau.crm.module.job.server.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.ICustomerOutputService;
import com.hoau.crm.module.job.server.service.IStatusChangeService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 客户产值信息更新JOB
 * 
 * @author 蒋落琛
 * @date 2015-7-8
 */
public class CustomerOutputJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 从ApplicationContext中取得Spring配置的service
			ICustomerOutputService iCustomerOutputService = getBean(
					"customerOutputServiceImpl", ICustomerOutputService.class);
			
			// 执行DC账号产值更新
			iCustomerOutputService.refreshCustomerOutput();
			
			// 执行负责人产值更新
			iCustomerOutputService.refreshManageEmpOutput();
			
			// 执行客户表近三个月产值更新
			iCustomerOutputService.refreshCustomerProductValueOfThreeMonth();
			
			// 删除客户   保留数据的天数，默认93天
	        int keepDay = 93;
	        String day = (String) getProperty(context, "keepDay");
	        if(!StringUtil.isEmpty(day)){
	        	keepDay = Integer.parseInt(day);
	        }
	        //从ApplicationContext中取得Spring配置的Service
	        IStatusChangeService statusChangeService 
	        	= getBean("statusChangeServiceImpl", IStatusChangeService.class);
	        statusChangeService.deleteWayBillByKeepDay(keepDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}