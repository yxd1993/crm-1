package com.hoau.crm.module.customer.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.IUserScopeService;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntity;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntryEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;


/**
 * @author 275636
 *服务范围Action
 */
@Controller
@Scope("prototype")
public class UserScopeAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582135222994410860L;

	
	@Resource
	private IUserScopeService iUserScopeService;
	
	/**
	 * 坐标集合
	 */
	private List<UserScopeEntryEntity> scopeEntryEntities;


	private UserScopeEntity userScopeEntity;
	
	public UserScopeEntity getUserScopeEntity() {
		return userScopeEntity;
	}

	public void setUserScopeEntity(UserScopeEntity userScopeEntity) {
		this.userScopeEntity = userScopeEntity;
	}

	public List<UserScopeEntryEntity> getScopeEntryEntities() {
		return scopeEntryEntities;
	}

	public void setScopeEntryEntities(List<UserScopeEntryEntity> scopeEntryEntities) {
		this.scopeEntryEntities = scopeEntryEntities;
	}

	public String queryUserScopeInfo(){
		try{
			scopeEntryEntities = iUserScopeService.queryUserScopeEntryInfo(UserContext.getCurrentUser().getUserName());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String addUserScopeInfo(){
		try{
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
//			userScopeEntity.setUserEntity(currUser);
			userScopeEntity.setUser_id(currUser.getUserName());
			userScopeEntity.setUser_name(currUser.getEmpEntity().getEmpName());
			userScopeEntity.setCreateUser(currUser.getUserName());
			iUserScopeService.addUserScopeInfo(userScopeEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}	
}
