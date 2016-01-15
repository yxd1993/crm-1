package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.Date;
import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.PhoneInfoEntity;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;

/**
 * 客户信息RESTFUL接口返回VO
 *
 * @author 蒋落琛
 * @date 2015-6-15
 */
public class CustomerAppVo{

	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	
	/**
	 *是否有签到
	 */
	private String IsSign = "N";
	/**
	 * 客户陪同人员信息
	 */
	private DepartmentVo departmentVo;
	
	/**
	 * 客户信息集合
	 */
	private List<CustomerEntity> customerEntityList;
	
	/**
	 * 资源客户信息集合
	 */
	private List<CustomerInfoPoolEntity> customerPooList;
	
	/**
	 * 资源客户信息
	 */
	private CustomerInfoPoolEntity customerPooLEntity;
	
	/**
	 * 联系人信息集合
	 */
	private List<ContactEntity> contactEntityList;
	
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;
	
	/**
	 * 手机号信息
	 */
	private PhoneInfoEntity phoneInfoEntity;
	
	/**
	 * 客户信息公共选择器查询参数  CRM账号、企业名称、联系人
	 */
	private String selectorParam;
	
	/**
	 * 客户信息公共选择器查询类型
	 */
	private int selectorType;
	
	/**
	 * 排序字段
	 */
	private String sortType;
	
	/**
	 * 排序规则
	 */
	private String sortTypeSub;
	
	/**
	 * 客户ID
	 */
	private String customerId;
	
	/**
	 * 开始时间
	 */
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 洽谈开始时间
	 */
	private Date chatsStartDate;
	
	/**
	 * 洽谈结束时间
	 */
	private Date chatsEndDate;
	
	/**
	 * 客户性质
	 */
	private String accountType;
	
	/**
	 * 合同状态
	 */
	private String contractStatus;
	
	/**
     * 每页数据量
     */
	private int limit;
    
    /**
     * 分布起始位置
     */
	private int start;
    
    /**
     * 分页数据总长度
     */
	private Long totalCount;
    
    /**
     * 客户信息ID集合
     */
	private List<String> ids;

	/**
	 *资源池客户
	 */
	private List<CustomerResourcePoolEntity> customerResourcePoolList;
	
	/**
	 * 转让客户
	 */
	private TransferCustomerVO transferCustomerVO;
	
	/**
	 * 共享客户状态
	 */
	private String customerInfoPoolStatus;
	
	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public List<CustomerEntity> getCustomerEntityList() {
		return customerEntityList;
	}

	public void setCustomerEntityList(List<CustomerEntity> customerEntityList) {
		this.customerEntityList = customerEntityList;
	}

	public String getSelectorParam() {
		return selectorParam;
	}

	public void setSelectorParam(String selectorParam) {
		this.selectorParam = selectorParam;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<ContactEntity> getContactEntityList() {
		return contactEntityList;
	}

	public void setContactEntityList(List<ContactEntity> contactEntityList) {
		this.contactEntityList = contactEntityList;
	}

	public PhoneInfoEntity getPhoneInfoEntity() {
		return phoneInfoEntity;
	}

	public void setPhoneInfoEntity(PhoneInfoEntity phoneInfoEntity) {
		this.phoneInfoEntity = phoneInfoEntity;
	}

	public ContactEntity getContactEntity() {
		return contactEntity;
	}

	public void setContactEntity(ContactEntity contactEntity) {
		this.contactEntity = contactEntity;
	}

	public DepartmentVo getDepartmentVo() {
		return departmentVo;
	}

	public void setDepartmentVo(DepartmentVo departmentVo) {
		this.departmentVo = departmentVo;
	}

	public List<CustomerInfoPoolEntity> getCustomerPooList() {
		return customerPooList;
	}

	public void setCustomerPooList(List<CustomerInfoPoolEntity> customerPooList) {
		this.customerPooList = customerPooList;
	}

	public Date getChatsStartDate() {
		return chatsStartDate;
	}

	public void setChatsStartDate(Date chatsStartDate) {
		this.chatsStartDate = chatsStartDate;
	}

	public Date getChatsEndDate() {
		return chatsEndDate;
	}

	public void setChatsEndDate(Date chatsEndDate) {
		this.chatsEndDate = chatsEndDate;
	}

	public CustomerInfoPoolEntity getCustomerPooLEntity() {
		return customerPooLEntity;
	}

	public void setCustomerPooLEntity(CustomerInfoPoolEntity customerPooLEntity) {
		this.customerPooLEntity = customerPooLEntity;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIsSign() {
		return IsSign;
	}

	public void setIsSign(String isSign) {
		IsSign = isSign;
	}

	public List<CustomerResourcePoolEntity> getCustomerResourcePoolList() {
		return customerResourcePoolList;
	}

	public void setCustomerResourcePoolList(
			List<CustomerResourcePoolEntity> customerResourcePoolList) {
		this.customerResourcePoolList = customerResourcePoolList;
	}

	public TransferCustomerVO getTransferCustomerVO() {
		return transferCustomerVO;
	}

	public void setTransferCustomerVO(TransferCustomerVO transferCustomerVO) {
		this.transferCustomerVO = transferCustomerVO;
	}

	public int getSelectorType() {
		return selectorType;
	}

	public void setSelectorType(int selectorType) {
		this.selectorType = selectorType;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortTypeSub() {
		return sortTypeSub;
	}

	public void setSortTypeSub(String sortTypeSub) {
		this.sortTypeSub = sortTypeSub;
	}

	public String getCustomerInfoPoolStatus() {
		return customerInfoPoolStatus;
	}

	public void setCustomerInfoPoolStatus(String customerInfoPoolStatus) {
		this.customerInfoPoolStatus = customerInfoPoolStatus;
	}
	
}
