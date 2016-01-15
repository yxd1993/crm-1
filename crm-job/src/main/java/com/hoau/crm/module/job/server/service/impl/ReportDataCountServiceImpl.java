package com.hoau.crm.module.job.server.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.job.server.dao.ReportDataCountMapper;
import com.hoau.crm.module.job.server.service.IReportDataCountService;

/**
 * 报表数据处理SERVICE实现
 * 
 * @author: 何斌
 * @create: 2015年8月1日 下午2:08:18
 */
@org.springframework.stereotype.Service
public class ReportDataCountServiceImpl implements IReportDataCountService {
	
	@Resource
	private ReportDataCountMapper reportDataCountMapper;

	@Transactional
	@Override
	public void executeSaleReportProc() {
		reportDataCountMapper.executeSaleReportProc();
	}

	@Transactional
	@Override
	public void executeCustomerCountProc() {
		reportDataCountMapper.executeCustomerCountProc();
	}

	@Transactional
	@Override
	public void executeReportAnalysisProc() {
		reportDataCountMapper.executeReportAnalysisProc();
	}

}
