package com.hoau.crm.module.appcore.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.ICustomerAppService;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerLatlngAppVo;
import com.hoau.crm.module.appcore.server.dao.CustomerAppMapper;
import com.hoau.crm.module.appcore.server.dao.SignAppMapper;
import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.server.service.IPhoneInfoService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.PhoneInfoEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.PhoneInfoException;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.common.server.util.LatitudeUtils;
import com.hoau.crm.module.customer.api.server.ICustomerInfoPoolService;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.server.ICustomerTotalService;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerTotalEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;
import com.hoau.crm.module.customer.api.shared.vo.CustomerVo;
import com.hoau.crm.module.customer.server.dao.CustomerMapper;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 客户管理模块RESTFUL接口实现
 * 
 * @author 蒋落琛
 * @date 2015-6-15
 */
@Service
public class CustomerAppService implements ICustomerAppService {

	@Resource
	ICustomerService iCustomerService;

	@Resource
	CustomerAppMapper customerAppMapper;
	
	@Resource
	CustomerMapper customerMapper;
	
	@Resource
	ICustomerInfoPoolService iCustomerInfoPoolService;

	@Resource
	ILoginService iLoginService;

	@Resource
	IPhoneInfoService iPhoneInfoService;
	
	@Resource
	private IDepartmentService iDepartmentService;
	
	@Resource
	private ICustomerTotalService iCustomerTotalService;
	
