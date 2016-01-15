package com.hoau.crm.module.customer.server.dao;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.CustomerTotalEntity;
import org.springframework.stereotype.Repository;

/**
 * 客户统计DAO
 * 
 * @author: 何斌
 * @create: 2015年12月29日 下午3:03:57
 */
@Repository
public interface CustomerTotalMapper {
	/**
	 * 查询所有拥有角色的用户工号
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	List<String> queryAllUserCode();
	/**
	 * 根据工号查询客户总数
	 * 
	 * @param userCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	CustomerTotalEntity queryCustomerTotalByUserCode(String userCode);
	/**
	 * 新增客户总数
	 * 
	 * @param customerTotalEntity
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	void addCustomerTotal(CustomerTotalEntity customerTotalEntity);
	/**
	 * 修改客户总数
	 * 
	 * @param customerTotalEntity
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	void updateCustomerTotal(CustomerTotalEntity customerTotalEntity);
	/**
	 * 判断用户是否存在
	 * 
	 * @param userCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	long isExistUserCode(String userCode);
}
