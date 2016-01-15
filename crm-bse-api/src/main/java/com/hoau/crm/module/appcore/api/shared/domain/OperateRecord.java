package com.hoau.crm.module.appcore.api.shared.domain;

/**
 * 操作日志信息
 *
 * @author 蒋落琛
 * @date 2015-6-12
 */
public class OperateRecord {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 地址
	 */
	private String uri;
	
	/**
	 * 请求人
	 */
	private String userCode;
	
	/**
	 * 被授权人
	 */
	private String wasAuthorizedPerson;
	
	/**
	 * APP版本号
	 */
	private String appVersion;
	
	/**
	 * 设备ID
	 */
	private String deviceId;
	
	/**
	 * 设备类型
	 */
	private String appType;
	
	/**
	 * 请求方法
	 */
	private String requestMethod;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getRequestMethod() {
		return requestMethod;
	}
	
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getWasAuthorizedPerson() {
		return wasAuthorizedPerson;
	}

	public void setWasAuthorizedPerson(String wasAuthorizedPerson) {
		this.wasAuthorizedPerson = wasAuthorizedPerson;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
}
