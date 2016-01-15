package com.hoau.crm.module.appcore.api.server.service;

import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.vo.MessageInfoVo;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

/**
 * 消息管理SEVICE
 * 
 * @author 蒋落琛
 * @date 2015-6-29
 */
public interface IMessageInfoService {

	/**
	 * 分页查询消息信息
	 * 
	 * @param r
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public List<MessageInfoEntity> queryMessageInfo(MessageInfoVo messageInfoVo, UserEntity user);

	/**
	 * 查询消息总数
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public long countMessageInfo(MessageInfoVo messageInfoVo, UserEntity user);
	
	/**
	 * 统计用户消息
	 * 
	 * @param user
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-2
	 * @update
	 */
	public List<MessageInfoVo> countMessageType(UserEntity user);

	/**
	 * 新增消息
	 * 
	 * @param messageEntity
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void addMessage(MessageInfoEntity messageEntity, UserEntity user);

	/**
	 * 修改消息发送状态
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void updateMessageSendStatus(MessageInfoEntity messageEntity, UserEntity user);

	/**
	 * 修改消息已读状态
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void updateMessageReadStatus(List<String> ids, UserEntity user);
	
	/**
	 * 根据条件修改消息的状态、发送时间、内容等
	 * 
	 * @param messageEntity
	 * @author 蒋落琛
	 * @date 2015-7-30
	 * @update
	 */
	public void updateMessageByCondition(MessageInfoEntity messageEntity, UserEntity user);

	/**
	 * 删除消息
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void deleteMessage(List<String> ids, UserEntity user);
}
