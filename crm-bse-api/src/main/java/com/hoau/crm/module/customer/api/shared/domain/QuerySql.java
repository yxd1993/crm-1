package com.hoau.crm.module.customer.api.shared.domain;


public class QuerySql extends TimeCondition{

	
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -7058291696783535856L;
	
//	private String id;//ID
	private String number;//序列号
	private String sql;//SQL
	private String tableHead;//表头
	private String param;//参数
	private String remark;//描述
    private String roles;
    private String myColumn;  //json格式列名
	 
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getTableHead() {
		return tableHead;
	}
	public void setTableHead(String tableHead) {
		this.tableHead = tableHead;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getMyColumn() {
        return myColumn;
    }
    public void setMyColumn(String myColumn) {
        this.myColumn = myColumn;
    }
	 
	 
}
