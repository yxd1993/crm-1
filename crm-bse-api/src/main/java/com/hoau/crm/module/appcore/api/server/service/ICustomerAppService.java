package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.CustomerAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerLatlngAppVo;
import com.hoau.crm.module.customer.api.shared.domain.CustomerTotalEntity;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 客户管理模块RESTFUL接口
 *
 * @author 蒋落琛
 * @date 2015-6-15
 */
public interface ICustomerAppService {

	/**
	 * 新增客户信息
	 * 
	 * @param customerRestfulVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-15
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> addCustomer(CustomerAppVo customerRestfulVo, String loginName);
	
	/**
	 * 修改客户信息
	 * 
	 * @param customerRestfulVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-18
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> editCustomer(CustomerAppVo customerRestfulVo, String loginName,String appVersion,String appType);
	
	/**
	 * 根据条件分页查询客户信息
	 * 
	 * @param customerEntity
	 * @param rb
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> queryCustomerInfo(CustomerAppVo cappVo, String loginName);
	
	/**
	 * 根据ID查询客户详情
	 * 
	 * @param searchVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> queryCustomerInfoById(CustomerAppVo searchVo, String loginName);
	
	/**
	 * 查询父公司信息
	 * 
	 * @param searchVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-24
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> querySuperiorCustomerInfo(CustomerAppVo searchVo, String loginName);
	
	/**
	 * 根据ID集合删除客户信息
	 * 
	 * @param customerVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> deleteCustomerByIds(CustomerAppVo customerVo, String loginName);
	
	/**
	 * 根据手机号查询手机信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> queryPhoneInfoByPhone(CustomerAppVo customerVo);
	
	/**
	 * 根据客户ID查询联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> queryContactList(CustomerAppVo customerVo);
	
	/**
	 * 新增联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> addContact(CustomerAppVo customerVo, String loginName);
	
	/**
	 * 修改联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> editContact(CustomerAppVo customerVo, String loginName);
	
	/**
	 * 删除联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> deleteContact(CustomerAppVo customerVo, String loginName);
	
	/**
	 * 修改联系人信息为默认
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-19
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> updateContactIsDefault(CustomerAppVo customerVo, String loginName);
	
	/**
	 * 新增意向
	 * @param customerVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015-7月-日
	 * @update 
	 */
	public ResponseBaseEntity<CustomerAppVo> updateCustomerTurnIntention(CustomerAppVo customerVo,String loginName);

	/**
	 * 获取附近指定公里范围内的客户信息
	 * 
	 * @return
	 * @author 275636
	 * @date 2015-7-10
	 * @update
	 */
	public ResponseBaseEntity<CustomerLatlngAppVo> getNearCustomerScopeLatLng(
			CustomerLatlngAppVo customerLatlngAppVo,String loginName);
	
	/**
	 * 查询资源客户信息
	 * 
	 * @param searchVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-14
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> queryCustomerInfoPool(
			CustomerAppVo searchVo, String loginName);
	
	/**
	 * 根据资源客户ID查询资源客户信息
	 * 
	 * @param searchVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-24
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> getCustomerInfoPoolById(
			CustomerAppVo searchVo, String loginName);
	
	/**
	 * 退回资源客户
	 * 
	 * @param searchVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-24
	 * @update
	 */
	public ResponseBaseEntity<CustomerAppVo> updateBackReason(
			CustomerAppVo searchVo, String loginName);
	
	/**
	 * 转让客户
	 * 
	 * @param customerAppVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月3日
	 * @update 
	 */
	public ResponseBaseEntity<Void> transferCustomer(CustomerAppVo customerAppVo, String loginName);
	
	/**
	 * 查询各客户总数
	 * 
	 * @param loginName
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	public ResponseBaseEntity<CustomerTotalEntity> queryCustomerTotal(String loginName);
	
}
