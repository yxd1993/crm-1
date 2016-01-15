package com.hoau.crm.module.customer.api.server;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.CustomerGroupEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.hbdp.framework.service.IService;

/**
 * @author 275636
 *客户经纬度实体
 */
public interface ICustomerLatlngService extends IService {
	/**
	 * @param customerLatlngEntity
	 */
	public void addCustomerLatlng(CustomerLatlngEntity customerLatlngEntity);
	/**
	 * @param latlngEntities
	 */
	public void addBatchCustomerLatlng(List<CustomerLatlngEntity> latlngEntities);
	
	/**
	 * 获取附近10公里范围内的客户信息
	 * @return
	 */
	public List<CustomerLatlngEntity> getNearCustomerScopeLatLng(String[] clientCoordinates);
	
	/**
	 * 客户坐标初始化
	 */
	public void initializeCustomerLatLng();
	
	/**
	 * 获取客户分布热力图
	 * @return
	 */
	public List<CustomerGroupEntity> getCustomerGroupCount();
	
	/**
	 * 获取客户产值热力图
	 * @return
	 */
	public List<CustomerGroupEntity> getCustomerHeatMapOutPut();
}
