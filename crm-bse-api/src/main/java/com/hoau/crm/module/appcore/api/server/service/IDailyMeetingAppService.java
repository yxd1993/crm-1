package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.DailyAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@SuppressWarnings("rawtypes")
public interface IDailyMeetingAppService {
	
	/**
	 * @param dailyMeetingAppVo
	 * @param loginName
	 * @return
	 * 每日会议分页查询
	 */
	ResponseBaseEntity<DailyAppVo> queryDailyMeetingList(DailyAppVo dailyMeetingAppVo, String loginName);
	/**
	 * @param dailyMeetingAppVo
	 * @return
	 * 每日会议根据ID删除
	 */
	ResponseBaseEntity<DailyAppVo> deleteDailyMeeting(DailyAppVo dailyMeetingAppVo);
	/**
	 * @param dailyMeetingAppVo
	 * @param loginName
	 * @return
	 * 每日会议新增
	 */
	ResponseBaseEntity<DailyAppVo> addDailyMeeting(DailyAppVo dailyMeetingAppVo,String loginName);
	/**
	 * @param dailyMeetingAppVo
	 * @return
	 * 每日会议根据ID查询
	 */
	ResponseBaseEntity<DailyAppVo> queryDailyMeetingInfoById(DailyAppVo dailyMeetingAppVo);
	/**
	 * @param loginName
	 * @return
	 * 获得参与人员 会议和培训
	 */
	ResponseBaseEntity<DailyAppVo> queryEmployeeInfoById(String loginName);
}
