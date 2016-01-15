/**
 * 
 */
package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IReportDataAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.ReportDataAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 报表模块对外接口
 *
 * @author 蒋落琛
 * @date 2015-7-13
 */
@SuppressWarnings("rawtypes")
@Service
@Path("/reportdata")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class ReportDataFacade extends AppBaseFacade {
	
	//注入接口实现
	@Resource
	IReportDataAppService iReportDataAppService;
	
	@POST
	@Path("queryAppReportData")
	public ResponseBaseEntity queryAppReportData(ReportDataAppVo searchVo) {
		return iReportDataAppService.queryAppReportData(searchVo, this.loginName);
	}
}
