package com.hoau.crm.module.bse.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

public class UserUseMobileEntity extends BaseEntity {

	private static final long serialVersionUID = 8865722899058062585L;

	/**
	 * 手机唯一标识
	 */
	private String uniqueId;

	/**
	 * 用户工号
	 */
	private String userCode;

	/**
	 * APPTYPE
	 */
	private String appType;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
}
