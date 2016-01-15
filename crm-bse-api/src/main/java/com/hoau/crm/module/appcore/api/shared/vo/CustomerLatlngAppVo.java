package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;


/**
 * @author 275636
 *客户经纬度
 */
public class CustomerLatlngAppVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6180141447923322165L;
	
	/**维度*/
	private double lat;
	/**经度*/
	private double lng;
	/**
	 * 距离范围
	 * */
	private double distanceScope;
	
	/**
	 * 客户范围集合
	 */
	private List<CustomerLatlngEntity> customerLatlngEntities;
	
	public List<CustomerLatlngEntity> getCustomerLatlngEntities() {
		return customerLatlngEntities;
	}
	public void setCustomerLatlngEntities(
			List<CustomerLatlngEntity> customerLatlngEntities) {
		this.customerLatlngEntities = customerLatlngEntities;
	}
	public double getDistanceScope() {
		return distanceScope;
	}
	public void setDistanceScope(double distanceScope) {
		this.distanceScope = distanceScope;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
}
