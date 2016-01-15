package com.hoau.crm.module.customer.api.server;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.CustomerTotalEntity;

/**
 * 客户总数统计
 * 
 * @author: 何斌
 * @create: 2015年12月29日 下午2:57:32
 */
public interface ICustomerTotalService {

	/**
	 * 查询所有拥有角色的用户工号
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	public List<String> queryAllUserCode();
	/**
	 * 根据工号查询客户总数
	 * 
	 * @param userCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	public CustomerTotalEntity queryCustomerTotalByUserCode(String userCode);
	/**
	 * 新增客户总数
	 * 
	 * @param customerTotalEntity
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	public void addCustomerTotal(CustomerTotalEntity customerTotalEntity);
	/**
	 * 修改客户总数
	 * 
	 * @param customerTotalEntity
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	public void updateCustomerTotal(CustomerTotalEntity customerTotalEntity);
	/**
	 * 判断用户是否存在
	 * 
	 * @param userCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	public boolean isExistUserCode(String userCode);
	
	/**
	 * 执行脚本
	 * 
	 * @author: 何斌
	 * @date: 2015年12月29日
	 * @update 
	 */
	public void execJob();
}
