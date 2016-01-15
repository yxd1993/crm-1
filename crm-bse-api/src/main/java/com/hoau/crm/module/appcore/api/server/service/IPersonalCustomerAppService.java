package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.PersonalCustomerAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 个人客户模块RESTFUL接口
 * 
 * @author: 何斌
 * @create: 2015年6月26日 下午3:55:27
 */
public interface IPersonalCustomerAppService {
	
	/**
	 * 新增个人客户
	 * 
	 * @param personalCustomerAppVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月26日
	 * @update 
	 */
	@SuppressWarnings("rawtypes")
	public ResponseBaseEntity addPersonalCustomer(PersonalCustomerAppVo personalCustomerAppVo);
}
