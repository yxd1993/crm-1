package com.hoau.crm.module.bse.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IFunctionService;
import com.hoau.crm.module.bse.api.shared.domain.FunctionEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.exception.FunctionException;
import com.hoau.crm.module.bse.server.dao.FunctionMapper;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 功能权限SERVICE
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class FunctionService implements IFunctionService {
	
	@Resource
	private FunctionMapper functionMapper;
	
	/**
	 * 查询当前节点子节点信息
	 * 
	 * @param functionEntity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	public List<FunctionEntity> queryTreeNodeList(FunctionEntity functionEntity){
		//判断节点参数是否为空
		if(functionEntity == null || functionEntity.getParentCode() == null || StringUtil.isEmpty(functionEntity.getParentCode().getFunctionCode())){
			throw new FunctionException(FunctionException.NODE_NULL);
		}
		return functionMapper.queryTreeNodeList(functionEntity);
	}
	
	@Override
	public List<FunctionEntity> queryTreeNodeListByHomeMenu(FunctionEntity functionEntity){
		//判断节点参数是否为空
		Map<String, String> map = new HashMap<String, String>();
		if(functionEntity == null || functionEntity.getParentCode() == null || StringUtil.isEmpty(functionEntity.getParentCode().getFunctionCode())){
			throw new FunctionException(FunctionException.NODE_NULL);
		}else{
			map.put("functionCode", functionEntity.getParentCode().getFunctionCode());
		}
		if(!StringUtil.isEmpty(functionEntity.getFunctionType())){
			map.put("functionType", functionEntity.getFunctionType());
		}
		// 当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		map.put("userCode", currUser.getUserName());
		return functionMapper.queryTreeNodeListByHomeMenu(map);
	}
	
	@Override
	public Set<String> queryTreeNodeListByUser(String userCode){
		return functionMapper.queryTreeNodeListByUser(userCode);
	}
}
