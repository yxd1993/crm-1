package com.hoau.crm.module.sales.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.appcore.api.shared.domain.UserOperationEntity;

/**
 * 客户操作信息SERVICE接口
 * 
 * @author: 潘强
 * @create: 2015年7月20日
 */

public interface IUserOperationService {

	/**
	 * 客户操作反信息
	 * 
	 * @param userOperation
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月15日
	 * @update
	 */
	List<UserOperationEntity> getUserOperation(
			UserOperationEntity userOperation, RowBounds rb);
	/**
	 * 统计客户操作总数
	 * 
	 * @param userOperation
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月20日
	 * @update
	 */
	Long countUserOperation(UserOperationEntity userOperation, RowBounds rb);

}
