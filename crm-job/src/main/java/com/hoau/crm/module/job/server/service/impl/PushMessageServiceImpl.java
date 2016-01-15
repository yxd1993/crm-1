package com.hoau.crm.module.job.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoRepeatEntity;
import com.hoau.crm.module.appcore.api.shared.domain.PushUserEntity;
import com.hoau.crm.module.appcore.api.shared.exception.MessageInfoException;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.bse.api.server.service.IWayBillService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.WayBillEntity;
import com.hoau.crm.module.bse.server.dao.WayBillMapper;
import com.hoau.crm.module.common.server.util.PushUtil;
import com.hoau.crm.module.common.shared.domain.PushAPS;
import com.hoau.crm.module.common.shared.domain.PushMessageAndroidEntity;
import com.hoau.crm.module.common.shared.domain.PushMessageIOSEntity;
import com.hoau.crm.module.job.server.dao.PushMessageJobMapper;
import com.hoau.crm.module.job.server.service.IPushMessageService;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.JsonUtils;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 消息发送SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-7-1
 */
@org.springframework.stereotype.Service
public class PushMessageServiceImpl implements IPushMessageService {

	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory
			.getLogger(PushMessageServiceImpl.class);

	@Resource
	private PushMessageJobMapper pushMessageJobMapper;

	@Resource
	private WayBillMapper wayBillMapper;

	@Resource
	private IMessageInfoService iMessageInfoService;

	@Resource
	private IWayBillService iWayBillService;

