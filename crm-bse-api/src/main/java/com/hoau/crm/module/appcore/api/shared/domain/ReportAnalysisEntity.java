package com.hoau.crm.module.appcore.api.shared.domain;


/**
 * 自定义报表实体
 * 
 * @author: 何斌
 * @create: 2015年9月8日 上午10:32:11
 */
public class ReportAnalysisEntity {
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 组织编码
	 */
	private String orgCode;
	/**
	 * 组织名称
	 */
	private String orgName;
	/**
	 * 总数
	 */
	private int	total;
	/**
	 * 所占百分比
	 */
	private double percent;
	/**
	 * 当前部门负责人
	 */
	private String managerName;
	/**
	 * 当前部门负责人电话
	 */
	private String managerCellphone;
	/**
	 * 是否能点击
	 */
	private String isClick;
	
	/**
	 * 组织级别
	 */
	private String orgType;
	
	/**
	 * 排序顺序
	 */
	private String sortNum;
	/**
	 * 人均
	 */
	private double avgOfPeople;
	
	/**
	 * 顶部开始
	 */
	private String head;
	/**
	 * 人均文字是否隐藏
	 */
	private String hidden;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public String getIsClick() {
		return isClick;
	}
	public void setIsClick(String isClick) {
		this.isClick = isClick;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerCellphone() {
		return managerCellphone;
	}
	public void setManagerCellphone(String managerCellphone) {
		this.managerCellphone = managerCellphone;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getSortNum() {
		return sortNum;
	}
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	public double getAvgOfPeople() {
		return avgOfPeople;
	}
	public void setAvgOfPeople(double avgOfPeople) {
		this.avgOfPeople = avgOfPeople;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getHidden() {
		return hidden;
	}
	public void setHidden(String hidden) {
		this.hidden = hidden;
	}
}
