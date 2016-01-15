package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.ContractAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 销售合同模块RESTFUL接口
 * 
 * @author: 何斌
 * @create: 2015年6月25日 下午5:27:03
 */
public interface IContractAppService {
	ResponseBaseEntity<ContractAppVo> updateContractInfo(ContractAppVo contractAppVo);
}
