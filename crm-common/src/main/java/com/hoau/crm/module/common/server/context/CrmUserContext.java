package com.hoau.crm.module.common.server.context;

import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.security.UserNotLoginException;
import com.hoau.hbdp.framework.server.context.UserContext;

/**
 * @author：高佳
 * @create：2015年5月18日 下午1:46:31
 * @description：crm系统用户context
 */
public final class CrmUserContext {
	private CrmUserContext() {
		super();
	}

	/**
	 * 获取当前会话用户信息
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	public static UserEntity getCurrentUser() {
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		if (user == null) {
			throw new UserNotLoginException();
		}
		return user;
	}
	
	/**
	 * 获取当前会话用户部门编码
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	public static String getCurrentDeptCode(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		if (user == null) {
			throw new UserNotLoginException();
		}
		return user.getEmpEntity().getDeptEntity().getDeptCode();
	}
	
	/**
	 * 获取当前会话用户部门名称
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	public static String getCurrentDeptName(){
		UserEntity user = (UserEntity) (UserContext.getCurrentUser());
		if (user == null) {
			throw new UserNotLoginException();
		}
		return user.getEmpEntity().getDeptEntity().getDeptName();
	}
}
