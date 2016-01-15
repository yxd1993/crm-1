/**
 * 
 */
package com.hoau.crm.module.sales.api.server.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.sales.api.shared.domain.ReportEmpWorkEntity;

/**
 *
 * @author 丁勇
 * @date 2015年7月20日
 */
public interface IReportEmpWorkService {
	/**
	 * 查询当前员工登录的当月工作信息
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月23日
	 * @update 
	 */
	public ReportEmpWorkEntity queryCurrentEmpWork(String empCode);
	/**
	 * 查询 当前登录者的当月总工作记录以及以下负责人记录
	 * @param map
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年7月18日
	 * @update 
	 */
	public List<ReportEmpWorkEntity> queryEmpWorkList(Map<String,Object> map,RowBounds rb);
	
	/**
	 * 
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月18日
	 * @update 
	 */
	public long queryEmpWorkListCount(Map<String,Object> map);
	/**
	 * 按 工号查询当月所有的客户操作记录
	 * @param map
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年7月18日
	 * @update 
	 */
	public List<Map<String,Object>> queryEmpWorkListDetail(Map<String,Object> map,RowBounds rb);
	/**
	 * 
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月18日
	 * @update 
	 */
	public long queryEmpWorkListDetailCount(Map<String,Object> map);
	
	/**
	 * 当月指标完成信息
	 * @param map
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年7月22日
	 * @update 
	 */
	public List<Map<String,Object>> queryEmpWorkIndex(Map<String,Object> map);
	
	/**
	 * 统计当前选择的工号的客户数量
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月24日
	 * @update 
	 */
	public Map<String, Object> queryCountCustomerByEmp(Map<String, Object> map);
	
	/**
	 * 查询 事业部,大区,路区,客户经理,门店的角色员工的每周工作指标和完成度
	 * @return
	 * @author 丁勇
	 * @date 2015年7月29日
	 * @update 
	 */
	public  List<Map<String,Object>> querEmpAllIndex();
	
}