	@Override
	public void synMessage(int synNum) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		// 消息同步的条数
		RowBounds rb = new RowBounds(0, synNum);
		// 运单消息
		List<WayBillEntity> waybillList = wayBillMapper
				.getNotCreateMessageWayBill(rb);
		List<String> bills = new ArrayList<String>();
		if (waybillList != null && waybillList.size() > 0) {
			LOG.info("synMessage start : " + sDateFormat.format(new Date())
					+ ", dataSize : " + waybillList.size());
			for (int i = 0; i < waybillList.size(); i++) {
				try {
					WayBillEntity waybillEntity = waybillList.get(i);
					// 运单号
					bills.add(waybillEntity.getBillNum());
					// 新增消息
					MessageInfoEntity messageEntity = new MessageInfoEntity();
					messageEntity
							.setMessageType(BseConstants.MESSAGE_TYPE_KHFHL);
					messageEntity.setDcAccount(waybillEntity.getDcAccount());
					messageEntity.setMessageContent(convertMessageContent(waybillEntity, BseConstants.MESSAGE_TYPE_KHFHL));
					iMessageInfoService.addMessage(messageEntity, null);
					// 新增发货日志
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", UUIDUtil.getUUID());
					map.put("dcAccount", waybillEntity.getDcAccount());
					map.put("waybillLog", convertMessageContent(waybillEntity, "WAYBILL_LOG"));
					if (iWayBillService.getWayBillLogByDcAccount(waybillEntity
							.getDcAccount()) == 0) {
						iWayBillService.addWayBillLog(map);
					} else {
						iWayBillService.updateWayBillLog(map);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 将这些运单改成已生成消息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bills", bills);
			wayBillMapper.updateWayBillIsMessage(map);
		}
		// 签收消息
		List<String> signNos = new ArrayList<String>();
		List<WayBillEntity> signList = wayBillMapper
				.getNotCreateMessageSign(rb);
		if (signList != null && signList.size() > 0) {
			LOG.info("synMessage start : " + sDateFormat.format(new Date())
					+ ", dataSize : " + signList.size());
			for (int i = 0; i < signList.size(); i++) {
				try {
					WayBillEntity waybillEntity = signList.get(i);
					// 签单号
					signNos.add(waybillEntity.getSignNo());
					MessageInfoEntity messageEntity = new MessageInfoEntity();
					messageEntity
							.setMessageType(BseConstants.MESSAGE_TYPE_HWQSL);
					messageEntity.setDcAccount(waybillEntity.getDcAccount());
					messageEntity.setMessageContent(convertMessageContent(
							waybillEntity, BseConstants.MESSAGE_TYPE_HWQSL));
					iMessageInfoService.addMessage(messageEntity, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 将这些运单改成已生成消息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("signNos", signNos);
			wayBillMapper.updateSignIsMessage(map);
		}
		LOG.info("synMessage start : " + sDateFormat.format(new Date()));
	}

	@Override
	public void pushMessageJob(int pushNum) {
		// 查询消息数据
		List<MessageInfoEntity> messageList = pushMessageJobMapper
				.queryMessageInfo(pushNum);
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		LOG.info("messageJob start : " + sDateFormat.format(new Date())
				+ ", dataSize : " + messageList.size());
		// 循环发送
		// 发送成功的记录ID
		for (int i = 0; i < messageList.size(); i++) {
			MessageInfoEntity messEntity = messageList.get(i);
			List<PushUserEntity> puEntityList = messEntity.getPushUserEntityList();
			// 发送状态 
			String sendStatus = "";
			// 单播
			if(messEntity.getPushType() == null || messEntity.getPushType().equals(BseConstants.MESSAGE_PUSH_TYPE_SINGLE)) {
				// 默认用户未注册APP
				sendStatus = BseConstants.MESSAGE_STATUS_NO_REGISTER;
				for(int j=0; j<puEntityList.size(); j++){
					PushUserEntity puEntity = puEntityList.get(j);
					// 先判断是什么系统账号
					if (!StringUtil.isEmpty(puEntity.getAppid()) && puEntity.getCancel() == 0) {
						// ANDROID
						PushMsgToSingleDeviceResponse response = null;
						try {
							// ANDROID
							if (puEntity.getAppid().equals(AppUtil.ANDROID)) {
								// 组装参数
								PushMessageAndroidEntity pe = new PushMessageAndroidEntity();
								pe.setTitle(messEntity.getMessageTitle());
								if(messEntity.getMessageContent().length() > 60){
									pe.setDescription(messEntity.getMessageContent().substring(0, 60));
								} else {
									pe.setDescription(messEntity.getMessageContent());
								}
								// pe.setCustom_content(mvo);
								pe.setNotification_basic_style(puEntity
										.getNotification_basic_style());
								pe.setNotification_builder_id(puEntity
										.getNotification_builder_id());
								String msg = JsonUtils.toJson(pe);
								// 发送
								response = PushUtil.pushMsgToSingleAndroidDevice(1,
										msg, puEntity.getChannelid());
								// IOS
							} else if (puEntity.getAppid().equals(AppUtil.IOS)) {
								PushAPS aps = new PushAPS();
								if(messEntity.getMessageContent().length() > 60){
									aps.setAlert(messEntity.getMessageContent().substring(0, 60));
								} else {
									aps.setAlert(messEntity.getMessageContent());
								}
								aps.setSound(puEntity.getSound());
								aps.setBadge(puEntity.getBadge());
								PushMessageIOSEntity iosEntity = new PushMessageIOSEntity();
								iosEntity.setAps(aps);
								String msg = JsonUtils.toJson(iosEntity);
								// 发送
								response = PushUtil.pushMsgToSingleIosDevice(1, msg,
										puEntity.getChannelid());
								// 新版本发送
								if(response == null){
									response = PushUtil.pushMsgToSingleIosDeviceByNew(1, msg,
											puEntity.getChannelid());
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						// 返回值处理
						if (response != null) {
							// 状态为默认
							if(sendStatus.equals(BseConstants.MESSAGE_STATUS_NO_REGISTER)){
								sendStatus = BseConstants.MESSAGE_STATUS_IS_SEND;
								// 发送失败状态
							} else if(sendStatus.equals(BseConstants.MESSAGE_STATUS_SEND_FAILURE)){
								sendStatus = BseConstants.MESSAGE_STATUS_SEND_SUCCESSANDFAILURE;
							}
						} else {
							// 状态为默认
							if(sendStatus.equals(BseConstants.MESSAGE_STATUS_NO_REGISTER)){
								sendStatus = BseConstants.MESSAGE_STATUS_SEND_FAILURE;
								// 发送失败状态
							} else if(sendStatus.equals(BseConstants.MESSAGE_STATUS_IS_SEND)){
								sendStatus = BseConstants.MESSAGE_STATUS_SEND_SUCCESSANDFAILURE;
							}
							// 新增到发送失败表
							MessageInfoRepeatEntity en = new MessageInfoRepeatEntity();
							en.setPushuserId(puEntity.getId());
							en.setMessageId(messEntity.getId());
							pushMessageJobMapper.insertSendFailureMessage(en);
						}
					}
				}
				// ANDROID广播
			} else if(BseConstants.MESSAGE_PUSH_TYPE_ANDROID_All.equals(messEntity.getPushType())){
				try {
					// ANDROID
					PushMsgToAllResponse response = null;
					// 默认未发送
					sendStatus = BseConstants.MESSAGE_STATUS_IS_SEND;
					// 组装参数
					PushMessageAndroidEntity pe = new PushMessageAndroidEntity();
					pe.setTitle(messEntity.getMessageTitle());
					if(messEntity.getMessageContent().length() > 60){
						pe.setDescription(messEntity.getMessageContent().substring(0, 60));
					} else {
						pe.setDescription(messEntity.getMessageContent());
					}
					// pe.setCustom_content(mvo);
					/*pe.setNotification_basic_style(puEntity
							.getNotification_basic_style());
					pe.setNotification_builder_id(puEntity
							.getNotification_builder_id());*/
					String msg = JsonUtils.toJson(pe);
					// 发送
					response = PushUtil.pushMsgToAllAndroidDevice(msg);
					// 返回值处理
					if (response != null) {
						// 发送成功
						sendStatus = BseConstants.MESSAGE_STATUS_IS_SEND;
					} else {
						// 发送失败
						sendStatus = BseConstants.MESSAGE_STATUS_SEND_FAILURE;
						// 新增到发送失败表
						MessageInfoRepeatEntity en = new MessageInfoRepeatEntity();
						en.setMessageId(messEntity.getId());
						pushMessageJobMapper.insertSendFailureMessage(en);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// IOS广播
			} else if(BseConstants.MESSAGE_PUSH_TYPE_IOS_All.equals(messEntity.getPushType())){
				try {
					// IOS
					PushAPS aps = new PushAPS();
					if(messEntity.getMessageContent().length() > 60){
						aps.setAlert(messEntity.getMessageContent().substring(0, 60));
					} else {
						aps.setAlert(messEntity.getMessageContent());
					}
					/*aps.setSound(puEntity.getSound());
					aps.setBadge(puEntity.getBadge());*/
					PushMessageIOSEntity iosEntity = new PushMessageIOSEntity();
					iosEntity.setAps(aps);
					String iosMsg = JsonUtils.toJson(iosEntity);
					PushMsgToAllResponse iosResponse = null;
					// 发送
					iosResponse = PushUtil.pushMsgToAllIosDevice(iosMsg);
					// 新版本发送
					if(iosResponse == null){
						iosResponse = PushUtil.pushMsgToAllIosDeviceByNew(iosMsg);
					}
					// 返回值处理
					if (iosResponse != null) {
						// 发送成功
						sendStatus = BseConstants.MESSAGE_STATUS_IS_SEND;
					} else {
						// 发送失败
						sendStatus = BseConstants.MESSAGE_STATUS_SEND_FAILURE;
						// 新增到发送失败表
						MessageInfoRepeatEntity en = new MessageInfoRepeatEntity();
						en.setMessageId(messEntity.getId());
						pushMessageJobMapper.insertSendFailureMessage(en);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 修改发送状态
			MessageInfoEntity messageEntity = new MessageInfoEntity();
			messageEntity.setId(messEntity.getId());
			//messageEntity.setMsgId(response.getMsgId());
			messageEntity.setSendTime(new Date());
			messageEntity.setIsSend(sendStatus);
			updateMessageSendStatus(messageEntity);
		}
		LOG.info("messageJob end : " + sDateFormat.format(new Date()) + "");
	}
	
	@Override
	public void pushMessageRepeatJob(int pushNum) {
		// 查询消息数据
		List<MessageInfoRepeatEntity> messageList = pushMessageJobMapper
				.queryRepeatMessageInfo(new RowBounds(0, pushNum));
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		LOG.info("pushMessageRepeatJob start : " + sDateFormat.format(new Date())
				+ ", dataSize : " + messageList.size());
		// 循环发送
		List<String> successIds = new ArrayList<String>();
		List<String> failureIds = new ArrayList<String>(); 
		// 发送成功的记录ID
		for (int i = 0; i < messageList.size(); i++) {
			MessageInfoRepeatEntity re = messageList.get(i);
			MessageInfoEntity messEntity = re.getMessageInfoEntity();
			// 先判断是什么系统账号
			try {
				// 单播
				if(messEntity.getPushType() == null || messEntity.getPushType().equals(BseConstants.MESSAGE_PUSH_TYPE_SINGLE)) {
					// 推送账号信息
					PushUserEntity puEntity = re.getPushUserEntity();
					// ANDROID
					PushMsgToSingleDeviceResponse response = null;
					// 先判断是什么系统账号
					if (!StringUtil.isEmpty(puEntity.getAppid()) && puEntity.getCancel() == 0) {
						try {
							// ANDROID
							if (puEntity.getAppid().equals(AppUtil.ANDROID)) {
								// 组装参数
								PushMessageAndroidEntity pe = new PushMessageAndroidEntity();
								pe.setTitle(messEntity.getMessageTitle());
								if(messEntity.getMessageContent().length() > 60){
									pe.setDescription(messEntity.getMessageContent().substring(0, 60));
								} else {
									pe.setDescription(messEntity.getMessageContent());
								}
								// pe.setCustom_content(mvo);
								pe.setNotification_basic_style(puEntity
										.getNotification_basic_style());
								pe.setNotification_builder_id(puEntity
										.getNotification_builder_id());
								String msg = JsonUtils.toJson(pe);
								// 发送
								response = PushUtil.pushMsgToSingleAndroidDevice(1,
										msg, puEntity.getChannelid());
								// 返回值处理
								if (response != null) {
									successIds.add(re.getId());
								} else {
									failureIds.add(re.getId());
								}
								// IOS
							} else if (puEntity.getAppid().equals(AppUtil.IOS)) {
								PushAPS aps = new PushAPS();
								if(messEntity.getMessageContent().length() > 60){
									aps.setAlert(messEntity.getMessageContent().substring(0, 60));
								} else {
									aps.setAlert(messEntity.getMessageContent());
								}
								aps.setSound(puEntity.getSound());
								aps.setBadge(puEntity.getBadge());
								PushMessageIOSEntity iosEntity = new PushMessageIOSEntity();
								iosEntity.setAps(aps);
								String msg = JsonUtils.toJson(iosEntity);
								// 发送
								response = PushUtil.pushMsgToSingleIosDevice(1, msg,
										puEntity.getChannelid());
								// 新版本发送
								if(response == null){
									response = PushUtil.pushMsgToSingleIosDeviceByNew(1, msg,
											puEntity.getChannelid());
								}
								// 返回值处理
								if (response != null) {
									successIds.add(re.getId());
								} else {
									failureIds.add(re.getId());
								}
							} else {
								successIds.add(re.getId());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						successIds.add(re.getId());
					}
					// ANDROID广播
				} else if(BseConstants.MESSAGE_PUSH_TYPE_ANDROID_All.equals(messEntity.getPushType())){
					try {
						// ANDROID
						PushMsgToAllResponse response = null;
						// 组装参数
						PushMessageAndroidEntity pe = new PushMessageAndroidEntity();
						pe.setTitle(messEntity.getMessageTitle());
						if(messEntity.getMessageContent().length() > 60){
							pe.setDescription(messEntity.getMessageContent().substring(0, 60));
						} else {
							pe.setDescription(messEntity.getMessageContent());
						}
						// pe.setCustom_content(mvo);
						/*pe.setNotification_basic_style(puEntity
								.getNotification_basic_style());
						pe.setNotification_builder_id(puEntity
								.getNotification_builder_id());*/
						String msg = JsonUtils.toJson(pe);
						// 发送
						response = PushUtil.pushMsgToAllAndroidDevice(msg);
						// 返回值处理
						if (response != null) {
							successIds.add(re.getId());
						} else {
							failureIds.add(re.getId());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// IOS广播
				} else if(BseConstants.MESSAGE_PUSH_TYPE_IOS_All.equals(messEntity.getPushType())){
					try {
						// IOS
						PushAPS aps = new PushAPS();
						if(messEntity.getMessageContent().length() > 60){
							aps.setAlert(messEntity.getMessageContent().substring(0, 60));
						} else {
							aps.setAlert(messEntity.getMessageContent());
						}
						/*aps.setSound(puEntity.getSound());
						aps.setBadge(puEntity.getBadge());*/
						PushMessageIOSEntity iosEntity = new PushMessageIOSEntity();
						iosEntity.setAps(aps);
						String iosMsg = JsonUtils.toJson(iosEntity);
						PushMsgToAllResponse iosResponse = null;
						// 发送
						iosResponse = PushUtil.pushMsgToAllIosDevice(iosMsg);
						// 新版本发送
						if(iosResponse == null){
							iosResponse = PushUtil.pushMsgToAllIosDeviceByNew(iosMsg);
						}
						// 返回值处理
						if (iosResponse != null) {
							successIds.add(re.getId());
						} else {
							failureIds.add(re.getId());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					successIds.add(re.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
				successIds.add(re.getId());
			}
		}
		// 更新重发消息表
		if(successIds.size() > 0){
			pushMessageJobMapper.updateMessageSuccess(successIds);
		}
		if(failureIds.size() > 0){
			pushMessageJobMapper.updateMessageRepeatNum(failureIds);
		}
		LOG.info("pushMessageRepeatJob end : " + sDateFormat.format(new Date()) + "");
	}

	/**
	 * 修改消息状态
	 * 
	 * @param messageEntity
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	private void updateMessageSendStatus(MessageInfoEntity messageEntity) {
		if (messageEntity == null) {
			throw new MessageInfoException(
					MessageInfoException.MESSAGEINFO_NULL);
		}
		// 条件判断
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", messageEntity.getId());
		map.put("msgId", messageEntity.getMsgId());
		map.put("sendTime", messageEntity.getSendTime());
		map.put("modifyUser", "sysadmin");
		if(StringUtil.isEmpty(messageEntity.getIsSend())){
			map.put("isSend", "1");
		} else {
			map.put("isSend", messageEntity.getIsSend());
		}
		pushMessageJobMapper.updateMessageSendStatus(map);
	}

	/**
	 * 转换消息内容
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-28
	 * @update
	 */
	private String convertMessageContent(WayBillEntity waybillEntity,
			String messageType) {
		String message = "";
		// 客户发货类
		if (messageType.equals(BseConstants.MESSAGE_TYPE_KHFHL)) {
			message = "于" + waybillEntity.getBillingDate() + "开了一单从"
					+ waybillEntity.getStartingPoint() + "发往"
					+ waybillEntity.getDestination() + "的货物，开单金额为"
					+ waybillEntity.getBillingAmount() + "元，请做好货物跟踪工作。";
			// 货物签收类
		} else if (messageType.equals(BseConstants.MESSAGE_TYPE_HWQSL)) {
			message = "的一票运单号为" + waybillEntity.getBillNum() + "开单金额为"
					+ waybillEntity.getBillingAmount() + "元的货物已被"
					+ (waybillEntity.getSignStatus().equals("0") ? "正常" : "异常")
					+ "签收。";
			// 开单日志
		} else if(messageType.equals("WAYBILL_LOG")){
			message = "运单编号：" + waybillEntity.getBillNum() + "，运输类型：" + BseConstants.convertTransportType(waybillEntity.getTransportType()) + 
					"，开单时间：" + waybillEntity.getBillingDate() + "，开单金额：" + waybillEntity.getBillingAmount() + "。";
		}
		return message;
	}
}
