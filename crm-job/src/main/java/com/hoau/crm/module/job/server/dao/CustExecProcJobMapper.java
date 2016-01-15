package com.hoau.crm.module.job.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.QueryCreateSql;

@Repository
public interface CustExecProcJobMapper {
	/**
	 * @param querySql
	 * @return
	 * 获取过程
	 */
	public List<QueryCreateSql> selectPageQuerySql();
	
	/**
	 * @param procName
	 * 执行过程
	 */
	public void execCustProc(Map<String,String> procName);
	
	/**
	 * @param createSqls
	 * 更新存储过程执行时间
	 */
	public void updateBatchProcDate(List<QueryCreateSql> createSqls);

}
