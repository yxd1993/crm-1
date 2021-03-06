package com.hoau.crm.module.job.server.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import com.hoau.crm.module.job.server.service.IDepartmentService;
import com.hoau.hbdp.framework.server.components.jobgrid.GridJob;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 组织信息同步定时器
 *
 * @author 蒋落琛
 * @date 2015-5-18
 */

public class DeptSynJob extends GridJob implements StatefulJob {
	
	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		try {
			// 取得静态参数配置
			Properties pro = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties");
			String url = pro.getProperty("oa.url");
			String password = pro.getProperty("oa.department.password");
			String synDay = (String) getProperty(context, "synDay");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 判断数据库是否配置了时间
			if(StringUtil.isEmpty(synDay)){
				synDay = sdf.format(new Date());
			}else{
				Calendar c = Calendar.getInstance();  
				c.setTime(new Date());  
				c.set(Calendar.DATE, c.get(Calendar.DATE) - Integer.parseInt(synDay)); 
				synDay = sdf.format(c.getTime());
			}
			// 从ApplicationContext中取得Spring配置的service
			IDepartmentService departmentService = getBean("departmentServiceImpl",
					IDepartmentService.class);
			
			// OA人员组织同步
			departmentService.deptSynJob(url,synDay,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}