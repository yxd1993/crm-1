package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.ReportAnalysisAuthEntity;
import com.hoau.crm.module.appcore.api.shared.domain.ReportAnalysisEntity;

/**
 * 自定义报表实体VO
 * @author: 何斌
 * @create: 2015年9月8日 上午10:56:25
 */
public class ReportAnalysisAppVo {
	
	/**
	 * 时间类型
	 */
	private String timeType;
	/**
	 * 组织类型
	 */
	private String orgType;
	/**
	 * 数据类型
	 */
	private String dataTypeBySort;
	/**
	 * 组织编码
	 */
	private String orgCode;
	/**
	 * 每页数据量
	 */
	private int limit;
	/**
	 * 分布起始位置
	 */
	private int start;
	

	/**
	 * 分页数据总长度
	 */
	private long totalCount;
	
	/**
	 * 数据明细
	 */
	private List<ReportAnalysisEntity> reportAnalysisEntities;
	
	/**
	 * 组织权限菜单
	 */
	private List<ReportAnalysisAuthEntity> reportAnalysisAuthEntities;
	
	/**
	 * 是否返回
	 */
	private String isReturn;
	
	/**
	 * 返回图标是否隐藏
	 */
	private String isHidden;
	
	/**
	 * 上级部门编码
	 */
	private String supOrgCode;
	
	/**
	 * 上级组织类型
	 */
	private String supOrgType;

	/**
	 * 是否顶部
	 */
	private String isHead;
	
	/**
	 * 第一个排序号
	 */
	private String firstSort;
	
	/**
	 * 组织名称
	 */
	private String orgName;
	
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getDataTypeBySort() {
		return dataTypeBySort;
	}
	public void setDataTypeBySort(String dataTypeBySort) {
		this.dataTypeBySort = dataTypeBySort;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public List<ReportAnalysisEntity> getReportAnalysisEntities() {
		return reportAnalysisEntities;
	}
	public void setReportAnalysisEntities(
			List<ReportAnalysisEntity> reportAnalysisEntities) {
		this.reportAnalysisEntities = reportAnalysisEntities;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public List<ReportAnalysisAuthEntity> getReportAnalysisAuthEntities() {
		return reportAnalysisAuthEntities;
	}
	public void setReportAnalysisAuthEntities(
			List<ReportAnalysisAuthEntity> reportAnalysisAuthEntities) {
		this.reportAnalysisAuthEntities = reportAnalysisAuthEntities;
	}
	public String getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}
	public String getSupOrgCode() {
		return supOrgCode;
	}
	public void setSupOrgCode(String supOrgCode) {
		this.supOrgCode = supOrgCode;
	}
	public String getIsHead() {
		return isHead;
	}
	public void setIsHead(String isHead) {
		this.isHead = isHead;
	}
	public String getSupOrgType() {
		return supOrgType;
	}
	public void setSupOrgType(String supOrgType) {
		this.supOrgType = supOrgType;
	}
	public String getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}
	public String getFirstSort() {
		return firstSort;
	}
	public void setFirstSort(String firstSort) {
		this.firstSort = firstSort;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
