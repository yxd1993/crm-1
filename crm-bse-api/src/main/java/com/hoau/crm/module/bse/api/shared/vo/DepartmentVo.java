package com.hoau.crm.module.bse.api.shared.vo;

import java.io.Serializable;

import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;

/**
 * 部门信息VO
 * 
 * @author 蒋落琛
 * @date 2015-5-22
 */
public class DepartmentVo implements Serializable {

	private static final long serialVersionUID = -18319596868725543L;
	
	/**
	 * 当前门店编码
	 */
	private String tierCode;
	
	/**
	 * 当前门店名称
	 */
	private String tierName;
	
	/**
	 * 门店负责人显示名称
	 */
	private String tierDisplayName;
	
	/**
	 * 门店负责人工号
	 */
	private String tierEmpCode;
	
	/**
	 * 门店负责人姓名
	 */
	private String tierEmpName;
	
	/**
	 * 当前组织的路区编码
	 */
	private String roadAreaCode;
	
	/**
	 * 当前组织的路区名称
	 */
	private String roadAreaName;
	
	/**
	 * 路区负责人显示名称
	 */
	private String roadDisplayName;
	
	/**
	 *  路区负责人
	 */
	private String roadEmpCode;
	
	/**
	 * 路区负责人名称
	 */
	private String roadEmpName;
	
	/**
	 * 当前组织的大区编码
	 */
	private String regionsCode;
	
	/**
	 * 当前组织的大区名称
	 */
	private String regionsName;
	
	/**
	 * 大区负责人显示名称
	 */
	private String regionsDisplayName;
	
	/**
	 *  大区负责人
	 */
	private String regionsEmpCode;
	
	/**
	 *  大区负责人名称
	 */
	private String regionsEmpName;
	
	/**
	 * 当前组织的事业部编码
	 */
	private String businessUnitCode;
	
	/**
	 * 当前组织事业部名称
	 */
	private String businessUnitName;
	
	/**
	 * 事业部负责人显示名称
	 */
	private String businessUnitDisplayName;
	
	/**
	 *  事业部负责人
	 */
	private String businessUnitEmpCode;
	
	/**
	 *  事业部负责人名称
	 */
	private String businessUnitEmpName;
	
	/**
	 * 营运副总显示名称
	 */
	private String odDisplayName;
	
	/**
	 * 营运副总工号
	 */
	private String odEmpCode;
	
	/**
	 * 营运副总名称
	 */
	private String odEmpName;
	
	/**
	 * 总裁显示名称
	 */
	private String ceoDisplayName;
	
	/**
	 * 总裁工号
	 */
	private String ceoEmpCode;
	
	/**
	 * 总裁名称
	 */
	private String ceoEmpName;
	
	/**
	 * 团队经理显示名称
	 */
	private String teamManagerDisplayName;
	
	/**
	 * 团队经理工号
	 */
	private String teamManagerCode;
	
	/**
	 * 团队经理名称
	 */
	private String teamManagerName;
	
	/**
	 * 客户经理显示名称
	 */
	private String saleManDisplayName;
	
	/**
	 * 客户经理工号
	 */
	private String saleManEmpCode;
	
	/**
	 * 客户经理名称
	 */
	private String saleManEmpName;
	
	/**
	 * 部门公共选择器查询参数
	 */
	private String selectorParam;
	
	/**
	 * 部门信息
	 */
	private DepartmentEntity deptEntity;
	
	public DepartmentEntity getDeptEntity() {
		return deptEntity;
	}

	public void setDeptEntity(DepartmentEntity deptEntity) {
		this.deptEntity = deptEntity;
	}

	public String getSelectorParam() {
		return selectorParam;
	}

	public void setSelectorParam(String selectorParam) {
		this.selectorParam = selectorParam;
	}

	public String getRoadAreaCode() {
		return roadAreaCode;
	}

	public void setRoadAreaCode(String roadAreaCode) {
		this.roadAreaCode = roadAreaCode;
	}

	public String getRoadAreaName() {
		return roadAreaName;
	}

	public void setRoadAreaName(String roadAreaName) {
		this.roadAreaName = roadAreaName;
	}

