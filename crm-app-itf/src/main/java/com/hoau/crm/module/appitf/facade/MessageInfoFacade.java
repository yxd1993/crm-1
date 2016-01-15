package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.MessageInfoVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@SuppressWarnings("rawtypes")
@Service
@Path("/message")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class MessageInfoFacade extends AppBaseFacade {

	@Resource
	IMessageInfoAppService iMessageInfoAppService;

	/**
	 * 新增消息
	 * 
	 * @param messageVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	@POST
	@Path("addMessage")
	public ResponseBaseEntity addMessage(MessageInfoVo messageVo) {
		return iMessageInfoAppService.addMessage(messageVo, loginName);
	}
	
	/**
	 * 分页查询消息信息
	 * 
	 * @param messageVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-29
	 * @update
	 */
	@POST
	@Path("queryMessageInfo")
	public ResponseBaseEntity queryMessageInfo(MessageInfoVo messageVo) {
		return iMessageInfoAppService.queryMessageInfo(messageVo, loginName);
	}
	
	/**
	 * 统计消息
	 * 
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-2
	 * @update
	 */
	@GET
	@Path("countMessageType")
	public ResponseBaseEntity countMessageType(){
		return iMessageInfoAppService.countMessageType(loginName);
	}
	
	/**
	 * 修改消息发送状态
	 * 
	 * @param ids
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-6-30
	 * @update
	 */
	@POST
	@Path("updateMessageSendStatus")
	public ResponseBaseEntity updateMessageSendStatus(MessageInfoVo messageVo){
		return iMessageInfoAppService.updateMessageSendStatus(messageVo, loginName);
	}

	/**
	 * 修改消息已读状态
	 * 
	 * @param ids
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-6-30
	 * @update
	 */
	@POST
	@Path("updateMessageReadStatus")
	public ResponseBaseEntity updateMessageReadStatus(MessageInfoVo messageVo){
		return iMessageInfoAppService.updateMessageReadStatus(messageVo, loginName);
	}

	/**
	 * 删除消息
	 * 
	 * @param ids
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-6-30
	 * @update
	 */
	@POST
	@Path("deleteMessage")
	public ResponseBaseEntity deleteMessage(MessageInfoVo messageVo){
		return iMessageInfoAppService.deleteMessage(messageVo, loginName);
	}
}
