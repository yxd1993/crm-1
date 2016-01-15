/**
 * 
 */
package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.ChatsAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 洽谈 restful 服务接口
 * @author 丁勇
 * @date 2015年7月8日
 */
public interface IChatsAppService {
	/**
	 * 按id查询洽谈信息
	 * @param chatsAppVo
	 * @return
	 * @author 丁勇
	 * @date 2015年7月8日
	 * @update 
	 */
	public ResponseBaseEntity<ChatsAppVo> queryChatsById(ChatsAppVo chatsAppVo,String loginName);
	
	
	/**
	 * 新增和修改
	 * @param chatsAppVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年7月8日
	 * @update 
	 */
	public ResponseBaseEntity<ChatsAppVo> merge(ChatsAppVo chatsAppVo,String loginName);
	
	/**
	 * 新增洽谈与关联的签到信息
	 * 
	 * @param chatsAppVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-10-14
	 * @update
	 */
	public ResponseBaseEntity<ChatsAppVo> addChartsAndSign(ChatsAppVo chatsAppVo,String loginName);
	
}