	public String getRegionsCode() {
		return regionsCode;
	}

	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode;
	}

	public String getRegionsName() {
		return regionsName;
	}

	public void setRegionsName(String regionsName) {
		this.regionsName = regionsName;
	}

	public String getBusinessUnitCode() {
		return businessUnitCode;
	}

	public void setBusinessUnitCode(String businessUnitCode) {
		this.businessUnitCode = businessUnitCode;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getRoadEmpCode() {
		return roadEmpCode;
	}

	public void setRoadEmpCode(String roadEmpCode) {
		this.roadEmpCode = roadEmpCode;
	}

	public String getRegionsEmpCode() {
		return regionsEmpCode;
	}

	public void setRegionsEmpCode(String regionsEmpCode) {
		this.regionsEmpCode = regionsEmpCode;
	}

	public String getBusinessUnitEmpCode() {
		return businessUnitEmpCode;
	}

	public void setBusinessUnitEmpCode(String businessUnitEmpCode) {
		this.businessUnitEmpCode = businessUnitEmpCode;
	}

	public String getRoadEmpName() {
		return roadEmpName;
	}

	public void setRoadEmpName(String roadEmpName) {
		this.roadEmpName = roadEmpName;
	}

	public String getRegionsEmpName() {
		return regionsEmpName;
	}

	public void setRegionsEmpName(String regionsEmpName) {
		this.regionsEmpName = regionsEmpName;
	}

	public String getBusinessUnitEmpName() {
		return businessUnitEmpName;
	}

	public void setBusinessUnitEmpName(String businessUnitEmpName) {
		this.businessUnitEmpName = businessUnitEmpName;
	}

	public String getOdEmpCode() {
		return odEmpCode;
	}

	public void setOdEmpCode(String odEmpCode) {
		this.odEmpCode = odEmpCode;
	}

	public String getOdEmpName() {
		return odEmpName;
	}

	public void setOdEmpName(String odEmpName) {
		this.odEmpName = odEmpName;
	}

	public String getCeoEmpCode() {
		return ceoEmpCode;
	}

	public void setCeoEmpCode(String ceoEmpCode) {
		this.ceoEmpCode = ceoEmpCode;
	}

	public String getCeoEmpName() {
		return ceoEmpName;
	}

	public void setCeoEmpName(String ceoEmpName) {
		this.ceoEmpName = ceoEmpName;
	}

	public String getTierCode() {
		return tierCode;
	}

	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}

	public String getTierName() {
		return tierName;
	}

	public void setTierName(String tierName) {
		this.tierName = tierName;
	}

	public String getTierEmpCode() {
		return tierEmpCode;
	}

	public void setTierEmpCode(String tierEmpCode) {
		this.tierEmpCode = tierEmpCode;
	}

	public String getTierEmpName() {
		return tierEmpName;
	}

	public void setTierEmpName(String tierEmpName) {
		this.tierEmpName = tierEmpName;
	}

	public String getTeamManagerCode() {
		return teamManagerCode;
	}

	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
	}

	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}

	public String getTierDisplayName() {
		return tierDisplayName;
	}

	public void setTierDisplayName(String tierDisplayName) {
		this.tierDisplayName = tierDisplayName;
	}

	public String getRoadDisplayName() {
		return roadDisplayName;
	}

	public void setRoadDisplayName(String roadDisplayName) {
		this.roadDisplayName = roadDisplayName;
	}

	public String getRegionsDisplayName() {
		return regionsDisplayName;
	}

	public void setRegionsDisplayName(String regionsDisplayName) {
		this.regionsDisplayName = regionsDisplayName;
	}

	public String getBusinessUnitDisplayName() {
		return businessUnitDisplayName;
	}

	public void setBusinessUnitDisplayName(String businessUnitDisplayName) {
		this.businessUnitDisplayName = businessUnitDisplayName;
	}

	public String getOdDisplayName() {
		return odDisplayName;
	}

	public void setOdDisplayName(String odDisplayName) {
		this.odDisplayName = odDisplayName;
	}

	public String getCeoDisplayName() {
		return ceoDisplayName;
	}

	public void setCeoDisplayName(String ceoDisplayName) {
		this.ceoDisplayName = ceoDisplayName;
	}

	public String getTeamManagerDisplayName() {
		return teamManagerDisplayName;
	}

	public void setTeamManagerDisplayName(String teamManagerDisplayName) {
		this.teamManagerDisplayName = teamManagerDisplayName;
	}

	public String getSaleManDisplayName() {
		return saleManDisplayName;
	}

	public void setSaleManDisplayName(String saleManDisplayName) {
		this.saleManDisplayName = saleManDisplayName;
	}

	public String getSaleManEmpCode() {
		return saleManEmpCode;
	}

	public void setSaleManEmpCode(String saleManEmpCode) {
		this.saleManEmpCode = saleManEmpCode;
	}

	public String getSaleManEmpName() {
		return saleManEmpName;
	}

	public void setSaleManEmpName(String saleManEmpName) {
		this.saleManEmpName = saleManEmpName;
	}
}
