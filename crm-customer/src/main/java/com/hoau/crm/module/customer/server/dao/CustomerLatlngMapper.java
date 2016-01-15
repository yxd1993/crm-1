package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerGroupEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;

/**
 * @author 275636
 *客户经纬度DAO
 */
@Repository
public interface CustomerLatlngMapper {
	/**
	 * @param customerLatlngEntity
	 */
	public void addCustomerLatlngInfo(CustomerLatlngEntity customerLatlngEntity);
	/**
	 * @param latlngEntities
	 */
	public void addBatchCustomerLatlng(List<CustomerLatlngEntity> latlngEntities);
	/**
	 * @param customerId
	 */
	public void delCustomerLatlngInfo(String customerId);
	
	/**
	 * 获得可以设置坐标的总条数
	 * @return
	 */
	public long countCustomer();
	
	/**
	 *获得可以得到坐标的客户
	 * @param params
	 * @return
	 */
	public List<CustomerEntity> getinitializeCustomerLatLng(Map<String, Integer> params);
	
	/**
	 * 获取附近10公里范围内的客户信息
	 * @return
	 */
	public List<CustomerLatlngEntity> getNearCustomerScopeLatLng(Map<String,String> map);
	
	/**
	 * 客户分布
	 * @return
	 */
	public List<CustomerGroupEntity> getCustomerGroupCount();
	
	/**
	 * 客户产值
	 * @return
	 */
	public List<CustomerGroupEntity> getCustomerHeatMapOutPut();
}
