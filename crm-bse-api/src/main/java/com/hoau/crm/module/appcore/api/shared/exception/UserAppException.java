package com.hoau.crm.module.appcore.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 用户信息模块异常
 *
 * @author 蒋落琛
 * @date 2015-6-25
 */
public class UserAppException extends BusinessException {
	
	private static final long serialVersionUID = -6599527517275125047L;
	
	/**
	 * 百度推送账号信息为空
	 */
	public static final String BAIDU_PUSHUSERINIFO_NULL = "crm.appcore.userapp.BaiduPushUserInfoException";
	
	/**
	 * 客户端系统标识错误
	 */
	public static final String SYSTEM_INFO_ERROR = "crm.appcore.userapp.SystemInfoError";
	
	/**
	 * 接收状态参数错误
	 */
	public static final String RECEIVE_STATUS_PARAMS_ERROR = "crm.appcore.userapp.ReceiveStatusParamsError";
	
	/**
	 * 问题反馈信息为空
	 */
	public static final String QUESTION_INFO_NULL = "crm.appcore.userapp.QuestionInfoNullException";
	
	/**
	 * 手机信息为空
	 */
	public static final String MOBILE_INFO_NULL = "crm.appcore.userapp.MobileInfoNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public UserAppException(String errCode){
		super();
		super.errCode = errCode;
	}

	public UserAppException(String code, String msg) {
		super(code, msg);
	}
	
	public UserAppException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
