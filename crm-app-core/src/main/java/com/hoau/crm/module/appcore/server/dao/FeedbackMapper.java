package com.hoau.crm.module.appcore.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.FeedBackEntity;
import com.hoau.crm.module.appcore.api.shared.vo.FeedBackVo;

@Repository
public interface FeedbackMapper {

	/**
	 * 提交反馈信息
	 * 
	 * @param feedBackVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public FeedBackVo userFeedBack(FeedBackEntity feedBackEntity);
}