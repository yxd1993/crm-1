package com.hoau.crm.module.login.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * @ClassName: LoginException
 * @Description: 登录模块相关异常
 * @author HOAU-271755
 * @date 2015年4月24日 下午12:45:03
 * 
 */
public class LoginException extends BusinessException {

	private static final long serialVersionUID = -393837315936020112L;
	public static final String LOGININFO_NULL = "crm.login.login.LoginInfoNullException";
	public static final String USERINFO_NULL = "crm.login.login.UserInfoNullException";
	public static final String USERPASSWORD_VERIFY = "crm.login.login.UserPasswordVerifyException";
	/**
	 * 用户失效
	 */
	public static final String USER_ISACTIVE = "crm.login.login.UserIsActiveException";
	
	/**
	 * 用户无角色权限
	 */
	public static final String USER_NOT_ROLE = "crm.login.login.UserNotRoleException";
	
	/**
	 * 验证码不正确
	 */
	public static final String VERIFICATIONCODE_ERROR_EXCEPTION = "crm.login.login.VerificationCodeErrorException";
	
	/**
	 * 此用户需要输入验证码
	 */
	public static final String USER_MUST_INPUT_VERIFICATIONCODE = "crm.login.login.UserMustInputVerificationCodeException";
	
	/**
	 * 此用户手机号为空
	 */
	public static final String MOBILE_ISNULL = "crm.login.login.UserMobileIsNullException";

	public LoginException(String errCode) {
		super();
		super.errCode = errCode;
	}

	public LoginException(String errCode, Object... para) {
		super(errCode, para);
		super.errCode = errCode;
	}
}
