package com.hoau.crm.module.appcore.api.shared.vo;

import com.hoau.crm.module.appcore.api.shared.domain.FeedBackEntity;

/**
 * 反馈信息VO
 *
 * @author 蒋落琛
 * @date 2015-6-26
 */
public class FeedBackVo{

	/**
	 * 反馈信息
	 */
	private FeedBackEntity feedBackEntity;

	public FeedBackEntity getFeedBackEntity() {
		return feedBackEntity;
	}

	public void setFeedBackEntity(FeedBackEntity feedBackEntity) {
		this.feedBackEntity = feedBackEntity;
	}
}
