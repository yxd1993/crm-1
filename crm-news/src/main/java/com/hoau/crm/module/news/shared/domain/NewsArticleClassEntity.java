/**
 * 
 */
package com.hoau.crm.module.news.shared.domain;

import java.util.Set;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 分类
 * @author 丁勇
 * @date 2015年9月21日
 */
public class NewsArticleClassEntity extends BaseEntity {

	private static final long serialVersionUID = -7818969127262651913L;

	/**
	 *类型名称
	 */
	private String articleClass;
	
	/**
	 *子id
	 */
	private String childAricleId;
	/**
	 *子集合
	 */
	private Set<NewsArticleClassEntity> childArticleClass;
	
	public String getArticleClass() {
		return articleClass;
	}
	public void setArticleClass(String articleClass) {
		this.articleClass = articleClass;
	}
	public String getChildAricleId() {
		return childAricleId;
	}
	public void setChildAricleId(String childAricleId) {
		this.childAricleId = childAricleId;
	}
	public Set<NewsArticleClassEntity> getChildArticleClass() {
		return childArticleClass;
	}
	public void setChildArticleClass(Set<NewsArticleClassEntity> childArticleClass) {
		this.childArticleClass = childArticleClass;
	}
	
	
}
