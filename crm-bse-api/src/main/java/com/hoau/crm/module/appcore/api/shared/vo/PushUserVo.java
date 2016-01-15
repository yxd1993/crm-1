package com.hoau.crm.module.appcore.api.shared.vo;

import com.hoau.crm.module.appcore.api.shared.domain.MobileInfoEntity;
import com.hoau.crm.module.appcore.api.shared.domain.PushUserEntity;


/**
 * 百度推送用户VO
 *
 * @author 蒋落琛
 * @date 2015-6-25
 */
public class PushUserVo{

	/**
	 * 百度推送用户信息
	 */
	private PushUserEntity pushUserEntity;
	
	/**
	 * 手机信息
	 */
	private MobileInfoEntity mobileInfoEntity;

	public PushUserEntity getPushUserEntity() {
		return pushUserEntity;
	}

	public void setPushUserEntity(PushUserEntity pushUserEntity) {
		this.pushUserEntity = pushUserEntity;
	}

	public MobileInfoEntity getMobileInfoEntity() {
		return mobileInfoEntity;
	}

	public void setMobileInfoEntity(MobileInfoEntity mobileInfoEntity) {
		this.mobileInfoEntity = mobileInfoEntity;
	}
}
