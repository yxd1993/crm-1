package com.hoau.crm.module.customer.api.shared.domain;

import java.io.Serializable;

/**
 * 官网注册实体
 * 
 * @author: 何斌
 * @create: 2015年12月23日 上午10:57:53
 */
public class RegisterEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8618739026791595085L;

	/**
	 * 基本信息
	 */
	private ObhUserEntity userEntity;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String cellphone;
	public ObhUserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(ObhUserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
}
