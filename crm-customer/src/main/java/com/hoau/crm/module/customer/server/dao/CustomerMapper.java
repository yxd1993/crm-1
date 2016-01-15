package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerNatureConvertEntity;


/**
 * 客户信息DAO
 *
 * @author 蒋落琛
 * @date 2015-5-20
 */
@Repository
public interface CustomerMapper {

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
	public List<CustomerEntity> getCustomerInfo(Map<String, String> params, RowBounds rb);
	
	/**
	 * 查询客户信息总数
	 * 
	 * @param customerEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public long countCustomer(Map<String, String> params);
	
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
	public List<CustomerEntity> getCustomerInfoByCombo(Map<String, String> params, RowBounds rb);
	
	/**
	 * 查询客户信息总数
	 * 
	 * @param customerEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public long countCustomerByCombo(Map<String, String> params);
	
	/**
	 * 新增客户信息
	 * 
	 * @param customerEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public void addCustomerInfo(CustomerEntity customerEntity);
	
	/**
	 * 新增客户联系人信息
	 * 
	 * @param contactEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public void addCustomerContactInfo(ContactEntity contactEntity);
	
	/**
	 * 修改客户信息
	 * 
	 * @param customerEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public void updateCustomerInfo(CustomerEntity customerEntity);
	
	/**
	 * 修改客户联系人信息
	 * 
	 * @param contactEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public void updateCustomerContactInfo(ContactEntity contactEntity);
	
	/**
	 * 根据主键查询客户信息
	 * 
	 * @param cId
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-25
	 * @update
	 */
	public CustomerEntity getCustomerInfoById(String cId);
	
	/**
	 * 根据主键查询所有客户信息，包括客户运单日志
	 * 
	 * @param cId
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-25
	 * @update
	 */
	public CustomerEntity getAllCustomerInfoById(String cId);
	
	/**
	 * 删除上传客户信息
	 * 
	 * @param deleteUploadCustomer
	 * @author: 蒋落琛
	 * @date: 2015年5月25日
	 * @update 
	 */
	void deleteCustomer(Map<String, Object> map);
	
	/**
	 * 修改客户信息
	 * 
	 * @param customerEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 *//*
	public void updateCustomer(CustomerEntity customerEntity);
	
	*//**
	 * 修改客户联系人信息
	 * 
	 * @param contactEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 *//*
	public void updateContact(ContactEntity contactEntity);
	
	*//**
	 * 删除客户信息
	 * 
	 * @param customerEntity
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 *//*
	public void deleteCustomer(CustomerEntity customerEntity);*/
	
	/**
	 * 判断当前信息是否重名,是否可以新增或修改
	 * 
	 * @param params
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月30日
	 * @update 
	 */
	long isAllowCustomer(Map<String, String> params);
	
	
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
	List<ContactEntity> getContactList(Map<String, String> map);
	
	/**
	 * 删除联系人信息
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-11
	 * @update
	 */
	void deleteContact(Map<String, Object> map);
	
	/**
	 * 根据客户ID删除联系人信息
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-12
	 * @update
	 */
	void deleteContactByAccountId(Map<String, Object> map);
	
	/**
	 * 判断联系人手机号是否重复
	 * 
	 * @param params
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-11
	 * @update
	 */
	long isAllowContact(Map<String, String> params);
	
	/**
	 * 修改联系人信息为默认
	 * 
	 * @author 蒋落琛
	 * @date 2015-6-11
	 * @update
	 */
	void updateContactIsDefault(Map<String, Object> map);
	
	/**
	 * 取消当前客户所有的默认联系人
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-6-12
	 * @update
	 */
	void removeContactIsDefault(Map<String, Object> map);
	
	/**
	 * 根据ID查询联系人信息
	 * 
	 * @param id
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-12
	 * @update
	 */
	ContactEntity getContactById(String id);
	
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
	 * 根据客户ID查询默认的区号
	 * 
	 * @param accountId
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月6日
	 * @update 
	 */
	public String queryDefaultDistrictNumberByAccountId(String accountId);
	
	/**
	 * 修改客户第一单与最后一单发货时间
	 * 
	 * @param customer
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
	 * 获取部门的是否门店属性
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月25日
	 * @update 
	 */
	public Map<String,String> getIsStoreInfo(String deptCode);
	
	/**
	 *	判断是否为路区 
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月29日
	 * @update 
	 */
	public long isRoadByDeptCode(String deptCode);
	
	/**
	 * 更新改签门店
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年11月11日
	 * @update 
	 */
	public void updateSignTierCode(Map<String,String> params);
	
	/**
	 * 更新转让客户负责人
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年12月2日
	 * @update 
	 */
	public void updateTransferCustomerManager(Map<String,Object> params);
	
	/**
	 * 将批量转发客户的老数据备份到客户历史记录备份表
	 * 
	 * @param map
	 * @author: 何斌
	 * @date: 2015年12月3日
	 * @update 
	 */
	public void backupSaleTransferCustomer(List<String> ids);
	
	/**
	 * 注册官网账号成功,更新对应状态
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年12月23日
	 * @update 
	 */
	public void updateCustomerOfObhAccountStatus(Map<String,Object> params);
}
