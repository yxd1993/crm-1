package com.hoau.crm.module.customer.api.server;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.BseDeptEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.DistrictEntity;
import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;

/**
 * 客户信息池SERVICE
 * 
 * @author: 何斌
 * @create: 2015年5月26日 上午10:34:01
 */
public interface ICustomerInfoPoolService {

	/**
	 * 新增上传客户信息
	 * 
	 * @param customerInfoPoolEntity
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update
	 */
	void addOrEditCustomer(CustomerInfoPoolEntity customerInfoPoolEntity);

	/**
	 * 删除上传客户信息
	 * 
	 * @param uploadCustomerEntity
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update
	 */
	void deleteCustomer(List<String> ids);

	/**
	 * 分页查询上传客户信息
	 * 
	 * @param customerInfoPoolEntity
	 * @param rowBounds
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update
	 */
	List<CustomerInfoPoolEntity> queryUploadCustomer(
			CustomerInfoPoolVo customerInfoPoolVo, RowBounds rowBounds, UserEntity currentUser);
	
	/**
	 * 查询当前条件的所有信息，用于导出功能
	 * 
	 * @param customerInfoPoolVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-1
	 * @update
	 */
	List<CustomerInfoPoolEntity> queryAllUploadCustomer(CustomerInfoPoolVo customerInfoPoolVo);
	
	/**
	 * 统计总数(导出)
	 * 
	 * @param customerInfoPoolVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月15日
	 * @update 
	 */
	long countAllUploadCustomer(CustomerInfoPoolVo customerInfoPoolVo);

	/**
	 * 查询上传客户信息总数
	 * 
	 * @param customerInfoPoolEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-26
	 * @update
	 */
	long countUploadCustomer(CustomerInfoPoolVo customerInfoPoolVo, UserEntity currentUser);
	
	/**
	 * 更新退回原因
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	void updateBackReason(CustomerInfoPoolEntity customerInfoPoolEntity, UserEntity currentUser);
	
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
	List<DistrictEntity> queryDistrictList(DistrictEntity districtEntity);
	
	/**
	 * 根据行政区域名称或者编码查询
	 * @param districtEntity
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月24日
	 * @update 
	 */
	DistrictEntity queryDistrictByNameOrCode(DistrictEntity districtEntity);
	
	/**
	 * 根据id查看客户共享客户信息
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月24日
	 * @update 
	 */
	CustomerInfoPoolEntity getCustomerInfoPoolById(String id);
	
	/**
	 *根据id标记是否已转为潜在
	 * 
	 * @param id
	 * @return
	 * @author: 275636	
	 * @date: 2015年7月31日
	 * @update 
	 */
	void updateCustomeriSpotential(String id);
	
	/**
	 * 事业部大区查询
	 * 
	 * @param supDeptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月19日
	 * @update 
	 */
	List<BseDeptEntity> queryDepts(String supDeptCode);
	
	/**
	 * 转让客户
	 * 
	 * @param idss
	 * @author: 何斌
	 * @date: 2015年12月3日
	 * @update 
	 */
	public void transferCustomer(UserEntity currentUser,TransferCustomerVO transferCustomerVO);
}
