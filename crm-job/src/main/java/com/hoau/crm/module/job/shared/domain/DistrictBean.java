package com.hoau.crm.module.job.shared.domain;

import java.sql.Date;

/**
 * 省市区信息实体
 *
 * @author 潘强
 * @date 2015-7-8
 */
public class DistrictBean {

	/**
	 * 省市区信息id
	 */
	private String id;
	/**
	 *减记码
	 */
	private String districtCode;
	 /**
	  * 名称
	  */
	private String districtName;
	 /**
	  * 类型
	  */
	private String districtType;
	 /**
	  * 拼音
	  */
	private String pinyin;
	 /**
	  * 版本号
	  */
	private String versionNo;
	/**
	  * 父简记码
	  */
	private String parentDistrictCode;
	/**
	  * 创建时间
	  */
	private String createDate;
	/**
	  * 创建用户减记码
	  */
	private String createUser;
	/**
	  * 修改时间
	  */
	private String modifyDate;
	/**
	  * 修改的用户
	  */
	private String modifyUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictType() {
		return districtType;
	}

	public void setDistrictType(String districtType) {
		this.districtType = districtType;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getParentDistrictCode() {
		return parentDistrictCode;
	}

	public void setParentDistrictCode(String parentDistrictCode) {
		this.parentDistrictCode = parentDistrictCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	
}
