package com.hoau.crm.module.bse.server.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hoau.hbdp.framework.cache.DefaultStrongCache;
import com.hoau.hbdp.framework.cache.provider.IBatchCacheProvider;

/**
 * @author：高佳
 * @create：2015年6月4日 下午3:01:35
 * @description：热门城市缓存
 */
@Component
public class DataDictionaryCache extends DefaultStrongCache<String , DataDictionaryCacheProvider>{
	
	public final static String KEY_DATA_DICTS = "dataDicts";
	
	@Override
	public String getUUID() {
		return KEY_DATA_DICTS;
	}
	
	@Override
	@Resource(name = "dataDictionaryCacheProvider")
	public void setCacheProvider(
			IBatchCacheProvider<String, DataDictionaryCacheProvider> cacheProvider) {
		//super.setInterval(30);
		super.setCacheProvider(cacheProvider);
	}
}
