package com.hoau.crm.module.bse.api.server.service;

import com.hoau.crm.module.bse.api.shared.vo.WayBillVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 运单模块SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-7-6
 */
public interface IWayBillAppService {

	/**
	 * 新增运单信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public ResponseBaseEntity<WayBillVo> addWayBillInfo(WayBillVo waybillVo);
	
	/**
	 * 按客户id查询运单日志
	 * @param waybillVo
	 * @return
	 * @author 丁勇
	 * @date 2015年9月9日
	 * @update 
	 */
	public ResponseBaseEntity<WayBillVo> queryWayBillLogByAccountId(WayBillVo waybillVo);
}
