/**
 * 
 */
package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.shared.domain.ServerStatusEntity;


/**
 *
 * @author 丁勇
 * @date 2015年8月13日
 */
@Repository
public interface ServerStatusMapper {
	/**
	 * 保存Nginx服务器状态记录
	 * @param ssEntity
	 * @author 丁勇
	 * @date 2015年8月12日
	 * @update 
	 */
	void insertServerLog(ServerStatusEntity ssEntity);
	
	/**
	 * 查询服务器连接数情况
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年8月13日
	 * @update 
	 */
	List<ServerStatusEntity> queryServerConnects(Map<String,Object> map);
}
