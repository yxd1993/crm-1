package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.shared.domain.FeedBackEntity;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.server.dao.FeedBackInfoMapper;
import com.hoau.crm.module.bse.shared.exception.FeedBackInfoException;
import com.hoau.crm.module.sales.api.server.service.IFeedBackInfoService;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 用户反馈SERVICE
 * 
 * @author: 潘强
 * @create: 2015年7月15日
 */
@Service
public class FeedBackInfoService implements IFeedBackInfoService {

	@Resource
	private FeedBackInfoMapper feedBackInfoMapper;

	@Override
	public List<FeedBackEntity> getFeedBackInfo(FeedBackEntity feedBackInfo,
			RowBounds rb) {
		if (rb == null) {
			throw new FeedBackInfoException(
					FeedBackInfoException.QUERY_FEEDBACKINFO_RB_NULL);
		}
		if (feedBackInfo == null) {
			throw new FeedBackInfoException(
					FeedBackInfoException.QUERY_FEEDBACKINFO_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(feedBackInfo.getUserName())) {
			params.put("userName",  "%" + feedBackInfo.getUserName() + "%");
		}
		if (!StringUtil.isEmpty(feedBackInfo.getFbTitle())) {
			params.put("fbTitle",  "%" + feedBackInfo.getFbTitle() + "%");
		}
		if (!StringUtil.isEmpty(feedBackInfo.getFbType())) {
			params.put("fbType", feedBackInfo.getFbType());
		}
		if (!StringUtil.isEmpty(feedBackInfo.getFbContent())) {
			params.put("fbContent", "%" + feedBackInfo.getFbContent() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (feedBackInfo.getFbTime() != null) {
			params.put("fbTime", sdf.format(feedBackInfo.getFbTime()));
		}
		if (feedBackInfo.getFbEndTime() != null) {
			params.put("fbEndTime", sdf.format(BseConstants.getEndDate(feedBackInfo.getFbEndTime())));
		}
		return feedBackInfoMapper.getFeedBackInfo(params, rb);
	}

	@Override
	public Long countFeedBackInfo(FeedBackEntity feedBackInfo, RowBounds rb) {
		if (rb == null) {
			throw new FeedBackInfoException(
					FeedBackInfoException.QUERY_FEEDBACKINFO_RB_NULL);
		}
		if (feedBackInfo == null) {
			throw new FeedBackInfoException(
					FeedBackInfoException.QUERY_FEEDBACKINFO_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if (!StringUtil.isEmpty(feedBackInfo.getUserName())) {
			params.put("userName","%" + feedBackInfo.getUserName() + "%");
		}
		if (!StringUtil.isEmpty(feedBackInfo.getFbTitle())) {
			params.put("fbTitle", "%" + feedBackInfo.getFbTitle() + "%");
		}
		if (!StringUtil.isEmpty(feedBackInfo.getFbType())) {
			params.put("fbType", feedBackInfo.getFbType());
		}
		if (!StringUtil.isEmpty(feedBackInfo.getFbContent())) {
			params.put("fbContent", "%" + feedBackInfo.getFbContent() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (feedBackInfo.getFbTime() != null) {
			params.put("fbTime", sdf.format(feedBackInfo.getFbTime()));
		}
		if (feedBackInfo.getFbEndTime() != null) {
			params.put("fbEndTime", sdf.format(BseConstants.getEndDate(feedBackInfo.getFbEndTime())));
		}
		return feedBackInfoMapper.countFeedBackInfo(params, rb);
	}

}
