package com.hoau.crm.module.bse.api.server.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;


/**
 * 组织信息SERVICE层接口
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public interface IDepartmentService{

	/**
	 * 根据父节点查询所有子组织信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-14
	 * @update
	 */
	public List<DepartmentEntity> queryChildDeptList(DepartmentEntity dept);
	
	/**
	 * 分页查询组织信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<DepartmentEntity> queryDeptList(DepartmentVo departmentVo, RowBounds rb, UserEntity currentUser);
	
	/**
	 * 查询组织信息总数
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public long countDept(DepartmentVo departmentVo, UserEntity currentUser);
	
	/**
	 * 根据组织编码查询单个组织信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public DepartmentEntity queryDeptByDeptCode(DepartmentEntity dept);
	
	/**
	 * 查询组织的路区、大区、事业部信息
	 * 
	 * @param deptEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-26
	 * @update
	 */
	public DepartmentVo queryDeptSuperiorDept(DepartmentEntity deptEntity, UserEntity currentUser);
	
	/**
	 * 根据当前用户查询组织的路区、大区、事业部信息
	 * 
	 * @param deptEntity
	 * @param currentUser
	 * @return
	 * @author 蒋落琛
	 * @date 2015-10-8
	 * @update
	 */
	public DepartmentVo queryDeptSuperiorDeptByCurrUser(Map<String, String> customerInfo, UserEntity currentUser);
	
	/**
	 * 递归获得大区
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月1日
	 * @update 
	 */
	public DepartmentEntity getSupDeptCodeManager(String deptCode);
	
}
