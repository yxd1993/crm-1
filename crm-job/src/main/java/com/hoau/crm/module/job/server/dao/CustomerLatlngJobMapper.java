package com.hoau.crm.module.job.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntity;

@Repository
public interface CustomerLatlngJobMapper {
	/**
	 *获得可以得到坐标的客户
	 * @param params
	 * @return
	 */
	public List<CustomerEntity> getinitializeCustomerLatLng(RowBounds rb);

	/**
	 * @param latlngEntities
	 * 批量添加客户坐标
	 */
	public void addBatchCustomerLatlng(List<CustomerLatlngEntity> latlngEntities);
	
	/**
	 * @param rb
	 * @return
	 * 获取可以分配的客户并初始化坐标
	 */
	public List<CustomerInfoPoolEntity> getCustomerNotdistributionInfo(RowBounds rb);
	
	/**
	 * @param map
	 * @return
	 * 根据一个客户坐标确认属于某个用户范围
	 */
	public List<UserScopeEntity> queryCustomerByUserScopeInfo(Map<String,String> map);
	
	/**
	 * @param map
	 * @return
	 * 获取公司所有用户服务范围
	 */
	public List<UserScopeEntity> queryByUserScopeInfo();
	
	/**
	 * @param infoPoolEntities
	 * 批量更新客户负责人
	 */
	public void updateBatchCustomerManagePerson(List<CustomerInfoPoolEntity> infoPoolEntities);
	
	/**
	 * @param ids
	 * 批量更新客户得不到坐标的客户下次不再扫描
	 */
	public void updateBatchCustomerIsUpdate(List<String> ids);
	
	/**
	 * @param ids
	 * @return
	 * 根据用户ID获得门店产值最高的的用户
	 */
	public String getMaxStoresOutputUsers(List<String> ids);
	
	/**
	 * @param ids
	 * @return
	 * 根据用户ID获得客户经理产值最高的的用户
	 */
	public String getMaxSalesManagerOutputUsers(List<String> ids);

}
