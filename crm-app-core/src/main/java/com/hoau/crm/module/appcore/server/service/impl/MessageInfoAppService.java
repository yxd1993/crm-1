package com.hoau.crm.module.appcore.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoAppService;
import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.exception.MessageInfoException;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.MessageInfoVo;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 消息SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-6-29
 */
@SuppressWarnings("rawtypes")
@Service
public class MessageInfoAppService implements IMessageInfoAppService {

	@Resource
	private IMessageInfoService iMessageInfoService;
	
	@Resource
	ILoginService iLoginService;

	@Override
	public ResponseBaseEntity addMessage(MessageInfoVo messageVo, String loginName) {
		if (messageVo == null
				|| (messageVo.getMessageInfoEntity() == null
						&& (messageVo.getMessageList() == null || messageVo
								.getMessageList().size() == 0))) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		// 单个新增
		if(messageVo.getMessageInfoEntity() != null){
			// 新增消息
			iMessageInfoService.addMessage(messageVo.getMessageInfoEntity(), user);
		}
		// 批量新增
		if(messageVo.getMessageList() != null){
			for(int i=0; i<messageVo.getMessageList().size(); i++){
				MessageInfoEntity messInfo = messageVo.getMessageList().get(i);
				// 新增消息
				iMessageInfoService.addMessage(messInfo, user);
			}
		}
		// 返回值
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity queryMessageInfo(MessageInfoVo messageVo,
			String loginName) {
		// 查询条件判断
		if (messageVo == null || StringUtil.isEmpty(messageVo.getBusType())) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGE_TYPE_NULL);
		}
		// 查询消息
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<MessageInfoEntity> messList = iMessageInfoService
				.queryMessageInfo(messageVo, user);
		long count = iMessageInfoService.countMessageInfo(messageVo, user);
		// 返回值
		ResponseBaseEntity<MessageInfoVo> result = new ResponseBaseEntity<MessageInfoVo>();
		MessageInfoVo mVo = new MessageInfoVo();
		mVo.setMessageList(messList);
		mVo.setTotalCount(count);
		result.setResult(mVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@Override
	public ResponseBaseEntity countMessageType(String loginName) {
		// 查询消息
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<MessageInfoVo> messageList = iMessageInfoService.countMessageType(user);
		// 返回值
		ResponseBaseEntity<MessageInfoVo> result = new ResponseBaseEntity<MessageInfoVo>();
		MessageInfoVo mVo = new MessageInfoVo();
		mVo.setMessageVoList(messageList);
		result.setResult(mVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity updateMessageSendStatus(MessageInfoVo messageVo,
			String loginName) {
		if (messageVo == null || messageVo.getMessageInfoEntity() == null) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iMessageInfoService.updateMessageSendStatus(
				messageVo.getMessageInfoEntity(), user);
		// 返回值
		ResponseBaseEntity<MessageInfoVo> result = new ResponseBaseEntity<MessageInfoVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity updateMessageReadStatus(MessageInfoVo messageVo,
			String loginName) {
		if (messageVo == null) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iMessageInfoService.updateMessageReadStatus(messageVo.getIds(), user);
		// 返回值
		ResponseBaseEntity<MessageInfoVo> result = new ResponseBaseEntity<MessageInfoVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity deleteMessage(MessageInfoVo messageVo, String loginName) {
		if (messageVo == null) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iMessageInfoService.deleteMessage(messageVo.getIds(), user);
		// 返回值
		ResponseBaseEntity<MessageInfoVo> result = new ResponseBaseEntity<MessageInfoVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
