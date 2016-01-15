package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.EmployeeVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 人员ACTION
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Controller
@Scope("prototype")
public class EmployeeAction extends AbstractAction {

	private static final long serialVersionUID = 2707503334590539749L;
	
	/**
	 * 人员信息VO
	 */
	private EmployeeVo employeeVo;

	/**
	 * 人员信息
	 */
	private EmployeeEntity empEntity;

	/**
	 * 人员信息集合
	 */
	private List<EmployeeEntity> empList;
	
	/**
	 * 人员信息SERVICE
	 */
	@Resource
	private IEmployeeService iEmployeeService;
	
	
	/**
	 * 分页查询人员信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String queryEmpList(){
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			empList = iEmployeeService.queryEmpList(employeeVo, rb);
			this.setTotalCount(iEmployeeService.countEmp(employeeVo));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据工号查询人员信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String queryEmployeeByEmpcode(){
		try {
			empEntity = iEmployeeService.queryEmployeeByEmpcode(empEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 查询部门下所有人员(客户经理及团队经理)
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月1日
	 * @update 
	 */
	public String querySaleEmpsByDeptCode(){
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			empList = iEmployeeService.querySaleEmpsByDeptCode(currentUser,employeeVo);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	public EmployeeEntity getEmpEntity() {
		return empEntity;
	}

	public void setEmpEntity(EmployeeEntity empEntity) {
		this.empEntity = empEntity;
	}

	public List<EmployeeEntity> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeEntity> empList) {
		this.empList = empList;
	}

	public EmployeeVo getEmployeeVo() {
		return employeeVo;
	}

	public void setEmployeeVo(EmployeeVo employeeVo) {
		this.employeeVo = employeeVo;
	}
}
