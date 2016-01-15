package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;

/**
 * 报表数据DAO
 * @author: 何斌
 * @create: 2015年6月2日 下午5:44:51
 */
@Repository
public interface ReportDataMapper {
	/**
	 * 查询行业报表数据
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月2日
	 * @update 
	 */
	List<ReportDataEntity> queryCustomerIndustryData(Map<String,String> params);
	
	/**
	 * 查询客户性质报表数据
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月2日
	 * @update 
	 */
	List<ReportDataEntity> queryCustomerNatureData(Map<String,String> params);
	
	/**
	 * 统计客户总数(带组织条件)
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月2日
	 * @update 
	 */
	ReportDataEntity countTotalCustomer(Map<String,String> params);
	
	/**
	 * 统计所有有效客户总数
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	long countAllCustomer(Map<String,String> params);
}
