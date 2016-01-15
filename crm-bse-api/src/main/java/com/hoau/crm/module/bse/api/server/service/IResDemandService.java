package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.ResDemandEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

public interface IResDemandService {

	/**
	 * @param resDemand
	 * @param rb
	 * @return
	 * 分页查询资源需求信息
	 */
	List<ResDemandEntity> queryResDemandList(ResDemandEntity resDemand,
			RowBounds rb,UserEntity currentUser);

	/**
	 * @param resDemand
	 * @param rb
	 * @return
	 * 分页查询资源需求信息
	 */
	Long countqueryResDemandList(ResDemandEntity resDemand, RowBounds rb,UserEntity currentUser);
	
	/**
	 * @param ids
	 * 根据ID集合删除资源需求信息
	 */
	void deleteResDemand(List<String> ids);
	
	/**
	 * @param resDemandEntity
	 * @param currentUser
	 * 新增资源信息
	 */
	void addResDemand(ResDemandEntity resDemandEntity, UserEntity currentUser);

	/**
	 * @param resDemand
	 * @return
	 * 根据ID获取资源信息
	 */
	ResDemandEntity queryResDemandInfoById(ResDemandEntity resDemand);
	
	/**
	 * @param resDemand
	 * 修改资源需求信息
	 */
	void updateResDemandInfoById(ResDemandEntity resDemand,UserEntity currentUser);
	
	/**
	 * @param resDemand
	 * 修改集团意见是否已答复
	 */
	void updateResDemandInfoByIsreply(ResDemandEntity resDemand);
}
