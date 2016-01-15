package com.hoau.crm.module.bse.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.RoleEntity;

/**
 * 角色DAO
 */
@Repository
public interface RoleMapper {

	/**
	 * 新增角色信息
	 * 
	 * @param role
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public void insert(RoleEntity role);

	/**
	 * 分页查询角色信息
	 * 
	 * @param role
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<RoleEntity> getRoleList(RoleEntity role, RowBounds rb);

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
	 * 修改角色 信息
	 * 
	 * @param role
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public void update(RoleEntity role);

	/**
	 * 删除角色信息
	 * 
	 * @param role
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public void delete(RoleEntity role);

	/**
	 * 根据角色ID查询角色信息
	 * 
	 * @param roleId
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public RoleEntity getRoleById(String roleId);
	
	/**
	 * 查询用户待分配的角色
	 * 
	 * @param userCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-28
	 * @update
	 */
	public List<RoleEntity> getLeftRoles(String userCode);

	/**
	 * 查询用户Code查询该用户已经分配的角色
	 * 
	 * @param userCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-28
	 * @update
	 */
	public List<RoleEntity> getAuthedRoles(String userCode);
	
	/**
	 * 是否重名判断
	 * 
	 * @param role
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-29
	 * @update
	 */
	public long isAllowRole(RoleEntity role);
}
