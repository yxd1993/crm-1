package com.hoau.crm.module.appcore.api.shared.vo;

import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerEntity;

/**
 * 个人客户VO
 * 
 * @author: 何斌
 * @create: 2015年6月26日 下午3:53:58
 */
public class PersonalCustomerAppVo {
	
	/**
	 * 个人客户
	 */
	private PersonalCustomerEntity personalCustomerEntity;
	
	public PersonalCustomerEntity getPersonalCustomerEntity() {
		return personalCustomerEntity;
	}

	public void setPersonalCustomerEntity(
			PersonalCustomerEntity personalCustomerEntity) {
		this.personalCustomerEntity = personalCustomerEntity;
	}
}
