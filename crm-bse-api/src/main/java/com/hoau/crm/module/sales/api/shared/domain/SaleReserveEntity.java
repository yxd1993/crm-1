package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;

import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 销售管理客户预约实体类
 * 
 * @author 丁勇
 * @date 2015年6月9日
 */
public class SaleReserveEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 7339353903452235137L;

	/**
	 * 客户id
	 */
	private CustomerEntity customerEntity;

	/**
	 * 联系人id
	 */
	private ContactEntity contactEntity;
	/**
	 * 创建人
	 */
	private EmployeeEntity employeeEntity;
	/**
	 * 预约开始时间
	 */
	private Date reserveStartTime;

	/**
	 * 预约结束时间
	 */
	private Date reserveEndTime;

	/**
	 * 提醒时间
	 */
	private String warningTime;
	/**
	 * 预约方式
	 */
	private String reserveType;
	/**
	 * 门店负责人
	 */
	private String comTierEmpCode;
	/**
	 * 路区负责人
	 */
	private String comRoadEmpCode;

	/**
	 * 大区负责人
	 */
	private String comRegionsEmpCode;

	/**
	 * 事业部负责人
	 */
	private String comBusinessEmpCode;
	/**
	 * 营运副总
	 */
	private String comOdEmpCode;
	/**
	 * 集团总裁
	 */
	private String comCeoEmpCode;
	/**
	 *团队负责人
	 */
	private String teamManagerEmpCode;
	/**
	 * 客户负责人
	 */
	private String saleManEmpCode;
	/**
	 * 预约主题
	 */
	private String reserveTheme;
	/**
	 * 预约内容
	 */
	private String reserveContents;
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 删除备注信息
	 */
	private String delDesc;
	/**
	 * 预约装态
	 */
	public String isAgreement;

	public String getActive() {
		return active;
	}

	public String getComBusinessEmpCode() {
		return comBusinessEmpCode;
	}

	public String getComCeoEmpCode() {
		return comCeoEmpCode;
	}

	public String getComOdEmpCode() {
		return comOdEmpCode;
	}

	public String getComRegionsEmpCode() {
		return comRegionsEmpCode;
	}

	public String getComRoadEmpCode() {
		return comRoadEmpCode;
	}
	public String getComTierEmpCode() {
		return comTierEmpCode;
	}
	public String getTeamManagerEmpCode() {
		return teamManagerEmpCode;
	}

	public void setTeamManagerEmpCode(String teamManagerEmpCode) {
		this.teamManagerEmpCode = teamManagerEmpCode;
	}

	public ContactEntity getContactEntity() {
		return contactEntity;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public String getDelDesc() {
		return delDesc;
	}

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public String getIsAgreement() {
		return isAgreement;
	}
	public String getReserveContents() {
		return reserveContents;
	}
	public Date getReserveEndTime() {
		return reserveEndTime;
	}

	public Date getReserveStartTime() {
		return reserveStartTime;
	}

	public String getReserveTheme() {
		return reserveTheme;
	}

	public String getReserveType() {
		return reserveType;
	}
	public String getWarningTime() {
		return warningTime;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setComBusinessEmpCode(String comBusinessEmpCode) {
		this.comBusinessEmpCode = comBusinessEmpCode;
	}

	public void setComCeoEmpCode(String comCeoEmpCode) {
		this.comCeoEmpCode = comCeoEmpCode;
	}

	public void setComOdEmpCode(String comOdEmpCode) {
		this.comOdEmpCode = comOdEmpCode;
	}

	public void setComRegionsEmpCode(String comRegionsEmpCode) {
		this.comRegionsEmpCode = comRegionsEmpCode;
	}

	public void setComRoadEmpCode(String comRoadEmpCode) {
		this.comRoadEmpCode = comRoadEmpCode;
	}

	public void setComTierEmpCode(String comTierEmpCode) {
		this.comTierEmpCode = comTierEmpCode;
	}

	public void setContactEntity(ContactEntity contactEntity) {
		this.contactEntity = contactEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}
	public void setDelDesc(String delDesc) {
		this.delDesc = delDesc;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}
	public void setIsAgreement(String isAgreement) {
		this.isAgreement = isAgreement;
	}

	public void setReserveContents(String reserveContents) {
		this.reserveContents = reserveContents;
	}

	public void setReserveEndTime(Date reserveEndTime) {
		this.reserveEndTime = reserveEndTime;
	}

	public void setReserveStartTime(Date reserveStartTime) {
		this.reserveStartTime = reserveStartTime;
	}

	public void setReserveTheme(String reserveTheme) {
		this.reserveTheme = reserveTheme;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}

	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}

	public String getSaleManEmpCode() {
		return saleManEmpCode;
	}

	public void setSaleManEmpCode(String saleManEmpCode) {
		this.saleManEmpCode = saleManEmpCode;
	}
	
}
