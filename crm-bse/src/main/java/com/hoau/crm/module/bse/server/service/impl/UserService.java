package com.hoau.crm.module.bse.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IFunctionService;
import com.hoau.crm.module.bse.api.server.service.IUserService;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.UserException;
import com.hoau.crm.module.bse.server.dao.UserMapper;
import com.hoau.crm.module.bse.server.dao.UserRoleMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 用户信息SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserService implements IUserService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Resource
	private IFunctionService iFunctionService;

	@Override
	public UserEntity queryUserByUserName(String userName) {
		if (!StringUtil.isEmpty(userName)) {
			UserEntity userEntity = userMapper.getUserByUserName(userName);
			if(userEntity != null){
				// 菜单初始化
				Set<String> urls = iFunctionService.queryTreeNodeListByUser(userEntity.getUserName());
				userEntity.setFunctionCodes(urls);
			}
			return userEntity;
		} else {
			throw new UserException(UserException.LOGINNAME_NULL);
		}
	}

	@Override
	public void insertUserRole(UserEntity user, List<String> roleCodes) {
		// 判断参数是否为空
		if (user == null || user.getUserName() == null) {
			throw new UserException(UserException.USER_ID_NULL);
		} /*else if (roleCodes == null || roleCodes.size() == 0) {
			throw new UserException(UserException.USER_ROLE_NULL);
		}*/
		// 删除用户角色信息
		userRoleMapper.deleteUserRoleByUserCode(user.getUserName());
		// 新增用户角色信息
		for (String roleCode : roleCodes) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", UUIDUtil.getUUID());
			map.put("userCode", user.getUserName());
			map.put("roleCode", roleCode);
			userRoleMapper.addUserRole(map);
		}
	}

	@Override
	public List<UserEntity> getUserList(UserEntity user, List<String> roleCodes, RowBounds rb) {
		// 判断分页参数是否为空
		if (rb == null) {
			throw new UserException(UserException.RB_NULL);
		}
		Map<String, String> map = new HashMap<String, String>();
		if (user != null) {
			if (!StringUtil.isEmpty(user.getUserName())) {
				map.put("userName", "%" + user.getUserName() + "%");
			}
			if (user.getEmpEntity() != null) {
				if (!StringUtil.isEmpty(user.getEmpEntity().getEmpName())) {
					map.put("empName", "%" + user.getEmpEntity().getEmpName()
							+ "%");
				}
				map.put("deptCode", user.getEmpEntity().getDeptcode());
			}
		}
		if(roleCodes != null && roleCodes.size() > 0){
			map.put("roleCode", roleCodes.get(0));
		}
		return userMapper.getUserList(map, rb);
	}

	@Override
	public long countUser(UserEntity user, List<String> roleCodes) {
		Map<String, String> map = new HashMap<String, String>();
		if (user != null) {
			if (!StringUtil.isEmpty(user.getUserName())) {
				map.put("userName", "%" + user.getUserName() + "%");
			}
			if (user.getEmpEntity() != null) {
				if (!StringUtil.isEmpty(user.getEmpEntity().getEmpName())) {
					map.put("empName", "%" + user.getEmpEntity().getEmpName()
							+ "%");
				}
				map.put("deptCode", user.getEmpEntity().getDeptcode());
			}
		}
		if(roleCodes != null && roleCodes.size() > 0){
			map.put("roleCode", roleCodes.get(0));
		}
		return userMapper.countUser(map);
	}
}
