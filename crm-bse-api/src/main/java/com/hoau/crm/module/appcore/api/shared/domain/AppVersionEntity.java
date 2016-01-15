package com.hoau.crm.module.appcore.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * APP版本信息实体
 *
 * @author 蒋落琛
 * @date 2015-6-26
 */
public class AppVersionEntity extends BaseEntity{

	private static final long serialVersionUID = 5883286605960061706L;

	/**
	 * 版本号
	 */
	private Integer versionCode;

	/**
	 * APK名字
	 */
	private String apkName;

	/**
	 * 是否强制更新
	 */
	private String isMust;
	
	/**
	 * 是否当前版本
	 */
	private String isNow;

	/**
	 * 安装包下载地址
	 */
	private String url;
	
	/**
	 * 头像下载地址
	 */
	private String userHeadUrl;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 创建版本结束时间
	 */
	private Date createEndDate;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getApkName() {
		return apkName;
	}

	public void setApkName(String apkName) {
		this.apkName = apkName;
	}

	public String getIsMust() {
		return isMust;
	}

	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}

	public String getIsNow() {
		return isNow;
	}

	public void setIsNow(String isNow) {
		this.isNow = isNow;
	}

	public String getUserHeadUrl() {
		return userHeadUrl;
	}

	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}
}
