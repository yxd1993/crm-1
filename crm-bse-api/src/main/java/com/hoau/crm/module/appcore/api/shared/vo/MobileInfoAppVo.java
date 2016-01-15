package com.hoau.crm.module.appcore.api.shared.vo;

import com.hoau.crm.module.appcore.api.shared.domain.MobileInfoEntity;

/**
 * 手机信息VO
 *
 * @author 蒋落琛
 * @date 2015-7-22
 */
public class MobileInfoAppVo{

	/**
	 * 手机信息
	 */
	private MobileInfoEntity mobileInfoEntity;

	public MobileInfoEntity getMobileInfoEntity() {
		return mobileInfoEntity;
	}

	public void setMobileInfoEntity(MobileInfoEntity mobileInfoEntity) {
		this.mobileInfoEntity = mobileInfoEntity;
	}
}
