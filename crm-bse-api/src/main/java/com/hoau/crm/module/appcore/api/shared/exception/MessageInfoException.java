package com.hoau.crm.module.appcore.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 用户信息模块异常
 *
 * @author 蒋落琛
 * @date 2015-6-25
 */
public class MessageInfoException extends BusinessException {
	
	private static final long serialVersionUID = -6599527517275125047L;
	
	/**
	 * 消息信息为空
	 */
	public static final String MESSAGEINFO_NULL = "crm.appcore.userapp.MessageInfoException";
	
	/**
	 * 消息类型为空
	 */
	public static final String MESSAGE_TYPE_NULL = "crm.appcore.userapp.MessageTypeException";
	
	/**
	 * 发送时间不能小于当前时间
	 */
	public static final String MESSAGE_SENDTIME_EXCEPTION = "crm.appcore.userapp.messageSendTimeException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public MessageInfoException(String errCode){
		super();
		super.errCode = errCode;
	}

	public MessageInfoException(String code, String msg) {
		super(code, msg);
	}
	
	public MessageInfoException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

}
