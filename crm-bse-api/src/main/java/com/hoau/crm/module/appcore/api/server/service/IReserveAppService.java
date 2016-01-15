/**
 * 
 */
package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.ReserveAppVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 预约 restful接口
 * 
 * @author 丁勇
 * @date 2015年7月6日
 */
public interface IReserveAppService {
	
	/**
	 * <p>
	 * 根据登录信息,和前台的值进行保存
	 * </p>
	 * 
	 * @param reserve
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年7月7日
	 * @update
	 */
	ResponseBaseEntity<ReserveAppVo> saveOrUpdate(ReserveAppVo reserve,
			String loginName);

	/**
	 * 按id查询预约信息
	 * 
	 * @param id
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年7月7日
	 * @update
	 */
	ResponseBaseEntity<ReserveAppVo> queryReserveById(ReserveAppVo reserveVo,
			String loginName);

	/**
	 * 作废预约
	 * 
	 * @param reserveVo
	 * @return
	 * @author 丁勇
	 * @date 2015年7月8日
	 * @update
	 */
	ResponseBaseEntity<ReserveAppVo> delReserve(ReserveAppVo reserveVo,
			String loginName);

	/**
	 * 分页查询预约信息
	 * 
	 * @param reserveVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年7月9日
	 * @update
	 */
	ResponseBaseEntity<ReserveAppVo> getReserveByPaging(ReserveAppVo reserveVo,
			String loginName);

	/**
	 * 查询未赴约的客户
	 * 
	 * @param reserveVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年7月10日
	 * @update
	 */
	ResponseBaseEntity<ReserveAppVo> queryNoReserve(SaleReserveVo reserveVo,
			String loginName);
}
