package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;

/**
 * @author 275636
 *资源需求实体
 */
public class ResDemandEntity extends DailyQueryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 期望解决时间
	 */
	private Date solvetime;
	
	/**
	 * 需求资源
	 */
	private String resources;
	
	/**
	 * 是否与大区总沟通
	 */
	private String isgt;
	
	/**
	 * 大区总意见
	 * 1:是
	 * 2：否
	 */
	private String regviews;
	
	/**
	 * 是否答复
	 * 1:未答复
	 * 2:已答复
	 */
	private String isreply;
	
	/**
	 * 创建结束日期
	 * */
	private Date createEndDate;
	
	/**
	 * 集团意见
	 */
	private String groupopin;
	
	public String getGroupopin() {
		return groupopin;
	}

	public void setGroupopin(String groupopin) {
		this.groupopin = groupopin;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}

	public Date getSolvetime() {
		return solvetime;
	}

	public void setSolvetime(Date solvetime) {
		this.solvetime = solvetime;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getIsgt() {
		return isgt;
	}

	public void setIsgt(String isgt) {
		this.isgt = isgt;
	}

	public String getRegviews() {
		return regviews;
	}

	public void setRegviews(String regviews) {
		this.regviews = regviews;
	}

	public String getIsreply() {
		return isreply;
	}

	public void setIsreply(String isreply) {
		this.isreply = isreply;
	}

}
