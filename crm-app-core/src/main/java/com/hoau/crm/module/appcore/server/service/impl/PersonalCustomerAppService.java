package com.hoau.crm.module.appcore.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IPersonalCustomerAppService;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.PersonalCustomerAppVo;
import com.hoau.crm.module.customer.api.server.IPersonalCustomerService;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 个人客户模块RESTFUL接口实现
 * 
 * @author: 何斌
 * @create: 2015年6月26日 下午3:58:17
 */
@Service
public class PersonalCustomerAppService implements IPersonalCustomerAppService{

	@Resource
	private IPersonalCustomerService personalCustomerService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseBaseEntity addPersonalCustomer(
			PersonalCustomerAppVo personalCustomerAppVo) {
		ResponseBaseEntity responseBaseEntity = new ResponseBaseEntity();
		if(personalCustomerAppVo == null || personalCustomerAppVo.getPersonalCustomerEntity() == null ||
				personalCustomerAppVo.getPersonalCustomerEntity().getSourceId() == 0){
			responseBaseEntity.setErrorCode(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
			return responseBaseEntity;
		}
		//判断这个客户是否已经存在
		if(personalCustomerService.isExistCustomerBySourceId(personalCustomerAppVo.getPersonalCustomerEntity().getSourceId())){
			personalCustomerService.updatePersonalCustomerInfo(personalCustomerAppVo.getPersonalCustomerEntity());
		}else{
			personalCustomerAppVo.getPersonalCustomerEntity().setId(UUIDUtil.getUUID());
			personalCustomerService.addPersonalCustomer(personalCustomerAppVo.getPersonalCustomerEntity());
		}
		responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return responseBaseEntity;
	}

}
