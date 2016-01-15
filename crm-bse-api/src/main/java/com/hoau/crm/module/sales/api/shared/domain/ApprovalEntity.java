package com.hoau.crm.module.sales.api.shared.domain;

/**
 * 审批实体
 * 
 * @author: 何斌
 * @create: 2015年6月16日 下午5:18:23
 */
public class ApprovalEntity {

	/**
	 * 合同流水号
	 */
	private String contractId;
	/**
	 * 审批人部门
	 */
	private String approvalDept;
	/**
	 * 审批人岗位
	 */
	private String approvalJob;
	/**
	 * 审批人工号
	 */
	private String approvalEmpCode;
	/**
	 * 审批人姓名
	 */
	private String approvalEmpName;
	/**
	 * 审批人电话
	 */
	private String approvalTelephone;
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getApprovalDept() {
		return approvalDept;
	}
	public void setApprovalDept(String approvalDept) {
		this.approvalDept = approvalDept;
	}
	public String getApprovalJob() {
		return approvalJob;
	}
	public void setApprovalJob(String approvalJob) {
		this.approvalJob = approvalJob;
	}
	public String getApprovalEmpCode() {
		return approvalEmpCode;
	}
	public void setApprovalEmpCode(String approvalEmpCode) {
		this.approvalEmpCode = approvalEmpCode;
	}
	public String getApprovalEmpName() {
		return approvalEmpName;
	}
	public void setApprovalEmpName(String approvalEmpName) {
		this.approvalEmpName = approvalEmpName;
	}
	public String getApprovalTelephone() {
		return approvalTelephone;
	}
	public void setApprovalTelephone(String approvalTelephone) {
		this.approvalTelephone = approvalTelephone;
	}
	
}
