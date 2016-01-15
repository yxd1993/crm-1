package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IDailyMeetingAppService;
import com.hoau.crm.module.appcore.api.server.service.IDailyProblemAppService;
import com.hoau.crm.module.appcore.api.server.service.IDailyTrainAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.DailyAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@Service
@Path("/daily")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class DailyFacade extends AppBaseFacade {
	@Resource
	IDailyMeetingAppService iDailyMeetingAppService;
	
	@Resource
	IDailyProblemAppService iDailyProblemAppService;
	
	@Resource
	IDailyTrainAppService iDailyTrainAppService;
	
	/**
	 * 每日会议 以下
	 * */
	@POST
	@Path("queryDailyMeetingInfo")
	public ResponseBaseEntity<DailyAppVo> queryDailyMeetingList(DailyAppVo dailyMeetingAppVo) {
		return iDailyMeetingAppService.queryDailyMeetingList(dailyMeetingAppVo, loginName);
	}
	
	@POST
	@Path("deleteDailyMeeting")
	public ResponseBaseEntity<DailyAppVo> deleteDailyMeeting(DailyAppVo dailyMeetingAppVo) {
		return iDailyMeetingAppService.deleteDailyMeeting(dailyMeetingAppVo);
	}
	@POST
	@Path("addDailyMeeting")
	public ResponseBaseEntity<DailyAppVo> addDailyMeeting(DailyAppVo dailyMeetingAppVo) {
		return iDailyMeetingAppService.addDailyMeeting(dailyMeetingAppVo, loginName);
	}
	
	@POST
	@Path("queryDailyMeetingInfoById")
	public ResponseBaseEntity<DailyAppVo> queryDailyMeetingInfoById(DailyAppVo dailyMeetingAppVo) {
		return iDailyMeetingAppService.queryDailyMeetingInfoById(dailyMeetingAppVo);
	}
	
	@POST
	@Path("queryEmployeeInfoById")
	public ResponseBaseEntity<DailyAppVo> queryEmployeeInfoById(DailyAppVo dailyMeetingAppVo) {
		return iDailyMeetingAppService.queryEmployeeInfoById(loginName);
	}
	
	/**
	 * 每日问题 以下
	 * */
	@POST
	@Path("queryDailyProblemInfo")
	public ResponseBaseEntity<DailyAppVo> queryDailyProblemList(DailyAppVo dailyProblemAppVo) {
		return iDailyProblemAppService.queryDailyProblemList(dailyProblemAppVo, loginName);
	}
	
	@POST
	@Path("deleteDailyProblem")
	public ResponseBaseEntity<DailyAppVo> deleteDailyProblem(DailyAppVo dailyProblemAppVo) {
		return iDailyProblemAppService.deleteDailyProblem(dailyProblemAppVo);
	}
	@POST
	@Path("addDailyProblem")
	public ResponseBaseEntity<DailyAppVo> addDailyProblem(DailyAppVo dailyProblemAppVo) {
		return iDailyProblemAppService.addDailyProblem(dailyProblemAppVo, loginName);
	}
	
	@POST
	@Path("queryDailyProblemInfoById")
	public ResponseBaseEntity<DailyAppVo> queryDailyProblemInfoById(DailyAppVo dailyProblemAppVo) {
		return iDailyProblemAppService.queryDailyProblemInfoById(dailyProblemAppVo);
	}
	
	/**
	 * 每日培训 以下
	 * */
	
	@POST
	@Path("queryDailyTrainInfo")
	public ResponseBaseEntity<DailyAppVo> queryDailyTrainList(DailyAppVo dailyTrainAppVo) {
		return iDailyTrainAppService.queryDailyTrainList(dailyTrainAppVo, loginName);
	}
	
	@POST
	@Path("deleteDailyTrain")
	public ResponseBaseEntity<DailyAppVo> deleteDailyTrain(DailyAppVo dailyTrainAppVo) {
		return iDailyTrainAppService.deleteDailyTrain(dailyTrainAppVo);
	}
	@POST
	@Path("addDailyTrain")
	public ResponseBaseEntity<DailyAppVo> addDailyTrain(DailyAppVo dailyTrainAppVo) {
		return iDailyTrainAppService.addDailyTrain(dailyTrainAppVo, loginName);
	}
	
	@POST
	@Path("queryDailyTrainInfoById")
	public ResponseBaseEntity<DailyAppVo> queryDailyTrainInfoById(DailyAppVo dailyTrainAppVo) {
		return iDailyTrainAppService.queryDailyTrainInfoById(dailyTrainAppVo);
	}
	
	@POST
	@Path("queryEmpAndDeptNames")
	public ResponseBaseEntity<DailyAppVo> queryEmpAndDeptName(DailyAppVo dailyAppVo){
		return iDailyTrainAppService.queryEmpAndDeptName(dailyAppVo);
	}
}
