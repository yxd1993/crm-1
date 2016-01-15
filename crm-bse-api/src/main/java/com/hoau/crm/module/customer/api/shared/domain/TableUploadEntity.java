package com.hoau.crm.module.customer.api.shared.domain;

import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 上传实体
 * @author: 何斌
 * @create: 2015年5月26日 上午10:03:40
 */
public class TableUploadEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8785779951831612144L;
	/**
	 *表名 
	 */
	private String tableName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 字段集合
	 */
	private List<TableUploadDetailEntity> columns;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
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
	public List<TableUploadDetailEntity> getColumns() {
		return columns;
	}
	public void setColumns(List<TableUploadDetailEntity> columns) {
		this.columns = columns;
	}
}
