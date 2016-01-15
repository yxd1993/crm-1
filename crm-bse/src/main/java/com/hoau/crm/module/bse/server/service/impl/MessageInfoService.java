package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.domain.PushUserEntity;
import com.hoau.crm.module.appcore.api.shared.exception.MessageInfoException;
import com.hoau.crm.module.appcore.api.shared.exception.UserAppException;
import com.hoau.crm.module.appcore.api.shared.vo.MessageInfoVo;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.MessageInfoMapper;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 消息SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-6-29
 */
@Service
public class MessageInfoService implements IMessageInfoService {

	@Resource
	private MessageInfoMapper messageInfoMapper;

	@Resource
	private ICustomerService iCustomerService;

	@Override
	public List<MessageInfoEntity> queryMessageInfo(
			MessageInfoVo messageInfoVo, UserEntity user) {
		// 分页查询条件
		if (messageInfoVo == null || messageInfoVo.getLimit() == 0) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		RowBounds rb = new RowBounds(messageInfoVo.getStart(),
				messageInfoVo.getLimit());
		// 查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("receiveUserId", user.getEmpEntity().getEmpCode());
		if (messageInfoVo.getMessageInfoEntity() != null) {
			if (!StringUtil.isEmpty(messageInfoVo.getMessageInfoEntity()
					.getMessageType())) {
				map.put("messageType", messageInfoVo.getMessageInfoEntity()
						.getMessageType());
			}
		}
		// 大类型
		if(!StringUtil.isEmpty(messageInfoVo.getBusType())){
			map.put("busType", messageInfoVo.getBusType());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		// 开始时间
		if (messageInfoVo.getStartDate() != null) {
			map.put("startDate", sdf.format(messageInfoVo.getStartDate()));
		}
		// 结束时间
		if (messageInfoVo.getEndDate() != null) {
			map.put("endDate", "" + sdf.format(messageInfoVo.getEndDate()));
		}
		// 排序方式
		if (StringUtil.isEmpty(messageInfoVo.getSortType())) {
			map.put("sortType", "DESC");
		} else {
			map.put("sortType", messageInfoVo.getSortType());
		}
		return messageInfoMapper.getMessageInfo(rb, map);
	}

	@Override
	public long countMessageInfo(MessageInfoVo messageInfoVo, UserEntity user) {
		// 查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("receiveUserId", user.getEmpEntity().getEmpCode());
		if (messageInfoVo.getMessageInfoEntity() != null) {
			if (!StringUtil.isEmpty(messageInfoVo.getMessageInfoEntity()
					.getMessageType())) {
				map.put("messageType", messageInfoVo.getMessageInfoEntity()
						.getMessageType());
			}
		}
		// 大类型
		if(!StringUtil.isEmpty(messageInfoVo.getBusType())){
			map.put("busType", messageInfoVo.getBusType());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		// 开始时间
		if (messageInfoVo.getStartDate() != null) {
			map.put("startDate", sdf.format(messageInfoVo.getStartDate()));
		}
		// 结束时间
		if (messageInfoVo.getEndDate() != null) {
			map.put("endDate", "" + sdf.format(messageInfoVo.getEndDate()));
		}
		return messageInfoMapper.countMessageInfo(map);
	}

	@Override
	public List<MessageInfoVo> countMessageType(UserEntity user) {
		// 查询条件
		Map<String, String> map = new HashMap<String, String>();
		map.put("receiveUserId", user.getEmpEntity().getEmpCode());
		return messageInfoMapper.countMessageType(map);
	}

	@Override
	public void addMessage(MessageInfoEntity messageEntity, UserEntity user) {
		try {
			// 条件判断
			if (messageEntity == null) {
				throw new MessageInfoException(
						MessageInfoException.MESSAGEINFO_NULL);
			}
			// 对应的客户
			CustomerEntity customerEntity = null;
			// 接收人
			if (StringUtil.isEmpty(messageEntity.getReceiveUserId())) {
				// 新增
				if (!StringUtil.isEmpty(messageEntity.getDcAccount())) {
					// 两边数据的匹配条件
					messageEntity.setCondition(messageEntity.getDcAccount());
					// 查询所属的客户，对应查出客户的负责人，此消息发给负责人
					customerEntity = iCustomerService
							.queryCustomerInfoByDcAcconut(messageEntity
									.getDcAccount());
					// 负责人工号
					if (customerEntity != null
							&& !StringUtil.isEmpty(customerEntity
									.getManageEmpCode())) {
						// 新增消息
						messageEntity.setCustomerEntity(customerEntity);
						messageEntity.setReceiveUserId(customerEntity
								.getManageEmpCode());
					} else {
						// 此客户没有负责人
						/*messageEntity
								.setIsSend(BseConstants.MESSAGE_STATUS_NO_CHIEF);*/
						return;
					}
				} else {
					// 没有DC账号，无法匹配客户与相应负责人
					/*messageEntity
							.setIsSend(BseConstants.MESSAGE_STATUS_NO_CONDITION);*/
					return;
				}
			}
			// 大类型
			if (!StringUtil.isEmpty(messageEntity.getMessageType())) {
				String mType = messageEntity.getMessageType();
				// 执行类
				if (mType.equals(BseConstants.MESSAGE_TYPE_BFZBL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_BFZQL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_KHLSL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_ZYKHTXL)) {
					messageEntity.setBusType(BseConstants.MESSAGE_BUSTYPE_ZXL);
					// 知晓类
				} else if (mType.equals(BseConstants.MESSAGE_TYPE_XTBFL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_XSHTL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_KHFHL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_HWYCL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_HWQSL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_KHTSL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_KHLPL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_KHJCL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_ZSBFL)
						|| mType.equals(BseConstants.MESSAGE_TYPE_SALEGZTX)) {
					messageEntity.setBusType(BseConstants.MESSAGE_BUSTYPE_ZXL2);
					// 营销类
				} else if(mType.equals(BseConstants.MESSAGE_TYPE_CRMYXHDTX)){
					messageEntity.setBusType(BseConstants.MESSAGE_BUSTYPE_YXL);
				}
				// 消息内容
				String message = null;
				if(customerEntity != null){
					if(mType.equals(BseConstants.MESSAGE_TYPE_KHFHL) || mType.equals(BseConstants.MESSAGE_TYPE_HWQSL)){
						message = "您的客户：" + customerEntity.getEnterpriseName();
						messageEntity.setMessageContent(message + messageEntity.getMessageContent());
					}
				}
			}
			// 主键
			messageEntity.setId(UUIDUtil.getUUID());
			// 阅读状态
			messageEntity.setIsRead(BseConstants.MESSAGE_NO_READ);
			// 消息主题
			if (StringUtil.isEmpty(messageEntity.getMessageTitle())) {
				messageEntity
						.setMessageTitle(getMessageTitle(messageEntity.getMessageType()));
			}
			// 消息状态，如果用户消息状态为空，则先判断此用户是否已经注册APP,如果没有则无需推送
			if (StringUtil.isEmpty(messageEntity.getIsSend())) {
				if (findEffectivePushUser(messageEntity.getReceiveUserId())) {
					messageEntity
							.setIsSend(BseConstants.MESSAGE_STATUS_NO_SEND);
				} else {
					if(messageEntity.getAllowSendTime() == null){
						// 用户未注册APP
						messageEntity
								.setIsSend(BseConstants.MESSAGE_STATUS_NO_REGISTER);
					} else {
						// 未发送，等待用户注册推送账号
						messageEntity
						.setIsSend(BseConstants.MESSAGE_STATUS_NO_SEND);
					}
				}
			}
			// 允许发送时间不为空，则创建时间为允许发送时间
			if(messageEntity.getAllowSendTime() != null){
				messageEntity.setCreateDate(messageEntity.getAllowSendTime());
			} else {
				messageEntity.setCreateDate(new Date());
			}
			// 创建人
			if(user == null){
				messageEntity.setCreateUser("sysadmin");
			} else {
				messageEntity.setCreateUser(user.getUserName());
			}
			// 新增
			messageInfoMapper.addMessage(messageEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMessageSendStatus(MessageInfoEntity messageEntity,
			UserEntity user) {
		if (messageEntity == null) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		// 条件判断
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", messageEntity.getId());
		map.put("msgId", messageEntity.getMsgId());
		map.put("sendTime", messageEntity.getSendTime());
		if (user == null) {
			map.put("modifyUser", "sysadmin");
		} else {
			map.put("modifyUser", user.getUserName());
		}
		map.put("isSend", "1");
		messageInfoMapper.updateMessageSendStatus(map);
	}

	@Override
	public void updateMessageReadStatus(List<String> ids, UserEntity user) {
		if (ids == null || ids.size() == 0) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		// 条件判断
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("modifyUser", user.getUserName());
		map.put("isRead", "1");
		messageInfoMapper.updateMessageReadStatus(map);
	}

	@Override
	public void deleteMessage(List<String> ids, UserEntity user) {
		if (ids == null || ids.size() == 0) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		// 条件判断
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("modifyUser", user.getUserName());
		map.put("active", "N");
		messageInfoMapper.deleteMessage(map);
	}
	
	@Override
	public void updateMessageByCondition(MessageInfoEntity messageEntity, UserEntity user) {
		messageEntity.setModifyUser(user.getUserName());
		// 判断接收人是否拥有推送账号
		if (!StringUtil.isEmpty(messageEntity.getReceiveUserId())) {
			if (findEffectivePushUser(messageEntity.getReceiveUserId())) {
				messageEntity
						.setIsSend(BseConstants.MESSAGE_STATUS_NO_SEND);
			} else {
				if(messageEntity.getAllowSendTime() == null){
					// 用户未注册APP
					messageEntity
							.setIsSend(BseConstants.MESSAGE_STATUS_NO_REGISTER);
				} else {
					// 未发送，等待用户注册推送账号
					messageEntity
					.setIsSend(BseConstants.MESSAGE_STATUS_NO_SEND);
				}
			}
		}
		messageInfoMapper.updateMessageByCondition(messageEntity);
	}

	/**
	 * 判断用户是否有有效账号
	 * 
	 * @param pusUserhVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	public boolean findEffectivePushUser(String empCode) {
		if (StringUtil.isEmpty(empCode)) {
			throw new UserAppException(
					UserAppException.BAIDU_PUSHUSERINIFO_NULL);
		}
		// 查询条件
		PushUserEntity searchEntity = new PushUserEntity();
		searchEntity.setEmpCode(empCode);
		searchEntity.setCancel(0);
		// 查询
		long result = messageInfoMapper
				.findPushUserByEmpCode(searchEntity);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取消息标题
	 * 
	 * @param messageType
	 * @return
	 * @author 蒋落琛
	 * @date 2015-8-17
	 * @update
	 */
	private String getMessageTitle(String messageType){
		String messageTitle = null;
		if(messageType.equals(BseConstants.MESSAGE_TYPE_BFZBL)){
			messageTitle = "拜访指标类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_BFZQL)){
			messageTitle = "拜访周期类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_XTBFL)){
			messageTitle = "协同拜访类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_XSHTL)){
			messageTitle = "销售合同类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_KHFHL)){
			messageTitle = "客户发货类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_HWYCL)){
			messageTitle = "货物异常类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_HWQSL)){
			messageTitle = "货物签收类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_KHTSL)){
			messageTitle = "客户投诉类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_KHLPL)){
			messageTitle = "客户理赔类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_KHJCL)){
			messageTitle = "客户减产类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_KHLSL)){
			messageTitle = "客户流失类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_ZYKHTXL)){
			messageTitle = "资源客户提醒类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_ZSBFL)){
			messageTitle = "赴约提醒类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_CRMYXHDTX)){
			messageTitle = "营销活动提醒类";
		} else if(messageType.equals(BseConstants.MESSAGE_TYPE_SALEGZTX)){
			messageTitle = "工作提醒类";
		} else {
			messageTitle = "CRM系统消息";
		}
		return messageTitle;
	}
}
