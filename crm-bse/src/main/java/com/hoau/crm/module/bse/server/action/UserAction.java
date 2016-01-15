package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IUserService;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 用户ACTION
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Controller
@Scope("prototype")
public class UserAction extends AbstractAction {

	private static final long serialVersionUID = -1091863618563159350L;

	@Resource
	private IUserService iUserService;

	/**
	 * 用户信息实体
	 */
	private UserEntity userEntity;

	/**
	 * 用户信息集合
	 */
	private List<UserEntity> userList;

	/**
	 * 用户角色ID集合
	 */
	private List<String> roleCodes;

	/**
	 * 修改用户角色信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String insertUserRole() {
		try {
			iUserService.insertUserRole(userEntity, roleCodes);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 分页查询用户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String queryUserList() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			userList = iUserService.getUserList(userEntity, roleCodes, rb);
			this.setTotalCount(iUserService.countUser(userEntity, roleCodes));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

	public List<UserEntity> getUserList() {
		return userList;
	}

	public void setUserList(List<UserEntity> userList) {
		this.userList = userList;
	}

}
