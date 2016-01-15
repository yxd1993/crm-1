package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;


/**
 * @author 275636
 *每日问题实体
 */
public class DailyProblemEntity extends DailyQueryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 演练时间
	 */
	private Date yltime;
	/**
	 * 演练地址
	 */
	private String yladdress;
	/**
	 * 参与人员
	 */
	private String cyEmp;
	/**
	 * 演练方式
	 * 1:现场演练
	 * 2:电话演练
	 */
	private String ylway;
	/**
	 * 问题描述
	 */
	private String wtdescribe;
	/**
	 * 举措描述
	 */
	private String jcdescribe;
	/**
	 * 会议签到ID
	 */
	private String meetingSignId;
	
	public String getYladdress() {
		return yladdress;
	}

	public void setYladdress(String yladdress) {
		this.yladdress = yladdress;
	}

	public String getCyEmp() {
		return cyEmp;
	}

	public void setCyEmp(String cyEmp) {
		this.cyEmp = cyEmp;
	}

	public Date getYltime() {
		return yltime;
	}

	public void setYltime(Date yltime) {
		this.yltime = yltime;
	}
	public String getMeetingSignId() {
		return meetingSignId;
	}

	public void setMeetingSignId(String meetingSignId) {
		this.meetingSignId = meetingSignId;
	}

	public String getYlway() {
		return ylway;
	}

	public void setYlway(String ylway) {
		this.ylway = ylway;
	}

	public String getWtdescribe() {
		return wtdescribe;
	}

	public void setWtdescribe(String wtdescribe) {
		this.wtdescribe = wtdescribe;
	}

	public String getJcdescribe() {
		return jcdescribe;
	}

	public void setJcdescribe(String jcdescribe) {
		this.jcdescribe = jcdescribe;
	}
}
