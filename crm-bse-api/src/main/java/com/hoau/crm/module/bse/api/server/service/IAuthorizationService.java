package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.AuthorizationEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

/**
 * 授权信息SERVICE
 *
 * @author 蒋落琛
 * @date 2015-9-16
 */
public interface IAuthorizationService {

	/**
	 * 根据登录名查询授权信息
	 * 
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-16
	 * @update
	 */
	public AuthorizationEntity getAuthrizationByLoginUser(String loginName);
	
	/**
	 * 新增授权信息
	 * 
	 * @param authorization
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月25日
	 * @update
	 */
	public void addOrUpdateAuthorizationEntity(AuthorizationEntity authorization,UserEntity currUser);

	/**
	 * 删除授权信息
	 * 
	 * @param ids
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月25日
	 * @update
	 */
	public void deleteAuthorization(List<String> ids);

	/**
	 * 查询权限信息
	 * 
	 * @param authorization
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月29日
	 * @update
	 */
	public List<AuthorizationEntity> getAuthorization(AuthorizationEntity authorization, RowBounds rb);

	/**
	 * 统计权限信息总数
	 * 
	 * @param appVersion
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月29日
	 * @update
	 */
	public Long countAuthorization(AuthorizationEntity authorization);

	/**
	 * 按id查询需要修改的授权信息
	 * @param id
	 * @author 潘强
	 * @date 2015年10月08日
	 * @update 
	 */
	public AuthorizationEntity getAuthorizationById(AuthorizationEntity authorization);
	
	
	
}
