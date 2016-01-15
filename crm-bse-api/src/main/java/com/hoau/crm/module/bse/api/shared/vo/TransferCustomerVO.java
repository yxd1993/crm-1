package com.hoau.crm.module.bse.api.shared.vo;

import java.util.List;

/**
 * 转让客户VO
 * 
 * @author: 何斌
 * @create: 2015年12月3日 上午10:56:03
 */
public class TransferCustomerVO {
	
	/**
	 * 客户id集合
	 */
	private List<String> ids;
	/**
	 * 新负责人工号
	 */
	private String newManagerCode;
	
	/**
	 * 客户类型(1-客户列表,2-共享客户) 
	 */
	private String customerType;
	
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public String getNewManagerCode() {
		return newManagerCode;
	}
	public void setNewManagerCode(String newManagerCode) {
		this.newManagerCode = newManagerCode;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
}
