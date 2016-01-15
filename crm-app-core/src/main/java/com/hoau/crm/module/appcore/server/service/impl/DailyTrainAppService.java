package com.hoau.crm.module.appcore.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IDailyTrainAppService;
import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.DailyAppVo;
import com.hoau.crm.module.bse.api.server.service.IDailyTrainService;
import com.hoau.crm.module.bse.api.shared.domain.DailyTrainEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.server.service.IMeetingSignService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@Service
public class DailyTrainAppService implements IDailyTrainAppService {
	
	@Resource
	IDailyTrainService iDailyTrainService;
	@Resource
	ILoginService iLoginService;
	@Resource
	IAttachmentService iAttachmentService;
	@Resource
	IMeetingSignService iMeetingSignService;

	@Override
	public ResponseBaseEntity<DailyAppVo> queryDailyTrainList(
			DailyAppVo dailyTrainAppVo, String loginName) {
		if (dailyTrainAppVo.getLimit() == 0) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		RowBounds rb = new RowBounds(dailyTrainAppVo.getStart(), dailyTrainAppVo.getLimit());
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		List<DailyTrainEntity> dailyTrainEntities = iDailyTrainService.queryDailyTrainList(dailyTrainAppVo.getTrainEntity(), rb,user);
		long count = iDailyTrainService.countqueryDailyTrainList(dailyTrainAppVo.getTrainEntity(), rb,user);
		DailyAppVo appVo = new DailyAppVo();
		appVo.setTrainList(dailyTrainEntities);
		appVo.setTotalCount(count);
		
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> deleteDailyTrain(
			DailyAppVo dailyTrainAppVo) {
		if(dailyTrainAppVo == null || null == dailyTrainAppVo.getIds())
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		iDailyTrainService.deleteDailyTrain(dailyTrainAppVo.getIds());
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> addDailyTrain(
			DailyAppVo dailyTrainAppVo, String loginName) {
		if(dailyTrainAppVo == null || dailyTrainAppVo.getTrainEntity() == null)
			throw new ResDemandException(ResDemandException.ADD_DAILY_TRAIN_NULL);
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iDailyTrainService.addDailyTrain(dailyTrainAppVo.getTrainEntity(), user);
		if(null != dailyTrainAppVo.getTrainEntity() && null != dailyTrainAppVo.getTrainEntity().getMeetingSignId() && !"".equals(dailyTrainAppVo.getTrainEntity().getMeetingSignId()))
			iMeetingSignService.updateMeetingSignIsRelation(dailyTrainAppVo.getTrainEntity().getMeetingSignId(), user);
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> queryDailyTrainInfoById(
			DailyAppVo dailyTrainAppVo) {
		if (dailyTrainAppVo == null || dailyTrainAppVo.getTrainEntity() == null
				|| StringUtil.isEmpty(dailyTrainAppVo.getTrainEntity().getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		DailyTrainEntity meetingEntity =iDailyTrainService.queryDailyTrainInfoById(dailyTrainAppVo.getTrainEntity());
		//获取附件列表
		List<AttachmentEntity> attachmentEntities = new ArrayList<AttachmentEntity>();
		if(null != meetingEntity.getMeetingSignId() && !"".equals(meetingEntity.getMeetingSignId()))
			attachmentEntities = iAttachmentService.queryAttachmentByfileId(meetingEntity.getMeetingSignId());
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		List<DailyTrainEntity> dailyTrainList = new ArrayList<DailyTrainEntity>();
		DailyAppVo appVo = new DailyAppVo();
		dailyTrainList.add(meetingEntity);
		appVo.setTrainList(dailyTrainList);
		appVo.setAttachmentEntityList(attachmentEntities);
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<DailyAppVo> queryEmpAndDeptName(
			DailyAppVo dailyAppVo) {
		if (dailyAppVo == null || 
				StringUtil.isEmpty(dailyAppVo.getSelectorParam())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		List<String> empAndDeptNames = new ArrayList<String>();
		//查询人员
		empAndDeptNames.addAll(iDailyTrainService.queryEmpNames(dailyAppVo.getSelectorParam()));
		//查询部门
		empAndDeptNames.addAll(iDailyTrainService.queryDeptNames(dailyAppVo.getSelectorParam()));
		// 返回值
		ResponseBaseEntity<DailyAppVo> result = new ResponseBaseEntity<DailyAppVo>();
		DailyAppVo appVo = new DailyAppVo();
		appVo.setEmpAndDeptNames(empAndDeptNames);
		result.setResult(appVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
