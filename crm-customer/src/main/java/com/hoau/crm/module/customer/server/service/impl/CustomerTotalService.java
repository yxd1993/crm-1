package com.hoau.crm.module.customer.server.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.hoau.crm.module.bse.api.server.service.IUserService;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.ICustomerInfoPoolService;
import com.hoau.crm.module.customer.api.server.ICustomerResourcePoolService;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.server.ICustomerTotalService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerTotalEntity;
import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;
import com.hoau.crm.module.customer.api.shared.vo.CustomerResourcePoolVo;
import com.hoau.crm.module.customer.api.shared.vo.CustomerVo;
import com.hoau.crm.module.customer.server.dao.CustomerTotalMapper;
import com.hoau.crm.module.util.UUIDUtil;

/**
 * 客户总数统计service
 * 
 * @author: 何斌
 * @create: 2015年12月29日 下午3:30:34
 */
@org.springframework.stereotype.Service
public class CustomerTotalService implements ICustomerTotalService{

	@Resource
	private CustomerTotalMapper customerTotalMapper;
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private ICustomerResourcePoolService customerResourcePoolService;
	
	@Resource
	private ICustomerInfoPoolService customerInfoPoolService;
	
	@Resource
	private IUserService userService;
	
	@Override
	public List<String> queryAllUserCode() {
		return customerTotalMapper.queryAllUserCode();
	}

	@Override
	public CustomerTotalEntity queryCustomerTotalByUserCode(String userCode) {
		return customerTotalMapper.queryCustomerTotalByUserCode(userCode);
	}

	@Override
	public void addCustomerTotal(CustomerTotalEntity customerTotalEntity) {
		customerTotalMapper.addCustomerTotal(customerTotalEntity);
	}

	@Override
	public void updateCustomerTotal(CustomerTotalEntity customerTotalEntity) {
		customerTotalMapper.updateCustomerTotal(customerTotalEntity);
	}

	@Override
	public boolean isExistUserCode(String userCode) {
		if(customerTotalMapper.isExistUserCode(userCode) > 0){
			return true;
		}
		return false;
	}

	@Override
	public void execJob() {
		//查询所有有角色的用户
		List<String> userCodes = this.queryAllUserCode();
		for(String userCode : userCodes){
			try {
				UserEntity currentUser  = userService.queryUserByUserName(userCode);
				if(currentUser != null){
					CustomerTotalEntity customerTotalEntity = new CustomerTotalEntity();
					customerTotalEntity.setId(UUIDUtil.getUUID());
					customerTotalEntity.setUserCode(userCode);
					//查询客户列表总数
					CustomerVo customerVo = new CustomerVo();
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					cal.add(Calendar.DATE, -1);
					customerVo.setEndDate(cal.getTime());
					customerTotalEntity.setCustomerTotal(customerService.countCustomer(customerVo,currentUser));
					//查询客户池总数
					CustomerResourcePoolVo customerResourcePoolVo = new CustomerResourcePoolVo();
					CustomerResourcePoolEntity customerResourcePoolEntity = new CustomerResourcePoolEntity();
					customerResourcePoolVo.setEndDate(cal.getTime());
					customerResourcePoolVo.setCustomerResourcePoolEntity(customerResourcePoolEntity);
					customerTotalEntity.setCustomerResourceTotal(
							customerResourcePoolService.countCustomerResourcePool(customerResourcePoolVo,currentUser));
					//查询资源客户总数
					CustomerInfoPoolVo customerInfoPoolVo = new CustomerInfoPoolVo();
					customerInfoPoolVo.setEndDate(cal.getTime());
					customerTotalEntity.setCustomerPoolTotal(customerInfoPoolService.countUploadCustomer(customerInfoPoolVo, currentUser));
					if(this.isExistUserCode(userCode)){
						this.updateCustomerTotal(customerTotalEntity);
					}else{
						this.addCustomerTotal(customerTotalEntity);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
