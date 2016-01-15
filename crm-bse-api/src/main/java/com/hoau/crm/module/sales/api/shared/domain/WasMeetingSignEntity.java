package com.hoau.crm.module.sales.api.shared.domain;


import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 会议被扫描签到实体
 * 
 * @author 潘强
 * @date 2015-9-10
 */
public class WasMeetingSignEntity extends BaseEntity {

	private static final long serialVersionUID = 210265119117733949L;
	
	/**
	 * 扫描人id
	 */
	private String wasSweepPeopSignId;
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


	public String getWasSweepPeop() {
		return wasSweepPeop;
	}

	public String getWasSweepPeopName() {
		return wasSweepPeopName;
	}

	public String getWasSweepPeopJobCode() {
		return wasSweepPeopJobCode;
	}

	public String getWasSweepPeopJobName() {
		return wasSweepPeopJobName;
	}

	public String getWasSweepPeopDeptCode() {
		return wasSweepPeopDeptCode;
	}

	public String getWasSweepPeopDeptName() {
		return wasSweepPeopDeptName;
	}

	public void setWasSweepPeop(String wasSweepPeop) {
		this.wasSweepPeop = wasSweepPeop;
	}

	public void setWasSweepPeopName(String wasSweepPeopName) {
		this.wasSweepPeopName = wasSweepPeopName;
	}

	public void setWasSweepPeopJobCode(String wasSweepPeopJobCode) {
		this.wasSweepPeopJobCode = wasSweepPeopJobCode;
	}

	public void setWasSweepPeopJobName(String wasSweepPeopJobName) {
		this.wasSweepPeopJobName = wasSweepPeopJobName;
	}

	public void setWasSweepPeopDeptCode(String wasSweepPeopDeptCode) {
		this.wasSweepPeopDeptCode = wasSweepPeopDeptCode;
	}

	public void setWasSweepPeopDeptName(String wasSweepPeopDeptName) {
		this.wasSweepPeopDeptName = wasSweepPeopDeptName;
	}

	public String getWasSweepPeopSignId() {
		return wasSweepPeopSignId;
	}

	public void setWasSweepPeopSignId(String wasSweepPeopSignId) {
		this.wasSweepPeopSignId = wasSweepPeopSignId;
	}

	
}
