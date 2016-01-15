/**
 * 
 */
package com.hoau.crm.module.appcore.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.vo.ReserveAppVo;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;

/**
 * 预约功能 restful mapperDao
 * 
 * @author 丁勇
 * @date 2015年7月7日
 */
@Repository
public interface ReserveAppMapper {
	/**
	 * 分页查询预约信息 (按时间分组)
	 * @param params
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 
	 * @update 
	 */
	public List<SaleReserveEntity> getReserveByPaging(Map<String, Object> params, RowBounds rb);
	/**
	 * 查询预约信息总数
	 * @param params
	 * @return
	 * @author 丁勇
	 * @date 
	 * @update 
	 */
	public long reserveCount(Map<String,Object> params);
	/**
	 * 按id查询信息
	 * 
	 * @param reserveId
	 * @return
	 * @author 丁勇
	 * @date 2015年7月7日
	 * @update
	 */
	public ReserveAppVo queryReserveById(String reserveId);
	
	/**
	 * 作废预约
	 * @param map
	 * @author 丁勇
	 * @date 2015年7月8日
	 * @update 
	 */
	public void delReserve(Map<String,Object> map);
}
