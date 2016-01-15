/**
 * 
 */
package com.hoau.crm.module.bse.server.service;

import java.util.List;

import com.hoau.crm.module.bse.shared.domain.ServerStatusEntity;

/**
 * nginx 服务器状态接口
 * @author 丁勇
 * @date 2015年8月13日
 */
public interface IServerStatusService {
	/**
	 * 查询连接数
	 * @return
	 * @author 丁勇
	 * @date 2015年8月13日
	 * @update 
	 */
	public List<ServerStatusEntity> queryServerConnects(ServerStatusEntity sse);
}
