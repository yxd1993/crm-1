package com.hoau.crm.module.customer.api.shared.util;

import java.util.Map;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.opensymphony.xwork2.ActionContext;

public class UserUitl {
	public static UserEntity getUser(String code){
		Map<String, Object> session = ActionContext.getContext()
				.getSession();
		UserEntity user = (UserEntity) session.get(code);
		return user;
	}
	
	public static String getEmpCode(){
		String userCode=null;
		UserEntity user = getUser("user");
//		if(user!=null && user.getUserName()!=null && !user.getEmpCode().equals("")){
//			userCode=user.getEmpCode();
//		}
		return userCode;
	}

}
