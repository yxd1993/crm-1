package com.hoau.crm.module.bse.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 客户操作Exception
 * 
 * @author: 潘强
 * @create: 2015年7月20日 
*/

public class UserOperationException extends BusinessException {

	private static final long serialVersionUID = 8493068143019572967L;

	/**
	 * 分页参数为空
	 */
	public static final String QUERY_USEROPERATION_RB_NULL="crm.bse.userOperation.queryUserOperationRbNullException";
	
	/**
	 * 查询参数为空
	 */
	public static final String QUERY_USEROPERATION_PARAM_NULL="crm.bse.userOperation.queryUserOperationParamNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public UserOperationException(String errCode){
		super();
		super.errCode = errCode;
	}

	public UserOperationException(String code, String msg) {
		super(code, msg);
	}
	
	public UserOperationException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
