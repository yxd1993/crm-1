package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IReportAnalysisAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.ReportAnalysisAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 自定义报表APP接口
 * 
 * @author: 何斌
 * @create: 2015年9月8日 下午2:06:59
 */
@Service
@Produces(HttpConstant.DATATYPE)
@Consumes(HttpConstant.DATATYPE)
@Path("/reportAnalysis")
public class ReportAnalysisFacade extends AppBaseFacade{
	
	@Resource
	IReportAnalysisAppService iReportAnalysisAppService;
	
	@POST
	@Path("queryReportAnalysisInfos")
	public ResponseBaseEntity<ReportAnalysisAppVo> queryReportAnalysisInfos(ReportAnalysisAppVo reportAnalysisAppVo){
		return iReportAnalysisAppService.getReportAnalysisInfos(reportAnalysisAppVo,this.loginName);
	};
	
	@POST
	@Path("queryReportAnalysisInfoByDeptCode")
	public ResponseBaseEntity<ReportAnalysisAppVo> queryReportAnalysisInfoByDeptCode(ReportAnalysisAppVo reportAnalysisAppVo){
		return iReportAnalysisAppService.getReportAnalysisInfoByDeptCode(reportAnalysisAppVo,this.loginName);
	}
	
	@POST
	@Path("queryReportAnalysisData")
	public ResponseBaseEntity<ReportAnalysisAppVo> queryReportAnalysisInfoData(ReportAnalysisAppVo reportAnalysisAppVo){
		return iReportAnalysisAppService.getReportAnalysisInfoData(reportAnalysisAppVo,this.loginName);
	}
}
