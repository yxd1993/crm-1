package com.hoau.crm.module.job.server.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.job.shared.domain.EmpBean;

/**
 * 人员信息DAO
 * @author: 何斌
 * @create: 2015年5月19日 上午10:10:04
 */
@Repository("employeeSynMapper")
public interface EmployeeMapper {
	/**
	 * 新增人员信息
	 * 
	 * @param emp
	 * @author: 何斌
	 * @date: 2015年5月19日
	 * @update 
	 */
	void insert(EmpBean emp);
	
	/**
	 * 更新人员信息
	 * 
	 * @param emp
	 * @author: 何斌
	 * @date: 2015年5月19日
	 * @update 
	 */
	void update(EmpBean emp);
	
	/**
	 * 根据人员编码查询人员信息
	 * 
	 * @param empcode
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月19日
	 * @update 
	 */
	EmpBean queryByEmpcode(String workcode);
	
	/**
	 * 如果离职了,修改用户表状态
	 * @author: 何斌
	 * @date: 2015年6月3日
	 * @update 
	 */
	void updateUser(Map<String, String> map);
	
	/**
	 * 新增用户信息
	 * 
	 * @param emp
	 * @author: 何斌
	 * @date: 2015年6月3日
	 * @update 
	 */
	void insertUser(EmpBean emp);
	
	/**
	 * 查看岗位是否拥有对应角色
	 * 
	 * @param workcode
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	String isExistRole(Map<String,String> params);
	
	/**
	 * 系统自动分配角色
	 * 
	 * @param workcode
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	void autoInsertRole(Map<String,String> params);
	
	/**
	 * 根据角色名字查询角色编码
	 * 
	 * @param roleName
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	String getRoleCodeByName(String roleName);
	
	/**
	 * 删除用户角色
	 * 
	 * @param userCode
	 * @author: 何斌
	 * @date: 2015年8月5日
	 * @update 
	 */
	void deleteRole(Map<String,String> params);
}
