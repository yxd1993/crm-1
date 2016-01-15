package com.hoau.crm.module.job.server.service;

/**
 * 状态装换SERVICE
 * 
 * @author: 何斌
 * @create: 2015年7月13日 上午10:31:06
 */
public interface IStatusChangeService {
	/**
	 * 刷新已过期合同状态
	 * 
	 * @author: 何斌
	 * @date: 2015年7月13日
	 * @update 
	 */
	public void statusChangeOfContract();
	
	/**
	 * 合同归档,更新客户性质为签约客户
	 * 
	 * @author: 何斌
	 * @date: 2015年7月15日
	 * @update 2015年11月24日
	 */
	public void statusChangeOfCustomerToSign();
	
	/**
	 * 更新客户的状态：签约、发货置为流失
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	public void refreshCustomerStatus();
	
	/**
	 * 更新客户的第1单、最后1单发货以及根据发货改变客户状态
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	public void refreshCustomerWayBillTime();
	
	/**
	 * 根据保留天数删除运单信息
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	public void deleteWayBillByKeepDay(int keepDay);
	
	/**
	 * 指标未完成消息提醒
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-28
	 * @update
	 */
	public void indexMessageRemind();
	
	/**
	 * 合同过期提前30天提醒
	 * 
	 * @author: 何斌
	 * @date: 2015年8月4日
	 * @update 
	 */
	public void contractOutOfTimeRemind();
	
	/**
	 * 随机洽谈记录
	 * 
	 * @author: 何斌
	 * @date: 2015年8月18日
	 * @update 
	 */
	public void randomChatInfos();
	
	/**
	 * 更新客户的发货日志
	 * 
	 * @author 蒋落琛
	 * @date 2015-10-22
	 * @update
	 */
	public void refreshCustomerWayBillLog();
}
