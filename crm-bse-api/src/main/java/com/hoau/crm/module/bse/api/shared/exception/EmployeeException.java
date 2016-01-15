package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 人员信息EXCEPTION
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class EmployeeException extends BusinessException {
	
	private static final long serialVersionUID = -4272941176864540259L;
	
	/**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.bse.employee.RbNullException";
	
	/**
	 * 工号为空
	 */
	public static final String EMP_CODE_NULL = "crm.bse.employee.EmpCodeNullException";
	
	/**
	 * 用户名为空
	 */
	public static final String USER_NULL = "crm.bse.employee.UserNullException";


	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public EmployeeException(String errCode){
		super();
		super.errCode = errCode;
	}

	public EmployeeException(String code, String msg) {
		super(code, msg);
	}
	
	public EmployeeException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
