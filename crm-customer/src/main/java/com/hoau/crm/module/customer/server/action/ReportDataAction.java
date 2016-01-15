package com.hoau.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.customer.api.server.IReportDataService;
import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 报表数据ACTION
 * @author: 何斌
 * @create: 2015年6月2日 下午6:02:07
 */
@Controller
@Scope("prototype")
public class ReportDataAction extends AbstractAction{
		
	private static final long serialVersionUID = 8887144809483219311L;
	
	@Resource
	private IReportDataService reportDataService;
	
	/**
	 * 客户行业数据
	 */
	private List<ReportDataEntity> customerIndustryDataLists  = new ArrayList<ReportDataEntity>();
	
	/**
	 * 客户性质数据
	 */
	private List<ReportDataEntity> customerNatureDataLists  = new ArrayList<ReportDataEntity>();
	
	/**
	 * 总客户数
	 */
	private long allCustomer;
	
	public String customerIndustryData(){
		customerIndustryDataLists = reportDataService.queryCustomerIndustryData();
		return returnSuccess();
	};
	
	public String customerNatureData(){
		customerNatureDataLists = reportDataService.queryCustomerNatureData();
		return returnSuccess();
	}
  
	public String allCustomerData(){
		allCustomer = reportDataService.countAllCustomer();
		return returnSuccess();
	}
	public List<ReportDataEntity> getCustomerIndustryDataLists() {
		return customerIndustryDataLists;
	}

	public void setCustomerIndustryDataLists(
			List<ReportDataEntity> customerIndustryDataLists) {
		this.customerIndustryDataLists = customerIndustryDataLists;
	}

	public List<ReportDataEntity> getCustomerNatureDataLists() {
		return customerNatureDataLists;
	}

	public void setCustomerNatureDataLists(
			List<ReportDataEntity> customerNatureDataLists) {
		this.customerNatureDataLists = customerNatureDataLists;
	}

	public long getAllCustomer() {
		return allCustomer;
	}

	public void setAllCustomer(long allCustomer) {
		this.allCustomer = allCustomer;
	}
	
}
