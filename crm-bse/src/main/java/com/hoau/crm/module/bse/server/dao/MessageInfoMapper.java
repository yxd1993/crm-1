package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.domain.PushUserEntity;
import com.hoau.crm.module.appcore.api.shared.vo.MessageInfoVo;

/**
 * 消息管理MAPPER
 * 
 * @author 蒋落琛
 * @date 2015-6-29
 */
public interface MessageInfoMapper {

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
	public List<MessageInfoEntity> getMessageInfo(RowBounds r,
			Map<String, String> map);

	/**
	 * 查询消息总数
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public long countMessageInfo(Map<String, String> map);
	
	/**
	 * 统计用户消息
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-2
	 * @update
	 */
	public List<MessageInfoVo> countMessageType(Map<String, String> map);

	/**
	 * 新增消息
	 * 
	 * @param messageEntity
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void addMessage(MessageInfoEntity messageEntity);

	/**
	 * 修改消息发送状态
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void updateMessageSendStatus(Map<String, Object> map);

	/**
	 * 修改消息已读状态
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void updateMessageReadStatus(Map<String, Object> map);

	/**
	 * 删除消息
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	public void deleteMessage(Map<String, Object> map);
	
	/**
	 * 根据条件修改消息的状态、发送时间、内容等
	 * 
	 * @param messageEntity
	 * @author 蒋落琛
	 * @date 2015-7-30
	 * @update
	 */
	public void updateMessageByCondition(MessageInfoEntity messageEntity);
	
	/**
	 * 查询百度推送用户信息
	 * 
	 * @param PushUserEntity
	 * @return
	 */
	public List<PushUserEntity> findPushUser(PushUserEntity PushUserEntity);
	
	/**
	 *  根据工号查询用户是否拥有百度推送账号信息
	 * 
	 * @param PushUserEntity
	 * @return
	 */
	public long findPushUserByEmpCode(PushUserEntity PushUserEntity);
}
