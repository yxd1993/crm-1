/**
 * 
 */
package com.hoau.crm.module.appcore.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.vo.ChatsAppVo;

/**
 * 洽谈 restful mapperdao
 * @author 丁勇
 * @date 2015年7月8日
 */
@Repository
public interface ChatsAppMapper {
	/**
	 * 按id 查询洽谈信息
	 * @param chatsVo
	 * @return
	 * @author 丁勇
	 * @date 2015年7月8日
	 * @update 
	 */
	public ChatsAppVo queryChatsById(String id);
	
}
