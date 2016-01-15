package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;
import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;


/**
 * 扫描签到实体
 * 
 * @author 蒋落琛
 * @date 2015-7-22
 */
public class SweepSignEntity extends BaseEntity {

	private static final long serialVersionUID = -47287003833107872L;

	/**
	 * 扫描人工号
	 */
	private String sweepPeop;
	
	/**
	 * 扫描人姓名
	 */
	private String sweepPeopName;
	
	/**
	 * 扫描人岗位编码
	 */
	private String sweepPeopJobCode;
	
	/**
	 * 扫描人岗位名称
	 */
	private String sweepPeopJobName;
	
	/**
	 * 扫描人部门编码
	 */
	private String sweepPeopDeptCode;
	
	/**
	 * 扫描人部门名称
	 */
	private String sweepPeopDeptName;

	/**
	 * 被扫描人工号
	 */
	private String wasSweepPeop;

	/**
	 * 被扫描人姓名
	 */
	private String wasSweepPeopName;
	
	/**
	 * 被扫描人岗位编码
	 */
	private String wasSweepPeopJobCode;
	
	/**
	 * 被扫描人岗位名称
	 */
	private String wasSweepPeopJobName;
	
	/**
	 * 被扫描人部门编码
	 */
	private String wasSweepPeopDeptCode;
	
	/**
	 * 被扫描人部门名称
	 */
	private String wasSweepPeopDeptName;

	/**
	 * 二维码生成时间
	 */
	private Date qrcodeTime;

	/**
	 * 扫描时间
	 */
	private Date sweepTime;
	
	/**
	 * 扫描结束时间
	 */
	private Date sweepEndTime;
	
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
	 * 积分
	 */
	private String vantages;
	
	/**
	 * 多张图片信息
	 */
	private List<AttachmentEntity> attachmentList;
	
	/**
	 *部门实体
	 */
	private DepartmentEntity departmentEntity;

	public String getSweepPeopName() {
		return sweepPeopName;
	}

	public void setSweepPeopName(String sweepPeopName) {
		this.sweepPeopName = sweepPeopName;
	}

	public String getWasSweepPeopName() {
		return wasSweepPeopName;
	}

	public void setWasSweepPeopName(String wasSweepPeopName) {
		this.wasSweepPeopName = wasSweepPeopName;
	}

	public String getSweepPeop() {
		return sweepPeop;
	}

	public void setSweepPeop(String sweepPeop) {
		this.sweepPeop = sweepPeop;
	}

	public String getWasSweepPeop() {
		return wasSweepPeop;
	}

	public void setWasSweepPeop(String wasSweepPeop) {
		this.wasSweepPeop = wasSweepPeop;
	}

	public Date getQrcodeTime() {
		return qrcodeTime;
	}

	public void setQrcodeTime(Date qrcodeTime) {
		this.qrcodeTime = qrcodeTime;
	}

	public Date getSweepTime() {
		return sweepTime;
	}

	public void setSweepTime(Date sweepTime) {
		this.sweepTime = sweepTime;
	}

	public Date getSweepEndTime() {
		return sweepEndTime;
	}

	public void setSweepEndTime(Date sweepEndTime) {
		this.sweepEndTime = sweepEndTime;
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

	public String getSweepPeopDeptCode() {
		return sweepPeopDeptCode;
	}

	public void setSweepPeopDeptCode(String sweepPeopDeptCode) {
		this.sweepPeopDeptCode = sweepPeopDeptCode;
	}

	public String getSweepPeopDeptName() {
		return sweepPeopDeptName;
	}

	public void setSweepPeopDeptName(String sweepPeopDeptName) {
		this.sweepPeopDeptName = sweepPeopDeptName;
	}

	public String getWasSweepPeopDeptCode() {
		return wasSweepPeopDeptCode;
	}

	public void setWasSweepPeopDeptCode(String wasSweepPeopDeptCode) {
		this.wasSweepPeopDeptCode = wasSweepPeopDeptCode;
	}

	public String getWasSweepPeopDeptName() {
		return wasSweepPeopDeptName;
	}

	public void setWasSweepPeopDeptName(String wasSweepPeopDeptName) {
		this.wasSweepPeopDeptName = wasSweepPeopDeptName;
	}

	public String getSweepPeopJobCode() {
		return sweepPeopJobCode;
	}

	public void setSweepPeopJobCode(String sweepPeopJobCode) {
		this.sweepPeopJobCode = sweepPeopJobCode;
	}

	public String getSweepPeopJobName() {
		return sweepPeopJobName;
	}

	public void setSweepPeopJobName(String sweepPeopJobName) {
		this.sweepPeopJobName = sweepPeopJobName;
	}

	public String getWasSweepPeopJobCode() {
		return wasSweepPeopJobCode;
	}

	public void setWasSweepPeopJobCode(String wasSweepPeopJobCode) {
		this.wasSweepPeopJobCode = wasSweepPeopJobCode;
	}

	public String getWasSweepPeopJobName() {
		return wasSweepPeopJobName;
	}

	public void setWasSweepPeopJobName(String wasSweepPeopJobName) {
		this.wasSweepPeopJobName = wasSweepPeopJobName;
	}

	public String getVantages() {
		return vantages;
	}

	public void setVantages(String vantages) {
		this.vantages = vantages;
	}

	public List<AttachmentEntity> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AttachmentEntity> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public DepartmentEntity getDepartmentEntity() {
		return departmentEntity;
	}

	public void setDepartmentEntity(DepartmentEntity departmentEntity) {
		this.departmentEntity = departmentEntity;
	}
	
}
