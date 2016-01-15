package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.MessageInfoVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 消息管理SEVICE
 * 
 * @author 蒋落琛
 * @date 2015-6-29
 */
@SuppressWarnings("rawtypes")
public interface IMessageInfoAppService {

	/**
	 * 新增消息
	 * 
	 * @param messageEntity
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public ResponseBaseEntity addMessage(MessageInfoVo messageVo, String loginName);
	
	/**
	 * 分页查询消息
	 * 
	 * @param messageVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public ResponseBaseEntity queryMessageInfo(MessageInfoVo messageVo, String loginName);
	
	/**
	 * 统计消息
	 * 
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-2
	 * @update
	 */
	public ResponseBaseEntity countMessageType(String loginName);
	
	/**
	 * 修改消息发送状态
	 * 
	 * @param ids
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-6-30
	 * @update
	 */
	public ResponseBaseEntity updateMessageSendStatus(MessageInfoVo messageVo, String loginName);

	/**
	 * 修改消息已读状态
	 * 
	 * @param ids
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-6-30
	 * @update
	 */
	public ResponseBaseEntity updateMessageReadStatus(MessageInfoVo messageVo, String loginName);

	/**
	 * 删除消息
	 * 
	 * @param ids
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-6-30
	 * @update
	 */
	public ResponseBaseEntity deleteMessage(MessageInfoVo messageVo, String loginName);
}
