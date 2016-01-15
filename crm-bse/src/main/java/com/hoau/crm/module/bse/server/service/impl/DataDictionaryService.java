package com.hoau.crm.module.bse.server.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IDataDictionaryService;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;
import com.hoau.crm.module.bse.api.shared.exception.DataDictionaryValueException;
import com.hoau.crm.module.bse.server.cache.DataDictionaryCache;
import com.hoau.crm.module.bse.server.cache.DataDictionaryCacheProvider;
import com.hoau.crm.module.bse.server.dao.DataDictionaryMapper;
import com.hoau.crm.module.common.server.context.CrmUserContext;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.crm.module.util.define.CrmConstants;
import com.hoau.hbdp.framework.cache.CacheManager;
import com.hoau.hbdp.framework.cache.ICache;

/**
 * @author：高佳
 * @create：2015年5月15日 上午9:03:22
 * @description：
 */
@Service
public class DataDictionaryService implements IDataDictionaryService{
	@Resource
	DataDictionaryMapper dataDictionaryMapper;
	
	@Override
	@Transactional
	public DataDictionaryEntity addDataDictionary(DataDictionaryEntity entity) {
		if(entity != null){
			entity.setActive(CrmConstants.ACTIVE);
			entity.setId(UUIDUtil.getUUID());
			entity.setCreateDate(new Date());
			entity.setCreateUser(CrmUserContext.getCurrentUser().getUserName());
			entity.setModifyDate(new Date());
			entity.setModifyUser(CrmUserContext.getCurrentUser().getUserName());
			if(queryDataDictionaryByTermsCode(entity.getTermsCode()) != null){
				throw new DataDictionaryValueException(DataDictionaryValueException.EXIST);
			}
			dataDictionaryMapper.addDataDictionary(entity);
		}
		return entity;
	}

	
	@Override
	public DataDictionaryEntity deleteDataDictionary(DataDictionaryEntity entity) {
		return null;
	}

	@Override
	public DataDictionaryEntity deleteDataDictionaryMore(
			DataDictionaryEntity entity) {
		return null;
	}

	@Override
	public DataDictionaryEntity updateDataDictionary(DataDictionaryEntity entity) {
		return null;
	}

	@Override
	public List<DataDictionaryEntity> queryAllDataDictionary() {
		ICache<String, List<DataDictionaryEntity>> cache = CacheManager.getInstance().getCache(DataDictionaryCache.KEY_DATA_DICTS);
		return cache.get(DataDictionaryCacheProvider.KEY_DATA_DICTS);
		 //dataDictionaryMapper.queryAllDataDictionary(CrmConstants.ACTIVE);
	}

	@Override
	public DataDictionaryEntity queryDataDictionaryByTermsCode(String code) {
		return dataDictionaryMapper.queryDataDictionaryByTermsCode(code);
	}

	@Override
	public List<DataDictionaryEntity> queryDataDictionaryByTermsCodes(
			List<String> termsCodes) {
		return null;
	}

	@Override
	public List<DataDictionaryEntity> queryDataDictionaryByEntity(
			DataDictionaryEntity entity, int limit, int start) {
		entity.setActive(CrmConstants.ACTIVE);
		return dataDictionaryMapper.queryDataDictionaryByEntity(entity);
	}

	@Override
	public long queryDataDictionaryByEntityCount(DataDictionaryEntity entity) {
		return 0;
	}

	@Override
	public long queryDataDictionaryCountByTermsCode(String columnValue) {
		return 0;
	}


}
