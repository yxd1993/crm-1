package com.hoau.crm.module.sales.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;
import com.hoau.crm.module.sales.api.server.service.IReportCustomerCountService;
import com.hoau.crm.module.sales.api.shared.domain.ReportCustomerCountEntity;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.crm.module.sales.server.dao.ReportCustomerCountMapper;

/**
 * 客户统计SERVICE
 * 
 * @author: 何斌
 * @create: 2015年7月18日 上午11:23:23
 */
@Service
public class ReportCustomerCountService implements IReportCustomerCountService{
	
	@Resource
	IDepartmentService departmentService;
	
	@Resource
	ReportCustomerCountMapper reportCustomerCountMapper;
	
	@Override
	public ReportCustomerCountEntity queryCustomerCountData(String deptCode) {
		ReportCustomerCountEntity reportCustomerCountEntity = new ReportCustomerCountEntity();
		Map<String,String> params = new HashMap<String,String>();
		params.put("deptCode", deptCode);
		//查询当前部门客户总数和名称
		ReportDataEntity reportDataEntity = reportCustomerCountMapper.queryCurrentCustomerCountData(deptCode);
		if(reportDataEntity == null){
			throw new SalesCommonException(SalesCommonException.REPORT_DATA_NULL);
		}
		reportCustomerCountEntity.setDeptName(reportDataEntity.getType());
		//客户总数
		reportCustomerCountEntity.setTotalCustomer(reportDataEntity.getDataOne());
		//客户性质分布
		List<ReportDataEntity> customerNatureLists = reportCustomerCountMapper.queryCustomerNatureDistribution(deptCode);
		if(customerNatureLists == null ){
			throw new SalesCommonException(SalesCommonException.REPORT_DATA_NULL);
		}
		reportCustomerCountEntity.setCustomerNatureDistributions(customerNatureLists);
		//客户详情
		//统计当前部门客户明细
		List<ReportDataEntity> reportDataEntities = reportCustomerCountMapper.queryCurrentCustomerCountDetailData(deptCode);
		if( reportDataEntities == null){
			throw new SalesCommonException(SalesCommonException.REPORT_DATA_NULL); 
		}
		reportCustomerCountEntity.setCustomerDetails(reportDataEntities);
		return reportCustomerCountEntity;
	}

}
