package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;


/**
 * 基础信息RESTFUL接口返回VO
 * 
 * @author 蒋落琛
 * @date 2015-6-23
 */
public class BseAppVo {
	
	/**
	 * 人员信息集合
	 */
	private List<EmployeeEntity> empList;
	
	/**
	 * 组织信息实体
	 */
	private DepartmentEntity deptEntity;
	
	/**
	 * 组织信息VO
	 */
	private DepartmentVo deptVo;
	
	/**
	 * 组织信息集合
	 */
	private List<DepartmentEntity> deptEntityList;
	
	/**
	 * 组织编码
	 */
	private String logistCode;
	
	/**
	 * 当前客户负责人工号
	 */
	private String customerManageEmpCode;
	
	/**
	 * 当前客户负责人姓名
	 */
	private String customerManageEmpName;
	
	/**
	 * 客户信息公共选择器查询参数 CRM账号、企业名称、联系人
	 */
	private String selectorParam;

	/**
	 * 每页数据量
	 */
	private int limit;

	/**
	 * 分布起始位置
	 */
	private int start;

	/**
	 * 分页数据总长度
	 */
	private Long totalCount;

	public String getSelectorParam() {
		return selectorParam;
	}

	public void setSelectorParam(String selectorParam) {
		this.selectorParam = selectorParam;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<DepartmentEntity> getDeptEntityList() {
		return deptEntityList;
	}

	public void setDeptEntityList(List<DepartmentEntity> deptEntityList) {
		this.deptEntityList = deptEntityList;
	}

	public DepartmentEntity getDeptEntity() {
		return deptEntity;
	}

	public void setDeptEntity(DepartmentEntity deptEntity) {
		this.deptEntity = deptEntity;
	}

	public String getLogistCode() {
		return logistCode;
	}

	public void setLogistCode(String logistCode) {
		this.logistCode = logistCode;
	}

	public DepartmentVo getDeptVo() {
		return deptVo;
	}

	public void setDeptVo(DepartmentVo deptVo) {
		this.deptVo = deptVo;
	}

	public List<EmployeeEntity> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeEntity> empList) {
		this.empList = empList;
	}

	public String getCustomerManageEmpCode() {
		return customerManageEmpCode;
	}

	public void setCustomerManageEmpCode(String customerManageEmpCode) {
		this.customerManageEmpCode = customerManageEmpCode;
	}

	public String getCustomerManageEmpName() {
		return customerManageEmpName;
	}

	public void setCustomerManageEmpName(String customerManageEmpName) {
		this.customerManageEmpName = customerManageEmpName;
	}
}
