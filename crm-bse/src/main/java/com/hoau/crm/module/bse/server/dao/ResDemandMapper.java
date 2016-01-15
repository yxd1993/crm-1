package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.ResDemandEntity;

/**
 * @author 275636
 *资源需求
 */
@Repository
public interface ResDemandMapper {
	List<ResDemandEntity> queryResDemandList(Map<String, Object> params,
			RowBounds rb);

	Long countqueryResDemandList(Map<String, Object> params, RowBounds rb);
	
	void addResDemand(ResDemandEntity resDemandEntity);
	
	void deleteResDemand(Map<String, Object> map);
	
	/**
	 * @param resDemand
	 * @return
	 * 根据ID获取资源信息
	 */
	ResDemandEntity queryResDemandInfoById(String id);
	
	/**
	 * @param resDemand
	 * 修改资源需求信息
	 */
	void updateResDemandInfoById(ResDemandEntity resDemand);
	
	/**
	 * @param resDemand
	 * 修改集团意见是否已答复
	 */
	void updateResDemandInfoByIsreply(ResDemandEntity resDemand);
}
