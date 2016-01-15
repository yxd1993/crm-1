package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 角色信息异常类
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class RoleException extends BusinessException {
	
	private static final long serialVersionUID = 590525254182760551L;

	/**
	 * 新增角色信息为空
	 */
	public static final String ADD_ROLE_NULL = "crm.bse.role.RoleInfoNullException";
	
	/**
	 * 此角色为系统默认角色，不能删除
	 */
	public static final String ADD_ROLE_IS_DEFAULT = "crm.bse.role.RoleIsDefaultException";
	
	/**
	 * 角色编码或名称重名
	 */
	public static final String ROLE_SHARES = "crm.bse.role.RoleSharesException";
	
	/**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.bse.role.RbNullException";
	
	/**
	 * 角色编码为空
	 */
	public static final String ROLE_CODE_NULL = "crm.bse.role.RoleCodeNullException";
	
	/**
	 * 角色对应的权限为空
	 */
	public static final String ROLE_FUNCTION_NULL = "crm.bse.role.RoleFunctionNullException";

	/**
	 * 异常的构造方法
	 * 
	 * @param errCode
	 * @since
	 */
	public RoleException(String errCode) {
		super();
		super.errCode = errCode;
	}

}
