package com.hoau.crm.module.appcore.api.server.service;

import com.hoau.crm.module.appcore.api.shared.vo.DailyAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@SuppressWarnings("rawtypes")
public interface IDailyProblemAppService {
	ResponseBaseEntity<DailyAppVo> queryDailyProblemList(DailyAppVo dailyProblemAppVo, String loginName);
	ResponseBaseEntity<DailyAppVo> deleteDailyProblem(DailyAppVo dailyProblemAppVo);
	ResponseBaseEntity<DailyAppVo> addDailyProblem(DailyAppVo dailyProblemAppVo,String loginName);
	ResponseBaseEntity<DailyAppVo> queryDailyProblemInfoById(DailyAppVo dailyProblemAppVo);
}
