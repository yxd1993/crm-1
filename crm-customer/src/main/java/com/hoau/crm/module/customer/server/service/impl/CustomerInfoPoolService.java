package com.hoau.crm.module.customer.server.service.impl;

import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.service.IRoleService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.BseDeptEntity;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.RoleEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.EmployeeException;
import com.hoau.crm.module.bse.api.shared.vo.TransferCustomerVO;
import com.hoau.crm.module.common.server.util.LatitudeUtils;
import com.hoau.crm.module.common.server.util.PolygonUtil;
import com.hoau.crm.module.customer.api.server.ICustomerInfoPoolService;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.crm.module.customer.api.shared.domain.DistrictEntity;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.customer.api.shared.exception.CustomerInfoPoolException;
import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;
import com.hoau.crm.module.customer.server.dao.CustomerInfoPoolMapper;
import com.hoau.crm.module.customer.server.dao.CustomerLatlngMapper;
import com.hoau.crm.module.customer.server.dao.UserScopeMapper;
import com.hoau.crm.module.sales.api.server.service.ISaleChatsService;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * @author: 何斌
 * @create: 2015年5月26日 下午4:50:47
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerInfoPoolService implements ICustomerInfoPoolService {
	
	@Resource
	private CustomerInfoPoolMapper customerInfoPoolMapper;
	
	@Resource
	private CustomerLatlngMapper customerLatlngMapper;
	
	@Resource
	private UserScopeMapper userScopeMapper;
	
	@Resource
	private ISaleChatsService saleChatsService;

	@Resource
	private IRoleService roleService;
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private IDepartmentService departmentService;

	@Resource
	private IEmployeeService iEmployeeService;

	@Override
	@Transactional
	public void addOrEditCustomer(CustomerInfoPoolEntity customerInfoPoolEntity) {
		if (customerInfoPoolEntity == null) {
			throw new CustomerInfoPoolException(
					CustomerInfoPoolException.CUSTOMERINFOPOOL_NULL);
		}
		// 判断是否重复
		if (!this.isAllowCustomer(customerInfoPoolEntity)) {
			throw new CustomerInfoPoolException(
					CustomerInfoPoolException.CUSTOMERINF_REPEAT);
		}
		// 用户信息
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		
		// 新增
		if (StringUtil.isEmpty(customerInfoPoolEntity.getId())) {
			/**
			 * 2015-06-30 修改ID
			 * */
			String uuid = UUIDUtil.getUUID();
			customerInfoPoolEntity.setId(uuid);
			
			//创建人
			customerInfoPoolEntity.setCreateUser(currUser.getUserName());
			
			//默认未分发
			customerInfoPoolEntity.setDispenseStatus(BseConstants.DISPENSE_STATU_NO);
			
			//得到经纬度坐标
			Map<String,String> map = LatitudeUtils.getLatitude(customerInfoPoolEntity.getCompanyAddress(), customerInfoPoolEntity.getCityCode());
			if(map != null && map.size() > 0){
				//新增完成之后给客户添加经纬度
				CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
				customerLatlngEntity.setId(UUIDUtil.getUUID());
				//代表客户共享里面添加
				customerLatlngEntity.setType("0");
				customerLatlngEntity.setCustomerId(uuid);
				//维度
				customerLatlngEntity.setLat(Double.parseDouble(map.get("lat")));
				//经度
				customerLatlngEntity.setLng(Double.parseDouble(map.get("lng")));
				//是否精准
				customerLatlngEntity.setPrecise(map.get("precise"));
				//可信度
				customerLatlngEntity.setConfidence(map.get("confidence"));
//				customerLatlngMapper.addCustomerLatlngInfo(customerLatlngEntity);
//				//保存经纬度之后确定客户范围
//				List<UserScopeEntity> entities = userScopeMapper.queryCustomerByUserScopeInfo(map);
//				if(!entities.isEmpty() && entities.size() > 0){
//					customerInfoPoolEntity.setManageEmpCode(entities.get(0).getUser_id());
//					customerInfoPoolEntity.setManagePerson(entities.get(0).getUser_name());
//					//已分发
//					customerInfoPoolEntity.setDispenseStatus("2");
//					customerInfoPoolEntity.setCreateUser(currUser.getUserName());
//				}
				// 获取公司所有用户服务范围
				List<UserScopeEntity> entities = userScopeMapper.queryCustomerByUserScopeInfo();
				//创建一个客户2d坐标
				Point2D.Double point = new Point2D.Double(Double.parseDouble(map.get("lng")), Double.parseDouble(map.get("lat")));
				//得到客户负责人
				String[] khManage = getCustomerPerson(point,entities);
				if(null != khManage){
					customerInfoPoolEntity.setManageEmpCode(khManage[0]);
					customerInfoPoolEntity.setManagePerson(khManage[1]);
					//已分发
					customerInfoPoolEntity.setDispenseStatus(BseConstants.DISPENSE_STATU_YES);
					//确认已经可以分配到客户就添加客户坐标
					customerLatlngMapper.addCustomerLatlngInfo(customerLatlngEntity);
				}
			}
			customerInfoPoolMapper.addUploadCustomer(customerInfoPoolEntity);
			
		// 修改
		} else {
			//根据主键查询客户信息
			CustomerInfoPoolEntity cInfo = customerInfoPoolMapper.getCustomerInfoPoolById(customerInfoPoolEntity.getId());
			// 判断原来的数据是否已经删除
			if(!StringUtil.isEmpty(cInfo.getActive()) && cInfo.getActive().equals(BseConstants.INACTIVE)){
				throw new CustomerInfoPoolException(CustomerException.UPDATE_IS_DELETE);
			}
			customerInfoPoolEntity.setDispenseStatus(cInfo.getDispenseStatus());
			customerInfoPoolEntity.setManageEmpCode(cInfo.getManageEmpCode());
			customerInfoPoolEntity.setManagePerson(cInfo.getManagePerson());
			//修改人
			customerInfoPoolEntity.setModifyUser(currUser.getUserName());
			
			//如果地址改变就重新设置经纬度分配,并且是未分配数据
			if(!customerInfoPoolEntity.getCompanyAddress().equals(cInfo.getCompanyAddress()) && cInfo.getDispenseStatus().equals(BseConstants.DISPENSE_STATU_NO)){
				//修改时先删除经纬度
				customerLatlngMapper.delCustomerLatlngInfo(customerInfoPoolEntity.getId());
				
				//得到经纬度坐标
				Map<String,String> map = LatitudeUtils.getLatitude(customerInfoPoolEntity.getCompanyAddress(), customerInfoPoolEntity.getCityCode());
				if(map != null  && map.size() > 0){
					//新增完成之后给客户添加经纬度
					CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
					customerLatlngEntity.setId(UUIDUtil.getUUID());
					//代表客户共享里面添加
					customerLatlngEntity.setType("0");
					customerLatlngEntity.setCustomerId(customerInfoPoolEntity.getId());
					//维度
					customerLatlngEntity.setLat(Double.parseDouble(map.get("lat")));
					//经度
					customerLatlngEntity.setLng(Double.parseDouble(map.get("lng")));
					//是否精准
					customerLatlngEntity.setPrecise(map.get("precise"));
					//可信度
					customerLatlngEntity.setConfidence(map.get("confidence"));
//					customerLatlngMapper.addCustomerLatlngInfo(customerLatlngEntity);
//					if("1".equals(cInfo.getDispenseStatus())){
//						//保存经纬度之后确定客户范围
//						List<UserScopeEntity> entities = userScopeMapper.queryCustomerByUserScopeInfo(map);
//						if(!entities.isEmpty() && entities.size() > 0){
//							customerInfoPoolEntity.setManageEmpCode(entities.get(0).getUser_id());
//							customerInfoPoolEntity.setManagePerson(entities.get(0).getUser_name());
//							//已分发
//							customerInfoPoolEntity.setDispenseStatus("2");
//							customerInfoPoolEntity.setModifyUser(currUser.getUserName());
//						}
//					}
					
					// 获取公司所有用户服务范围
					List<UserScopeEntity> entities = userScopeMapper.queryCustomerByUserScopeInfo();
					//创建一个客户2d坐标
					Point2D.Double point = new Point2D.Double(Double.parseDouble(map.get("lng")), Double.parseDouble(map.get("lat")));
					//得到客户负责人
					String[] khManage = getCustomerPerson(point,entities);
					if(null != khManage){
						customerInfoPoolEntity.setManageEmpCode(khManage[0]);
						customerInfoPoolEntity.setManagePerson(khManage[1]);
						//已分发
						customerInfoPoolEntity.setDispenseStatus(BseConstants.DISPENSE_STATU_YES);
						//确认已经可以分配到客户就添加客户坐标
						customerLatlngMapper.addCustomerLatlngInfo(customerLatlngEntity);
					}
				}
			}
			customerInfoPoolMapper.updateUploadCustomer(customerInfoPoolEntity);
		}
	}

	@Override
	@Transactional
	public void deleteCustomer(List<String> ids) {
		if (ids == null || ids.size() == 0) {
			throw new CustomerInfoPoolException(
					CustomerInfoPoolException.CUSTOMERINFOPOOL_NULL);
		}
		// 用户信息
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("username", currUser.getUserName());
		customerInfoPoolMapper.deleteUploadCustomer(map);
	}

	@Override
	public List<CustomerInfoPoolEntity> queryUploadCustomer(
			CustomerInfoPoolVo customerInfoPoolVo, RowBounds rb, UserEntity currentUser) {
		if (rb == null) {
			throw new CustomerInfoPoolException(
					CustomerInfoPoolException.RB_NULL);
		}
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		// 判断查询条件，模糊查询的进行处理
		if (customerInfoPoolVo != null) {
			if (customerInfoPoolVo.getCustomerInfoPoolEntity() != null) {
				CustomerInfoPoolEntity customerInfoPoolEntity = customerInfoPoolVo.getCustomerInfoPoolEntity();
				// 上传人部门
				if (customerInfoPoolEntity.getEmployeeEntity()!=null 
						&& !StringUtil.isEmpty(customerInfoPoolEntity.getEmployeeEntity().getDeptname())) {
					params.put("uploadDept","%" + customerInfoPoolEntity.getEmployeeEntity().getDeptname()+ "%");
				}
				// 公司名称
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getCompanyName())) {
					params.put("companyName","%" + customerInfoPoolEntity.getCompanyName() + "%");
				}
				// 联系人
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getContactPerson())) {
					params.put("contactPerson","%" + customerInfoPoolEntity.getContactPerson() + "%");
				}
				// 联系方式
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getContactWay())) {
					params.put("contactWay","%" + customerInfoPoolEntity.getContactWay() + "%");
				}
				// 大区
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getRegions())) {
					params.put("regions","%" + customerInfoPoolEntity.getRegions() + "%");
				}
				// 负责人
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getManagePerson())) {
					params.put("managePerson", "%" + customerInfoPoolEntity.getManagePerson() + "%");
				}
				// 详细地址
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getCompanyAddress())) {
					params.put("companyAddress","%" + customerInfoPoolEntity.getCompanyAddress()+ "%");
				}
				//分发状态
				if(!StringUtil.isEmpty(customerInfoPoolEntity.getDispenseStatus())){
					params.put("dispenseStatus", customerInfoPoolEntity.getDispenseStatus());
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 开始时间
			if (customerInfoPoolVo.getStartDate() != null) {
				params.put("startDate",sdf.format(customerInfoPoolVo.getStartDate()));
			}
			// 结束时间
			if (customerInfoPoolVo.getEndDate() != null) {
				Calendar c = Calendar.getInstance();  
				c.setTime(customerInfoPoolVo.getEndDate());  
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1); 
				params.put("endDate",sdf.format(c.getTime()));
			}
		}
		//当前用户拥有的功能信息ID集合
		Set<String> functions = currentUser.getFunctionCodes();
		List<RoleEntity> roles = roleService.getAuthedRoles(currentUser.getUserName());
		String empCode = "";
		if(currentUser.getEmpEntity() != null){
			empCode = currentUser.getEmpEntity().getEmpCode();
		}
		if(!functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL)){
			//客户管理部权限
			if(functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL_CUSTOMERMANAGE)){
				params.put("customerManage", BseConstants.YES);
			}else if(functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL_NEWSTORE)){
				//新门店研究组权限
				params.put("newStore", BseConstants.YES);
			}else{
				//是否门店
				Map<String,String> map = customerService.getIsStoreInfo(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				if(BseConstants.IS_STORE.equals(map.get("isStore"))){
					params.put("storeAuth", departmentService.getSupDeptCodeManager(currentUser.getEmpEntity().getDeptEntity().getDeptCode()).getDeptCode());
					params.put("userCode", currentUser.getUserName());
					//是否负责人
				}else if(saleChatsService.isDeptManeger(empCode)){
					//判断是大区还是事业部角色
					if(BseConstants.ROLE_BUSINESS.equals(roles.get(0).getRoleCode())){
						if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
							params.put("userCode", currentUser.getUserName());
							params.put("deptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						}
					}else if(BseConstants.ROLE_REGIONS.equals(roles.get(0).getRoleCode())){
						if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
							params.put("userCode", currentUser.getUserName());
							params.put("createUser", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						}
					}else if(BseConstants.ROLE_ROAD.equals(roles.get(0).getRoleCode())){
						//路区负责人
						DepartmentEntity dept = new DepartmentEntity();
						dept.setDeptCode(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						dept = departmentService.queryDeptByDeptCode(dept);
						params.put("userCode", currentUser.getUserName());
						params.put("road", dept.getSupdeptCode());
					}else{
						//团队经理
						params.put("termManage", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					}
				}else{
					params.put("manageUser", currentUser.getUserName());
				}
			}
		}
		//排序规则
		if(StringUtil.isEmpty(customerInfoPoolVo.getSortType())
				|| "DESC".equals(customerInfoPoolVo.getSortType())){
			params.put("timeDesc", BseConstants.YES);
		}else if("ASC".equals(customerInfoPoolVo.getSortType())){
			params.put("timeAsc", BseConstants.YES);
		}
		return customerInfoPoolMapper.queryUploadCustomer(params, rb);
	}
	
	@Override
	public List<CustomerInfoPoolEntity> queryAllUploadCustomer(
			CustomerInfoPoolVo customerInfoPoolVo) {
		Map<String, String> params = getExportParams(customerInfoPoolVo);
		return customerInfoPoolMapper.queryAllUploadCustomer(params);
	}

	private Map<String, String> getExportParams(
			CustomerInfoPoolVo customerInfoPoolVo) {
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		// 判断查询条件，模糊查询的进行处理
		if (customerInfoPoolVo != null) {
			if (customerInfoPoolVo.getCustomerInfoPoolEntity() != null) {
				CustomerInfoPoolEntity customerInfoPoolEntity = customerInfoPoolVo.getCustomerInfoPoolEntity();
				// 上传人部门
				if (customerInfoPoolEntity.getEmployeeEntity()!=null
						&& !StringUtil.isEmpty(customerInfoPoolEntity.getEmployeeEntity().getDeptname())) {
					params.put("uploadDept","%" + customerInfoPoolEntity.getEmployeeEntity().getDeptname()+ "%");
				}
				// 公司名称
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getCompanyName())) {
					params.put("companyName","%" + customerInfoPoolEntity.getCompanyName() + "%");
				}
				// 联系人
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getContactPerson())) {
					params.put("contactPerson","%" + customerInfoPoolEntity.getContactPerson() + "%");
				}
				// 联系方式
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getContactWay())) {
					params.put("contactWay","%" + customerInfoPoolEntity.getContactWay() + "%");
				}
				// 大区
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getRegions())) {
					params.put("regions","%" + customerInfoPoolEntity.getRegions() + "%");
				}
				// 负责人
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getManagePerson())) {
					params.put("managePerson", "%" + customerInfoPoolEntity.getManagePerson() + "%");
				}
				// 详细地址
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getCompanyAddress())) {
					params.put("companyAddress","%" + customerInfoPoolEntity.getCompanyAddress()+ "%");
				}
				//分发状态
				if(!StringUtil.isEmpty(customerInfoPoolEntity.getDispenseStatus())){
					params.put("dispenseStatus", customerInfoPoolEntity.getDispenseStatus());
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 开始时间
			if (customerInfoPoolVo.getStartDate() != null) {
				params.put("startDate",sdf.format(customerInfoPoolVo.getStartDate()));
			}
			// 结束时间
			if (customerInfoPoolVo.getEndDate() != null) {
				Calendar c = Calendar.getInstance();  
				c.setTime(customerInfoPoolVo.getEndDate());  
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1); 
				params.put("endDate",sdf.format(c.getTime()));
			}
		}
		//当前用户
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		//当前用户拥有的功能信息ID集合
		Set<String> functions = currentUser.getFunctionCodes();
		List<RoleEntity> roles = roleService.getAuthedRoles(currentUser.getUserName());
		String empCode = "";
		if(currentUser.getEmpEntity() != null){
			empCode = currentUser.getEmpEntity().getEmpCode();
		}
		if(!functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL)){
			//客户管理部权限
			if(functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL_CUSTOMERMANAGE)){
				params.put("customerManage", BseConstants.YES);
			}else if(functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL_NEWSTORE)){
				//新门店研究组权限
				params.put("newStore", BseConstants.YES);
			}else{
				//是否门店
				Map<String,String> map = customerService.getIsStoreInfo(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				if(BseConstants.IS_STORE.equals(map.get("isStore"))){
					params.put("storeAuth", departmentService.getSupDeptCodeManager(currentUser.getEmpEntity().getDeptEntity().getDeptCode()).getDeptCode());
					params.put("userCode", currentUser.getUserName());
					//是否负责人
				}else if(saleChatsService.isDeptManeger(empCode)){
					//判断是大区还是事业部角色
					if(BseConstants.ROLE_BUSINESS.equals(roles.get(0).getRoleCode())){
						if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
							params.put("userCode", currentUser.getUserName());
							params.put("deptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						}
					}else if(BseConstants.ROLE_REGIONS.equals(roles.get(0).getRoleCode())){
						if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
							params.put("userCode", currentUser.getUserName());
							params.put("createUser", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						}
					}else if(BseConstants.ROLE_ROAD.equals(roles.get(0).getRoleCode())){
						//路区负责人
						DepartmentEntity dept = new DepartmentEntity();
						dept.setDeptCode(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						dept = departmentService.queryDeptByDeptCode(dept);
						params.put("userCode", currentUser.getUserName());
						params.put("road", dept.getSupdeptCode());
					}else{
						//团队经理
						params.put("termManage", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					}
				}else{
					params.put("manageUser", currentUser.getUserName());
				}
			}
		}
		return params;
	}

	@Override
	public long countUploadCustomer(CustomerInfoPoolVo customerInfoPoolVo, UserEntity currentUser) {
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		// 判断查询条件，模糊查询的进行处理
		if (customerInfoPoolVo != null) {
			if (customerInfoPoolVo.getCustomerInfoPoolEntity() != null) {
				CustomerInfoPoolEntity customerInfoPoolEntity = customerInfoPoolVo.getCustomerInfoPoolEntity();
				// 上传人部门
				if (customerInfoPoolEntity.getEmployeeEntity()!=null
						&& !StringUtil.isEmpty(customerInfoPoolEntity.getEmployeeEntity().getDeptname())) {
					params.put("uploadDept","%" + customerInfoPoolEntity.getEmployeeEntity().getDeptname()+ "%");
				}
				// 公司名称
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getCompanyName())) {
					params.put("companyName","%" + customerInfoPoolEntity.getCompanyName() + "%");
				}
				// 联系人
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getContactPerson())) {
					params.put("contact","%" + customerInfoPoolEntity.getContactPerson() + "%");
				}
				// 联系方式
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getContactWay())) {
					params.put("cellphone","%" + customerInfoPoolEntity.getContactWay() + "%");
				}
				// 大区
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getRegions())) {
					params.put("regions","%" + customerInfoPoolEntity.getRegions() + "%");
				}
				// 负责人
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getManagePerson())) {
					params.put("managePerson", "%" + customerInfoPoolEntity.getManagePerson() + "%");
				}
				// 详细地址
				if (!StringUtil.isEmpty(customerInfoPoolEntity.getCompanyAddress())) {
					params.put("companyAddress","%" + customerInfoPoolEntity.getCompanyAddress()+ "%");
				}
				//分发状态
				if(!StringUtil.isEmpty(customerInfoPoolEntity.getDispenseStatus())){
					params.put("dispenseStatus", customerInfoPoolEntity.getDispenseStatus());
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			// 开始时间
			if (customerInfoPoolVo.getStartDate() != null) {
				params.put("startDate",sdf.format(customerInfoPoolVo.getStartDate()));
			}
			// 结束时间
			if (customerInfoPoolVo.getEndDate() != null) {
				Calendar c = Calendar.getInstance();  
				c.setTime(customerInfoPoolVo.getEndDate());  
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1); 
				params.put("endDate",sdf.format(c.getTime()));
			}
		}
		//当前用户拥有的功能信息ID集合
		Set<String> functions = currentUser.getFunctionCodes();
		List<RoleEntity> roles = roleService.getAuthedRoles(currentUser.getUserName());
		String empCode = "";
		if(currentUser.getEmpEntity() != null){
			empCode = currentUser.getEmpEntity().getEmpCode();
		}
		if(!functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL)){
			//客户管理部权限
			if(functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL_CUSTOMERMANAGE)){
				params.put("customerManage", BseConstants.YES);
			}else if(functions.contains(BseConstants.ALLDATA_CUSTOMERPOOL_NEWSTORE)){
				//新门店研究组权限
				params.put("newStore", BseConstants.YES);
			}else{
				//是否门店
				Map<String,String> map = customerService.getIsStoreInfo(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				if(BseConstants.IS_STORE.equals(map.get("isStore"))){
					params.put("storeAuth", departmentService.getSupDeptCodeManager(currentUser.getEmpEntity().getDeptEntity().getDeptCode()).getDeptCode());
					params.put("userCode", currentUser.getUserName());
					//是否负责人
				}else if(saleChatsService.isDeptManeger(empCode)){
					//判断是大区还是事业部角色
					if(BseConstants.ROLE_BUSINESS.equals(roles.get(0).getRoleCode())){
						if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
							params.put("userCode", currentUser.getUserName());
							params.put("deptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						}
					}else if(BseConstants.ROLE_REGIONS.equals(roles.get(0).getRoleCode())){
						if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
							params.put("userCode", currentUser.getUserName());
							params.put("createUser", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						}
					}else if(BseConstants.ROLE_ROAD.equals(roles.get(0).getRoleCode())){
						//路区负责人
						DepartmentEntity dept = new DepartmentEntity();
						dept.setDeptCode(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
						dept = departmentService.queryDeptByDeptCode(dept);
						params.put("userCode", currentUser.getUserName());
						params.put("road", dept.getSupdeptCode());
					}else{
						//团队经理
						params.put("termManage", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					}
				}else{
					params.put("manageUser", currentUser.getUserName());
				}
			}
		}
		return customerInfoPoolMapper.countUploadCustomer(params);
	}

	/**
	 * 判断当前客户信息是否允许新增或修改
	 * 
	 * @param uploadCustomerEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-27
	 * @update
	 */
	private boolean isAllowCustomer(CustomerInfoPoolEntity uploadCustomerEntity) {
        return customerInfoPoolMapper.isAllowCustomer(uploadCustomerEntity) <= 0;
	}

	@Override
	@Transactional
	public void updateBackReason(CustomerInfoPoolEntity customerInfoPoolEntity, UserEntity currentUser) {
		customerInfoPoolEntity.setDispenseStatus(BseConstants.DISPENSE_STATU_BACK);
		customerInfoPoolEntity.setModifyUser(currentUser.getUserName());
		customerInfoPoolMapper.updateBackReason(customerInfoPoolEntity);
		
	}

	@Override
	@Transactional
	public void updateDispenseStatus(CustomerInfoPoolEntity customerInfoPoolEntity) {
		customerInfoPoolEntity.setModifyUser(UserContext.getCurrentUser().getUserName());
		customerInfoPoolMapper.updateDispenseStatus(customerInfoPoolEntity);
	}

	@Override
	public List<DistrictEntity> queryDistrictList(DistrictEntity districtEntity) {
		if(districtEntity == null){
			throw new BusinessException(CustomerInfoPoolException.PARAM_NULL);
		}
		return customerInfoPoolMapper.getDistrictList(districtEntity);
	}

	@Override
	public DistrictEntity queryDistrictByNameOrCode(
			DistrictEntity districtEntity) {
		if(districtEntity == null){
			throw new BusinessException(CustomerInfoPoolException.PARAM_NULL);
		}
		return customerInfoPoolMapper.getDistrictByNameOrCode(districtEntity);
	}

	@Override
	public CustomerInfoPoolEntity getCustomerInfoPoolById(String id) {
		if(StringUtil.isEmpty(id)){
			throw new BusinessException(CustomerInfoPoolException.PARAM_NULL);
		}
		CustomerInfoPoolEntity customerInfoPoolEntity  = customerInfoPoolMapper.getCustomerInfoPoolById(id);
		if(customerInfoPoolEntity == null){
			throw new BusinessException(CustomerInfoPoolException.RESULT_NULL);
		}
		return customerInfoPoolEntity;
	}
	
	
	/**
	 * @param point
	 * @param entities
	 * @return
	 */
	private String[] getCustomerPerson(java.awt.geom.Point2D.Double point,
			List<UserScopeEntity> entities) {
		String[] khManage = new String[2];
		if(!entities.isEmpty() && entities.size() > 0){
			List<UserScopeEntity> userScopes = new ArrayList<UserScopeEntity>();
			for (UserScopeEntity scopeEntity : entities) {
				if(null != scopeEntity.getPloygongeo() && !"".equals(scopeEntity.getPloygongeo())){
					String[] points = scopeEntity.getPloygongeo().split(";");
					List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
					for(int j=0;j<points.length;j++){
						String[] xy  = points[j].split(" ");
						Point2D.Double double1 = new Point2D.Double(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
						polygon.add(double1);
					}
					if(polygon.size() > 0){
						boolean temp = PolygonUtil.checkWithJdkGeneralPath(point, polygon);
						if(temp)//添加所属的用户范围
							userScopes.add(scopeEntity);
					}else
						continue;
				}
			}
			
			if(userScopes.size() > 0)
			{
				//只找到一个用户就直接return
				if(userScopes.size() == 1)
					return new String[]{userScopes.get(0).getUser_id(),userScopes.get(0).getUser_name()};
				//门店 用户
				List<String> md = new ArrayList<String>();
				List<UserScopeEntity> mdkeyValue = new ArrayList<UserScopeEntity>();
				//客户经理 用户
				List<String> xs = new ArrayList<String>();
				List<UserScopeEntity> xskeyValue = new ArrayList<UserScopeEntity>();
				for(int j = 0;j<userScopes.size();j++){
					if(userScopes.get(j).getRole_code().equals("1006")){
						md.add(userScopes.get(j).getUser_id());
						mdkeyValue.add(userScopes.get(j));
					}
					else if(userScopes.get(j).getRole_code().equals("1007")){
						xs.add(userScopes.get(j).getUser_id());
						xskeyValue.add(userScopes.get(j));
					}
				}
				//证明有门店用户，就不考虑客户经理，优先给门店产值高的门店
				if(md.size() > 0){
					String users = userScopeMapper.getMaxStoresOutputUsers(md);
					if(null != users && !"".equals(users)){
						khManage = users.split(";");
						return khManage;
					}else 
						return new String[]{mdkeyValue.get(0).getUser_id(),mdkeyValue.get(0).getUser_name()};
				}else{//没有门店就按照客户经理负责的客户产值高的人员分配
					String users = userScopeMapper.getMaxSalesManagerOutputUsers(xs);
					if(null != users && !"".equals(users)){
						khManage = users.split(";");
						return khManage;
					}else 
						return new String[]{xskeyValue.get(0).getUser_id(),xskeyValue.get(0).getUser_name()};
				}
			}else
				return null;
		}
		return null;
	}

	@Override
	public void updateCustomeriSpotential(String id) {
		if(StringUtil.isEmpty(id)){
			throw new BusinessException(CustomerInfoPoolException.PARAM_NULL);
		}
		//customerInfoPoolMapper.updateCustomeriSpotential(id);
	}

	@Override
	public List<BseDeptEntity> queryDepts(String supDeptCode) {
		List<BseDeptEntity> list;
		if(StringUtil.isEmpty(supDeptCode)){
			list = customerInfoPoolMapper.getBusinessDepts();
		}else{
			list = customerInfoPoolMapper.getRegionsDepts(supDeptCode);
		}
		return list;
	}

	@Override
	@Transactional
	public void transferCustomer(UserEntity currentUser,TransferCustomerVO transferCustomerVO) {
		if(transferCustomerVO == null || transferCustomerVO.getIds() == null 
				|| StringUtil.isEmpty(transferCustomerVO.getNewManagerCode())){
			throw new CustomerInfoPoolException(CustomerInfoPoolException.PARAM_NULL);
		}
		//先备份
		customerInfoPoolMapper.backupSaleTransferCustomer(transferCustomerVO.getIds());
		//执行转让
		Map<String,Object> params = new HashMap<String,Object>();
		//新负责人
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setEmpCode(transferCustomerVO.getNewManagerCode());
		employeeEntity = iEmployeeService.queryEmployeeByEmpcode(employeeEntity);
		if(employeeEntity == null){
			throw new EmployeeException(EmployeeException.USER_NULL);
		}
		params.put("ids", transferCustomerVO.getIds());
		params.put("userName", currentUser.getUserName());
		params.put("account", employeeEntity.getAccount());
		params.put("empName", employeeEntity.getEmpName());
		customerInfoPoolMapper.updateTransferCustomerManager(params);
	}

	@Override
	public long countAllUploadCustomer(CustomerInfoPoolVo customerInfoPoolVo) {
		Map<String, String> params = getExportParams(customerInfoPoolVo);
		return customerInfoPoolMapper.countAllUploadCustomer(params);
	}
}
