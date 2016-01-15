package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IResDemandAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.ResDemandAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@Service
@Path("/resdemand")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class ResDemandFacade extends AppBaseFacade {

	@Resource
	IResDemandAppService iResDemandAppService;
	
	@POST
	@Path("queryResDemandInfo")
	public ResponseBaseEntity<ResDemandAppVo> queryResDemandList(ResDemandAppVo resDemandAppVo) {
		return iResDemandAppService.queryResDemandList(resDemandAppVo, loginName);
	}
	
	@POST
	@Path("deleteResDemand")
	public ResponseBaseEntity<ResDemandAppVo> deleteResDemand(ResDemandAppVo resDemandAppVo) {
		return iResDemandAppService.deleteResDemand(resDemandAppVo);
	}
	@POST
	@Path("addResDemand")
	public ResponseBaseEntity<ResDemandAppVo> addResDemand(ResDemandAppVo resDemandAppVo) {
		return iResDemandAppService.addResDemand(resDemandAppVo, loginName);
	}
	
	@POST
	@Path("queryResDemandInfoById")
	public ResponseBaseEntity<ResDemandAppVo> queryResDemandInfoById(ResDemandAppVo resDemandAppVo) {
		return iResDemandAppService.queryResDemandInfoById(resDemandAppVo);
	}
	
	@POST
	@Path("updateResDemandInfoById")
	public ResponseBaseEntity<ResDemandAppVo> updateResDemandInfoById(ResDemandAppVo resDemandAppVo) {
		return iResDemandAppService.updateResDemandInfoById(resDemandAppVo, loginName);
	}
	
	@POST
	@Path("updateResDemandInfoByIsreply")
	public ResponseBaseEntity<ResDemandAppVo> updateResDemandInfoByIsreply(ResDemandAppVo resDemandAppVo) {
		return iResDemandAppService.updateResDemandInfoByIsreply(resDemandAppVo);
	}
}
