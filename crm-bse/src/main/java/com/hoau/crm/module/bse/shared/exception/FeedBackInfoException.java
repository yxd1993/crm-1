package com.hoau.crm.module.bse.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 用户反馈Exception
 * 
 * @author: 潘强
 * @create: 2015年7月15日 
 */
public class FeedBackInfoException extends BusinessException 
{
	private static final long serialVersionUID = -6650601415446699037L;

	/**
	 * 分页参数为空
	 */
	public static final String QUERY_FEEDBACKINFO_RB_NULL="crm.bse.feedBackInfo.queryFeedBackInfoRbNullException";
	
	/**
	 * 查询参数为空
	 */
	public static final String QUERY_FEEDBACKINFO_PARAM_NULL="crm.bse.feedBackInfo.queryFeedBackInfoParamNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public FeedBackInfoException(String errCode){
		super();
		super.errCode = errCode;
	}

	public FeedBackInfoException(String code, String msg) {
		super(code, msg);
	}
	
	public FeedBackInfoException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
