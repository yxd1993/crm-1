package com.hoau.crm.module.job.server.job;

import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IDistrictService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * 省市区信息同步
 * 
 * @author 潘强
 * @date 2015-7-8
 */
public class BseDistrictJob extends GridJob implements StatefulJob {

	@Override
	protected void doExecute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			// 取得静态参数配置
			Properties pro = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties");
			String url = pro.getProperty("districtinfo.url");
			// 从ApplicationContext中取得Spring配置的service
			IDistrictService districtService= getBean("districtservice",IDistrictService.class);
			
			// 省市区信息同步
			districtService.synJob(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
