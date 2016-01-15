package com.hoau.crm.module.appcore.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 手机信息ENTITY
 *
 * @author 蒋落琛
 * @date 2015-7-22
 */
public class MobileInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 8376522373594716832L;
	
	/**
     * 用户ID
     */
    private String userCode;

	/**
	 * IMEI
	 */
	private String imei;

	/**
	 * IMSI
	 */
	private String imsi;

	/**
	 * 手机型号
	 */
	private String mtype;

	/**
	 * 手机品牌
	 */
	private String mtyb;

	/**
	 * SDK版本
	 */
	private String androidsdk;

	/**
	 * 系统版本
	 */
	private String androidv;

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public String getMtyb() {
		return mtyb;
	}

	public void setMtyb(String mtyb) {
		this.mtyb = mtyb;
	}

	public String getAndroidsdk() {
		return androidsdk;
	}

	public void setAndroidsdk(String androidsdk) {
		this.androidsdk = androidsdk;
	}

	public String getAndroidv() {
		return androidv;
	}

	public void setAndroidv(String androidv) {
		this.androidv = androidv;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
