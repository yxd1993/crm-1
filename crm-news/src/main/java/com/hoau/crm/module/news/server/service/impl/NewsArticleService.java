/**
 * 
 */
package com.hoau.crm.module.news.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.news.server.dao.NewsArticleMapper;
import com.hoau.crm.module.news.server.service.INewsArticleService;
import com.hoau.crm.module.news.shared.domain.DataTableObject;
import com.hoau.crm.module.news.shared.domain.NewsArticleClassEntity;
import com.hoau.crm.module.news.shared.domain.NewsArticleEntity;
import com.hoau.crm.module.news.shared.exception.NewsException;
import com.hoau.crm.module.news.shared.vo.NewsArticleVo;

/**
 * 
 * @author 丁勇
 * @date 2015年9月10日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class NewsArticleService implements INewsArticleService {
	@Resource
	NewsArticleMapper newsArticleMapper;
	
	Map<String,Object> params;
	
	@SuppressWarnings("unchecked")
	@Override
	public DataTableObject queryNews(Map<String, Object> map) {
		//分页参数
		RowBounds rb = new RowBounds(Integer.valueOf(map.get("start")
				.toString()), Integer.valueOf(map.get("length").toString()));
		//获取 数据
		List<NewsArticleEntity> datalist = (List<NewsArticleEntity>) newsArticleMapper
				.queryNews(map, rb);
		//查询总数
		long count = newsArticleMapper.queryNewsCount(map);
		// 根据分页条件查询相关的数据
		DataTableObject dataTableObject = new DataTableObject(
				Integer.valueOf(map.get("draw").toString()),
				Integer.valueOf(map.get("start").toString()),
				Integer.valueOf(map.get("length").toString()), count, count,
				datalist,null);
		return dataTableObject;
	}

	@Override
	@Transactional
	public void addArticle(NewsArticleEntity newsArticleEntity) {
		if(StringUtils.isEmpty(newsArticleEntity.getNewsArticleClass())||StringUtils.isEmpty(newsArticleEntity.getNewsArticleClass().getId())){
			throw new NewsException(NewsException.ARTICLECLASSIDISNULL);
		}
		UUID uid = UUID.randomUUID();
		newsArticleEntity.setId(uid.toString());
		NewsArticleClassEntity nac = newsArticleEntity.getNewsArticleClass();
		if(!StringUtils.isEmpty(newsArticleEntity.getNewsArticleClass().getChildAricleId())){
			nac.setId(newsArticleEntity.getNewsArticleClass().getChildAricleId());
		}
		newsArticleEntity.setNewsArticleClass(nac);
		newsArticleEntity.setArticleType("【"+newsArticleEntity.getArticleType()+"】");
		newsArticleMapper.addArticle(newsArticleEntity);
	}

	@Override
	@Transactional
	public NewsArticleEntity queryNewsContent(String id) {
		newsArticleMapper.updateArticleHits(id);
		return newsArticleMapper.queryNewsContent(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsArticleEntity> queryFaqsArticleClass(NewsArticleVo newsArticleVo) {
		params = new HashMap <String, Object>();
		if(!StringUtils.isEmpty(newsArticleVo.getSearchValue())){
			params.put("searchValue","%"+ newsArticleVo.getSearchValue()+"%");
		}
		params.put("parentId", "4");
		return (List<NewsArticleEntity>) newsArticleMapper.queryNews(params,new RowBounds());
	}

}
