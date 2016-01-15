package com.hoau.crm.module.sales.api.shared.vo;

import java.util.Date;
import java.util.List;

import com.hoau.crm.module.sales.api.shared.domain.SaleChatRandomEntity;


/**
 *  洽谈回访随机抽取VO
 * 
 * @author: 何斌
 * @create: 2015年8月17日 上午11:31:30
 */
public class SaleChatRandomVo {
	
	/**
	 *创建开始时间
	 */
	private Date startDate;
	/**
	 *创建结束时间
	 */
	private Date endDate;
	
	/**
	 *id集合
	 */
	private List<String> ids;
	
	/**
	 *随机抽取洽谈实体
	 */
	private SaleChatRandomEntity saleChatRandomEntity;

	public SaleChatRandomEntity getSaleChatRandomEntity() {
		return saleChatRandomEntity;
	}

	public void setSaleChatRandomEntity(SaleChatRandomEntity saleChatRandomEntity) {
		this.saleChatRandomEntity = saleChatRandomEntity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}
