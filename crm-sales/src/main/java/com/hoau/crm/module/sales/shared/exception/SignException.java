package com.hoau.crm.module.sales.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 客户签到Exception
 * 
 * @author: 潘强
 * @create: 2015年7月21日 
*/
public class SignException extends BusinessException{

	private static final long serialVersionUID = -4053631595517971885L;
	
	/**
	 * 分页参数为空
	 */
	public static final String QUERY_SIGN_RB_NULL="crm.sales.sign.querySignRbNullException";
	
	/**
	 * 查询参数为空
	 */
	public static final String QUERY_SIGN_PARAM_NULL="crm.sales.sign.querySignParamNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public SignException(String errCode){
		super();
		super.errCode = errCode;
	}

	public SignException(String code, String msg) {
		super(code, msg);
	}
	
	public SignException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
