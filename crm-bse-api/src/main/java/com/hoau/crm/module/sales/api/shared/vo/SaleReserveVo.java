/**
 * 
 */
package com.hoau.crm.module.sales.api.shared.vo;

import java.io.Serializable;

import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;

/**
 * 预约vo
 * 
 * @author 丁勇
 * @date 2015年6月10日
 */
public class SaleReserveVo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -18319596868725543L;

	/**
	 * 客户id
	 */
	private String accountId;
	/**
	 * 预约id
	 */
	private String reserveId;
	/**
	 * 预约信息
	 */
	private String reserveInfo;

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
	 * 预约信息实体
	 */
	private SaleReserveEntity reserveEntity;
	

	public String getAccountId() {
		return accountId;
	}

	public String getBusinessUnitEmpName() {
		return businessUnitEmpName;
	}

	public String getCeoEmpName() {
		return ceoEmpName;
	}

	public String getOdEmpName() {
		return odEmpName;
	}

	public String getRegionsEmpName() {
		return regionsEmpName;
	}

	public SaleReserveEntity getReserveEntity() {
		return reserveEntity;
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
	
	public String getTeamManagerEmpName() {
		return teamManagerEmpName;
	}

	public void setTeamManagerEmpName(String teamManagerEmpName) {
		this.teamManagerEmpName = teamManagerEmpName;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setBusinessUnitEmpName(String businessUnitEmpName) {
		this.businessUnitEmpName = businessUnitEmpName;
	}

	public void setCeoEmpName(String ceoEmpName) {
		this.ceoEmpName = ceoEmpName;
	}

	public void setOdEmpName(String odEmpName) {
		this.odEmpName = odEmpName;
	}

	public void setRegionsEmpName(String regionsEmpName) {
		this.regionsEmpName = regionsEmpName;
	}

	public void setReserveEntity(SaleReserveEntity reserveEntity) {
		this.reserveEntity = reserveEntity;
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

	public String getSaleManEmpName() {
		return saleManEmpName;
	}

	public void setSaleManEmpName(String saleManEmpName) {
		this.saleManEmpName = saleManEmpName;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

}
