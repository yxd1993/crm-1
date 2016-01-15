package com.hoau.crm.module.appcore.api.shared.vo;

import com.hoau.crm.module.appcore.api.shared.domain.AppVersionEntity;


/**
 * APP版本信息VO
 *
 * @author 蒋落琛
 * @date 2015-6-26
 */
public class AppVersionVo{

	/**
	 * APP版本信息
	 */
	private AppVersionEntity  appVersionEntity;

	public AppVersionEntity getAppVersionEntity() {
		return appVersionEntity;
	}

	public void setAppVersionEntity(AppVersionEntity appVersionEntity) {
		this.appVersionEntity = appVersionEntity;
	}
}
