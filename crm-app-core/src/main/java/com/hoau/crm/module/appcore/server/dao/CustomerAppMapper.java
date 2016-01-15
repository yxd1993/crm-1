package com.hoau.crm.module.appcore.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;


/**
 * 客户信息DAO
 *
 * @author 蒋落琛
 * @date 2015-6-17
 */
@Repository
public interface CustomerAppMapper {

	/**
	 * 根据条件分页查询客户信息
	 * 
	 * @param customerEntity
	 * @param rb
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public List<CustomerEntity> getCustomerInfo(Map<String, String> params, RowBounds rb);
	
	/**
	 * 查询客户信息总数
	 * 
	 * @param customerEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public long countCustomer(Map<String, String> params);

	/**
	 * 获取附近指定公里范围内的客户信息
	 * 
	 * @return
	 * @author 275636
	 * @date 2015-7-10
	 * @update
	 */
	public List<CustomerLatlngEntity> getNearCustomerScopeLatLng(
			Map<String, String> map);
		
}
