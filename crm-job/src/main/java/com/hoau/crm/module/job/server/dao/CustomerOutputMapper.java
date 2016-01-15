package com.hoau.crm.module.job.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 客户产值信息更新DAO
 *
 * @author 蒋落琛
 * @date 2015-7-8
 */
@Repository
public interface CustomerOutputMapper {

	/**
	 * 给新增的有DC账号的客户增加产值数据
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-7-8
	 * @update
	 */
	void addCustomerOutput(Map<String, Object> map);
	
	/**
	 * 刷新客户的产值
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-7-9
	 * @update
	 */
	void refreshCustomerOutput(Map<String, Object> map);
	
	/**
	 * 查询上月产值小于前三月平均产值的客户与负责人
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-16
	 * @update
	 */
	List<Map<String, String>> getCutProductionCustomer(Map<String, Object> map);

	/**
	 * 给新增的客户有负责人且dc账号不为空的添加产值
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-16
	 * @update
	 */
	void addManageEmpOutput(Map<String, Object> map);

	/**
	 * 刷新负责人产值数据
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-7-9
	 * @update
	 */
	void refreshManageEmpOutput(Map<String, Object> map);
	
	/**
	 * 刷新客户表近三个月产值
	 * 
	 * @author: 何斌
	 * @date: 2015年12月9日
	 * @update 
	 */
	void refreshCustomerProductValueOfThreeMonth();
}
