package com.hoau.crm.module.sales.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

public class IntentionException extends BusinessException {

	public IntentionException(String errCode){
		super();
		super.errCode = errCode;
	}
	public IntentionException(String code, String msg){
		super(code, msg);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 意向列表主键为空
	 */
	public static final String ADD_INTENTION_ID_NULL = "crm.intention.intention.addIntentionIdNullException";
	/**
	 * 意向信息为空
	 */
	public static final String ADD_INTENTION_NULL = "crm.intention.intention.addIntentionNullException";
	
	/**
	 * 意向客户信息为空
	 * */
	public static final String ADD_INTENTION_CUSTOMER_NULL = "crm.intention.intention.addCustomerNullException";
	/**
	 * 此意向数据已经删除
	 */
	public static final String UPDATE_IS_DELETE = "crm.intention.intention.updateIsDeleteException";
}
