package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.ResDemandAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * @author 275636
 *资源需求Appservice
 */
@SuppressWarnings("rawtypes")
public interface IResDemandAppService {
	ResponseBaseEntity<ResDemandAppVo> queryResDemandList(ResDemandAppVo resDemandAppVo, String loginName);
	ResponseBaseEntity<ResDemandAppVo> deleteResDemand(ResDemandAppVo resDemandAppVo);
	ResponseBaseEntity<ResDemandAppVo> addResDemand(ResDemandAppVo resDemandAppVo,String loginName);
	/**
	 * @param resDemand
	 * @return
	 * 根据ID获取资源信息
	 */
	ResponseBaseEntity<ResDemandAppVo> queryResDemandInfoById(ResDemandAppVo resDemandAppVo);
	
	/**
	 * @param resDemand
	 * 修改资源需求信息
	 */
	ResponseBaseEntity<ResDemandAppVo> updateResDemandInfoById(ResDemandAppVo resDemandAppVo,String loginName);
	
	/**
	 * @param resDemand
	 * 修改集团意见是否已答复
	 */
	ResponseBaseEntity<ResDemandAppVo> updateResDemandInfoByIsreply(ResDemandAppVo resDemandAppVo);
}
