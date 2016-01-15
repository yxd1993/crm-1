package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.FunctionEntity;

/**
 * 功能权限DAO
 */
@Repository
public interface FunctionMapper {

	/**
	 * 查询当前节点子节点信息
	 * 
	 * @param functionEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	List<FunctionEntity> queryTreeNodeList(FunctionEntity functionEntity);
	
	/**
	 * 获取当前节点下的所有节点，首页菜单
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-2
	 * @update
	 */
	List<FunctionEntity> queryTreeNodeListByHomeMenu(Map<String, String> map);
	
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
