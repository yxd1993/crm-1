/**
 * 
 */
package com.hoau.crm.module.news.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 *	内容类
 * @author 丁勇
 * @date 2015年9月21日
 */
public class NewsArticleEntity extends BaseEntity {

	private static final long serialVersionUID = 2708883261971129841L;
	
	/**
	 * 分类
	 */
	private NewsArticleClassEntity newsArticleClass;
	
	/**
	 * 类型
	 */
	private String articleType;
	/**
	 * 标题
	 */
	private String articleTitle;
	/**
	 * 简介
	 */
	private String articleSummary;
	/**
	 * 内容
	 */
	private String articleContent;
	/**
	 * 点击率
	 */
	private int articleHits;
	/**
	 *静态文章路径
	 */
	private String contentPath;
	/**
	 *是否有效
	 */
	private String active;
	
	public NewsArticleClassEntity getNewsArticleClass() {
		return newsArticleClass;
	}
	public void setNewsArticleClass(NewsArticleClassEntity newsArticleClass) {
		this.newsArticleClass = newsArticleClass;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleSummary() {
		return articleSummary;
	}
	public void setArticleSummary(String articleSummary) {
		this.articleSummary = articleSummary;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	
	public int getArticleHits() {
		return articleHits;
	}
	public void setArticleHits(int articleHits) {
		this.articleHits = articleHits;
	}
	
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
