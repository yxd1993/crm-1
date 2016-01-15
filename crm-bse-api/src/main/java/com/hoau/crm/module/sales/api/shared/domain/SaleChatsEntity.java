package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 销售管理 客户洽谈实体类
 * 
 * @author 丁勇
 * @date 2015年6月9日
 */
public class SaleChatsEntity extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = -5185845591245052112L;

	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;
	/**
	 * 关联预约信息
	 */
	private SaleReserveEntity reserveEntity;
	
	/**
	 *签到信息
	 */
	private SignEntity sign;
	/**
	 * 员工信息
	 */
	private EmployeeEntity employeeEntity;

	/**
	 * 洽谈开始时间
	 */
	private Date chatStartTime;

	/**
	 * 洽谈结束时间
	 */
	private Date chatEndTime;
	/**
	 * 洽谈方式
	 */
	private String chatType;

	/**
	 * 门店负责人
	 */
	private String comTierEmpCode;
	
	/**
	 * 路区负责人
	 */
	private String comRoadEmpCode;

	/**
	 * 大区负责人
	 */
	private String comRegionsEmpCode;
	/**
	 * 事业部负责人
	 */
	private String comBusinessEmpCode;
	/**
	 * 营运副总
	 */
	private String comOdEmpCode;
	/**
	 * 集团总裁
	 */
	private String comCeoEmpCode;
	/**
	 *团队负责人
	 */
	private String teamManagerEmpCode;
	/**
	 * 客户负责人
	 */
	private String saleManEmpCode;
	/**
	 * 实际地址
	 */
	private String reserveAddress;
	/**
	 * 洽谈主题
	 */
	private String chatTheme;
	/**
	 * 洽谈内容
	 */
	private String chatContents;
	/**
	 * 删除原因
	 */
	private String delDesc;
	/**
	 * 是否有效
	 */
	private String active;
	
	/**
	 *客户端录入平台 
	 *Y:app端,N:web端
	 */
	private String platform;
	/**
	 *创建人的部门编号
	 */
	private String createDeptCode;
	

	public String getActive() {
		return active;
	}

	public String getChatContents() {
		return chatContents;
	}

	public Date getChatEndTime() {
		return chatEndTime;
	}

	public Date getChatStartTime() {
		return chatStartTime;
	}

	public String getChatTheme() {
		return chatTheme;
	}

	public String getChatType() {
		return chatType;
	}

	public String getComBusinessEmpCode() {
		return comBusinessEmpCode;
	}

	public String getComCeoEmpCode() {
		return comCeoEmpCode;
	}

	public String getComOdEmpCode() {
		return comOdEmpCode;
	}

	public String getComRegionsEmpCode() {
		return comRegionsEmpCode;
	}

	public String getComRoadEmpCode() {
		return comRoadEmpCode;
	}

	public String getComTierEmpCode() {
		return comTierEmpCode;
	}

	public String getTeamManagerEmpCode() {
		return teamManagerEmpCode;
	}

	public void setTeamManagerEmpCode(String teamManagerEmpCode) {
		this.teamManagerEmpCode = teamManagerEmpCode;
	}

	public ContactEntity getContactEntity() {
		return contactEntity;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public String getDelDesc() {
		return delDesc;
	}

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public SaleReserveEntity getReserveEntity() {
		return reserveEntity;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setChatContents(String chatContents) {
		this.chatContents = chatContents;
	}

	public void setChatEndTime(Date chatEndTime) {
		this.chatEndTime = chatEndTime;
	}

	public void setChatStartTime(Date chatStartTime) {
		this.chatStartTime = chatStartTime;
	}

	public void setChatTheme(String chatTheme) {
		this.chatTheme = chatTheme;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public void setComBusinessEmpCode(String comBusinessEmpCode) {
		this.comBusinessEmpCode = comBusinessEmpCode;
	}

	public void setComCeoEmpCode(String comCeoEmpCode) {
		this.comCeoEmpCode = comCeoEmpCode;
	}

	public void setComOdEmpCode(String comOdEmpCode) {
		this.comOdEmpCode = comOdEmpCode;
	}

	public void setComRegionsEmpCode(String comRegionsEmpCode) {
		this.comRegionsEmpCode = comRegionsEmpCode;
	}

	public void setComRoadEmpCode(String comRoadEmpCode) {
		this.comRoadEmpCode = comRoadEmpCode;
	}

	public void setComTierEmpCode(String comTierEmpCode) {
		this.comTierEmpCode = comTierEmpCode;
	}

	public String getSaleManEmpCode() {
		return saleManEmpCode;
	}

	public void setSaleManEmpCode(String saleManEmpCode) {
		this.saleManEmpCode = saleManEmpCode;
	}

	public void setContactEntity(ContactEntity contactEntity) {
		this.contactEntity = contactEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public void setDelDesc(String delDesc) {
		this.delDesc = delDesc;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}

	public void setReserveEntity(SaleReserveEntity reserveEntity) {
		this.reserveEntity = reserveEntity;
	}

	public String getReserveAddress() {
		return reserveAddress;
	}

	public void setReserveAddress(String reserveAddress) {
		this.reserveAddress = reserveAddress;
	}
	public SignEntity getSign() {
		return sign;
	}

	public void setSign(SignEntity sign) {
		this.sign = sign;
	}

	public String getCreateDeptCode() {
		return createDeptCode;
	}

	public void setCreateDeptCode(String createDeptCode) {
		this.createDeptCode = createDeptCode;
	}
	
}
