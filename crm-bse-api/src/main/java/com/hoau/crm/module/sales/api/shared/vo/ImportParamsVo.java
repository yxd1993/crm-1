package com.hoau.crm.module.sales.api.shared.vo;

/**
 * 附件上传参数VO
 * 
 * @author: 何斌
 * @create: 2015年7月10日 下午2:04:14
 */
public class ImportParamsVo {
	/**
	 * DC账号
	 */
	private String dcAccount;
	/**
	 * 合同唯一标识
	 */
	private String id;
	/**
	 * 是否覆盖
	 */
	private String isCover;
	public String getDcAccount() {
		return dcAccount;
	}
	public void setDcAccount(String dcAccount) {
		this.dcAccount = dcAccount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsCover() {
		return isCover;
	}
	public void setIsCover(String isCover) {
		this.isCover = isCover;
	}
}
