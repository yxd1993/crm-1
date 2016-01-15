package com.hoau.crm.module.job.server.service;

/**
 * @author: 何斌
 * @create: 2015年5月19日 下午8:05:27
 */
public interface IEmployeeService {
	/**
	 * 调用任务
	 * 
	 * @param url
	 * @param soapAction
	 * @param methName
	 * @param synDay
	 * @param password
	 * @author: 何斌
	 * @date: 2015年5月20日
	 * @update
	 */
	void empSynJob(String url, String synDay, String password);
}
