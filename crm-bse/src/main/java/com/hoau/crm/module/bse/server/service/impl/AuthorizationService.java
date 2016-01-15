package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IAuthorizationService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.AuthorizationEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.AuthorizationMapper;
import com.hoau.crm.module.bse.shared.exception.AuthorizationException;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 根据登录名查询授权信息
 * 
 * @author 蒋落琛
 * @date 2015-9-16
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AuthorizationService implements IAuthorizationService {

	@Resource
	private AuthorizationMapper authorizationMapper;

	@Override
	public AuthorizationEntity getAuthrizationByLoginUser(String loginName) {
		return authorizationMapper.getAuthrizationByLoginUser(loginName);
	}

	@Override
	public void addOrUpdateAuthorizationEntity(AuthorizationEntity authorization,UserEntity currUser) {
		// 授权结束时间不能小于当前时间
		if(authorization.getAuthorizedEndTime().getTime() <= new Date().getTime()){
			throw new AuthorizationException(AuthorizationException.ADD_AUTHORIZATION_DATETIME_INVALID);
		}
		//授权结束时间不能小于授权开始时间
		if(authorization.getAuthorizedEndTime().getTime() <=authorization.getAuthorizedStartTime().getTime() ){
			throw new AuthorizationException(AuthorizationException.ADD_DATETIME_INVALID);
		}
		if(StringUtils.isEmpty(authorization.getId())){
			String uuid=UUIDUtil.getUUID();
			authorization.setId(uuid);
			authorization.setCreateUser(currUser.getUserName());
			AuthorizationEntity auth=authorizationMapper.findAuthorizedPersonByWasAuthorizedPerson(authorization.getWasAuthorizedPerson());
			//判断该被授权人是否已经被其他人授权
			if(auth!=null){
				throw new AuthorizationException(AuthorizationException.ADD_AUTHORIZEDPERSON_INVALID);
			}
			authorizationMapper.addAuthorization(authorization);
		}else{
			Map<String,Object> map=new HashMap<String,Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			map.put("id",authorization.getId());
			map.put("authorizedStartTime", sdf.format(authorization.getAuthorizedStartTime()));
			map.put("authorizedEndTime", sdf.format(authorization.getAuthorizedEndTime()));
			authorizationMapper.updateAuthorization(map);
		}
		
	}

	@Override
	@Transactional
	public void deleteAuthorization(List<String> ids) {
		if(ids==null || ids.size()==0){
			throw new AuthorizationException(AuthorizationException.AUTHORIZATION_NULL);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids",ids);
		authorizationMapper.deleteAuthorization(map);
	}

	@Override
	public List<AuthorizationEntity> getAuthorization(AuthorizationEntity authorization, RowBounds rb) {
		if (rb == null) {
			throw new AuthorizationException(AuthorizationException.QUERY_AUTHORIZATION_RB_NULL);
		}
		if (authorization == null) {
			throw new AuthorizationException(AuthorizationException.QUERY_AUTHORIZATION_PARAM_NULL);
		}
		Map<String,Object> params=new HashMap<String,Object>();
		if (!StringUtil.isEmpty(authorization.getAuthorizedPerson())) {
			params.put("authorizedPerson", "%" + authorization.getAuthorizedPerson() + "%");
		}
		if (!StringUtil.isEmpty(authorization.getWasAuthorizedPerson())) {
			params.put("wasAuthorizedPerson", "%" + authorization.getWasAuthorizedPerson() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (authorization.getAuthorizedStartTime() != null) {
			params.put("authorizedStartTime", sdf.format(authorization.getAuthorizedStartTime()));
		}
		if (authorization.getAuthorizedEndTime() != null) {
			params.put("authorizedEndTime", sdf.format(BseConstants.getEndDate(authorization.getAuthorizedEndTime())));
		}
	    return authorizationMapper.getAuthorization(params, rb);
	}

	@Override
	public Long countAuthorization(AuthorizationEntity authorization) {
		if (authorization == null) {
			throw new AuthorizationException(AuthorizationException.QUERY_AUTHORIZATION_PARAM_NULL);
		}
		Map<String,Object> params=new HashMap<String,Object>();
		if (!StringUtil.isEmpty(authorization.getAuthorizedPerson())) {
			params.put("authorizedPerson", "%" + authorization.getAuthorizedPerson() + "%");
		}
		if (!StringUtil.isEmpty(authorization.getWasAuthorizedPerson())) {
			params.put("wasAuthorizedPerson", "%" + authorization.getWasAuthorizedPerson() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (authorization.getAuthorizedStartTime() != null) {
			params.put("authorizedStartTime", sdf.format(authorization.getAuthorizedStartTime()));
		}
		if (authorization.getAuthorizedEndTime() != null) {
			params.put("authorizedEndTime", sdf.format(BseConstants.getEndDate(authorization.getAuthorizedEndTime())));
		}
		return authorizationMapper.countAuthorization(params);
	}

	@Override
	public AuthorizationEntity getAuthorizationById(AuthorizationEntity authorization) {
		return authorizationMapper.getAuthorizationById(authorization.getId());
	}

}
