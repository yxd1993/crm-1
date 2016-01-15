package com.hoau.crm.module.customer.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.SequenceNoEntity;

/**
 * 序列号DAO
 * 
 * @author: 何斌
 * @create: 2015年5月28日 上午8:39:29
 */

@Repository
public interface SequenceNoMapper {
	/**
	 * 根据类型查找序列号信息
	 * @param type
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update 
	 */
	SequenceNoEntity query(String businessType);
	/**
	 * 更新
	 * @param sequenceNoEntity
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update 
	 */
	void updateSeqNo(SequenceNoEntity sequenceNoEntity);
	
	/**
	 * 锁表
	 * 
	 * @param type
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update 
	 */
	void lockWfno(String type);
	
	/**
	 * 加锁
	 * 
	 * @author 蒋落琛
	 * @date 2015-6-8
	 * @update
	 */
	void setSessionLockWaitTimeout();
}
