/**
 * 
 */
package com.hoau.crm.module.sales.api.shared.vo;

import java.io.Serializable;

import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;

/**
 * 
 * @author 丁勇
 * @date 2015年6月11日
 */
public class SaleChatsVo implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -839035353251539839L;
	/**
	 * 门店负责人姓名
	 */
	private String tierEmpName;
	/**
	 * 路区负责人名称
	 */
	private String roadEmpName;

	/**
	 * 大区负责人名称
	 */
	private String regionsEmpName;
	/**
	 * 事业部负责人名称
	 */
	private String businessUnitEmpName;

	/**
	 * 营运副总名称
	 */
	private String odEmpName;
	/**
	 * 总裁名称
	 */
	private String ceoEmpName;
	/**
	 * 团队经理名称
	 */
	private String teamManagerEmpName;
	/**
	 * 客户经理名称
	 */
	private String saleManEmpName;
	/**
	 * 洽谈实体
	 */
	private SaleChatsEntity chatsEntity;
	/**
	 * 预约信息
	 */
	private String reserveInfo;
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建人姓名
	 */
	private String createUserName;
	
	/**
	 * DC账号
	 */
	private String dcAccount;
	
	/**
	 * 一级部门
	 */
	private String oneLevelUnit;
	
	/**
	 * 二级部门
	 */
	private String twoLevelUnit;
	
	/**
	 * 三级部门 
	 */
	private String threeLevelUnit;
	
	
	public String getDcAccount() {
		return dcAccount;
	}

	public void setDcAccount(String dcAccount) {
		this.dcAccount = dcAccount;
	}

	public String getOneLevelUnit() {
		return oneLevelUnit;
	}

	public void setOneLevelUnit(String oneLevelUnit) {
		this.oneLevelUnit = oneLevelUnit;
	}

	public String getTwoLevelUnit() {
		return twoLevelUnit;
	}

	public void setTwoLevelUnit(String twoLevelUnit) {
		this.twoLevelUnit = twoLevelUnit;
	}

	public String getThreeLevelUnit() {
		return threeLevelUnit;
	}

	public void setThreeLevelUnit(String threeLevelUnit) {
		this.threeLevelUnit = threeLevelUnit;
	}

	public String getBusinessUnitEmpName() {
		return businessUnitEmpName;
	}

	public String getCeoEmpName() {
		return ceoEmpName;
	}

	public SaleChatsEntity getChatsEntity() {
		return chatsEntity;
	}

	public String getOdEmpName() {
		return odEmpName;
	}

	public String getRegionsEmpName() {
		return regionsEmpName;
	}

	public String getReserveInfo() {
		return reserveInfo;
	}

	public String getRoadEmpName() {
		return roadEmpName;
	}

	public String getTierEmpName() {
		return tierEmpName;
	}

	public void setBusinessUnitEmpName(String businessUnitEmpName) {
		this.businessUnitEmpName = businessUnitEmpName;
	}

	public void setCeoEmpName(String ceoEmpName) {
		this.ceoEmpName = ceoEmpName;
	}

	public void setChatsEntity(SaleChatsEntity chatsEntity) {
		this.chatsEntity = chatsEntity;
	}

	public void setOdEmpName(String odEmpName) {
		this.odEmpName = odEmpName;
	}

	public void setRegionsEmpName(String regionsEmpName) {
		this.regionsEmpName = regionsEmpName;
	}

	public void setReserveInfo(String reserveInfo) {
		this.reserveInfo = reserveInfo;
	}

	public void setRoadEmpName(String roadEmpName) {
		this.roadEmpName = roadEmpName;
	}

	public void setTierEmpName(String tierEmpName) {
		this.tierEmpName = tierEmpName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getTeamManagerEmpName() {
		return teamManagerEmpName;
	}

	public void setTeamManagerEmpName(String teamManagerEmpName) {
		this.teamManagerEmpName = teamManagerEmpName;
	}

	public String getSaleManEmpName() {
		return saleManEmpName;
	}

	public void setSaleManEmpName(String saleManEmpName) {
		this.saleManEmpName = saleManEmpName;
	}

	
}
