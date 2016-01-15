package com.hoau.crm.module.common.server.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.hoau.crm.module.bse.api.shared.domain.PhoneInfoEntity;
import com.hoau.crm.module.common.shared.domain.SmsEntity;
import com.hoau.crm.module.common.shared.domain.SmsPlatformConfig;
import com.hoau.crm.module.common.shared.domain.SmsPlatformEntity;
import com.hoau.crm.module.common.shared.domain.SmsPlatformVo;
import com.hoau.hbdp.framework.shared.util.JsonUtils;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 短信平台发送
 * 
 * @author 蒋落琛
 * @date 2015-12-17
 */
@Component
public class SMSPlatformUtil {

	private static Log log = LogFactory.getLog(SMSPlatformUtil.class);

	/**
	 * @Title: sendMsg
	 * @Description: 发送短信
	 * @param @param smsEntity 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void sendMsg(SmsEntity smsEntity) {
		// 系统信息
		SmsPlatformVo smsinfo = new SmsPlatformVo();
		smsinfo.setSystemName(SmsPlatformConfig.SMS_PLATFORM_SYSTEMNAME);
		smsinfo.setBusinessType(SmsPlatformConfig.SMS_PLATFORM_BUSTYPE);
		// 短信信息
		SmsPlatformEntity smsen = new SmsPlatformEntity();
		smsen.setContent(smsEntity.getContent());
		smsen.setMobile(smsEntity.getMobile());
		List<SmsPlatformEntity> smsenList = new ArrayList<SmsPlatformEntity>();
		smsenList.add(smsen);
		smsinfo.setRequestContentList(smsenList);
		String sms = JsonUtils.toJson(smsinfo);
		// 短信发送
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(sms, headers);
		String result = restTemplate.postForObject(SmsPlatformConfig.SMS_PLATFORM_URL, formEntity, String.class);
		if (!StringUtil.isEmpty(result)) {
			log.info("短信发送成功 : " + result);
		}
	}
}
