package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.UserOperationEntity;

/**
 * 客户操作DAO
 * 
 * @author: 潘强
 * @create: 2015年7月20日
 */

@Repository
public interface UserOperationMapper {

	/**
	 * 查询客户操作信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月20日
	 * @update
	 */
	List<UserOperationEntity> getUserOperation(Map<String, Object> params,
			RowBounds rb);
	/**
	 * 统计客户操作信息总数
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月20日
	 * @update
	 */
	Long countUserOperation(Map<String, Object> params, RowBounds rb);

}
