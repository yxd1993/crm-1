package com.hoau.crm.module.bse.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 授权信息Exception
 * 
 * @author: 潘强
 * @create: 2015年7月30日 
*/
public class AuthorizationException extends BusinessException {

	private static final long serialVersionUID = -1686588907215768462L;

	/**
	 * 删除授权信息参数为空
	 */
	public static final String AUTHORIZATION_NULL="crm.bas.authorization.delectAuthorizationIdsNullException";
	
	/**
	 *分页参数为空 
	 */
	public static final String QUERY_AUTHORIZATION_RB_NULL="crm.bas.authorization.queryAuthorizationRbNullException";
	
	/**
	 *查询参数为空 
	 */
	 public static final String QUERY_AUTHORIZATION_PARAM_NULL="crm.bas.authorization.queryAuthorizationParamNullException";       
	 
	 /**
	  *授权结束时间不能小于当前时间
	  */
	 public static final String ADD_AUTHORIZATION_DATETIME_INVALID="crm.bas.authorization.addAuthorizationDatetimeInvalidException";
	 
	 /**
	  *授权结束时间不能小于授权开始时间
	  */
	 public static final String ADD_DATETIME_INVALID="crm.bas.authorization.addDatetimeInvalidException";
	 
	 /**
	  *被授权人只能被一个授权人授权
	  */
	 public static final String ADD_AUTHORIZEDPERSON_INVALID="crm.bas.authorization.addAuthorizedpersonInvalidException";
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public AuthorizationException(String errCode){
		super();
		super.errCode = errCode;
	}

	public AuthorizationException(String code, String msg) {
		super(code, msg);
	}
	
	public AuthorizationException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
