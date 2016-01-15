package com.hoau.crm.module.job.server.service;

/**
 * 消息发送SERVICE
 *
 * @author 蒋落琛
 * @date 2015-7-1
 */
public interface IPushMessageService {
	
	/**
	 * 消息同步
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	void synMessage(int synNum);
	
	/**
	 * 消息发送
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	void pushMessageJob(int pushNum);
	
	/**
	 * 消息重新发送
	 * 
	 * @param pushNum
	 * @author 蒋落琛
	 * @date 2015-8-8
	 * @update
	 */
	void pushMessageRepeatJob(int pushNum);
}
