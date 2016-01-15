package com.hoau.crm.module.login.server.service.impl;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IAuthorizationService;
import com.hoau.crm.module.bse.api.server.service.IFunctionService;
import com.hoau.crm.module.bse.api.server.service.IOperationLogService;
import com.hoau.crm.module.bse.api.server.service.IUserService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.server.util.GetHostAddressUtil;
import com.hoau.crm.module.bse.api.shared.domain.AuthorizationEntity;
import com.hoau.crm.module.bse.api.shared.domain.OperationLogEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.login.server.util.MD5;
import com.hoau.crm.module.login.shared.exception.LoginException;
import com.hoau.hbdp.framework.server.Definitions;
import com.hoau.hbdp.framework.server.context.SessionContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

@Service
public class LoginService implements ILoginService {

	//private LoginDao loginDao;

	@Resource
	IUserService iUserService;
	
	@Resource
	IFunctionService iFunctionService;
	
	@Resource
	IOperationLogService iOperationLogService;
	
	@Resource
	private IAuthorizationService iAuthorizationService;

	@Override
	@Transactional
	public void verifyPassword(UserEntity loginUser) {
		// 登录信息为空
		if (loginUser == null || StringUtil.isEmpty(loginUser.getUserName())
				|| StringUtil.isEmpty(loginUser.getPassword())) {
			throw new LoginException(LoginException.LOGININFO_NULL);
		}
		UserEntity user = iUserService.queryUserByUserName(loginUser
				.getUserName());
		// 数据库用户不存在
		if(user == null){
			throw new LoginException(LoginException.USERINFO_NULL);
		}
		//验证密码
		MD5 m = new MD5();
		String password =  m.getMD5ofStr(loginUser.getPassword());
		if(!password.equals(user.getPassword())){
			throw new LoginException(LoginException.USERPASSWORD_VERIFY);
		}
		// 判断用户是否有效
		if (StringUtil.isEmpty(user.getActive())
				|| user.getActive().equals(BseConstants.INACTIVE)) {
			throw new LoginException(LoginException.USER_ISACTIVE);
		}
		// 记录用户授权
		AuthorizationEntity auth = iAuthorizationService.getAuthrizationByLoginUser(loginUser
				.getUserName());
		if(auth != null){
			UserEntity authUser = iUserService.queryUserByUserName(auth.getAuthorizedPerson());
			if(authUser != null){
				user = authUser;
			}
			//System.out.println("授权人：" + auth.getAuthorizedPerson() + "被授权人：" + auth.getWasAuthorizedPerson());
		}
		// 菜单初始化
		Set<String> urls = iFunctionService.queryTreeNodeListByUser(user.getUserName());
		// 判断用户是否拥有菜单
		if(urls.size() == 0){
			throw new LoginException(LoginException.USER_NOT_ROLE);
		}
		user.setFunctionCodes(urls);
		
		//将用户信息注入SESSION
		SessionContext.setCurrentUser(user.getUserName());
		SessionContext.getSession().setObject(Definitions.KEY_USER_CACHE, user);
		
		//记录登录操作日志
		OperationLogEntity operationLogEntity = new OperationLogEntity();
		operationLogEntity.setOperationType(BseConstants.OPERATION_TYPE_LOGIN);
		operationLogEntity.setOperationIp(GetHostAddressUtil.getIpAddr(ServletActionContext.getRequest()));
		operationLogEntity.setOperationTime(new Date());
		operationLogEntity.setOperationUser(user.getUserName());
		if(auth != null){
			operationLogEntity.setWasAuthorizedPerson(auth.getWasAuthorizedPerson());
		}
		iOperationLogService.saveOperationLog(operationLogEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public void userLogout() {
		SessionContext.invalidateSession();
	}

	@Override
	public UserEntity getUserByLoginName(String loginName) {
		// 登录信息为空
		if (StringUtil.isEmpty(loginName)) {
			throw new LoginException(LoginException.LOGININFO_NULL);
		}
		UserEntity userEntity = iUserService.queryUserByUserName(loginName);
		// 数据库用户不存在
		if(userEntity == null){
			throw new LoginException(LoginException.USERINFO_NULL);
		}
		// 菜单初始化
		Set<String> urls = iFunctionService.queryTreeNodeListByUser(userEntity.getUserName());
		userEntity.setFunctionCodes(urls);
		return userEntity;
	}
}
