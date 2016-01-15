package com.hoau.crm.module.customer.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.server.util.PingYinUtil;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.EmployeeException;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.common.server.util.LatitudeUtils;
import com.hoau.crm.module.common.server.util.MobileUtil;
import com.hoau.crm.module.common.server.util.ObhWebserviceUtil;
import com.hoau.crm.module.common.server.util.SMSPlatformUtil;
import com.hoau.crm.module.common.server.wsdl.obh.ISyncFacadeService;
import com.hoau.crm.module.common.server.wsdl.obh.RegisterUserResModel;
import com.hoau.crm.module.common.server.wsdl.obh.ResResult;
import com.hoau.crm.module.common.shared.domain.SmsEntity;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerNatureConvertEntity;
import com.hoau.crm.module.customer.api.shared.domain.ObhUserEntity;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.RegisterEntity;
import com.hoau.crm.module.customer.api.shared.domain.ShipperEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.customer.api.shared.vo.CustomerVo;
import com.hoau.crm.module.customer.server.dao.CustomerInfoPoolMapper;
import com.hoau.crm.module.customer.server.dao.CustomerLatlngMapper;
import com.hoau.crm.module.customer.server.dao.CustomerMapper;
import com.hoau.crm.module.customer.server.dao.PersonalCustomerMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.JsonUtils;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 客户信息SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-5-20
 */
