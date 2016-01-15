package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;

/**
 * 客户统计DAO
 * 
 * @author: 何斌
 * @create: 2015年7月18日 下午2:18:23
 */
@Repository
public interface ReportCustomerCountMapper {
	/**
	 * 查询当前部门客户总数和名称
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月20日
	 * @update 
	 */
	public ReportDataEntity queryCurrentCustomerCountData(String deptCode);
	
	/**
	 * 查询部门客户总数和发货客户
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月20日
	 * @update 
	 */
	public ReportDataEntity queryDeliverCustomerCountData(String deptCode);
	
	/**
	 * 客户性质分布
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月20日
	 * @update 
	 */
	public List<ReportDataEntity> queryCustomerNatureDistribution(String deptCode);
	
	/**
	 * 统计当前部门客户明细
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月20日
	 * @update 
	 */
	public List<ReportDataEntity> queryCurrentCustomerCountDetailData(String deptCode);
	
	/**
	 * 查询当前部门的子部门
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月20日
	 * @update 
	 */
	public List<String> querySubCompanyDeptCode(Map<String,String> params);
	
	/**
	 * 统计客户总数(客户经理)
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月23日
	 * @update 
	 */
	public long countCustomerOfManage(String empCode);
	
	/**
	 * 统计发货客户数(客户经理)
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月23日
	 * @update 
	 */
	public long countDeliveryCustomerOfManage(String empCode);
	
	/**
	 * 本月新增客户数
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月23日
	 * @update 
	 */
	public long countNewCustomer(String deptCode);
	
	/**
	 * 本月新增客户数(客户经理)
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月23日
	 * @update 
	 */
	public long countNewCustomerOfManage(String empCode);
}
