package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;

/**
 * 客户签到DAO
 * 
 * @author: 潘强
 * @create: 2015年7月21日
 */

@Repository
public interface SignMapper {
	/**
	 * 查询签到信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月21日
	 * @update
	 */
	List<SignEntity> getSign(Map<String, Object> params, RowBounds rb);
	/**
	 * 统计客户签到信息总数
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月21日
	 * @update
	 */
	long countSign(Map<String, Object> params);

	/**
	 * 更新绑定的签到id
	 * 
	 * @param signId
	 * @author: 何斌
	 * @date: 2015年8月14日
	 * @update 
	 */
	void updateRelationChatStatus(String signId);
	
	/**
	 * 统计未关联的签到
	 * @param signEntity
	 * @return
	 * @author 丁勇
	 * @date 2015年12月2日
	 * @update 
	 */
	List<SignEntity> getSignInfoNoRelation(Map<String, Object> params,RowBounds rb);
	
	/**
	 * 统计未关联签到的数量
	 * @param sign
	 * @return
	 * @author 丁勇
	 * @date 2015年12月21日
	 * @update 
	 */
	long countSignInfoNoRelation(Map<String, Object> params);
	
	/**
	 * 保存之前查询是否已被其他设备端绑定过
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年12月21日
	 * @update 
	 */
	long isHasThereSign (String id);
}
