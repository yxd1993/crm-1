package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;


/**
 * @author 275636
 *每日会议
 */
public class DailyMeetingEntity extends DailyQueryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 会议类型
	 * 1:早会
	 * 2:晚会
	 */
	private String hyform;
	/**
	 *会议形式
	 * 1:现场会议
	 * 2:电话会议
	 */
	private String hyType;
	/**
	 * 会议地址
	 */
	private String hyaddress;
	/**
	 * 会议时间
	 */
	private Date hyDate;
	/**
	 * 参与人员
	 */
	private String cyEmp;
	/**
	 * 会议内容
	 */
	private String hycontext;
	/**
	 * 会议签到ID
	 */
	private String meetingSignId;
	
	public String getMeetingSignId() {
		return meetingSignId;
	}

	public void setMeetingSignId(String meetingSignId) {
		this.meetingSignId = meetingSignId;
	}

	public String getHyType() {
		return hyType;
	}

	public void setHyType(String hyType) {
		this.hyType = hyType;
	}
	
	public String getHyaddress() {
		return hyaddress;
	}

	public void setHyaddress(String hyaddress) {
		this.hyaddress = hyaddress;
	}

	public Date getHyDate() {
		return hyDate;
	}

	public void setHyDate(Date hyDate) {
		this.hyDate = hyDate;
	}

	public String getCyEmp() {
		return cyEmp;
	}

	public void setCyEmp(String cyEmp) {
		this.cyEmp = cyEmp;
	}

	public String getHyform() {
		return hyform;
	}

	public void setHyform(String hyform) {
		this.hyform = hyform;
	}

	public String getHycontext() {
		return hycontext;
	}

	public void setHycontext(String hycontext) {
		this.hycontext = hycontext;
	}

}
