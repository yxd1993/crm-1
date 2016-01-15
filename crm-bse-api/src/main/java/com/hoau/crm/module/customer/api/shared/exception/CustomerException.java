package com.hoau.crm.module.customer.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 客户信息EXCEPTION
 *
 * @author 蒋落琛
 * @date 2015-5-20
 */
public class CustomerException extends BusinessException {

    private static final long serialVersionUID = 9063168317009336403L;

    /**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.customer.customer.RbNullException";
	
	/**
	 * 客户信息为空
	 */
	public static final String ADD_CUSTOMER_NULL = "crm.customer.customer.addCustomerNullException";
	
	/**
	 * 客户主键为空
	 */
	public static final String ADD_CUSTOMER_ID_NULL = "crm.customer.customer.addCustomerIdNullException";
	
	/**
	 * 客户联系人信息为空
	 */
	public static final String ADD_CONTACT_NULL = "crm.customer.customer.addContactNullException";
	
	/**
	 * 您没有修改客户GAM级别的权限
	 */
	public static final String UPDATE_CONTACT_NOT_UPDATERATINGAUTH = "crm.customer.customer.updateContactNotUpdateRatingAuth";
	
	/**
	 * 您没有修改GAM级别客户信息的权限
	 */
	public static final String UPDATE_GAM_CONTACT_NOT_UPDATERATINGAUTH = "crm.customer.customer.updateGamContactNotUpdateRatingAuth";
	
	/**
	 * 此客户数据已经删除
	 */
	public static final String UPDATE_IS_DELETE = "crm.customer.customer.updateIsDeleteException";
	
	/**
	 * 当前相同客户名称已存在，不允许重复新增
	 */
	public static final String ENTERPRISENAME_REPEAT = "crm.customer.customer.enterpriseNameRepeatException";
	
	/**
	 * 当前相同手机号的客户已存在，不允许重复新增
	 */
	public static final String CELLPHONE_REPEAT = "crm.customer.customer.cellPhoneRepeatException";
	
	/**
	 * 当前相同座机号的客户已存在，不允许重复新增
	 */
	public static final String TELEPHONE_REPEAT = "crm.customer.customer.telephoneRepeatException";
	
	/**
	 * 调用DC接口失败
	 */
	public static final String CUSTOMERINF_DC = "crm.customer.customer.CustomerInfoDCException";
	
	/**
	 * CRM账号为空
	 */
	public static final String CRMACCOUNT_NULL = "crm.customer.customer.crmAccountNullException";
	
	/**
	 * 此联系人数据已经删除
	 */
	public static final String CONTACT_IS_DELETE = "crm.customer.customer.contactIsDeleteException";
	
	/**
	 * 不能删除默认联系人
	 */
	public static final String DELETE_DEFAULT_CONTACT = "crm.customer.customer.deleteDefaultContactException";
	
	/**
	 * 当前联系人已经是默认联系人
	 */
	public static final String ALREADY_DEFAULT_CONTACT = "crm.customer.customer.alreadyDefaultContactException";
	
	/**
	 * 当前相同手机号的联系人已存在，不允许重复新增
	 */
	public static final String PHONE_REPEAT = "crm.customer.contact.contactPhoneException";
	/**
	 * 在新增意向时判断是否是洽谈用户,
	 */
	public static final String IS_NOT_CHAT = "crm.customer.accountTypeIsNotChatException";
	
	/**
	 * 坐标参数不能为空
	 */
	public static final String IS_NOT_NEARSCOPE = "crm.customer.customerNearScopeException";
	
	/**
	 * 参数为空
	 */
	public static final String PARAMS_NULL = "crm.customer.customerParamsNullException";
	
	/**
	 * 手机信息错误
	 */
	public static final String REGISTER_INFO_NULL = "crm.customer.registerInfoErrorException";
	/**
	 * 短信发送失败
	 */
	public static final String SEND_FAIL = "crm.customer.sendFailException";
	
	/**
	 * 手机号已注册
	 */
	public static final String CELLPHONE_REGISTER_ERROR = "crm.customer.cellphoneRegisterErrorException";
	/**
	 * 邮箱已注册
	 */
	public static final String EMAIL_REGISTER_ERROR = "crm.customer.emailRegisterErrorException";
	
	/**
	 * 网厅接口调用失败
	 */
	public static final String OBH_WEBSERVICE_FAIL = "crm.customer.obhWebServiceFailException";
	
	
    public CustomerException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public CustomerException(String code, String msg) {
    	super(code, msg);
    }

    public CustomerException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
