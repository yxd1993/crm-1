package com.hoau.crm.module.customer.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.DataDictionaryValueException;
import com.hoau.crm.module.bse.server.dao.DataDictionaryValueMapper;
import com.hoau.crm.module.customer.api.server.ICustomerResourcePoolService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerResourcePoolException;
import com.hoau.crm.module.customer.api.shared.vo.CustomerResourcePoolVo;
import com.hoau.crm.module.customer.server.dao.CustomerMapper;
import com.hoau.crm.module.customer.server.dao.CustomerResourcePoolMapper;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 客户资源池SERVICE
 * 
 * @author: 何斌
 * @create: 2015年10月20日 上午11:11:35
 */
@Service
public class CustomerResourcePoolService implements ICustomerResourcePoolService {
	
	@Resource
	private CustomerResourcePoolMapper customerResourcePoolMapper;
	
	@Resource
	private DataDictionaryValueMapper dataDictionaryValueMapper;
	
	@Resource
	private CustomerMapper customerMapper;
	@Resource
	private IDepartmentService iDepartmentService;
	
	@Override
	public List<CustomerResourcePoolEntity> queryCustomerResourcePools(
			CustomerResourcePoolVo customerResourcePoolVo, RowBounds rb) {
		if(rb == null){
			throw new CustomerResourcePoolException(CustomerResourcePoolException.RB_NULL);
		}
		if(customerResourcePoolVo == null || customerResourcePoolVo.getCustomerResourcePoolEntity() == null){
			throw new CustomerResourcePoolException(CustomerResourcePoolException.PARAM_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		CustomerResourcePoolEntity customerResourcePoolEntity = customerResourcePoolVo.getCustomerResourcePoolEntity();
		//客户企业名称
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getEnterpriseName())){
			params.put("enterpriseName", "%" + customerResourcePoolEntity.getEnterpriseName() + "%");
		}
		//客户性质
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getAccountType())){
			params.put("accountType",customerResourcePoolEntity.getAccountType());
		}
		//所属行业
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getIndustryCode())){
			params.put("industryCode", customerResourcePoolEntity.getIndustryCode());
		}
		//所属大区
		if (!StringUtil.isEmpty(customerResourcePoolEntity.getRegions())) {
			params.put("regions", "%" + customerResourcePoolEntity.getRegions() + "%");
		}
		//联系人姓名
		if (!StringUtil.isEmpty(customerResourcePoolEntity.getContactName())) {
			params.put("contactName", "%" + customerResourcePoolEntity.getContactName() +"%");
		}
		//联系人手机
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getCellphone())){
			params.put("cellphone", customerResourcePoolEntity.getCellphone());
		}
		//联系人地址
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getAddress())){
			params.put("address","%" + customerResourcePoolEntity.getAddress()+ "%");
		}
		//流入时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		// 开始
		if (customerResourcePoolVo.getStartDate() != null) {
			params.put("startDate",sdf.format(customerResourcePoolVo.getStartDate()));
		}
		// 结束
		if (customerResourcePoolVo.getEndDate() != null) {
			params.put("endDate", sdf.format(BseConstants.getEndDate(customerResourcePoolVo.getEndDate())));
		}
		//当前用户
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		//功能id集合
		Set<String> functions  = currentUser.getFunctionCodes();
		//数据权限
		if(!functions.contains(BseConstants.CUSTOMERRESOURCEPOOL_ALLDATA)){
			String deptCode = currentUser.getEmpEntity().getDeptEntity().getDeptCode();
			//查询大区编码
			String regionsCode = customerResourcePoolMapper.getRegionsCode(deptCode);
			if(StringUtil.isEmpty(regionsCode)){
				params.put("deptCode", deptCode);
			}else{
				params.put("deptCode", regionsCode);
			}
		}
		return customerResourcePoolMapper.getCustomerResourcePools(params, rb);
	}

	@Override
	public long countCustomerResourcePool(CustomerResourcePoolVo customerResourcePoolVo,UserEntity currentUser) {
		if(customerResourcePoolVo == null || customerResourcePoolVo.getCustomerResourcePoolEntity() == null){
			throw new CustomerResourcePoolException(CustomerResourcePoolException.PARAM_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		CustomerResourcePoolEntity customerResourcePoolEntity = customerResourcePoolVo.getCustomerResourcePoolEntity();
		//客户企业名称
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getEnterpriseName())){
			params.put("enterpriseName", "%" + customerResourcePoolEntity.getEnterpriseName() + "%");
		}
		//客户性质
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getAccountType())){
			params.put("accountType",customerResourcePoolEntity.getAccountType());
		}
		//所属行业
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getIndustryCode())){
			params.put("industryCode", customerResourcePoolEntity.getIndustryCode());
		}
		//所属大区
		if (!StringUtil.isEmpty(customerResourcePoolEntity.getRegions())) {
			params.put("regions", "%" + customerResourcePoolEntity.getRegions() + "%");
		}
		//联系人姓名
		if (!StringUtil.isEmpty(customerResourcePoolEntity.getContactName())) {
			params.put("contactName", "%" + customerResourcePoolEntity.getContactName() +"%");
		}
		//联系人手机
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getCellphone())){
			params.put("cellphone", customerResourcePoolEntity.getCellphone());
		}
		//联系人地址
		if(!StringUtil.isEmpty(customerResourcePoolEntity.getAddress())){
			params.put("address", "%" + customerResourcePoolEntity.getAddress()+ "%");
		}
		//流入时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		// 开始
		if (customerResourcePoolVo.getStartDate() != null) {
			params.put("startDate",sdf.format(customerResourcePoolVo.getStartDate()));
		}
		// 结束
		if (customerResourcePoolVo.getEndDate() != null) {
			params.put("endDate", sdf.format(BseConstants.getEndDate(customerResourcePoolVo.getEndDate())));
		}
		//功能id集合
		Set<String> functions  = currentUser.getFunctionCodes();
		//数据权限
		if(!functions.contains(BseConstants.CUSTOMERRESOURCEPOOL_ALLDATA)){
			String deptCode = currentUser.getEmpEntity().getDeptEntity().getDeptCode();
			//查询大区编码
			String regionsCode = customerResourcePoolMapper.getRegionsCode(deptCode);
			if(StringUtil.isEmpty(regionsCode)){
				params.put("deptCode", deptCode);
			}else{
				params.put("deptCode", regionsCode);
			}
		}
		return customerResourcePoolMapper.countCustomerResourcePool(params);
	}

	@Override
	public CustomerResourcePoolEntity queryCustomerResourcePoolById(String id) {
		if(StringUtil.isEmpty(id)){
			throw new CustomerResourcePoolException(CustomerResourcePoolException.PID_NULL);
		}
		CustomerResourcePoolEntity customerResourcePoolEntity = customerResourcePoolMapper.getCustomerResourcePoolById(id);
		if(customerResourcePoolEntity == null ){
			throw new CustomerResourcePoolException(CustomerResourcePoolException.CUSTOMER_NULL);
		}
		return customerResourcePoolEntity;
	}

	@Override
	@Transactional
	public long claimCustomerResourcePool(List<String> ids,UserEntity currentUser) {
		if(ids == null){
			throw new CustomerResourcePoolException(CustomerResourcePoolException.PARAM_NULL);
		}
		Map<String,Object> params = new HashMap<String,Object>();
		//当前用户
		//UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		String claimPerson = currentUser.getUserName();
		//参数
		params.put( "claimPerson", claimPerson);
		params.put("claimNum", ids.size());
		//最大认领数
		long maxClaimNum = this.getMaxClaimNum();
		//判断当前用户今天是否产生了认领记录
		if(customerResourcePoolMapper.isClaimCurrent(claimPerson)==0){
			customerResourcePoolMapper.insertClaimRecord(params);
			//认领处理
			this.dealWithClaim(ids,currentUser);
			return maxClaimNum-ids.size();
		}else{
			//查询当前用户当天已认领个数
			long claimNum = customerResourcePoolMapper.getClaimNum(claimPerson);
			if(claimNum == maxClaimNum){
				return -1;
			}else{
				if(ids.size() + claimNum > maxClaimNum){
					return maxClaimNum  - claimNum;
				}else{
					//认领处理
					this.dealWithClaim(ids,currentUser);
					//更新认领数
					customerResourcePoolMapper.updateClaimRecord(params);
					return maxClaimNum - claimNum - ids.size();
				}
			}
		}
	}

	/**
	 * 最大认领数
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年10月20日
	 * @update 
	 */
	private long getMaxClaimNum(){
		DataDictionaryValueEntity dataDictionaryValueEntity = new DataDictionaryValueEntity();
		dataDictionaryValueEntity.setActive(BseConstants.YES);
		dataDictionaryValueEntity.setTermsCode("CUSTOMERRESOURCEPOOL_MAX_CLAIM_NUM");
		dataDictionaryValueEntity.setValueCode(BseConstants.CUSTOMERRESOURCEPOOL_MAX_CLAIM_NUM_VALUENAME);
		List<DataDictionaryValueEntity> list = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(dataDictionaryValueEntity);
		if(list == null || list.size() == 0){
			throw new DataDictionaryValueException(DataDictionaryValueException.EXIST);
		}
		return Long.parseLong(list.get(0).getValueName());
	}
	
	/**
	 * 认领处理
	 * 
	 * @param ids
	 * @param currentUser
	 * @author: 何斌
	 * @date: 2015年10月21日
	 * @update 
	 */
	private void dealWithClaim(List<String> ids,UserEntity currentUser){
		//CustomerService customerSerivce = new CustomerService();
		//参数
		Map<String,String> params = new HashMap<String,String>();
		
		EmployeeEntity employeeEntity = currentUser.getEmpEntity();
		
		//负责人归属
		if(employeeEntity != null){
			String jobName = currentUser.getEmpEntity().getJobname();
			if(BseConstants.MANAGENAME.equals(jobName) || BseConstants.TEAMMANNAME.equals(jobName)){
				params.put("manageEmpCode", employeeEntity.getEmpCode());
				params.put("managePerson", employeeEntity.getEmpName());
				//客户所属组织
				params.put("customerOfOrg", BseConstants.CUSTOMEROFORG_SALE);
			}else{
				//所属门店
				DepartmentEntity tierDepartmentEntity = new DepartmentEntity();
				tierDepartmentEntity.setDeptCode(employeeEntity.getDeptEntity().getDeptCode());
				tierDepartmentEntity = iDepartmentService.queryDeptByDeptCode(tierDepartmentEntity);
				params.put("tierCode", tierDepartmentEntity.getLogistCode());
				params.put("manageEmpCode", tierDepartmentEntity.getManagerId());
				params.put("managePerson", tierDepartmentEntity.getLastName());
				//客户所属组织
				params.put("customerOfOrg", BseConstants.CUSTOMEROFORG_STOER);
			}
		}
		for (String id : ids) {
			//CustomerEntity entity = customerMapper.getCustomerInfoById(id);
			//校验客户池的名称,手机号信息是否和客户列表有一致
			//customerSerivce.isAllowCustomer(entity, 1);
			//主键id
			params.put("id", id);
			customerResourcePoolMapper.dealWithClaim(params);
		}
	}
}
