package com.hoau.crm.module.login.server.service;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

public interface ILoginService {

	/**
	 * 验证用户名密码
	 * 
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-5-14
	 * @update
	 */
	public void verifyPassword(UserEntity user);

	/**
	 * SESSION失效
	 * 
	 * @author 蒋落琛
	 * @date 2015-6-6
	 * @update
	 */
	public void userLogout();
	
	/**
	 * 根据用户名查询用户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-17
	 * @update
	 */
	public UserEntity getUserByLoginName(String loginName);
}
