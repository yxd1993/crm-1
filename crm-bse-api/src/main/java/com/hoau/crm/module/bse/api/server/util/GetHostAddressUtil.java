package com.hoau.crm.module.bse.api.server.util;

import javax.servlet.http.HttpServletRequest;

import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 获取IP工具类
 * 
 * @author: 何斌
 * @create: 2015年6月10日 上午9:36:18
 */
public class GetHostAddressUtil {
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtil.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
