package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.RoleEntity;

/**
 * 角色SERVICE层接口
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
public interface IRoleService {

	/**
	 * 分页查询角色信息
	 * 
	 * @param role
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<RoleEntity> queryRoleList(RoleEntity role, RowBounds rb);

	/**
	 * 查询分页信息总数
	 * 
	 * @param role
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public long countRole(RoleEntity role);

	/**
	 * 新增角色信息
	 * 
	 * @param role
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public void addRole(RoleEntity role);

	/**
	 * 修改角色 信息
	 * 
	 * @param role
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public void updateRole(RoleEntity role);

	/**
	 * 删除角色信息
	 * 
	 * @param role
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public void deleteRole(List<String> roles);

	/**
	 * 根据角色ID查询角色信息
	 * 
	 * @param roleId
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public RoleEntity getRoleById(RoleEntity role);
	
	/**
	 * 新增角色权限信息
	 * 
	 * @param role
	 * @param funCodes
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public void insertRoleFun(RoleEntity role, List<String > funCodes);

	/**
	 * 查询待分配的角色信息
	 * 
	 * @param userCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-28
	 * @update
	 */
	public List<RoleEntity> getLeftRoles(String userCode);

	/**
	 * 查询已分配的角色信息
	 * 
	 * @param userCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-28
	 * @update
	 */
	public List<RoleEntity> getAuthedRoles(String userCode);
	
	/**
	 * 根据角色编码查询权限信息
	 * 
	 * @param roleCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-29
	 * @update
	 */
	List<String> queryAllFunctionIdByRoleId(String roleCode);
}
