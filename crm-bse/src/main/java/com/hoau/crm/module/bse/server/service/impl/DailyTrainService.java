package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.service.IDailyTrainService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DailyTrainEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.DailyTrainMapper;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
/**
 * @author 275636
 *每日培训service
 */
@Service
public class DailyTrainService implements IDailyTrainService {
	
	@Resource
	DailyTrainMapper dailyTrainMapper;

	@Override
	public List<DailyTrainEntity> queryDailyTrainList(
			DailyTrainEntity dailyTrain, RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (dailyTrain == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(dailyTrain.getCreateUser())) {
			params.put("createUser","%" + dailyTrain.getCreateUser() + "%");
		}
		if (dailyTrain.getHyDate() != null) {
			params.put("hyDate",sdf.format(dailyTrain.getHyDate()));
		}
		if (!StringUtil.isEmpty(dailyTrain.getHyaddress())) {
			params.put("hyaddress", "%" + dailyTrain.getHyaddress() + "%");
		}
		if (!StringUtil.isEmpty(dailyTrain.getPxType())) {
			params.put("pxType", dailyTrain.getPxType());
		}
		if(dailyTrain.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf.format(dailyTrain.getQueryCreateTime()));
		}
		if(!StringUtil.isEmpty(dailyTrain.getSortType())){
			if("DESC".equals(dailyTrain.getSortType())){
				params.put("timeDesc", BseConstants.YES);
			}else if("ASC".equals(dailyTrain.getSortType())){
				params.put("timeAsc", BseConstants.YES);
			}
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_TRAIN))
			params.put("createdBy", currentUser.getUserName());
		return dailyTrainMapper.queryDailyTrainList(params, rb);
	}

	@Override
	public Long countqueryDailyTrainList(DailyTrainEntity dailyTrain,
			RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (dailyTrain == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(dailyTrain.getCreateUser())) {
			params.put("createUser","%" + dailyTrain.getCreateUser() + "%");
		}
		if (dailyTrain.getHyDate() != null) {
			params.put("hyDate",sdf.format(dailyTrain.getHyDate()));
		}
		if (!StringUtil.isEmpty(dailyTrain.getHyaddress())) {
			params.put("hyaddress", "%" + dailyTrain.getHyaddress() + "%");
		}
		if (!StringUtil.isEmpty(dailyTrain.getPxType())) {
			params.put("pxType", dailyTrain.getPxType());
		}
		if(dailyTrain.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf.format(dailyTrain.getQueryCreateTime()));
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_TRAIN))
			params.put("createdBy", currentUser.getUserName());
		return dailyTrainMapper.countqueryDailyTrainList(params, rb);
	}

	@Override
	public void deleteDailyTrain(List<String> ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		dailyTrainMapper.deleteDailyTrain(map);
	}

	@Override
	public void addDailyTrain(DailyTrainEntity dailyTrainEntity,
			UserEntity currentUser) {
		dailyTrainEntity.setCreateUser(currentUser.getUserName());
		dailyTrainMapper.addDailyTrain(dailyTrainEntity);
	}

	@Override
	public DailyTrainEntity queryDailyTrainInfoById(DailyTrainEntity dailyTrain) {
		if (dailyTrain == null
				|| StringUtil.isEmpty(dailyTrain.getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		return dailyTrainMapper.queryDailyTrainInfoById(dailyTrain.getId());
	}

	@Override
	public List<String> queryEmpNames(String selectorParam) {
		selectorParam = "%"+selectorParam+"%";
		return dailyTrainMapper.queryEmpNames(selectorParam);
	}

	@Override
	public List<String> queryDeptNames(String selectorParam) {
		selectorParam = "%"+selectorParam+"%";
		return dailyTrainMapper.queryDeptNames(selectorParam);
	}

}
