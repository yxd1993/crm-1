/**
 * 
 */
package com.hoau.crm.module.appcore.api.server.service;


import com.hoau.crm.module.appcore.api.shared.vo.CustomerAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 *
 * @author 丁勇
 * @date 2015年10月21日
 */
public interface ICustomerResourcePoolAppService {

	/**
	 * 查询资源客户列表
	 * @param customerVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年10月21日
	 * @update 
	 */
	public ResponseBaseEntity<CustomerAppVo> queryCustomerResourcePool(CustomerAppVo customerVo, String loginName);
	
	/**
	 * 认领资源客户处理
	 * @param customerVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年10月21日
	 * @update 
	 */
	public ResponseBaseEntity<Long> claimCustomerResourcePool(CustomerAppVo customerVo,String loginName);
}
