package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.BseDeptEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.DistrictEntity;

/**
 * 上传客户信息DAO
 * @author: 何斌
 * @create: 2015年5月25日 下午3:14:14
 */
@Repository
public interface CustomerInfoPoolMapper {
	/**
	 * 新增上传客户信息
	 * 
	 * @param uploadCustomerEntity
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update 
	 */
	void addUploadCustomer(CustomerInfoPoolEntity uploadCustomerEntity);
	
	/**
	 * 删除上传客户信息
	 * 
	 * @param uploadCustomerEntity
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update 
	 */
	void deleteUploadCustomer(Map<String, Object> map);
	
	/**
	 * 修改上传客户信息
	 * 
	 * @param uploadCustomerEntity
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update 
	 */
	void updateUploadCustomer(CustomerInfoPoolEntity uploadCustomerEntity);
	
	/**
	 * 分页查询上传客户信息
	 * 
	 * @param uploadCustomerEntity
	 * @param rowBounds
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update 
	 */
	List<CustomerInfoPoolEntity> queryUploadCustomer(Map<String,String> parms, RowBounds rowBounds);
	
	/**
	 *  查询当前条件的所有信息，用于导出功能
	 * 
	 * @param parms
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-1
	 * @update
	 */
	List<CustomerInfoPoolEntity> queryAllUploadCustomer(Map<String,String> params);
	
	/**
	 * 查询上传客户信息总数
	 * 
	 * @param parms
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-26
	 * @update
	 */
	long countUploadCustomer(Map<String,String> parms);
	
	/**
	 * 判断当前信息是否重名,是否可以新增或修改
	 * 
	 * @param uploadCustomerEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-27
	 * @update
	 */
	long isAllowCustomer(CustomerInfoPoolEntity uploadCustomerEntity);
	
	/**
	 * 根据ID查询客户信息
	 * 
	 * @param customerInfoPoolEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-3
	 * @update
	 */
	CustomerInfoPoolEntity getCustomerInfoPoolById(String id);
	
	/**
	 * 更新退回原因
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	void updateBackReason(CustomerInfoPoolEntity customerInfoPoolEntity);
	
	/**
	 * 更新分发状态
	 * @param uploadCustomerEntity
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	void updateDispenseStatus(CustomerInfoPoolEntity customerInfoPoolEntity);
	
	/**
	 * 根据行政区域类型获得行政区域
	 * 
	 * @param districtType
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	List<DistrictEntity> getDistrictList(DistrictEntity districtEntity);
	
	/**
	 * 根据行政编码或者行政名称查询
	 * 
	 * @param districtEntity
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月24日
	 * @update 
	 */
	DistrictEntity getDistrictByNameOrCode(DistrictEntity districtEntity);
	
	/**
	 *根据id标记是否已转为潜在
	 * 
	 * @param id
	 * @return
	 * @author: 275636	
	 * @date: 2015年7月31日
	 * @update 
	 */
	void updateCustomeriSpotential(Map<String, String> customerinfoMap);
	
	/**
	 * 查询所有事业部
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月19日
	 * @update 
	 */
	List<BseDeptEntity> getBusinessDepts();
	
	/**
	 * 查询某个事业部下的大区
	 * 
	 * @param supDeptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月19日
	 * @update 
	 */
	List<BseDeptEntity> getRegionsDepts(String supDeptCode);
	
	/**
	 * 查询大区部门编码
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月19日
	 * @update 
	 */
	String getRegionsDeptCode(String deptName);
	
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
	 * 统计总数(导出)
	 * 
	 * @param customerInfoPoolVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月15日
	 * @update 
	 */
	long countAllUploadCustomer(Map<String,String> params);
	
}