/**
 * @author: 何斌
 * @create: 2015年6月3日 下午5:17:28
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerService implements ICustomerService {
	private static Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Resource
	CustomerMapper customerMapper;
	
	@Resource
	private IDepartmentService iDepartmentService;
	
	@Resource
	private SequenceNoService sequenceNoService;
	
	@Resource
	private CustomerSyncService customerSyncService;
	
	@Resource
	private IReviewHistoryService reviewHistoryService;
	
	@Resource
	private CustomerLatlngMapper customerLatlngMapper;
	
	@Resource
	private CustomerInfoPoolMapper customerInfoPoolMapper;
	
	@Resource
	private PersonalCustomerMapper personalCustomerMapper;
	
	@Resource
	private IEmployeeService employeeService;

	@Override
	public List<CustomerEntity> queryCustomerInfo(
			CustomerVo customerVo, RowBounds rb) {
		if (rb == null) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		// 判断查询条件，模糊查询的进行处理
		if(customerVo != null){
			if (customerVo.getCustomerEntity() != null) {
				// 客户企业全称
				if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getEnterpriseName())) {
					params.put("enterpriseName",
							"%" + customerVo.getCustomerEntity().getEnterpriseName() + "%");
				}
				/*// 主联系人姓名
				if (customerVo.getCustomerEntity().getContactEntity() != null) {
					if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity()
							.getContactName())) {
						params.put("contactName", "%"
								+ customerVo.getCustomerEntity().getContactEntity()
										.getContactName() + "%");
					}
				}*/
				//DC账号
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getDcAccount())){
					params.put("dcAccount",customerVo.getCustomerEntity().getDcAccount());
				}
				// 所属行业
				if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getIndustryCode())) {
					params.put("industryCode", customerVo.getCustomerEntity().getIndustryCode());
				}
				// 客户性质
				if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getAccountType())) {
					params.put("accountType", customerVo.getCustomerEntity().getAccountType());
				}
				// 手机
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity().getCellphone())){
					params.put("cellphone",customerVo.getCustomerEntity().getContactEntity().getCellphone());
				}
				/*// 区号
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity().getDistrictNumber())){
					params.put("districtNumber", "%" + customerVo.getCustomerEntity().getContactEntity().getDistrictNumber() + "%");
				}*/
				// 负责人
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getManagePerson())){
					params.put("managePerson", customerVo.getCustomerEntity().getManagePerson());
				}
				// 大区名称
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getRegionsName())){
					params.put("regionsName", "%" + customerVo.getCustomerEntity().getRegionsName() + "%");
				}
				/*// 座机
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity().getTelephone())){
					params.put("telephone", "%" + customerVo.getCustomerEntity().getContactEntity().getTelephone() + "%");
				}*/
				/*// 详细地址
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getDetailedAddress())){
					params.put("detailedAddress", "%" + customerVo.getCustomerEntity().getDetailedAddress() + "%");
				}*/
				// 门店代码
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getTierCode())){
					params.put("tierCode", customerVo.getCustomerEntity().getTierCode());
				}
			}
			//当前用户
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			//当前用户拥有的功能信息ID集合
			Set<String> functions = currentUser.getFunctionCodes();
			if(!StringUtil.isEmpty(currentUser.getUserName())){
				params.put("createdBy", currentUser.getUserName());
			}
			//所有数据权限
			if(!functions.contains(BseConstants.ALLDATA_CUSTOMERMANAGER)){
				//是否有新门店研究组数据权限
				if(functions.contains(BseConstants.FUNCTION_AUTH_NEWSTOREDATA)){
					params.put("newStoreData", BseConstants.MANAGENAME);
				}else if(functions.contains(BseConstants.FUNCTION_AUTH_TACTICCUSTOMER)){
					//是否有战略客户部数据权限
					if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
						params.put("tacticCustomerDeptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					}
				}else if(functions.contains(BseConstants.FUNCTION_AUTH_CUSTOMERMANAGE)){
					//是否有客户管理部数据权限
					if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
						params.put("customerManage",BseConstants.YES);
					}
				}else if(functions.contains(BseConstants.ALLDATA_SUBCOMPANYCUSTOMERMANAGER)){
					//普通权限
					DepartmentEntity currDepartmentEntity = currentUser.getEmpEntity().getDeptEntity();
					if(!StringUtil.isEmpty(currDepartmentEntity.getDeptCode())){
						Map<String,String> map = customerMapper.getIsStoreInfo(currDepartmentEntity.getDeptCode());
						//是门店
						if(BseConstants.IS_STORE.equals(map.get("isStore"))){
							params.put("storeAuth", map.get("logistCode"));
						}else if(customerMapper.isRoadByDeptCode(currDepartmentEntity.getDeptCode())>0){
							params.put("roadDeptcode", currDepartmentEntity.getDeptCode());
						}else{
							params.put("userDeptcode", currDepartmentEntity.getDeptCode());
						}
					}
				}else{
					//客户经理数据权限
					if(!StringUtil.isEmpty(currentUser.getEmpEntity().getEmpCode())){
						params.put("salePerson", currentUser.getEmpEntity().getEmpCode());
					}
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			// 开始时间
			if (customerVo.getStartDate() != null) {
				params.put("startDate",
						sdf.format(customerVo.getStartDate()));
			}
			// 结束时间
			if (customerVo.getEndDate() != null) {
				params.put("endDate",
						"" + sdf.format(BseConstants.getEndDate(customerVo.getEndDate())));
			}
			//判断是否是预约和洽谈的客户选择
			if (!StringUtil.isEmpty(customerVo.getSelectorParam())) {
				params.put("selectorParam", "%" + customerVo.getSelectorParam()
						+ "%");
			}
		}
		return customerMapper.getCustomerInfo(params, rb);
	}

	@Override
	public long countCustomer(CustomerVo customerVo,UserEntity currUser) {
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		// 判断查询条件，模糊查询的进行处理
		if(customerVo != null){
			if (customerVo.getCustomerEntity() != null) {
				// 客户企业全称
				if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getEnterpriseName())) {
					params.put("enterpriseName",
							"%" + customerVo.getCustomerEntity().getEnterpriseName() + "%");
				}
				/*// 主联系人姓名
				if (customerVo.getCustomerEntity().getContactEntity() != null) {
					if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity()
							.getContactName())) {
						params.put("contactName", "%"
								+ customerVo.getCustomerEntity().getContactEntity()
										.getContactName() + "%");
					}
				}*/
				//DC账号
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getDcAccount())){
					params.put("dcAccount", customerVo.getCustomerEntity().getDcAccount());
				}
				// 所属行业
				if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getIndustryCode())) {
					params.put("industryCode", customerVo.getCustomerEntity().getIndustryCode());
				}
				// 客户性质
				if (!StringUtil.isEmpty(customerVo.getCustomerEntity().getAccountType())) {
					params.put("accountType", customerVo.getCustomerEntity().getAccountType());
				}
				// 手机
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity().getCellphone())){
					params.put("cellphone", customerVo.getCustomerEntity().getContactEntity().getCellphone());
				}
				/*// 区号
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity().getDistrictNumber())){
					params.put("districtNumber", "%" + customerVo.getCustomerEntity().getContactEntity().getDistrictNumber() + "%");
				}*/
				// 负责人
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getManagePerson())){
					params.put("managePerson", customerVo.getCustomerEntity().getManagePerson());
				}
				// 大区名称
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getRegionsName())){
					params.put("regionsName", "%" + customerVo.getCustomerEntity().getRegionsName() + "%");
				}
				/*// 座机
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getContactEntity().getTelephone())){
					params.put("telephone", "%" + customerVo.getCustomerEntity().getContactEntity().getTelephone() + "%");
				}*/
				// 详细地址
				/*if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getDetailedAddress())){
					params.put("detailedAddress", "%" + customerVo.getCustomerEntity().getDetailedAddress() + "%");
				}*/
				// 门店代码
				if(!StringUtil.isEmpty(customerVo.getCustomerEntity().getTierCode())){
					params.put("tierCode", customerVo.getCustomerEntity().getTierCode());
				}
			}
			Set<String> functions = currUser.getFunctionCodes();
			if(!StringUtil.isEmpty(currUser.getUserName())){
				params.put("createdBy", currUser.getUserName());
			}
			//所有数据权限
			if(!functions.contains(BseConstants.ALLDATA_CUSTOMERMANAGER)){
				//是否有新门店研究组数据权限
				if(functions.contains(BseConstants.FUNCTION_AUTH_NEWSTOREDATA)){
					params.put("newStoreData", BseConstants.MANAGENAME);
				}else if(functions.contains(BseConstants.FUNCTION_AUTH_TACTICCUSTOMER)){
					//是否有战略客户部数据权限
					if(!StringUtil.isEmpty(currUser.getEmpEntity().getDeptEntity().getDeptCode())){
						params.put("tacticCustomerDeptCode", currUser.getEmpEntity().getDeptEntity().getDeptCode());
					}
				}else if(functions.contains(BseConstants.FUNCTION_AUTH_CUSTOMERMANAGE)){
					//是否有客户管理部数据权限
					if(!StringUtil.isEmpty(currUser.getEmpEntity().getDeptEntity().getDeptCode())){
						params.put("customerManage",BseConstants.YES);
					}
				}else if(functions.contains(BseConstants.ALLDATA_SUBCOMPANYCUSTOMERMANAGER)){
					//普通权限
					DepartmentEntity currDepartmentEntity = currUser.getEmpEntity().getDeptEntity();
					if(!StringUtil.isEmpty(currDepartmentEntity.getDeptCode())){
						Map<String,String> map = customerMapper.getIsStoreInfo(currDepartmentEntity.getDeptCode());
						//是门店
						if(BseConstants.IS_STORE.equals(map.get("isStore"))){
							params.put("storeAuth", map.get("logistCode"));
						}else if(customerMapper.isRoadByDeptCode(currDepartmentEntity.getDeptCode())>0){
							params.put("roadDeptcode", currDepartmentEntity.getDeptCode());
						}else{
							params.put("userDeptcode", currDepartmentEntity.getDeptCode());
						}
					}
				}else{
					//客户经理数据权限
					if(!StringUtil.isEmpty(currUser.getEmpEntity().getEmpCode())){
						params.put("salePerson", currUser.getEmpEntity().getEmpCode());
					}
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			// 开始时间
			if (customerVo.getStartDate() != null) {
				params.put("startDate",sdf.format(customerVo.getStartDate()));
			}
			// 结束时间
			if (customerVo.getEndDate() != null) {
				params.put("endDate","" + sdf.format(BseConstants.getEndDate(customerVo.getEndDate())));
			}
			//判断是否是预约和洽谈的客户选择
			if (!StringUtil.isEmpty(customerVo.getSelectorParam())) {
				params.put("selectorParam", "%" + customerVo.getSelectorParam()+ "%");
			}
		}
		return customerMapper.countCustomer(params);
	}

	@Override
	public List<CustomerEntity> queryCustomerInfoByCombo(CustomerVo customerVo,
			RowBounds rb) {
		if (rb == null) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		if (customerVo != null
				&& !StringUtil.isEmpty(customerVo.getSelectorParam())) {
			params.put("selectorParam", "%" + customerVo.getSelectorParam()
					+ "%");
		}
		return customerMapper.getCustomerInfoByCombo(params, rb);
	}

	@Override
	public long countCustomerByCombo(CustomerVo customerVo) {
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		if (customerVo != null
				&& !StringUtil.isEmpty(customerVo.getSelectorParam())) {
			params.put("selectorParam", "%" + customerVo.getSelectorParam()
					+ "%");
		}
		return customerMapper.countCustomerByCombo(params);
	}

	@Override
	@Transactional
	public void addCustomer(CustomerEntity customerEntity, UserEntity currUser,String appVersion,String appType) {
		if (customerEntity == null) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		/*if (customerEntity.getContactEntity() == null) {
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}*/
		// 判断是否重复
		/*if(!this.isAllowCustomer(customerEntity)){
			throw new CustomerException(CustomerException.CUSTOMERINF_REPEAT);
		}*/
		// 判断是新增还是修改
		if (StringUtil.isEmpty(customerEntity.getId())) {
			this.isAllowCustomer(customerEntity, 1);
			// 主键赋值
			String uuid = UUIDUtil.getUUID();
			customerEntity.setId(uuid);
			customerEntity.getContactEntity().setAccountId(uuid);
			customerEntity.getContactEntity().setId(UUIDUtil.getUUID());
			customerEntity.getContactEntity().setIsDefault(BseConstants.ACTIVE);
			//CRM账号赋值
			String crmNo = sequenceNoService.getSeqNo(BseConstants.SEQ_TYPE);
			customerEntity.setAccountCode(crmNo);
			//企业名称缩写
			customerEntity.setEnterpriseShortName(PingYinUtil.getFirstSpell(customerEntity.getEnterpriseName()));
			
			//折扣率默认值设置
			customerEntity.setDiscountRate(BseConstants.DISCOUNT_RATE);
			
			//负责人归属
			EmployeeEntity employeeEntity = new EmployeeEntity();
			employeeEntity.setEmpCode(customerEntity.getManageEmpCode());
			employeeEntity = employeeService.queryEmployeeByEmpcode(employeeEntity);
			if(employeeEntity != null){
				String jobName = employeeEntity.getJobname();
				if(BseConstants.MANAGENAME.equals(jobName) || BseConstants.TEAMMANNAME.equals(jobName)){
					//负责人
					customerEntity.setManageEmpCode(employeeEntity.getEmpCode());
					customerEntity.setManagePerson(employeeEntity.getEmpName());
					//客户所属组织
					customerEntity.setCustomerOfOrg(BseConstants.CUSTOMEROFORG_SALE);
				}else{
					//门店负责人
					DepartmentEntity tierDepartmentEntity = new DepartmentEntity();
					tierDepartmentEntity.setLogistCode(customerEntity.getTierCode());
					tierDepartmentEntity = iDepartmentService.queryDeptByDeptCode(tierDepartmentEntity);
					//负责人不为空的当前部门
					DepartmentEntity departmentEntity = this.queryManagerEmpInfo(tierDepartmentEntity.getDeptCode());
					customerEntity.setManageEmpCode(departmentEntity.getManagerId());
					customerEntity.setManagePerson(departmentEntity.getLastName());
					//客户所属组织
					customerEntity.setCustomerOfOrg(BseConstants.CUSTOMEROFORG_STOER);
				}
			}
			
			// 用户信息
			customerEntity.setCreateUser(currUser.getUserName());
			customerEntity.getContactEntity().setCreateUser(currUser.getUserName());
			
			if(customerEntity.getObhUserId() != null ){
				customerEntity.setIsCreateObhAccount(BseConstants.YES);
				customerEntity.setObhAccount(customerEntity.getContactEntity().getCellphone());
			}else{
				customerEntity.setIsCreateObhAccount(BseConstants.NO);
				customerEntity.setObhUserId(null);
			}
			
			// 新增客户信息
			customerMapper.addCustomerInfo(customerEntity);
			
			//新增客户经纬度
			Map<String,String> map = LatitudeUtils.getLatitude(customerEntity.getDetailedAddress(),"");
			if(map != null && map.size() > 0){
				//新增完成之后给客户添加经纬度
				CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
				customerLatlngEntity.setId(UUIDUtil.getUUID());
				//代表客户共享里面添加
				customerLatlngEntity.setType("1");
				customerLatlngEntity.setCustomerId(uuid);
				//维度
				customerLatlngEntity.setLat(Double.parseDouble(map.get("lat")));
				//经度
				customerLatlngEntity.setLng(Double.parseDouble(map.get("lng")));
				//是否精准
				customerLatlngEntity.setPrecise(map.get("precise"));
				//可信度
				customerLatlngEntity.setConfidence(map.get("confidence"));
				customerLatlngMapper.addCustomerLatlngInfo(customerLatlngEntity);
			}
			
			//资源客户转为潜在修改状态
			if(null != customerEntity.getCustomerInfoPoolId() && !"".equals(customerEntity.getCustomerInfoPoolId())){
				Map<String, String> customerinfoMap = new HashMap<String, String>();
				customerinfoMap.put("customerId", customerEntity.getId());
				customerinfoMap.put("customerPoolID", customerEntity.getCustomerInfoPoolId());
				customerInfoPoolMapper.updateCustomeriSpotential(customerinfoMap);
			}
			//个人客户转为潜在客户修改状态
			if(customerEntity.getObhUserId() != null){
				//更新个人客户列表状态
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("customerId", customerEntity.getId());
				params.put("sourceId", customerEntity.getObhUserId());
                if (employeeEntity != null) {
                    params.put("managePersonCode", employeeEntity.getAccount());
                    params.put("managePerson", employeeEntity.getEmpName());
                }
				personalCustomerMapper.udpatePersonalCustomerStatus(params);
				//更新网厅
				ISyncFacadeService port = ObhWebserviceUtil.PORT;
				ObhUserEntity userEntity = new ObhUserEntity();
				userEntity.setUserId(customerEntity.getObhUserId());
				userEntity.setCrmguid(customerEntity.getId());
				ResResult result =  port.updateUserInfo(JsonUtils.toJson(userEntity));
				if(!"1000".equals(result.getStatusCode())){
					LOG.info(result.getStatusMessage());
					throw new CustomerException(CustomerException.OBH_WEBSERVICE_FAIL);
				}
			}
			
			// 新增联系人信息
			customerMapper.addCustomerContactInfo(customerEntity.getContactEntity());
			//个人客户转为潜在客户新增联系人信息
			if(customerEntity.getObhUserId() != null){
				PersonalCustomerContactEntity personalCustomerContactEntity = new PersonalCustomerContactEntity();
				personalCustomerContactEntity.setUserId(customerEntity.getObhUserId());
				List<PersonalCustomerContactEntity> list =
						personalCustomerMapper.getPersonalCustomerContactInfo(personalCustomerContactEntity);
				for(PersonalCustomerContactEntity pcc : list){
					String contactId = UUIDUtil.getUUID();
					ContactEntity contactEntity = new ContactEntity();
					contactEntity.setId(contactId);
					contactEntity.setAccountId(customerEntity.getId());
					contactEntity.setContactName(pcc.getContactName());
					contactEntity.setCellphone(pcc.getCellphone());
					contactEntity.setTelephone(pcc.getTelephone());
					contactEntity.setDistrictNumber(pcc.getDistrictNumber());
					contactEntity.setIsDefault(BseConstants.NO);
					contactEntity.setCreateUser(currUser.getUserName());
					customerMapper.addCustomerContactInfo(contactEntity);
					//更新个人列表状态
					Map<String,Object> params = new HashMap<String, Object>();
					params.put("sourceId", pcc.getSourceId());
					params.put("customerContactId", contactId);
					personalCustomerMapper.updetePersonalCustomerContactStatus(params);
					//网厅绑定联系人id
					ISyncFacadeService port = ObhWebserviceUtil.PORT;
					ShipperEntity shipperEntity = new ShipperEntity();
					shipperEntity.setShipperId(pcc.getSourceId());
					shipperEntity.setCrmContactGuid(contactId);
					ResResult result = port.updateShipperInfo(JsonUtils.toJson(shipperEntity));
					if(!"1000".equals(result.getStatusCode())){
						LOG.info(result.getStatusMessage());
						throw new CustomerException(CustomerException.OBH_WEBSERVICE_FAIL);
					}
				}
			}
			
			//新增 -客户性质  意向：3,触发调用DC接口。
			if(BseConstants.CUSTOMER_NATURE_INTENTION.equals(customerEntity.getAccountType())){
				//根据主键查询客户信息
				CustomerEntity customerInfo = customerMapper.getCustomerInfoById(customerEntity.getId());
				customerInfo.setDcAccount(customerSyncService.getDCAccount(customerInfo));
				customerInfo.setModifyUser(currUser.getUserName());
				customerMapper.updateCustomerInfo(customerInfo);
			}
			//记录新增潜在客户操作
			if(BseConstants.CUSTOMER_NATURE_POTENTIAL.equals(customerEntity.getAccountType())){
				ReviewHistoryEntity reviewhistoryEntity = 
						new ReviewHistoryEntity(customerEntity, null, null, null, null, BseConstants.OPER_CREATE_CUSTOMER_HIDDEN, currUser.getEmpEntity().getEmpCode());
				reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
			}
			//记录新增意向客户操作
			if(BseConstants.CUSTOMER_NATURE_INTENTION.equals(customerEntity.getAccountType())){
				ReviewHistoryEntity reviewhistoryEntity = 
						new ReviewHistoryEntity(customerEntity, null, null, null, null, BseConstants.OPER_CREATE_CUSTOMER_INTENT, currUser.getEmpEntity().getEmpCode());
				reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
			}
			//记录新增联系人操作
			ReviewHistoryEntity reviewhistoryEntity = 
					new ReviewHistoryEntity(null, customerEntity.getContactEntity(), null, null, null, BseConstants.OPER_CREATE_CONTACT, currUser.getEmpEntity().getEmpCode());
			reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
		} else {
			// 修改客户信息时，不验证联系人信息
			this.isAllowCustomer(customerEntity, 2);
			// 用户信息
			customerEntity.setModifyUser(currUser.getUserName());
			/*customerEntity.getContactEntity().setModifyUser(
					currUser.getUserName());*/
			//根据主键查询客户信息
			CustomerEntity customerInfo = customerMapper.getCustomerInfoById(customerEntity.getId());
			
			//门店修改
			if(BseConstants.CUSTOMEROFORG_STOER.equals(customerInfo.getCustomerOfOrg()) 
					&& !customerInfo.getTierCode().equals(customerEntity.getTierCode())){
				//门店负责人
				DepartmentEntity tierDepartmentEntity = new DepartmentEntity();
				tierDepartmentEntity.setLogistCode(customerEntity.getTierCode());
				tierDepartmentEntity = iDepartmentService.queryDeptByDeptCode(tierDepartmentEntity);
				//负责人不为空的当前部门
				DepartmentEntity departmentEntity = this.queryManagerEmpInfo(tierDepartmentEntity.getDeptCode());
				
				customerEntity.setManageEmpCode(departmentEntity.getManagerId());
				customerEntity.setManagePerson(departmentEntity.getLastName());
			}
			
			// 判断原来的数据是否已经删除
			if(!StringUtil.isEmpty(customerInfo.getActive()) && customerInfo.getActive().equals(BseConstants.INACTIVE)){
				throw new CustomerException(CustomerException.UPDATE_IS_DELETE);
			}
			// 判断是否修改客户状态为GAM
			if(!StringUtil.isEmpty(customerEntity.getAccountRatingCode())){
				// 原来为GAM，现在改成不是GAM。
				if(BseConstants.CUSTOMER_LEVEL_GAM.equals(customerInfo.getAccountRatingCode()) && !BseConstants.CUSTOMER_LEVEL_GAM.equals(customerEntity.getAccountRatingCode())){
					if(!currUser.getFunctionCodes().contains(BseConstants.FUNCTION_AUTH_RATING)){
						throw new CustomerException(CustomerException.UPDATE_CONTACT_NOT_UPDATERATINGAUTH);
					}
				}
				// 原来不是GAM，现在改成GAM
				if(!BseConstants.CUSTOMER_LEVEL_GAM.equals(customerInfo.getAccountRatingCode()) && BseConstants.CUSTOMER_LEVEL_GAM.equals(customerEntity.getAccountRatingCode())){
					if(!currUser.getFunctionCodes().contains(BseConstants.FUNCTION_AUTH_RATING)){
						throw new CustomerException(CustomerException.UPDATE_CONTACT_NOT_UPDATERATINGAUTH);
					}
				}
				// 现在是GAM
				if(BseConstants.CUSTOMER_LEVEL_GAM.equals(customerInfo.getAccountRatingCode()) && BseConstants.CUSTOMER_LEVEL_GAM.equals(customerEntity.getAccountRatingCode())){
					if(!currUser.getFunctionCodes().contains(BseConstants.FUNCTION_AUTH_RATING)){
						throw new CustomerException(CustomerException.UPDATE_GAM_CONTACT_NOT_UPDATERATINGAUTH);
					}
				}
			}
			//企业名称缩写
			customerEntity.setEnterpriseShortName(PingYinUtil.getFirstSpell(customerEntity.getEnterpriseName()));
			//折扣率默认值设置
			customerEntity.setDiscountRate(BseConstants.DISCOUNT_RATE);
			//联系人信息
			customerEntity.setContactEntity(customerInfo.getContactEntity());
			//客户组织
			customerEntity.setCustomerOfOrg(customerInfo.getCustomerOfOrg());
			//有DC账号
			if(!StringUtil.isEmpty(customerInfo.getDcAccount())){
				customerSyncService.updateDcAccount(customerEntity);
			}else{
				//客户性质修改  意向：3 ,状态描述:1(可用),并且现在无DC账号， 触发调用DC接口。
				if(BseConstants.CUSTOMER_NATURE_INTENTION.equals(customerEntity.getAccountType())){
					customerEntity.setDcAccount(customerSyncService.getDCAccount(customerEntity));
					//保存这次修改的内容
					customerSyncService.updateDcAccount(customerEntity);
				}
			}
			//修改改签门店
			if(!StringUtil.isEmpty(customerEntity.getSignTierCode()) &&
					(StringUtil.isEmpty(appType) || StringUtil.isEmpty(appVersion)
					|| (BseConstants.APP_TYPE_IOS.equals(appType) && BseConstants.APP_VERSION_IOS_OLD<Double.parseDouble(appVersion)) 
					|| (BseConstants.APP_TYPE_ANDROID.equals(appType) && BseConstants.APP_VERSION_ANDROID_OLD<Integer.parseInt(appVersion)))){
				Map<String, String> params = new HashMap<String,String>();
				params.put("id", customerEntity.getId());
				params.put("signTierCode", customerEntity.getSignTierCode());
				customerMapper.updateSignTierCode(params);
			}
			// 修改客户信息
			customerMapper.updateCustomerInfo(customerEntity);
			if(StringUtil.isEmpty(customerInfo.getDetailedAddress()) || !customerInfo.getDetailedAddress().equals(customerEntity.getDetailedAddress())){
				//修改时先删除经纬度
				customerLatlngMapper.delCustomerLatlngInfo(customerEntity.getId());
				//修改客户经纬度
				Map<String,String> map = LatitudeUtils.getLatitude(customerEntity.getDetailedAddress(),"");
				if(map != null && map.size() > 0){
					//新增完成之后给客户添加经纬度
					CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
					customerLatlngEntity.setId(UUIDUtil.getUUID());
					//代表客户共享里面添加
					customerLatlngEntity.setType("1");
					customerLatlngEntity.setCustomerId(customerEntity.getId());
					//维度
					customerLatlngEntity.setLat(Double.parseDouble(map.get("lat")));
					//经度
					customerLatlngEntity.setLng(Double.parseDouble(map.get("lng")));
					//是否精准
					customerLatlngEntity.setPrecise(map.get("precise"));
					//可信度
					customerLatlngEntity.setConfidence(map.get("confidence"));
					customerLatlngMapper.addCustomerLatlngInfo(customerLatlngEntity);
				}
			}
			// 修改联系人信息
			/*customerMapper.updateCustomerContactInfo(customerEntity.getContactEntity());*/
			//记录修改操作
			//如果是转为意向,意向操作 1.转为意向
			if(customerEntity.isTurnIntentFlag()){
				ReviewHistoryEntity reviewhistoryEntity = 
						new ReviewHistoryEntity(customerEntity, null, null, null, null, BseConstants.OPER_TURN_INTENT, currUser.getEmpEntity().getEmpCode());
				reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
			}else{

				ReviewHistoryEntity reviewhistoryEntity = 
						new ReviewHistoryEntity(customerEntity, null, null, null, null, BseConstants.OPER_UPDATE_CUSTOMER, currUser.getEmpEntity().getEmpCode());
				reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
			}
			//客户性质修改记录
			if(!customerEntity.getAccountType().equals(customerInfo.getAccountType())){
				CustomerNatureConvertEntity cNatureConvertEntity = new CustomerNatureConvertEntity();
				//主键
				cNatureConvertEntity.setId(UUIDUtil.getUUID());
				//客户id
				cNatureConvertEntity.setAccountId(customerEntity.getId());
				//原状态
				cNatureConvertEntity.setOldStatus(customerInfo.getAccountType());
				//新状态
				cNatureConvertEntity.setNewStatus(customerEntity.getAccountType());
				//转换人
				cNatureConvertEntity.setConvertUser(currUser.getUserName());
				this.addCustomerConvertRecord(cNatureConvertEntity);
			}
		}
	}

	@Override
	@Transactional
	public void deleteCustomer(List<String> ids, UserEntity currUser,String delDesc) {
		if (ids == null || ids.size() == 0) {
			throw new CustomerException(
					CustomerException.ADD_CUSTOMER_NULL);
		}
		// 用户信息
		//UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("delDesc", delDesc);
		map.put("username", currUser.getUserName());
		// 删除客户信息
		customerMapper.deleteCustomer(map);
		// 删除客户联系人信息
		customerMapper.deleteContactByAccountId(map);
		//删除时,dc账号不为空,通知DC删除该客户
		for(String cId :ids){
			CustomerEntity customerEntity = customerMapper.getCustomerInfoById(cId);
			if(!StringUtil.isEmpty(customerEntity.getDcAccount())){
				customerSyncService.deleteDcAccount(cId);
			}
			//记录删除操作
			//记录修改操作
			ReviewHistoryEntity reviewhistoryEntity = 
					new ReviewHistoryEntity(customerEntity, null, null, null, null, BseConstants.OPER_DELETE_CUSTOMER, currUser.getEmpEntity().getEmpCode());
			reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
		}
	}

	@Override
	public CustomerEntity queryCustomerInfoById(CustomerEntity customerEntity) {
		if (customerEntity == null
				|| StringUtil.isEmpty(customerEntity.getId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 查询部门的路区、大区、事业部信息
		CustomerEntity customerInfo = customerMapper.getCustomerInfoById(customerEntity.getId());
		if(customerInfo != null && !StringUtil.isEmpty(customerInfo.getTierCode())){
			DepartmentEntity deptEntity = new DepartmentEntity();
			deptEntity.setLogistCode(customerInfo.getTierCode());
			DepartmentVo departmentVo = iDepartmentService.queryDeptSuperiorDept(deptEntity,null);
			if(departmentVo != null){
				customerInfo.setRoadAreaCode(departmentVo.getRoadAreaCode());
				customerInfo.setRoadAreaName(departmentVo.getRoadAreaName());
				customerInfo.setRegionsCode(departmentVo.getRegionsCode());
				customerInfo.setRegionsName(departmentVo.getRegionsName());
				customerInfo.setBusinessUnitCode(departmentVo.getBusinessUnitCode());
				customerInfo.setBusinessUnitName(departmentVo.getBusinessUnitName());
			}
		}
		return customerInfo;
	}
	
	@Override
	public CustomerEntity queryAllCustomerInfoById(CustomerEntity customerEntity) {
		if (customerEntity == null
				|| StringUtil.isEmpty(customerEntity.getId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 查询部门的路区、大区、事业部信息
		CustomerEntity customerInfo = customerMapper.getAllCustomerInfoById(customerEntity.getId());
		if(customerInfo != null && !StringUtil.isEmpty(customerInfo.getTierCode())){
			DepartmentEntity deptEntity = new DepartmentEntity();
			deptEntity.setLogistCode(customerInfo.getTierCode());
			DepartmentVo departmentVo = iDepartmentService.queryDeptSuperiorDept(deptEntity,null);
			if(departmentVo != null){
				customerInfo.setRoadAreaCode(departmentVo.getRoadAreaCode());
				customerInfo.setRoadAreaName(departmentVo.getRoadAreaName());
				customerInfo.setRegionsCode(departmentVo.getRegionsCode());
				customerInfo.setRegionsName(departmentVo.getRegionsName());
				customerInfo.setBusinessUnitCode(departmentVo.getBusinessUnitCode());
				customerInfo.setBusinessUnitName(departmentVo.getBusinessUnitName());
			}
		}
		return customerInfo;
	}

	public void isAllowCustomer(CustomerEntity customerEntity, int type){
		Map<String,String> params = new HashMap<String, String>();
		// 先验证全称
		params.put("id", customerEntity.getId());
		params.put("enterpriseName", customerEntity.getEnterpriseName());
		if(customerMapper.isAllowCustomer(params) >0){
			throw new CustomerException(CustomerException.ENTERPRISENAME_REPEAT);
		}
		params.put("enterpriseName", null);
		// 如果是客户修改，不验证联系人信息
		if(type != 2){
			// 验证电话  区号与座机全部相同时，认为是重复
			/*if(customerEntity.getContactEntity() != null && 
					!StringUtil.isEmpty(customerEntity.getContactEntity().getDistrictNumber()) && 
					!StringUtil.isEmpty(customerEntity.getContactEntity().getTelephone())){
				params.put("districtNumber", customerEntity.getContactEntity().getDistrictNumber());
				params.put("telephone", customerEntity.getContactEntity().getTelephone());
				if(customerMapper.isAllowCustomer(params) >0){
					throw new CustomerException(CustomerException.TELEPHONE_REPEAT);
				}
			} else */if(customerEntity.getContactEntity() != null &&
					!StringUtil.isEmpty(customerEntity.getContactEntity().getTelephone())){
				params.put("telephone", customerEntity.getContactEntity().getTelephone());
				if(customerMapper.isAllowCustomer(params) >0){
					throw new CustomerException(CustomerException.TELEPHONE_REPEAT);
				}
			}
			// 再验证手机号
			params.put("districtNumber", null);
			params.put("telephone", null);
			if(customerEntity.getContactEntity() != null && 
				!StringUtil.isEmpty(customerEntity.getContactEntity().getCellphone())){
				params.put("cellPhone", customerEntity.getContactEntity().getCellphone());
				if(customerMapper.isAllowCustomer(params) >0){
					throw new CustomerException(CustomerException.CELLPHONE_REPEAT);
				}
			}
		}
	}

	@Override
	public List<ContactEntity> queryContactList(CustomerEntity customerInfo) {
		Map<String, String> map = new HashMap<String, String>();
		if(customerInfo != null && !StringUtil.isEmpty(customerInfo.getId())){
			map.put("accountId", customerInfo.getId());
		}else{
			return new ArrayList<ContactEntity>();
		}
		return customerMapper.getContactList(map);
	}
	
	private boolean isAllowContact(ContactEntity contactEntity){
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtil.isEmpty(contactEntity.getId())){
			map.put("id", contactEntity.getId());
		}
		// 手机号验证
		if(!StringUtil.isEmpty(contactEntity.getCellphone())){
			map.put("cellPhone", contactEntity.getCellphone());
			if(customerMapper.isAllowContact(map) >0){
				throw new CustomerException(CustomerException.CELLPHONE_REPEAT);
			}
		}
		// 验证电话  区号与座机全部相同时，认为是重复
		/*if(!StringUtil.isEmpty(contactEntity.getDistrictNumber()) && 
				!StringUtil.isEmpty(contactEntity.getTelephone())){
			map.put("cellPhone", null);
			map.put("districtNumber", contactEntity.getDistrictNumber());
			map.put("telephone", contactEntity.getTelephone());
			if(customerMapper.isAllowContact(map) >0){
				throw new CustomerException(CustomerException.TELEPHONE_REPEAT);
			}
		} else */if(!StringUtil.isEmpty(contactEntity.getTelephone())){
			map.put("cellPhone", null);
			map.put("telephone", contactEntity.getTelephone());
			if(customerMapper.isAllowContact(map) >0){
				throw new CustomerException(CustomerException.TELEPHONE_REPEAT);
			}
		}
		return true;
	}
	
	@Override
	public String addContactInfo(ContactEntity contactEntity, UserEntity currUser){
		if(contactEntity == null){
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}
		// 判断是否重复
		if(!this.isAllowContact(contactEntity)){
			throw new CustomerException(CustomerException.PHONE_REPEAT);
		}
		// 用户信息
		//UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		contactEntity.setId(UUIDUtil.getUUID());
		// 是否是默认联系人
		boolean isDefault = false;
		if(!StringUtil.isEmpty(contactEntity.getIsDefault()) && contactEntity.getIsDefault().equals(BseConstants.ACTIVE)){
			isDefault = true;
		}
		contactEntity.setIsDefault(BseConstants.INACTIVE);
		contactEntity.setCreateUser(
				currUser.getUserName());
		//区号
		if(StringUtil.isEmpty(contactEntity.getSource())){
			String districtNumber = customerMapper.queryDefaultDistrictNumberByAccountId(contactEntity.getAccountId());
			contactEntity.setDistrictNumber(districtNumber);
		}
		customerMapper.addCustomerContactInfo(contactEntity);
		// 判断是否是默认客户
		if(isDefault){
			updateContactIsDefault(contactEntity, currUser);
		}
		//记录新增联系人操作
		ReviewHistoryEntity reviewhistoryEntity = 
				new ReviewHistoryEntity(null, contactEntity, null, null, null, BseConstants.OPER_CREATE_CONTACT, currUser.getEmpEntity().getEmpCode());
		reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
		return contactEntity.getId();
	}
	
	@Override
	public void updateContactInfo(ContactEntity contactEntity, UserEntity currUser){
		if(contactEntity == null || StringUtil.isEmpty(contactEntity.getId())){
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}
		// 判断是否已删除
		ContactEntity cInfo = customerMapper.getContactById(contactEntity.getId());
		if(cInfo != null && !StringUtil.isEmpty(cInfo.getActive())){
			if(cInfo.getActive().equals(BseConstants.INACTIVE)){
				throw new CustomerException(CustomerException.CONTACT_IS_DELETE);
			}
		}
		// 判断是否重复
		if(!this.isAllowContact(contactEntity)){
			throw new CustomerException(CustomerException.PHONE_REPEAT);
		}
		// 用户信息
		//UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		contactEntity.setModifyUser(
				currUser.getUserName());
		customerMapper.updateCustomerContactInfo(contactEntity);
		// 是否是默认联系人
		if(!StringUtil.isEmpty(contactEntity.getIsDefault()) && contactEntity.getIsDefault().equals(BseConstants.ACTIVE)){
			updateContactIsDefault(contactEntity, currUser);
		}
		//记录修改联系人操作
		ReviewHistoryEntity reviewhistoryEntity = 
				new ReviewHistoryEntity(null, cInfo, null, null, null, BseConstants.OPER_UPDATE_CONTACT, currUser.getEmpEntity().getEmpCode());
		reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
	}
	
	@Override
	public void deleteContact(List<String> ids, UserEntity currUser) {
		if (ids == null || ids.size() == 0) {
			throw new CustomerException(
					CustomerException.ADD_CONTACT_NULL);
		}
		for(int i=0; i<ids.size(); i++){
			// 判断是否是默认
			ContactEntity cInfo = customerMapper.getContactById(ids.get(i));
			if(cInfo != null && !StringUtil.isEmpty(cInfo.getActive())){
				if(cInfo.getIsDefault().equals(BseConstants.ACTIVE)){
					throw new CustomerException(CustomerException.DELETE_DEFAULT_CONTACT);
				}
			}
		}
		// 用户信息
		//UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("username", currUser.getUserName());
		customerMapper.deleteContact(map);
		for(String id :ids){
			ContactEntity  contactEntity = customerMapper.getContactById(id);
			//记录新增联系人操作
			ReviewHistoryEntity reviewhistoryEntity = 
					new ReviewHistoryEntity(null, contactEntity, null, null, null, BseConstants.OPER_DELETE_CONTACT, currUser.getEmpEntity().getEmpCode());
			reviewHistoryService.insertReviewOrHistory(reviewhistoryEntity);
		}
	}
	
	@Override
	@Transactional
	public void updateContactIsDefault(ContactEntity contactEntity, UserEntity currUser) {
		if(contactEntity == null || StringUtil.isEmpty(contactEntity.getId())){
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}
		// 判断是否已删除
		ContactEntity cInfo = customerMapper.getContactById(contactEntity.getId());
		if(cInfo != null && !StringUtil.isEmpty(cInfo.getActive())){
			if(cInfo.getActive().equals(BseConstants.INACTIVE)){
				throw new CustomerException(CustomerException.CONTACT_IS_DELETE);
			}
		}
		// 判断是否已经是默认
		if(cInfo != null && !StringUtil.isEmpty(cInfo.getIsDefault())){
			if(cInfo.getIsDefault().equals(BseConstants.ACTIVE)){
				throw new CustomerException(CustomerException.ALREADY_DEFAULT_CONTACT);
			}
		}
		// 用户信息
		//UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", contactEntity.getId());
        if (cInfo != null) {
            map.put("accountId", cInfo.getAccountId());
        }
        map.put("username", currUser.getUserName());
		customerMapper.removeContactIsDefault(map);
		customerMapper.updateContactIsDefault(map);
	}

	@Override
	public CustomerEntity queryCustomerInfoByCrmAcconut(String crmAccount) {
		return customerMapper.queryCustomerInfoByCrmAcconut(crmAccount);
	}

	@Override
	public CustomerEntity queryCustomerInfoByDcAcconut(String dcAccount) {
		if (StringUtil.isEmpty(dcAccount)){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		return customerMapper.queryCustomerInfoByDcAcconut(dcAccount);
	}

	@Override
	@Transactional
	public void updateCustomerInfoInfoById(
			CustomerEntity customerEntity) {
		if(customerEntity == null){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		customerMapper.updateCustomerInfoInfoById(customerEntity);
	}

	@Override
	public String queryTierManagerIdByName(String tierName) {
		if (StringUtil.isEmpty(tierName)){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		return customerMapper.queryTierManagerIdByName(tierName);
	}
	
	@Override
	@Transactional
	public void updateCustomerTurnIntention(CustomerEntity customerIntention,
			UserEntity currUser) {
		if (StringUtils.isEmpty(customerIntention)
				|| StringUtils.isEmpty(customerIntention.getId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 查询当前客户信息
		CustomerEntity customerEntity = customerMapper
				.getCustomerInfoById(customerIntention.getId());
		// 判断原来的数据是否已经删除
		if (!StringUtil.isEmpty(customerEntity.getActive())
				&& customerEntity.getActive().equals(BseConstants.INACTIVE)) {
			throw new CustomerException(CustomerException.UPDATE_IS_DELETE);
		}
		// 判断是否是洽谈用户,新增意向(洽谈客户→转为意向)
		if (!BseConstants.CUSTOMER_NATURE_CHAT.equals(customerEntity.getAccountType())) {
			throw new CustomerException(CustomerException.IS_NOT_CHAT);
		}
		// 洽谈客户转为意向客户
		customerEntity.setAccountType(BseConstants.CUSTOMER_NATURE_INTENTION);
		customerEntity.setTurnIntentFlag(true);
		// 货物名称
		if (!StringUtil.isEmpty(customerIntention.getMainGoodsName())) {
			customerEntity.setMainGoodsName(customerIntention
					.getMainGoodsName());
		}
		// 包装方式
		if (!StringUtil.isEmpty(customerIntention.getTypeOfPackage())) {
			customerEntity.setTypeOfPackage(customerIntention
					.getTypeOfPackage());
		}
		// 邮编
		if (!StringUtil.isEmpty(customerIntention
				.getDeliveryAddressPostalCode())) {
			customerEntity.setDeliveryAddressPostalCode(customerIntention
					.getDeliveryAddressPostalCode());
		}
		// 上门取货地址
		if (!StringUtil.isEmpty(customerIntention.getDeliveryAddress())) {
			customerEntity.setDeliveryAddress(customerIntention
					.getDeliveryAddress());
		}
		// 客户期望营销类型
		if (!StringUtil.isEmpty(customerIntention.getMarketActiveType())) {
			customerEntity.setMarketActiveType(customerIntention
					.getMarketActiveType());
		}
		// 客户信用评分
		if (!StringUtil.isEmpty(customerIntention.getAccountCreditGrade())) {
			customerEntity.setAccountCreditGrade(customerIntention
					.getAccountCreditGrade());
		}
		// 营销类型描述
		if (!StringUtil.isEmpty(customerIntention.getMarketActiveRemark())) {
			customerEntity.setMarketActiveRemark(customerIntention
					.getMarketActiveRemark());
		}
		addCustomer(customerEntity, currUser,null,null);
		CustomerNatureConvertEntity convertEntity = new CustomerNatureConvertEntity();
		//主键
		convertEntity.setId(UUIDUtil.getUUID());
		//客户id
		convertEntity.setAccountId(customerEntity.getId());
		//原状态
		convertEntity.setOldStatus(BseConstants.CUSTOMER_NATURE_CHAT);
		//新状态
		convertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_INTENTION);
		//转换人
		convertEntity.setConvertUser(currUser.getUserName());
		this.addCustomerConvertRecord(convertEntity);
	}
	
	
	/**
	 * 修改客户第一单与最后一单发货时间
	 * 
	 * @author 蒋落琛
	 * @date 2015-7-20
	 * @update
	 */
	@Override
	public void updateCustomerShipmentsTime(CustomerEntity customer){
		if (customer == null || StringUtil.isEmpty(customer.getDcAccount()) || customer.getLastShipmentsTime() == null){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		customerMapper.updateCustomerShipmentsTime(customer);
	}

	@Override
	public List<CustomerEntity> queryCustomerInfosByTierCode(String TierCode) {
		if(StringUtil.isEmpty(TierCode)){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		return customerMapper.queryCustomerInfosByTierCode(TierCode);
	}

	@Override
	public void backupUpdateCustomerManagerId(Map<String, String> map) {
		customerMapper.backupUpdateCustomerManagerId(map);
	}
	
	@Override
	public void updateCustomerManagerId(Map<String, String> map) {
		customerMapper.updateCustomerManagerId(map);
	}
	
	/**
	 * 递归查询负责人信息
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月13日
	 * @update 
	 */
	private DepartmentEntity queryManagerEmpInfo(String deptCode){
		DepartmentEntity departmentEntity = new DepartmentEntity();
		departmentEntity.setDeptCode(deptCode);
		DepartmentEntity supDepartment = iDepartmentService.queryDeptByDeptCode(departmentEntity);
		if(supDepartment != null && StringUtil.isEmpty(supDepartment.getManagerId()) && !StringUtil.isEmpty(supDepartment.getSupdeptCode())){
			return queryManagerEmpInfo(supDepartment.getSupdeptCode());
		} else {
			return supDepartment;
		}
	}

	@Override
	@Transactional
	public void addCustomerConvertRecord(
			CustomerNatureConvertEntity customerNatureConvertEntity) {
		customerMapper.addCustomerConvertRecord(customerNatureConvertEntity);
	}

	@Override
	public Map<String,String> getIsStoreInfo(String deptCode) {
		Map<String,String> map = customerMapper.getIsStoreInfo(deptCode);
		return map;
	}

	@Override
	@Transactional
	public void transferCustomer(UserEntity currentUser,TransferCustomerVO transferCustomerVO) {
		if(transferCustomerVO == null || StringUtil.isEmpty(transferCustomerVO.getNewManagerCode())
				|| transferCustomerVO.getIds() == null){
			throw new CustomerException(CustomerException.PARAMS_NULL);
		}
		//备份
		customerMapper.backupSaleTransferCustomer(transferCustomerVO.getIds());
		//更新新负责人
		Map<String,Object> params = new HashMap<String,Object>();
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setEmpCode(transferCustomerVO.getNewManagerCode());
		employeeEntity = employeeService.queryEmployeeByEmpcode(employeeEntity);
		if(employeeEntity == null){
			throw new EmployeeException(EmployeeException.USER_NULL);
		}
		params.put("empCode", employeeEntity.getEmpCode());
		params.put("empName", employeeEntity.getEmpName());
		params.put("userName", currentUser.getUserName());
		params.put("ids", transferCustomerVO.getIds());
		customerMapper.updateTransferCustomerManager(params);
		//同步负责人给DC
		for(String id : transferCustomerVO.getIds()){
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setId(id);
			customerEntity = this.queryCustomerInfoById(customerEntity);
			if(!StringUtil.isEmpty(customerEntity.getDcAccount())){
				customerSyncService.updateDcAccount(customerEntity);
			}
		}
	}

	@Override
	public String registerObhAccount(CustomerEntity customerEntity) {
		if(customerEntity == null || customerEntity.getId() == null){
			//无法注册
			throw new CustomerException(CustomerException.REGISTER_INFO_NULL);
		}
		customerEntity = this.customerMapper.getCustomerInfoById(customerEntity.getId());
		if(customerEntity == null || customerEntity.getContactEntity() == null
				|| StringUtil.isEmpty(customerEntity.getContactEntity().getCellphone())
				|| !MobileUtil.isMobileNO(customerEntity.getContactEntity().getCellphone())){
			//无法注册
			throw new CustomerException(CustomerException.REGISTER_INFO_NULL);
		}
		RegisterEntity registerEntity = new RegisterEntity();
		ObhUserEntity userEntity = new ObhUserEntity();
		userEntity.setCrmguid(customerEntity.getId());
		userEntity.setEnterpriseName(customerEntity.getEnterpriseName());
		userEntity.setDetailedAddress(customerEntity.getDetailedAddress());
		userEntity.setContactName(customerEntity.getContactEntity().getContactName());
		userEntity.setEmail(customerEntity.getContactEntity().geteMailAddress());
		userEntity.setTelephone(customerEntity.getContactEntity().getTelephone());
		registerEntity.setUserEntity(userEntity);
		registerEntity.setCellphone(customerEntity.getContactEntity().getCellphone());
		String password = MobileUtil.getRandomString(6);
		registerEntity.setPassword(password);
		ISyncFacadeService port = ObhWebserviceUtil.PORT;
		RegisterUserResModel result =  port.registerUser(JsonUtils.toJson(registerEntity));
		if(result.isResult()){
			//成功
			//发送账号短信
			SmsEntity smsEntity = new SmsEntity();
			smsEntity.setContent("尊敬的用户，这是系统为您自动生成的天地华宇官网账号"+registerEntity.getCellphone()+"和登陆密码"+password
					+"。为了您的账户安全，请用本手机号登陆天地华宇官网并及时修改密码。");
			smsEntity.setMobile(registerEntity.getCellphone());
			smsEntity.setSearchID(UUIDUtil.getUUID());
			smsEntity.setSendTime(new Date());
			String isSendSuccess = BseConstants.YES;
			try {
				SMSPlatformUtil.sendMsg(smsEntity);
			} catch (Exception e) {
				e.printStackTrace();
				isSendSuccess = BseConstants.NO;
			}
			if(BseConstants.NO.equals(isSendSuccess)){
				//提示短信发送失败
				throw new CustomerException(CustomerException.SEND_FAIL);
			}
			//更新客户表
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", result.getUserId());
			params.put("id", customerEntity.getId());
			params.put("obhAccount", registerEntity.getCellphone());
			this.updateCustomerOfObhAccountStatus(params);
            return  BseConstants.YES;
		}else if(BseConstants.OBH_WEBSERVICE_STATUS_CELLPHONE.equals(result.getResultCode())
					|| BseConstants.OBH_WEBSERVICE_STATUS_EMAIL.equals(result.getResultCode())){
			//更新客户表
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", result.getUserId());
			params.put("id", customerEntity.getId());
			params.put("obhAccount", customerEntity.getContactEntity().getCellphone());
			this.updateCustomerOfObhAccountStatus(params);
			//绑定crmID到官网
			userEntity.setUserId(Integer.valueOf(result.getUserId()));
			port.updateUserInfo(JsonUtils.toJson(userEntity));
            return  BseConstants.NO;
		}else{
			LOG.info(result.getResultInfo());
			throw new CustomerException(CustomerException.OBH_WEBSERVICE_FAIL);
		}
	}

	@Override
	public void updateCustomerOfObhAccountStatus(Map<String, Object> params) {
		customerMapper.updateCustomerOfObhAccountStatus(params);
	}
}
