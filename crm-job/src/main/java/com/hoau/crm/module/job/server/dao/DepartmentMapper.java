package com.hoau.crm.module.job.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.job.shared.domain.OrgBean;

/**
 * 组织信息DAO
 * @author: 何斌
 * @create: 2015年5月19日 上午10:08:22
 */

@Repository("departmentSynMapper")
public interface DepartmentMapper {
	/**
	 * 新增组织信息
	 * 
	 * @param org
	 * @author: 何斌
	 * @date: 2015年5月19日
	 * @update 
	 */
	void insert(OrgBean org);
	
	/**
	 * 更新组织信息
	 * 
	 * @param org
	 * @author: 何斌
	 * @date: 2015年5月19日
	 * @update 
	 */
	void update(OrgBean org);
	
	/**
	 * 根据组织编码查询组织信息
	 * 
	 * @param deptcode
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月19日
	 * @update 
	 */
	OrgBean queryByDeptcode(String deptcode);
}
