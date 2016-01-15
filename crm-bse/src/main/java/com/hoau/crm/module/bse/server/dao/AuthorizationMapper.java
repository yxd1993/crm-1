package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.AuthorizationEntity;

/**
 *  授权信息DAO
 *
 * @author 蒋落琛
 * @date 2015-9-16
 */
@Repository
public interface AuthorizationMapper {

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
	 * @author 潘强
	 * @date 2015-9-25
	 * @update
	 */
	public void addAuthorization(AuthorizationEntity authorization);

	/**
	 * 删除授权信息
	 * 
	 * @param authorization
	 * @return
	 * @author 潘强
	 * @date 2015-9-28
	 * @update
	 */
	public void deleteAuthorization(Map<String, Object> map);

	/**
	 * 权限信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月29日
	 * @update
	 */
	public List<AuthorizationEntity> getAuthorization(Map<String, Object> params, RowBounds rb);

	/**
	 * 统计权限信息数量
	 * 
	 * @param params
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月29日
	 * @update
	 */
	public Long countAuthorization(Map<String, Object> params);

	/**
	 * 根据id查询授权信息详情
	 * @param id
	 * @return
	 * @author 潘强
	 * @date 2015年10月08日
	 * @update 
	 */
	public AuthorizationEntity getAuthorizationById(String id);

	/**
	 * 更改授权信息
	 * @param map
	 * @author 潘强
	 * @date 2015年10月8日
	 * @update 
	 */
	public void updateAuthorization(Map<String, Object> map);
	
	/**
	 * 根据被授权人查询授权人
	 * @param wasAuthorizedPerson
	 * @author 潘强
	 * @date 2015年10月9日
	 * @update 
	 */
	public AuthorizationEntity findAuthorizedPersonByWasAuthorizedPerson(String wasAuthorizedPerson);

	
}
