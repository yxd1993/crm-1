package com.hoau.crm.module.job.shared.domain;

import java.util.List;


/**
 * 用于接收接口返回实体转换
 * @author 丁勇
 * @date 2015年8月4日
 */
public class BseDepartmentResEntity {
	/**
	 * 状态码
	 */
	private String errorCode;
	/**
	 * 信息
	 */
	private String errorMessage;
	/**
	 * 接收数据
	 */
	private List<BseDepartmentBean> result;

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

	public List<BseDepartmentBean> getResult() {
		return result;
	}

	public void setResult(List<BseDepartmentBean> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "BseDepartmentResEntity [errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + ", result=" + result + "]";
	}
	
	
}
