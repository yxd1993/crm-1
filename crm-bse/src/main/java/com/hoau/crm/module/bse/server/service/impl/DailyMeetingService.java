package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.bse.api.server.service.IDailyMeetingService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DailyMeetingEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.DailyMeetingMapper;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * @author 275636
 *每日会议service
 */
@Service
public class DailyMeetingService implements IDailyMeetingService {
	
	@Resource
	DailyMeetingMapper dailyMeetingMapper;
	
	@Resource
	IAttachmentService iAttachmentService;

	@Override
	public List<DailyMeetingEntity> queryDailyMeetingList(
			DailyMeetingEntity dailyMeeting, RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (dailyMeeting == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(dailyMeeting.getCreateUser())) {
			params.put("createUser","%" + dailyMeeting.getCreateUser() + "%");
		}
		if(!StringUtil.isEmpty(dailyMeeting.getHyform())){
			params.put("hyform",dailyMeeting.getHyform());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtil.isEmpty(dailyMeeting.getHyaddress())){
			params.put("hyaddress","%" + dailyMeeting.getHyaddress() + "%");
		}
		if(!StringUtil.isEmpty(dailyMeeting.getHyType())){
			params.put("hyType",dailyMeeting.getHyType());
		}
		if(dailyMeeting.getHyDate() != null){
			params.put("hyDate",sdf.format(dailyMeeting.getHyDate()));
		}
		if(dailyMeeting.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf.format(dailyMeeting.getQueryCreateTime()));
		}
		if(!StringUtil.isEmpty(dailyMeeting.getSortType())){
			if("DESC".equals(dailyMeeting.getSortType())){
				params.put("timeDesc", BseConstants.YES);
			}else if("ASC".equals(dailyMeeting.getSortType())){
				params.put("timeAsc", BseConstants.YES);
			}
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_MEETING))
			params.put("createdBy", currentUser.getUserName());
		return dailyMeetingMapper.queryDailyMeetingList(params, rb);
	}

	@Override
	public Long countqueryDailyMeetingList(DailyMeetingEntity dailyMeeting,
			RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (dailyMeeting == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(dailyMeeting.getCreateUser())) {
			params.put("createUser","%" + dailyMeeting.getCreateUser() + "%");
		}
		if(!StringUtil.isEmpty(dailyMeeting.getHyform())){
			params.put("hyform",dailyMeeting.getHyform());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtil.isEmpty(dailyMeeting.getHyaddress())){
			params.put("hyaddress","%" + dailyMeeting.getHyaddress() + "%");
		}
		if(!StringUtil.isEmpty(dailyMeeting.getHyType())){
			params.put("hyType",dailyMeeting.getHyType());
		}
		if(dailyMeeting.getHyDate() != null){
			params.put("hyDate",sdf.format(dailyMeeting.getHyDate()));
		}
		if(dailyMeeting.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf.format(dailyMeeting.getQueryCreateTime()));
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_MEETING))
			params.put("createdBy", currentUser.getUserName());
		
		return dailyMeetingMapper.countqueryDailyMeetingList(params, rb);
	}

	@Override
	public void deleteDailyMeeting(List<String> ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		dailyMeetingMapper.deleteDailyMeeting(map);
	}

	@Override
	public void addDailyMeeting(DailyMeetingEntity dailyMeetingEntity,
			UserEntity currentUser) {
		dailyMeetingEntity.setCreateUser(currentUser.getUserName());
		dailyMeetingMapper.addDailyMeeting(dailyMeetingEntity);
	}

	@Override
	public DailyMeetingEntity queryDailyMeetingInfoById(
			DailyMeetingEntity dailyMeeting) {
		if (dailyMeeting == null
				|| StringUtil.isEmpty(dailyMeeting.getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		return dailyMeetingMapper.queryDailyMeetingInfoById(dailyMeeting.getId());
	}

	@Override
	public List<EmployeeEntity> queryEmployeeInfoById(UserEntity currentUser) {
		return dailyMeetingMapper.queryEmployeeInfoById(currentUser.getUserName());
	}

	@Override
	public List<AttachmentEntity> queryMeetingAttachAction(String id) {
		return iAttachmentService.queryAttachmentByfileId(id);
	}
}
