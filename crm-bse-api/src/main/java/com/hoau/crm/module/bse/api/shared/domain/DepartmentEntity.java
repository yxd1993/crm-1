package com.hoau.crm.module.bse.api.shared.domain;

import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 组织信息实体
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class DepartmentEntity extends BaseEntity {

	private static final long serialVersionUID = 7634964873180883023L;

	/**
	 * 部门编号
	 */
	private String deptCode;

	/**
	 * 部门名称
	 */
	private String deptName;

	/**
	 * 部门状态 0 正常，1 取消
	 */
	private String canceled;

	/**
	 * 上级部门编号
	 */
	private String supdeptCode;

	/**
	 * 上级部门名称
	 */
	private String supdeptName;

	/**
	 * 部门性质
	 */
	private String deptNature;

	/**
	 * 部门代码
	 */
	private String logistCode;

	/**
	 * 部门负责人
	 */
	private String managerId;

	/**
	 * 部门负责人姓名
	 */
	private String lastName;

	/**
	 * 所属分部编码
	 */
	private String subCompanyId;

	/**
	 * 所属分部名称
	 */
	private String subCompanyName;
	
	/**
	 * 是否门店
	 */
	private String isStore;
	
	/**
	 * 是否路区
	 */
	private String isRoad;
	
	/**
	 * 是否大区
	 */
	private String isRegion;
	
	/**
	 * 是否事业部
	 */
	private String isBusines;

	/**
	 * 父组织
	 */
	private DepartmentEntity parentDeptEntity;

	/**
	 * 子组织
	 */
	private List<DepartmentEntity> childDeptList;

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getSupdeptCode() {
		return supdeptCode;
	}

	public void setSupdeptCode(String supdeptCode) {
		this.supdeptCode = supdeptCode;
	}

	public String getSupdeptName() {
		return supdeptName;
	}

	public void setSupdeptName(String supdeptName) {
		this.supdeptName = supdeptName;
	}

	public String getDeptNature() {
		return deptNature;
	}

	public void setDeptNature(String deptNature) {
		this.deptNature = deptNature;
	}

	public String getLogistCode() {
		return logistCode;
	}

	public void setLogistCode(String logistCode) {
		this.logistCode = logistCode;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSubCompanyId() {
		return subCompanyId;
	}

	public void setSubCompanyId(String subCompanyId) {
		this.subCompanyId = subCompanyId;
	}

	public String getSubCompanyName() {
		return subCompanyName;
	}

	public void setSubCompanyName(String subCompanyName) {
		this.subCompanyName = subCompanyName;
	}

	public DepartmentEntity getParentDeptEntity() {
		return parentDeptEntity;
	}

	public void setParentDeptEntity(DepartmentEntity parentDeptEntity) {
		this.parentDeptEntity = parentDeptEntity;
	}

	public List<DepartmentEntity> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<DepartmentEntity> childDeptList) {
		this.childDeptList = childDeptList;
	}

	public String getIsStore() {
		return isStore;
	}

	public void setIsStore(String isStore) {
		this.isStore = isStore;
	}

	public String getIsRoad() {
		return isRoad;
	}

	public void setIsRoad(String isRoad) {
		this.isRoad = isRoad;
	}

	public String getIsRegion() {
		return isRegion;
	}

	public void setIsRegion(String isRegion) {
		this.isRegion = isRegion;
	}

	public String getIsBusines() {
		return isBusines;
	}

	public void setIsBusines(String isBusines) {
		this.isBusines = isBusines;
	}
}
