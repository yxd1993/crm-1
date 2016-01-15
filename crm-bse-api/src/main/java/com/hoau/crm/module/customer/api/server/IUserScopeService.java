package com.hoau.crm.module.customer.api.server;

import java.util.List;
import java.util.Map;

import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntity;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntryEntity;
import com.hoau.hbdp.framework.service.IService;

/**
 * @author 275636
 *用户服务范围接口
 */
public interface IUserScopeService extends IService {
	/**
	 * @param userID
	 * @return
	 * 获取用户标记坐标 
	 */
	public List<UserScopeEntryEntity> queryUserScopeEntryInfo(String userID);
	/**
	 * @param userID
	 * @return
	 * 获取用户服务范围
	 */
	public UserScopeEntity queryUserScopeInfo(String userID);
	/**
	 * @param userScopeEntity
	 * 新增用户范围
	 */
	public void addUserScopeInfo(UserScopeEntity userScopeEntity);
	/**
	 * @param scopeEntryEntities
	 * 新增用户坐标
	 */
	public void addUserScopeEntryInfo(List<UserScopeEntryEntity> scopeEntryEntities);
	/**
	 * @param userID
	 * 删除用户范围
	 */
	public void delUserScopeInfo(String userID);
	/**
	 * @param parentid
	 * 删除用户坐标
	 */
	public void delUserScopeEntryInfo(String parentid);
	
	/**
	 * @param map
	 * @return
	 * 根据一个客户坐标确认属于某个用户范围
	 */
	public List<UserScopeEntity> queryCustomerByUserScopeInfo(Map<String,String> map);
}
