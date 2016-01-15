package com.hoau.crm.module.bse.api.server.service;

import java.io.File;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.appcore.api.shared.domain.AppVersionEntity;

/**
 * App版本信息SERVICE
 * 
 * @author 潘强
 * @date 2015-7-30
 */
public interface IAppVersionService {

	/**
	 * 统计App版本信息总数
	 * 
	 * @param appVersion
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月30日
	 * @update
	 */
	Long countAppVersion(AppVersionEntity appVersion, RowBounds rb);

	/**
	 * 查询App版本信息
	 * 
	 * @param appVersion
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月30日
	 * @update
	 */
	List<AppVersionEntity> getAPPVersion(AppVersionEntity appVersion,
			RowBounds rb);
	/**
	 * 删除App版本信息
	 * 
	 * @param ids
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月31日
	 * @update
	 */
	
	void deleteAppVersion(List<String> ids);
	/**
	 * 新增App版本信息
	 * 
	 * @param appVersion
	 * @return
	 * @author: 潘强
	 * @date: 2015年8月3日
	 * @update
	 */
	void addAppVersion(AppVersionEntity appVersion,File file,String fileName);
	/**
	 * 设置App当前版本信息
	 * 
	 * @param versionCode
	 * @return
	 * @author: 潘强
	 * @date: 2015年8月3日
	 * @update
	 */
	void updateVersionCode(int versionCode);

	/**
	 * 下载App当前版本信息
	 * 
	 * @param 
	 * @return
	 * @author: 潘强
	 * @date: 2015年8月5日
	 * @update
	 */
	void exportAppVersion();

}
