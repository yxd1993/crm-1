package com.hoau.crm.module.appcore.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IReportDataAppService;
import com.hoau.crm.module.appcore.api.shared.exception.ReportDataAppException;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.ReportDataAppVo;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.server.service.IReportCustomerCountService;
import com.hoau.crm.module.sales.api.server.service.IReportEmpWorkService;
import com.hoau.crm.module.sales.api.server.service.ISaleIncomeReportService;
import com.hoau.crm.module.sales.api.shared.domain.ReportCustomerCountEntity;
import com.hoau.crm.module.sales.api.shared.domain.ReportEmpWorkEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleIncomeAnalysisEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleIncomeDetailVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 报表模块数据SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-7-13
 */
@SuppressWarnings("rawtypes")
@Service
public class ReportDataAppService implements IReportDataAppService {
	
	@Resource
	ILoginService iLoginService;
	@Resource
	IReportCustomerCountService reportCustomerCountService;
	
	@Resource
	ISaleIncomeReportService saleIncomeReportService;

	@Resource
	IReportEmpWorkService reportEmpWorkService;
	
	@Override
	public ResponseBaseEntity queryAppReportData(ReportDataAppVo searchVo,
			String loginName) {
		// 判断参数是否为空
		if (searchVo == null || searchVo.getQueryParam() == null ) {
			throw new ReportDataAppException(
					ReportDataAppException.REPORTDATA_TYPE_NULL);
		}
		ReportCustomerCountEntity reportCustomerCountEntity = null;
		SaleIncomeAnalysisEntity saleIncomeAnalysisEntity = null;
		SaleIncomeDetailVo saleIncomeDetailVo = null;
		//返回Vo
		ReportDataAppVo  sv = new ReportDataAppVo();
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		//参数配置
		Map<String,Object>  maps = new HashMap<String,Object>();
		//分页条件
		RowBounds rb = new RowBounds(searchVo.getStart(),searchVo.getLimit());
		//获取数据总数
		long count = 0l;
		//当前登录人
		maps.put("currUser",currentUser);
		// 门店报表
		if (searchVo.getQueryParam().getReportType().equals(BseConstants.REPORTDATA_STORES)) {
			//测试test---不要删除
//			JSONArray js = (JSONArray) JSONArray.toJSON( reportEmpWorkService.querEmpAllIndex());
//			System.out.println(js.toString());
			//结果返回详情
			sv.setEmpworkDetail(commReportModule(maps,searchVo).get(0));
			//指标
			sv.setEmpworkIndex(commReportModule(maps,searchVo).get(1));
			//查询客户统计
			sv.setCountCustomerByEmp(null);
			//获取数据总数
			count = 0;
			// 销售报表
		} else if (searchVo.getQueryParam().getReportType().equals(
				BseConstants.REPORTDATA_SALES)) {
			//模块一
			if(BseConstants.MOUDLE_ONE.equals(searchVo.getQueryParam().getReportChildType())){
				if(currentUser.getEmpEntity() != null){
					String empCode = currentUser.getEmpEntity().getEmpCode();
					saleIncomeDetailVo = saleIncomeReportService.querySaleIncomeDetailData(empCode,rb);
					sv.setTotalCount(saleIncomeReportService.countSaleIncomeDetailData(empCode));
				}
				sv.setSaleIncomeDetailVo(saleIncomeDetailVo);
				//模块二
			}else if(BseConstants.MOUDLE_TWO.equals(searchVo.getQueryParam().getReportChildType())){
				if(currentUser.getEmpEntity() != null){
					saleIncomeAnalysisEntity = saleIncomeReportService.querySaleIncomeAnalysisData(currentUser.getEmpEntity().getEmpCode());
				}
				sv.setSaleIncomeAnalysisEntity(saleIncomeAnalysisEntity);
			}
			// 事业部\大区\路区报表
		} else if (searchVo.getQueryParam().getReportType().equals(
				BseConstants.REPORTDATA_BUSINESS)) {
			//报表模块一(1)(2)
			if(BseConstants.MOUDLE_ONE.equals(searchVo.getQueryParam().getReportChildType())){
				//判断是个人工作列表(模块1)还是个人工作详情(模块2)
				if(StringUtils.isEmpty(searchVo.getQueryParam().getAccount())){
					//查询结果
					List<ReportEmpWorkEntity> empworkList = reportEmpWorkService.queryEmpWorkList(maps, rb);
					//获取当前登录者的工作信息
					sv.setEmpEntity(reportEmpWorkService.queryCurrentEmpWork(currentUser.getEmpEntity().getEmpCode()));
					//结果返回
					sv.setEmpworkList(empworkList);
					//获取数据总数
					count = reportEmpWorkService.queryEmpWorkListCount(maps);
				}else{
					//重新获取工号
					UserEntity choseUser = iLoginService.getUserByLoginName(searchVo.getQueryParam().getAccount());
					//选择的账号的工号
					maps.put("empCode",choseUser.getEmpEntity().getEmpCode());
					//选择的账号
					maps.put("choseUser",choseUser);
					maps.put("account",searchVo.getQueryParam().getAccount());
					//结果返回
					sv.setEmpworkDetail(commReportModule(maps,searchVo).get(0));
					sv.setEmpworkIndex(commReportModule(maps,searchVo).get(1));
					//查询客户统计
					sv.setCountCustomerByEmp(reportEmpWorkService.queryCountCustomerByEmp(maps));
					//获取数据总数
					count = reportEmpWorkService.queryEmpWorkListDetailCount(maps);
				}
				//赋值总数
				sv.setTotalCount(count);
				//模块二
			}else if(BseConstants.MOUDLE_TWO.equals(searchVo.getQueryParam().getReportChildType())){
				if(currentUser.getEmpEntity() != null){
					reportCustomerCountEntity = 
							reportCustomerCountService.queryCustomerCountData(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					//返回结果
					sv.setReportCustomerCountEntity(reportCustomerCountEntity);
				}
			}
			// 集团报表报表
		} else if (searchVo.getQueryParam().getReportType().equals(
				BseConstants.REPORTDATA_GROUP)) {
			reportCustomerCountEntity = reportCustomerCountService.queryCustomerCountData(BseConstants.GROUPCODE);
			//返回结果
			sv.setReportCustomerCountEntity(reportCustomerCountEntity);
		}
		// 返回值
		ResponseBaseEntity<ReportDataAppVo> result = new ResponseBaseEntity<ReportDataAppVo>();
		result.setResult(sv);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	/**
	 * 统计分析(门店),事业部和大区下的选择门店
	 * @param maps
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年7月23日
	 * @update 
	 */
	public List<List<Map<String,Object>>> commReportModule(Map<String,Object>  maps,ReportDataAppVo searchVo){
		List<List<Map<String,Object>>> resObject = new ArrayList<List<Map<String,Object>>>();
		//分页条件
		//RowBounds rb = new RowBounds(searchVo.getStart(),searchVo.getLimit());
		//查询工作详情结果
		List<Map<String,Object>> empworkDetailMap = null;
		//查询工作指标结果
		List<Map<String,Object>> empworkIndex = reportEmpWorkService.queryEmpWorkIndex(maps);
		resObject.add(empworkDetailMap);
		resObject.add(empworkIndex);
		return resObject;
	}
}
