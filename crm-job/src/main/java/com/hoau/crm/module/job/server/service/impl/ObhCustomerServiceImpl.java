package com.hoau.crm.module.job.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.EmployeeException;
import com.hoau.crm.module.common.server.util.ObhWebserviceUtil;
import com.hoau.crm.module.common.server.wsdl.obh.ISyncFacadeService;
import com.hoau.crm.module.common.server.wsdl.obh.ResResult;
import com.hoau.crm.module.config.server.util.LogUtil;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.server.IPersonalCustomerService;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerEntity;
import com.hoau.crm.module.customer.server.dao.CustomerMapper;
import com.hoau.crm.module.job.server.service.IObhCustomerService;
import com.hoau.crm.module.job.shared.domain.PersonalCustomerBean;
import com.hoau.crm.module.job.shared.domain.PersonalShipperBean;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.JsonUtils;

/**
 * 个人客户数据同步SERVICE
 * 
 * @author: 何斌
 * @create: 2015年12月21日 上午10:01:06
 */
@org.springframework.stereotype.Service
public class ObhCustomerServiceImpl implements IObhCustomerService {
	
	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(ObhCustomerServiceImpl.class);
	
	@Resource
	private IPersonalCustomerService personalCustomerService;
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private CustomerMapper customerMapper;
	
	@Resource
	private IEmployeeService employeeService;
	
