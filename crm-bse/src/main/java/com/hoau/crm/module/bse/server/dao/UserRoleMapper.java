package com.hoau.crm.module.bse.server.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 用户角色信息DAO
 */
@Repository
public interface UserRoleMapper {

	/**
	 * 新增用户角色信息
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public void addUserRole(Map<String, String> map);
	
	/**
	 * 删除某个用户角色信息
	 * 
	 * @param userCode
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public void deleteUserRoleByUserCode(String userCode);
	
	/**
	 * 删除某个角色用户信息
	 * 
	 * @param roleCode
	 * @author 蒋落琛
	 * @date 2015-7-15
	 * @update
	 */
	public void deleteUserRoleByRoleCode(String roleCode);
}
