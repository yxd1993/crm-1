package com.hoau.crm.module.appcore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IResDemandAppService;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.ResDemandAppVo;
import com.hoau.crm.module.bse.api.server.service.IResDemandService;
import com.hoau.crm.module.bse.api.shared.domain.ResDemandEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;
@Service
public class ResDemandAppService implements IResDemandAppService {

	@Resource
	ILoginService iLoginService;
	
	@Resource
	private IResDemandService iResDemandService;
	
	@Override
	public ResponseBaseEntity<ResDemandAppVo> queryResDemandList(
			ResDemandAppVo resDemandAppVo, String loginName) {
		if (resDemandAppVo.getLimit() == 0) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		RowBounds rb = new RowBounds(resDemandAppVo.getStart(), resDemandAppVo.getLimit());
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<ResDemandEntity> demandEntities = iResDemandService.queryResDemandList(resDemandAppVo.getResDemandEntity(), rb,user);
		long count = iResDemandService.countqueryResDemandList(resDemandAppVo.getResDemandEntity(), rb,user);
		ResDemandAppVo demandAppVo = new ResDemandAppVo();
		demandAppVo.setResDemandList(demandEntities);
		demandAppVo.setTotalCount(count);
		
		ResponseBaseEntity<ResDemandAppVo> result = new ResponseBaseEntity<ResDemandAppVo>();
		result.setResult(demandAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ResDemandAppVo> deleteResDemand(
			ResDemandAppVo resDemandAppVo) {
		if(resDemandAppVo == null)
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_NULL);
		iResDemandService.deleteResDemand(resDemandAppVo.getIds());
		// 返回值
		ResponseBaseEntity<ResDemandAppVo> result = new ResponseBaseEntity<ResDemandAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ResDemandAppVo> addResDemand(
			ResDemandAppVo resDemandAppVo, String loginName) {
		if(resDemandAppVo == null || resDemandAppVo.getResDemandEntity() == null)
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_NULL);
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iResDemandService.addResDemand(resDemandAppVo.getResDemandEntity(), user);
		// 返回值
		ResponseBaseEntity<ResDemandAppVo> result = new ResponseBaseEntity<ResDemandAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ResDemandAppVo> queryResDemandInfoById(
			ResDemandAppVo resDemandAppVo) {
		if (resDemandAppVo == null || resDemandAppVo.getResDemandEntity() == null
				|| StringUtil.isEmpty(resDemandAppVo.getResDemandEntity().getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		ResDemandEntity demandEntity =iResDemandService.queryResDemandInfoById(resDemandAppVo.getResDemandEntity());
		// 返回值
		ResponseBaseEntity<ResDemandAppVo> result = new ResponseBaseEntity<ResDemandAppVo>();
		List<ResDemandEntity> resDemandList = new ArrayList<ResDemandEntity>();
		ResDemandAppVo appVo = new ResDemandAppVo();
		resDemandList.add(demandEntity);
		appVo.setResDemandList(resDemandList);
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ResDemandAppVo> updateResDemandInfoById(
			ResDemandAppVo resDemandAppVo, String loginName) {
		if (resDemandAppVo == null || resDemandAppVo.getResDemandEntity() == null
				|| StringUtil.isEmpty(resDemandAppVo.getResDemandEntity().getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		//获取当前用户
		UserEntity currUser = iLoginService.getUserByLoginName(loginName);
		iResDemandService.updateResDemandInfoById(resDemandAppVo.getResDemandEntity(),currUser);
		ResponseBaseEntity<ResDemandAppVo> result = new ResponseBaseEntity<ResDemandAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ResDemandAppVo> updateResDemandInfoByIsreply(
			ResDemandAppVo resDemandAppVo) {
		if (resDemandAppVo == null || resDemandAppVo.getResDemandEntity() == null
				|| StringUtil.isEmpty(resDemandAppVo.getResDemandEntity().getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		//获取当前用户
		iResDemandService.updateResDemandInfoByIsreply(resDemandAppVo.getResDemandEntity());
		ResponseBaseEntity<ResDemandAppVo> result = new ResponseBaseEntity<ResDemandAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
