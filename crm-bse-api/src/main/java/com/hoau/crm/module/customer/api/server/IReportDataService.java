package com.hoau.crm.module.customer.api.server;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;

/**
 * 报表数据SERVICE
 * @author: 何斌
 * @create: 2015年6月2日 下午5:37:35
 */
public interface IReportDataService {
	/**
	 * 查询行业报表数据
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月2日
	 * @update 
	 */
	List<ReportDataEntity> queryCustomerIndustryData();
	
	/**
	 * 查询客户性质报表数据
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月2日
	 * @update 
	 */
	List<ReportDataEntity> queryCustomerNatureData();
	
	/**
	 * 统计总客户数
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	long countAllCustomer();
}
