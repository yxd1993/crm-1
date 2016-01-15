package com.hoau.crm.module.bse.api.shared.domain;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.domain.SweepSignEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 历史操作和回顾操作实体
 * 
 * @author 丁勇
 * @date 2015年6月24日
 */
public class ReviewHistoryEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -7338393317637223992L;

	/**
	 * 客户
	 */
	private CustomerEntity customerEntity;
	/**
	 * 联系人
	 */
	private ContactEntity contactEntity;
	/**
	 * 预约
	 */
	private SaleReserveEntity reserveEntity;
	/**
	 * 洽談
	 */
	private SaleChatsEntity chatsEntity;
	/**
	 * 签到
	 */
	private SignEntity signEntity;
	/**
	 *,门店走访扫描签到
	 */
	private SweepSignEntity sweepSignEntity;
	/**
	 * 执行操作描述
	 */
	private String operateType;
	/**
	 * 操作类型名称
	 */
	private String operateName;

	/**
	 *操作人
	 */
	private String empName;
	/**
	 * 
	 * @author 丁勇
	 * @date 2015年6月29日
	 * @update
	 */
	public ReviewHistoryEntity() {
	}

	/**
	 * 
	 * @param customerEntity
	 * @param contactEntity
	 * @param reserveEntity
	 * @param chatsEntity
	 * @param signEntity
	 * @param operateType
	 * @author 丁勇
	 * @date 2015年6月25日
	 * @update
	 */
	public ReviewHistoryEntity(CustomerEntity customerEntity,
			ContactEntity contactEntity, SaleReserveEntity reserveEntity,
			SaleChatsEntity chatsEntity,SignEntity signEntity,
			String operateType, String createUser) {
		this.customerEntity = customerEntity;
		this.contactEntity = contactEntity;
		this.reserveEntity = reserveEntity;
		this.chatsEntity = chatsEntity;
		this.signEntity = signEntity;
		this.operateType = operateType;
		this.setCreateUser(createUser);
	}

	public SaleChatsEntity getChatsEntity() {
		return chatsEntity;
	}

	public ContactEntity getContactEntity() {
		return contactEntity;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public SignEntity getSignEntity() {
		return signEntity;
	}

	public String getOperateType() {
		return operateType;
	}

	public SaleReserveEntity getReserveEntity() {
		return reserveEntity;
	}

	public void setChatsEntity(SaleChatsEntity chatsEntity) {
		this.chatsEntity = chatsEntity;
	}

	public void setContactEntity(ContactEntity contactEntity) {
		this.contactEntity = contactEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public void setSignEntity(SignEntity signEntity) {
		this.signEntity = signEntity;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public void setReserveEntity(SaleReserveEntity reserveEntity) {
		this.reserveEntity = reserveEntity;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public SweepSignEntity getSweepSignEntity() {
		return sweepSignEntity;
	}

	public void setSweepSignEntity(SweepSignEntity sweepSignEntity) {
		this.sweepSignEntity = sweepSignEntity;
	}
	
}
