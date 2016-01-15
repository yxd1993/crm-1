package com.hoau.crm.module.bse.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IDataDictionaryValueService;
import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.BseDepartmentBean;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.DepartmentException;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;
import com.hoau.crm.module.bse.server.dao.DepartmentMapper;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 组织信息SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DepartmentService implements IDepartmentService {

	@Resource
	private DepartmentMapper departmentMapper;
	
	@Resource
	private IDataDictionaryValueService iDataDictionaryValueService;

	@Override
	public List<DepartmentEntity> queryChildDeptList(DepartmentEntity dept) {
		// 判断父节点是否为空
		if (dept == null || dept.getParentDeptEntity() == null
				|| StringUtil.isEmpty(dept.getParentDeptEntity().getDeptCode())) {
			throw new DepartmentException(DepartmentException.PARENT_NODE_NULL);
		} else {
			return departmentMapper.getChildDeptList(dept);
		}
	}

	@Override
	public List<DepartmentEntity> queryDeptList(DepartmentVo departmentVo,
			RowBounds rb, UserEntity currentUser) {
		// 判断分页参数是否为空
		if (rb == null) {
			throw new DepartmentException(DepartmentException.RB_NULL);
		}
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		if (departmentVo != null
				&& !StringUtil.isEmpty(departmentVo.getSelectorParam())) {
			params.put("selectorParam", "%" + departmentVo.getSelectorParam()
					+ "%");
		}
		// 当前用户
		//UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		// 判断是否拥有全国门店的数据
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.FUNCTION_AUTH_ALLSTORE)){
			if (currentUser != null
					&& currentUser.getEmpEntity() != null
					&& currentUser.getEmpEntity().getDeptEntity() != null
					&& !StringUtil.isEmpty(currentUser.getEmpEntity()
							.getDeptEntity().getDeptCode())) {
				// 客户经理\团队经理特殊处理
				if(currentUser.getEmpEntity().getJobname().equals(BseConstants.MANAGENAME) || currentUser.getEmpEntity().getJobname().equals(BseConstants.TEAMMANNAME)){
					DepartmentEntity dept = getSupDeptCodeManager(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					// 查找当前组织的大区
					if(dept != null){
						params.put("deptCode", dept.getDeptCode());
					} else {
						params.put("deptCode", currentUser.getEmpEntity().getDeptEntity()
								.getDeptCode());
					}
				} else {
					params.put("deptCode", currentUser.getEmpEntity().getDeptEntity()
							.getDeptCode());
				}
			} else {
				return new ArrayList<DepartmentEntity>();
			}
		}
		return departmentMapper.getDeptList(params, rb);
	}

	@Override
	public long countDept(DepartmentVo departmentVo, UserEntity currentUser) {
		// 查询参数
		Map<String, String> params = new HashMap<String, String>();
		if (departmentVo != null
				&& !StringUtil.isEmpty(departmentVo.getSelectorParam())) {
			params.put("selectorParam", "%" + departmentVo.getSelectorParam()
					+ "%");
		}
		// 当前用户
		//UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		// 判断是否拥有全国门店的数据
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.FUNCTION_AUTH_ALLSTORE)){
			if (currentUser != null
					&& currentUser.getEmpEntity() != null
					&& currentUser.getEmpEntity().getDeptEntity() != null
					&& !StringUtil.isEmpty(currentUser.getEmpEntity()
							.getDeptEntity().getDeptCode())) {
				// 客户经理\团队经理特殊处理
				if(currentUser.getEmpEntity().getJobname().equals(BseConstants.MANAGENAME) || currentUser.getEmpEntity().getJobname().equals(BseConstants.TEAMMANNAME)){
					DepartmentEntity dept = getSupDeptCodeManager(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					// 查找当前组织的大区
					if(dept != null){
						params.put("deptCode", dept.getDeptCode());
					} else {
						params.put("deptCode", currentUser.getEmpEntity().getDeptEntity()
								.getDeptCode());
					}
				} else {
					params.put("deptCode", currentUser.getEmpEntity().getDeptEntity()
							.getDeptCode());
				}
			} else {
				return 0;
			}
		}
		return departmentMapper.countDept(params);
	}

	@Override
	public DepartmentEntity queryDeptByDeptCode(DepartmentEntity dept) {
		// 判断分页参数是否为空
		if (dept == null) {
			throw new DepartmentException(DepartmentException.DEPT_CODE_NULL);
		}
		// 这个地方是用logiscode查询时，可能会出现多条，只有一条是有效的
		List<DepartmentEntity> deptList = departmentMapper.getDeptByDeptCode(dept);
		if(deptList != null && deptList.size() > 0){
			return deptList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public DepartmentVo queryDeptSuperiorDept(DepartmentEntity deptEntity, UserEntity currentUser) {
		DepartmentVo departmentVo = new DepartmentVo();
		if(deptEntity == null || StringUtil.isEmpty(deptEntity.getLogistCode())){
			throw new DepartmentException(DepartmentException.DEPT_CODE_NULL);
		}
		// 路区
		DepartmentEntity roadAreaDept = this.queryDeptByDeptCode(deptEntity);
		if (roadAreaDept != null && roadAreaDept.getParentDeptEntity() != null) {
			departmentVo = new DepartmentVo();
			departmentVo.setTierCode(roadAreaDept.getDeptCode());
			departmentVo.setTierName(roadAreaDept.getDeptName());
			departmentVo.setTierEmpCode(roadAreaDept.getManagerId());
			departmentVo.setTierEmpName(roadAreaDept.getLastName());
			departmentVo.setRoadAreaCode(roadAreaDept.getParentDeptEntity()
					.getDeptCode());
			departmentVo.setRoadAreaName(roadAreaDept.getParentDeptEntity()
					.getDeptName());
			departmentVo.setRoadEmpCode(roadAreaDept.getParentDeptEntity().getManagerId());
			departmentVo.setRoadEmpName(roadAreaDept.getParentDeptEntity().getLastName());
			deptEntity.setLogistCode(null);
			deptEntity.setDeptCode(roadAreaDept.getParentDeptEntity()
					.getDeptCode());
			// 大区
			DepartmentEntity regionsDept = this.queryDeptByDeptCode(deptEntity);
			if (regionsDept != null
					&& regionsDept.getParentDeptEntity() != null) {
				departmentVo.setRegionsCode(regionsDept.getParentDeptEntity()
						.getDeptCode());
				departmentVo.setRegionsName(regionsDept.getParentDeptEntity()
						.getDeptName());
				departmentVo.setRegionsEmpCode(regionsDept.getParentDeptEntity().getManagerId());
				departmentVo.setRegionsEmpName(regionsDept.getParentDeptEntity().getLastName());
				deptEntity.setDeptCode(regionsDept.getParentDeptEntity()
						.getDeptCode());
				// 事业部
				DepartmentEntity businessUnitDept = this
						.queryDeptByDeptCode(deptEntity);
				if (businessUnitDept != null
						&& businessUnitDept.getParentDeptEntity() != null) {
					departmentVo.setBusinessUnitCode(businessUnitDept
							.getParentDeptEntity().getDeptCode());
					departmentVo.setBusinessUnitName(businessUnitDept
							.getParentDeptEntity().getDeptName());
					departmentVo.setBusinessUnitEmpCode(businessUnitDept.getParentDeptEntity().getManagerId());
					departmentVo.setBusinessUnitEmpName(businessUnitDept.getParentDeptEntity().getLastName());
				}
			}
		}
		// 总裁与副总
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode(BseConstants.ENTOURAGE);
		List<DataDictionaryValueEntity> dataDictionaryEntity = iDataDictionaryValueService.queryDataDictionaryValueByEntity(entity, 0, 10);
		if(dataDictionaryEntity != null && dataDictionaryEntity.size() > 0){
			for(int i=0; i<dataDictionaryEntity.size(); i++){
				DataDictionaryValueEntity data = dataDictionaryEntity.get(i);
				if(data.getValueCode().equals("1")){
					departmentVo.setCeoEmpCode(data.getValueName());
				}else if(data.getValueCode().equals("2")){
					departmentVo.setCeoEmpName(data.getValueName());
				}else if(data.getValueCode().equals("3")){
					departmentVo.setOdEmpCode(data.getValueName());
				}else if(data.getValueCode().equals("4")){
					departmentVo.setOdEmpName(data.getValueName());
				}
			}
		}
		// 团队经理
		if(currentUser != null){
			String jobName = currentUser.getEmpEntity().getJobname();
			if(BseConstants.MANAGENAME.equals(jobName) || BseConstants.TEAMMANNAME.equals(jobName)){
				DepartmentEntity dEntity = new DepartmentEntity();
				dEntity.setDeptCode(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				// 路区
				DepartmentEntity termManagerDept = this.queryDeptByDeptCode(dEntity);
				if(departmentVo.getRegionsEmpCode().equals(termManagerDept.getManagerId())){
					departmentVo.setTeamManagerCode(null);
					departmentVo.setTeamManagerName(null);
				}else{
					departmentVo.setTeamManagerCode(termManagerDept.getManagerId());
					departmentVo.setTeamManagerName(termManagerDept.getLastName());
				}
			}
		}
		return departmentVo;
	}
	
	@Override
	public DepartmentVo queryDeptSuperiorDeptByCurrUser(Map<String, String> customerInfo, UserEntity currentUser) {
		DepartmentEntity dEntity = new DepartmentEntity();
		DepartmentEntity lEntity = new DepartmentEntity();
		DepartmentVo departmentVo = new DepartmentVo();
		if(currentUser != null && currentUser.getEmpEntity() != null && currentUser.getEmpEntity().getDeptEntity() != null){
			String jobName = currentUser.getEmpEntity().getJobname();
			// 团队经理
			if(BseConstants.TEAMMANNAME.equals(jobName)){
				// 客户经理
				if(!StringUtil.isEmpty(customerInfo.get("customerManageEmpCode"))){
					departmentVo.setSaleManDisplayName(BseConstants.ACCOMPANY_PEOP_SALEMAN);
					departmentVo.setSaleManEmpCode(customerInfo.get("customerManageEmpCode"));
					departmentVo.setSaleManEmpName(customerInfo.get("customerManageEmpName"));
				}
				// 大区总
				lEntity.setLogistCode(customerInfo.get("logistCode"));
				DepartmentEntity roadAreaDept = this.queryDeptByDeptCode(lEntity);
				if (roadAreaDept != null && roadAreaDept.getParentDeptEntity() != null){
					dEntity.setDeptCode(roadAreaDept.getParentDeptEntity()
							.getDeptCode());
					// 大区
					DepartmentEntity regionsDept = this.queryDeptByDeptCode(dEntity);
					if(regionsDept.getParentDeptEntity() != null){
						departmentVo.setRegionsDisplayName(BseConstants.ACCOMPANY_PEOP_REGIONS);
						departmentVo.setRegionsEmpCode(regionsDept.getParentDeptEntity().getManagerId());
						departmentVo.setRegionsEmpName(regionsDept.getParentDeptEntity().getLastName());
						dEntity.setDeptCode(regionsDept.getParentDeptEntity()
								.getDeptCode());
						// 事业部总
						DepartmentEntity businessUnitDept = this
								.queryDeptByDeptCode(dEntity);
						if (businessUnitDept != null
								&& businessUnitDept.getParentDeptEntity() != null) {
							departmentVo.setBusinessUnitDisplayName(BseConstants.ACCOMPANY_PEOP_BUSSINESS);
							departmentVo.setBusinessUnitEmpCode(businessUnitDept.getParentDeptEntity().getManagerId());
							departmentVo.setBusinessUnitEmpName(businessUnitDept.getParentDeptEntity().getLastName());
						}
					}
				}
			// 客户经理
			} else if(BseConstants.MANAGENAME.equals(jobName)){
				dEntity.setDeptCode(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				DepartmentEntity termManagerDept = this.queryDeptByDeptCode(dEntity);
				//  团队经理\大区总
				if(termManagerDept.getParentDeptEntity() != null){
					departmentVo.setTeamManagerDisplayName(BseConstants.ACCOMPANY_PEOP_TEAMMAN);
					departmentVo.setTeamManagerCode(termManagerDept.getManagerId());
					departmentVo.setTeamManagerName(termManagerDept.getLastName());
					// 大区总
					lEntity.setLogistCode(customerInfo.get("logistCode"));
					DepartmentEntity roadAreaDept = this.queryDeptByDeptCode(lEntity);
					if (roadAreaDept != null && roadAreaDept.getParentDeptEntity() != null){
						dEntity.setDeptCode(roadAreaDept.getParentDeptEntity()
								.getDeptCode());
						// 大区
						DepartmentEntity regionsDept = this.queryDeptByDeptCode(dEntity);
						departmentVo.setRegionsDisplayName(BseConstants.ACCOMPANY_PEOP_REGIONS);
						departmentVo.setRegionsEmpCode(regionsDept.getParentDeptEntity().getManagerId());
						departmentVo.setRegionsEmpName(regionsDept.getParentDeptEntity().getLastName());
					}
				}
			} else {
				// 不是团队经理与客户经理
				BseDepartmentBean bseDept = departmentMapper.queryBseDeptByDeptcode(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				if(bseDept != null){
					// 门店
					if(bseDept.getIsStore().equals(BseConstants.ACTIVE)){
						// 此处之所以用logistCode，是因为团队经理部门性质为门店，导致路区、大区取值错误
						lEntity.setLogistCode(customerInfo.get("logistCode"));
						// 路区
						DepartmentEntity roadAreaDept = this.queryDeptByDeptCode(lEntity);
						if (roadAreaDept != null && roadAreaDept.getParentDeptEntity() != null) {
							departmentVo.setRoadDisplayName(BseConstants.ACCOMPANY_PEOP_ROAD);
							departmentVo.setRoadEmpCode(roadAreaDept.getParentDeptEntity().getManagerId());
							departmentVo.setRoadEmpName(roadAreaDept.getParentDeptEntity().getLastName());
							dEntity.setDeptCode(roadAreaDept.getParentDeptEntity()
									.getDeptCode());
							// 大区
							DepartmentEntity regionsDept = this.queryDeptByDeptCode(dEntity);
							if (regionsDept != null
									&& regionsDept.getParentDeptEntity() != null) {
								departmentVo.setRegionsDisplayName(BseConstants.ACCOMPANY_PEOP_REGIONS);
								departmentVo.setRegionsEmpCode(regionsDept.getParentDeptEntity().getManagerId());
								departmentVo.setRegionsEmpName(regionsDept.getParentDeptEntity().getLastName());
							}
						}
					// 路区
					} else if(bseDept.getIsRoad().equals(BseConstants.ACTIVE)){
						// 门店经理
						if(!StringUtil.isEmpty(customerInfo.get("customerManageEmpCode"))){
							// 判断门店经理经理是否等于当前路区经理
							if(!currentUser.getEmpEntity().getEmpCode().equals(customerInfo.get("customerManageEmpCode"))){
								// 门店经理
								departmentVo.setTierDisplayName(BseConstants.ACCOMPANY_PEOP_TIER);
								departmentVo.setTierEmpCode(customerInfo.get("customerManageEmpCode"));
								departmentVo.setTierEmpName(customerInfo.get("customerManageEmpName"));
							}
						}
						// 大区
						lEntity.setLogistCode(customerInfo.get("logistCode"));
						DepartmentEntity roadAreaDept = this.queryDeptByDeptCode(lEntity);
						if (roadAreaDept != null && roadAreaDept.getParentDeptEntity() != null){
							dEntity.setDeptCode(roadAreaDept.getParentDeptEntity()
									.getDeptCode());
							DepartmentEntity regionsDept = this.queryDeptByDeptCode(dEntity);
							if (regionsDept != null && regionsDept.getParentDeptEntity() != null) {
								departmentVo.setRegionsDisplayName(BseConstants.ACCOMPANY_PEOP_REGIONS);
								departmentVo.setRegionsEmpCode(regionsDept.getParentDeptEntity().getManagerId());
								departmentVo.setRegionsEmpName(regionsDept.getParentDeptEntity().getLastName());
								dEntity.setDeptCode(regionsDept.getParentDeptEntity()
										.getDeptCode());
								// 事业部
								DepartmentEntity businessUnitDept = this.queryDeptByDeptCode(dEntity);
								if (businessUnitDept != null
										&& businessUnitDept.getParentDeptEntity() != null) {
									departmentVo.setBusinessUnitDisplayName(BseConstants.ACCOMPANY_PEOP_BUSSINESS);
									departmentVo.setBusinessUnitEmpCode(businessUnitDept.getParentDeptEntity().getManagerId());
									departmentVo.setBusinessUnitEmpName(businessUnitDept.getParentDeptEntity().getLastName());
								}
							}
						}
					// 大区总或事业部总
					} else if(bseDept.getIsRegion().equals(BseConstants.ACTIVE) || bseDept.getIsBusiness().equals(BseConstants.ACTIVE)){
						// 门店\路区
						lEntity.setLogistCode(customerInfo.get("logistCode"));
						DepartmentEntity roadAreaDept = this.queryDeptByDeptCode(lEntity);
						if (roadAreaDept != null && roadAreaDept.getParentDeptEntity() != null) {
							// 门店经理
							if(!StringUtil.isEmpty(customerInfo.get("customerManageEmpCode"))){
								departmentVo.setTierDisplayName(BseConstants.ACCOMPANY_PEOP_TIER);
								departmentVo.setTierEmpCode(customerInfo.get("customerManageEmpCode"));
								departmentVo.setTierEmpName(customerInfo.get("customerManageEmpName"));
							}
							departmentVo.setRoadDisplayName(BseConstants.ACCOMPANY_PEOP_ROAD);
							departmentVo.setRoadEmpCode(roadAreaDept.getParentDeptEntity().getManagerId());
							departmentVo.setRoadEmpName(roadAreaDept.getParentDeptEntity().getLastName());
							dEntity.setDeptCode(roadAreaDept.getParentDeptEntity()
									.getDeptCode());
							// 大区
							DepartmentEntity regionsDept = this.queryDeptByDeptCode(dEntity);
							if (regionsDept != null
									&& regionsDept.getParentDeptEntity() != null) {
								departmentVo.setRegionsDisplayName(BseConstants.ACCOMPANY_PEOP_REGIONS);
								departmentVo.setRegionsEmpCode(regionsDept.getParentDeptEntity().getManagerId());
								departmentVo.setRegionsEmpName(regionsDept.getParentDeptEntity().getLastName());
								dEntity.setDeptCode(regionsDept.getParentDeptEntity()
										.getDeptCode());
								// 事业部
								DepartmentEntity businessUnitDept = this
										.queryDeptByDeptCode(dEntity);
								if (businessUnitDept != null
										&& businessUnitDept.getParentDeptEntity() != null) {
									departmentVo.setBusinessUnitDisplayName(BseConstants.ACCOMPANY_PEOP_BUSSINESS);
									departmentVo.setBusinessUnitEmpCode(businessUnitDept.getParentDeptEntity().getManagerId());
									departmentVo.setBusinessUnitEmpName(businessUnitDept.getParentDeptEntity().getLastName());
								}
							}
						}
						// 总裁与副总
						DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
						entity.setTermsCode(BseConstants.ENTOURAGE);
						List<DataDictionaryValueEntity> dataDictionaryEntity = iDataDictionaryValueService.queryDataDictionaryValueByEntity(entity, 0, 10);
						if(dataDictionaryEntity != null && dataDictionaryEntity.size() > 0){
							for(int i=0; i<dataDictionaryEntity.size(); i++){
								DataDictionaryValueEntity data = dataDictionaryEntity.get(i);
								if(data.getValueCode().equals("1")){
									departmentVo.setCeoEmpCode(data.getValueName());
								}else if(data.getValueCode().equals("2")){
									departmentVo.setCeoEmpName(data.getValueName());
								}else if(data.getValueCode().equals("3")){
									departmentVo.setOdEmpCode(data.getValueName());
								}else if(data.getValueCode().equals("4")){
									departmentVo.setOdEmpName(data.getValueName());
								}
								departmentVo.setOdDisplayName(BseConstants.ACCOMPANY_PEOP_OD);
								departmentVo.setCeoDisplayName(BseConstants.ACCOMPANY_PEOP_CEO);
							}
						}
					}
				}
			}
		}
		return departmentVo;
	}
	
	/**
	 * 递归获得大区
	 * 
	 * @param supDeptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月9日
	 * @update 
	 */
	public DepartmentEntity getSupDeptCodeManager(String deptCode){
		List<DepartmentEntity> supOrgBeanList = departmentMapper.getSimpleDeptByDeptCode(deptCode);
		if(supOrgBeanList != null && supOrgBeanList.size() > 0){
			if(StringUtil.isEmpty(supOrgBeanList.get(0).getIsRegion()) || supOrgBeanList.get(0).getIsRegion().equals(BseConstants.INACTIVE)){
				return getSupDeptCodeManager(supOrgBeanList.get(0).getSupdeptCode());
			} else {
				return supOrgBeanList.get(0);
			}
		} else {
			return null;
		}
	}

}
