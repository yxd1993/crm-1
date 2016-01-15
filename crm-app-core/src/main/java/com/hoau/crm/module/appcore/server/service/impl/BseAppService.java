package com.hoau.crm.module.appcore.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.IBseAppService;
import com.hoau.crm.module.appcore.api.shared.domain.AppSweepCardsEntity;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.BseAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.DataDictionaryAppVo;
import com.hoau.crm.module.appcore.server.dao.AppSweepCardsMapper;
import com.hoau.crm.module.bse.api.server.service.IDataDictionaryService;
import com.hoau.crm.module.bse.api.server.service.IDataDictionaryValueService;
import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.appcore.api.shared.exception.AppSweepCardsException;
import com.hoau.crm.module.bse.api.shared.exception.DepartmentException;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;
import com.hoau.crm.module.bse.api.shared.vo.EmployeeVo;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 数据字典APP SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-6-18
 */
@Service
public class BseAppService implements IBseAppService {

	@Resource
	private IDataDictionaryValueService dataDictionaryValueService;

	@Resource
	private IDataDictionaryService dataDictionaryService;

	@Resource
	private IDepartmentService iDepartmentService;

	@Resource
	private ILoginService iLoginService;

	@Resource
	private IEmployeeService iEmployeeService;
	
	@Resource
	private AppSweepCardsMapper appSweepCardsMapper;
	
	@Override
	public ResponseBaseEntity<DataDictionaryAppVo> queryDataDictionaryVersion() {
		long lastChangeVersionNo = dataDictionaryValueService
				.getLastChangeVersionNo();
		// 返回值
		DataDictionaryAppVo dataDictVo = new DataDictionaryAppVo();
		dataDictVo.setDataVersion(lastChangeVersionNo);
		ResponseBaseEntity<DataDictionaryAppVo> result = new ResponseBaseEntity<DataDictionaryAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(dataDictVo);
		return result;
	}

	@Override
	public ResponseBaseEntity<DataDictionaryAppVo> queryAllDataDictionary() {
		List<DataDictionaryEntity> dataList = dataDictionaryService
				.queryAllDataDictionary();
		long lastChangeVersionNo = dataDictionaryValueService
				.getLastChangeVersionNo();
		// 返回值
		DataDictionaryAppVo dataDictVo = new DataDictionaryAppVo();
		dataDictVo.setDataDictList(dataList);
		dataDictVo.setDataVersion(lastChangeVersionNo);
		ResponseBaseEntity<DataDictionaryAppVo> result = new ResponseBaseEntity<DataDictionaryAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(dataDictVo);
		return result;
	}

