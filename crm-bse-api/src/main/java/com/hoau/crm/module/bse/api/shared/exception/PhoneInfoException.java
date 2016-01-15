package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 手机号信息查询异常类
 *
 * @author 蒋落琛
 * @date 2015-6-9
 */
public class PhoneInfoException extends BusinessException {


	private static final long serialVersionUID = -5622426289804672119L;
	
	/**
     * 手机号为空异常
     */
    public static final String PHONE_NULL = "crm.bse.phoneinfo.PhoneNullException";
    
    public PhoneInfoException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public PhoneInfoException(String code, String msg) {
    	super(code, msg);
    }

    public PhoneInfoException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
