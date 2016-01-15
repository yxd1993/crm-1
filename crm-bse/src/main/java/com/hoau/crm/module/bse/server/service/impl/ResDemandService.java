package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.service.IResDemandService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.ResDemandEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.ResDemandMapper;
import com.hoau.crm.module.bse.shared.exception.ResDemandException;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * @author 275636
 *资源需求service
 */
@Service
public class ResDemandService implements IResDemandService {
	
	@Resource
	private ResDemandMapper resDemandMapper;

	@Override
	public List<ResDemandEntity> queryResDemandList(ResDemandEntity resDemand,
			RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (resDemand == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(resDemand.getCreateUser())) {
			params.put("createUser","%" + resDemand.getCreateUser() + "%");
		}
		if (resDemand.getSolvetime() != null) {
			params.put("solvetime",sdf1.format(resDemand.getSolvetime()));
		}
		if (!StringUtil.isEmpty(resDemand.getResources())) {
			params.put("resources", "%" + resDemand.getResources() + "%");
		}
		if (resDemand.getCreateDate() != null) {
			params.put("createDate", sdf.format(resDemand.getCreateDate()));
		}
		if (resDemand.getCreateEndDate() != null) {
			params.put("createEndDate", sdf.format(BseConstants.getEndDate(resDemand.getCreateEndDate())));
		}
		if(resDemand.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf1.format(resDemand.getQueryCreateTime()));
		}
		if(!StringUtil.isEmpty(resDemand.getSortType())){
			if("DESC".equals(resDemand.getSortType())){
				params.put("timeDesc", BseConstants.YES);
			}else if("ASC".equals(resDemand.getSortType())){
				params.put("timeAsc", BseConstants.YES);
			}
		}
		if(!StringUtil.isEmpty(resDemand.getIsreply())){
			params.put("isreply", resDemand.getIsreply());
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_RESDEMAND))
			params.put("createdBy", currentUser.getUserName());
		return resDemandMapper.queryResDemandList(params, rb);
	}

	@Override
	public Long countqueryResDemandList(ResDemandEntity resDemand, RowBounds rb,UserEntity currentUser) {
		if (rb == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_RB_NULL);
		}
		if (resDemand == null) {
			throw new ResDemandException(ResDemandException.QUERY_RESDEMAND_PARAM_NULL);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(resDemand.getCreateUser())) {
			params.put("createUser","%" + resDemand.getCreateUser() + "%");
		}
		if (resDemand.getSolvetime() != null) {
			params.put("solvetime",sdf1.format(resDemand.getSolvetime()));
		}
		if (!StringUtil.isEmpty(resDemand.getResources())) {
			params.put("resources", "%" + resDemand.getResources() + "%");
		}
		if (resDemand.getCreateDate() != null) {
			params.put("createDate", sdf.format(resDemand.getCreateDate()));
		}
		if (resDemand.getCreateEndDate() != null) {
			params.put("createEndDate", sdf.format(BseConstants.getEndDate(resDemand.getCreateEndDate())));
		}
		if(resDemand.getQueryCreateTime() != null){
			params.put("queryCreateTime", sdf1.format(resDemand.getQueryCreateTime()));
		}
		if(!StringUtil.isEmpty(resDemand.getIsreply())){
			params.put("isreply", resDemand.getIsreply());
		}
		Set<String> functions = currentUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_RESDEMAND))
			params.put("createdBy", currentUser.getUserName());
		return resDemandMapper.countqueryResDemandList(params, rb);
	}

	@Override
	public void deleteResDemand(List<String> ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		resDemandMapper.deleteResDemand(map);
	}

	@Override
	public void addResDemand(ResDemandEntity resDemandEntity,UserEntity currentUser) {
		resDemandEntity.setCreateUser(currentUser.getUserName());
		resDemandMapper.addResDemand(resDemandEntity);
	}

	@Override
	public ResDemandEntity queryResDemandInfoById(ResDemandEntity resDemand) {
		if (resDemand == null
				|| StringUtil.isEmpty(resDemand.getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		return resDemandMapper.queryResDemandInfoById(resDemand.getId());
	}

	@Override
	public void updateResDemandInfoById(ResDemandEntity resDemand,UserEntity currentUser) {
		if (resDemand == null
				|| StringUtil.isEmpty(resDemand.getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		resDemand.setModifyUser(currentUser.getUserName());
		resDemandMapper.updateResDemandInfoById(resDemand);
	}

	@Override
	public void updateResDemandInfoByIsreply(ResDemandEntity resDemand) {
		if (resDemand == null
				|| StringUtil.isEmpty(resDemand.getId())) {
			throw new ResDemandException(ResDemandException.ADD_RESDEMAND_ID_NULL);
		}
		resDemandMapper.updateResDemandInfoByIsreply(resDemand);
	}

}
