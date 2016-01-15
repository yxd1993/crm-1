package com.hoau.crm.module.bse.api.server.service;

import com.hoau.crm.module.bse.api.shared.domain.PhoneInfoEntity;
import com.hoau.hbdp.framework.service.IService;

/**
 * 手机号信息查询接口
 *
 * @author 蒋落琛
 * @date 2015-6-9
 */
public interface IPhoneInfoService extends IService {
   
	/**
	 * 根据手机号查询手机号相关信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-9
	 * @update
	 */
	PhoneInfoEntity queryPhoneInfoByPhone(PhoneInfoEntity phoneInfoEntity);
}
