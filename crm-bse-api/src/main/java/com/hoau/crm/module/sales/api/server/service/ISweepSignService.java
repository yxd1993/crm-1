package com.hoau.crm.module.sales.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.sales.api.shared.domain.SweepSignEntity;

/**
 * 扫描签到SERVICE
 *
 * @author 蒋落琛
 * @date 2015-7-23
 */
public interface ISweepSignService {
	
	/**
	 * 增加扫描签到信息
	 * 
	 * @param ssEntity
	 * @author 蒋落琛
	 * @date 2015-7-23
	 * @update
	 */
	void addSweepSignInfo(SweepSignEntity ssEntity);

	/**
	 * 客户扫描签到信息
	 * 
	 * @param sweepSign
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月27日
	 * @update
	 */
	List<SweepSignEntity> getSweepSign(SweepSignEntity sweepSign, RowBounds rb);

	/**
	 * 统计客户扫描签到总数
	 * 
	 * @param sweepSign
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月27日
	 * @update
	 */
	Long countSweepSign(SweepSignEntity sweepSign, RowBounds rb);
	
}
