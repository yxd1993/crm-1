package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.vo.ReviewHistoryVo;

/**
 * 
 * @author 丁勇
 * @date 2015年6月24日
 */
@Repository
public interface ReviewHistoryMapper {
	/**
	 * 保存操作记录
	 * 
	 * @param reviewhistoryEntity
	 * @author 丁勇
	 * @date 2015年7月14日
	 * @update
	 */
	public void insertReviewOrHistory(ReviewHistoryEntity reviewhistoryEntity);

	/**
	 * app端 分页查询客户历史跟进操作
	 * 
	 * @param map
	 *            查询参数
	 * @param rb
	 *            分页参数
	 * @return
	 * @author 丁勇
	 * @date 2015年7月14日
	 * @update
	 */
	public List<ReviewHistoryVo> queryHistoryList(Map<String, Object> map,
			RowBounds rb);
	/**
	 * web端 分页查询历史跟进信息
	 * @param accountId
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年9月8日
	 * @update 
	 */
	public List<Map<String,Object>> queryHistoryListByWeb(String accountId,RowBounds rb);
	/**
	 * 历史跟进操作总数
	 * 
	 * @param map
	 *            查询参数
	 * @return
	 * @author 丁勇
	 * @date 2015年7月14日
	 * @update
	 */
	public long queryHistoryCount(Map<String, Object> map);

	/**
	 * app端 分页查询工作回顾
	 * 
	 * @param map
	 *            查询参数
	 * @param rb
	 *            分页参数
	 * @return
	 * @author 丁勇
	 * @date 2015年7月14日
	 * @update
	 */
	public List<ReviewHistoryVo> queryReviewList(Map<String, Object> map,
			RowBounds rb);

	/**
	 * 工作回顾信息总数
	 * 
	 * 
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月14日
	 * @update
	 */
	public long queryReviewCount(Map<String, Object> map);
}
