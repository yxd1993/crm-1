package com.hoau.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.IReportDataService;
import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;
import com.hoau.crm.module.customer.server.dao.CustomerMapper;
import com.hoau.crm.module.customer.server.dao.ReportDataMapper;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 报表数据SERVICE
 * @author: 何斌
 * @create: 2015年6月2日 下午5:59:15
 */
@Service
public class ReportDataService implements IReportDataService{
	
	@Resource
	private ReportDataMapper reportDataMapper;
	
	@Resource
	private CustomerMapper customerMapper;

	@Override
	public List<ReportDataEntity> queryCustomerIndustryData() {
		Map<String,String> params = new HashMap<String, String>();
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
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
					params.put("managerPerson", currentUser.getEmpEntity().getEmpCode());
				}
			}
		}
		return reportDataMapper.queryCustomerIndustryData(params);
	}

	@Override
	public List<ReportDataEntity> queryCustomerNatureData() {
		List<ReportDataEntity> lists = new ArrayList<ReportDataEntity>();
		Map<String,String> params = new HashMap<String, String>();
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
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
					params.put("managerPerson", currentUser.getEmpEntity().getEmpCode());
				}
			}
		}
		//根据客户性质统计客户总数
		lists.addAll(reportDataMapper.queryCustomerNatureData(params));
		//客户总数
		lists.add(reportDataMapper.countTotalCustomer(params));
		return lists;
	}

	@Override
	public long countAllCustomer() {
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functions = currentUser.getFunctionCodes();
		Map<String,String> params = new HashMap<String, String>();
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
					params.put("managerPerson", currentUser.getEmpEntity().getEmpCode());
				}
			}
		}
		return reportDataMapper.countAllCustomer(params);
	}

}
