package com.hoau.crm.module.sales.api.server.service;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.sales.api.shared.domain.SaleIncomeAnalysisEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleIncomeDetailVo;

/**
 * 销售收入报表SERVICE
 * 
 * @author: 何斌
 * @create: 2015年7月21日 下午2:09:17
 */
public interface ISaleIncomeReportService {
	/**
	 * 查询销售收入分析数据
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月21日
	 * @update 
	 */
	SaleIncomeAnalysisEntity querySaleIncomeAnalysisData(String empCode);
	
	/**
	 * 查询销售收入明细数据
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月21日
	 * @update 
	 */
	SaleIncomeDetailVo querySaleIncomeDetailData(String empCode,RowBounds rb);
	
	/**
	 *  收入明细总数
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月22日
	 * @update 
	 */
	long countSaleIncomeDetailData(String empCode);
}
