package com.hoau.crm.module.customer.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 序列号实体
 * @author: 何斌
 * @create: 2015年5月28日 上午8:36:24
 */
public class SequenceNoEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6650536744003515627L;
	
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 时间 
	 */
	private Date time;
	/**
	 * 序号
	 */
	private Integer seqNo;
	/**
	 * 是否有效
	 */
	private String active;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
}
