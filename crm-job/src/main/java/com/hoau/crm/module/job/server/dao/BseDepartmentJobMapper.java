/**
 * 
 */
package com.hoau.crm.module.job.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.job.shared.domain.BseDepartmentBean;

/**
 *
 * @author 丁勇
 * @date 2015年8月1日
 */
@Repository
public interface BseDepartmentJobMapper {
	/**
	 * 查询最大版本号
	 * @return
	 * @author 丁勇
	 * @date 2015年8月4日
	 * @update 
	 */
	public String queryBseDeptByVersionNo();
	
	/**
	 * 按部门编号查询部门信息
	 * @param deptCode
	 * @return
	 * @author 丁勇
	 * @date 2015年8月4日
	 * @update 
	 */
	public BseDepartmentBean queryBseDetptByDeptCode(String deptCode);
	/**
	 * 添加最新的部门信息
	 * @param bseDept
	 * @author 丁勇
	 * @date 2015年8月1日
	 * @update 
	 */
	public void insertBaseDept(BseDepartmentBean bseDept);
	
	/**
	 * 根据部门编号进行批量删除,
	 * @param deptCode
	 * @author 丁勇
	 * @date 2015年8月4日
	 * @update 
	 */
	public void delBaseDeptByDeptCode(String deptCode);
}
