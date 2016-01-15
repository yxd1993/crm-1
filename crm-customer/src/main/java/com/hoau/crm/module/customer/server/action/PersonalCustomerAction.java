package com.hoau.crm.module.customer.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.customer.api.server.IPersonalCustomerService;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author: 何斌
 * @create: 2015年6月21日 上午9:29:56
 */
@Controller
@Scope("prototype")
public class PersonalCustomerAction extends AbstractAction{

	private static final long serialVersionUID = 1792156208938146327L;
	
	private List<PersonalCustomerEntity> personalCustomers;

	private PersonalCustomerEntity personalCustomerEntity;
	
	private PersonalCustomerContactEntity personalCustomerContactEntity;
	
	private List<PersonalCustomerContactEntity> personalCustomerContacts;
	
	private String cId;
	
	@Resource
	private IPersonalCustomerService personalCustomerService;
	
	/**
	 * 去个人客户主界面
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月21日
	 * @update 
	 */
	public String main(){
		return returnSuccess();
	}

	/**
	 * 分页查询
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月21日
	 * @update 
	 */
	public String queryPersonalCustomerInfo(){
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			personalCustomers = personalCustomerService.queryPersonalCustomerInfo(personalCustomerEntity, rb);
			this.setTotalCount(personalCustomerService.countPersonalCustomer(personalCustomerEntity));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	
	/**
	 * 查看客户
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月22日
	 * @update 
	 */
	public String lookPersonalCustomer(){
		try {
			personalCustomerEntity = personalCustomerService.queryPersonalCustomerInfoById(cId);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 查询个人客户联系人信息
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月22日
	 * @update 
	 */
	public String queryPersonalCustomerContactInfo(){
		try {
			personalCustomerContacts = personalCustomerService.queryPersonalCustomerContactInfo(personalCustomerContactEntity);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public List<PersonalCustomerEntity> getPersonalCustomers() {
		return personalCustomers;
	}

	public void setPersonalCustomers(List<PersonalCustomerEntity> personalCustomers) {
		this.personalCustomers = personalCustomers;
	}

	public PersonalCustomerEntity getPersonalCustomerEntity() {
		return personalCustomerEntity;
	}

	public void setPersonalCustomerEntity(
			PersonalCustomerEntity personalCustomerEntity) {
		this.personalCustomerEntity = personalCustomerEntity;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public PersonalCustomerContactEntity getPersonalCustomerContactEntity() {
		return personalCustomerContactEntity;
	}

	public void setPersonalCustomerContactEntity(
			PersonalCustomerContactEntity personalCustomerContactEntity) {
		this.personalCustomerContactEntity = personalCustomerContactEntity;
	}

	public List<PersonalCustomerContactEntity> getPersonalCustomerContacts() {
		return personalCustomerContacts;
	}

	public void setPersonalCustomerContacts(
			List<PersonalCustomerContactEntity> personalCustomerContacts) {
		this.personalCustomerContacts = personalCustomerContacts;
	}
	
}
