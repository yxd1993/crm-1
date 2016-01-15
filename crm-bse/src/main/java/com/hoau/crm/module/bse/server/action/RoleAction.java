package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IRoleService;
import com.hoau.crm.module.bse.api.shared.domain.RoleEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 角色ACTION
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Controller
@Scope("prototype")
public class RoleAction extends AbstractAction {

	private static final long serialVersionUID = 6426347642453827268L;

	/**
	 * 角色信息SERVICE
	 */
	@Resource
	IRoleService iRoleService;

	/**
	 * 角色信息实体
	 */
	private RoleEntity role;
	
	/**
	 * 角色CODE集合
	 */
	private List<String> roles;

	/**
	 * 角色信息集合
	 */
	private List<RoleEntity> roleList;

	/**
	 * 权限信息集合
	 */
	private List<String> funCodes;
	
	/**
	 * 用户编码
	 */
	private String userCode;

	/**
	 * 分页查询角色信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String queryRoleList() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			roleList = iRoleService.queryRoleList(role, rb);
			this.setTotalCount(iRoleService.countRole(role));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 新增角色信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public String addRole() {
		try {
			iRoleService.addRole(role);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 修改角色信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public String updateRole() {
		try {
			iRoleService.updateRole(role);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 删除角色信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public String deleteRole() {
		try {
			iRoleService.deleteRole(roles);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 根据角色ID查询角色信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public String queryRoleById() {
		try {
			role = iRoleService.getRoleById(role);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 新增角色权限信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String addRoleFun() {
		try {
			iRoleService.insertRoleFun(role, funCodes);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 查询待分配的角色信息
	 * 
	 * @param userCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-28
	 * @update
	 */
	public String queryLeftRoles(){
		roleList = iRoleService.getLeftRoles(userCode);
		return returnSuccess();
	}

	/**
	 * 查询已分配的角色信息
	 * 
	 * @param userCode
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-28
	 * @update
	 */
	public String queryAuthedRoles(){
		roleList = iRoleService.getAuthedRoles(userCode);
		return returnSuccess();
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public List<RoleEntity> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleEntity> roleList) {
		this.roleList = roleList;
	}

	public List<String> getFunCodes() {
		return funCodes;
	}

	public void setFunCodes(List<String> funCodes) {
		this.funCodes = funCodes;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
