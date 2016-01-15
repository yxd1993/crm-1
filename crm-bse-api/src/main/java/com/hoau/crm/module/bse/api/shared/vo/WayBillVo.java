package com.hoau.crm.module.bse.api.shared.vo;

import java.io.Serializable;
import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.WayBillEntity;

/**
 * 运单VO对象
 * 
 * @author 蒋落琛
 * @date 2015-7-6
 */
public class WayBillVo implements Serializable {

	private static final long serialVersionUID = 905795424317678870L;
	
	/**
	 *客户id
	 */
	private String accountId;
	
	/**
	 *运单日志集合
	 */
	private List<String> wayBillLogList;
	/**
	 * 运单数据集合
	 */
	private List<WayBillEntity> wayBillList;

	/**
	 * 成功数据的ID
	 */
	private List<String> successIdList;

	/**
	 * 失败数据的ID
	 */
	private List<String> failureIdList;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<String> getSuccessIdList() {
		return successIdList;
	}

	public void setSuccessIdList(List<String> successIdList) {
		this.successIdList = successIdList;
	}
	
	public List<String> getWayBillLogList() {
		return wayBillLogList;
	}

	public void setWayBillLogList(List<String> wayBillLogList) {
		this.wayBillLogList = wayBillLogList;
	}

	public List<String> getFailureIdList() {
		return failureIdList;
	}

	public void setFailureIdList(List<String> failureIdList) {
		this.failureIdList = failureIdList;
	}

	public List<WayBillEntity> getWayBillList() {
		return wayBillList;
	}

	public void setWayBillList(List<WayBillEntity> wayBillList) {
		this.wayBillList = wayBillList;
	}
}
