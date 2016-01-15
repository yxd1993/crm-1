package com.hoau.crm.module.appcore.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.MobileInfoEntity;

@Repository
public interface MobileInfoMapper {

	/**
	 * 根据IMES号查询手机设备信息
	 * 
	 * @param mobilevo
	 * @return
	 */
	public MobileInfoEntity findMobileInfo(MobileInfoEntity mobilevo);

	/**
	 * 添加手机设备信息
	 * 
	 * @param mobilevo
	 */
	public void createMobileInfo(MobileInfoEntity mobilevo);

	/**
	 * 添加手机设备明细信息
	 * 
	 * @param mentryvo
	 */
	public void createMobileInfoEntry(MobileInfoEntity mentryvo);
}
