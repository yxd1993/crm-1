/**
 * 
 */
package com.hoau.crm.module.news.server.service;

import java.util.List;
import java.util.Map;

import com.hoau.crm.module.news.shared.domain.DataTableObject;
import com.hoau.crm.module.news.shared.domain.NewsArticleEntity;
import com.hoau.crm.module.news.shared.vo.NewsArticleVo;

/**
 *
 * @author 丁勇
 * @date 2015年9月10日
 */
public interface INewsArticleService {
	/**
	 * 查看内容
	 * @return
	 * @author 丁勇
	 * @date 2015年9月21日
	 * @update 
	 */
	public DataTableObject queryNews(Map<String,Object> map);
	
	/**
	 * 添加文章
	 * @param map
	 * @author 丁勇
	 * @date 2015年9月29日
	 * @update 
	 */
	public void addArticle(NewsArticleEntity newsArticleEntity);
	
	/**
	 * 
	 * @param newsArticleEntity
	 * @return
	 * @author 丁勇
	 * @date 2015年9月30日
	 * @update 
	 */
	public NewsArticleEntity queryNewsContent(String id);
	
	
	/**
	 * 用于常见问题页查询界面
	 * @return
	 * @author 丁勇
	 * @date 2015年11月11日
	 * @update 
	 */
	public List<NewsArticleEntity> queryFaqsArticleClass(NewsArticleVo newsArticleVo);
	
}
