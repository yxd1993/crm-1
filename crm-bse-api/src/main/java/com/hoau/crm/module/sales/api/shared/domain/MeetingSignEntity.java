package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;
import java.util.List;




import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.sales.api.shared.domain.WasMeetingSignEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;
/**
 * 会议签到扫描人信息实体
 * 
 * @author 潘强
 * @date 2015-9-10
 */
public class MeetingSignEntity extends BaseEntity{

	private static final long serialVersionUID = -2664958865755655421L;
	
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
	 * 扫描签到类型
	 */
	private String sweepPeopType; 
	
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
	 *参与率
	 */
	private String inRate;

	/**
	 * 被扫描签到信息
	 */
	private List<WasMeetingSignEntity> wasMeetingSignList;
	
	/**
	 * 附件信息
	 */
	private List<AttachmentEntity> attachmentList;
	
	/**
	 *当前扫描人的部门编码
	 */
	private String deptCode;
	
	public String getSweepPeopName() {
		return sweepPeopName;
	}
	public void setSweepPeopName(String sweepPeopName) {
		this.sweepPeopName = sweepPeopName;
	}
	
	public List<WasMeetingSignEntity> getWasMeetingSignList() {
		return wasMeetingSignList;
	}
	public void setWasMeetingSignList(List<WasMeetingSignEntity> wasMeetingSignList) {
		this.wasMeetingSignList = wasMeetingSignList;
	}
	public String getSweepPeop() {
		return sweepPeop;
	}
	public void setSweepPeop(String sweepPeop) {
		this.sweepPeop = sweepPeop;
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
	public String getSweepPeopType() {
		return sweepPeopType;
	}
	public void setSweepPeopType(String sweepPeopType) {
		this.sweepPeopType = sweepPeopType;
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
	public String getInRate() {
		return inRate;
	}
	public void setInRate(String inRate) {
		this.inRate = inRate;
	}
	public List<AttachmentEntity> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<AttachmentEntity> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
}
