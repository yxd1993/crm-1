package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 行政区域实体
 * 
 * @author: 何斌
 * @create: 2015年6月23日 下午5:17:59
 */
public class DistrictEntity extends BaseEntity {

	private static final long serialVersionUID = -3950647614937305177L;
	
	/**
	 * 行政区域编码
	 */
	private String districtCode;
	/**
	 * 行政区域名称
	 */
	private String districtName;
	/**
	 * 行政区域类型
	 */
	private String districtType;
	/**
	 * 上级行政区域编码
	 */
	private String parentDistrictCode;
	/**
	 * 拼音
	 */
	private String pinYin;
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
	public String getParentDistrictCode() {
		return parentDistrictCode;
	}
	public void setParentDistrictCode(String parentDistrictCode) {
		this.parentDistrictCode = parentDistrictCode;
	}
	public String getPinYin() {
		return pinYin;
	}
	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
	
}
