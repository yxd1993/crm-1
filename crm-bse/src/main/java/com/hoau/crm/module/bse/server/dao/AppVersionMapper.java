package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.AppVersionEntity;

/**
 * App版本信息DAO
 *
 * @author 潘强
 * @date 2015-7-30
 */
@Repository
public interface AppVersionMapper {

	/**
	 * App版本信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月30日
	 * @update
	 */
	List<AppVersionEntity> getAPPVersion(Map<String, Object> params,
			RowBounds rb);

	/**
	 * 统计App版本信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月30日
	 * @update
	 */
	Long countAppVersion(Map<String, Object> params, RowBounds rb);
	/**
	 * 删除App版本信息
	 * 
	 * @param map
	 * @author: 潘强
	 * @date: 2015年7月31日
	 * @update 
	 */
	void deleteAppVersion(Map<String, Object> map);
	/**
	 * 查询最大App版本号
	 * 
	 * @param null
	 * @author: 潘强
	 * @date: 2015年8月3日
	 * @update 
	 */
	int getMaxAppVersionCode();
	
	/**
	 * 新增App版本信息
	 * 
	 * @param appVersion
	 * @author: 潘强
	 * @date: 2015年8月3日
	 * @update 
	 */
	void addAppVersion(AppVersionEntity appVersion);
	/**
	 * 设置当前数据库中App版本为过去版本
	 * @param null
	 * @author: 潘强
	 * @date: 2015年8月3日
	 * @update 
	 */
	void updateAppVersionIsNow();
	
	/**
	 * 通过最大版本号设置当前数据库中App版本为当前版本
	 * @param i
	 * @author: 潘强
	 * @date: 2015年8月3日
	 * @update 
	 */
	void setIsNowFromMaxCode(int i);

	/**
	 * 获取当前版本的App的url
	 * @param 
	 * @author: 潘强
	 * @date: 2015年8月5日
	 * @update 
	 */
	String getUrlFromIsNow();
	
	/**
	 * 获取当前版本的App的apkName
	 * @param 
	 * @author: 潘强
	 * @date: 2015年8月5日
	 * @update 
	 */
	String getApkNameFromIsNow();
	/**
	 * 通过id获取App的url
	 * @param 
	 * @author: 潘强
	 * @date: 2015年8月6日
	 * @update 
	 */
	String getUrlFromId(String id);

}
