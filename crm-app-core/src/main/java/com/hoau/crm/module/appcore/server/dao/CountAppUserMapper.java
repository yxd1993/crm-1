package com.hoau.crm.module.appcore.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 统计APP的用户信息
 * 
 * @author 蒋落琛
 * @date 2015-12-29
 */
@Repository
public interface CountAppUserMapper {

	/**
	 * 统计APP的用户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-12-29
	 * @update
	 */
	public List<Map<String, String>> countAppUserInfo();

}
