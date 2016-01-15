package com.hoau.crm.module.sales.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.sales.api.server.service.ISignService;
import com.hoau.crm.module.sales.server.dao.SignMapper;
import com.hoau.crm.module.sales.shared.exception.SignException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 客户签到信息SERVICE
 * 
 * @author: 潘强
 * @create: 2015年7月21日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SignService implements ISignService {

	@Resource
	private SignMapper signMapper;

	@Override
	public List<SignEntity> getSign(SignEntity sign, RowBounds rb) {
		if (rb == null) {
			throw new SignException(SignException.QUERY_SIGN_RB_NULL);
		}
		if (sign == null) {
			throw new SignException(SignException.QUERY_SIGN_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(sign.getCustomerName())) {
			params.put("customerName", "%" + sign.getCustomerName() + "%");
		}
		if (!StringUtil.isEmpty(sign.getSignAddress())) {
			params.put("signAddress", "%" + sign.getSignAddress() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (sign.getCreateDate() != null) {
			params.put("createDate", sdf.format(sign.getCreateDate()));
		}
		if (sign.getCreateEndDate() != null) {
			params.put("createEndDate", sdf.format(BseConstants.getEndDate(sign.getCreateEndDate())));
		}
		//权限控制
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functions = currentUser.getFunctionCodes();
		//全国数据
		if(!functions.contains(BseConstants.SIGN_ALLDATA)){
			//新门店研究组权限
			if(functions.contains(BseConstants.SIGN_NEWSTORE)){
				params.put("newstore", BseConstants.YES);
			//战略客户部权限
			}else if(functions.contains(BseConstants.SIGN_TACTICCUSTOMER)){
				params.put("tacticcustomer", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			//客户管理部权限
			}else if(functions.contains(BseConstants.SIGN_CUSTOMERMANAGE)){
				params.put("customermanage", BseConstants.YES);
			//事业部大区路区权限
			}else if(functions.contains(BseConstants.SIGN_BUSINESSREGIONROAD)){
				params.put("businessregionroad", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			//门店客户经理权限
			}else if(functions.contains(BseConstants.SIGN_STOREORSALE)){
				params.put("storeorsale", currentUser.getUserName());
			//团队经理权限
			}else if(functions.contains(BseConstants.SIGN_TERMMANAGE)){
				params.put("termmanage", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			}
		}
		return signMapper.getSign(params, rb);

	}

	@Override
	public Long countSign(SignEntity sign) {
		if (sign == null) {
			throw new SignException(SignException.QUERY_SIGN_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(sign.getCustomerName())) {
			params.put("customerName", "%" + sign.getCustomerName() + "%");
		}
		if (!StringUtil.isEmpty(sign.getSignAddress())) {
			params.put("signAddress", "%" + sign.getSignAddress() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (sign.getCreateDate() != null) {
			params.put("createDate", sdf.format(sign.getCreateDate()));
		}
		if (sign.getCreateEndDate() != null) {
			params.put("createEndDate", sdf.format(BseConstants.getEndDate(sign.getCreateEndDate())));
		}
		//权限控制
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functions = currentUser.getFunctionCodes();
		//全国数据
		if(!functions.contains(BseConstants.SIGN_ALLDATA)){
			//新门店研究组权限
			if(functions.contains(BseConstants.SIGN_NEWSTORE)){
				params.put("newstore", BseConstants.YES);
			//战略客户部权限
			}else if(functions.contains(BseConstants.SIGN_TACTICCUSTOMER)){
				params.put("tacticcustomer", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			//客户管理部权限
			}else if(functions.contains(BseConstants.SIGN_CUSTOMERMANAGE)){
				params.put("customermanage", BseConstants.YES);
			//事业部大区路区权限
			}else if(functions.contains(BseConstants.SIGN_BUSINESSREGIONROAD)){
				params.put("businessregionroad", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			//门店客户经理权限
			}else if(functions.contains(BseConstants.SIGN_STOREORSALE)){
				params.put("storeorsale", currentUser.getUserName());
			//团队经理权限
			}else if(functions.contains(BseConstants.SIGN_TERMMANAGE)){
				params.put("termmanage", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			}
		}
		return signMapper.countSign(params);

	}

	@Override
	public List<SignEntity> getSignInfoNoRelation(SignEntity sign, RowBounds rb) {
		//当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		if (rb == null) {
			throw new SignException(SignException.QUERY_SIGN_RB_NULL);
		}
		if(sign == null || StringUtil.isEmpty(sign.getCustomerId())){
			throw new SignException(SignException.QUERY_SIGN_PARAM_NULL);
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("customerId", sign.getCustomerId());
		params.put("createUser", currUser.getUserName());
		return signMapper.getSignInfoNoRelation(params,rb);
	}
	
	@Override
	public long countSignInfoNoRelation(SignEntity sign) {
		//当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Map<String,Object> params = new HashMap<String,Object>();
		if(sign == null || StringUtil.isEmpty(sign.getCustomerId())){
			throw new SignException(SignException.QUERY_SIGN_PARAM_NULL);
		}
		params.put("customerId", sign.getCustomerId());
		params.put("createUser", currUser.getUserName());
		return signMapper.countSignInfoNoRelation(params);
	}
	
	@Override
	@Transactional
	public void updateRelationChatStatus(String signId) {
		 if(StringUtil.isEmpty(signId)){
			 throw new SignException(SignException.QUERY_SIGN_PARAM_NULL);
		 }
		 signMapper.updateRelationChatStatus(signId);
	}

	

}
