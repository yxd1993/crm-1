package com.hoau.crm.module.sales.api.server.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.appcore.api.shared.domain.FeedBackEntity;

/**
 * 用户反馈SERVICE接口
 * 
 * @author: 潘强
 * @create: 2015年7月15日
 */
public interface IFeedBackInfoService {
	/**
	 * 查询用户反馈信息
	 * 
	 * @param feedBackInfo
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月15日
	 * @update
	 */
	List<FeedBackEntity> getFeedBackInfo(FeedBackEntity feedBackInfo,
			RowBounds rb);

	/**
	 * 统计销售合同总数
	 * 
	 * @param feedBackInfo
	 * @param rb
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月15日
	 * @update
	 */
	Long countFeedBackInfo(FeedBackEntity feedBackInfo, RowBounds rb);

}
