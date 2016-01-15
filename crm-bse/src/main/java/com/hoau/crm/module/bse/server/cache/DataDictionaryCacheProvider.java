package com.hoau.crm.module.bse.server.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;
import com.hoau.crm.module.bse.server.dao.DataDictionaryMapper;
import com.hoau.crm.module.bse.server.dao.DataDictionaryValueMapper;
import com.hoau.crm.module.util.define.CrmConstants;
import com.hoau.hbdp.framework.cache.provider.IBatchCacheProvider;

/**
 * @author：高佳
 * @create：2015年6月4日 下午3:02:10
 * @description：热门城市缓存数据提供者
 */
@Component
public class DataDictionaryCacheProvider implements IBatchCacheProvider<String, Object> {
	
	public final static String KEY_DATA_DICTS = "dataDicts";
	public final static String KEY_DATA_DICTSVERSION = "dataVersion";
	@Resource
	private DataDictionaryMapper dataDictionaryMapper;
	@Resource
	private DataDictionaryValueMapper dataDictionaryValueMapper;
	
	@Override
	public Date getLastModifyTime() {
		return new Date(dataDictionaryValueMapper.getLastChangeVersionNo());
	}
	
	@Override
	public Map<String, Object> get() {
		Map<String,Object> hashMap = new HashMap<String, Object>();
		List<DataDictionaryEntity> dataDicts = dataDictionaryMapper.queryAllDataDictionary(CrmConstants.ACTIVE);
		hashMap.put(KEY_DATA_DICTS, dataDicts);
		hashMap.put(KEY_DATA_DICTSVERSION, dataDictionaryValueMapper.getLastChangeVersionNo());
		return hashMap;
	}
	
	
}
