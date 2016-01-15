package com.hoau.crm.module.customer.server.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IPhoneInfoService;
import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.shared.domain.PhoneInfoEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.vo.CustomerVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 客户信息管理ACTION
 * 
 * @author 蒋落琛
 * @date 2015-5-19
 */
@Controller
@Scope("prototype")
public class CustomerAction extends AbstractAction {

	private static final long serialVersionUID = -7906620442117930987L;

	/**
	 * 客户信息SERVICE
	 */
	@Resource
	private ICustomerService iCustomerService;
	
	/**
	 * 手机号信息查询接口
	 */
	@Resource
	private IPhoneInfoService iPhoneInfoService;
	/**
	 *历史跟进回顾service
	 */
	@Resource
	private IReviewHistoryService IReviewHistoryService;
	/**
	 * 客户信息集合
	 */
	private List<CustomerEntity> customerList;

	/**
	 * 客户信息VO
	 */
	private CustomerVo customerVo;

	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	
	/**
	 * 客户信息ID集合
	 */
	private List<String> ids;
	
	/**
	 * 手机号信息
	 */
	private PhoneInfoEntity phoneInfoEntity;
	
	/**
	 * 联系人信息集合
	 */
	private List<ContactEntity> contactList;
	
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;

	/**
	 *历史跟进信息
	 */
	private List<Map<String,Object>> historyList;
	
	/**
	 * 转让客户VO
	 */
	private TransferCustomerVO transferCustomerVO;
	/**
	 * 进入客户管理查看页面
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-19
	 * @update
	 */
	public String index() {
		return returnSuccess();
	}

	/**
	 * 分页查询客户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public String queryCustomeInfo() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			customerList = iCustomerService.queryCustomerInfo(customerVo,rb);
			//当前用户
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			if(customerVo.getSelectorParam() == null){
				this.setTotalCount(iCustomerService.countCustomer(customerVo,currentUser));
			}
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 分页查询客户信息 ：下拉组件
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-20
	 * @update
	 */
	public String queryCustomeInfoByCombo() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			customerList = iCustomerService.queryCustomerInfoByCombo(
					customerVo, rb);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据主键查询客户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-25
	 * @update
	 */
	public String queryCustomerInfoById(){
		try {
			customerEntity = iCustomerService.queryCustomerInfoById(customerEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据主键查询所有客户信息，包括客户运单日志
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-25
	 * @update
	 */
	public String queryAllCustomerInfoById(){
		try {
			customerEntity = iCustomerService.queryAllCustomerInfoById(customerEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 新增客户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-25
	 * @update
	 */
	public String addCustomer() {
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerService.addCustomer(customerEntity, currUser,null,null);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 删除客户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-1
	 * @update
	 */
	public String deleteCustomer(){
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerService.deleteCustomer(ids, currUser,customerEntity.getDelDesc());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据手机号查询手机信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-9
	 * @update
	 */
	public String queryPhoneInfoByPhone(){
		try {
			phoneInfoEntity = iPhoneInfoService.queryPhoneInfoByPhone(phoneInfoEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据客户ID查询联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	public String queryContactList(){
		try {
			contactList = iCustomerService.queryContactList(customerEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据客户ID查询历史跟进信息
	 * @return
	 * @author 丁勇
	 * @date 2015年9月8日
	 * @update 
	 */
	public String queryHistory(){
		// 参数map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountId", customerEntity.getId());
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			historyList = IReviewHistoryService.queryHistoryListByWeb(customerEntity, rb);
			this.setTotalCount(IReviewHistoryService.queryHistoryCount(map));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 新增联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	public String addContact() {
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerService.addContactInfo(contactEntity, currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 修改联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	public String editContact(){
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerService.updateContactInfo(contactEntity, currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 删除联系人信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-10
	 * @update
	 */
	public String deleteContact(){
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerService.deleteContact(ids, currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 修改联系人信息为默认
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-11
	 * @update
	 */
	public String updateContactIsDefault(){
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerService.updateContactIsDefault(contactEntity, currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 转为意向客户,并更新意向信息
	 * @return
	 * @author 丁勇
	 * @date 2015年6月30日
	 * @update 
	 */
	public String updateCustomerTurnIntention(){
		try {
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			iCustomerService.updateCustomerTurnIntention(customerEntity,currUser);
		} catch (BusinessException e) {
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
			iCustomerService.transferCustomer(currentUser, transferCustomerVO);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 注册官网账号
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月23日
	 * @update 
	 */
	public String registerObhAccount(){
        String result = "";
		try {
			String res = iCustomerService.registerObhAccount(customerEntity);
            if (BseConstants.NO.equals(res)){
                result = BseConstants.OBH_SUCCESS;
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            return returnError(e);
        }
        if(StringUtil.isEmpty(result)){
            return returnSuccess();
        }else{
            return returnSuccess(result);
        }
	}
	
	public List<CustomerEntity> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerEntity> customerList) {
		this.customerList = customerList;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public CustomerVo getCustomerVo() {
		return customerVo;
	}

	public void setCustomerVo(CustomerVo customerVo) {
		this.customerVo = customerVo;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public PhoneInfoEntity getPhoneInfoEntity() {
		return phoneInfoEntity;
	}

	public void setPhoneInfoEntity(PhoneInfoEntity phoneInfoEntity) {
		this.phoneInfoEntity = phoneInfoEntity;
	}

	public List<ContactEntity> getContactList() {
		return contactList;
	}

	public void setContactList(List<ContactEntity> contactList) {
		this.contactList = contactList;
	}

	public ContactEntity getContactEntity() {
		return contactEntity;
	}

	public void setContactEntity(ContactEntity contactEntity) {
		this.contactEntity = contactEntity;
	}

	public List<Map<String, Object>> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<Map<String, Object>> historyList) {
		this.historyList = historyList;
	}

	public TransferCustomerVO getTransferCustomerVO() {
		return transferCustomerVO;
	}

	public void setTransferCustomerVO(TransferCustomerVO transferCustomerVO) {
		this.transferCustomerVO = transferCustomerVO;
	}
	
}
