package com.hoau.crm.module.sales.api.shared.domain;

import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author 275636 意向列表VO
 * @modifyuser 丁勇
 * @modifydate 2015年6月19日
 */
public class IntentionEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 客户ID
	 */
	private CustomerEntity customerEntity;
	/**
	 * 联系人id
	 */
	private ContactEntity contactEntity;
	/**
	 * 主要货物名称
	 */
	private String goodsName;
	/**
	 * 主要包装方式
	 */
	private String packaging;
	/**
	 * 客户信用评分
	 */
	private String customerScore;
	/**
	 * 客户最期望的营销活动类型
	 */
	private String activityType;
	/**
	 * 客户最期望的营销活动详述
	 */

	private String activityRemarks;
	/**
	 * 删除原因
	 */
	private String delDesc;
	/**
	 * 是否有效
	 */
	private String active;

	/**
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @return the activityRemarks
	 */
	public String getActivityRemarks() {
		return activityRemarks;
	}

	/**
	 * @return the activityType
	 */
	public String getActivityType() {
		return activityType;
	}

	/**
	 * @return the contactEntity
	 */
	public ContactEntity getContactEntity() {
		return contactEntity;
	}

	/**
	 * @return the customerEntity
	 */
	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	/**
	 * @return the customerScore
	 */
	public String getCustomerScore() {
		return customerScore;
	}

	/**
	 * @return the delDesc
	 */
	public String getDelDesc() {
		return delDesc;
	}

	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @return the packaging
	 */
	public String getPackaging() {
		return packaging;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * @param activityRemarks
	 *            the activityRemarks to set
	 */
	public void setActivityRemarks(String activityRemarks) {
		this.activityRemarks = activityRemarks;
	}

	/**
	 * @param activityType
	 *            the activityType to set
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	/**
	 * @param contactEntity
	 *            the contactEntity to set
	 */
	public void setContactEntity(ContactEntity contactEntity) {
		this.contactEntity = contactEntity;
	}

	/**
	 * @param customerEntity
	 *            the customerEntity to set
	 */
	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	/**
	 * @param customerScore
	 *            the customerScore to set
	 */
	public void setCustomerScore(String customerScore) {
		this.customerScore = customerScore;
	}

	/**
	 * @param delDesc
	 *            the delDesc to set
	 */
	public void setDelDesc(String delDesc) {
		this.delDesc = delDesc;
	}

	/**
	 * @param goodsName
	 *            the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @param packaging
	 *            the packaging to set
	 */
	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
}
