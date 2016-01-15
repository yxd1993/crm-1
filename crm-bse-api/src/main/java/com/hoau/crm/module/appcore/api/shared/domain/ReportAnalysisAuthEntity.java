package com.hoau.crm.module.appcore.api.shared.domain;

import java.util.Date;

/**
 * 自定义报表组织类型权限实体
 * 
 * @author: 何斌
 * @create: 2015年9月8日 下午5:01:32
 */
public class ReportAnalysisAuthEntity {

	/**
	 * 主键
	 */
	private String id;
	/**
	 *  角色编号
	 */
	private String roleCode;
	/**
	 * 功能id
	 */
	private String functionCode;
	/**
	 * 功能名称
	 */
	private String functionName;
	/**
	 * 排序
	 */
	private String sort;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 是否有效
	 */
	private String active;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
