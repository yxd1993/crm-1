package com.hoau.crm.module.bse.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IOperationLogService;
import com.hoau.crm.module.bse.api.shared.domain.OperationLogEntity;
import com.hoau.crm.module.bse.server.dao.OperationLogMapper;

/**
 * 操作日志SERVICE
 * @author: 何斌
 * @create: 2015年6月10日 上午9:25:10
 */
@Service
public class OperationLogService implements IOperationLogService {
	
	@Resource
	private OperationLogMapper operationLogMapper;

	@Override
	@Transactional
	public void saveOperationLog(OperationLogEntity operationLogEntity) {
		operationLogMapper.saveOperationLog(operationLogEntity);
	}

}
