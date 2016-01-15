package com.hoau.crm.module.sales.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;
/**
 * 客户扫描签到Exception
 * 
 * @author: 潘强
 * @create: 2015年7月27日 
*/
public class SweepSignException extends BusinessException {

	private static final long serialVersionUID = -1048418765102863550L;
	
	/**
	 *分页参数为空 
	 */
	public static final String QUERY_SWEEPSIGN_RB_NULL="crm.sales.sweepsign.querySweepSignRbNullException";
	
	/**
	 *查询参数为空 
	 */
	public static final String  QUERY_SWEEPSIGN_PARAM_NULL="crm.sales.sweepsign.querySweepSignParamNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public SweepSignException(String errCode){
		super();
		super.errCode = errCode;
	}

	public SweepSignException(String code, String msg) {
		super(code, msg);
	}
	
	public SweepSignException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
