package com.hoau.crm.module.customer.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.customer.api.server.ICustomerLatlngService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerGroupEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

@Controller
@Scope("prototype")
public class CustomerLatlngAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1278266788714348489L;
	
	/**
	 * 客户端坐标
	 * */
	private String[] clientCoordinates;
	
	public String[] getClientCoordinates() {
		return clientCoordinates;
	}

	public void setClientCoordinates(String[] clientCoordinates) {
		this.clientCoordinates = clientCoordinates;
	}

	/**
	 * 客户坐标信息
	 * */
	private List<CustomerLatlngEntity> customerLatlngEntities;
	
	/**
	 * 客户分布热力
	 */
	private List<CustomerGroupEntity> customerGroupEntities;
	
	private List<CustomerGroupEntity> customerHeatOutPutEntities;
	
	public List<CustomerGroupEntity> getCustomerHeatOutPutEntities() {
		return customerHeatOutPutEntities;
	}

	public void setCustomerHeatOutPutEntities(
			List<CustomerGroupEntity> customerHeatOutPutEntities) {
		this.customerHeatOutPutEntities = customerHeatOutPutEntities;
	}

	public List<CustomerLatlngEntity> getCustomerLatlngEntities() {
		return customerLatlngEntities;
	}

	public void setCustomerLatlngEntities(
			List<CustomerLatlngEntity> customerLatlngEntities) {
		this.customerLatlngEntities = customerLatlngEntities;
	}

	@Resource
	private ICustomerLatlngService iCustomerLatlngService;
	
	public String initializeCustomerLatLng(){
		try{
			iCustomerLatlngService.initializeCustomerLatLng();
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String getNearCustomerScopeLatLng(){
		try{
			customerLatlngEntities = iCustomerLatlngService.getNearCustomerScopeLatLng(clientCoordinates);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public List<CustomerGroupEntity> getCustomerGroupEntities() {
		return customerGroupEntities;
	}

	public void setCustomerGroupEntities(
			List<CustomerGroupEntity> customerGroupEntities) {
		this.customerGroupEntities = customerGroupEntities;
	}

	public String getCustomerGroupCount(){
		try{
		customerGroupEntities = iCustomerLatlngService.getCustomerGroupCount();
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String getCustomerHeatMapOutPut(){
		try{
			customerHeatOutPutEntities = iCustomerLatlngService.getCustomerHeatMapOutPut();
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
}
