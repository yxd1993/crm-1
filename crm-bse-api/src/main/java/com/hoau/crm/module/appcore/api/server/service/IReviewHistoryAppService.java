/**
 * 
 */
package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.ReviewHistoryAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 
 * @author 丁勇
 * @date
 */
public interface IReviewHistoryAppService {
	/**
	 * 查询历史跟进信息
	 * 
	 * 
	 * @param accountId
	 * @return
	 * @author 丁勇
	 * @date 2015年7月13日
	 * @update
	 */
	public ResponseBaseEntity<ReviewHistoryAppVo> queryHistoryList(
			ReviewHistoryAppVo rhaVo, String loginName);

	/**
	 * 查询工作回顾信息
	 * 
	 * @param maps
	 * @return
	 * @author 丁勇
	 * @date 2015年7月13日
	 * @update
	 */
	public ResponseBaseEntity<ReviewHistoryAppVo> queryReviewList(
			ReviewHistoryAppVo rhaVo, String loginName);
}
