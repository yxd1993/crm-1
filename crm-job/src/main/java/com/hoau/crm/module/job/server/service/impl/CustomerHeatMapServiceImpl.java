package com.hoau.crm.module.job.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.hoau.crm.module.job.server.dao.CustExecProcJobMapper;
import com.hoau.crm.module.job.server.service.ICustomerHeatMapService;

@org.springframework.stereotype.Service
public class CustomerHeatMapServiceImpl implements ICustomerHeatMapService {
	@Resource
	CustExecProcJobMapper custExecProcJobMapper;
	
	@Override
	public void refreshCustomerHeatMap() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("procName", "CRM_CUSTOMER_HEATMAP");
		custExecProcJobMapper.execCustProc(map);
	}

	@Override
	public void refreshCUstomerHeatMapOutPut() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("procName", "CRM_CUSTOMER_HEATMAP_OUTPUT");
		custExecProcJobMapper.execCustProc(map);
	}

}
