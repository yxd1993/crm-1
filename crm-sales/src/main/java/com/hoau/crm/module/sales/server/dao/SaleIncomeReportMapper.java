package com.hoau.crm.module.sales.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleIncomeDetailEntity;

/**
 * 销售收入报表DAO
 * 
 * @author: 何斌
 * @create: 2015年7月21日 下午2:10:36
 */
@Repository
public interface SaleIncomeReportMapper {
	/**
	 * 查询奖金分布数据
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月21日
	 * @update 
	 */
	List<ReportDataEntity> queryBonusDistributionLists(String empCode);
	/**
	 * 查询运输类型分布数据
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月21日
	 * @update 
	 */
	List<ReportDataEntity> queryTransportTypeDistributionLists(String empCode);
	
	/**
	 * 查询奖金总和
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月21日
	 * @update 
	 */
	double queryBonusTotal(String empCode);
	
	/**
	 * 分页查询收入明细
	 * 
	 * @param empCode,rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月21日
	 * @update 
	 */
	List<SaleIncomeDetailEntity> querySaleIncomeDetailLists(String empCode,RowBounds rb);
	
	/**
	 * 统计查询收入明细总数
	 * 
	 * @param empCode,rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月21日
	 * @update 
	 */
	long countSaleIncomeDetailLists(String empCode);
}
