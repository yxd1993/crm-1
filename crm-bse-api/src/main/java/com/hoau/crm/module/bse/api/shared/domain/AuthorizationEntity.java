package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 授权信息ENTITY
 *
 * @author 蒋落琛
 * @date 2015-9-16
 */
public class AuthorizationEntity extends BaseEntity {

	private static final long serialVersionUID = -5279013229518305047L;
	
	/**
	 * 授权人工号
	 */
	private String authorizedPerson;
	
	/**
	 * 被授权人工号
	 */
	private String wasAuthorizedPerson;
	
	/**
	 * 授权开始时间
	 */
	private Date authorizedStartTime;
	
	/**
	 * 授权结束时间
	 */
	private Date authorizedEndTime;

	public String getAuthorizedPerson() {
		return authorizedPerson;
	}

	public void setAuthorizedPerson(String authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
	}

	public String getWasAuthorizedPerson() {
		return wasAuthorizedPerson;
	}

	public void setWasAuthorizedPerson(String wasAuthorizedPerson) {
		this.wasAuthorizedPerson = wasAuthorizedPerson;
	}

	public Date getAuthorizedStartTime() {
		return authorizedStartTime;
	}

	public void setAuthorizedStartTime(Date authorizedStartTime) {
		this.authorizedStartTime = authorizedStartTime;
	}

	public Date getAuthorizedEndTime() {
		return authorizedEndTime;
	}

	public void setAuthorizedEndTime(Date authorizedEndTime) {
		this.authorizedEndTime = authorizedEndTime;
	}
}