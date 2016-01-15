package com.hoau.crm.module.bse.api.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.exception.DataDictionaryValueException;
import com.hoau.hbdp.framework.service.IService;

/**
 * Service接口
 * @author 高佳
 * @date 2015年5月14日
 */
/**
 *
 * @author 高佳
 * @date 2015年5月14日
 */
public interface IDataDictionaryValueService extends IService{
    /**
     * 插入
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    void addDataDictionaryValue(
	    DataDictionaryValueEntity entity);

    /**
     * 根据CODE删除
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    DataDictionaryValueEntity deleteDataDictionaryValue(DataDictionaryValueEntity entity);
   
	
    /**
     * 更新
     * @param entity
     * @return
     * @throws DataDictionaryValueException
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    DataDictionaryValueEntity updateDataDictionaryValue(
	    DataDictionaryValueEntity entity);	
    
	
    /**
     * 以下全为查询 
     */
	
    /**
     * 根据entity模糊查询，
     * @param entity
     * @param start
     * @param limit
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    List<DataDictionaryValueEntity> queryDataDictionaryValueByEntity(DataDictionaryValueEntity entity,int start, int limit);
	
    /**
     * 查询queryDataDictionaryValueByEntity返回的记录总数,用于分页
     * @param entity
     * @return
     * @author 高佳
     * @date 2015年5月14日
     * @update 
     */
    long queryDataDictionaryValueByEntityCount(DataDictionaryValueEntity entity);
    
    
    /**
     * 下面是特殊方法
     */
    
    /**
     * 批量作废数据字典-值
     * @param entitys
     * @author 高佳
     * @date 2015年5月15日
     * @update 
     */
    void deleteDataDictionaryValueMore(List<DataDictionaryValueEntity> entitys);
    
    /**
     * 判断数据字典内容是否有更新
     * @return
     * @author 高佳
     * @date 2015年5月15日
     * @update 
     */
    long getLastChangeVersionNo();
    
}
