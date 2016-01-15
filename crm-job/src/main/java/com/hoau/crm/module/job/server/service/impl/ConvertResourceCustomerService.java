/**
 * 
 */
package com.hoau.crm.module.job.server.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.job.server.dao.ConvertResourceCustomerMapper;
import com.hoau.crm.module.job.server.service.IConvertResourceCustomerService;

/**
 *
 * @author 丁勇
 * @date 2015年10月26日
 */
@org.springframework.stereotype.Service
public class ConvertResourceCustomerService implements
		IConvertResourceCustomerService {
	private static Logger LOG = LoggerFactory
			.getLogger(ConvertResourceCustomerService.class);
	@Resource
	ConvertResourceCustomerMapper convertResourceCustomerMapper;
	@Override
	@Transactional
	public void convertResourceCustomerMapper() {
		LOG.info("开始执行更新"+System.currentTimeMillis());
		convertResourceCustomerMapper.convertResourceCustomerMapper();
		LOG.info("更新结束"+System.currentTimeMillis());
	}

}
