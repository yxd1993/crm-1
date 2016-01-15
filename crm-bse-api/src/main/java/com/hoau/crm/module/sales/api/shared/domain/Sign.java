package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 签到实体
 * 
 * @author: 潘强
 * @create: 2015年7月21日
 * @modfiy 丁勇
 * @modifydate 2015年8月14日
 */
public class Sign extends BaseEntity {

	private static final long serialVersionUID = 6890500589343602371L;

	/**
	 * 客户id
	 */
	private String accountId;
	
	/**
	 * 客户姓名
	 */
	private String customerName;

	/**
	 * 经度
	 */
	private Double longitude;

	/**
	 * 纬度
	 */
	private Double latitude;

	/**
	 * 签到地址
	 */
	private String signAddress;
	
	/**
	 * 签到信息
	 */
	private String signInfo;

	/**
	 * 图片文件名
	 */
	private String imgName;

	/**
	 * 图片路径
	 */
	private String imgUrl;

	/**
	 * 签到结束时间
	 */
	private Date createEndDate;
	
	/**
	 *是否关联
	 */
	private String isRelationChat;

	public String getIsRelationChat() {
		return isRelationChat;
	}

	public void setIsRelationChat(String isRelationChat) {
		this.isRelationChat = isRelationChat;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSignAddress() {
		return signAddress;
	}

	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}
}
