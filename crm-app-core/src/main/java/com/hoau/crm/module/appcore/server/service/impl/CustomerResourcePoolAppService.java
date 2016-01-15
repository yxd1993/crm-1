/**
 * 
 */
package com.hoau.crm.module.appcore.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.ICustomerResourcePoolAppService;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerAppVo;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.ICustomerResourcePoolService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerResourcePoolEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.customer.server.dao.CustomerResourcePoolMapper;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 资源客户 restful 接口实现
 * 
 * @author 丁勇
 * @date 2015年10月21日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerResourcePoolAppService implements ICustomerResourcePoolAppService {
	@Resource
	CustomerResourcePoolMapper customerResourcePoolMapper;
	
	@Resource
	ICustomerResourcePoolService iCustomerResourcePoolService;
	
	@Resource
	ILoginService iLoginService;
	
	@Override
	public ResponseBaseEntity<CustomerAppVo> queryCustomerResourcePool(
			CustomerAppVo customerVo, String loginName) {
		if(StringUtils.isEmpty(customerVo.getStart())||StringUtils.isEmpty(customerVo.getLimit())){
			throw new CustomerException(CustomerException.RB_NULL);
		}
		RowBounds rb = new RowBounds(customerVo.getStart(),customerVo.getLimit());
		Map<String,String> map = new HashMap<String,String>();
		//当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		//功能id集合
		Set<String> functions  = currentUser.getFunctionCodes();
		// 模糊查询参数
		if (!StringUtil.isEmpty(customerVo.getSelectorParam())) {
			//模糊查询内容
			String selectorParam = customerVo.getSelectorParam();
			if(customerVo.getSelectorType()==0){
				//旧版本兼容
				map.put("selectorParam", customerVo.getSelectorParam());
				// 如果包含中文，则查询企业名称、联系人
				if(BseConstants.isContainsChinese(selectorParam)){
					map.put("enterpriseName", "%" + selectorParam+ "%");
					// 如果全是拼音，则搜索企业全称首字母
				} else if(BseConstants.isAllString(selectorParam)){
					map.put("enterpriseShortName", "%" + selectorParam+ "%");
					// 否则为搜索CRM账号
				} else {
					map.put("accountCode", selectorParam);
				}
			}else{
				//模糊查询类型(1-客户名称,2-迪辰账号,3-CRM账号,4-手机号,5-座机号,6-联系人姓名,7-客户地址)
				int selectorType = customerVo.getSelectorType();
				switch (selectorType) {
				case 1: // 如果包含中文，则查询企业名称、联系人
					if(BseConstants.isContainsChinese(selectorParam)){
						map.put("enterpriseName", "%" + selectorParam+ "%");
					} else if(BseConstants.isAllString(selectorParam)){
						// 如果全是拼音，则搜索企业全称首字母
						map.put("enterpriseShortName", "%" + selectorParam+ "%");
					}
					break;
				case 2: map.put("dcAccount", selectorParam);
					break;
				case 3: map.put("accountCode", selectorParam);
					break;
				case 4: map.put("cellPhone", selectorParam);
					break;
				case 5: map.put("telephone", selectorParam);
					break;
				case 6: map.put("contactName", "%" + selectorParam+ "%");
					break;
				case 7: map.put("address", "%" + selectorParam+ "%");
					break;
				}
			}
		}
		//数据权限
		if(!functions.contains(BseConstants.CUSTOMERRESOURCEPOOL_ALLDATA)){
			String deptCode = currentUser.getEmpEntity().getDeptEntity().getDeptCode();
			//查询大区编码
			String regionsCode = customerResourcePoolMapper.getRegionsCode(deptCode);
			if(StringUtil.isEmpty(regionsCode)){
				map.put("deptCode", deptCode);
			}else{
				map.put("deptCode", regionsCode);
			}
		}
		//客户性质
		if(!StringUtil.isEmpty(customerVo.getAccountType())){
			map.put("accountType", customerVo.getAccountType());
		}
		//排序
		if(StringUtil.isEmpty(customerVo.getSortTypeSub())
				|| "DESC".equals(customerVo.getSortTypeSub())){
			map.put("timeDesc", BseConstants.YES);
		}else if("ASC".equals(customerVo.getSortTypeSub())){
			map.put("timeAsc", BseConstants.YES);
		}
		//查询总数
		long resCount = customerResourcePoolMapper.countCustomerResourcePool(map);
		
		//查询结果
		List<CustomerResourcePoolEntity> resList = customerResourcePoolMapper.getCustomerResourcePools(map,rb);
		//返回结果
		CustomerAppVo vo = new CustomerAppVo();
		vo.setCustomerResourcePoolList(resList);
		vo.setTotalCount(resCount);
		ResponseBaseEntity<CustomerAppVo>  result = new ResponseBaseEntity<CustomerAppVo>();
		result.setResult(vo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<Long> claimCustomerResourcePool(
			CustomerAppVo customerVo, String loginName) {
		if(StringUtils.isEmpty(customerVo.getIds())){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		ResponseBaseEntity<Long> res = new ResponseBaseEntity<Long>();
		res.setResult(iCustomerResourcePoolService.claimCustomerResourcePool(customerVo.getIds(), currentUser));
		res.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return res;
	}

}
