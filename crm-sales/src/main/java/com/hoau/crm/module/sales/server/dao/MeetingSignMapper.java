package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.sales.api.shared.domain.MeetingSignEntity;




/**
 * 会议签到DAO
 *
 * @author 潘强
 * @date 2015-9-8
 */
@Repository
public interface MeetingSignMapper {
	
	/**
	 * 查询会议签到信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月8日
	 * @update
	 */
	List<MeetingSignEntity> getMeetingSign(Map<String, Object> params);

	/**
	 * 统计会议签到总数
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月8日
	 * @update
	 */
	Long countMeetingSign(Map<String, Object> params);
	
	/**
	 * 新增会议签到实体
	 * 
	 * @param meetingSign
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月17日
	 * @update
	 */
	void addMeetingSignEntityInfo(MeetingSignEntity meetingSign);

	/**
	 * 根据会议类型与当前用户查询未关联的会议签到信息
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-21
	 * @update
	 */
	List<MeetingSignEntity> queryNoRelationMeetingSignList(Map<String, String> map);
	
	/**
	 * 将签到置为已关联
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-9-21
	 * @update
	 */
	public void updateMeetingSignIsRelation(Map<String, String> map);
}
