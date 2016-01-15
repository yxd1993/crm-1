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
public class SmsConfig {
	
	private static Properties pro = loadConfig();

	public static final String LOGIN_NAME = pro.getProperty("LOGIN_NAME");
	public static final String LOGIN_PWD = pro.getProperty("LOGIN_PWD");
	public static final String OPKIND = pro.getProperty("OPKIND");
	public static final String INTERFACEID = pro.getProperty("INTERFACEID");
	public static final String SERTYPE = pro.getProperty("SERTYPE");
	public static final String URL = pro.getProperty("URL");

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
