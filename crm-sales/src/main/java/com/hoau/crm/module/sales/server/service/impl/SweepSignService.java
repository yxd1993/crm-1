package com.hoau.crm.module.sales.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.sales.api.server.service.ISweepSignService;
import com.hoau.crm.module.sales.api.shared.domain.SweepSignEntity;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.crm.module.sales.server.dao.SweepSignMapper;
import com.hoau.crm.module.sales.shared.exception.SweepSignException;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 扫描签到SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-7-23
 */
@Service
public class SweepSignService implements ISweepSignService {

	@Resource
	private SweepSignMapper sweepSignMapper;

	@Override
	public void addSweepSignInfo(SweepSignEntity ssEntity) {
		// 判断参数是否为空
		if (ssEntity == null) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		// 主键
		ssEntity.setId(UUIDUtil.getUUID());
		sweepSignMapper.addSweepSignInfo(ssEntity);
	}

	@Override
	public List<SweepSignEntity> getSweepSign(SweepSignEntity sweepSign,
			RowBounds rb) {
		if (rb == null) {
			throw new SweepSignException(SweepSignException.QUERY_SWEEPSIGN_RB_NULL);
		}
		if (sweepSign == null) {
			throw new SweepSignException(SweepSignException.QUERY_SWEEPSIGN_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(sweepSign.getSweepPeopName())) {
			params.put("sweepPeopName", "%" + sweepSign.getSweepPeopName() + "%");
		}
		if (!StringUtil.isEmpty(sweepSign.getWasSweepPeopName())) {
			params.put("wasSweepPeopName", "%" + sweepSign.getWasSweepPeopName() + "%");
		}
		if (!StringUtil.isEmpty(sweepSign.getSignAddress())) {
			params.put("signAddress", "%" + sweepSign.getSignAddress() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (sweepSign.getSweepTime() != null) {
			params.put("sweepTime", sdf.format(sweepSign.getSweepTime()));
		}
		if (sweepSign.getSweepEndTime() != null) {
			params.put("sweepEndTime", sdf.format(BseConstants.getEndDate(sweepSign.getSweepEndTime())));
		}
		// 自己写分页
		if(rb != null){
			params.put("startNum", String.valueOf(rb.getOffset()));
			params.put("endNum", String.valueOf(rb.getLimit()));
		}
		//权限控制
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functions = currentUser.getFunctionCodes();
		//全国数据
		if(!functions.contains(BseConstants.SWEEP_SIGN_ALLDATA)){
			params.put("userDeptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
		}
		return sweepSignMapper.getSweepSign(params);
	}

	@Override
	public Long countSweepSign(SweepSignEntity sweepSign, RowBounds rb) {

		if (rb == null) {
			throw new SweepSignException(SweepSignException.QUERY_SWEEPSIGN_RB_NULL);
		}
		if (sweepSign == null) {
			throw new SweepSignException(SweepSignException.QUERY_SWEEPSIGN_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(sweepSign.getSweepPeopName())) {
			params.put("sweepPeopName", "%" + sweepSign.getSweepPeopName() + "%");
		}
		if (!StringUtil.isEmpty(sweepSign.getWasSweepPeopName())) {
			params.put("wasSweepPeopName", "%" + sweepSign.getWasSweepPeopName() + "%");
		}
		if (!StringUtil.isEmpty(sweepSign.getSignAddress())) {
			params.put("signAddress", "%" + sweepSign.getSignAddress() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (sweepSign.getSweepTime() != null) {
			params.put("sweepTime", sdf.format(sweepSign.getSweepTime()));
		}
		if (sweepSign.getSweepEndTime() != null) {
			params.put("sweepEndTime", sdf.format(BseConstants.getEndDate(sweepSign.getSweepEndTime())));
		}
		//权限控制
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functions = currentUser.getFunctionCodes();
		//全国数据
		if(!functions.contains(BseConstants.SWEEP_SIGN_ALLDATA)){
			params.put("userDeptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
		}
		return sweepSignMapper.countSweepSign(params, rb);
	
	}

}
