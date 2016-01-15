package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;

/**
 * @author：高佳
 * @create：2015年5月15日 下午1:29:12
 * @description：
 */
@Repository
public interface DataDictionaryValueMapper {

	/**
	 * 根据条件查询数据字典
	 * @param entity
	 * @param rowBounds
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	List<DataDictionaryValueEntity> queryDataDictionaryValueByEntity(
			DataDictionaryValueEntity entity, RowBounds rowBounds);

	/**
	 * 根据条件查询数据字典数据统计
	 * @param entity
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	long queryDataDictionaryValueByEntityCount(DataDictionaryValueEntity entity);

	/**
	 * 新增数据字典信息
	 * @param entity
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	void addDataDictionaryValue(DataDictionaryValueEntity entity);

	/**
	 * 删除数据字典信息
	 * @param map
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	void deleteDataDictionaryValue(Map<String, Object> map);

	/**
	 * 获取数据字典最新版本号
	 * @return
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	long getLastChangeVersionNo();

	/**
	 * 根据valueCode 与 valueName查询数据字典
	 * @param entity
	 * @return
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	List<DataDictionaryValueEntity> queryDataDictionaryValueByCodeName(
			DataDictionaryValueEntity entity);
	
	/**
	 * 根据TermsCode查询某一类型的数据字典
	 * 
	 * @param entity
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-15
	 * @update
	 */
	List<DataDictionaryValueEntity> queryDataDictionaryListByTermsCode(
			DataDictionaryValueEntity entity);
}
