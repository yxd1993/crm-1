package com.hoau.crm.module.appcore.api.server.service;


import com.hoau.crm.module.appcore.api.shared.vo.DailyAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

public interface IDailyTrainAppService {
	ResponseBaseEntity<DailyAppVo> queryDailyTrainList(DailyAppVo dailyTrainAppVo, String loginName);
	ResponseBaseEntity<DailyAppVo> deleteDailyTrain(DailyAppVo dailyTrainAppVo);
	ResponseBaseEntity<DailyAppVo> addDailyTrain(DailyAppVo dailyTrainAppVo,String loginName);
	ResponseBaseEntity<DailyAppVo> queryDailyTrainInfoById(DailyAppVo dailyTrainAppVo);
	ResponseBaseEntity<DailyAppVo> queryEmpAndDeptName(DailyAppVo dailyAppVo);
}
