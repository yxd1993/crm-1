package com.hoau.crm.module.appcore.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.AppSweepCardsEntity;

/**
 *
 * @author 丁勇
 * @date 2015年11月23日
 */
@Repository
public interface AppSweepCardsMapper {

	/**
	 * 保存扫描名片的信息
	 * @param sweepCardsEntity
	 * @author 丁勇
	 * @date 2015年11月23日
	 * @update 
	 */
	void insertSweepCardsInfo(AppSweepCardsEntity appSweepCardsEntity);
}
