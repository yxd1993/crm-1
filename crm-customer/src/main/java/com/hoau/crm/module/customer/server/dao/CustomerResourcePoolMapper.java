package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;

/**
 * 客户资源池DAO
 * 
 * @author: 何斌
 * @create: 2015年10月20日 上午10:50:30
 */
@Repository
public interface CustomerResourcePoolMapper {
	/**
	 * 分页查询
	 * @param customerResourcePoolVo
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public List<CustomerResourcePoolEntity> getCustomerResourcePools(Map<String,String> params,RowBounds rb);
	
	/**
	 *  统计
	 * @param params
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public long countCustomerResourcePool(Map<String,String> params);
	
	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public CustomerResourcePoolEntity getCustomerResourcePoolById(String id);
	
	/**
	 * 当天认领数
	 * 
	 * @param claimPerson
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public long getClaimNum(String claimPerson);
	
	/**
	 * 新增认领记录
	 * 
	 * @param claimPerson
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public void insertClaimRecord(Map<String,Object> params);
	
	/**
	 * 当天是否认领
	 * 
	 * @param claimPerson
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public long isClaimCurrent(String claimPerson);
	
	/**
	 * 更新认领数
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public void updateClaimRecord(Map<String,Object> params);
	
	/**
	 * 处理认领
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年10月21日
	 * @update 
	 */
	public void dealWithClaim(Map<String,String> params);
	
	/**
	 * 获取大区编码
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月22日
	 * @update 
	 */
	public String getRegionsCode(String deptCode);
}
