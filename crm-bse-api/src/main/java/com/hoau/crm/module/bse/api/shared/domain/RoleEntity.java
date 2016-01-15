package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Collection;

import com.hoau.hbdp.framework.entity.BaseEntity;
import com.hoau.hbdp.framework.entity.IRole;

/**
 * 角色信息实体
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class RoleEntity extends BaseEntity implements IRole{
	
	private static final long serialVersionUID = 9028743382362598259L;

	//角色编码
	private String roleCode;
	
	//角色名称
	private String roleName;

	//角色描述
	private String roleDesc;
	
	//是否有效
	private String active;
	
	//新增的FunctionCode
    private String[] resourceCodes;
    
	//删除的FunctionCode
    private String[] deleteResourceCodes;
	
	//功能对象ID
	private Collection<String> functionIds;
	
	//是否默认
	private String isDefault;
	
	public String getRoleCode() {
		return roleCode;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getActive() {
		return active;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setActive(String active) {
		this.active = active;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getRoleName() {
		return this.roleName;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getRoleDesc() {
		return this.roleDesc;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */	
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	@Override
	public Collection<String> getFunctionIds() {
		return this.functionIds;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setFunctionIds(Collection<String> functionIds){
		this.functionIds = functionIds;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String[] getResourceCodes() {
			return resourceCodes;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setResourceCodes(String[] resourceCodes) {
			this.resourceCodes = resourceCodes;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String[] getDeleteResourceCodes() {
			return deleteResourceCodes;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setDeleteResourceCodes(String[] deleteResourceCodes) {
			this.deleteResourceCodes = deleteResourceCodes;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}
