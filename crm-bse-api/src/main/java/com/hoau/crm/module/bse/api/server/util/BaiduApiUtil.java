package com.hoau.crm.module.bse.api.server.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;


/**
 * 调用百度服务器工具类
 *
 * @author 蒋落琛
 * @date 2015-10-27
 */
public class BaiduApiUtil {
	
	private static Logger LOG =LoggerFactory.getLogger(BaiduApiUtil.class);

	/**
	 * 百度接口
	 */
	public static String BASE_URL = "http://api.map.baidu.com/geocoder/v2/?";

	/**
	 * 要所经纬度获取地址信息
	 * 
	 * @param lon 经度
	 * @param lat 纬度
	 * @return
	 * @author 蒋落琛
	 * @date 2015-10-27
	 * @update
	 */
	public static String getAddressByLL(String lon,String lat){
		String address = null;
		try {
			// 取得静态参数配置
			Properties pro = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties");
			String rs = BASE_URL + "ak=" + pro.getProperty("baidumap.browser.snkey") + "&location="+lat+","+lon+"&output=json&pois=0";
			// 创建接口调用
			RestTemplate restTemplate=new RestTemplate();
			rs = restTemplate.getForObject(rs, String.class);
			LOG.info("getAddressByLL result : " + rs);
			// 解析返回值
			JSONObject jsonObject = JSON.parseObject(rs);
			// 0表示正常
		    if(jsonObject.get("status").toString().equals("0")){
	        	address = jsonObject.getJSONObject("result").get("formatted_address").toString();
	        }
		    LOG.info("getAddressByLL address : " + address);
		} catch (FileNotFoundException e) {
			LOG.info(e.getMessage());
		} catch (IOException e) {
			LOG.info(e.getMessage());
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		return address;
	}
	
	/*public static String getAddressByLL(String lon,String lat){
		String address = null;
		// 取得静态参数配置
		RestTemplate restTemplate=new RestTemplate();
		try {
			String rs = BASE_URL + "ak=" + "GKpF5kBmwGGcVRGgBzUe5AvE" + "&location="+lat+","+lon+"&output=json&pois=0";
			System.out.println(rs);
			rs = restTemplate.getForObject(rs, String.class);
			LOG.info("getAddressByLL result : " + rs);
			JSONObject jsonObject = JSON.parseObject(rs);
			//0表示正常
		    if(jsonObject.get("status").toString().equals("0")){
	        	address = jsonObject.getJSONObject("result").get("formatted_address").toString();
	        }
		    System.out.println(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}*/
	
	/*public static void main(String[] args) {
		BaiduApiUtil.getAddressByLL("104.16403","30.65908");
	}*/
}
