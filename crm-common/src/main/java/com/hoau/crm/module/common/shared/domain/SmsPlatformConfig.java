package com.hoau.crm.module.common.shared.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * 短信发送配置信息
 *
 * @author 蒋落琛
 * @date 2015-12-8
 */
public class SmsPlatformConfig {
	
	private static Properties pro = loadConfig();

	public static final String SMS_PLATFORM_URL = pro.getProperty("SMS_PLATFORM_URL");
	public static final String SMS_PLATFORM_SYSTEMNAME = pro.getProperty("SMS_PLATFORM_SYSTEMNAME");
	public static final String SMS_PLATFORM_BUSTYPE = pro.getProperty("SMS_PLATFORM_BUSTYPE");

	private static Properties loadConfig() {
		Properties pro;
		try {
			pro = ConfigFileLoadUtil
					.getPropertiesForClasspath("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			pro = new Properties();
		} catch (IOException e) {
			e.printStackTrace();
			pro = new Properties();
		}
		return pro;
	}
}
