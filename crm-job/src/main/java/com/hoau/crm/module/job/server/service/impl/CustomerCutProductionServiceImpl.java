package com.hoau.crm.module.job.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.job.server.dao.CustomerOutputMapper;
import com.hoau.crm.module.job.server.service.ICustomerCutProductionService;

/**
 * 客户减产更新
 * 
 * @author 蒋落琛
 * @date 2015-7-8
 */
@org.springframework.stereotype.Service
public class CustomerCutProductionServiceImpl implements ICustomerCutProductionService {

	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory
			.getLogger(CustomerCutProductionServiceImpl.class);

	@Resource
	private CustomerOutputMapper customerOutputMapper;

	@Resource
	private IMessageInfoService iMessageInfoService;

	@Override
	public void refreshCutProductionInfo() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		LOG.info("refreshCutProductionInfo start : "
				+ sDateFormat.format(new Date()) + "");
		try {
			// 当前时间
			Date currDate = new Date();
			// 获取昨天的年月
			Calendar cal = Calendar.getInstance();
			cal.setTime(currDate);
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
			// 客户减产类
			Map<String, Object> dateMap = new HashMap<String, Object>();
			List<Map<String, String>> beforeMonth = new ArrayList<Map<String, String>>();
			// 上月
			Map<String, String> m1 = new HashMap<String, String>();
			m1.put("year", String.valueOf(cal.get(Calendar.YEAR)));
			m1.put("month", String.valueOf(cal.get(Calendar.MONTH)  + 1));
			// 上月
			dateMap.put("year", String.valueOf(cal.get(Calendar.YEAR)));
			dateMap.put("month", String.valueOf(cal.get(Calendar.MONTH) + 1));
			Map<String, String> m2 = new HashMap<String, String>();
			// 上上月
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
			m2.put("year", String.valueOf(cal.get(Calendar.YEAR)));
			m2.put("month", String.valueOf(cal.get(Calendar.MONTH) + 1));
			Map<String, String> m3 = new HashMap<String, String>();
			// 上上上月
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
			m3.put("year", String.valueOf(cal.get(Calendar.YEAR)));
			m3.put("month", String.valueOf(cal.get(Calendar.MONTH) + 1));
			beforeMonth.add(m1);
			beforeMonth.add(m2);
			beforeMonth.add(m3);
			dateMap.put("beforeMonth", beforeMonth);
			// 查询减产客户信息
			List<Map<String, String>> list = customerOutputMapper
					.getCutProductionCustomer(dateMap);
			// 新增消息
			cal.setTime(currDate);
			cal.set(Calendar.HOUR_OF_DAY, 9);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MINUTE, 0);
			Date allowSendDate = cal.getTime();
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> customerInfo = list.get(i);
				String enterpriseName = customerInfo.get("enterpriseName");
				String manageEmpCode = customerInfo.get("manageEmpCode");
				String dCaccount = customerInfo.get("dCaccount");
				// 新增消息
				MessageInfoEntity messageEntity = new MessageInfoEntity();
				messageEntity.setMessageType(BseConstants.MESSAGE_TYPE_KHJCL);
				messageEntity.setReceiveUserId(manageEmpCode);
				messageEntity.setCondition(dCaccount);
				messageEntity.setAllowSendTime(allowSendDate);
				messageEntity.setIsSend(BseConstants.MESSAGE_STATUS_IS_SEND);
				messageEntity.setMessageContent("您的客户：" + enterpriseName
						+ "，上月发货产值低于前三个月平均产值，请及时跟进。");
				iMessageInfoService.addMessage(messageEntity, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("refreshCutProductionInfo end : "
				+ sDateFormat.format(new Date()) + "");
	}
}
