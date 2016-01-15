package com.hoau.crm.module.appcore.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 自定义报表EXCEPTION
 * 
 * @author: 何斌
 * @create: 2015年9月8日 下午5:10:01
 */
public class ReportAnalysisException extends BusinessException {

	private static final long serialVersionUID = -1982586715098015347L;

	/**
	 *参数为空
	 */
	public static final String PARAMS_NULL = "crm.appcore.reportAnalysis.ParamsNullException";

	/**
	 *结果为空
	 */
	public static final String RESULT_NULL = "crm.appcore.reportAnalysis.ResultNullException";
	
	public ReportAnalysisException(String code) {
		super();
		super.errCode = code;
	}

	public ReportAnalysisException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public ReportAnalysisException(String code, String msg) {
		super(code, msg);
	}
}
