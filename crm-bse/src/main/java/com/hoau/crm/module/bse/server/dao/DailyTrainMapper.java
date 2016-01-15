package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.DailyTrainEntity;

/**
 * @author 275636
 *每日培训
 */
@Repository
public interface DailyTrainMapper {
	List<DailyTrainEntity> queryDailyTrainList(Map<String, Object> params,
			RowBounds rb);

	Long countqueryDailyTrainList(Map<String, Object> params, RowBounds rb);
	
	void addDailyTrain(DailyTrainEntity dailyTrainEntity);
	
	void deleteDailyTrain(Map<String, Object> map);
	
	DailyTrainEntity queryDailyTrainInfoById(String id);
	
	List<String> queryEmpNames(String selectorParam);
	
	List<String> queryDeptNames(String selectorParam);
}
