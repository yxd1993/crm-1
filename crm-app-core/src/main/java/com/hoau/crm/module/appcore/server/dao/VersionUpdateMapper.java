package com.hoau.crm.module.appcore.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.AppVersionEntity;

@Repository
public interface VersionUpdateMapper {

	/**
	 * 查询当前APP的最新版本
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public AppVersionEntity getVersionInfo();
}