package com.hoau.crm.module.job.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.job.server.dao.CustomerOutputMapper;
import com.hoau.crm.module.job.server.service.ICustomerOutputService;

/**
 * 客户产值信息更新
 * 
 * @author 蒋落琛
 * @date 2015-7-8
 */
@org.springframework.stereotype.Service
public class CustomerOutputServiceImpl implements ICustomerOutputService {

	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory
			.getLogger(CustomerOutputServiceImpl.class);

	@Resource
	private CustomerOutputMapper customerOutputMapper;
	
	@Override
	public void refreshCustomerOutput() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		LOG.info("refreshCustomerOutput start : "
				+ sDateFormat.format(new Date()) + "");
		try {
			// 当前时间
			Date currDate = new Date();
			// 获取昨天的年月
			Calendar cal = Calendar.getInstance();
			cal.setTime(currDate);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1); 
			Map<String, Object> map = new HashMap<String, Object>();
			LOG.info("refreshCustomerOutput Date : " + sDateFormat2.format(cal.getTime()));
			map.put("year", cal.get(Calendar.YEAR));
			map.put("month", (cal.get(Calendar.MONTH) + 1));
			// 新增客户产值数据   范围为昨天所属的年月，主要是为新增的客户
			customerOutputMapper.addCustomerOutput(map);
			// 刷新产值   范围为1号至昨天晚上12点
			String endDate = sDateFormat2.format(currDate) + " 00:00:00";
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(cal.getTime());
			cal2.set(Calendar.DATE, 1); 
			String startDate = sDateFormat2.format(cal2.getTime()) + " 00:00:00";
			LOG.info("refreshCustomerOutput startDate : " + startDate + ", endDate : " + endDate);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			// 刷新产值数据
			customerOutputMapper.refreshCustomerOutput(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("refreshCustomerOutput end : "
				+ sDateFormat.format(new Date()) + "");
	}

	/**
	 * 按客户负责人统计产值
	 * 
	 * @author 275636
	 * @date 2015-7-30
	 * @update
	 */
	@Override
	public void refreshManageEmpOutput() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		LOG.info("refreshManageEmpOutput start : "
				+ sDateFormat.format(new Date()) + "");
		try {
			// 当前时间
			Date currDate = new Date();
			// 获取昨天的年月
			Calendar cal = Calendar.getInstance();
			cal.setTime(currDate);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1); 
			Map<String, Object> map = new HashMap<String, Object>();
			LOG.info("refreshManageEmpOutput Date : " + sDateFormat2.format(cal.getTime()));
			map.put("year", cal.get(Calendar.YEAR));
			map.put("month", (cal.get(Calendar.MONTH) + 1));
			// 新增客户产值数据   范围为昨天所属的年月，主要是为新增的客户
			customerOutputMapper.addManageEmpOutput(map);
			// 刷新产值   范围为1号至昨天晚上12点
			String endDate = sDateFormat2.format(currDate) + " 00:00:00";
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(cal.getTime());
			cal2.set(Calendar.DATE, 1); 
			String startDate = sDateFormat2.format(cal2.getTime()) + " 00:00:00";
			LOG.info("refreshManageEmpOutput startDate : " + startDate + ", endDate : " + endDate);
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			// 刷新产值数据
			customerOutputMapper.refreshManageEmpOutput(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("refreshManageEmpOutput end : "
				+ sDateFormat.format(new Date()) + "");
	}

	@Override
	@Transactional
	public void refreshCustomerProductValueOfThreeMonth() {
		customerOutputMapper.refreshCustomerProductValueOfThreeMonth();
	}
}
