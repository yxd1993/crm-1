package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerEntity;

/**
 * 个人客户DAO
 * 
 * @author: 何斌
 * @create: 2015年6月19日 下午5:31:41
 */
@Repository
public interface PersonalCustomerMapper {
	/**
	 * 新增个人客户
	 * @param personalCustomerEntity
	 * @author: 何斌
	 * @date: 2015年6月19日
	 * @update 
	 */
	void addPersonalCustomer(PersonalCustomerEntity personalCustomerEntity);
	/**
	 * 删除个人客户
	 * @param ids
	 * @author: 何斌
	 * @date: 2015年6月19日
	 * @update 
	 */
	void deletePersonalCustomer(Map<String,Object> params);
	/**
	 * 更新个人客户
	 * @param personalCustomerEntity
	 * @author: 何斌
	 * @date: 2015年6月19日
	 * @update 
	 */
	void updatePersonalCustomerInfo(PersonalCustomerEntity personalCustomerEntity);
	/**
	 * 分页查询个人客户
	 * @param params
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月19日
	 * @update 
	 */
	List<PersonalCustomerEntity> getPersonalCustomerInfo(Map<String,String> params,RowBounds rb);
	/**
	 * 分页统计个人客户
	 * @param params
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月19日
	 * @update 
	 */
	long countPersonalCustomer(Map<String,String> params);
	/**
	 * 根据id查询个人客户
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月19日
	 * @update 
	 */
	PersonalCustomerEntity getPersonalCustomerInfoById(String id);
	
	/**
	 * 判断渠道ID是否存在
	 * 
	 * @param sourceId
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月26日
	 * @update 
	 */
	long isExistBySourceId(int sourceId);

	/**
	 * 新增个人客户联系人
	 * 
	 * @param personalCustomerContactEntity
	 * @author: 何斌
	 * @date: 2015年12月21日
	 * @update 
	 */
	public void addPersonalCustomerContact(PersonalCustomerContactEntity personalCustomerContactEntity);
	
	/**
	 * 更新个人客户联系人
	 * 
	 * @param personalCustomerContactEntity
	 * @author: 何斌
	 * @date: 2015年12月21日
	 * @update 
	 */
	public void updatePersonalCustomerContact(PersonalCustomerContactEntity personalCustomerContactEntity);
	
	/**
	 * 判断联系人是否存在
	 * 
	 * @param sourceId
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月21日
	 * @update 
	 */
	long isExistContactBySourceId(int sourceId);
	
	/**
	 * 查询个人客户联系人信息
	 * 
	 * @param personalCustomerContactEntity
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月22日
	 * @update 
	 */
	public List<PersonalCustomerContactEntity> getPersonalCustomerContactInfo(PersonalCustomerContactEntity personalCustomerContactEntity);
	
	/**
	 * 修改个人客户状态
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年12月22日
	 * @update 
	 */
	public void udpatePersonalCustomerStatus(Map<String,Object> params);
	
	/**
	 * 修改个客户联系人状态
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年12月24日
	 * @update 
	 */
	public void updetePersonalCustomerContactStatus(Map<String,Object> params);
}
