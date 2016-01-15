package com.hoau.crm.module.customer.server.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.hoau.crm.module.bse.api.server.service.IPhoneInfoService;
import com.hoau.crm.module.bse.api.shared.domain.PhoneInfoEntity;
import com.hoau.crm.module.bse.api.shared.exception.PhoneInfoException;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class PhoneInfoService implements IPhoneInfoService {

	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(PhoneInfoService.class);

	@Override
	public PhoneInfoEntity queryPhoneInfoByPhone(PhoneInfoEntity phoneInfoEntity) {
		RestTemplate restTemplate = new RestTemplate();
		PhoneInfoEntity phoneInfo = null;
		Properties pro;
		try {
			if(phoneInfoEntity == null || StringUtil.isEmpty(phoneInfoEntity.getMobile())){
				throw new PhoneInfoException(PhoneInfoException.PHONE_NULL);
			}
			// 地址信息
			pro = ConfigFileLoadUtil
					.getPropertiesForClasspath("config.properties");
			// 网站地址加参数手机号
			String url = pro.getProperty("phoneinfo.url") + phoneInfoEntity.getMobile();
			LOG.info("phoneinfo.url : " + pro);
			String res = restTemplate.getForObject(url, String.class);
			if (!StringUtil.isEmpty(res)) {
				phoneInfo = JSON.parseObject(res, PhoneInfoEntity.class);
				LOG.info("queryPhoneInfo : " + res);
			}
			// 将返回的省市组装在一起形成地址
			if(phoneInfo != null && !StringUtil.isEmpty(phoneInfo.getProvince())){
				String province = phoneInfo.getProvince();
				String city = phoneInfo.getCity();
				String address = "";
				if(province.equals("北京") || province.equals("天津") || province.equals("上海") || province.equals("重庆")){
					address = city + "市";
				}else if(province.equals("内蒙古")){
					address = "内蒙古自治区" + city + "市";
				}else if(province.equals("新疆")){
					address = "新疆维吾尔自治区" + city + "市";
				}else if(province.equals("广西")){
					address = "广西壮族自治区" + city + "市";
				}else if(province.equals("宁夏")){
					address = "宁夏回族自治区" + city + "市";
				}else if(province.equals("西藏")){
					address = "西藏自治区" + city + "市";
				}else{
					address = province + "省" + city + "市";
				}
				phoneInfo.setAddress(address);
			}
		} catch (FileNotFoundException e) {
			LOG.info(e.getMessage());
		} catch (IOException e) {
			LOG.info(e.getMessage());
		} catch (Exception e){
			LOG.info(e.getMessage());
		}
		return phoneInfo;
	}
	
}
