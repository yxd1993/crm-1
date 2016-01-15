/**
 * 
 */
package com.hoau.crm.module.job.server.job;

import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IBseDepartmentService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 *
 * @author 丁勇
 * @date 2015年8月4日
 */
public class BseDeptSynJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 取得静态参数配置
			Properties pro = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties");
			String url = pro.getProperty("bseDeptinfo.url");
			// 从ApplicationContext中取得Spring配置的service
			IBseDepartmentService iBseDepartmentSerivce = getBean("bseDepartmentService",IBseDepartmentService.class);
			
			// 基础数据平台的组织信息同步
			iBseDepartmentSerivce.synJobBseDept(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
