package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 角色权限信息DAO
 */
@Repository
public interface RoleFunctionMapper {

	/**
	 * 新增角色权限信息
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public void insertRoleFun(Map<String, String> map);
	
	/**
	 * 删除角色所有权限信息
	 * 
	 * @param role
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public void deleteRoleFunByRoleCode(String roleCode);
	
	/**
	 * 根据角色编码查询角色信息
	 * 
	 * @param roleId
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-29
	 * @update
	 */
	List<String> getAllIdByRoleId(String roleId);
}
