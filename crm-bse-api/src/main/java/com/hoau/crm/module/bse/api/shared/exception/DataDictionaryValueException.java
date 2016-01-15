package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 数据字典-值 的异常类
 * @author 高佳
 * @date 2015年5月14日
 */
public class DataDictionaryValueException extends BusinessException {

    private static final long serialVersionUID = 9063168317009336403L;

    /**
     * "数据字典"的业务异常ERROR_KEY
     */
    public static final String EXIST = "crm.bse.dic.exist";
    
    public DataDictionaryValueException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public DataDictionaryValueException(String code, String msg) {
    	super(code, msg);
    }

    public DataDictionaryValueException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
