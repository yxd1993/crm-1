package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.ICustomerAppService;
import com.hoau.crm.module.appcore.api.server.service.ICustomerResourcePoolAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerLatlngAppVo;
import com.hoau.crm.module.customer.api.shared.domain.CustomerTotalEntity;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@SuppressWarnings("rawtypes")
@Service
@Path("/customer")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class CustomerFacade extends AppBaseFacade {

	@Resource
	ICustomerAppService iCustomerAppService;
	
	@Resource
	ICustomerResourcePoolAppService iCustomerResourcePoolAppService;
	
	@POST
	@Path("addCustomer")
	public ResponseBaseEntity addCustomer(CustomerAppVo customerRestfulVo) {
		return iCustomerAppService.addCustomer(customerRestfulVo, loginName);
	}
	
	@POST
	@Path("editCustomer")
	public ResponseBaseEntity<CustomerAppVo> editCustomer(
			CustomerAppVo customerRestfulVo){
		return iCustomerAppService.editCustomer(customerRestfulVo, loginName,appVersion,appType);
	}

	@POST
	@Path("queryCustomerInfo")
	public ResponseBaseEntity queryCustomerInfo(CustomerAppVo searchVo) {
		return iCustomerAppService.queryCustomerInfo(searchVo, loginName);
	}
	
	@POST
	@Path("queryCustomerInfoById")
	public ResponseBaseEntity queryCustomerInfoById(CustomerAppVo searchVo) {
		return iCustomerAppService.queryCustomerInfoById(searchVo, loginName);
	}
	
	@POST
	@Path("querySuperiorCustomerInfo")
	public ResponseBaseEntity querySuperiorCustomerInfo(CustomerAppVo searchVo) {
		return iCustomerAppService.querySuperiorCustomerInfo(searchVo, loginName);
	}
	
	@POST
	@Path("deleteCustomerByIds")
	public ResponseBaseEntity deleteCustomerByIds(CustomerAppVo customerVo){
		return iCustomerAppService.deleteCustomerByIds(customerVo, loginName);
	}
	
	@POST
	@Path("queryPhoneInfoByPhone")
	public ResponseBaseEntity<CustomerAppVo> queryPhoneInfoByPhone(CustomerAppVo customerVo){
		return iCustomerAppService.queryPhoneInfoByPhone(customerVo);
	}
	
	@POST
	@Path("queryContactList")
	public ResponseBaseEntity<CustomerAppVo> queryContactList(CustomerAppVo customerVo){
		return iCustomerAppService.queryContactList(customerVo);
	}
	
	@POST
	@Path("addContact")
	public ResponseBaseEntity<CustomerAppVo> addContact(CustomerAppVo customerVo){
		return iCustomerAppService.addContact(customerVo, loginName);
	}
	
	@POST
	@Path("editContact")
	public ResponseBaseEntity<CustomerAppVo> editContact(CustomerAppVo customerVo){
		return iCustomerAppService.editContact(customerVo, loginName);
	}
	
	@POST
	@Path("deleteContact")
	public ResponseBaseEntity<CustomerAppVo> deleteContact(CustomerAppVo customerVo){
		return iCustomerAppService.deleteContact(customerVo, loginName);
	}
	
	@POST
	@Path("updateContactIsDefault")
	public ResponseBaseEntity<CustomerAppVo> updateContactIsDefault(CustomerAppVo customerVo){
		return iCustomerAppService.updateContactIsDefault(customerVo, loginName);
	}
	@POST
	@Path("addIntention")
	public ResponseBaseEntity<CustomerAppVo> updateCustomerTurnIntention(CustomerAppVo customerVo){
		return iCustomerAppService.updateCustomerTurnIntention(customerVo, loginName);
	}
	
	@POST
	@Path("nearCustomer")
	public ResponseBaseEntity<CustomerLatlngAppVo> getNearCustomerScopeLatLng(CustomerLatlngAppVo customerLatlngAppVo){
		return iCustomerAppService.getNearCustomerScopeLatLng(customerLatlngAppVo,loginName);
	}
	
	@POST
	@Path("queryCustomerInfoPool")
	public ResponseBaseEntity<CustomerAppVo> queryCustomerInfoPool(
			CustomerAppVo searchVo){
		return iCustomerAppService.queryCustomerInfoPool(searchVo, loginName);
	}
	
	@POST
	@Path("getCustomerInfoPoolById")
	public ResponseBaseEntity<CustomerAppVo> getCustomerInfoPoolById(
			CustomerAppVo searchVo){
		return iCustomerAppService.getCustomerInfoPoolById(searchVo, loginName);
	}
	
	@POST
	@Path("updateBackReason")
	public ResponseBaseEntity<CustomerAppVo> updateBackReason(
			CustomerAppVo searchVo){
		return iCustomerAppService.updateBackReason(searchVo, loginName);
	}
	
	@POST
	@Path("queryCustomerResourcePool")
	public ResponseBaseEntity<CustomerAppVo> queryCustomerResourcePool(
			CustomerAppVo searchVo){
		return iCustomerResourcePoolAppService.queryCustomerResourcePool(searchVo, loginName);
	}
	@POST
	@Path("claimCustomerResourcePool")
	public ResponseBaseEntity<Long> claimCustomerResourcePool(
			CustomerAppVo searchVo){
		return iCustomerResourcePoolAppService.claimCustomerResourcePool(searchVo, loginName);
	}
	
	@POST
	@Path("transferCustomer")
	public ResponseBaseEntity<Void> transferCustomer(CustomerAppVo customerAppVo){
		return iCustomerAppService.transferCustomer(customerAppVo,this.loginName);
	}
	
	@GET
	@Path("queryCustomerTotal")
	public ResponseBaseEntity<CustomerTotalEntity> queryCustomerTotal(){
		return iCustomerAppService.queryCustomerTotal(this.loginName);
	}
}
