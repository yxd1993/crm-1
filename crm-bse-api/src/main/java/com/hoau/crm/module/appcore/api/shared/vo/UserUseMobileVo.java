package com.hoau.crm.module.appcore.api.shared.vo;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserUseMobileEntity;
import com.hoau.crm.module.bse.api.shared.domain.ValidateCodeEntity;

public class UserUseMobileVo {

	/**
	 * 用户与手机对应关系
	 */
	private UserUseMobileEntity userUseMobileEntity;
	
	/**
	 *  验证码信息
	 */
	private ValidateCodeEntity validateCodeEntity;
	
	/**
	 * 用户登录信息
	 */
	private UserEntity userEntity;

	public UserUseMobileEntity getUserUseMobileEntity() {
		return userUseMobileEntity;
	}

	public void setUserUseMobileEntity(UserUseMobileEntity userUseMobileEntity) {
		this.userUseMobileEntity = userUseMobileEntity;
	}

	public ValidateCodeEntity getValidateCodeEntity() {
		return validateCodeEntity;
	}

	public void setValidateCodeEntity(ValidateCodeEntity validateCodeEntity) {
		this.validateCodeEntity = validateCodeEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
