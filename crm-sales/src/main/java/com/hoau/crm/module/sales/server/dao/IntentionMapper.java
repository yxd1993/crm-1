package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.sales.api.shared.domain.IntentionEntity;

/**
 * 意向列表DAO
 *
 * @author 275636
 * @date 2015-6-10
 */
@Repository
public interface IntentionMapper {
	/**
	 * 根据条件分页查询意向列表
	 * @param params
	 * @param rb
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
	public List<IntentionEntity> getIntentionInfo(Map<String, String> params, RowBounds rb);
	
	/**
	 * 查询意向列表总数
	 * @param params
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
	public long countIntention(Map<String, String> params);
	
	/**
	 * 根据条件分页查询意向列表
	 * @param params
	 * @param rb
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
	//public List<IntentionEntity> getIntentionInfoByCombo(Map<String, String> params, RowBounds rb);
	
	/**
	 * 查询意向列表总数
	 * @param params
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
//	public long countIntentionByCombo(Map<String, String> params);
	
	/**
	 * 新增意向列表
	 * @param intentionEntity
	 * @author 275636
	 * @date 2015-06-10
	 */
	public void addIntentionInfo(IntentionEntity intentionEntity);
	
	/**
	 * 修改意向列表
	 * 
	 * @param intentionEntity
	 * @author 275636
	 * @date 2015-06-10
	 */
	public void updateIntentionInfo(IntentionEntity intentionEntity);
	
	/**
	 * 根据主键查询意向列表
	 * 
	 * @param fId
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
	public IntentionEntity getIntentionInfoById(String fId);
	
	/**
	 * 删除意向
	 * @param map
	 * @author 275636
	 * @date 2015-06-10
	 */
	public void deleteIntentionInfo(Map<String, Object> map);
}
