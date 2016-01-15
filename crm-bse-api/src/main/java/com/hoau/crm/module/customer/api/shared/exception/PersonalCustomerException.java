package com.hoau.crm.module.customer.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 个人各户业务异常
 * @author: 何斌
 * @create: 2015年6月21日 上午8:38:25
 */
public class PersonalCustomerException extends BusinessException{

	private static final long serialVersionUID = -5766994931240395680L;
	
	/**
	 * 分页参数为空
	 */
	public static final String PERSONALCUSTOMER_QUERY_RB_NULL = "crm.customer.personalCustomer.queryPersonalCustomerRbNullException";


	/**
	 * 新增参数为空
	 */
	public static final String PERSONALCUSTOMER_ADD_PARAM_NULL = "crm.customer.personalCustomer.addPersonalCustomerParamNullException";
	
	/**
	 * 删除参数为空
	 */
	public static final String PERSONALCUSTOMER_DELETE_PARAM_NULL = "crm.customer.personalCustomer.deletePersonalCustomerParamNullException";

	/**
	 * 修改参数为空
	 */
	public static final String PERSONALCUSTOMER_UPDATE_PARAM_NULL = "crm.customer.personalCustomer.updatePersonalCustomerParamNullException";
	
	/**
	 *查看参数为空
	 */
	public static final String PERSONALCUSTOMER_QUERY_PARAM_NULL = "crm.customer.personalCustomer.queryPersonalCustomerParamNullException";
	
	/**
	 *查看结果为空
	 */
	public static final String PERSONALCUSTOMER_QUERY_RESULT_NULL = "crm.customer.personalCustomer.queryPersonalCustomerResultNullException";

	
    public PersonalCustomerException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public PersonalCustomerException(String code, String msg) {
    	super(code, msg);
    }

    public PersonalCustomerException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
