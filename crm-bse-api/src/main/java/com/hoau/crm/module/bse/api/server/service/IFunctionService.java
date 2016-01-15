package com.hoau.crm.module.bse.api.server.service;

import java.util.List;
import java.util.Set;

import com.hoau.crm.module.bse.api.shared.domain.FunctionEntity;

/**
 * 功能权限INTERFACE
 * @author 蒋落琛
 * @date 2015-5-13
 */
public interface IFunctionService{
	
	/**
	 * 查询当前节点子节点信息
	 * 
	 * @param functionEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public List<FunctionEntity> queryTreeNodeList(FunctionEntity functionEntity);
	
	/**
	 * 获取当前节点下的所有节点，首页菜单
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-2
	 * @update
	 */
	List<FunctionEntity> queryTreeNodeListByHomeMenu(FunctionEntity functionEntity);
	
	/**
	 * 查询当前用户所有菜单
	 * 
	 * @param userCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-2
	 * @update
	 */
	Set<String> queryTreeNodeListByUser(String userCode);
}
