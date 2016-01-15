package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 每日查询实体
 * 
 * @author: 何斌
 * @create: 2015年12月24日 下午3:26:55
 */
public class DailyQueryEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -455633540238009628L;
	/**
	 * 排序规则
	 */
	private String sortType;
	/**
	 * 创建时间查询
	 */
	private Date queryCreateTime;
	
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public Date getQueryCreateTime() {
		return queryCreateTime;
	}
	public void setQueryCreateTime(Date queryCreateTime) {
		this.queryCreateTime = queryCreateTime;
	}
	
}
