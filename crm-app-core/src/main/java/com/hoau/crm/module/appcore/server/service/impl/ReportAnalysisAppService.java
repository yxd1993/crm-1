package com.hoau.crm.module.appcore.server.service.impl;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.server.service.IReportAnalysisAppService;
import com.hoau.crm.module.appcore.api.shared.domain.ReportAnalysisAuthEntity;
import com.hoau.crm.module.appcore.api.shared.domain.ReportAnalysisEntity;
import com.hoau.crm.module.appcore.api.shared.exception.ReportAnalysisException;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.ReportAnalysisAppVo;
import com.hoau.crm.module.appcore.server.dao.ReportAnalysisAppMapper;
import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 自定义报表SERVICE
 * 
 * @author: 何斌
 * @create: 2015年9月8日 下午2:18:58
 */
@Service
public class ReportAnalysisAppService implements IReportAnalysisAppService{
	
	@Resource
	ReportAnalysisAppMapper reportAnalysisAppMapper;

	@Resource
	ILoginService iLoginService;
	
	@Resource
	IDepartmentService iDepartmentService;
	
	@Override
	@Transactional
	public ResponseBaseEntity<ReportAnalysisAppVo> getReportAnalysisInfos(ReportAnalysisAppVo reportAnalysisAppVo,String loginName) {
		ResponseBaseEntity<ReportAnalysisAppVo> responseBaseEntity = new ResponseBaseEntity<ReportAnalysisAppVo>();
		//自定义报表数据结果
		ReportAnalysisAppVo result = new ReportAnalysisAppVo();
		//当前用户角色
		String roleCode = reportAnalysisAppMapper.getRoleCodeByUserCode(loginName);
		//设置默认值
		if(StringUtil.isEmpty(reportAnalysisAppVo.getOrgType())){
			//当前用户角色
			//组织权限菜单
			List<ReportAnalysisAuthEntity> reportAnalysisAuthEntity = this.getReportAnalysisAuthByRoleCode(roleCode);
			result.setReportAnalysisAuthEntities(reportAnalysisAuthEntity);
			reportAnalysisAppVo.setOrgType(reportAnalysisAuthEntity.get(0).getFunctionCode());
			reportAnalysisAppVo.setDataTypeBySort(BseConstants.REPORT_ANALYSIS_DATATYPE_CUSTOMER_DESC);
			reportAnalysisAppVo.setTimeType(BseConstants.REPORT_ANALYSIS_TIMETYPE_CURWEEK);
		}
		String[] strs = reportAnalysisAppVo.getDataTypeBySort().split("-");
		//数据类型
		String dataType = strs[0];
		//排序规则
		String sortType = BseConstants.getSortKey(strs[1]);
		String orgType = reportAnalysisAppVo.getOrgType();
		//查询参数
		Map<String,String> params = new HashMap<String,String>();
		//组织
		params.put("dataType", dataType);
		params.put("timeType", reportAnalysisAppVo.getTimeType());
		params.put("sortType", sortType);
		params.put("orgType", orgType);
				
		//分页
		RowBounds rb = new RowBounds(reportAnalysisAppVo.getStart(),reportAnalysisAppVo.getLimit());
		
		//当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		Set<String> functions = currentUser.getFunctionCodes();
		String deptCode = "";
		if(currentUser.getEmpEntity().getDeptEntity()!=null){
			deptCode = currentUser.getEmpEntity().getDeptEntity().getDeptCode();
		}
		
		//判断所在百分比计算调用哪个存储过程,全部数据已生成好
		if(!functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(reportAnalysisAppVo.getOrgType())){
				params.put("procName", BseConstants.REPORT_ANALYSIS_PERCENT_EXEC_SALE);
			}else{
				params.put("procName", BseConstants.REPORT_ANALYSIS_PERCENT_EXEC);
			}
		}
		//百分比取值
		if(functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			//全部数据直接取值
			params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_3);
		}else if(BseConstants.REPORT_ANALYSIS_ORGTYPE_BUSINESS.equals(reportAnalysisAppVo.getOrgType())){
			//事业部
			params.put("supOrgCode", BseConstants.GROUPCODE);
			params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
			reportAnalysisAppMapper.execPercent(params);
		}else if(this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode)){
			//团队经理或者客户经理,上级部门取值--当前部门编码
			if(BseConstants.TERMMANAGE_CODE.equals(roleCode) 
					|| BseConstants.convertJobCode(BseConstants.MANAGENAME).equals(roleCode)){
				params.put("supOrgCode", deptCode);
				params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
			}else{
				//上级部门取值 -- 当前部门上级部门编码
				params.put("supOrgCode", currentUser.getEmpEntity().getDeptEntity().getSupdeptCode());
				params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
			}
			reportAnalysisAppMapper.execPercent(params);
		}else{
			// 非默认管理区域
			params.put("supOrgCode", deptCode);
			params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
			reportAnalysisAppMapper.execPercent(params);
		}
		
		//查询数据,上级部门赋值
		if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(reportAnalysisAppVo.getOrgType())
				&& !functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			params.put("sale", deptCode);
		}else if(this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode) 
				&& functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			params.put("orgParent", BseConstants.GROUPCODE);
		}else if(this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode)){
			params.put("orgParent", currentUser.getEmpEntity().getDeptEntity().getSupdeptCode());
		}else if(!this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode)
				&& !functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			params.put("orgParent", deptCode);
		}
		
		//是否能点击
		if(BseConstants.REPORT_ANALYSIS_ORGTYPE_STORE.equals(reportAnalysisAppVo.getOrgType()) 
				|| BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(reportAnalysisAppVo.getOrgType())){
			params.put("isClick", BseConstants.NO);
		}else if(BseConstants.REPORT_ANALYSIS_ORGTYPE_ROAD.equals(reportAnalysisAppVo.getOrgType()) 
				&& (BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_ASC.equals(reportAnalysisAppVo.getDataTypeBySort())
						|| BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_DESC.equals(reportAnalysisAppVo.getDataTypeBySort()))){
			//管理区域--路区,统计纬度--门店走访
			params.put("isClick", BseConstants.NO);
		}else if(functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			params.put("isClick", BseConstants.YES);
		}else if(!this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode)){
			params.put("isClick", BseConstants.YES);
		}else{
			params.put("deptCode",currentUser.getEmpEntity().getDeptEntity().getDeptCode());
		}
		
		List<ReportAnalysisEntity> list =  reportAnalysisAppMapper.getReportAnalysisInfos(params, rb);
		//orgType在当前角色菜单中的排序
		for(ReportAnalysisEntity obj :list){
			//客户经理查询负责人和负责人电话
			if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(obj.getOrgType())){
				Map<String,String> saleInfo = reportAnalysisAppMapper.getManagerInfo(obj.getOrgCode());
				if(saleInfo!=null){
					obj.setManagerName(saleInfo.get("empname"));
					obj.setManagerCellphone(saleInfo.get("mobile"));
				}
			}
			//当前管理区域在当前用户角色中排序号
			obj.setSortNum(this.getSortNumInMenu(loginName, obj.getOrgType(),null,null));
			//是否数据顶部
			obj.setHead(BseConstants.YES);
			//人均是否隐藏
			if(BseConstants.REPORT_ANALYSIS_ORGTYPE_STORE.equals(orgType)
				|| BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(orgType)
				|| (BseConstants.REPORT_ANALYSIS_ORGTYPE_ROAD.equals(orgType) 
						&&(BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_ASC.equals(reportAnalysisAppVo.getDataTypeBySort())
							|| BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_DESC.equals(reportAnalysisAppVo.getDataTypeBySort())))){
				obj.setHidden(BseConstants.YES);
			}
		}
		//上级部门和组织名称
		if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(reportAnalysisAppVo.getOrgType())
				&& !functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			result.setSupOrgCode(deptCode);
			result.setOrgName(this.getOrgName(deptCode));
		}else if(this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode) 
				&& functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			result.setSupOrgCode(BseConstants.GROUPCODE);
			result.setOrgName(this.getOrgName(BseConstants.GROUPCODE));
		}else if(this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode)){
			result.setSupOrgCode(currentUser.getEmpEntity().getDeptEntity().getSupdeptCode());
			result.setOrgName(this.getOrgName(currentUser.getEmpEntity().getDeptEntity().getSupdeptCode()));
		}else if(!this.isDefaultOrgType(reportAnalysisAppVo.getOrgType(), roleCode)
				&& !functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			result.setSupOrgCode(deptCode);
			result.setOrgName(this.getOrgName(deptCode));
		}else if(functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
			result.setSupOrgCode(BseConstants.GROUPCODE);
			result.setOrgName(this.getOrgName(BseConstants.GROUPCODE));
		}
		
		result.setOrgType(orgType);
		result.setReportAnalysisEntities(list);
		result.setTotalCount(reportAnalysisAppMapper.countReportAnalysisInfos(params, rb));
		
		responseBaseEntity.setResult(result);
		responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return responseBaseEntity;
	}

	@Override
	public List<ReportAnalysisAuthEntity> getReportAnalysisAuthByRoleCode(
			String roleCode) {
		if(StringUtil.isEmpty(roleCode)){
			throw new ReportAnalysisException(ReportAnalysisException.PARAMS_NULL);
		}
		return reportAnalysisAppMapper.getReportAnalysisAuthByRoleCode(roleCode);
	}

	@Override
	public ResponseBaseEntity<ReportAnalysisAppVo> getReportAnalysisInfoByDeptCode(ReportAnalysisAppVo reportAnalysisAppVo,String loginName) {
		ResponseBaseEntity<ReportAnalysisAppVo> responseBaseEntity = new ResponseBaseEntity<ReportAnalysisAppVo>();
		if(StringUtil.isEmpty(reportAnalysisAppVo.getDataTypeBySort()) || StringUtil.isEmpty(reportAnalysisAppVo.getTimeType())){
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
			responseBaseEntity.setErrorMessage(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
		}else{
			//自定义报表数据结果
			ReportAnalysisAppVo result = new ReportAnalysisAppVo();
			String[] strs = reportAnalysisAppVo.getDataTypeBySort().split("-");
			//数据类型
			String dataType = strs[0];
			//排序规则
			String sortType = BseConstants.getSortKey(strs[1]);
			//查询参数
			Map<String,String> params = new HashMap<String,String>();
			params.put("dataType", dataType);
			params.put("timeType", reportAnalysisAppVo.getTimeType());
			params.put("sortType", sortType);
			params.put("supOrg", reportAnalysisAppVo.getOrgCode());
			
			//百分比取值
			params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_1);
			
			//分页
			RowBounds rb = new RowBounds(reportAnalysisAppVo.getStart(),reportAnalysisAppVo.getLimit());
			//自定义报表数据
			params.put("isClick", BseConstants.YES);
			List<ReportAnalysisEntity> list =  reportAnalysisAppMapper.getReportAnalysisInfos(params, rb);
			for(ReportAnalysisEntity obj :list){
				if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(obj.getOrgType())){
					Map<String,String> saleInfo = reportAnalysisAppMapper.getManagerInfo(obj.getOrgCode());
					if(saleInfo!=null){
						obj.setManagerName(saleInfo.get("empname"));
						obj.setManagerCellphone(saleInfo.get("mobile"));
					}
					obj.setIsClick(BseConstants.NO);
				}else if(BseConstants.REPORT_ANALYSIS_ORGTYPE_STORE.equals(obj.getOrgType())){
					obj.setIsClick(BseConstants.NO);
				}
				obj.setSortNum(this.getSortNumInMenu(loginName, obj.getOrgType(),null,null));
			}
			result.setReportAnalysisEntities(list);
			result.setTotalCount(reportAnalysisAppMapper.countReportAnalysisInfos(params, rb));
			
			responseBaseEntity.setResult(result);
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		}
		return responseBaseEntity;
	}

	private String getSortNumInMenu(String loginName, String orgType,String flag,String isHead) {
		//判断orgType在当前角色菜单中的排序
		//当前用户角色
		String roleCode = reportAnalysisAppMapper.getRoleCodeByUserCode(loginName);
		String sortNum = "";
		Map<String,String> params = new HashMap<String,String>();
		params.put("roleCode", roleCode);
		if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALETERM.equals(orgType) && "1".equals(flag) 
				&& !BseConstants.YES.equals(isHead)){
			params.put("orgType", BseConstants.REPORT_ANALYSIS_ORGTYPE_ROAD);
			sortNum = reportAnalysisAppMapper.getSortNumInMenu(params);
		}else if(BseConstants.REPORT_ANALYSIS_ORGTYPE_STORE.equals(orgType) && BseConstants.TERMMANAGE_CODE.equals(roleCode)){
			params.put("orgType", BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE);
			sortNum = reportAnalysisAppMapper.getSortNumInMenu(params);
		}else{
			params.put("orgType", orgType);
			sortNum = reportAnalysisAppMapper.getSortNumInMenu(params);
		}
		return sortNum;
	}

	@Override
	public boolean isDefaultOrgType(String orgType,String roleCode) {
		if(StringUtil.isEmpty(orgType) || StringUtil.isEmpty(orgType)){
			throw new ReportAnalysisException(ReportAnalysisException.PARAMS_NULL);
		}
		Map<String, String> params = new HashMap<String,String>();
		params.put("orgType", orgType);
		params.put("roleCode", roleCode);
		if(reportAnalysisAppMapper.isDefaultOrgType(params) == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 获取部门名称
	 * 
	 * @param orgCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月8日
	 * @update 
	 */
	private String getOrgName(String orgCode){
		//组织名称
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setDeptCode(orgCode);
		departmentEntity = iDepartmentService.queryDeptByDeptCode(departmentEntity);
		if(BseConstants.GROUPCODE.equals(orgCode)){
			return BseConstants.REPORT_ANALYSIS_NAME;
		}else{
			return departmentEntity.getDeptName();
		}
	}

	@Override
	public ResponseBaseEntity<ReportAnalysisAppVo> getReportAnalysisInfoData(
			ReportAnalysisAppVo reportAnalysisAppVo, String loginName) {
		ResponseBaseEntity<ReportAnalysisAppVo> responseBaseEntity = new ResponseBaseEntity<ReportAnalysisAppVo>();
 		if(StringUtil.isEmpty(reportAnalysisAppVo.getDataTypeBySort()) || StringUtil.isEmpty(reportAnalysisAppVo.getTimeType())){
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
			responseBaseEntity.setErrorMessage(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
		}else{		
			//自定义报表数据结果
			ReportAnalysisAppVo result = new ReportAnalysisAppVo();
			//自定义报表数据
			UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
			Set<String> functions = currentUser.getFunctionCodes();
			String deptCode = "";
			if(currentUser.getEmpEntity().getDeptEntity()!=null){
				deptCode = currentUser.getEmpEntity().getDeptEntity().getDeptCode();
			}
			//当前用户角色
			String roleCode = reportAnalysisAppMapper.getRoleCodeByUserCode(loginName);
			
			//是否返回
			if(BseConstants.YES.equals(reportAnalysisAppVo.getIsHead())){
				if(BseConstants.REPORT_ANALYSIS_ORGTYPE_BUSINESS.equals(reportAnalysisAppVo.getSupOrgType())
						|| functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
					reportAnalysisAppVo.setOrgCode(BseConstants.GROUPCODE);
				}else{
					reportAnalysisAppVo.setOrgCode(deptCode);
				}
				result.setIsHidden(BseConstants.YES);
			}else if(BseConstants.REPORT_ANALYSIS_ISRETURN.equals(reportAnalysisAppVo.getIsReturn())){
				String supOrgCode = reportAnalysisAppMapper.getSupOrgCode(reportAnalysisAppVo.getOrgCode());
				reportAnalysisAppVo.setOrgCode(supOrgCode);
			}
			
			//上级部门编码
			if(!StringUtil.isEmpty(reportAnalysisAppVo.getSupOrgCode())){
				result.setSupOrgCode(reportAnalysisAppVo.getSupOrgCode());
			}else{
				result.setSupOrgCode(reportAnalysisAppVo.getOrgCode());
			}
			
			//区域类型
			String[] strs = reportAnalysisAppVo.getDataTypeBySort().split("-");
			//数据类型
			String dataType = strs[0];
			//排序规则
			String sortType = BseConstants.getSortKey(strs[1]);
			//查询参数
			Map<String,String> params = new HashMap<String,String>();
			params.put("dataType", dataType);
			params.put("timeType", reportAnalysisAppVo.getTimeType());
			params.put("sortType", sortType);
			if(BseConstants.REPORT_ANALYSIS_ISRETURN.equals(reportAnalysisAppVo.getIsReturn())
					&& BseConstants.YES.equals(reportAnalysisAppVo.getIsHead())){
				params.put("orgType", reportAnalysisAppVo.getSupOrgType());
			}
			
			//分页
			RowBounds rb = new RowBounds(reportAnalysisAppVo.getStart(),reportAnalysisAppVo.getLimit());
			
			if(!BseConstants.REPORT_ANALYSIS_ISRETURN.equals(reportAnalysisAppVo.getIsReturn())){
				params.put("supOrg", reportAnalysisAppVo.getOrgCode());
			}else if(StringUtil.isEmpty(reportAnalysisAppVo.getIsHead())){
				params.put("supOrg", reportAnalysisAppVo.getOrgCode());
			}else if(!functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)
					&& !BseConstants.YES.equals(reportAnalysisAppVo.getIsHead())){
				params.put("supOrg", reportAnalysisAppVo.getOrgCode());
			}else if(!functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)
					&& BseConstants.YES.equals(reportAnalysisAppVo.getIsHead())){
				params.put("orgParent", reportAnalysisAppVo.getOrgCode());
			}
			
			//是否点击
			if(BseConstants.REPORT_ANALYSIS_ISRETURN.equals(reportAnalysisAppVo.getIsReturn())
					&& !functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)
					&& this.isDefaultOrgType(reportAnalysisAppVo.getSupOrgType(), roleCode)){
				params.put("deptCode",currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			}else{
				params.put("isClick", BseConstants.YES);
			}
			
			//百分比取值
			if(!functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
				if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(reportAnalysisAppVo.getOrgType())){
					params.put("procName", BseConstants.REPORT_ANALYSIS_PERCENT_EXEC_SALE);
				}else{
					params.put("procName", BseConstants.REPORT_ANALYSIS_PERCENT_EXEC);
				}
			}
			if(!BseConstants.YES.equals(reportAnalysisAppVo.getIsHead())){
				params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_1);
			}else if(BseConstants.YES.equals(reportAnalysisAppVo.getIsHead()) 
					&& functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)){
				params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_3);
			}else if(BseConstants.YES.equals(reportAnalysisAppVo.getIsHead()) 
					&& !functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)
					&& BseConstants.REPORT_ANALYSIS_ORGTYPE_BUSINESS.equals(reportAnalysisAppVo.getOrgType())){
				params.put("supOrgCode", BseConstants.GROUPCODE);
				params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
				reportAnalysisAppMapper.execPercent(params);
			}else if(BseConstants.YES.equals(reportAnalysisAppVo.getIsHead()) 
					&& !functions.contains(BseConstants.REPORT_ANALYSIS_ALLDATA)
					&& this.isDefaultOrgType(reportAnalysisAppVo.getSupOrgType(), roleCode)){
				if(BseConstants.TERMMANAGE_CODE.equals(roleCode) 
						|| BseConstants.convertJobCode(BseConstants.MANAGENAME).equals(roleCode)){
					params.put("supOrgCode", deptCode);
					params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
				}else{
					params.put("supOrgCode", currentUser.getEmpEntity().getDeptEntity().getSupdeptCode());
					params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
				}
				reportAnalysisAppMapper.execPercent(params);
			}else{
				params.put("supOrgCode", deptCode);
				params.put("percent", BseConstants.REPORT_ANALYSIS_PERCENT_2);
				reportAnalysisAppMapper.execPercent(params);
			}
			
			List<ReportAnalysisEntity> list =  reportAnalysisAppMapper.getReportAnalysisInfos(params, rb);
			Set<String> orgTypes = new HashSet<String>();
			for(ReportAnalysisEntity obj :list){
				if(BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(obj.getOrgType())){
					Map<String,String> saleInfo = reportAnalysisAppMapper.getManagerInfo(obj.getOrgCode());
					if(saleInfo!=null){
						obj.setManagerName(saleInfo.get("empname"));
						obj.setManagerCellphone(saleInfo.get("mobile"));
					}
					obj.setIsClick(BseConstants.NO);
				}else if(BseConstants.REPORT_ANALYSIS_ORGTYPE_ROAD.equals(obj.getOrgType()) 
						&& (BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_ASC.equals(reportAnalysisAppVo.getDataTypeBySort())
								|| BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_DESC.equals(reportAnalysisAppVo.getDataTypeBySort()))){
					obj.setIsClick(BseConstants.NO);
				}else if(BseConstants.REPORT_ANALYSIS_ORGTYPE_STORE.equals(obj.getOrgType())){
					obj.setIsClick(BseConstants.NO);
				}
				obj.setSortNum(this.getSortNumInMenu(loginName, obj.getOrgType(),"1",reportAnalysisAppVo.getIsHead()));
				if(BseConstants.YES.equals(reportAnalysisAppVo.getIsHead())){
					obj.setHead(BseConstants.YES);
				}
				orgTypes.add(obj.getOrgType());
				//人均是否隐藏
				if(BseConstants.REPORT_ANALYSIS_ORGTYPE_STORE.equals(obj.getOrgType())
						|| BseConstants.REPORT_ANALYSIS_ORGTYPE_SALE.equals(obj.getOrgType())
						|| (BseConstants.REPORT_ANALYSIS_ORGTYPE_ROAD.equals(obj.getOrgType()) 
								&&(BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_ASC.equals(reportAnalysisAppVo.getDataTypeBySort())
									|| BseConstants.REPORT_ANALYSIS_DATATYPE_VISIT_DESC.equals(reportAnalysisAppVo.getDataTypeBySort())))){
					obj.setHidden(BseConstants.YES);
				}
			}
			// 统一返回的管理区域(点击大区,返回值中会有(路区(3)和团队经理(5)))
			if(list!=null && list.size()>0 && orgTypes.size() ==1){
				result.setOrgType(list.get(0).getOrgType());
			}else if(orgTypes.size() == 2){
				result.setOrgType(BseConstants.REPORT_ANALYSIS_ORGTYPE_ROAD);
			}
			if(BseConstants.REPORT_ANALYSIS_ISRETURN.equals(reportAnalysisAppVo.getIsReturn())){
				result.setSupOrgType(Integer.parseInt(reportAnalysisAppVo.getSupOrgType())-1+"");
			}else{
				result.setSupOrgType(reportAnalysisAppVo.getOrgType());
			}
			//当前列表下次返回是否返回顶部赋值
			if(!StringUtil.isEmpty(reportAnalysisAppVo.getSupOrgCode())){
				result.setIsHead(BseConstants.YES);
			}else if(BseConstants.REPORT_ANALYSIS_ISRETURN.equals(reportAnalysisAppVo.getIsReturn())
					 && (Integer.valueOf(reportAnalysisAppVo.getFirstSort())-Integer.valueOf(reportAnalysisAppVo.getSupOrgType())==-1)){
				result.setIsHead(BseConstants.YES);
			}
			//组织名称
			result.setOrgName(this.getOrgName(reportAnalysisAppVo.getOrgCode()));
			
			result.setReportAnalysisEntities(list);
			result.setTotalCount(reportAnalysisAppMapper.countReportAnalysisInfos(params, rb));
			
			responseBaseEntity.setResult(result);
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		}
		return responseBaseEntity;
	}
}
