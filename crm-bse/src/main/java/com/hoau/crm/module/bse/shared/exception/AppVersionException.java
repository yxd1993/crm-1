package com.hoau.crm.module.bse.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;
/**
 * App版本信息Exception
 * 
 * @author: 潘强
 * @create: 2015年7月30日 
*/
public class AppVersionException extends BusinessException {

	private static final long serialVersionUID = 8296234147390779047L;

	/**
	 *分页参数为空 
	 */
	public static final String QUERY_APPVERSION_RB_NULL="crm.bas.appversion.queryAppVersionRbNullException";
	
	/**
	 *查询参数为空 
	 */
	public static final String  QUERY_APPVERSION_PARAM_NULL="crm.bas.appversion.queryAppVersionParamNullException";
	
	/**
	 * 删除App版本信息参数为空
	 */
	public static final String APPVERSIONIDS_NULL="crm.bas.appversion.delectAppVersionIdsNullException";

	public static final String APPVERSION_NULL = "crm.bas.appversion.addAppVersionNullException";

	public static final String APPVERSIONCODE = "crm.bas.appversion.addAppVersionCodeException";
	
	/**
	 * App版本应经被删除
	 */
	public static final String APPVERSIONFILE ="crm.bas.appversion.ExtAppVersionFileException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public AppVersionException(String errCode){
		super();
		super.errCode = errCode;
	}

	public AppVersionException(String code, String msg) {
		super(code, msg);
	}
	
	public AppVersionException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
