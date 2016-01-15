package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.sales.api.shared.domain.SweepSignEntity;

/**
 * 扫描签到DAO
 *
 * @author 蒋落琛
 * @date 2015-7-23
 */
@Repository
public interface SweepSignMapper {

	/**
	 * 增加扫描签到
	 * 
	 * @param signEntity
	 * @author 蒋落琛
	 * @date 2015-7-23
	 * @update
	 */
	void addSweepSignInfo(SweepSignEntity ssEntity);
	
	/**
	 * 查询扫描签到信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月27日
	 * @update
	 */
	List<SweepSignEntity> getSweepSign(Map<String, Object> params);
	/**
	 * 统计扫描签到总数
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月27日
	 * @update
	 */
	Long countSweepSign(Map<String, Object> params, RowBounds rb);
	
}
