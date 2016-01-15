package com.hoau.crm.module.bse.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

public class ValidateCodeEntity extends BaseEntity{

	private static final long serialVersionUID = -8992999274761109636L;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 验证码
	 */
	private String validateCode;
	
	/**
	 * 操作类型  1为重新发送
	 */
	private String operType;
	
	/**
	 * 发送是否成功
	 */
	private String isSend;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}
}
