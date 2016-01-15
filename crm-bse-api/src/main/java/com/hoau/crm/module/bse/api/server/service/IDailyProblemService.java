package com.hoau.crm.module.bse.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.DailyProblemEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;

public interface IDailyProblemService {
	List<DailyProblemEntity> queryDailyProblemList(DailyProblemEntity dailyProblem,
			RowBounds rb,UserEntity currentUser);

	Long countqueryDailyProblemList(DailyProblemEntity dailyProblem, RowBounds rb,UserEntity currentUser);
	
	void deleteDailyProblem(List<String> ids);
	
	void addDailyProblem(DailyProblemEntity dailyProblemEntity, UserEntity currentUser);
	
	DailyProblemEntity queryDailyProblemInfoById(DailyProblemEntity dailyProblem);

}
