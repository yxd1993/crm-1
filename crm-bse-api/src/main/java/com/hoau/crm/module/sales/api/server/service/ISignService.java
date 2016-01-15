package com.hoau.crm.module.sales.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
/**
 * 客户签到信息service接口
 * @author 潘强
 *@create: 2015年7月21日
 */
public interface ISignService {
	/**
	 * 客户签到信息
	 * 
	 * @param sign
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月21日
	 * @update
	 */
	List<SignEntity> getSign(SignEntity sign, RowBounds rb);
	/**
	 * 统计客户签到总数
	 * 
	 * @param sign
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月21日
	 * @update
	 */
	Long countSign(SignEntity sign);
	
	/**
	 * 查询未关联的签到
	 * 
	 * @param signVo
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月14日
	 * @update 
	 */
	List<SignEntity> getSignInfoNoRelation(SignEntity sign, RowBounds rb);
	
	/**
	 * 统计未关联的签到
	 * 
	 * @param signVo
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月14日
	 * @update 
	 */
	long countSignInfoNoRelation(SignEntity sign);
	
	/**
	 * 更新绑定的签到id
	 * 
	 * @param signId
	 * @author: 何斌
	 * @date: 2015年8月14日
	 * @update 
	 */
	void updateRelationChatStatus(String signId);

}
