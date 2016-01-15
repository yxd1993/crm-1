/**
 * 
 */
package com.hoau.crm.module.sales.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;
import com.hoau.hbdp.framework.service.IService;

/**
 *
 * @author 丁勇
 * @date 2015年6月9日
 */
public interface ISaleReserveService extends IService {
	/**
	 * 分页查询预约信息
	 * @param role
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年6月10日
	 * @update 
	 */
	public List<SaleReserveEntity> getReserveByPaging(SaleReserveVo reserveVo, RowBounds rb);
	/**
	 * 查询预约信息总数
	 * @param reserveVo
	 * @return
	 * @author 丁勇
	 * @date 2015年6月10日
	 * @update 
	 */
	public long reserveCount(SaleReserveVo reserveVo);
	/**
	 * 新增预约计划
	 * @param reserveplan
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	
	public void saveOrUpdateReservePlan(SaleReserveEntity reserveplan,UserEntity currUser);
	/**
	 *  修改按钮按id查询预约信息
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	public SaleReserveEntity getReserveDetailByIdUseUpdate(SaleReserveEntity reserveplan,UserEntity currUser);
	
	/**
	 * 双击查看信息
	 * @param reserveEntity
	 * @return
	 * @author 丁勇
	 * @date 2015年6月18日
	 * @update 
	 */
	public SaleReserveVo getReserveDetailById(SaleReserveEntity reserveEntity);
	/**
	 * 删除预约信息
	 * @param ids
	 * @author 丁勇
	 * @date 2015年6月15日
	 * @update 
	 */
	public void delete(List<String> ids,String delDesc,UserEntity currUser);
	
	/**
	 * 查询未赴约的信息
	 * @param saleReserveVo
	 * @return
	 * @author 丁勇
	 * @date 2015年7月1日
	 * @update 
	 */
	public List<SaleReserveVo> queryNotReserve(SaleReserveVo saleReserveVo,UserEntity currUser);
}
