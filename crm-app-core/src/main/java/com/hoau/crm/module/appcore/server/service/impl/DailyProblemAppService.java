package com.hoau.crm.module.appcore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IDailyProblemAppService;
import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.DailyAppVo;
import com.hoau.crm.module.bse.api.server.service.IDailyProblemService;
import com.hoau.crm.module.bse.api.shared.domain.DailyProblemEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.server.service.IMeetingSignService;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@Service
public class DailyProblemAppService implements IDailyProblemAppService {
	
	@Resource
	IDailyProblemService iDailyProblemService;
	@Resource
	ILoginService iLoginService;
	@Resource
	IAttachmentService iAttachmentService;
	@Resource
	IMeetingSignService iMeetingSignService;

	@Override
	public ResponseBaseEntity<DailyAppVo> queryDailyProblemList(
			DailyAppVo dailyProblemAppVo, String loginName) {
		if (dailyProblemAppVo.getLimit() == 0) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		RowBounds rb = new RowBounds(dailyProblemAppVo.getStart(), dailyProblemAppVo.getLimit());
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<DailyProblemEntity> dailyProblemEntities = iDailyProblemService.queryDailyProblemList(dailyProblemAppVo.getProblemEntity(), rb,user);
		long count = iDailyProblemService.countqueryDailyProblemList(dailyProblemAppVo.getProblemEntity(), rb,user);
		DailyAppVo appVo = new DailyAppVo();
		appVo.setProblemList(dailyProblemEntities);
		appVo.setTotalCount(count);
		
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> deleteDailyProblem(
			DailyAppVo dailyProblemAppVo) {
		if(dailyProblemAppVo == null || null == dailyProblemAppVo.getIds())
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		iDailyProblemService.deleteDailyProblem(dailyProblemAppVo.getIds());
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> addDailyProblem(
			DailyAppVo dailyProblemAppVo, String loginName) {
		if(dailyProblemAppVo == null || dailyProblemAppVo.getProblemEntity() == null)
			throw new ResDemandException(ResDemandException.ADD_DAILY_PROBLEM_NULL);
		String uuid = UUIDUtil.getUUID();
		dailyProblemAppVo.getProblemEntity().setId(uuid);
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iDailyProblemService.addDailyProblem(dailyProblemAppVo.getProblemEntity(), user);
		//修改已签到关联
		if(null != dailyProblemAppVo.getProblemEntity() && null != dailyProblemAppVo.getProblemEntity().getMeetingSignId() && !"".equals(dailyProblemAppVo.getProblemEntity().getMeetingSignId()))
			iMeetingSignService.updateMeetingSignIsRelation(dailyProblemAppVo.getProblemEntity().getMeetingSignId(), user);
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> queryDailyProblemInfoById(
			DailyAppVo dailyProblemAppVo) {
		if (dailyProblemAppVo == null || dailyProblemAppVo.getProblemEntity() == null
				|| StringUtil.isEmpty(dailyProblemAppVo.getProblemEntity().getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		DailyProblemEntity problemEntity =iDailyProblemService.queryDailyProblemInfoById(dailyProblemAppVo.getProblemEntity());
		//获取附件列表
		List<AttachmentEntity> attachmentEntities = new ArrayList<AttachmentEntity>();
		if(null != problemEntity.getMeetingSignId() && !"".equals(problemEntity.getMeetingSignId()))
			attachmentEntities = iAttachmentService.queryAttachmentByfileId(problemEntity.getMeetingSignId());
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		List<DailyProblemEntity> dailyProblemList = new ArrayList<DailyProblemEntity>();
		DailyAppVo appVo = new DailyAppVo();
		dailyProblemList.add(problemEntity);
		appVo.setProblemList(dailyProblemList);
		appVo.setAttachmentEntityList(attachmentEntities);
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
