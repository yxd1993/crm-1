package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;


/**
 * @author 275636
 *每日培训实体
 */
public class DailyTrainEntity extends DailyQueryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 培训类型
	 * 1.业务考试
	 * 2.模拟演练
	 * 3.销售技巧
	 */
	private String pxType;
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
	 * 培训内容
	 */
	private String pxcontext;
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

	public String getPxType() {
		return pxType;
	}

	public void setPxType(String pxType) {
		this.pxType = pxType;
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

	public String getPxcontext() {
		return pxcontext;
	}

	public void setPxcontext(String pxcontext) {
		this.pxcontext = pxcontext;
	}
}
