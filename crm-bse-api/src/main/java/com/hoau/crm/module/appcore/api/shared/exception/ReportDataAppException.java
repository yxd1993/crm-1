package com.hoau.crm.module.appcore.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 报表模块异常定义
 *
 * @author 蒋落琛
 * @date 2015-7-13
 */
public class ReportDataAppException extends BusinessException {
	
	private static final long serialVersionUID = 45416285197208683L;
	/**
	 *查看报表用户为空
	 */
	public static final String LOGININFO_NULL = "crm.appcore.reportData.ReportUserLoginIsNullException";
	/**
	 * 报表类型为空
	 */
	public static final String REPORTDATA_TYPE_NULL = "crm.appcore.reportData.ReportDataIsNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public ReportDataAppException(String errCode){
		super();
		super.errCode = errCode;
	}

	public ReportDataAppException(String code, String msg) {
		super(code, msg);
	}
	
	public ReportDataAppException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
