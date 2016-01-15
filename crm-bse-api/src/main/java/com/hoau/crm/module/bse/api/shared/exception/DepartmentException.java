package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 组织信息EXCEPTION
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class DepartmentException extends BusinessException {
	
	private static final long serialVersionUID = -8984769064487718498L;
	
	/**
	 * 父节点为空
	 */
	public static final String PARENT_NODE_NULL = "crm.bse.department.ParentNodeNullException";
	
	/**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.bse.deptartment.RbNullException";
	
	/**
	 * 组织编号参数为空
	 */
	public static final String DEPT_CODE_NULL = "crm.bse.deptartment.DeptCodeNullException";

	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public DepartmentException(String errCode){
		super();
		super.errCode = errCode;
	}

	public DepartmentException(String code, String msg) {
		super(code, msg);
	}
	
	public DepartmentException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
