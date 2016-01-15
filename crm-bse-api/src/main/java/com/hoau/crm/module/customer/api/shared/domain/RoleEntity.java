package com.hoau.crm.module.customer.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 
 * @package com.deppon.pda.module.bamCode.shared.domain
 * @file RoleEntity.java
 * @description 角色实体类
 * @author ChenLiang
 * @created 2012-9-26 上午11:48:33
 * @version 1.0
 */
public class RoleEntity extends BaseEntity {

	private static final long serialVersionUID = -1812547734969152639L;

	/**
	 * id
	 */
	private String id;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色标准编码
	 */
	private String roleBasNumber;

	/**
	 * 角色描述
	 */
	private String roleDesc;
	
	/**
	 * 用户是否拥有该角色
	 */
	private String status;
	
	/**
	 * 角色类型
	 */
	private String roleType;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleBasNumber() {
		return roleBasNumber;
	}

	public void setRoleBasNumber(String roleBasNumber) {
		this.roleBasNumber = roleBasNumber;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

}
