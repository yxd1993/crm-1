package com.hoau.crm.module.bse.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.UserUseMobileEntity;

/**
 * 用户、手机对应关系DAO
 * 
 * @author 蒋落琛
 * @date 2015-12-8
 */
@Repository
public interface UserUseMobileMapper {

	/**
	 * 查询此用户是否已在此手机登录过
	 * 
	 * @param phone
	 * @return
	 * @author 蒋落琛
	 * @date 2015-12-8
	 * @update
	 */
	public UserUseMobileEntity getUserMobileInfo(
			UserUseMobileEntity userMobileInfo);

	/**
	 * 创建用户与使用手机信息
	 * 
	 * @param validateCode
	 * @author 蒋落琛
	 * @date 2015-12-8
	 * @update
	 */
	public void createUserMobile(UserUseMobileEntity userMobileInfo);

}