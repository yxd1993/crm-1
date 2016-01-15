/**
 * 
 */
package com.hoau.crm.module.job.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 销售经理每日工作情况mapper
 * @author 丁勇
 * @date 2015年10月12日
 */
@Repository
public interface SaleManagerWorkMapper {
	
	/**
	 * 销售经理工作情况记录保存
	 * @param map
	 * @author 丁勇
	 * @date 2015年10月12日
	 * @update 
	 */
	public void insertSaleManagerWork();
	
	/**
	 * 查询销售经理工作记录
	 * @return
	 * @author 丁勇
	 * @date 2015年10月14日
	 * @update 
	 */
	public List<Map<String,Object>> querySaleManagerWork();
}
