/**
 * 
 */
package com.hoau.crm.module.job.server.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.hoau.crm.module.bse.server.dao.ServerStatusMapper;
import com.hoau.crm.module.bse.shared.domain.ServerStatusEntity;
import com.hoau.crm.module.job.server.service.IServerStatusJobService;
import com.hoau.crm.module.util.UUIDUtil;

/**
 * 获取服务器状态调度任务service
 * @author 丁勇
 * @date 2015年8月12日
 */
@org.springframework.stereotype.Service
public class ServerStatusJobService implements IServerStatusJobService {
	@Resource
	private ServerStatusMapper ssm;

	private static Logger LOG = LoggerFactory
			.getLogger(ServerStatusJobService.class);

	@Override
	@Transactional
	public void synStatusService(String url) {
		// 保存对象
		ServerStatusEntity sse = new ServerStatusEntity();
		RestTemplate restTemplate = new RestTemplate();
		String uid = UUIDUtil.getUUID();
		if(!StringUtils.isEmpty(url)){
			sse.setId(uid);
			// 获取状态结果
			String res = restTemplate.getForObject(url, String.class);
			LOG.info("服务器状态结果 : " + res);
			if (StringUtils.isEmpty(res)) {
				LOG.info("服务器无响应信息 ");
				return;
			}
			// 状态结果字符串,分割
			String[] statusString = res.split("\n");
			// 得到活跃的连接数量
			sse.setActiveConnections(statusString[0].substring(
					statusString[0].indexOf(" ", statusString[0].indexOf(":")) + 1,
					statusString[0].lastIndexOf(" ")));
			// 服务器处理结果分割
			String[] handleds = statusString[2].split(" ");
			// 连接数,握手数,请求数
			sse.setHandledConnections(handleds[1]);
			sse.setHandledSuccess(handleds[2]);
			sse.setHandledRequest(handleds[3]);
			// 客户端连接情况结果
			String[] keeps = statusString[3].split(" ");
			// 客户端的连接数,响应数据到客户端的数量,请求指令的驻留连接.
			sse.setReading(keeps[1]);
			sse.setWriting(keeps[3]);
			sse.setWaiting(keeps[5]);
			ssm.insertServerLog(sse);
			LOG.info("获取成功");
		}else{
			LOG.info("获取url失败");
		}
		
	}
	//测试main
	public static void main(String[] args) {
		// 保存对象
		ServerStatusEntity sse = new ServerStatusEntity();
		RestTemplate restTemplate = new RestTemplate();
		// 获取状态结果
		String res = restTemplate.getForObject(
				"http://crm.hoau.net/nginxstatus", String.class);
		// 状态结果字符串,分割
		String[] statusString = res.split("\n");
		// 得到活跃的连接数量
		sse.setActiveConnections(statusString[0].substring(
				statusString[0].indexOf(" ", statusString[0].indexOf(":")) + 1,
				statusString[0].lastIndexOf(" ")));
		// 服务器处理结果分割
		String[] handleds = statusString[2].split(" ");
		// 连接数,成功握手数,请求数
		sse.setHandledConnections(handleds[1]);
		sse.setHandledSuccess(handleds[2]);
		sse.setHandledRequest(handleds[3]);
		// 客户端连接情况结果
		String[] keeps = statusString[3].split(" ");
		// 客户端的连接数,响应数据到客户端的数量,请求指令的驻留连接.
		sse.setReading(keeps[1]);
		sse.setWriting(keeps[3]);
		sse.setWaiting(keeps[5]);
		System.out.println(sse.toString());
	}
}
