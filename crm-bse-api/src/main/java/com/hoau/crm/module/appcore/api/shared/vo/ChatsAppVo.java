/**
 * 
 */
package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;
import java.util.Map;

import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;

/**
 * 洽谈vo信息
 * 
 * @author 丁勇
 * @date 2015年7月8日
 */
public class ChatsAppVo {
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
	 * 关联预约信息
	 */
	private String reserveInfo;
	/**
	 * 洽谈实体
	 */
	private SaleChatsEntity chatsEntity;
	
	/**
	 * 签到信息VO
	 */
	private SignVo signVo;
	
	/**
	 *签到扫描陪同人工号列表
	 */
	private List<Map<String,Object>> empCodeList;
	
	public String getTierEmpName() {
		return tierEmpName;
	}

	public void setTierEmpName(String tierEmpName) {
		this.tierEmpName = tierEmpName;
	}

	public String getTeamManagerEmpName() {
		return teamManagerEmpName;
	}

	public void setTeamManagerEmpName(String teamManagerEmpName) {
		this.teamManagerEmpName = teamManagerEmpName;
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

	public String getOdEmpName() {
		return odEmpName;
	}

	public void setOdEmpName(String odEmpName) {
		this.odEmpName = odEmpName;
	}

	public String getCeoEmpName() {
		return ceoEmpName;
	}

	public void setCeoEmpName(String ceoEmpName) {
		this.ceoEmpName = ceoEmpName;
	}

	public String getSaleManEmpName() {
		return saleManEmpName;
	}

	public void setSaleManEmpName(String saleManEmpName) {
		this.saleManEmpName = saleManEmpName;
	}

	public SaleChatsEntity getChatsEntity() {
		return chatsEntity;
	}

	public void setChatsEntity(SaleChatsEntity chatsEntity) {
		this.chatsEntity = chatsEntity;
	}

	public String getReserveInfo() {
		return reserveInfo;
	}

	public void setReserveInfo(String reserveInfo) {
		this.reserveInfo = reserveInfo;
	}

	public SignVo getSignVo() {
		return signVo;
	}

	public void setSignVo(SignVo signVo) {
		this.signVo = signVo;
	}

	public List<Map<String, Object>> getEmpCodeList() {
		return empCodeList;
	}

	public void setEmpCodeList(List<Map<String, Object>> empCodeList) {
		this.empCodeList = empCodeList;
	}
}
