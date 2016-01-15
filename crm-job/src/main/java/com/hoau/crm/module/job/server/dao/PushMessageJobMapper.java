package com.hoau.crm.module.job.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoRepeatEntity;

/**
 * 消息推送MAPPER
 *
 * @author 蒋落琛
 * @date 2015-7-1
 */
public interface PushMessageJobMapper {

	/**
	 * 分页查找消息信息
	 * 
	 * @param rb
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	List<MessageInfoEntity> queryMessageInfo(int pushNum);
	
	/**
	 * 修改消息发送状态
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	void updateMessageSendStatus(Map<String, Object> map);
	
	/**
	 * 分页查询补发消息信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-8-8
	 * @update
	 */
	List<MessageInfoRepeatEntity> queryRepeatMessageInfo(RowBounds rb);
	
	/**
	 * 新增发送失败消息
	 * 
	 * @param messageInfo
	 * @author 蒋落琛
	 * @date 2015-8-8
	 * @update
	 */
	void insertSendFailureMessage(MessageInfoRepeatEntity messageInfo);
	
	/**
	 * 将消息置为发送成功
	 * 
	 * @param ids
	 * @author 蒋落琛
	 * @date 2015-8-8
	 * @update
	 */
	void updateMessageSuccess(List<String> ids);
	
	/**
	 * 将消息增加一次发送次数
	 * 
	 * @param ids
	 * @author 蒋落琛
	 * @date 2015-8-8
	 * @update
	 */
	void updateMessageRepeatNum(List<String> ids);
}
