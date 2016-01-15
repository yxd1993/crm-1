package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.DailyMeetingEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;

/**
 * @author 275636
 *每日会议
 */
@Repository
public interface DailyMeetingMapper {
	List<DailyMeetingEntity> queryDailyMeetingList(Map<String, Object> params,
			RowBounds rb);

	Long countqueryDailyMeetingList(Map<String, Object> params, RowBounds rb);
	
	void addDailyMeeting(DailyMeetingEntity dailyMeetingEntity);
	
	void deleteDailyMeeting(Map<String, Object> map);
	
	DailyMeetingEntity queryDailyMeetingInfoById(String id);
	
	List<EmployeeEntity> queryEmployeeInfoById(String empCode);
}
