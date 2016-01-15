/**
 * 
 */
package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.sales.api.shared.domain.ReportEmpWorkEntity;

/**
 *
 * @author 丁勇
 * @date 2015年7月18日
 */
@Repository
public interface ReportEmpWorkMapper {
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
	 * 按 工号查询本月所有的客户操作记录
	 * @param map
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年7月18日
	 * @update 
	 */
	public List<Map<String, Object>> queryEmpWorkListDetail(Map<String,Object> map,RowBounds rb);
	/**
	 * 按 工号查询本月所有的客户操作记录总数
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月18日
	 * @update 
	 */
	public long queryEmpWorkListDetailCount(Map<String,Object> map);
	
	/**
	 * 查询当月指标信息
	 * @param map
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年7月22日
	 * @update 
	 */
	public long queryEmpWorkIndex(Map<String,Object> map);
	/**
	 * 按登录名查询角色编码
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年7月22日
	 * @update 
	 */
	public Set<String> queryUserRole(String account);
	/**
	 * 查询角色下面当前年月的指标值
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年7月22日
	 * @update 
	 */
	public long queryUserRoleIndex(Map<String,Object> map);
	
	/**
	 * 查询门店类型
	 * @param empCode
	 * @return
	 * @author 丁勇
	 * @date 2015年7月22日
	 * @update 
	 */
	public String queryStoreType(String empCode);
	
	/**
	 * 查询一周事业部,大区,路区角色未完成的指标的员工进行消息推送
	 * @return
	 * @author 丁勇
	 * @date 2015年7月29日
	 * @update 
	 */
	public List<Map<String,Object>> querEmpIndexNoStore(Map<String,Object> map);
	/**
	 * 查询一周 门店(到货和无到货门店)角色未完成的指标的员工进行消息推送
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月29日
	 * @update 
	 */
	public List<Map<String,Object>> querEmpIndexByStore(Map<String,Object> map);
	
	/**
	 * 查询是否有指标值
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年8月25日
	 * @update 
	 */
	public long queryIndexCountByDate(Map<String,Object> map);
	
	/**
	 * 初始化默认指标到数据库中
	 * @param map
	 * @author 丁勇
	 * @date 2015年8月25日
	 * @update 
	 */
	public void insertDefaultIndex(Map<String,Object> map);
}