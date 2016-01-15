package com.hoau.crm.module.customer.api.shared.domain;

import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * @author 275636
 *用户服务范围
 */
public class UserScopeEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4974925027067708787L;
	
	/**
	 * 用户实体
	 * */
//	private UserEntity userEntity;
	
	private String user_id;
	
	private String user_name;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	/**
	 * 两点之间最长距离
	 */
	private double maxlength;
	
	/**
	 * 最长经度A
	 */
	private double lngA;
	/**
	 * 最长经度B
	 */
	private double lngB;
	
	/**
	 * 最长纬度A
	 */
	private double latA;
	
	/**
	 * 最长纬度B
	 */
	private double latB;
	
	/**
	 * 中心点经度
	 */
	private double centerlng;
	
	/**
	 * 中心点纬度
	 */
	private double centerlat;
	
	/**
	 * 用户所属范围最小纬度
	 */
	private double minLat;
	
	/**
	 * 用户所属范围最小经度
	 */
	private double minLng;
	/**
	 * 用户所属范围最大纬度
	 */
	private double maxLat;
	/**
	 * 用户所属范围最大经度
	 */
	private double maxLng;
	
	/**多边形坐标集合*/
	private String ploygongeo;
	
	/**用户角色*/
	private String role_code;
	

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getPloygongeo() {
		return ploygongeo;
	}

	public void setPloygongeo(String ploygongeo) {
		this.ploygongeo = ploygongeo;
	}

	public double getMinLat() {
		return minLat;
	}

	public void setMinLat(double minLat) {
		this.minLat = minLat;
	}

	public double getMinLng() {
		return minLng;
	}

	public void setMinLng(double minLng) {
		this.minLng = minLng;
	}

	public double getMaxLat() {
		return maxLat;
	}

	public void setMaxLat(double maxLat) {
		this.maxLat = maxLat;
	}

	public double getMaxLng() {
		return maxLng;
	}

	public void setMaxLng(double maxLng) {
		this.maxLng = maxLng;
	}

	
	private List<UserScopeEntryEntity> scopeEntryEntities;

	public List<UserScopeEntryEntity> getScopeEntryEntities() {
		return scopeEntryEntities;
	}

	public void setScopeEntryEntities(List<UserScopeEntryEntity> scopeEntryEntities) {
		this.scopeEntryEntities = scopeEntryEntities;
	}

//	public UserEntity getUserEntity() {
//		return userEntity;
//	}
//
//	public void setUserEntity(UserEntity userEntity) {
//		this.userEntity = userEntity;
//	}

	public double getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(double maxlength) {
		this.maxlength = maxlength;
	}

	public double getLngA() {
		return lngA;
	}

	public void setLngA(double lngA) {
		this.lngA = lngA;
	}

	public double getLngB() {
		return lngB;
	}

	public void setLngB(double lngB) {
		this.lngB = lngB;
	}

	public double getLatA() {
		return latA;
	}

	public void setLatA(double latA) {
		this.latA = latA;
	}

	public double getLatB() {
		return latB;
	}

	public void setLatB(double latB) {
		this.latB = latB;
	}

	public double getCenterlng() {
		return centerlng;
	}

	public void setCenterlng(double centerlng) {
		this.centerlng = centerlng;
	}

	public double getCenterlat() {
		return centerlat;
	}

	public void setCenterlat(double centerlat) {
		this.centerlat = centerlat;
	}
}
