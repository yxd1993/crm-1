/**
 * 
 */
package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;
/**
 *	预约信息DAO
 * @author 丁勇
 * @date 2015年6月9日
 */
@Repository
public interface SaleReserveMapper {
	/**
	 * 分页查询预约信息
	 * @param params
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年6月10日
	 * @update 
	 */
	public List<SaleReserveEntity> getReserveByPaging(Map<String, Object> params, RowBounds rb);
	/**
	 * 查询预约信息总数
	 * @param params
	 * @return
	 * @author 丁勇
	 * @date 2015年6月10日
	 * @update 
	 */
	public long reserveCount(Map<String,Object> params);
	/**
	 * 新增预约计划
	 * @param reserveplan
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	public void addCusReservePlan(SaleReserveEntity reserveplan);
	
	/**
	 * 修改预约计划信息
	 * @param reserveplan
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	public void updateReserveplan(SaleReserveEntity reserveplan);
	/**
	 *  按id查询需要修改的信息
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	public SaleReserveEntity getReserveDetailByIdUseUpdate(Map<String,Object> params);
	
	/**
	 * 查看预约详情 
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年6月18日
	 * @update 
	 */
	public SaleReserveVo getReserveDetailById(String id);
	/**
	 * 删除预约信息(修改状态)
	 * @param map
	 * @author 丁勇
	 * @date 2015年6月15日
	 * @update 
	 */
	public void deleteReserve(Map<String,Object> map);
	
	/**
	 * 查询未赴约列表信息
	 * @param accountid
	 * @return
	 * @author 丁勇
	 * @date 2015年7月1日
	 * @update 
	 */
	public List<SaleReserveVo> queryNotReserve(Map<String,Object> map);
}
