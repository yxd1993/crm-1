package com.hoau.crm.module.customer.api.server;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerEntity;

/**
 * 个人客户SERVICE
 * @author: 何斌
 * @create: 2015年6月21日 上午9:09:14
 */
public interface IPersonalCustomerService {
	/**
	 * 新增个人客户
	 * @param personalCustomerEntity
	 * @author: 何斌
	 * @date: 2015年6月21日
	 * @update 
	 */
	void addPersonalCustomer(PersonalCustomerEntity personalCustomerEntity);
	/**
	 * 删除个人客户
	 * @param ids
	 * @author: 何斌
	 * @date: 2015年6月21日
	 * @update 
	 */
	void deletePersonalCustomer(List<String> ids);
	/**
	 * 更新个人客户
	 * @param personalCustomerEntity
	 * @author: 何斌
	 * @date: 2015年6月21日
	 * @update 
	 */
	void updatePersonalCustomerInfo(PersonalCustomerEntity personalCustomerEntity);
	/**
	 * 分页查询个人客户
	 * @param params
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月21日
	 * @update 
	 */
	List<PersonalCustomerEntity> queryPersonalCustomerInfo(PersonalCustomerEntity personalCustomerEntity,RowBounds rb);
	/**
	 * 分页统计个人客户
	 * @param params
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月21日
	 * @update 
	 */
	long countPersonalCustomer(PersonalCustomerEntity personalCustomerEntity);
	/**
	 * 根据id查询个人客户
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月19日
	 * @update 
	 */
	PersonalCustomerEntity queryPersonalCustomerInfoById(String id);
	
	/**
	 * 判断客户渠道ID是否存在
	 * 
	 * @param sourceId
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月26日
	 * @update 
	 */
	boolean isExistCustomerBySourceId(int sourceId);
	
	/**
	 * 新增个人客户联系人
	 * 
	 * @param personalCustomerContactEntity
	 * @author: 何斌
	 * @date: 2015年12月21日
	 * @update 
	 */
	public void addPersonalCustoemrContact(PersonalCustomerContactEntity personalCustomerContactEntity);
	
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
	boolean isExistContactBySourceId(int sourceId);
	
	/**
	 * 查询个人客户联系人信息
	 * 
	 * @param personalCustomerContactEntity
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月22日
	 * @update 
	 */
	public List<PersonalCustomerContactEntity> queryPersonalCustomerContactInfo(PersonalCustomerContactEntity personalCustomerContactEntity);
	
}
