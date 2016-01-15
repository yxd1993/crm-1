package com.hoau.crm.module.sales.api.shared.vo;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author: 何斌
 * @create: 2015年6月11日 下午4:40:21
 */
public class SaleContractVo extends BaseEntity{

	private static final long serialVersionUID = -3317399566971051252L;
	
	/**
	 * 查询开始时间1
	 */
	private Date firstStartDate;
	
	/**
	 * 查询结束时间1
	 */
	private Date firstEndDate; 
	
	/**
	 * 查询开始时间2
	 */
	private Date secondStartDate;
	
	/**
	 * 查询结束时间2
	 */
	private Date secondEndDate; 
	
	/**
	 * 合同状态
	 */
	private String status;
	/**
	 * 客户全称
	 */
	private String enterpriseName;
	
	/**
	 * 合同开始日期
	 */
	private Date contractStartDate;
	
	/**
	 * 合同结束日期
	 */
	private Date contractEndDate;
	
	/**
	 * 流水号ID
	 */
	private String workflowCode;
	
	/**
	 * CRM账号
	 */
	private String crmAccount;
	
	/**
	 * 申请日期
	 */
	private Date applyDate;
	
	/**
	 * DC账号 
	 */
	private String dcAccount;
	
	/**
	 * 合同归档时间
	 */
	private Date fileDate;
	
	/**
	 * 门店
	 */
	private String applyUserDeclareName;
	
	/**
	 * 路区
	 */
	private String road;
	
	/**
	 * 大区
	 */
	private String regions;
	
	/**
	 * 事业部
	 */
	private String businessUnit;
	
	/**
	 * 附件URL 
	 */
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDcAccount() {
		return dcAccount;
	}

	public void setDcAccount(String dcAccount) {
		this.dcAccount = dcAccount;
	}

	public Date getFirstStartDate() {
		return firstStartDate;
	}

	public void setFirstStartDate(Date firstStartDate) {
		this.firstStartDate = firstStartDate;
	}

	public Date getFirstEndDate() {
		return firstEndDate;
	}

	public void setFirstEndDate(Date firstEndDate) {
		this.firstEndDate = firstEndDate;
	}

	public Date getSecondStartDate() {
		return secondStartDate;
	}

	public void setSecondStartDate(Date secondStartDate) {
		this.secondStartDate = secondStartDate;
	}

	public Date getSecondEndDate() {
		return secondEndDate;
	}

	public void setSecondEndDate(Date secondEndDate) {
		this.secondEndDate = secondEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getWorkflowCode() {
		return workflowCode;
	}

	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode;
	}

	public String getCrmAccount() {
		return crmAccount;
	}

	public void setCrmAccount(String crmAccount) {
		this.crmAccount = crmAccount;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getRegions() {
		return regions;
	}

	public void setRegions(String regions) {
		this.regions = regions;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getApplyUserDeclareName() {
		return applyUserDeclareName;
	}

	public void setApplyUserDeclareName(String applyUserDeclareName) {
		this.applyUserDeclareName = applyUserDeclareName;
	}

}
