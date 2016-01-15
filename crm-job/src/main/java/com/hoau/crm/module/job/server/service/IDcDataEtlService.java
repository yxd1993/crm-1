package com.hoau.crm.module.job.server.service;

import java.util.Map;


/**
 * DC数据初始化
 * 
 * @author: 何斌
 * @create: 2015年7月28日 上午10:51:01
 */

public interface IDcDataEtlService {
	/**
	 * 执行ETL文件
	 * 
	 * @author: 何斌
	 * @date: 2015年7月28日
	 * @update 
	 */
	public void executeEtlFile();
	
	/**
	 * 保存状态信息
	 * @param map
	 */
	public void saveLog(Map<String,String> map);
	
	/**
	 * 更新开始时间
	 */
	public void updateBeginTime();
}
