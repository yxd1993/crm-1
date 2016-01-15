package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;
import java.util.Map;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.sales.api.shared.domain.MeetingSignEntity;
import com.hoau.crm.module.sales.api.shared.domain.SweepSignEntity;

/**
 * 签到VO
 * @author: 何斌
 * @create: 2015年7月3日 上午9:21:37
 */
public class SignVo {
	
	/**
	 * 客户ID
	 */
	private String accountId;
	
	/**
	 * 会议类型
	 */
	private String meetingType;
	
	/**
	 * 签到实体
	 */
	private SignEntity signEntity;
	
	/**
	 * 扫描签到实体
	 */
	private SweepSignEntity sweepSignEntity;
	
	/**
	 * 会议签到信息实体
	 */
	private MeetingSignEntity meetingSignEntity;
	
	/**
	 *未被关联签到地址
	 */
	private List<SignEntity> noRelationSignList;
	
	/**
	 * 图片内容
	 */
	private byte[] imgContent;
	
	/**
	 * 未被关联会议签到信息
	 */
	private List<MeetingSignEntity> meetingSignList;
	
	/**
	 *签到扫描陪同人工号列表
	 */
	private List<Map<String,Object>> empCodeList;
	
	public List<SignEntity> getNoRelationSignList() {
		return noRelationSignList;
	}

	public void setNoRelationSignList(List<SignEntity> noRelationSignList) {
		this.noRelationSignList = noRelationSignList;
	}

	public byte[] getImgContent() {
		return imgContent;
	}

	public void setImgContent(byte[] imgContent) {
		this.imgContent = imgContent;
	}

	public SignEntity getSignEntity() {
		return signEntity;
	}

	public void setSignEntity(SignEntity signEntity) {
		this.signEntity = signEntity;
	}

	public SweepSignEntity getSweepSignEntity() {
		return sweepSignEntity;
	}

	public void setSweepSignEntity(SweepSignEntity sweepSignEntity) {
		this.sweepSignEntity = sweepSignEntity;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public MeetingSignEntity getMeetingSignEntity() {
		return meetingSignEntity;
	}

	public void setMeetingSignEntity(MeetingSignEntity meetingSignEntity) {
		this.meetingSignEntity = meetingSignEntity;
	}

	public List<MeetingSignEntity> getMeetingSignList() {
		return meetingSignList;
	}

	public void setMeetingSignList(List<MeetingSignEntity> meetingSignList) {
		this.meetingSignList = meetingSignList;
	}

	public String getMeetingType() {
		return meetingType;
	}
	
	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public List<Map<String, Object>> getEmpCodeList() {
		return empCodeList;
	}

	public void setEmpCodeList(List<Map<String, Object>> empCodeList) {
		this.empCodeList = empCodeList;
	}
	
}
