package com.hoau.crm.module.sales.api.server.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.sales.api.shared.domain.MeetingSignEntity;




/**
 * 会议签到SERVICE
 *
 * @author 潘强
 * @date 2015-9-8
 */
public interface IMeetingSignService {

	/**
	 * 查询会议签到信息
	 * 
	 * @param meetingSign
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月8日
	 * @update
	 */
	List<MeetingSignEntity> getMeetingSign(MeetingSignEntity meetingSign,RowBounds rb);
	
	/**
	 * 统计会议签到总数
	 * 
	 * @param meetingSign
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月8日
	 * @update
	 */
	Long countMeetingSign(MeetingSignEntity meetingSign, RowBounds rb);
	
	/**
	 * 新增会议扫描签到实体
	 * 
	 * @param meetingSign
	 * @param imgDir
	 * @param loginName
	 * @return
	 * @author: 潘强
	 * @date: 2015年9月17日
	 * @update
	 */
	public void addMeetingSignEntityInfo(MeetingSignEntity meetingSign,String imgDir,UserEntity currentUser);
	
	/**
	 * 根据会议类型与当前用户查询未关联的会议签到信息
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-21
	 * @update
	 */
	public List<MeetingSignEntity> queryNoRelationMeetingSignList(Map<String, String> map);

	/**
	 * 将签到置为已关联
	 * 
	 * @param signVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-21
	 * @update
	 */
	public void updateMeetingSignIsRelation(String signId, UserEntity user);
}
