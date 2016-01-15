package com.hoau.crm.module.customer.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 客户信息EXCEPTION
 *
 * @author 蒋落琛
 * @date 2015-5-20
 */
public class CustomerResourcePoolException extends BusinessException {

    private static final long serialVersionUID = 9063168317009336403L;

    /**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.customer.customerResourcePool.RbNullException";
	
	/**
	 * 客户信息为空
	 */
	public static final String CUSTOMER_NULL = "crm.customer.customerResourcePool.CustomerNullException";
	
	/**
	 * 参数为空
	 */
	public static final String PARAM_NULL = "crm.customer.customerResourcePool.ParamNullException";
	
	/**
	 * 主键为空
	 */
	public static final String PID_NULL = "crm.customer.customerResourcePool.PidNullException";
	
    public CustomerResourcePoolException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public CustomerResourcePoolException(String code, String msg) {
    	super(code, msg);
    }

    public CustomerResourcePoolException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
