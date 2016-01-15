package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;


/**
 * 用户信息SERVICE层接口
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public interface IUserService{
	
	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param userName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-14
	 * @update
	 */
	public UserEntity queryUserByUserName(String userName);
	
	/**
	 * 新增用户角色信息
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public void insertUserRole(UserEntity user, List<String > roleCodes);
	
	/**
	 * 分页查询用户信息
	 * 
	 * @param user
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<UserEntity> getUserList(UserEntity user, List<String> roleCodes, RowBounds rb);
	
	/**
	 * 统计用户总数
	 * 
	 * @param user
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public long countUser(UserEntity user, List<String> roleCodes);
	
}
