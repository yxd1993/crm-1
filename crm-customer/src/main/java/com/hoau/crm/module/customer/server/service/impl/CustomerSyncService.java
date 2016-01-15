package com.hoau.crm.module.customer.server.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.server.dao.DataDictionaryValueMapper;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.customer.server.si.dc.CRMServices;
import com.hoau.crm.module.customer.server.si.dc.CRMServices_Service;
import com.hoau.crm.module.customer.server.si.dc.CrmCustomer;
import com.hoau.crm.module.customer.server.si.dc.ResultMsg;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 客户信息同步DC SERVICE
 * @author: 何斌
 * @create: 2015年5月27日 上午10:10:45
 */
@Service
public class CustomerSyncService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerSyncService.class);
	
	@Resource
	private DataDictionaryValueMapper dataDictionaryValueMapper;
	
	@Resource
	private IEmployeeService employeeService;
	
	/**
	 * 意向客户新增获取DC账号
	 * 
	 * @param customerEntity
	 * @return
	 * @throws MalformedURLException
	 * @throws CustomerException
	 * @author: 何斌
	 * @date: 2015年6月13日
	 * @update 
	 */
	public String getDCAccount(CustomerEntity customerEntity){
		CrmCustomer customer = this.getCustomerInfo(customerEntity);
		ResultMsg resultMsg =  this.getService().addCustomer(customer);
		if(!resultMsg.isSuccess()){
			LOG.info(resultMsg.getErrormessage());
			if(!StringUtil.isEmpty(resultMsg.getKhzh())){
				return resultMsg.getKhzh();
			}else{
				throw new CustomerException(CustomerException.CUSTOMERINF_DC);
			}
		}
		return resultMsg.getKhzh();
	}

	/**
	 * CRM修改客户信息后,同步到DC
	 * 
	 * @param customerEntity
	 * @author: 何斌
	 * @date: 2015年6月13日
	 * @update 
	 */
	public void updateDcAccount(CustomerEntity customerEntity){
		CrmCustomer customer = this.getCustomerInfo(customerEntity);
		ResultMsg resultMsg = this.getService().modifyCustomer(customer);
		if(!resultMsg.isSuccess()){
			LOG.info(resultMsg.getErrormessage());
			throw new CustomerException(CustomerException.CUSTOMERINF_DC);
		}
	}
	
	/**
	 * CRM删除客户后,通知DC删除该对应客户
	 * 
	 * @param crmGuid
	 * @author: 何斌
	 * @date: 2015年6月13日
	 * @update 
	 */
	public void deleteDcAccount(String id){
		CrmCustomer customer = new CrmCustomer();
		customer.setCrmguid(id.toLowerCase());
		ResultMsg resultMsg = this.getService().deleteCustomer(customer);
		if(!resultMsg.isSuccess()){
			LOG.error(resultMsg.getErrormessage());
			throw new CustomerException(CustomerException.CUSTOMERINF_DC);
		}
	}
	
	/**
	 * @param customerEntity
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月5日
	 * @update 
	 */
	private CrmCustomer getCustomerInfo(CustomerEntity customerEntity) {
		CrmCustomer customerInfo = new  CrmCustomer();
		//备注
		if(StringUtil.isEmpty(customerEntity.getAccountRemark())){
			customerInfo.setBz(null);	
		}else{
			customerInfo.setBz(customerEntity.getAccountRemark());
		}
		//主要货物
		if(StringUtil.isEmpty(customerEntity.getMainGoodsName())){
			customerInfo.setCpmc(null);
		}else{
			customerInfo.setCpmc(customerEntity.getMainGoodsName());
		}
		//CRM账号
		customerInfo.setCrmKhzh(customerEntity.getAccountCode());
		//客户编号
		customerInfo.setCrmKhbh(customerEntity.getAccountCode());
		//CRM_GUID
		String uuid = customerEntity.getId().toLowerCase();
		customerInfo.setCrmguid(uuid);
		//财务联系人电话
		customerInfo.setCwlxdh(null);
		//财务联系人
		customerInfo.setCwlxr(null);
		//邮箱
		if(StringUtil.isEmpty(customerEntity.getContactEntity().geteMailAddress())){
			customerInfo.setEmail(null);
		}else{
			customerInfo.setEmail(customerEntity.getContactEntity().geteMailAddress());
		}
		//发货方向
		customerInfo.setFhfx(null);
		//公司邮编
		if(StringUtil.isEmpty(customerEntity.getDetailedAddressPostalCode())){
			customerInfo.setGsYb(null);
		}else{
			customerInfo.setGsYb(customerEntity.getDetailedAddressPostalCode());
		}
		//门店代码
		if(StringUtil.isEmpty(customerEntity.getSignTierCode())){
			if(StringUtil.isEmpty(customerEntity.getTierCode())){
				customerInfo.setGsbh(null);
			}else{
				customerInfo.setGsbh("N"+customerEntity.getTierCode());
			}
		}else{
			customerInfo.setGsbh("N"+customerEntity.getSignTierCode());
		}
		
		//客户标识
		customerInfo.setKhbs(null);
		//客户分类
		if(!StringUtil.isEmpty(customerEntity.getAccountSub()) ){
			customerInfo.setKhdj(this.getCustomerGradetype(customerEntity.getAccountSub()));
		}else{
			customerInfo.setKhdj("总公司");
		}
		//行业类型
		if(StringUtil.isEmpty(customerEntity.getIndustryCode())){
			customerInfo.setKhhylx(null);
		}else{
			customerInfo.setKhhylx(customerEntity.getIndustryCode());
		}
		//客户级别
		if(StringUtil.isEmpty(customerEntity.getAccountRatingCode())){
			customerInfo.setKhlb(null);
		}else{
			customerInfo.setKhlb(this.getCustomerLevelName(customerEntity.getAccountRatingCode()));
		}
		//客户名称
		if(StringUtil.isEmpty(customerEntity.getEnterpriseName())){
			customerInfo.setKhmc(null);
		}else{
			customerInfo.setKhmc(customerEntity.getEnterpriseName());
		}
		//客户性质
		if(StringUtil.isEmpty(customerEntity.getEnterpriseType())){
			customerInfo.setKhxz(null);
		}else{
			customerInfo.setKhxz(customerEntity.getEnterpriseType());
		}
		//客户状态
		if(StringUtil.isEmpty(customerEntity.getAccountType())){
			customerInfo.setKhzt(null);
		}else{
			customerInfo.setKhzt(this.convertAccountType(customerEntity.getAccountType()));
		}
		//联系电话
		if(StringUtil.isEmpty(customerEntity.getContactEntity().getTelephone())){
			customerInfo.setLxdh(null);
		}else{
			customerInfo.setLxdh(customerEntity.getContactEntity().getTelephone());
		}
		//联系人
		if(StringUtil.isEmpty(customerEntity.getContactEntity().getContactName())){
			customerInfo.setLxr(null);
		}else{
			customerInfo.setLxr(customerEntity.getContactEntity().getContactName());
		}
		//手机
		if(StringUtil.isEmpty(customerEntity.getContactEntity().getCellphone())){
			customerInfo.setMobilphone(null);
		}else{
			customerInfo.setMobilphone(customerEntity.getContactEntity().getCellphone());
		}
		//取货地址
		if(StringUtil.isEmpty(customerEntity.getDeliveryAddress())){
			customerInfo.setQhxxdz(null);
		}else{
			customerInfo.setQhxxdz(customerEntity.getDeliveryAddress());
		}
		//取货邮编
		if(StringUtil.isEmpty(customerEntity.getDeliveryAddressPostalCode())){
			customerInfo.setQhyb(null);
		}else{
			customerInfo.setQhyb(customerEntity.getDeliveryAddressPostalCode());
		}
		// 是否有效
		if(BseConstants.STATUE_CODE_NO.equals(customerEntity.getStatusCode())){
			customerInfo.setSfyx("0");
		}else{
			customerInfo.setSfyx("1");
		}
		//上级单位
		customerInfo.setSjgsbh(null);
		//所属总公司
		if(StringUtil.isEmpty(customerEntity.getParentCompany())){
			customerInfo.setSsjtgs(null);
		}else{
			customerInfo.setSsjtgs(customerEntity.getParentCompany().toLowerCase());
		}
		//详细地址
		if(StringUtil.isEmpty(customerEntity.getDetailedAddress())){
			customerInfo.setXxdz(null);
		}else{
			customerInfo.setXxdz(customerEntity.getDetailedAddress());
		}
		//所属一级公司
		customerInfo.setYjgsdm(null);
		if( null != customerEntity.getStartingTime()){
			customerInfo.setYwkssj(new SimpleDateFormat("yyyy-MM-dd").format(customerEntity.getStartingTime()));
		}else{
			customerInfo.setYwkssj(null);
		}
		//折扣率
		if(customerEntity.getDiscountRate() == null){
			customerInfo.setZkl(null);
		}else{
			customerInfo.setZkl(String.valueOf(customerEntity.getDiscountRate()));
		}
		//包装方式
		if(StringUtil.isEmpty(customerEntity.getTypeOfPackage())){
			customerInfo.setZybzfs(null);
		}else{
			customerInfo.setZybzfs(this.getTypeOfPackageName(customerEntity.getTypeOfPackage()));
		}
		customerInfo.setZysx(null);
		//客户组织
		customerInfo.setSFXSJLKH(customerEntity.getCustomerOfOrg());
		//负责人
		if(StringUtil.isEmpty(customerEntity.getManagePerson())){
			customerInfo.setXSJLXM(null);
		}else{
			customerInfo.setXSJLXM(customerEntity.getManagePerson());
		}
		//负责人工号(6位)
		if(StringUtil.isEmpty(customerEntity.getManageEmpCode())){
			customerInfo.setXSJLGH(null);
		}else{
			customerInfo.setXSJLGH(this.getManageAccount(customerEntity.getManageEmpCode()));
		}
		return customerInfo;
	}
	
	/**
	 * 获取WebService服务端口
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月13日
	 * @update 
	 */
	private CRMServices getService(){
		CRMServices_Service service = null;
		try {
			service = new CRMServices_Service(new URL(ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("dc.url")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        CRMServices port;
        if(service == null){
            throw new CustomerException(CustomerException.CUSTOMERINF_DC);
        }else {
            port = service.getCRMServicesPort();
        }
		return port;
	}
	
	/**
	 * 根据客户级别编码获取客户级别名称
	 * 
	 * @param customerLevelCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update 
	 */
	private String getCustomerLevelName(String customerLevelCode){
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("CUSTOMER_LEVEL");
		entity.setActive("Y");
		entity.setValueCode(customerLevelCode);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
		String value = "";
		if(lists.size()> 0){
			value = lists.get(0).getValueName();
		}
		return value;
	}
	
	/**
	 * 根据客户分类级别编码获取客户分类名称
	 * 
	 * @param customerGradetype
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update 
	 */
	private String getCustomerGradetype(String customerGradetype){
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("CUSTOMER_GRADETYPE");
		entity.setActive("Y");
		entity.setValueCode(customerGradetype);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
		String value = "";
		if(lists.size()> 0){
			value = lists.get(0).getValueName();
		}
		return value;
	}
	
	/**
	 * 根据包装方式编码获取包装方式名称
	 * 
	 * @param businessName
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月29日
	 * @update 
	 */
	private String getTypeOfPackageName(String typeOfPackageCode){
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("CUSTOMER_PACKAGING");
		entity.setActive("Y");
		entity.setValueCode(typeOfPackageCode);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
		String value = "";
		if(lists.size()> 0){
			value = lists.get(0).getValueName();
		}
		return value;
	}
	
	/**
	 * 客户性质转换
	 * 
	 * @param accountType
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月8日
	 * @update 
	 */
	private String convertAccountType(String accountType){
		if(BseConstants.CUSTOMER_NATURE_CHAT.equals(accountType)){
			return BseConstants.CUSTOMER_NATURE_POTENTIAL;
		}else if(BseConstants.CUSTOMER_NATURE_DELIVER.equals(accountType)){
			return BseConstants.CUSTOMER_NATURE_INTENTION;
		}else{
			return accountType;
		}
	}
	
	/**
	 * 获取6位工号
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月28日
	 * @update 
	 */
	private String getManageAccount(String empCode){
		String account = "";
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setEmpCode(empCode);
		employeeEntity = employeeService.queryEmployeeByEmpcode(employeeEntity);
		if(employeeEntity != null){
			account = employeeEntity.getAccount();
		}
		return account;
	}
}
