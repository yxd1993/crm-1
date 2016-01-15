package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;
import java.util.Map;

import com.hoau.crm.module.appcore.api.shared.domain.ReportDataQueryParamEntity;
import com.hoau.crm.module.sales.api.shared.domain.ReportCustomerCountEntity;
import com.hoau.crm.module.sales.api.shared.domain.ReportEmpWorkEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleIncomeAnalysisEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleIncomeDetailVo;

/**
 * 报表RESTFUL接口返回VO
 * 
 * @author: 何斌
 * @create: 2015年6月25日 下午5:22:41
 * @modify: 丁勇
 * @date 2015-07-21 09:03:37
 */
public class ReportDataAppVo {

	/**
	 *当前登录者
	 */
	private ReportEmpWorkEntity empEntity;
	/**
	 *报表参数
	 */
	private ReportDataQueryParamEntity queryParam;
	/**
	 *个人工作报表结果
	 */
	private List<ReportEmpWorkEntity> empworkList;
	
	/**
	 *工作详情报表
	 */
	private List<Map<String,Object>> empworkDetail;
	
	/**
	 *工作详情指标
	 */
	private List<Map<String,Object>> empworkIndex;
	
	/**
	 * 当月客户统计
	 */
	private Map<String,Object> countCustomerByEmp;
	/**
	 *统计分析报表结果
	 */
	private ReportCustomerCountEntity reportCustomerCountEntity;
	/**
	 *分页起始
	 */
	private int start;
	/**
	 *分页数量
	 */
	private int limit;
	/**
	 * 分页数据总长度
	 */
	private long totalCount;
	
	/**
	 * 销售收入分析
	 */
	private SaleIncomeAnalysisEntity saleIncomeAnalysisEntity;
	
	/**
	 * 销售收入明细
	 */
	private SaleIncomeDetailVo saleIncomeDetailVo;

	public ReportDataQueryParamEntity getQueryParam() {
		return queryParam;
	}

	public void setQueryParam(ReportDataQueryParamEntity queryParam) {
		this.queryParam = queryParam;
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

	public List<ReportEmpWorkEntity> getEmpworkList() {
		return empworkList;
	}

	public void setEmpworkList(List<ReportEmpWorkEntity> empworkList) {
		this.empworkList = empworkList;
	}

	public ReportCustomerCountEntity getReportCustomerCountEntity() {
		return reportCustomerCountEntity;
	}

	public void setReportCustomerCountEntity(
			ReportCustomerCountEntity reportCustomerCountEntity) {
		this.reportCustomerCountEntity = reportCustomerCountEntity;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public SaleIncomeAnalysisEntity getSaleIncomeAnalysisEntity() {
		return saleIncomeAnalysisEntity;
	}

	public void setSaleIncomeAnalysisEntity(
			SaleIncomeAnalysisEntity saleIncomeAnalysisEntity) {
		this.saleIncomeAnalysisEntity = saleIncomeAnalysisEntity;
	}

	public SaleIncomeDetailVo getSaleIncomeDetailVo() {
		return saleIncomeDetailVo;
	}

	public void setSaleIncomeDetailVo(SaleIncomeDetailVo saleIncomeDetailVo) {
		this.saleIncomeDetailVo = saleIncomeDetailVo;
	}

	public List<Map<String, Object>> getEmpworkDetail() {
		return empworkDetail;
	}

	public void setEmpworkDetail(List<Map<String, Object>> empworkDetail) {
		this.empworkDetail = empworkDetail;
	}

	public List<Map<String, Object>> getEmpworkIndex() {
		return empworkIndex;
	}

	public void setEmpworkIndex(List<Map<String, Object>> empworkIndex) {
		this.empworkIndex = empworkIndex;
	}

	public ReportEmpWorkEntity getEmpEntity() {
		return empEntity;
	}

	public void setEmpEntity(ReportEmpWorkEntity empEntity) {
		this.empEntity = empEntity;
	}

	public Map<String, Object> getCountCustomerByEmp() {
		return countCustomerByEmp;
	}

	public void setCountCustomerByEmp(Map<String, Object> countCustomerByEmp) {
		this.countCustomerByEmp = countCustomerByEmp;
	}


}
