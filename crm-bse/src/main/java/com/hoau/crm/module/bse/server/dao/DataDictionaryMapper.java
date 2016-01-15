package com.hoau.crm.module.bse.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;


/**
 * @author：高佳
 * @create：2015年5月15日 下午1:29:12
 * @description：
 */
@Repository
public interface DataDictionaryMapper {

	/**
	 * 查询所有数据字典
	 * @param string
	 * @return
	 * @author 高佳
	 * @date 2015年5月15日
	 * @update 
	 */
	List<DataDictionaryEntity> queryAllDataDictionary(String string);

	/**
	 * 根据条件查询词条
	 * @param entity
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	List<DataDictionaryEntity> queryDataDictionaryByEntity(
			DataDictionaryEntity entity);

	/**
	 * 新增数据字典词条信息
	 * @param entity
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	void addDataDictionary(DataDictionaryEntity entity);

	/**
	 * 根据编码查询数据字典
	 * @param code
	 * @return
	 * @author 高佳
	 * @date 2015年5月20日
	 * @update 
	 */
	DataDictionaryEntity queryDataDictionaryByTermsCode(String code);

	
}
