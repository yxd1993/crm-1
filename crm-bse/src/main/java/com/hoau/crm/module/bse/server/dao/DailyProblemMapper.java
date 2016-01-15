package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.DailyProblemEntity;

/**
 * @author 275636
 *每日问题
 */
@Repository
public interface DailyProblemMapper {
	List<DailyProblemEntity> queryDailyProblemList(Map<String, Object> params,
			RowBounds rb);

	Long countqueryDailyProblemList(Map<String, Object> params, RowBounds rb);
	
	void addDailyProblem(DailyProblemEntity dailyProblemEntity);
	
	void deleteDailyProblem(Map<String, Object> map);
	
	DailyProblemEntity queryDailyProblemInfoById(String id);

}
