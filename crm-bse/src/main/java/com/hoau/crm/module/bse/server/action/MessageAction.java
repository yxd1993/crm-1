package com.hoau.crm.module.bse.server.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.exception.MessageInfoException;
import com.hoau.crm.module.appcore.api.shared.vo.MessageInfoVo;
import com.hoau.crm.module.bse.api.server.service.IUserService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * MESSAGE ACTION
 *
 * @author 蒋落琛
 * @date 2015-6-30
 */
@Controller
@Scope("prototype")
public class MessageAction extends AbstractAction {

	private static final long serialVersionUID = 4072897299137498451L;
	
	@Resource
	private IMessageInfoService iMessageInfoService;
	
	@Resource
	private IUserService iUserService;

	/**
	 * 消息信息
	 */
	private List<MessageInfoEntity> messageList;
	
	/**
	 * 消息信息VO
	 */
	private MessageInfoVo messageInfoVo;
	
	/**
	 * 消息信息实体
	 */
	private MessageInfoEntity messageInfoEntity;
	
	public String queryMessageInfo() {
		try {
			if(messageInfoVo == null){
				messageInfoVo = new MessageInfoVo();
			}
			messageInfoVo.setStart(this.start);
			messageInfoVo.setLimit(this.limit);
			UserEntity user = (UserEntity) UserContext.getCurrentUser();
			messageList = iMessageInfoService.queryMessageInfo(messageInfoVo, user);
			this.setTotalCount(iMessageInfoService.countMessageInfo(messageInfoVo, user));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 广播
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-14
	 * @update
	 */
	public String addMessage() {
		try {
			UserEntity user = (UserEntity) UserContext.getCurrentUser();
			// 条件判断
			if (messageInfoVo == null || messageInfoVo.getMessageInfoEntity() == null) {
				throw new MessageInfoException(
						MessageInfoException.MESSAGEINFO_NULL);
			}
			MessageInfoEntity messageEntity = messageInfoVo.getMessageInfoEntity();
			// 判断时间是否小于当前时间
			if(messageEntity.getAllowSendTime().getTime() < new Date().getTime()){
				throw new MessageInfoException(
						MessageInfoException.MESSAGE_SENDTIME_EXCEPTION);
			}
			// 默认未发送
			messageEntity.setIsSend(BseConstants.MESSAGE_STATUS_NO_SEND);
			// 新增消息 ANDROID
			messageEntity.setReceiveUserId(BseConstants.MESSAGE_RECEIVE_ALL_ANDROID);
			messageEntity.setPushType(BseConstants.MESSAGE_PUSH_TYPE_ANDROID_All);
			iMessageInfoService.addMessage(messageEntity, user);
			// 新增消息 IOS
			messageEntity.setReceiveUserId(BseConstants.MESSAGE_RECEIVE_ALL_IOS);
			messageEntity.setPushType(BseConstants.MESSAGE_PUSH_TYPE_IOS_All);
			iMessageInfoService.addMessage(messageEntity, user);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 单播
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-14
	 * @update
	 */
	public String addSingleDeviceMessage() {
		try {
			UserEntity user = (UserEntity) UserContext.getCurrentUser();
			// 条件判断
			if (messageInfoVo == null || messageInfoVo.getMessageInfoEntity() == null) {
				throw new MessageInfoException(
						MessageInfoException.MESSAGEINFO_NULL);
			}
			MessageInfoEntity messageEntity = messageInfoVo.getMessageInfoEntity();
			// 判断时间是否小于当前时间
			if(messageEntity.getAllowSendTime().getTime() < new Date().getTime()){
				throw new MessageInfoException(
						MessageInfoException.MESSAGE_SENDTIME_EXCEPTION);
			}
			// 默认未发送
			messageEntity.setIsSend(BseConstants.MESSAGE_STATUS_NO_SEND);
			// 角色 ID
			List<String> list = new ArrayList<String>();
			list.add(messageInfoVo.getRoleId());
			// 查询角色下的用户信息
			List<UserEntity> userList = iUserService.getUserList(null, list, new RowBounds(0, 10000));
			for(int i=0; i<userList.size(); i++){
				UserEntity currUser = userList.get(i);
				if(currUser.getEmpEntity() != null && !StringUtil.isEmpty(currUser.getEmpEntity().getEmpCode())){
					messageEntity.setReceiveUserId(currUser.getEmpEntity().getEmpCode());
					messageEntity.setPushType(BseConstants.MESSAGE_PUSH_TYPE_SINGLE);
					// 新增消息 ANDROID
					iMessageInfoService.addMessage(messageEntity, user);
				}
			}
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	public List<MessageInfoEntity> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageInfoEntity> messageList) {
		this.messageList = messageList;
	}

	public MessageInfoVo getMessageInfoVo() {
		return messageInfoVo;
	}

	public void setMessageInfoVo(MessageInfoVo messageInfoVo) {
		this.messageInfoVo = messageInfoVo;
	}

	public MessageInfoEntity getMessageInfoEntity() {
		return messageInfoEntity;
	}

	public void setMessageInfoEntity(MessageInfoEntity messageInfoEntity) {
		this.messageInfoEntity = messageInfoEntity;
	}
}
