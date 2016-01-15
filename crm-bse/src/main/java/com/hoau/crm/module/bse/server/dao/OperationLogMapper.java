package com.hoau.crm.module.bse.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.OperationLogEntity;

/**
 * 操作日志DAO
 * 
 * @author: 何斌
 * @create: 2015年6月10日 上午9:14:49
 */
@Repository
public interface OperationLogMapper {
	/**
	 * 保存操作日志
	 * @param operationLogEntity
	 * @author: 何斌
	 * @date: 2015年6月10日
	 * @update 
	 */
	void saveOperationLog(OperationLogEntity operationLogEntity);
}