	@Override
	public void customerDataSync() {
		try {
			LOG.info("接口调用开始:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ISyncFacadeService port = ObhWebserviceUtil.PORT;
			//查询需要同步的客户数据
			this.getCustomerData(port);
			//查询需要同步的联系人数据
			this.getShipperData(port);
			LOG.info("接口调用结束:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("接口调用失败:" +LogUtil.logFormat(e));
		}
	}
	
	/**
	 * 客户数据
	 * @param port
	 * @author: 何斌
	 * @date: 2015年12月21日
	 * @update 
	 */
	private void getCustomerData(ISyncFacadeService port){
		LOG.info("客户数据同步开始:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ResResult result = port.queryNotSyncInfos(BseConstants.DATASYNC_TYPE_OBH_CUSTOMER);
		if(!"1000".equals(result.getStatusCode())){
			LOG.info("同步异常："+ result.getStatusMessage());
		}else{
			String dateStr = result.getResult();
			List<PersonalCustomerBean> customers = JsonUtils.toList(dateStr, PersonalCustomerBean.class);
			if(customers!=null && customers.size() > 0){
				List<Integer> ids = new ArrayList<Integer>();
				//保存
				for(PersonalCustomerBean personalCustomerBean :customers){
					try {
						if(BseConstants.YES.equals(personalCustomerBean.getIsCrmCustomer())){
							//已经是CRM客户,修改其对应字段
							CustomerEntity customerEntity = new CustomerEntity();
							customerEntity.setId(personalCustomerBean.getCrmguid());
							customerEntity = customerService.queryCustomerInfoById(customerEntity);
							customerEntity.setEnterpriseName(personalCustomerBean.getEnterpriseName());
							customerEntity.setDetailedAddress(personalCustomerBean.getDetailedAddress());
							ContactEntity contactEntity = new ContactEntity();
							contactEntity.setContactName(personalCustomerBean.getContactName());
							contactEntity.setCellphone(personalCustomerBean.getCellphone());
							contactEntity.setTelephone(personalCustomerBean.getTelephone());
							contactEntity.seteMailAddress(personalCustomerBean.getEmail());
							customerEntity.setContactEntity(contactEntity);
							customerMapper.updateCustomerInfo(customerEntity);
						}else{
							//判断是否存在
							if(personalCustomerService.isExistCustomerBySourceId(personalCustomerBean.getUserId())){
								//更新个人列表
								personalCustomerService.updatePersonalCustomerInfo(this.getPersonalCustomerEntity(personalCustomerBean));
							}else{
								PersonalCustomerEntity personalCustomerEntity = this.getPersonalCustomerEntity(personalCustomerBean);
								personalCustomerEntity.setId(UUIDUtil.getUUID());
								personalCustomerService.addPersonalCustomer(personalCustomerEntity);
							}
						}
						ids.add(personalCustomerBean.getUserId());
					} catch (Exception e) {
						continue;
					}
				}
				//返回状态
				if(ids != null && ids.size() > 0){
					this.updateSyncStatus(JsonUtils.toJson(ids), BseConstants.DATASYNC_TYPE_OBH_CUSTOMER, port);
				}
			}else{
				LOG.info("客户数据同步接口返回空");
			}
		}
		LOG.info("客户数据同步结束:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	private PersonalCustomerEntity getPersonalCustomerEntity(PersonalCustomerBean personalCustomerBean) {
		PersonalCustomerEntity personalCustomerEntity = new PersonalCustomerEntity();
		personalCustomerEntity.setSource(BseConstants.PERSONALCUSTOMER_SOURCE_OBH);
		personalCustomerEntity.setSourceId(personalCustomerBean.getUserId());
		personalCustomerEntity.setUserName(personalCustomerBean.getUserName());
		personalCustomerEntity.setEnterpriseName(personalCustomerBean.getEnterpriseName());
		personalCustomerEntity.setDetailedAddress(personalCustomerBean.getDetailedAddress());
		personalCustomerEntity.setContactName(personalCustomerBean.getContactName());
		personalCustomerEntity.setCellphone(personalCustomerBean.getCellphone());
		personalCustomerEntity.setTelephone(personalCustomerBean.getTelephone());
		personalCustomerEntity.setEmail(personalCustomerBean.getEmail());
		return personalCustomerEntity;
	}

	/**
	 * 发货人信息-联系人信息
	 * 
	 * @param port
	 * @author: 何斌
	 * @date: 2015年12月21日
	 * @update 
	 */
	private void getShipperData(ISyncFacadeService port){
		LOG.info("联系人数据同步开始:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ResResult result = port.queryNotSyncInfos(BseConstants.DATASYNC_TYPE_OBH_SHIPPER);
		if(!"1000".equals(result.getStatusCode())){
			LOG.info("同步异常："+ result.getStatusMessage());
		}else{
			String dateStr = result.getResult();
			List<PersonalShipperBean> shippers = JsonUtils.toList(dateStr, PersonalShipperBean.class);
			if(shippers!=null && shippers.size() > 0){
				List<Integer> ids = new ArrayList<Integer>();
				//保存
				for(PersonalShipperBean shipperEntity : shippers){
					try {
						if(BseConstants.YES.equals(shipperEntity.getIsCrmCustomer())){
							CustomerEntity customerEntity = new CustomerEntity();
							customerEntity.setId(shipperEntity.getCrmGuid());
							customerEntity = customerService.queryCustomerInfoById(customerEntity);
							EmployeeEntity employeeEntity = new EmployeeEntity();
							employeeEntity.setEmpCode(customerEntity.getManageEmpCode());
							employeeEntity = employeeService.queryEmployeeByEmpcode(employeeEntity);
							UserEntity currUser = new UserEntity();
							if(employeeEntity == null){
								throw new EmployeeException(EmployeeException.USER_NULL);
							}
							currUser.setUserName(employeeEntity.getAccount());
							currUser.setEmpEntity(employeeEntity);
							//直接新增联系人
							ContactEntity contactEntity = new ContactEntity();
							//更新联系人
							if(BseConstants.YES.equals(shipperEntity.getIsCrmCustomerContact())){
								contactEntity.setId(shipperEntity.getCrmContactGuid());
								contactEntity.setAccountId(shipperEntity.getCrmGuid());
								contactEntity.setContactName(shipperEntity.getContactName());
								contactEntity.setCellphone(shipperEntity.getCellphone());
								contactEntity.setTelephone(shipperEntity.getTelephone());
								contactEntity.setDistrictNumber(shipperEntity.getDistrictNumber());
								customerService.updateContactInfo(contactEntity, currUser);
							}else{
								contactEntity.setAccountId(shipperEntity.getCrmGuid());
								contactEntity.setContactName(shipperEntity.getContactName());
								contactEntity.setCellphone(shipperEntity.getCellphone());
								contactEntity.setTelephone(shipperEntity.getTelephone());
								contactEntity.setDistrictNumber(shipperEntity.getDistrictNumber());
								contactEntity.setSource(BseConstants.YES);
								String contactId = customerService.addContactInfo(contactEntity, currUser);
								shipperEntity.setCrmContactGuid(contactId);
								ISyncFacadeService service = ObhWebserviceUtil.PORT;
								service.updateShipperInfo(JsonUtils.toJson(shipperEntity));
							}
						}else{
							//判断是否存在
							if(personalCustomerService.isExistContactBySourceId(shipperEntity.getShipperId())){
								//更新个人列表
								personalCustomerService.updatePersonalCustomerContact(this.getPersonalCustomerContactEntity(shipperEntity));
							}else{
								PersonalCustomerContactEntity personalCustomerContactEntity = this.getPersonalCustomerContactEntity(shipperEntity);
								personalCustomerContactEntity.setId(UUIDUtil.getUUID());
								personalCustomerService.addPersonalCustoemrContact(personalCustomerContactEntity);
							}
						}
						ids.add(shipperEntity.getShipperId());
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
				//返回状态
				if(ids != null && ids.size() > 0){
					this.updateSyncStatus(JsonUtils.toJson(ids), BseConstants.DATASYNC_TYPE_OBH_SHIPPER, port);
				}
			}else{
				LOG.info("联系人数据同步接口返回空");
			}
		}
		LOG.info("联系人数据同步结束:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	private PersonalCustomerContactEntity getPersonalCustomerContactEntity(PersonalShipperBean shipperEntity) {
		PersonalCustomerContactEntity personalCustomerContactEntity = new PersonalCustomerContactEntity();
		personalCustomerContactEntity.setSourceId(shipperEntity.getShipperId());
		personalCustomerContactEntity.setUserId(shipperEntity.getUserId());
		personalCustomerContactEntity.setContactName(shipperEntity.getContactName());
		personalCustomerContactEntity.setCellphone(shipperEntity.getCellphone());
		personalCustomerContactEntity.setTelephone(shipperEntity.getTelephone());
		personalCustomerContactEntity.setDistrictNumber(shipperEntity.getDistrictNumber());
		personalCustomerContactEntity.setTierCode(shipperEntity.getTierCode());
		return personalCustomerContactEntity;
	}
	
	/**
	 * 更新同步状态给官网
	 * 
	 * @param ids
	 * @param type
	 * @param port
	 * @author: 何斌
	 * @date: 2015年12月21日
	 * @update 
	 */
	private void updateSyncStatus(String ids,String type,ISyncFacadeService port){
		LOG.info("返回接口状态调用开始:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		ResResult result = port.updateSyncSuccessInfo(ids, type);
		if(!"1000".equals(result.getStatusCode())){
			LOG.info("返回接口状态调用失败:" + result.getStatusMessage());
		}else{
			LOG.info("返回接口状态调用成功:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		LOG.info("返回接口状态调用结束:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
}
