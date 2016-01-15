package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.DailyTrainEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

public interface IDailyTrainService {
	List<DailyTrainEntity> queryDailyTrainList(DailyTrainEntity dailyTrain,
			RowBounds rb,UserEntity currentUser);

	Long countqueryDailyTrainList(DailyTrainEntity dailyTrain, RowBounds rb,UserEntity currentUser);
	
	void deleteDailyTrain(List<String> ids);
	
	void addDailyTrain(DailyTrainEntity dailyTrainEntity, UserEntity currentUser);
	
	DailyTrainEntity queryDailyTrainInfoById(DailyTrainEntity dailyTrain);

	List<String> queryEmpNames(String selectorParam);
	
	List<String> queryDeptNames(String selectorParam);
}
