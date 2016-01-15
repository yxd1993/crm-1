package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntity;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntryEntity;

/**
 *服务范围DAO
 * @author: 275636
 */
@Repository
public interface UserScopeMapper {
	/**
	 * @param userID
	 * @return
	 * 获取用户标记坐标 
	 */
	public List<UserScopeEntryEntity> queryUserScopeEntryInfo(String userID);
	/**
	 * 添加用户范围
	 * @param userScopeEntity
	 */
	public void addUserScopeInfo(UserScopeEntity userScopeEntity);
	/**
	 * 批量添加用户范围坐标
	 * @param scopeEntryEntities
	 */
	public void addUserScopeEntryInfo(List<UserScopeEntryEntity> scopeEntryEntities);
	/**
	 * 删除用户范围
	 * @param map
	 */
	public void delUserScopeInfo(Map<String, Object> map);
	/**
	 * 删除用户范围坐标
	 * @param userID
	 */
	public void delUserScopeEntryInfo(String userID);
	/**
	 * @param userID
	 * @return
	 * 获取用户服务范围
	 */
	public UserScopeEntity queryUserScopeInfo(String userID);
	
	/**
	 * @param map
	 * @return
	 * 得到用户的所有范围
	 */
	public List<UserScopeEntity> queryCustomerByUserScopeInfo();
	
	/**
	 * @param ids
	 * @return
	 * 根据用户ID获得门店产值最高的的用户
	 */
	public String getMaxStoresOutputUsers(List<String> ids);
	
	/**
	 * @param ids
	 * @return
	 * 根据用户ID获得客户经理产值最高的的用户
	 */
	public String getMaxSalesManagerOutputUsers(List<String> ids);
}
