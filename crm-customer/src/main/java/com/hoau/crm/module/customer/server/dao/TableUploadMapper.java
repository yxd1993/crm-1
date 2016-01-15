package com.hoau.crm.module.customer.server.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.TableUploadEntity;


/**
 * 上传DAO
 * @author: 何斌
 * @create: 2015年5月26日 上午10:10:18
 */
@Repository
public interface TableUploadMapper {
	/**
	 * 根据表名查询相关字段信息
	 * 
	 * @param tableName
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月26日
	 * @update 
	 */
	TableUploadEntity queryTableUploadInfoByTableName(String tableName);
	/**
	 * excel批量导入
	 * 
	 * @param map
	 * @author: 何斌
	 * @date: 2015年5月26日
	 * @update 
	 */
	void tableImport(Map map);
	
	/**
	 * 清除上传后产生的重复数据
	 * 
	 * @author: 何斌
	 * @date: 2015年6月1日
	 * @update 
	 */
	void clearRepeatData();
}
