package com.hoau.crm.module.bse.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.EmployeeException;
import com.hoau.crm.module.bse.api.shared.vo.EmployeeVo;
import com.hoau.crm.module.bse.server.dao.EmployeeMapper;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 人员信息SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class EmployeeService implements IEmployeeService {

	@Resource
	private EmployeeMapper employeeMapper;

	@Override
	public List<EmployeeEntity> queryEmpList(EmployeeVo employeeVo, RowBounds rb) {
		// 判断分页参数是否为空
		if (rb == null) {
			throw new EmployeeException(EmployeeException.RB_NULL);
		}
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		if (employeeVo != null
				&& !StringUtil.isEmpty(employeeVo.getSelectorParam())) {
			params.put("selectorParam", "%" + employeeVo.getSelectorParam()
					+ "%");
		}
		return employeeMapper.getEmployeeList(params, rb);
	}

	@Override
	public long countEmp(EmployeeVo employeeVo) {
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		if (employeeVo != null
				&& !StringUtil.isEmpty(employeeVo.getSelectorParam())) {
			params.put("selectorParam", "%" + employeeVo.getSelectorParam()
					+ "%");
		}
		return employeeMapper.countEmp(params);
	}

	@Override
	public EmployeeEntity queryEmployeeByEmpcode(EmployeeEntity emp) {
		// 判断工号是否为空
		if (emp == null || StringUtil.isEmpty(emp.getEmpCode())) {
			throw new EmployeeException(EmployeeException.EMP_CODE_NULL);
		}
		return employeeMapper.getEmployeeByEmpcode(emp);
	}

	@Override
	public List<EmployeeEntity> querySaleEmpsByDeptCode(UserEntity currentUser,EmployeeVo employeeVo) {
		if(currentUser == null){
			throw new EmployeeException(EmployeeException.USER_NULL);
		}
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		Set<String> functions =  currentUser.getFunctionCodes();
		if(functions.contains(BseConstants.ALLDATA_SALEEMP)){
			if (employeeVo != null && !StringUtil.isEmpty(employeeVo.getSelectorParam())) {
				params.put("selectorParam", "%" + employeeVo.getSelectorParam()	+ "%");
			}
			return employeeMapper.getEmployeesByDeptCode(params);
		}else{
			String deptCode = currentUser.getEmpEntity().getDeptEntity().getDeptCode();
			if (employeeVo != null && !StringUtil.isEmpty(employeeVo.getSelectorParam())) {
				params.put("selectorParam", "%" + employeeVo.getSelectorParam()	+ "%");
			}
			params.put("deptCode", deptCode);
			return employeeMapper.getEmployeesByDeptCode(params);
		}
	}
}