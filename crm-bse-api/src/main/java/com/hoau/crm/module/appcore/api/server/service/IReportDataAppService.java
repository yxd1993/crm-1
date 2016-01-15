/**
 * 
 */
package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.ReportDataAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;


/**
 * 报表模块数据
 *
 * @author 蒋落琛
 * @date 2015-7-13
 */
@SuppressWarnings("rawtypes")
public interface IReportDataAppService {
	
	/**
	 * 获取APP报表信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-13
	 * @update
	 */
	public ResponseBaseEntity queryAppReportData(ReportDataAppVo searchVo, String loginName);
}
