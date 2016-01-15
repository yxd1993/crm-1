/**
 * 
 */
package com.hoau.crm.module.news.shared.vo;

import java.io.Serializable;
/**
 *
 * @author 丁勇
 * @date 2015年11月13日
 */
public class NewsArticleVo  implements Serializable{
	
	private static final long serialVersionUID = -5464147902364217617L;
	
	/**
	 *查询条件
	 */
	private String searchValue;
	
	/**
	 *文章内容
	 */
	private String body;
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
