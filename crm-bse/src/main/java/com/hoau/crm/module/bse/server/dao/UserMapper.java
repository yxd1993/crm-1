package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

/**
 * 用户信息DAO
 */
@Repository
public interface UserMapper {

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @param userName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-14
	 * @update
	 */
	public UserEntity getUserByUserName(String userName);
	
	/**
	 * 分页查询用户信息
	 * 
	 * @param user
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<UserEntity> getUserList(Map<String, String> map, RowBounds rb);
	
	/**
	 * 统计用户总数
	 * 
	 * @param user
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public long countUser(Map<String, String> map);
}
