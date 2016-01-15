package com.hoau.crm.module.job.server.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * DC数据初始化DAO
 * 
 * @author: 何斌
 * @create: 2015年7月28日 上午10:55:19
 */
@Repository
public interface DcDataEtlMapper {

	/**
	 * 查询结束时间
	 * 
	 * @param sysName
	 * @return
	 */
	String queryEndTime();
	
	/**
	 * 更新开始时间
	 * 
	 * @param sysName
	 */
	void updateBeginTime();
	
	/**
	 * 更新结束时间
	 * 
	 * @param sysName
	 */
	void updateEndTime();
	
	/**
	 * 更新抽取状态
	 * 
	 * @param sysName
	 */
	void updateStatus(Map<String,String> params);
}
