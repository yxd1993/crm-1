/**
 * 
 */
package com.hoau.crm.module.news.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 *
 * @author 丁勇
 * @date 2015年10月16日
 */
public class NewsException extends BusinessException {

	private static final long serialVersionUID = -3105060077332546373L;
	
	//校验文章的编号是否有效
	public static String ARTICLECLASSIDISNULL = "news.NewsArticle.articleClassIdIsNull";
	
	public NewsException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public NewsException(String code, String msg) {
    	super(code, msg);
    }

    public NewsException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
