package com.hoau.crm.module.job.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 状态转换DAO
 * 
 * @author: 何斌
 * @create: 2015年7月13日 上午10:47:25
 */
@Repository
public interface StatusChangeMapper {
	/**
	 * 刷新已过期合同状态
	 * 
	 * @author: 何斌
	 * @date: 2015年7月13日
	 * @update 
	 */
	public void StatusChangeOfContract();
	
	/**
	 * 更新客户的状态：已过期合同,变发货或流失
	 * 
	 * @param map
	 * @author 何斌
	 * @date 2015-08-20
	 * @update
	 */
	public void refreshCustomerStatusOutOfTimeContract(Map<String, String> map);
	
	/**
	 * 查询客户的状态：更新客户的状态：已过期合同,变发货或流失
	 * 
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public List<Map<String,Object>> getCustomerStatusIsOutOfTimeContract(Map<String,String> map);
	
	
	/**
	 * 合同归档,更新客户性质为签约客户
	 * 
	 * @author: 何斌
	 * @date: 2015年7月15日
	 * @update 
	 */
	public void statusChangeOfCustomerToSign(Map<String,String> map);
	
	/**
	 * 查询客户的状态：合同归档,更新客户性质为签约客户
	 * 
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public List<Map<String,String>> getCustomerStatusIsSign(Map<String,String> map);
	
	/**
	 * 查询客户的状态：签约、发货置为流失
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	public List<Map<String, String>> selectCustomerStatusIsLoss(Map<String, String> map);
	
	/**
	 * 更新客户的状态：签约、发货置为流失
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	public void refreshCustomerStatus(Map<String, String> map);
	
	/**
	 * 更新客户的第1单、最后1单发货以及根据发货改变客户状态
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	public void refreshCustomerWayBillTime(Map<String, String> map);
	
	/**
	 * 查询客户的状态：意向、流失置为发货
	 * 
	 * @param map
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public List<Map<String,Object>> getCustomerStatusIsDeliJver(Map<String, String> map);
	
	/**
	 * 根据保留天数删除运单信息
	 * 
	 * @param keepDay
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	public void deleteWayBillByKeepDay(Map<String, String> map);
	
	/**
	 * 根据保留天数删除运单签收信息
	 * 
	 * @param keepDay
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	public void deleteWaybillSignByKeepDay(Map<String, String> map);
	
	/**
	 * 合同过期提前提醒
	 * 
	 * @param endTime
	 * @author: 何斌
	 * @date: 2015年8月4日
	 * @update 
	 */
	public List<String> contractOutOfTimeRemind(String endTime);
	
	/**
	 * 合同过期提前提醒,合同状态修改
	 * 
	 * @param endTime
	 * @author: 何斌
	 * @date: 2015年8月6日
	 * @update 
	 */
	public void updateContractStatusOutofTimeRemind(String endTime);
	
	/**
	 * 执行随机抽取洽谈记录存储过程
	 * 
	 * @author: 何斌
	 * @date: 2015年8月18日
	 * @update 
	 */
	public void executeRandomChatInfosProc();
	
	/**
	 * 更新客户的发货日志
	 * 
	 * @author 蒋落琛
	 * @date 2015-10-22
	 * @update
	 */
	public void refreshCustomerWayBillLog(Map<String, String> map);
}
