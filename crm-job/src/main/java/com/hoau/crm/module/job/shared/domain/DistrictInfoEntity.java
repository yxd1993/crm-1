package com.hoau.crm.module.job.shared.domain;

import java.util.List;

/**
 * 省市区获取接口信息实体
 *
 * @author 潘强
 * @date 2015-7-8
 */
public class DistrictInfoEntity {
	/**
	 * 状态码
	 */
	private String errorCode;
	/**
	 * 信息
	 */
	private String errorMessage;
	/**
	 * 接受内容
	 */
	private List<DistrictBean> result;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<DistrictBean> getResult() {
		return result;
	}

	public void setResult(List<DistrictBean> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "DistrictInfoEntity [errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + ", result=" + result + "]";
	}
	
	
}
