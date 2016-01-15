package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 上传明细实体
 * @author: 何斌
 * @create: 2015年5月26日 上午9:52:25
 */
public class TableUploadDetailEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2728346787912417334L;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 字段名
	 */
	private String columnName;
	/**
	 * 字段类型
	 */
	private String columnType;
	/**
	 * 字段顺序
	 */
	private Integer sortNumber;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否有效
	 */
	private String active;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
