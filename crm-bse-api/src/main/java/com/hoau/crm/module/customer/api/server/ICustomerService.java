package com.hoau.crm.module.customer.api.server;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerNatureConvertEntity;
import com.hoau.crm.module.customer.api.shared.vo.CustomerVo;
import com.hoau.hbdp.framework.service.IService;

/**
 * 客户信息SERVICE
 *
 * @author 蒋落琛
 * @date 2015-5-20
 */
public interface ICustomerService extends IService {
	
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
	public List<CustomerEntity> queryCustomerInfo(CustomerVo customerVo, RowBounds rb);
	
	/**
	 * 查询客户信息总数
	 * 
	 * @param customerEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public long countCustomer(CustomerVo customerVo,UserEntity currUser);
	
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
	public List<CustomerEntity> queryCustomerInfoByCombo(CustomerVo customerVo, RowBounds rb);
	
	/**
	 * 查询客户信息总数
	 * 
	 * @param customerEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public long countCustomerByCombo(CustomerVo customerVo);
	
	/**
	 * 根据主键查询客户信息
	 * 
	 * @param cId
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-25
	 * @update
	 */
	public CustomerEntity queryCustomerInfoById(CustomerEntity customerEntity);
	
	/**
	 * 根据主键查询所有客户信息，包括客户运单日志
	 * 
	 * @param cId
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-25
	 * @update
	 */
	public CustomerEntity queryAllCustomerInfoById(CustomerEntity customerEntity);
	
	/**
	 * 新增客户信息
	 * 
	 * @param customerEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public void addCustomer(CustomerEntity customerEntity, UserEntity currUser,String appVersion,String appType);
	
	/**
	 * 删除客户信息
	 * 
	 * @param uploadCustomerEntity
	 * @author: 蒋落琛
	 * @date: 2015年5月25日
	 * @update
	 */
	void deleteCustomer(List<String> ids, UserEntity currUser,String delDesc);
	
	/**
	 * 根据CRM账号查询客户信息
	 * 
	 * @param crmAccount
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月26日
	 * @update 
	 */
	public CustomerEntity queryCustomerInfoByCrmAcconut(String crmAccount);
	
	/**
	 * 根据客户ID查询联系人信息
	 * 
	 * @param map
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	List<ContactEntity> queryContactList(CustomerEntity customerInfo);
	
	/**
	 * 新增联系人信息
	 * 
	 * @param contactEntity
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	public String addContactInfo(ContactEntity contactEntity, UserEntity currUser);
	
	/**
	 * 修改联系人信息
	 * 
	 * @param contactEntity
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	public void updateContactInfo(ContactEntity contactEntity, UserEntity currUser);
	
	/**
	 * 删除联系人信息
	 * 
	 * @param ids
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	public void deleteContact(List<String> ids, UserEntity currUser);
	
	/**
	 * 修改联系人信息为默认
	 * 
	 * @param contactEntity
	 * @author 蒋落琛
	 * @date 2015-6-11
	 * @update
	 */
	public void updateContactIsDefault(ContactEntity contactEntity, UserEntity currUser);
	
	/**
	 * 根据DC账号查询客户信息
	 * 
	 * @param dcAccount
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月26日
	 * @update 
	 */
	public CustomerEntity queryCustomerInfoByDcAcconut(String dcAccount);
	
	/**
	 * 更新DC账号查询客户信息
	 * 
	 * @param customerEntity
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月26日
	 * @update 
	 */
	public void updateCustomerInfoInfoById(CustomerEntity customerEntity);
	
	/**
	 * 查看门店负责人
	 * 
	 * @param tierName
	 * @author: 何斌
	 * @date: 2015年6月30日
	 * @update 
	 */
	public String queryTierManagerIdByName(String tierName);
	
	/**
	 * 转为意向客户,并更新意向信息
	 * @param customerEntity
	 * @author 丁勇
	 * @date 2015年6月30日
	 * @update 
	 */
	public void updateCustomerTurnIntention(CustomerEntity customerEntity,UserEntity currUser);

	/**
	 * 修改客户第一单与最后一单发货时间
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	public void updateCustomerShipmentsTime(CustomerEntity customer);
	
	/**
	 * 根据门店代码查询客户信息
	 * 
	 * @param TierCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月9日
	 * @update 
	 */
	public List<CustomerEntity> queryCustomerInfosByTierCode(String TierCode);
	
	/**
	 * 将批量修改客户负责人的老数据备份到客户历史记录备份表
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-8-10
	 * @update
	 */
	public void backupUpdateCustomerManagerId(Map<String, String> map);
	
	/**
	 * 将客户信息负责人进行批量修改
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-8-10
	 * @update
	 */
	public void updateCustomerManagerId(Map<String, String> map);
	
	/**
	 * 新增客户性质转换记录
	 * 
	 * @param customerConvertEntity
	 * @author: 何斌
	 * @date: 2015年8月15日
	 * @update 
	 */
	public void addCustomerConvertRecord(CustomerNatureConvertEntity customerNatureConvertEntity);
	
	/**
	 * 是否门店属性
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月18日
	 * @update 
	 */
	public Map<String,String> getIsStoreInfo(String deptCode);
	
	/**
	 * 转让客户
	 * 
	 * @param transferCustomerVO
	 * @author: 何斌
	 * @date: 2015年12月3日
	 * @update 
	 */
	public void transferCustomer(UserEntity currentUser,TransferCustomerVO transferCustomerVO);
	
	/**
	 * 注册官网账号
	 * 
	 * @param customerEntity
	 * @author: 何斌
	 * @date: 2015年12月23日
	 * @update 
	 */
	String registerObhAccount(CustomerEntity customerEntity);
	
	/**
	 * 注册官网账号成功,更新对应状态
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年12月23日
	 * @update 
	 */
	void updateCustomerOfObhAccountStatus(Map<String,Object> params);

}
