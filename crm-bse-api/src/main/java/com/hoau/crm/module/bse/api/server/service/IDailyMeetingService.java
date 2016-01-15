package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.DailyMeetingEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

public interface IDailyMeetingService {
	List<DailyMeetingEntity> queryDailyMeetingList(DailyMeetingEntity dailyMeeting,
			RowBounds rb,UserEntity currentUser);

	Long countqueryDailyMeetingList(DailyMeetingEntity dailyMeeting, RowBounds rb,UserEntity currentUser);
	
	void deleteDailyMeeting(List<String> ids);
	
	void addDailyMeeting(DailyMeetingEntity dailyMeetingEntity, UserEntity currentUser);
	
	DailyMeetingEntity queryDailyMeetingInfoById(DailyMeetingEntity dailyMeeting);
	
	List<EmployeeEntity> queryEmployeeInfoById(UserEntity currentUser);

	List<AttachmentEntity> queryMeetingAttachAction(String id);
}
