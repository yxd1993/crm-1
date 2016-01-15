package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

public class UserScopeEntryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2723054399284005572L;

	/**
	 * 主表实体
	 */
	private String parentid;
	
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * 标记经度
	 */
	private double lan;
	/**
	 * 标记纬度
	 * */
	private double lat;
	
	/**
	 * 排序规则
	 * */
	private int sort;
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public double getLan() {
		return lan;
	}
	public void setLan(double lan) {
		this.lan = lan;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}

}