	@Override
	public ResponseBaseEntity<BseAppVo> queryDeptList(BseAppVo bseAppVo,
			String loginName) {
		RowBounds rb;
		// 分页查询条件
		if (bseAppVo == null || bseAppVo.getLimit() == 0) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		rb = new RowBounds(bseAppVo.getStart(), bseAppVo.getLimit());
		DepartmentVo searchVo = new DepartmentVo();
		if (!StringUtil.isEmpty(bseAppVo.getSelectorParam())) {
			searchVo.setSelectorParam(bseAppVo.getSelectorParam());
		}
		// 当前用户
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<DepartmentEntity> deptList = iDepartmentService.queryDeptList(
				searchVo, rb, user);
		// 前台只显示分页的搜索结果，不会显示下一页
		long totalCount = 0;
		//long totalCount = iDepartmentService.countDept(searchVo, user);
		BseAppVo rBseVo = new BseAppVo();
		rBseVo.setDeptEntityList(deptList);
		rBseVo.setTotalCount(totalCount);
		ResponseBaseEntity<BseAppVo> result = new ResponseBaseEntity<BseAppVo>();
		result.setResult(rBseVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<BseAppVo> queryDeptSuperiorDept(BseAppVo bseAppVo,String loginName) {
		DepartmentEntity deptEntity = new DepartmentEntity();
		if (bseAppVo != null && !StringUtil.isEmpty(bseAppVo.getLogistCode())) {
			deptEntity.setLogistCode(bseAppVo.getLogistCode());
		} else {
			throw new DepartmentException(DepartmentException.DEPT_CODE_NULL);
		}
		// 当前用户
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		DepartmentVo deptVo = iDepartmentService
				.queryDeptSuperiorDept(deptEntity,user);
		// 返回值
		BseAppVo rBseAppVo = new BseAppVo();
		rBseAppVo.setDeptVo(deptVo);
		ResponseBaseEntity<BseAppVo> result = new ResponseBaseEntity<BseAppVo>();
		result.setResult(rBseAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@Override
	public ResponseBaseEntity<BseAppVo> queryDeptSuperiorDeptByCurrUser(BseAppVo bseAppVo, String loginName) {
		Map<String, String> customerInfo = new HashMap<String, String>();
		if (bseAppVo != null && !StringUtil.isEmpty(bseAppVo.getLogistCode())) {
			customerInfo.put("logistCode", bseAppVo.getLogistCode());
		} else {
			throw new DepartmentException(DepartmentException.DEPT_CODE_NULL);
		}
		if(StringUtil.isEmpty(bseAppVo.getCustomerManageEmpCode()) || StringUtil.isEmpty(bseAppVo.getCustomerManageEmpName())){
			customerInfo.put("customerManageEmpCode", null);
			customerInfo.put("customerManageEmpName", null);
		} else {
			customerInfo.put("customerManageEmpCode", bseAppVo.getCustomerManageEmpCode());
			customerInfo.put("customerManageEmpName", bseAppVo.getCustomerManageEmpName());
		}
		// 当前用户
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		DepartmentVo deptVo = iDepartmentService
				.queryDeptSuperiorDeptByCurrUser(customerInfo, user);
		// 返回值
		BseAppVo rBseAppVo = new BseAppVo();
		rBseAppVo.setDeptVo(deptVo);
		ResponseBaseEntity<BseAppVo> result = new ResponseBaseEntity<BseAppVo>();
		result.setResult(rBseAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<BseAppVo> queryEmpList(BseAppVo bseAppVo) {
		RowBounds rb;
		// 分页查询条件
		if (bseAppVo == null || bseAppVo.getLimit() == 0) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		rb = new RowBounds(bseAppVo.getStart(), bseAppVo.getLimit());
		EmployeeVo employeeVo = new EmployeeVo();
		if (!StringUtil.isEmpty(bseAppVo.getSelectorParam())) {
			employeeVo.setSelectorParam(bseAppVo.getSelectorParam());
		}
		// 查询人员信息
		List<EmployeeEntity> empList = iEmployeeService.queryEmpList(
				employeeVo, rb);
		long count = iEmployeeService.countEmp(employeeVo);
		// 返回值
		BseAppVo rBseAppVo = new BseAppVo();
		rBseAppVo.setEmpList(empList);
		rBseAppVo.setTotalCount(count);
		ResponseBaseEntity<BseAppVo> result = new ResponseBaseEntity<BseAppVo>();
		result.setResult(rBseAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public ResponseBaseEntity insertSweepCardsInfo(AppSweepCardsEntity appSweepCardsEntity,String loginName) {
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		String uuid = UUIDUtil.getUUID();
		if(StringUtils.isEmpty(appSweepCardsEntity)){
			throw new AppSweepCardsException(AppSweepCardsException.SWEEP_INFO_ISNULL);
		}
		appSweepCardsEntity.setId(uuid);
		appSweepCardsEntity.setCreateUser(currentUser.getUserName());
		appSweepCardsMapper.insertSweepCardsInfo(appSweepCardsEntity);
		ResponseBaseEntity res = new ResponseBaseEntity();
		res.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return res;
	}

	@Override
	public ResponseBaseEntity<BseAppVo> querySaleEmpLists(String loginName,BseAppVo bseAppVo) {
		// 当前用户
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		EmployeeVo employeeVo = new EmployeeVo();
		if (!StringUtil.isEmpty(bseAppVo.getSelectorParam())) {
			employeeVo.setSelectorParam(bseAppVo.getSelectorParam());
		}
		List<EmployeeEntity> empLists =iEmployeeService.querySaleEmpsByDeptCode(user,employeeVo);
		// 返回值
		BseAppVo rBseAppVo = new BseAppVo();
		rBseAppVo.setEmpList(empLists);
		ResponseBaseEntity<BseAppVo> result = new ResponseBaseEntity<BseAppVo>();
		result.setResult(rBseAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
