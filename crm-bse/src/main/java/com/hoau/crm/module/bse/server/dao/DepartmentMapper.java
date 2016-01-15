package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.BseDepartmentBean;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;

/**
 * 组织信息DAO
 */
@Repository
public interface DepartmentMapper {

	/**
	 * 根据父节点查询所有子组织信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-14
	 * @update
	 */
	public List<DepartmentEntity> getChildDeptList(DepartmentEntity dept);
	
	/**
	 * 分页查询组织信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<DepartmentEntity> getDeptList(Map<String, String> params, RowBounds rb);
	
	/**
	 * 查询组织信息总数
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public long countDept(Map<String, String> params);
	
	/**
	 * 根据组织编码查询单个组织信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<DepartmentEntity> getDeptByDeptCode(DepartmentEntity dept);
	
	/**
	 * 根据组织编码查询单个组织基础信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public List<DepartmentEntity> getSimpleDeptByDeptCode(String deptCode);
	
	/**
	 * 根据组织编码查询基础组织信息
	 * 
	 * @param dept
	 * @return
	 * @author 蒋落琛
	 * @date 2015-10-8
	 * @update
	 */
	public BseDepartmentBean queryBseDeptByDeptcode(String deptCode);
}
