/**
 * 
 */
package com.hoau.crm.module.news.server.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.news.server.dao.NewsArticleClassMapper;
import com.hoau.crm.module.news.server.service.INewsArticleClassService;
import com.hoau.crm.module.news.shared.domain.NewsArticleClassEntity;

/**
 *
 * @author 丁勇
 * @date 2015年9月25日
 */
@Service
public class NewsArticleClassService implements INewsArticleClassService {
	
	@Resource
	NewsArticleClassMapper newsArticleClassMapper;
	
	@Override
	public List<NewsArticleClassEntity> queryArticleClass(String id) {
		return newsArticleClassMapper.queryArticleClass(id);
	}

}
