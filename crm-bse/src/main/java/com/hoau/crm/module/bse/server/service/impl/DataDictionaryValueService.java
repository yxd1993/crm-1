package com.hoau.crm.module.bse.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IDataDictionaryValueService;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.exception.DataDictionaryValueException;
import com.hoau.crm.module.bse.server.cache.DataDictionaryCache;
import com.hoau.crm.module.bse.server.cache.DataDictionaryCacheProvider;
import com.hoau.crm.module.bse.server.dao.DataDictionaryValueMapper;
import com.hoau.crm.module.common.server.context.CrmUserContext;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.crm.module.util.define.CrmConstants;
import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;

/**
 * @author：高佳
 * @create：2015年5月15日 上午9:01:07
 * @description：
 */
@Service
public class DataDictionaryValueService implements IDataDictionaryValueService{
	@Resource
	private DataDictionaryValueMapper dataDictionaryValueMapper;
	
	@Override
	@Transactional
	public void addDataDictionaryValue(
			DataDictionaryValueEntity entity) {
		if(entity != null){
			entity.setActive(CrmConstants.ACTIVE);
			entity.setId(UUIDUtil.getUUID());
			entity.setVersionNo(UUIDUtil.getVersion());
			entity.setCreateDate(new Date());
			entity.setCreateUser(CrmUserContext.getCurrentUser().getUserName());
			entity.setModifyDate(new Date());
			entity.setModifyUser(CrmUserContext.getCurrentUser().getUserName());
			if(this.queryDataDictionaryValueByCodeName(entity).size() > 0){
				throw new DataDictionaryValueException(DataDictionaryValueException.EXIST);
			}
			dataDictionaryValueMapper.addDataDictionaryValue(entity);
		}
	}
	
	public List<DataDictionaryValueEntity> queryDataDictionaryValueByCodeName(DataDictionaryValueEntity entity){
		return dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
	}
	
	@Override
	public DataDictionaryValueEntity deleteDataDictionaryValue(
			DataDictionaryValueEntity entity) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("entity", entity);
		map.put("conditionActive", CrmConstants.ACTIVE);
		dataDictionaryValueMapper.deleteDataDictionaryValue(map);
		return entity;
	}

	@Override
	public DataDictionaryValueEntity updateDataDictionaryValue(
			DataDictionaryValueEntity entity) {
		return null;
	}

	@Override
	public List<DataDictionaryValueEntity> queryDataDictionaryValueByEntity(
			DataDictionaryValueEntity entity, int start, int limit) {
		RowBounds rowBounds = new RowBounds(start, limit);
		entity.setActive(CrmConstants.ACTIVE);
		return dataDictionaryValueMapper.queryDataDictionaryValueByEntity(entity,rowBounds);
	}

	@Override
	public long queryDataDictionaryValueByEntityCount(
			DataDictionaryValueEntity entity) {
		return dataDictionaryValueMapper.queryDataDictionaryValueByEntityCount(entity);
	}


	@Override
	@Transactional
	public void deleteDataDictionaryValueMore(
			List<DataDictionaryValueEntity> entitys) {
		for (DataDictionaryValueEntity entity : entitys) {
			entity.setActive(CrmConstants.INACTIVE);
			entity.setVersionNo(UUIDUtil.getVersion());
			entity.setModifyDate(new Date());
			entity.setModifyUser(CrmUserContext.getCurrentUser().getUserName());
			this.deleteDataDictionaryValue(entity);
		}
	}

	@Override
	public long getLastChangeVersionNo() {
		ICache<String, Long> cache = CacheManager.getInstance().getCache(DataDictionaryCache.KEY_DATA_DICTS);
		return cache.get(DataDictionaryCacheProvider.KEY_DATA_DICTSVERSION);
		//return dataDictionaryValueMapper.getLastChangeVersionNo();
	}

}
