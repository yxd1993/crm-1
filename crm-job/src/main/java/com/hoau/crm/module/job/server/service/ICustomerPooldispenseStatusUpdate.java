package com.hoau.crm.module.job.server.service;


/**
 * 超过7天客户没有转为潜在的自动销毁
 * 
 *275636
 */
public interface ICustomerPooldispenseStatusUpdate {
	void updateCustDspenseStatus();
}
