/**
 * 
 */
package com.hoau.crm.module.news.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.news.shared.domain.NewsArticleClassEntity;

/**
 * 新闻类dao
 * @author 丁勇
 * @date 2015年9月21日
 */
@Repository
public interface NewsArticleClassMapper {
	/**
	 * 查询文章类型
	 * @return
	 * @author 丁勇
	 * @date 2015年9月25日
	 * @update 
	 */
	public List<NewsArticleClassEntity> queryArticleClass(String id);
}
