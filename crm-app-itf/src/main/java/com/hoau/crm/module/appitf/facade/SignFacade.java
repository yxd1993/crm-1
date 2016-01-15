package com.hoau.crm.module.appitf.facade;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.ISignAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.SignVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * @author: 何斌
 * @create: 2015年7月3日 上午10:33:24
 */
@SuppressWarnings("rawtypes")
@Service
@Produces(HttpConstant.DATATYPE)
@Consumes(HttpConstant.DATATYPE)
@Path("/sign")
public class SignFacade extends AppBaseFacade{

	@Resource
	private ISignAppService iSignAppService;
	
	@POST
	@Path("addSignInfo")
	public ResponseBaseEntity addSignInfo(SignVo signVo){
		super.isUserLogin();
		return iSignAppService.addSignInfo(signVo,loginName);
	}
	
	@POST
	@Path("addSweepSignInfo")
	public ResponseBaseEntity addSweepSignInfo(SignVo signVo){
		super.isUserLogin();
		return iSignAppService.addSweepSignInfo(signVo,loginName);
	}
	
	@POST
	@Path("addMoreImgSweepSignInfo")
	public ResponseBaseEntity addMoreImgSweepSignInfo(SignVo signVo){
		super.isUserLogin();
		return iSignAppService.addMoreImgSweepSignInfo(signVo,loginName);
	}
	
	@POST
	@Path("addMeetingSignInfo")
	public ResponseBaseEntity addMeetingSignInfo(SignVo signVo){
		super.isUserLogin();
		return iSignAppService.addMeetingSignInfo(signVo, loginName);
	}
	
	@POST
	@Path("queryNoRelationSignList")
	public ResponseBaseEntity queryNoRelationSignList(SignVo signVo){
		return iSignAppService.queryNoRelationSignList(signVo,loginName);
	}
	
	@POST
	@Path("queryNoRelationMeetingSignList")
	public ResponseBaseEntity queryNoRelationMeetingSignList(SignVo signVo){
		super.isUserLogin();
		return iSignAppService.queryNoRelationMeetingSignList(signVo,loginName);
	}
	
	@POST
	@Path("addSignNewMethod")
	public ResponseBaseEntity addSignNewMethod(List<SignVo> signList){
		super.isUserLogin();
		return iSignAppService.addSignNewMethod(signList,loginName);
	}
}
