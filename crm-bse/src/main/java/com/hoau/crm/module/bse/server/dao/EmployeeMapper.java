package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;

/**
 * 人员信息DAO
 */
@Repository
public interface EmployeeMapper {

	/**
	 * 分页查询人员信息
	 * 
	 * @param emp
	 * @param rb
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<EmployeeEntity> getEmployeeList(Map<String, String> params, RowBounds rb);

	/**
	 * 查询人员信息总数
	 * 
	 * @param emp
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public long countEmp(Map<String, String> params);

	/**
	 * 根据工号查询人员信息
	 * 
	 * @param emp
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public EmployeeEntity getEmployeeByEmpcode(EmployeeEntity emp);
	
	/**
	 * 查询部门下所有人员(客户经理及团队经理)
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月1日
	 * @update 
	 */
	public List<EmployeeEntity> getEmployeesByDeptCode(Map<String,String> params);

}