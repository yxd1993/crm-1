package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;
import com.hoau.hbdp.framework.service.IService;


/**
 *
 * @author 高佳
 * @date 2015年5月14日
 */
/**
 *
 * @author 高佳
 * @date 2015年5月14日
 */
/**
 *
 * @author 高佳
 * @date 2015年5月14日
 */
public interface IDataDictionaryService extends IService {
   
    /**
     *  插入
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    DataDictionaryEntity addDataDictionary(DataDictionaryEntity entity);
    
    /**
     * 根据CODE删除
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    DataDictionaryEntity deleteDataDictionary(DataDictionaryEntity entity);
    
    /**
     * 根据CODE批量删除
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    DataDictionaryEntity deleteDataDictionaryMore(DataDictionaryEntity entity);
	
    
    /**
     * 更新 
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    DataDictionaryEntity updateDataDictionary(DataDictionaryEntity entity);
	
	/**
	 * 查询所有数据字典对象：
	 * @return
	 * @author 高佳
	 * @date 2015年5月14日
	 * @update 
	 */
	List<DataDictionaryEntity> queryAllDataDictionary();

    
    /**
     * 根据 词条查询
     * @param code
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    DataDictionaryEntity queryDataDictionaryByTermsCode(String code);
    
    /**
     * 根据词条集合查询数据字典对象
     * @param termsCodes
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    List<DataDictionaryEntity> queryDataDictionaryByTermsCodes(
			List<String> termsCodes);
	
    /**
     * 根据entity模糊查询，
     * @param entity
     * @param limit
     * @param start
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    List<DataDictionaryEntity> queryDataDictionaryByEntity(DataDictionaryEntity entity,int limit,int start);
	
    /**
     * 查询queryDataDictionaryByEntity返回的记录总数,用于分页
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    long queryDataDictionaryByEntityCount(DataDictionaryEntity entity);

    /**
     * 查询 columnName列的columnValue值有多少个，可用于去重
     * @param columnValue
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    long queryDataDictionaryCountByTermsCode(String columnValue);
    
    
    
    
	
}
