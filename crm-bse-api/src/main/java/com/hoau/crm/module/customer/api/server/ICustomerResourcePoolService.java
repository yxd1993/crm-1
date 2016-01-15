package com.hoau.crm.module.customer.api.server;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;
import com.hoau.crm.module.customer.api.shared.vo.CustomerResourcePoolVo;

/**
 * @author: 何斌
 * @create: 2015年10月20日 上午10:44:48
 */
public interface ICustomerResourcePoolService {

	/**
	 * 分页查询
	 * @param customerResourcePoolVo
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public List<CustomerResourcePoolEntity> queryCustomerResourcePools(CustomerResourcePoolVo customerResourcePoolVo,RowBounds rb);
	
	/**
	 * 统计
	 * @param customerResourcePoolVo
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public long countCustomerResourcePool(CustomerResourcePoolVo customerResourcePoolVo,UserEntity currentUser);
	
	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public CustomerResourcePoolEntity queryCustomerResourcePoolById(String id);
	
	/**
	 * 认领
	 * @param ids
	 * @param currentUser
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月27日
	 * @update 
	 */
	public long claimCustomerResourcePool(List<String> ids, UserEntity currentUser);
	
	
	
}
