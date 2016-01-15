package com.hoau.crm.module.sales.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.sales.api.server.service.ISaleIncomeReportService;
import com.hoau.crm.module.sales.api.shared.domain.SaleIncomeAnalysisEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleIncomeDetailVo;
import com.hoau.crm.module.sales.server.dao.SaleIncomeReportMapper;

/**
 * 销售收入报表SERVICE
 * 
 * @author: 何斌
 * @create: 2015年7月21日 下午2:24:55
 */
@Service
public class SaleIncomeReportService implements ISaleIncomeReportService{
	
	@Resource
	SaleIncomeReportMapper saleIncomeReportMapper;
	
	@Override
	public SaleIncomeAnalysisEntity querySaleIncomeAnalysisData(String empCode) {
		SaleIncomeAnalysisEntity saleIncomeAnalysisEntity = new SaleIncomeAnalysisEntity();
		//奖金分布数据
		saleIncomeAnalysisEntity.setBonusDistributionLists(saleIncomeReportMapper.queryBonusDistributionLists(empCode));
		//运输类型分布数据
		saleIncomeAnalysisEntity.setTransportTypeDistributionLists(saleIncomeReportMapper.queryTransportTypeDistributionLists(empCode));
		return saleIncomeAnalysisEntity;
	}

	@Override
	public SaleIncomeDetailVo querySaleIncomeDetailData(String empCode,RowBounds rb) {
		SaleIncomeDetailVo saleIncomeDetailVo = new SaleIncomeDetailVo();
		//当前月份
		saleIncomeDetailVo.setCurrentMonth(new SimpleDateFormat("yyyyMM").format(new Date()));
		//总奖金
		saleIncomeDetailVo.setTotalIncome(saleIncomeReportMapper.queryBonusTotal(empCode));
		//奖金明细
		saleIncomeDetailVo.setSaleIncomeDetails(saleIncomeReportMapper.querySaleIncomeDetailLists(empCode,rb));
		return saleIncomeDetailVo;
	}

	@Override
	public long countSaleIncomeDetailData(String empCode) {
		return saleIncomeReportMapper.countSaleIncomeDetailLists(empCode);
	}

}
