package com.hoau.crm.module.common.shared.domain;

import java.util.List;

/**
 * 短信平台参数信息VO
 * 
 * @author 蒋落琛
 * @date 2015-12-17
 */
public class SmsPlatformVo {

	/**
	 * 系统标识
	 */
	private String systemName;

	/**
	 * 业务类型
	 */
	private String businessType;

	/**
	 * 短信信息集合
	 */
	private List<SmsPlatformEntity> requestContentList;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public List<SmsPlatformEntity> getRequestContentList() {
		return requestContentList;
	}

	public void setRequestContentList(List<SmsPlatformEntity> requestContentList) {
		this.requestContentList = requestContentList;
	}

}
