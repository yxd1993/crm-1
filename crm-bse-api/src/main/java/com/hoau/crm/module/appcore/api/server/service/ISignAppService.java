package com.hoau.crm.module.appcore.api.server.service;

import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.appcore.api.shared.vo.SignVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 签到SERVICE
 * 
 * @author: 何斌
 * @create: 2015年7月3日 上午10:00:11
 */
@SuppressWarnings("rawtypes")
public interface ISignAppService {
	/**
	 * 新增签到记录
	 * 
	 * @param signEntity,loginName
	 * @author: 何斌
	 * @date: 2015年7月3日
	 * @update 
	 */
	ResponseBaseEntity addSignInfo(SignVo signVo,String loginName);
	
	/**
	 * 新增扫描签到信息
	 * 
	 * @param signVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-23
	 * @update
	 */
	ResponseBaseEntity addSweepSignInfo(SignVo signVo,String loginName);
	
	
	/**
	 * 新增多张图片的扫描签到
	 * 
	 * @param signVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-23
	 * @update
	 */
	ResponseBaseEntity addMoreImgSweepSignInfo(SignVo signVo,String loginName);
	
	/**
	 * 新增会议签到信息
	 * 
	 * @param signVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-21
	 * @update
	 */
	ResponseBaseEntity addMeetingSignInfo(SignVo signVo,String loginName);
	
	/**
	 * 查询未关联的扫描签到信息
	 * 
	 * @param signVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年8月14日
	 * @update 
	 */
	ResponseBaseEntity queryNoRelationSignList(SignVo signVo,String loginName);
	
	/**
	 * 查询未关联的会议签到信息
	 * 
	 * @param signVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-21
	 * @update
	 */
	ResponseBaseEntity queryNoRelationMeetingSignList(SignVo signVo,String loginName);
	
	/**
	 * 判断此签到是否存在
	 * 
	 * @param signEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-10-14
	 * @update
	 */
	public SignEntity isHasThereSign(SignEntity signEntity);
	
	/**
	 * 新的签到保存方法
	 * @param chatsAppVo
	 * @return
	 * @author 丁勇
	 * @date 2015年12月1日
	 * @update 
	 */
	ResponseBaseEntity addSignNewMethod(List<SignVo> signVo,String loginName);
}
