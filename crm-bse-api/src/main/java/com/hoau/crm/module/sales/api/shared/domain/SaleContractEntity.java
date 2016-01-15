package com.hoau.crm.module.sales.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 销售合同实体
 * 
 * @author: 何斌
 * @create: 2015年6月11日 下午4:18:08
 */
public class SaleContractEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3549970827471320301L;
	/**
	 * 合同状态
	 */
	private String status;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 工作流说明
	 */
	private String workflowExplain;
	/**
	 * 紧急程度
	 */
	private String emergencyDegree;
	/**
	 * 流水号ID
	 */
	private String workflowCode;
	/**
	 * 申请日期
	 */
	private Date applyDate;
	/**
	 * 申请人
	 */
	private String applyUser;
	/**
	 * 申请人工号
	 */
	private String applyUserEmpCode;
	/**
	 * 申请人岗位
	 */
	private String applyUserJobName;
	/**
	 * 申请人部门
	 */
	private String applyUserDeptName;
	/**
	 * 申请门店名称
	 */
	private String applyUserDeclareName;
	/**
	 * 物流代码
	 */
	private String applyUserLogisticCode;
	/**
	 * 所属路区
	 */
	private String roadAreaCode;
	/**
	 * 所属大区
	 */
	private String regionsCode;
	/**
	 * 所属事业部
	 */
	private String businessUnitCode;
	/**
	 * 所属财务大区
	 */
	private String financeRegionsCode;
	/**
	 * 合同管理员
	 */
	private String contractAdmin;
	/**
	 * 是否客户经理级别
	 */
	private String ifSaleManager;
	/**
	 * 流程类型
	 */
	private String processType;
	/**
	 * 合同版本
	 */
	private String contractVersion;
	/**
	 * 签约属性
	 */
	private String signAttribute;
	/**
	 * DC账号
	 */
	private String dcAccount;
	/**
	 * 客户全称
	 */
	private String enterpriseName;
	/**
	 * 客户性质
	 */
	private String customerNature;
	/**
	 * 客户所属行业类型
	 */
	private String industryCode;
	/**
	 * 货物名称
	 */
	private String mainGoodsName;
	/**
	 * 包装
	 */
	private String typeOfPackage;
	/**
	 * 总产值
	 */
	private double ddMonthCount;
	/**
	 * DD折扣
	 */
	private double ddDiscount;
	/**
	 * DU折扣
	 */
	private double duDiscount;
	/**
	 * 最低保价费
	 */
	private double lowestValuationFee;
	/**
	 * 最低保价费率
	 */
	private double insuranceRate;
	/**
	 * 大件货服务约定
	 */
	private String cargoServiceAgreement;
	/**
	 * 最低提货费
	 */
	private double lowestDeliveryFee;
	/**
	 * 最低送货费
	 */
	private double lowestShippingFee;
	/**
	 * 提送货费约定
	 */
	private String agreedDelivery;
	/**
	 * 工本费
	 */
	private double expense;
	/**
	 * 信息费
	 */
	private double informationCost;
	/**
	 * 最低燃油费
	 */
	private double lowestFuel;
	/**
	 * 其他服务费约定
	 */
	private String serviceAgreement;
	/**
	 * 合同开始日期
	 */
	private Date contractStartDate;
	/**
	 * 合同结束日期
	 */
	private Date contractEndDate;
	/**
	 * 是否代收货款
	 */
	private String ifCod;
	/**
	 * 最低代收费
	 */
	private double collectionChargesMin;
	/**
	 * 最低代收费率
	 */
	private double chargeRateMin;
	/**
	 * 结算方式
	 */
	private String paymentMethod;
	/**
	 * 账期描述 
	 */
	private String accountDescribe;
	/**
	 * 客户提供合同版本
	 */
	private String provideContractVersion;
	/**
	 * 是否有非标合同条款
	 */
	private String ifHaveNonstandardContractTerm;
	/**
	 * 是否有非标运营条款
	 */
	private String ifHaveNonstandardOperatTerm;
	/**
	 * 是否开通网上下单
	 */
	private String ifOpenOnlineOrder;
	/**
	 * 原DD折扣
	 */
	private double oldDdDiscount;
	/**
	 * 原DU折扣
	 */
	private double oldDuDiscount;
	/**
	 * 原保价率
	 */
	private double oldInsuranceRate;
	/**
	 * 原最低保价费
	 */
	private double oldLowestValuationFee;
	/**
	 * 原最低提货费
	 */
	private double oldLowestDeliveryFee;
	/**
	 * 原最低送货费
	 */
	private double oldLowestShippingFee;
	/**
	 * 原工本费
	 */
	private double oldExpense;
	/**
	 * 原信息费
	 */
	private double oldInformationCost;
	/**
	 * 原最低燃油费
	 */
	private double oldLowestFuel;
	/**
	 * 原代收货款费率
	 */
	private double oldChargeRateMin;
	/**
	 * 申请事由说明
	 */
	private String applyInstruction;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWorkflowExplain() {
		return workflowExplain;
	}
	public void setWorkflowExplain(String workflowExplain) {
		this.workflowExplain = workflowExplain;
	}
	public String getEmergencyDegree() {
		return emergencyDegree;
	}
	public void setEmergencyDegree(String emergencyDegree) {
		this.emergencyDegree = emergencyDegree;
	}
	public String getWorkflowCode() {
		return workflowCode;
	}
	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public String getApplyUserEmpCode() {
		return applyUserEmpCode;
	}
	public void setApplyUserEmpCode(String applyUserEmpCode) {
		this.applyUserEmpCode = applyUserEmpCode;
	}
	public String getApplyUserJobName() {
		return applyUserJobName;
	}
	public void setApplyUserJobName(String applyUserJobName) {
		this.applyUserJobName = applyUserJobName;
	}
	public String getApplyUserDeptName() {
		return applyUserDeptName;
	}
	public void setApplyUserDeptName(String applyUserDeptName) {
		this.applyUserDeptName = applyUserDeptName;
	}
	public String getApplyUserDeclareName() {
		return applyUserDeclareName;
	}
	public void setApplyUserDeclareName(String applyUserDeclareName) {
		this.applyUserDeclareName = applyUserDeclareName;
	}
	public String getApplyUserLogisticCode() {
		return applyUserLogisticCode;
	}
	public void setApplyUserLogisticCode(String applyUserLogisticCode) {
		this.applyUserLogisticCode = applyUserLogisticCode;
	}
	public String getRoadAreaCode() {
		return roadAreaCode;
	}
	public void setRoadAreaCode(String roadAreaCode) {
		this.roadAreaCode = roadAreaCode;
	}
	public String getRegionsCode() {
		return regionsCode;
	}
	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode;
	}
	public String getBusinessUnitCode() {
		return businessUnitCode;
	}
	public void setBusinessUnitCode(String businessUnitCode) {
		this.businessUnitCode = businessUnitCode;
	}
	public String getFinanceRegionsCode() {
		return financeRegionsCode;
	}
	public void setFinanceRegionsCode(String financeRegionsCode) {
		this.financeRegionsCode = financeRegionsCode;
	}
	public String getContractAdmin() {
		return contractAdmin;
	}
	public void setContractAdmin(String contractAdmin) {
		this.contractAdmin = contractAdmin;
	}
	public String getIfSaleManager() {
		return ifSaleManager;
	}
	public void setIfSaleManager(String ifSaleManager) {
		this.ifSaleManager = ifSaleManager;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getContractVersion() {
		return contractVersion;
	}
	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}
	public String getSignAttribute() {
		return signAttribute;
	}
	public void setSignAttribute(String signAttribute) {
		this.signAttribute = signAttribute;
	}
	public String getDcAccount() {
		return dcAccount;
	}
	public void setDcAccount(String dcAccount) {
		this.dcAccount = dcAccount;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getCustomerNature() {
		return customerNature;
	}
	public void setCustomerNature(String customerNature) {
		this.customerNature = customerNature;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getMainGoodsName() {
		return mainGoodsName;
	}
	public void setMainGoodsName(String mainGoodsName) {
		this.mainGoodsName = mainGoodsName;
	}
	public String getTypeOfPackage() {
		return typeOfPackage;
	}
	public void setTypeOfPackage(String typeOfPackage) {
		this.typeOfPackage = typeOfPackage;
	}
	public double getDdMonthCount() {
		return ddMonthCount;
	}
	public void setDdMonthCount(double ddMonthCount) {
		this.ddMonthCount = ddMonthCount;
	}
	public double getDdDiscount() {
		return ddDiscount;
	}
	public void setDdDiscount(double ddDiscount) {
		this.ddDiscount = ddDiscount;
	}
	public double getDuDiscount() {
		return duDiscount;
	}
	public void setDuDiscount(double duDiscount) {
		this.duDiscount = duDiscount;
	}
	public double getLowestValuationFee() {
		return lowestValuationFee;
	}
	public void setLowestValuationFee(double lowestValuationFee) {
		this.lowestValuationFee = lowestValuationFee;
	}
	public double getInsuranceRate() {
		return insuranceRate;
	}
	public void setInsuranceRate(double insuranceRate) {
		this.insuranceRate = insuranceRate;
	}
	public String getCargoServiceAgreement() {
		return cargoServiceAgreement;
	}
	public void setCargoServiceAgreement(String cargoServiceAgreement) {
		this.cargoServiceAgreement = cargoServiceAgreement;
	}
	public double getLowestDeliveryFee() {
		return lowestDeliveryFee;
	}
	public void setLowestDeliveryFee(double lowestDeliveryFee) {
		this.lowestDeliveryFee = lowestDeliveryFee;
	}
	public double getLowestShippingFee() {
		return lowestShippingFee;
	}
	public void setLowestShippingFee(double lowestShippingFee) {
		this.lowestShippingFee = lowestShippingFee;
	}
	public String getAgreedDelivery() {
		return agreedDelivery;
	}
	public void setAgreedDelivery(String agreedDelivery) {
		this.agreedDelivery = agreedDelivery;
	}
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	public double getInformationCost() {
		return informationCost;
	}
	public void setInformationCost(double informationCost) {
		this.informationCost = informationCost;
	}
	public double getLowestFuel() {
		return lowestFuel;
	}
	public void setLowestFuel(double lowestFuel) {
		this.lowestFuel = lowestFuel;
	}
	public String getServiceAgreement() {
		return serviceAgreement;
	}
	public void setServiceAgreement(String serviceAgreement) {
		this.serviceAgreement = serviceAgreement;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getIfCod() {
		return ifCod;
	}
	public void setIfCod(String ifCod) {
		this.ifCod = ifCod;
	}
	public double getCollectionChargesMin() {
		return collectionChargesMin;
	}
	public void setCollectionChargesMin(double collectionChargesMin) {
		this.collectionChargesMin = collectionChargesMin;
	}
	public double getChargeRateMin() {
		return chargeRateMin;
	}
	public void setChargeRateMin(double chargeRateMin) {
		this.chargeRateMin = chargeRateMin;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getAccountDescribe() {
		return accountDescribe;
	}
	public void setAccountDescribe(String accountDescribe) {
		this.accountDescribe = accountDescribe;
	}
	public String getProvideContractVersion() {
		return provideContractVersion;
	}
	public void setProvideContractVersion(String provideContractVersion) {
		this.provideContractVersion = provideContractVersion;
	}
	public String getIfHaveNonstandardContractTerm() {
		return ifHaveNonstandardContractTerm;
	}
	public void setIfHaveNonstandardContractTerm(
			String ifHaveNonstandardContractTerm) {
		this.ifHaveNonstandardContractTerm = ifHaveNonstandardContractTerm;
	}
	public String getIfHaveNonstandardOperatTerm() {
		return ifHaveNonstandardOperatTerm;
	}
	public void setIfHaveNonstandardOperatTerm(String ifHaveNonstandardOperatTerm) {
		this.ifHaveNonstandardOperatTerm = ifHaveNonstandardOperatTerm;
	}
	public String getIfOpenOnlineOrder() {
		return ifOpenOnlineOrder;
	}
	public void setIfOpenOnlineOrder(String ifOpenOnlineOrder) {
		this.ifOpenOnlineOrder = ifOpenOnlineOrder;
	}
	public double getOldDdDiscount() {
		return oldDdDiscount;
	}
	public void setOldDdDiscount(double oldDdDiscount) {
		this.oldDdDiscount = oldDdDiscount;
	}
	public double getOldDuDiscount() {
		return oldDuDiscount;
	}
	public void setOldDuDiscount(double oldDuDiscount) {
		this.oldDuDiscount = oldDuDiscount;
	}
	public double getOldInsuranceRate() {
		return oldInsuranceRate;
	}
	public void setOldInsuranceRate(double oldInsuranceRate) {
		this.oldInsuranceRate = oldInsuranceRate;
	}
	public double getOldLowestValuationFee() {
		return oldLowestValuationFee;
	}
	public void setOldLowestValuationFee(double oldLowestValuationFee) {
		this.oldLowestValuationFee = oldLowestValuationFee;
	}
	public double getOldLowestDeliveryFee() {
		return oldLowestDeliveryFee;
	}
	public void setOldLowestDeliveryFee(double oldLowestDeliveryFee) {
		this.oldLowestDeliveryFee = oldLowestDeliveryFee;
	}
	public double getOldLowestShippingFee() {
		return oldLowestShippingFee;
	}
	public void setOldLowestShippingFee(double oldLowestShippingFee) {
		this.oldLowestShippingFee = oldLowestShippingFee;
	}
	public double getOldExpense() {
		return oldExpense;
	}
	public void setOldExpense(double oldExpense) {
		this.oldExpense = oldExpense;
	}
	public double getOldInformationCost() {
		return oldInformationCost;
	}
	public void setOldInformationCost(double oldInformationCost) {
		this.oldInformationCost = oldInformationCost;
	}
	public double getOldLowestFuel() {
		return oldLowestFuel;
	}
	public void setOldLowestFuel(double oldLowestFuel) {
		this.oldLowestFuel = oldLowestFuel;
	}
	public double getOldChargeRateMin() {
		return oldChargeRateMin;
	}
	public void setOldChargeRateMin(double oldChargeRateMin) {
		this.oldChargeRateMin = oldChargeRateMin;
	}
	public String getApplyInstruction() {
		return applyInstruction;
	}
	public void setApplyInstruction(String applyInstruction) {
		this.applyInstruction = applyInstruction;
	}
}
