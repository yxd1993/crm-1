package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

public class QueryCreateSql extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String num;//序号
	private String tablenum;//表名
	private String tablename;//中文名
	private String createSql;//创建SQL
	private String remark;//备注
	private String createType;//创建类型
	private String createTypeName;//创建名称
	private String execTime;//执行时间
	private String lastTime;//上次执行时间
	private String alter;//修改表或者存储过程
	public String getAlter() {
		return alter;
	}
	public void setAlter(String alter) {
		this.alter = alter;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTablenum() {
		return tablenum;
	}
	public void setTablenum(String tablenum) {
		this.tablenum = tablenum;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getCreateSql() {
		return createSql;
	}
	public void setCreateSql(String createSql) {
		this.createSql = createSql;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateType() {
		return createType;
	}
	public void setCreateType(String createType) {
		this.createType = createType;
	}
	public String getCreateTypeName() {
		return createTypeName;
	}
	public void setCreateTypeName(String createTypeName) {
		this.createTypeName = createTypeName;
	}
	public String getExecTime() {
		return execTime;
	}
	public void setExecTime(String execTime) {
		this.execTime = execTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
}
