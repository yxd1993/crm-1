/**
 * 
 */
package com.hoau.crm.module.appcore.api.shared.domain;

import java.util.Date;
import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.SignAccompanyEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 *
 * @author 丁勇
 * @date 2015年11月5日
 */
public class SignEntity extends BaseEntity {

	private static final long serialVersionUID = 4932364252919356704L;

	/**
	 *客户ID
	 */
	private String customerId;

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
	 * 图片文件名
	 */
	private String imgName;

	/**
	 * 图片路径
	 */
	private String imgUrl;
	/**
	 * 客户姓名
	 */
	private String customerName;
	
	/**
	 * 签到信息
	 */
	private String signInfo;

	/**
	 * 签到结束时间
	 */
	private Date createEndDate;
	
	/**
	 *是否关联
	 */
	private String isRelationChat;

	/**
	 *签到扫描陪同人列表
	 */
	private  List<SignAccompanyEntity> signAccopmanyList;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getSignAddress() {
		return signAddress;
	}

	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}

	public String getIsRelationChat() {
		return isRelationChat;
	}

	public void setIsRelationChat(String isRelationChat) {
		this.isRelationChat = isRelationChat;
	}

	public List<SignAccompanyEntity> getSignAccopmanyList() {
		return signAccopmanyList;
	}

	public void setSignAccopmanyList(List<SignAccompanyEntity> signAccopmanyList) {
		this.signAccopmanyList = signAccopmanyList;
	}

	
	
}
