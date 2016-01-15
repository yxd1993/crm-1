package com.hoau.crm.module.bse.api.server.service;

import com.hoau.crm.module.bse.api.shared.domain.OperationLogEntity;

/**
 * 操作日志SERVICE
 * 
 * @author: 何斌
 * @create: 2015年6月10日 上午9:12:33
 */
public interface IOperationLogService {
	/**
	 * 保存操作日志
	 * 
	 * @param operationLogEntity
	 * @author: 何斌
	 * @date: 2015年6月10日
	 * @update 
	 */
	void saveOperationLog(OperationLogEntity operationLogEntity);
}
