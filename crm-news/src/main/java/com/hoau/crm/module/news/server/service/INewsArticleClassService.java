/**
 * 
 */
package com.hoau.crm.module.news.server.service;

import java.util.List;

import com.hoau.crm.module.news.shared.domain.NewsArticleClassEntity;


/**
 * 文章类接口
 * @author 丁勇
 * @date 2015年9月25日
 */
public interface INewsArticleClassService {
	
	/**
	 * 查询文章类型
	 * @return
	 * @author 丁勇
	 * @date 2015年9月25日
	 * @update 
	 */
	public List<NewsArticleClassEntity> queryArticleClass(String id); 
}
