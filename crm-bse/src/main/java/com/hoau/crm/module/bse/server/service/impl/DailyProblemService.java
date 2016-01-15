package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.service.IDailyProblemService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DailyProblemEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.DailyProblemMapper;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * @author 275636
 *每日问题service
 */
@Service
public class DailyProblemService implements IDailyProblemService {
	
	@Resource
	DailyProblemMapper dailyProblemMapper;

	@Override
	public List<DailyProblemEntity> queryDailyProblemList(
			DailyProblemEntity dailyProblem, RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (dailyProblem == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(dailyProblem.getCreateUser())) {
			params.put("createUser","%" + dailyProblem.getCreateUser() + "%");
		}
		if (dailyProblem.getYltime() != null) {
			params.put("yltime",sdf.format(dailyProblem.getYltime()));
		}
		if (!StringUtil.isEmpty(dailyProblem.getWtdescribe())) {
			params.put("wtdescribe", "%" + dailyProblem.getWtdescribe() + "%");
		}
		
		if (!StringUtil.isEmpty(dailyProblem.getYlway())) {
			params.put("ylway",dailyProblem.getYlway());
		}
		if (!StringUtil.isEmpty(dailyProblem.getJcdescribe())) {
			params.put("jcdescribe", "%" + dailyProblem.getJcdescribe() + "%");
		}
		if(dailyProblem.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf.format(dailyProblem.getQueryCreateTime()));
		}
		if(!StringUtil.isEmpty(dailyProblem.getSortType())){
			if("DESC".equals(dailyProblem.getSortType())){
				params.put("timeDesc", BseConstants.YES);
			}else if("ASC".equals(dailyProblem.getSortType())){
				params.put("timeAsc", BseConstants.YES);
			}
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_PROBLEM))
			params.put("createdBy", currentUser.getUserName());
		return dailyProblemMapper.queryDailyProblemList(params, rb);
	}

	@Override
	public Long countqueryDailyProblemList(DailyProblemEntity dailyProblem,
			RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (dailyProblem == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(dailyProblem.getCreateUser())) {
			params.put("createUser","%" + dailyProblem.getCreateUser() + "%");
		}
		if (dailyProblem.getYltime() != null) {
			params.put("yltime",sdf.format(dailyProblem.getYltime()));
		}
		if (!StringUtil.isEmpty(dailyProblem.getWtdescribe())) {
			params.put("wtdescribe", "%" + dailyProblem.getWtdescribe() + "%");
		}
		
		if (!StringUtil.isEmpty(dailyProblem.getYlway())) {
			params.put("ylway",dailyProblem.getYlway());
		}
		if (!StringUtil.isEmpty(dailyProblem.getJcdescribe())) {
			params.put("jcdescribe", "%" + dailyProblem.getJcdescribe() + "%");
		}
		if(dailyProblem.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf.format(dailyProblem.getQueryCreateTime()));
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_PROBLEM))
			params.put("createdBy", currentUser.getUserName());
		return dailyProblemMapper.countqueryDailyProblemList(params, rb);
	}

	@Override
	public void deleteDailyProblem(List<String> ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		dailyProblemMapper.deleteDailyProblem(map);
	}

	@Override
	public void addDailyProblem(DailyProblemEntity dailyProblemEntity,
			UserEntity currentUser) {
		dailyProblemEntity.setCreateUser(currentUser.getUserName());
		dailyProblemMapper.addDailyProblem(dailyProblemEntity);
	}

	@Override
	public DailyProblemEntity queryDailyProblemInfoById(
			DailyProblemEntity dailyProblem) {
		if (dailyProblem == null
				|| StringUtil.isEmpty(dailyProblem.getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		return dailyProblemMapper.queryDailyProblemInfoById(dailyProblem.getId());
	}

}
