package com.hoau.crm.module.job.server.service.impl;

import javax.annotation.Resource;

import com.hoau.crm.module.job.server.dao.CustDispenseStatusUpdateMapper;
import com.hoau.crm.module.job.server.service.ICustomerPooldispenseStatusUpdate;

@org.springframework.stereotype.Service
public class CustDispenseStatusUpdateImpl implements ICustomerPooldispenseStatusUpdate{
	@Resource
	CustDispenseStatusUpdateMapper custDispenseStatusUpdateMapper;

	@Override
	public void updateCustDspenseStatus() {
		custDispenseStatusUpdateMapper.updateCustDspenseStatus();
	}
}
