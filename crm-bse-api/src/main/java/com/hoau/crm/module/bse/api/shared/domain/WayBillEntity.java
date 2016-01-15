package com.hoau.crm.module.bse.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 运单信息实体
 * 
 * @author 蒋落琛
 * @date 2015-7-6
 */
public class WayBillEntity extends BaseEntity {

	private static final long serialVersionUID = -6194237619236459709L;

	/**
	 * 数据类型 1、运单 2、修改金额  3、签收 4、异常 5、投诉 6、理赔 
	 */
	private String dataType;

	/**
	 * 运单编号
	 */
	private String billNum;

	/**
	 * 迪辰账号
	 */
	private String dcAccount;

	/**
	 * 运输类型
	 * 10000000000000000001:定日达
	 * 20000000000000000001:整车
	 * 30000000000000000001:普通零担
	 * 40000000000000000001:经济快运
	 * 50000000000000000001:偏线
	 */
	private String transportType;

	/**
	 * 开单时间
	 */
	private String billingDate;

	/**
	 * 托运日期
	 */
	private String checkDate;

	/**
	 * 起运地
	 */
	private String startingPoint;

	/**
	 * 目的地
	 */
	private String destination;

	/**
	 * 发货人
	 */
	private String shipper;

	/**
	 * 收货人
	 */
	private String consignee;

	/**
	 * 开单金额
	 */
	private double billingAmount;

	/**
	 * 实收金额
	 */
	private double incomeAmount;

	/**
	 * 收款门店
	 */
	private String incomeDept;
	
	/**
	 * 是否有效
	 */
	private String active;

	/************************ 异常上报信息开始 *********************/
	/**
	 * 异常编号
	 */
	// private String abnormalNum;

	/**
	 * 异常环节
	 */
	// private String abnormalParts;

	/**
	 * 异常类型
	 */
	// private String abnormalType;

	/**
	 * 异常处理状态
	 */
	// private String abnormalStatus;
	/************************ 异常上报信息结束 *********************/
	/************************ 理赔信息开始 *********************/
	/**
	 * 理赔单号
	 */
	// private String compensationNum;

	/**
	 * 理赔类型
	 */
	// private String compensationType;

	/**
	 * 理赔状态
	 */
	// private String compensationStatus;
	/************************ 理赔信息结束 *********************/
	/************************ 投诉信息开始 *********************/
	/**
	 * 投诉编号
	 */
	// private String complaintNum;

	/**
	 * 投诉类型
	 */
	// private String complaintType;

	/**
	 * 投诉状态
	 */
	// private String complaintStatus;
	/************************ 投诉信息结束 *********************/
	/************************ 签收信息开始 *********************/
	/**
	 * 签收状态   0正常签收 1异常签收
	 */
	private String signStatus;
	
	/**
	* 签收编号
	*/
	private String signNo;

	/************************ 签收信息结束 *********************/

	public String getBillNum() {
		return billNum;
	}

	public void setBillNum(String billNum) {
		this.billNum = billNum;
	}

	public String getDcAccount() {
		return dcAccount;
	}

	public void setDcAccount(String dcAccount) {
		this.dcAccount = dcAccount;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public double getBillingAmount() {
		return billingAmount;
	}

	public void setBillingAmount(double billingAmount) {
		this.billingAmount = billingAmount;
	}

	public double getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIncomeDept() {
		return incomeDept;
	}

	public void setIncomeDept(String incomeDept) {
		this.incomeDept = incomeDept;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
}
