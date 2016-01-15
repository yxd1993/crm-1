package com.hoau.crm.module.bse.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IRoleService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.RoleEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.RoleException;
import com.hoau.crm.module.bse.server.dao.RoleFunctionMapper;
import com.hoau.crm.module.bse.server.dao.RoleMapper;
import com.hoau.crm.module.bse.server.dao.UserRoleMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 角色信息SERFICE实现
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class RoleService implements IRoleService {

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private RoleFunctionMapper roleFunctionMapper;
	
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public List<RoleEntity> queryRoleList(RoleEntity role, RowBounds rb) {
		if (rb == null) {
			throw new RoleException(RoleException.RB_NULL);
		}
		return roleMapper.getRoleList(role, rb);
	}

	@Override
	public long countRole(RoleEntity role) {
		return roleMapper.countRole(role);
	}

	@Override
	public void addRole(RoleEntity role) {
		if (role != null) {
			if(isAllowRole(role)){
				role.setId(UUIDUtil.getUUID());
				// 用户信息
				UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
				role.setCreateUser(currUser.getUserName());
				roleMapper.insert(role);
			}else {
				throw new RoleException(RoleException.ROLE_SHARES);
			}
		} else {
			throw new RoleException(RoleException.ADD_ROLE_NULL);
		}
	}

	@Override
	public void updateRole(RoleEntity role) {
		if (role != null) {
			if(isAllowRole(role)){
				// 用户信息
				UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
				role.setModifyUser(currUser.getUserName());
				roleMapper.update(role);
			}else {
				throw new RoleException(RoleException.ROLE_SHARES);
			}
		} else {
			throw new RoleException(RoleException.ADD_ROLE_NULL);
		}
	}

	@Override
	public void deleteRole(List<String> roles) {
		if (roles != null && roles.size() > 0) {
			for(int i=0; i<roles.size(); i++){
				RoleEntity role = new RoleEntity();
				// 用户信息
				UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
				role.setModifyUser(currUser.getUserName());
				role.setId(roles.get(i));
				RoleEntity currRole = getRoleById(role);
				if(currRole != null){
					if(currRole.getIsDefault() != null && currRole.getIsDefault().equals(BseConstants.ACTIVE)){
						throw new RoleException(RoleException.ADD_ROLE_IS_DEFAULT);
					}else{
						roleMapper.delete(role);
						userRoleMapper.deleteUserRoleByRoleCode(currRole.getRoleCode());
					}
				}
			}
		} else {
			throw new RoleException(RoleException.ADD_ROLE_NULL);
		}
	}

	@Override
	public RoleEntity getRoleById(RoleEntity role) {
		if (role == null || StringUtil.isEmpty(role.getId())) {
			throw new RoleException(RoleException.ADD_ROLE_NULL);
		} else {
			return roleMapper.getRoleById(role.getId());
		}
	}

	@Override
	public void insertRoleFun(RoleEntity role, List<String> funCodes) {
		// 判断参数是否为空
		if (role == null || role.getRoleCode() == null) {
			throw new RoleException(RoleException.ROLE_CODE_NULL);
		} else if (funCodes == null || funCodes.size() == 0) {
			throw new RoleException(RoleException.ROLE_FUNCTION_NULL);
		}
		// 删除角色权限信息
		roleFunctionMapper.deleteRoleFunByRoleCode(role.getRoleCode());
		// 新增角色权限信息
		for (String funCode : funCodes) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", UUIDUtil.getUUID());
			map.put("roleCode", role.getRoleCode());
			map.put("funCode", funCode);
			roleFunctionMapper.insertRoleFun(map);
		}
	}

	@Override
	public List<RoleEntity> getLeftRoles(String userCode) {
		List<RoleEntity> list = roleMapper.getLeftRoles(userCode);
		return list;
	}

	@Override
	public List<RoleEntity> getAuthedRoles(String userCode) {
		List<RoleEntity> list = roleMapper.getAuthedRoles(userCode);
		return list;
	}
	
	@Override
	public List<String> queryAllFunctionIdByRoleId(String roleCode)
			throws RoleException {
		if (StringUtil.isEmpty(roleCode)) {
			throw new RoleException(RoleException.ROLE_CODE_NULL);
		}
		List<String> functions = roleFunctionMapper.getAllIdByRoleId(roleCode);
		return functions;
	}
	
	/**
	 * 判断是否重名
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-29
	 * @update
	 */
	private boolean isAllowRole(RoleEntity role){
		long size = roleMapper.isAllowRole(role);
		if(size > 0){
			return false;
		}else{
			return true;
		}
	}
}
