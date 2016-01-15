/**
 * 
 */
package com.hoau.crm.module.appcore.api.shared.domain;

/**
 * 报表接口
 * 
 * @author: 何斌
 * @create: 2015年7月17日 上午9:27:26
 */
public class ReportDataQueryParamEntity {
	/**
	 * 报表类型
	 */
	private String reportType;
	
	/**
	 * 报表子类型
	 */
	private String reportChildType;
	
	/**
	 * 账号
	 */
	private String account;

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportChildType() {
		return reportChildType;
	}

	public void setReportChildType(String reportChildType) {
		this.reportChildType = reportChildType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
