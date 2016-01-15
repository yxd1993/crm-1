/**
 * 
 */
package com.hoau.crm.module.news.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.news.shared.domain.NewsArticleEntity;

/**
 * 文章dao
 * @author 丁勇
 * @date 2015年9月21日
 */
@Repository
public interface NewsArticleMapper {
	/**
	 * 查看文章
	 * @return
	 * @author 丁勇
	 * @date 2015年9月21日
	 * @update 
	 */
	public List<?> queryNews(Map<String,Object> map,RowBounds rb);
	/**
	 * 查询总数
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年9月23日
	 * @update 
	 */
	public long queryNewsCount(Map<String,Object> map);
	
	/**
	 * 保存文章
	 * @param newsArticleEntity
	 * @author 丁勇
	 * @date 2015年9月29日
	 * @update 
	 */
	public void addArticle(NewsArticleEntity newsArticleEntity);
	
	/**
	 * 查看文章内容
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年9月30日
	 * @update 
	 */
	public NewsArticleEntity queryNewsContent(String id);
	
	/**
	 * 更新文章点击率
	 * @param id
	 * @author 丁勇
	 * @date 2015年10月16日
	 * @update 
	 */
	public void updateArticleHits(String id);
}
