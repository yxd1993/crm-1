package com.hoau.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.common.server.util.LatitudeUtils;
import com.hoau.crm.module.customer.api.server.IUserScopeService;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntity;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntryEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerInfoPoolException;
import com.hoau.crm.module.customer.server.dao.UserScopeMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;

/**
 * @author 275636
 *用户服务范围Service
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserScopeService implements IUserScopeService {
	
	@Resource
	private UserScopeMapper userScopeMapper;
	
	@Override
	public List<UserScopeEntryEntity> queryUserScopeEntryInfo(String userID) {
		return userScopeMapper.queryUserScopeEntryInfo(userID);
	}

	@Override
	public void addUserScopeInfo(UserScopeEntity userScopeEntity) {
		if (userScopeEntity == null) {
			throw new CustomerInfoPoolException(CustomerInfoPoolException.PARAM_NULL);
		}
		/**
		 * 新增之前删除历史坐标和范围
		 * */
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		//删除坐标
		userScopeMapper.delUserScopeEntryInfo(currentUser.getUserName());
		//删除范围
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", currentUser.getUserName());
		map.put("userID", currentUser.getUserName());
		userScopeMapper.delUserScopeInfo(map);
		
		String uuid = UUIDUtil.getUUID();
		userScopeEntity.setId(uuid);
		//新增之前计算用户所属的最大最小范围
		double[] ds = LatitudeUtils.getAround(userScopeEntity.getCenterlng(), userScopeEntity.getCenterlat(), (int)(userScopeEntity.getMaxlength()/4));
		userScopeEntity.setMinLat(ds[0]);
		userScopeEntity.setMinLng(ds[3]);
		userScopeEntity.setMaxLat(ds[2]);
		userScopeEntity.setMaxLng(ds[1]);
		userScopeMapper.addUserScopeInfo(userScopeEntity);
		if(!userScopeEntity.getScopeEntryEntities().isEmpty()){
			List<UserScopeEntryEntity> entities = new ArrayList<UserScopeEntryEntity>();
			for(int index = 0,len=userScopeEntity.getScopeEntryEntities().size();index<len;index++){
				UserScopeEntryEntity scopeEntryEntity = userScopeEntity.getScopeEntryEntities().get(index);
				scopeEntryEntity.setId(UUIDUtil.getUUID());
				scopeEntryEntity.setParentid(uuid);
				entities.add(scopeEntryEntity);
			}
			//添加坐标信息
			userScopeMapper.addUserScopeEntryInfo(entities);
		}
	}

	@Override
	public void addUserScopeEntryInfo(
			List<UserScopeEntryEntity> scopeEntryEntities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delUserScopeInfo(String userID) {
		
	}

	@Override
	public void delUserScopeEntryInfo(String parentid) {
		
	}

	@Override
	public UserScopeEntity queryUserScopeInfo(String userID) {
		return null;
	}

	/**
	 * @param map
	 * @return
	 * 根据一个客户坐标确认属于某个用户范围
	 */
	@Override
	public List<UserScopeEntity> queryCustomerByUserScopeInfo(
			Map<String, String> map) {
		List<UserScopeEntity> entities = userScopeMapper.queryCustomerByUserScopeInfo();
		return entities;
	}
}
