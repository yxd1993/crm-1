package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.FeedBackEntity;

/**
 * 用户反馈DAO
 * 
 * @author: 潘强
 * @create: 2015年7月15日
 */

@Repository
public interface FeedBackInfoMapper {
	/**
	 * 查询用户反馈信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月15日
	 * @update
	 */
	List<FeedBackEntity> getFeedBackInfo(Map<String, Object> params,
			RowBounds rb);

	/**
	 * 统计用户反馈信息总数
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月15日
	 * @update
	 */
	Long countFeedBackInfo(Map<String, Object> params, RowBounds rb);

}
