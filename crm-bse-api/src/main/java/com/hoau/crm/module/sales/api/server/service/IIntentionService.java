package com.hoau.crm.module.sales.api.server.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.sales.api.shared.domain.IntentionEntity;
import com.hoau.crm.module.sales.api.shared.vo.IntentionVo;
import com.hoau.hbdp.framework.service.IService;

/**
 * @author 275636
 *	意向列表SERVICE
 */
public interface IIntentionService extends IService {
	/**
	 * 根据条件分页查询意向列表
	 * @param params
	 * @param rb
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
	public List<IntentionEntity> getIntentionInfo(IntentionVo intentionVo, RowBounds rb);
	
	/**
	 * 查询意向列表总数
	 * @param params
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
	public long countIntention(IntentionVo intentionVo);
	
	/**
	 * 根据条件分页查询意向列表
	 * @param params
	 * @param rb
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
//	public List<IntentionEntity> getIntentionInfoByCombo(Map<String, String> params, RowBounds rb);
	
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
	
//	/**
//	 * 修改意向列表
//	 * 
//	 * @param intentionEntity
//	 * @author 275636
//	 * @date 2015-06-10
//	 */
//	public void updateIntentionInfo(IntentionEntity intentionEntity);
	
	/**
	 * 根据主键查询意向列表
	 * 
	 * @param fId
	 * @return
	 * @author 275636
	 * @date 2015-06-10
	 */
	public IntentionEntity getIntentionInfoById(IntentionEntity intentionEntity);
	
	/**
	 * 删除意向
	 * @param map
	 * @author 275636
	 * @date 2015-06-10
	 */
	void deleteIntentionInfo(Map<String,Object> maps);
}
