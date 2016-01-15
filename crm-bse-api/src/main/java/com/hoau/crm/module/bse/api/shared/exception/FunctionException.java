package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 菜单权限EXCEPTION
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class FunctionException extends BusinessException {
	
	private static final long serialVersionUID = -3422399009620737953L;
	
	/**
	 * 功能节点不能为空
	 */
	public static final String NODE_NULL = "crm.bse.function.FunctionNodeNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public FunctionException(String errCode){
		super();
		super.errCode = errCode;
	}

	public FunctionException(String code, String msg) {
		super(code, msg);
	}
	
	public FunctionException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
