package com.hoau.crm.module.customer.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 客户信息EXCEPTION
 *
 * @author 蒋落琛
 * @date 2015-5-20
 */
public class CustomerInfoPoolException extends BusinessException {


	private static final long serialVersionUID = -5525040975998871429L;

	/**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.customer.customerInfoPool.RbNullException";
	
	/**
	 * 客户信息为空
	 */
	public static final String CUSTOMERINFOPOOL_NULL = "crm.customer.customerInfoPool.CustomerInfoPoolNullException";
	
	/**
	 * 当前相同联系人与联系人电话的客户已存在，不允许重复新增
	 */
	public static final String CUSTOMERINF_REPEAT = "crm.customer.customerInfoPool.CustomerInfoRepeatNullException";
	
	/**
	 * 此客户数据已经删除
	 */
	public static final String UPDATE_IS_DELETE = "crm.customer.customerInfoPool.updateIsDeleteException";
	
	/**
	 * 参数为空
	 */
	public static final String PARAM_NULL = "crm.customer.customerInfoPool.ParamNullException";
	
	/**
	 * 结果为空
	 */
	public static final String RESULT_NULL = "crm.customer.customerInfoPool.ResultNullException";
	
	/**
	 * 数据量太大
	 */
	public static final String DATA_TOO_BIG = "crm.customer.customerInfoPool.DataTooBigException";
	
    public CustomerInfoPoolException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public CustomerInfoPoolException(String code, String msg) {
    	super(code, msg);
    }

    public CustomerInfoPoolException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
