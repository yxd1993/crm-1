package com.hoau.crm.module.bse.api.shared.vo;

import java.io.Serializable;

import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;

/**
 * 人员信息VO
 *
 * @author 蒋落琛
 * @date 2015-5-22
 */
public class EmployeeVo implements Serializable {

	private static final long serialVersionUID = 153457847220157441L;
	
	/**
	 * 部门公共选择器查询参数
	 */
	private String selectorParam;
	
	/**
	 * 人员信息
	 */
	private EmployeeEntity empEntity;

	public String getSelectorParam() {
		return selectorParam;
	}

	public void setSelectorParam(String selectorParam) {
		this.selectorParam = selectorParam;
	}

	public EmployeeEntity getEmpEntity() {
		return empEntity;
	}

	public void setEmpEntity(EmployeeEntity empEntity) {
		this.empEntity = empEntity;
	}
}
