package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.shared.domain.UserOperationEntity;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.server.dao.UserOperationMapper;
import com.hoau.crm.module.bse.shared.exception.UserOperationException;
import com.hoau.crm.module.sales.api.server.service.IUserOperationService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
/**
 * 客户操作信息SERVICE
 * 
 * @author: 潘强
 * @create: 2015年7月20日
 */
@Service
public class UserOperationService implements IUserOperationService {
	
	@Resource
	private UserOperationMapper userOperationMapper;
	
	@Override
	public List<UserOperationEntity> getUserOperation(
			UserOperationEntity userOperation, RowBounds rb) {
		if (rb == null) {
			throw new UserOperationException(
					UserOperationException.QUERY_USEROPERATION_RB_NULL);
		}
		if (userOperation == null) {
			throw new UserOperationException(
					UserOperationException.QUERY_USEROPERATION_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userOperation.getUserOperationName())) {
			params.put("userOperationName",  "%" + userOperation.getUserOperationName() + "%");
		}
		if (!StringUtil.isEmpty(userOperation.getUserOperationType())) {
			params.put("userOperationType",  "%" + userOperation.getUserOperationType() + "%");
		}
		if (!StringUtil.isEmpty(userOperation.getUserOperationIp())) {
			params.put("userOperationIp",  "%" + userOperation.getUserOperationIp() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (userOperation.getUserOperationTime() != null) {
			params.put("userOperationTime", sdf.format(userOperation.getUserOperationTime()));
		}
		if(userOperation.getUserOperationEndTime() !=null){
			params.put("userOperationEndTime", sdf.format(BseConstants.getEndDate(userOperation.getUserOperationEndTime())));
		}
		return userOperationMapper.getUserOperation(params,rb);
	}

	@Override
	public Long countUserOperation(UserOperationEntity userOperation,
			RowBounds rb) {
		if (rb == null) {
			throw new UserOperationException(
					UserOperationException.QUERY_USEROPERATION_RB_NULL);
		}
		if (userOperation == null) {
			throw new UserOperationException(
					UserOperationException.QUERY_USEROPERATION_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(userOperation.getUserOperationName())) {
			params.put("userOperationName",  "%" + userOperation.getUserOperationName() + "%");
		}
		if (!StringUtil.isEmpty(userOperation.getUserOperationType())) {
			params.put("userOperationType",  "%" + userOperation.getUserOperationType() + "%");
		}
		if (!StringUtil.isEmpty(userOperation.getUserOperationIp())) {
			params.put("userOperationIp",  "%" + userOperation.getUserOperationIp() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (userOperation.getUserOperationTime() != null) {
			params.put("userOperationTime", sdf.format(userOperation.getUserOperationTime()));
		}
		if(userOperation.getUserOperationEndTime() !=null){
			params.put("userOperationEndTime", sdf.format(BseConstants.getEndDate(userOperation.getUserOperationEndTime())));
		}
		return userOperationMapper.countUserOperation(params,rb);
	}

}
