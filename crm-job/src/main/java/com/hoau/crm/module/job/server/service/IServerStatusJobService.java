/**
 * 
 */
package com.hoau.crm.module.job.server.service;

/**
 *
 * @author 丁勇
 * @date 2015年8月12日
 */
public interface IServerStatusJobService {

	/**
	 * 定时调度nginx服务器状态
	 * @param url
	 * @author 丁勇
	 * @date 2015年8月12日
	 * @update 
	 */
	void synStatusService(String url);
}
