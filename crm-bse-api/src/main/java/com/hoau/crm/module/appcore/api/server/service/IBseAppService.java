package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.domain.AppSweepCardsEntity;
import com.hoau.crm.module.appcore.api.shared.vo.BseAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.DataDictionaryAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;


/**
 * 数据字典模块RESTFUL接口
 *
 * @author 蒋落琛
 * @date 2015-6-15
 */
public interface IBseAppService {
	
	/**
	 * 查询数据字典最新的版本信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-30
	 * @update
	 */
	public ResponseBaseEntity<DataDictionaryAppVo> queryDataDictionaryVersion();

	/**
	 * 查询所有数据字典信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-18
	 * @update
	 */
	public ResponseBaseEntity<DataDictionaryAppVo> queryAllDataDictionary();
	
	/**
	 * 模糊搜索组织信息
	 * 
	 * @param bseAppVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-23
	 * @update
	 */
	public ResponseBaseEntity<BseAppVo> queryDeptList(BseAppVo bseAppVo, String loginName);
	
	/**
	 * 查询当前组织所在的路区、大区、事业部信息
	 * 
	 * @param bseAppVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-23
	 * @update
	 */
	public ResponseBaseEntity<BseAppVo> queryDeptSuperiorDept(BseAppVo bseAppVo,String loginName);
	
	/**
	 * 
	 * 
	 * @param bseAppVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-10-8
	 * @update
	 */
	public ResponseBaseEntity<BseAppVo> queryDeptSuperiorDeptByCurrUser(BseAppVo bseAppVo, String loginName);
	
	/**
	 * 模糊搜索人员信息
	 * 
	 * @param bseAppVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-24
	 * @update
	 */
	public ResponseBaseEntity<BseAppVo> queryEmpList(BseAppVo bseAppVo);
	
	/**
	 * 保存扫描的卡片信息
	 * @param appSweepCardsEntity
	 * @return
	 * @author 丁勇
	 * @date 2015年11月23日
	 * @update 
	 */
	@SuppressWarnings("rawtypes")
	ResponseBaseEntity insertSweepCardsInfo(AppSweepCardsEntity appSweepCardsEntity,String loginName);
	
	/**
	 * 查询未离职的客户经理和团队经理
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月1日
	 * @update 
	 */
	public ResponseBaseEntity<BseAppVo> querySaleEmpLists(String loginName,BseAppVo bseAppVo);
}
