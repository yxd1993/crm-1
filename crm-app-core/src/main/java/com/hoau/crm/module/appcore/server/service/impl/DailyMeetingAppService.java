package com.hoau.crm.module.appcore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IDailyMeetingAppService;
import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.DailyAppVo;
import com.hoau.crm.module.bse.api.server.service.IDailyMeetingService;
import com.hoau.crm.module.bse.api.shared.domain.DailyMeetingEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.server.service.IMeetingSignService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;


@Service
public class DailyMeetingAppService implements IDailyMeetingAppService {
	@Resource
	IDailyMeetingService iDailyMeetingService;
	@Resource
	ILoginService iLoginService;
	@Resource
	IAttachmentService iAttachmentService;
	@Resource
	IMeetingSignService iMeetingSignService;

	@Override
	public ResponseBaseEntity<DailyAppVo> queryDailyMeetingList(
			DailyAppVo dailyMeetingAppVo, String loginName) {
		if (dailyMeetingAppVo.getLimit() == 0) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		RowBounds rb = new RowBounds(dailyMeetingAppVo.getStart(), dailyMeetingAppVo.getLimit());
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<DailyMeetingEntity> dailyMeetingEntities = iDailyMeetingService.queryDailyMeetingList(dailyMeetingAppVo.getMeetingEntity(), rb,user);
		long count = iDailyMeetingService.countqueryDailyMeetingList(dailyMeetingAppVo.getMeetingEntity(), rb,user);
		DailyAppVo appVo = new DailyAppVo();
		appVo.setMeetingList(dailyMeetingEntities);
		appVo.setTotalCount(count);
		
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> deleteDailyMeeting(
			DailyAppVo dailyMeetingAppVo) {
		if(dailyMeetingAppVo == null)
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_NULL);
		iDailyMeetingService.deleteDailyMeeting(dailyMeetingAppVo.getIds());
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> addDailyMeeting(
			DailyAppVo dailyMeetingAppVo, String loginName) {
		if(dailyMeetingAppVo == null || dailyMeetingAppVo.getMeetingEntity() == null)
			throw new ResDemandException(ResDemandException.ADD_DAILY_MEETING_NULL);
		
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iDailyMeetingService.addDailyMeeting(dailyMeetingAppVo.getMeetingEntity(), user);
		//修改已签到关联
		if(null != dailyMeetingAppVo.getMeetingEntity() && null != dailyMeetingAppVo.getMeetingEntity().getMeetingSignId() && !"".equals(dailyMeetingAppVo.getMeetingEntity().getMeetingSignId()))
			iMeetingSignService.updateMeetingSignIsRelation(dailyMeetingAppVo.getMeetingEntity().getMeetingSignId(), user);
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> queryDailyMeetingInfoById(
			DailyAppVo dailyMeetingAppVo) {
		if (dailyMeetingAppVo == null || dailyMeetingAppVo.getMeetingEntity() == null
				|| StringUtil.isEmpty(dailyMeetingAppVo.getMeetingEntity().getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		DailyMeetingEntity meetingEntity =iDailyMeetingService.queryDailyMeetingInfoById(dailyMeetingAppVo.getMeetingEntity());
		//获取附件列表
		List<AttachmentEntity> attachmentEntities = new ArrayList<AttachmentEntity>();
		if(null != meetingEntity.getMeetingSignId() && !"".equals(meetingEntity.getMeetingSignId()))
			attachmentEntities = iAttachmentService.queryAttachmentByfileId(meetingEntity.getMeetingSignId());
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		List<DailyMeetingEntity> dailyMeetingList = new ArrayList<DailyMeetingEntity>();
		DailyAppVo appVo = new DailyAppVo();
		dailyMeetingList.add(meetingEntity);
		appVo.setMeetingList(dailyMeetingList);
		appVo.setAttachmentEntityList(attachmentEntities);
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> queryEmployeeInfoById(String loginName) {
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<EmployeeEntity> empList = iDailyMeetingService.queryEmployeeInfoById(user);
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		DailyAppVo appVo = new DailyAppVo();
		appVo.setEmpList(empList);
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
