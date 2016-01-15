package com.hoau.crm.module.customer.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.BseDeptEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.customer.api.server.ICustomerInfoPoolService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.DistrictEntity;
import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author: 何斌
 * @create: 2015年5月26日 下午4:44:50
 */
@Controller
@Scope("prototype")
public class CustomerInfoPoolAction extends AbstractAction{

	private static final long serialVersionUID = -8940169640073106988L;
	
	@Resource
	private ICustomerInfoPoolService iCustomerInfoPoolService;

	/**
	 * 客户信息集合
	 */
	private List<CustomerInfoPoolEntity> customerInfoPoolList;
	
	/**
	 * 客户信息实体
	 */
	private CustomerInfoPoolEntity customerInfoPoolEntity;
	
	/**
	 * 客户信息VO
	 */
	private CustomerInfoPoolVo customerInfoPoolVo;
	
	/**
	 * 客户信息ID集合
	 */
	private List<String> ids;
	
	/**
	 * 行政区域集合
	 */
	private List<DistrictEntity> districtInfoList;
	
	/**
	 * 行政区域类型
	 */
	private String districtType;
	
	/**
	 * 行政区域上级编码
	 */
	private String parentDistrictCode;
	
	/**
	 * 客户ID
	 */
	private String customerPoolID;
	
	/**
	 * 上级部门
	 */
	private String supDeptCode;
	
	/**
	 * 部门集合
	 */
	private List<BseDeptEntity> depts;
	
	/**
	 * 转让客户VO
	 */
	private TransferCustomerVO transferCustomerVO;
	
	/**
	 * 分页查询客户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-26
	 * @update
	 */
	public String queryCustomerInfoPool(){
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			RowBounds rb = new RowBounds(this.start, this.limit);
			customerInfoPoolList = iCustomerInfoPoolService.queryUploadCustomer(customerInfoPoolVo, rb, currUser);
			this.setTotalCount(iCustomerInfoPoolService.countUploadCustomer(customerInfoPoolVo, currUser));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据id查看客户共享客户信息
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月24日
	 * @update 
	 */
	public String queryCustomerInfoPoolById(){
		try {
			customerInfoPoolEntity = 
				iCustomerInfoPoolService.getCustomerInfoPoolById(customerInfoPoolEntity.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 新增或修改客户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-27
	 * @update
	 */
	public String addOrEditCustomerInfoPool(){
		try {
			iCustomerInfoPoolService.addOrEditCustomer(customerInfoPoolEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 批量删除客户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-27
	 * @update
	 */
	public String deleteCustomerInfoPool(){
		try {
			iCustomerInfoPoolService.deleteCustomer(ids);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 退回客户
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	public String backCustomer(){
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerInfoPoolService.updateBackReason(customerInfoPoolEntity, currUser);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 查询行政区域信息
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月23日
	 * @update 
	 */
	public String queryDistrictInfo(){
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setDistrictType(districtType);
		districtEntity.setParentDistrictCode(parentDistrictCode);
		districtInfoList = iCustomerInfoPoolService.queryDistrictList(districtEntity);
		return returnSuccess();
	}
	
	/**
	 *根据id标记是否已转为潜在
	 * 
	 * @param id
	 * @return
	 * @author: 275636	
	 * @date: 2015年7月31日
	 * @update 
	 */
	public String updateCustomeriSpotential(){
		try {
			iCustomerInfoPoolService.updateCustomeriSpotential(customerPoolID);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 查询事业部大区 
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月19日
	 * @update 
	 */
	public String queryDepts(){
		try {
			depts = iCustomerInfoPoolService.queryDepts(supDeptCode);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 转让客户
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月3日
	 * @update 
	 */
	public String transferCustomer(){
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerInfoPoolService.transferCustomer(currentUser,transferCustomerVO);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public List<CustomerInfoPoolEntity> getCustomerInfoPoolList() {
		return customerInfoPoolList;
	}

	public void setCustomerInfoPoolList(
			List<CustomerInfoPoolEntity> customerInfoPoolList) {
		this.customerInfoPoolList = customerInfoPoolList;
	}

	public CustomerInfoPoolEntity getCustomerInfoPoolEntity() {
		return customerInfoPoolEntity;
	}

	public void setCustomerInfoPoolEntity(
			CustomerInfoPoolEntity customerInfoPoolEntity) {
		this.customerInfoPoolEntity = customerInfoPoolEntity;
	}

	public CustomerInfoPoolVo getCustomerInfoPoolVo() {
		return customerInfoPoolVo;
	}

	public void setCustomerInfoPoolVo(CustomerInfoPoolVo customerInfoPoolVo) {
		this.customerInfoPoolVo = customerInfoPoolVo;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<DistrictEntity> getDistrictInfoList() {
		return districtInfoList;
	}

	public void setDistrictInfoList(List<DistrictEntity> districtInfoList) {
		this.districtInfoList = districtInfoList;
	}

	public String getDistrictType() {
		return districtType;
	}

	public void setDistrictType(String districtType) {
		this.districtType = districtType;
	}

	public String getParentDistrictCode() {
		return parentDistrictCode;
	}

	public void setParentDistrictCode(String parentDistrictCode) {
		this.parentDistrictCode = parentDistrictCode;
	}

	public String getCustomerPoolID() {
		return customerPoolID;
	}

	public void setCustomerPoolID(String customerPoolID) {
		this.customerPoolID = customerPoolID;
	}

	public String getSupDeptCode() {
		return supDeptCode;
	}

	public void setSupDeptCode(String supDeptCode) {
		this.supDeptCode = supDeptCode;
	}

	public List<BseDeptEntity> getDepts() {
		return depts;
	}

	public void setDepts(List<BseDeptEntity> depts) {
		this.depts = depts;
	}

	public TransferCustomerVO getTransferCustomerVO() {
		return transferCustomerVO;
	}

	public void setTransferCustomerVO(TransferCustomerVO transferCustomerVO) {
		this.transferCustomerVO = transferCustomerVO;
	}
	
}
