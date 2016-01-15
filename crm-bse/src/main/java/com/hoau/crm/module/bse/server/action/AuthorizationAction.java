package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IAuthorizationService;
import com.hoau.crm.module.bse.api.shared.domain.AuthorizationEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年9月25日
 * @description：授权信息action
 */
@Controller
@Scope("prototype")
public class AuthorizationAction extends AbstractAction {

	private static final long serialVersionUID = -1229397198817117887L;

	/**
	 * 接收授权信息实体数据
	 */
	private AuthorizationEntity authorization;
	
	/**
	 * 接收从数据库中查询到的满足相应条件的授权信息数据
	 */
	private List<AuthorizationEntity> authorizationList;
	
	/**
	 * 授权信息ID集合
	 */
	private List<String> ids;

	@Resource
	private IAuthorizationService authorizationService;
	
	/**
	 * @author：潘强
	 * @create：2015年9月29日
	 * @description：查询权限信息
	 */
	public String queryAuthorization(){
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			authorizationList = authorizationService.getAuthorization(authorization, rb);
			this.setTotalCount(authorizationService.countAuthorization(authorization));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 按 id查询需要修改的授权信息
	 * @author 潘强
	 * @date 2015年10月08日
	 * @update
	 */
	public String queryAuthorizationById(){
		try {
			authorization = authorizationService.getAuthorizationById(authorization);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
		
	}
	
	/**
	 * @author：潘强
	 * @create：2015年9月25日
	 * @description：新增权限信息
	 */
	public String addOrUpdateAuthorization(){
		try {
			// 创建用户
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			authorizationService.addOrUpdateAuthorizationEntity(authorization,currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * @author：潘强
	 * @create：2015年9月25日
	 * @description：删除权限信息
	 */
	public String deleteAuthorization(){
		try {
			authorizationService.deleteAuthorization(ids);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	
	public AuthorizationEntity getAuthorization() {
		return authorization;
	}

	public void setAuthorization(AuthorizationEntity authorization) {
		this.authorization = authorization;
	}

	public List<String> getIds() {
		return ids;
	}
	
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<AuthorizationEntity> getAuthorizationList() {
		return authorizationList;
	}

	public void setAuthorizationList(List<AuthorizationEntity> authorizationList) {
		this.authorizationList = authorizationList;
	}
	
}
