package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IContractAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.ContractAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * @author: 何斌
 * @create: 2015年6月25日 下午1:47:25
 */
@SuppressWarnings("rawtypes")
@Service
@Path("/contract")
@Produces(HttpConstant.DATATYPE)
@Consumes(HttpConstant.DATATYPE)
public class ContractFacade {
	
	@Resource
	IContractAppService iContractAppService;
	
	@POST
	@Path("addSaleContract")
	public ResponseBaseEntity addSaleContract(ContractAppVo contractAppVo){
		return iContractAppService.updateContractInfo(contractAppVo);
	}
}
