/**
 * 
 */
package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IChatsAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.ChatsAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 洽谈 restful 接口发布调用
 * @author 丁勇
 * @date 2015年7月9日
 */
@SuppressWarnings("rawtypes")
@Service
@Path("/chats")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class ChatsFacade extends AppBaseFacade {
	//注入接口实现
	@Resource
	IChatsAppService IChatsAppService;
	
	@POST
	@Path("merge")
	public ResponseBaseEntity merge(ChatsAppVo chatsAppVo) {
		return IChatsAppService.merge(chatsAppVo, loginName);
	}
	
	@POST
	@Path("addChartsAndSign")
	public ResponseBaseEntity addChartsAndSign(ChatsAppVo chatsAppVo) {
		super.isUserLogin();
		return IChatsAppService.addChartsAndSign(chatsAppVo, loginName);
	}
	
	@POST
	@Path("queryChatsById")
	public ResponseBaseEntity queryChatsById(ChatsAppVo chatsAppVo) {
		return IChatsAppService.queryChatsById(chatsAppVo,loginName);
	}
}