	@Resource
	SignAppMapper signAppMapper;
	@Override
	public ResponseBaseEntity<CustomerAppVo> addCustomer(
			CustomerAppVo customerRestfulVo, String loginName) {
		// 新增
		if (customerRestfulVo == null
				|| customerRestfulVo.getCustomerEntity() == null
				|| customerRestfulVo.getCustomerEntity().getContactEntity() == null) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iCustomerService.addCustomer(customerRestfulVo.getCustomerEntity(),	user,null,null);
		// 返回值
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	public ResponseBaseEntity<CustomerAppVo> editCustomer(CustomerAppVo customerRestfulVo, String loginName
				,String appVersion,String appType) {
		// 修改
		if (customerRestfulVo == null
				|| customerRestfulVo.getCustomerEntity() == null
				|| StringUtil.isEmpty(customerRestfulVo.getCustomerEntity()
						.getId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		/*
		 * CustomerEntity customer =
		 * iCustomerService.queryCustomerInfoById(customerRestfulVo.getCustomerEntity());
		 */
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iCustomerService.addCustomer(customerRestfulVo.getCustomerEntity(),	user,appVersion,appType);
		// 返回值
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> queryCustomerInfo(
			CustomerAppVo searchVo, String loginName) {
		// 返回值
		CustomerAppVo customerAppVo = new CustomerAppVo();
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		RowBounds rb;
		// 分页查询条件
		if (searchVo.getLimit() == 0) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		rb = new RowBounds(searchVo.getStart(), searchVo.getLimit());
		if (searchVo != null) {
			// 列表查询参数拼接
			if (!StringUtil.isEmpty(searchVo.getSelectorParam())) {
				//模糊查询内容
				String selectorParam = searchVo.getSelectorParam();
				if(searchVo.getSelectorType() == 0){
					//旧版本兼容
					params.put("selectorParam", searchVo.getSelectorParam());
					// 如果包含中文，则查询企业名称、联系人
					if(BseConstants.isContainsChinese(selectorParam)){
						params.put("enterpriseName", "%" + selectorParam+ "%");
						// 如果全是拼音，则搜索企业全称首字母
					}else if(BseConstants.isAllString(selectorParam)){
						params.put("enterpriseShortName", "%" + selectorParam+ "%");
						// 否则为搜索CRM账号
					}else {
						params.put("accountCode", selectorParam);
					}
				}else{
					//模糊查询类型(1-客户名称,2-迪辰账号,3-CRM账号,4-手机号,5-座机号,6-联系人姓名,7-客户地址)
					int selectorType = searchVo.getSelectorType();
					switch (selectorType) {
					case 1: // 如果包含中文，则查询企业名称、联系人
						if(BseConstants.isContainsChinese(selectorParam)){
							params.put("enterpriseName", "%" + selectorParam+ "%");
						} else if(BseConstants.isAllString(selectorParam)){
							// 如果全是拼音，则搜索企业全称首字母
							params.put("enterpriseShortName", "%" + selectorParam+ "%");
						}
						break;
					case 2: params.put("dcAccount", selectorParam);
						break;
					case 3: params.put("accountCode", selectorParam);
						break;
					case 4: params.put("cellPhone", selectorParam);
						break;
					case 5: params.put("telephone", selectorParam);
						break;
					case 6: params.put("contactName", "%" + selectorParam+ "%");
						break;
					case 7: params.put("customerAddress", selectorParam);
						break;
					}
				}
			}
			// 客户性质
			if (!StringUtil.isEmpty(searchVo.getAccountType())) {
				params.put("accountType", searchVo.getAccountType());
			}
			// 合同状态
			if (!StringUtil.isEmpty(searchVo.getContractStatus())) {
				params.put("contractStatus", searchVo.getContractStatus());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			// 开始时间
			if (searchVo.getStartDate() != null) {
				params.put("startDate", sdf.format(searchVo.getStartDate()));
			}
			// 结束时间
			if (searchVo.getEndDate() != null) {
				params.put("endDate", "" + sdf.format(searchVo.getEndDate()));
			}
			// 洽谈开始时间
			if (searchVo.getChatsStartDate() != null) {
				params.put("chatsStartDate", sdf.format(searchVo.getChatsStartDate()));
			}
			// 洽谈结束时间
			if (searchVo.getChatsEndDate() != null) {
				params.put("chatsEndDate", "" + sdf.format(searchVo.getChatsEndDate()));
			}
			// 客户ID
			if (!StringUtil.isEmpty(searchVo.getCustomerId())) {
				params.put("customerId", searchVo.getCustomerId());
			}
			//排序字段
			if(StringUtil.isEmpty(searchVo.getSortType()) || BseConstants.YES.equals(searchVo.getSortType())){
				//时间排序
				//排序规则
				if(StringUtil.isEmpty(searchVo.getSortTypeSub())
						|| "DESC".equals(searchVo.getSortTypeSub())){
					params.put("timeSortDesc", BseConstants.ACTIVE);
				}else if("ASC".equals(searchVo.getSortTypeSub())){
					params.put("timeSortAsc", BseConstants.ACTIVE);
				}
			}else if(BseConstants.NO.equals(searchVo.getSortType())){
				//产值排序
				//排序规则
				if(StringUtil.isEmpty(searchVo.getSortTypeSub())
						|| "DESC".equals(searchVo.getSortTypeSub())){
					params.put("productValueSortDesc", BseConstants.ACTIVE);
				}else if("ASC".equals(searchVo.getSortTypeSub())){
					params.put("productValueSortAsc", BseConstants.ACTIVE);
				}
			}
		}
		// 判断是否根据ID查询
		if(StringUtil.isEmpty(params.get("customerId"))){
			// 当前用户
			UserEntity currentUser = iLoginService
					.getUserByLoginName(loginName);
			if(!StringUtil.isEmpty(currentUser.getUserName())){
				params.put("createdBy", currentUser.getUserName());
			}
			//当前用户拥有的功能信息ID集合
			Set<String> functions = currentUser.getFunctionCodes();
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
			} else if(searchVo.getStartDate() == null && searchVo.getEndDate() == null && StringUtil.isEmpty(searchVo.getSelectorParam())){
				// 默认保持一个月的查询范围
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
				Calendar c1 = Calendar.getInstance();  
				c1.setTime(new Date());  
				c1.set(Calendar.DATE, c1.get(Calendar.DATE) - 31); 
				String startDate = sdf.format(c1.getTime());
				Calendar c2 = Calendar.getInstance();  
				c2.setTime(new Date());  
				c2.set(Calendar.DATE, c2.get(Calendar.DATE) + 1); 
				String endDate = sdf.format(c2.getTime());
				// 开始时间
				params.put("startDate", startDate);
				// 结束时间
				params.put("endDate", "" + endDate);
			}
		}
		// 查询数据
		List<CustomerEntity> list = customerAppMapper.getCustomerInfo(params,rb);
		// 数据总数
		long count = customerAppMapper.countCustomer(params);
		/*if (searchVo == null || StringUtil.isEmpty(searchVo.getSelectorParam())) {
			count = customerAppMapper.countCustomer(params);
		}*/
		customerAppVo.setCustomerEntityList(list);
		customerAppVo.setTotalCount(count);
		// 返回值
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setResult(customerAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> queryCustomerInfoById(
			CustomerAppVo searchVo, String loginName) {
		if (searchVo == null || searchVo.getCustomerEntity() == null
				|| StringUtil.isEmpty(searchVo.getCustomerEntity().getId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 查询客户信息
		CustomerEntity customer = iCustomerService
				.queryCustomerInfoById(searchVo.getCustomerEntity());
		// 查询陪同人员信息
		DepartmentVo deptVo = null;
		DepartmentEntity deptEntity = new DepartmentEntity();
		if (customer != null && !StringUtil.isEmpty(customer.getTierCode())) {
			deptEntity.setLogistCode(customer.getTierCode());
			deptVo = iDepartmentService
					.queryDeptSuperiorDept(deptEntity,null);
		}
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		CustomerAppVo customerVo = new CustomerAppVo();
		customerVo.setDepartmentVo(deptVo);
		//查询该客户的签到
		Map<String,String> map = new HashMap<String, String>();
		map.put("loginName", loginName);
		map.put("accountId", searchVo.getCustomerEntity().getId());
		long count = signAppMapper.countQueryNoRelationSignList(map);
		if(count>0){
			customerVo.setIsSign("Y");
		}
		customerVo.setCustomerEntity(customer);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(customerVo);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> deleteCustomerByIds(
			CustomerAppVo customerVo, String loginName) {
		if (customerVo == null) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		iCustomerService.deleteCustomer(customerVo.getIds(), currentUser,null);
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> queryPhoneInfoByPhone(
			CustomerAppVo customerVo) {
		if (customerVo == null) {
			throw new PhoneInfoException(PhoneInfoException.PHONE_NULL);
		}
		PhoneInfoEntity phoneInfoEntity = iPhoneInfoService
				.queryPhoneInfoByPhone(customerVo.getPhoneInfoEntity());
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		CustomerAppVo resultInfo = new CustomerAppVo();
		resultInfo.setPhoneInfoEntity(phoneInfoEntity);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(resultInfo);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> queryContactList(
			CustomerAppVo customerVo) {
		if (customerVo == null) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		List<ContactEntity> list = iCustomerService.queryContactList(customerVo
				.getCustomerEntity());
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		CustomerAppVo resultInfo = new CustomerAppVo();
		resultInfo.setContactEntityList(list);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(resultInfo);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> addContact(
			CustomerAppVo customerVo, String loginName) {
		if (customerVo == null) {
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		iCustomerService.addContactInfo(customerVo.getContactEntity(),
				currentUser);
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		CustomerAppVo resultInfo = new CustomerAppVo();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(resultInfo);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> editContact(
			CustomerAppVo customerVo, String loginName) {
		if (customerVo == null) {
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);

		iCustomerService.updateContactInfo(customerVo.getContactEntity(),
				currentUser);
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		CustomerAppVo resultInfo = new CustomerAppVo();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(resultInfo);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> deleteContact(
			CustomerAppVo customerVo, String loginName) {
		if (customerVo == null) {
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);

		iCustomerService.deleteContact(customerVo.getIds(), currentUser);
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		CustomerAppVo resultInfo = new CustomerAppVo();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(resultInfo);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> updateContactIsDefault(
			CustomerAppVo customerVo, String loginName) {
		if (customerVo == null) {
			throw new CustomerException(CustomerException.ADD_CONTACT_NULL);
		}
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);

		iCustomerService.updateContactIsDefault(customerVo.getContactEntity(),
				currentUser);
		// 返回结果
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		CustomerAppVo resultInfo = new CustomerAppVo();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(resultInfo);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> querySuperiorCustomerInfo(
			CustomerAppVo searchVo, String loginName) {
		RowBounds rb;
		// 分页查询条件
		if (searchVo == null || searchVo.getLimit() == 0) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		rb = new RowBounds(searchVo.getStart(), searchVo.getLimit());
		CustomerVo customerVo = new CustomerVo();
		if (!StringUtil.isEmpty(searchVo.getSelectorParam())) {
			customerVo.setSelectorParam(searchVo.getSelectorParam());
		}
		// 查询
		List<CustomerEntity> cList = iCustomerService.queryCustomerInfoByCombo(
				customerVo, rb);
		// 前台只显示分页的搜索结果，不会显示下一页
		long count = 0;
		//long count = iCustomerService.countCustomerByCombo(customerVo);
		// 返回值
		CustomerAppVo rVo = new CustomerAppVo();
		rVo.setCustomerEntityList(cList);
		rVo.setTotalCount(count);
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setResult(rVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	@Override
	public ResponseBaseEntity<CustomerAppVo> updateCustomerTurnIntention(
			CustomerAppVo customerVo, String loginName) {
		if(StringUtils.isEmpty(customerVo)||StringUtils.isEmpty(customerVo.getCustomerEntity().getId())){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		//获取当前用户
		UserEntity currUser = iLoginService.getUserByLoginName(loginName);
		//保存当前意向信息
		iCustomerService.updateCustomerTurnIntention(customerVo.getCustomerEntity(),currUser);
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	/**
	 * 获取附近指定公里范围内的客户信息
	 * 
	 * @return
	 * @author 275636
	 * @date 2015-7-10
	 * @update
	 */
	@Override
	public ResponseBaseEntity<CustomerLatlngAppVo> getNearCustomerScopeLatLng(
			CustomerLatlngAppVo customerLatlngAppVo,String loginName) {
		if(customerLatlngAppVo.getLat() <= 0 || customerLatlngAppVo.getLng() <=0 || customerLatlngAppVo.getDistanceScope() <= 0){
			throw new CustomerException(CustomerException.IS_NOT_NEARSCOPE);
		}
		//根据中心经纬度获取10公里范围内的最大最小经纬度
		double[] ds = LatitudeUtils.getAround(customerLatlngAppVo.getLat(), customerLatlngAppVo.getLng(), (int)(customerLatlngAppVo.getDistanceScope()/4));
		Map<String,String> map = new HashMap<String,String>();
		map.put("minLat", ds[0]+"");
		map.put("minLng", ds[3]+"");
		map.put("maxLat", ds[2]+"");
		map.put("maxLng", ds[1]+"");
		map.put("lat", customerLatlngAppVo.getLat()+"");
		map.put("lng", customerLatlngAppVo.getLng()+"");
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		//当前用户拥有的功能信息ID集合
		Set<String> functions = currentUser.getFunctionCodes();
		if(!StringUtil.isEmpty(currentUser.getUserName())){
			map.put("createdBy", currentUser.getUserName());
		}
		//所有数据权限
		if(!functions.contains(BseConstants.ALLDATA_CUSTOMERMANAGER)){
			//是否有新门店研究组数据权限
			if(functions.contains(BseConstants.FUNCTION_AUTH_NEWSTOREDATA)){
				map.put("newStoreData", BseConstants.MANAGENAME);
			}else if(functions.contains(BseConstants.FUNCTION_AUTH_TACTICCUSTOMER)){
				//是否有战略客户部数据权限
				if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
					map.put("tacticCustomerDeptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				}
			}else if(functions.contains(BseConstants.FUNCTION_AUTH_CUSTOMERMANAGE)){
				//是否有客户管理部数据权限
				if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
					map.put("customerManage",BseConstants.YES);
				}
			}else if(functions.contains(BseConstants.ALLDATA_SUBCOMPANYCUSTOMERMANAGER)){
				//普通权限
				DepartmentEntity currDepartmentEntity = currentUser.getEmpEntity().getDeptEntity();
				if(!StringUtil.isEmpty(currDepartmentEntity.getDeptCode())){
					Map<String,String> map1 = customerMapper.getIsStoreInfo(currDepartmentEntity.getDeptCode());
					//是门店
					if(BseConstants.IS_STORE.equals(map1.get("isStore"))){
						map.put("storeAuth", map1.get("logistCode"));
					}else if(customerMapper.isRoadByDeptCode(currDepartmentEntity.getDeptCode())>0){
						map.put("roadDeptcode", currDepartmentEntity.getDeptCode());
					}else{
						map.put("userDeptcode", currDepartmentEntity.getDeptCode());
					}
				}
			}else{
				//客户经理数据权限
				if(!StringUtil.isEmpty(currentUser.getEmpEntity().getEmpCode())){
					map.put("salePerson", currentUser.getEmpEntity().getEmpCode());
				}
			}
		}
				
		List<CustomerLatlngEntity> customerLatlngEntities = new ArrayList<CustomerLatlngEntity>();
		customerLatlngEntities = customerAppMapper.getNearCustomerScopeLatLng(map);
		
		customerLatlngAppVo.setCustomerLatlngEntities(customerLatlngEntities);
		ResponseBaseEntity<CustomerLatlngAppVo> result = new ResponseBaseEntity<CustomerLatlngAppVo>();
		result.setResult(customerLatlngAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@Override
	public ResponseBaseEntity<CustomerAppVo> queryCustomerInfoPool(
			CustomerAppVo searchVo, String loginName) {
		// 返回值
		CustomerAppVo customerAppVo = new CustomerAppVo();
		RowBounds rb;
		// 分页查询条件
		if (searchVo.getLimit() == 0) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		rb = new RowBounds(searchVo.getStart(), searchVo.getLimit());
		CustomerInfoPoolVo customerInfoPoolVo = new CustomerInfoPoolVo();
		CustomerInfoPoolEntity customerInfoPoolEntity = new CustomerInfoPoolEntity();
		if (searchVo != null) {
			//模糊查询内容
			String selectorParam = searchVo.getSelectorParam();
			if(searchVo.getSelectorType() == 0){
				//旧版本兼容
				//企业名称
				customerInfoPoolEntity.setCompanyName(searchVo.getSelectorParam());
				customerInfoPoolVo.setCustomerInfoPoolEntity(customerInfoPoolEntity);
			}else{
				//模糊查询类型(1-客户名称,2-手机号,3-座机号,4-联系人姓名,5-客户地址)
				int selectorType = searchVo.getSelectorType();
				switch (selectorType) {
				case 1:	customerInfoPoolEntity.setCompanyName(selectorParam);
					break;
				case 2: customerInfoPoolEntity.setContactWay(selectorParam);
					break;
				case 3: customerInfoPoolEntity.setContactWay(selectorParam);
					break;
				case 4: customerInfoPoolEntity.setContactPerson(selectorParam);
					break;
				case 5: customerInfoPoolEntity.setCompanyAddress(selectorParam);
					break;
				}
			}
		}
		//共享客户状态
		if(!StringUtil.isEmpty(searchVo.getCustomerInfoPoolStatus())){
			customerInfoPoolEntity.setDispenseStatus(searchVo.getCustomerInfoPoolStatus());
		}
		customerInfoPoolVo.setCustomerInfoPoolEntity(customerInfoPoolEntity);
		//获取当前用户
		UserEntity currUser = iLoginService.getUserByLoginName(loginName);
		//排序规则
		customerInfoPoolVo.setSortType(searchVo.getSortTypeSub());
		//查询数据
		List<CustomerInfoPoolEntity> list = iCustomerInfoPoolService.queryUploadCustomer(customerInfoPoolVo, rb, currUser);
		long count = iCustomerInfoPoolService.countUploadCustomer(customerInfoPoolVo, currUser);
		customerAppVo.setCustomerPooList(list);
		customerAppVo.setTotalCount(count);
		// 返回值
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setResult(customerAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerAppVo> getCustomerInfoPoolById(
			CustomerAppVo searchVo, String loginName) {
		// 查询条件
		if (searchVo == null || searchVo.getCustomerPooLEntity() == null || StringUtil.isEmpty(searchVo.getCustomerPooLEntity().getId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 返回值
		CustomerAppVo customerAppVo = new CustomerAppVo();
		//获取当前用户
		//UserEntity currUser = iLoginService.getUserByLoginName(loginName);
		CustomerInfoPoolEntity customerInfo = iCustomerInfoPoolService.getCustomerInfoPoolById(searchVo.getCustomerPooLEntity().getId());
		customerAppVo.setCustomerPooLEntity(customerInfo);
		// 返回值
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setResult(customerAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@Override
	public ResponseBaseEntity<CustomerAppVo> updateBackReason(
			CustomerAppVo searchVo, String loginName) {
		// 查询条件
		if (searchVo == null || searchVo.getCustomerPooLEntity() == null || StringUtil.isEmpty(searchVo.getCustomerPooLEntity().getId())
				|| StringUtil.isEmpty(searchVo.getCustomerPooLEntity().getBackReason())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		//获取当前用户
		UserEntity currUser = iLoginService.getUserByLoginName(loginName);
		iCustomerInfoPoolService.updateBackReason(searchVo.getCustomerPooLEntity(), currUser);
		// 返回值
		ResponseBaseEntity<CustomerAppVo> result = new ResponseBaseEntity<CustomerAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<Void> transferCustomer(CustomerAppVo customerAppVo, String loginName) {
		if(customerAppVo == null || customerAppVo.getTransferCustomerVO() == null
				|| StringUtil.isEmpty(customerAppVo.getTransferCustomerVO().getCustomerType())
				|| StringUtil.isEmpty(customerAppVo.getTransferCustomerVO().getNewManagerCode())
				|| customerAppVo.getTransferCustomerVO().getIds() == null){
			throw new CustomerException(CustomerException.PARAMS_NULL);
		}
		TransferCustomerVO transferCustomerVO = customerAppVo.getTransferCustomerVO();
		//获取当前用户
		UserEntity currUser = iLoginService.getUserByLoginName(loginName);
		//客户列表转让
		if(BseConstants.TRANSFERCUSTOMER_TYPE_CUSTOMER.equals(transferCustomerVO.getCustomerType())){
			iCustomerService.transferCustomer(currUser,transferCustomerVO);
		}else if(BseConstants.TRANSFERCUSTOMER_TYPE_CUSTOMERINFOPOOL.equals(transferCustomerVO.getCustomerType())){
			//共享客户转让
			iCustomerInfoPoolService.transferCustomer(currUser,transferCustomerVO);
		}
		// 返回值
		ResponseBaseEntity<Void> result = new ResponseBaseEntity<Void>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<CustomerTotalEntity> queryCustomerTotal(
			String loginName) {
		// 返回值
		ResponseBaseEntity<CustomerTotalEntity> result = new ResponseBaseEntity<CustomerTotalEntity>();
		result.setResult(iCustomerTotalService.queryCustomerTotalByUserCode(loginName));
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
