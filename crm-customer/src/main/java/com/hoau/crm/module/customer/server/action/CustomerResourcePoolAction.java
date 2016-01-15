/**
 * 
 */
package com.hoau.crm.module.customer.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.ICustomerResourcePoolService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;
import com.hoau.crm.module.customer.api.shared.vo.CustomerResourcePoolVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 *	客户资源池 ACTION
 * @author 丁勇
 * @date 2015年10月19日
 */
@Controller
@Scope("prototype")
public class CustomerResourcePoolAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -6970502848497882375L;
	
	private CustomerResourcePoolVo customerResourcePoolVo;
	
	/**
	 * 主键
	 */
	private String cId;
	
	private List<CustomerResourcePoolEntity> customerResourcePools;
	
	private CustomerResourcePoolEntity customerResourcePoolEntity;
	
	@Resource
	private ICustomerResourcePoolService customerResourcePoolService;
	
	/**
	 * 剩余认领数
	 */
	private Long claimNum;
	
	/**
	 * 客户集合
	 */
	private List<String> ids;
	
	/**
	 * 分页查询
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public String queryCustomerResourcePools(){
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			customerResourcePools = customerResourcePoolService.queryCustomerResourcePools(customerResourcePoolVo, rb);
			//当前用户
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			this.setTotalCount(customerResourcePoolService.countCustomerResourcePool(customerResourcePoolVo,currentUser));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据主键查询信息
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public String queryCustomerResourcePoolById(){
		try {
			customerResourcePoolEntity = customerResourcePoolService.queryCustomerResourcePoolById(cId);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 认领客户
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	public String claimCustomerResourcePool(){
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		try {
			claimNum = customerResourcePoolService.claimCustomerResourcePool(ids,currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	public CustomerResourcePoolVo getCustomerResourcePoolVo() {
		return customerResourcePoolVo;
	}

	public void setCustomerResourcePoolVo(
			CustomerResourcePoolVo customerResourcePoolVo) {
		this.customerResourcePoolVo = customerResourcePoolVo;
	}

	public List<CustomerResourcePoolEntity> getCustomerResourcePools() {
		return customerResourcePools;
	}

	public void setCustomerResourcePools(
			List<CustomerResourcePoolEntity> customerResourcePools) {
		this.customerResourcePools = customerResourcePools;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public CustomerResourcePoolEntity getCustomerResourcePoolEntity() {
		return customerResourcePoolEntity;
	}

	public void setCustomerResourcePoolEntity(
			CustomerResourcePoolEntity customerResourcePoolEntity) {
		this.customerResourcePoolEntity = customerResourcePoolEntity;
	}

	public Long getClaimNum() {
		return claimNum;
	}

	public void setClaimNum(Long claimNum) {
		this.claimNum = claimNum;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
