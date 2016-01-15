package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.EmployeeVo;


/**
 * 人员信息SERVICE层接口
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public interface IEmployeeService{
	
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
	public List<EmployeeEntity> queryEmpList(EmployeeVo employeeVo, RowBounds rb);
	
	/**
	 * 查询人员信息总数
	 * 
	 * @param emp
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public long countEmp(EmployeeVo employeeVo);
	
	/**
	 * 根据工号查询人员信息
	 * 
	 * @param emp
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public EmployeeEntity queryEmployeeByEmpcode(EmployeeEntity emp);
	
	/**
	 * 查询部门下所有人员(客户经理及团队经理)
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月1日
	 * @update 
	 */
	public List<EmployeeEntity> querySaleEmpsByDeptCode(UserEntity currentUser,EmployeeVo employeeVo);
}
