package com.hoau.crm.module.sales.api.shared.vo;

import java.io.Serializable;

import com.hoau.crm.module.sales.api.shared.domain.IntentionEntity;

/**
 * 
 * @modifyuser 丁勇
 * @modifydate 2015年6月19日
 */
public class IntentionVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 联系人姓名
	 */
	private String linkanName;
	/**
	 * 客户企业全称
	 */
	private String enterpriseName;
	/**
	 * 客户信用评分
	 */
	private String creditcustomer;
	/**
	 * 意向信息
	 */
	private IntentionEntity intentionEntity;

	public String getCreditcustomer() {
		return creditcustomer;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	/**
	 * @return the intentionEntity
	 */
	public IntentionEntity getIntentionEntity() {
		return intentionEntity;
	}

	public String getLinkanName() {
		return linkanName;
	}

	public void setCreditcustomer(String creditcustomer) {
		this.creditcustomer = creditcustomer;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	/**
	 * @param intentionEntity
	 *            the intentionEntity to set
	 */
	public void setIntentionEntity(IntentionEntity intentionEntity) {
		this.intentionEntity = intentionEntity;
	}

	public void setLinkanName(String linkanName) {
		this.linkanName = linkanName;
	}
}
