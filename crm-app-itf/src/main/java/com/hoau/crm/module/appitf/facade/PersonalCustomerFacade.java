package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IPersonalCustomerAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.PersonalCustomerAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@Service
@Path("/personalcustomer")
@Produces(HttpConstant.DATATYPE)
@Consumes(HttpConstant.DATATYPE)
@SuppressWarnings("rawtypes")
public class PersonalCustomerFacade {
	
	@Resource
	private IPersonalCustomerAppService iPersonalCustomerAppService;
	
	@POST
	@Path("addPersonalCustomer")
	public ResponseBaseEntity addPersonalCustomer(PersonalCustomerAppVo personalCustomerAppVo){
		return iPersonalCustomerAppService.addPersonalCustomer(personalCustomerAppVo);
	}
}
