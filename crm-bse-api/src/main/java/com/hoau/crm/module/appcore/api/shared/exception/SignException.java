package com.hoau.crm.module.appcore.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;


/**
 * 签到模块异常
 * 
 * @author: 何斌
 * @create: 2015年7月3日 上午10:02:17
 */
public class SignException extends BusinessException {
	
	private static final long serialVersionUID = 9164419462650419257L;
	
	/**
	 * 参数为空
	 */
	public static final String PARAM_NULL = "crm.appcore.sign.ParamNullException";
	
	/**
	 * 签到信息保存失败
	 */
	public static final String SAVE_SIGN_FAILURE_NULL = "crm.appcore.sign.SaveSignFailureNullException";
	
	/**
	 * 此签到信息存在
	 */
	public static final String ADD_SIGN_IS_EXIST = "crm.AddSignIsExist.Exception";
	
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
