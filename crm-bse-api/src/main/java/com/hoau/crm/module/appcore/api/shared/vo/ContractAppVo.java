package com.hoau.crm.module.appcore.api.shared.vo;

import com.hoau.crm.module.sales.api.shared.domain.SaleContractEntity;

/**
 * 合同信息RESTFUL接口返回VO
 * 
 * @author: 何斌
 * @create: 2015年6月25日 下午5:22:41
 */
public class ContractAppVo {

	/**
	 * 合同信息
	 */
	private SaleContractEntity saleContractEntity;

	public SaleContractEntity getSaleContractEntity() {
		return saleContractEntity;
	}

	public void setSaleContractEntity(SaleContractEntity saleContractEntity) {
		this.saleContractEntity = saleContractEntity;
	}
	
}
