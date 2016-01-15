package com.hoau.crm.module.appcore.server.dao;

import com.hoau.crm.module.appcore.api.shared.domain.PushUserEntity;

/**
 * 百度推送账号管理
 *
 * @author 蒋落琛
 * @date 2015-6-25
 */
public interface PushUserMapper {

	/**
	 * 新增百度推送用户信息
	 * 
	 * @param PushUserEntity
	 */
	public void createPushUser(PushUserEntity PushUserEntity);

	/**
	 * 注销百度推送用户信息
	 * 
	 * @param PushUserEntity
	 */
	public void modifyPushUser(PushUserEntity PushUserEntity);

	/**
	 * 方法体说明：推送消息声音设置
	 * 
	 * @param pushvo
	 */
	public void modifyPushSound(PushUserEntity pushvo);

	/**
	 * 推送消息接收设置
	 * 
	 * @param pushvo
	 */
	public void modifyPushSendState(PushUserEntity pushvo);

}
