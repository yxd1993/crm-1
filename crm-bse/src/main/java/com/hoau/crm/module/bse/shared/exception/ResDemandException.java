package com.hoau.crm.module.bse.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

public class ResDemandException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *分页参数为空 
	 */
	public static final String QUERY_RESDEMAND_RB_NULL="crm.bas.resDemand.queryResDemandListRbNullException";
	
	/**
	 *查询参数为空 
	 */
	public static final String  QUERY_RESDEMAND_PARAM_NULL="crm.bas.resDemand.queryResDemandListParamNullException";
	
	/**
	 * 新增资源需求不能为空
	 */
	public static final String  ADD_RESDEMAND_NULL="crm.bas.resDemand.addResdemandNullException";
	
	/**
	 * 新增每日会议不能为空
	 */
	public static final String  ADD_DAILY_MEETING_NULL="crm.bas.daily.addDailyMeetingNullException";
	
	/**
	 * 新增每日问题不能为空
	 */
	public static final String  ADD_DAILY_PROBLEM_NULL ="crm.bas.daily.addDailyProblemNullException";
	
	/**
	 * 新增每日培训不能为空
	 */
	public static final String ADD_DAILY_TRAIN_NULL ="crm.bas.daily.addDailyTrainNullException";
	
	/**
	 * 主键不能为空
	 * */
	public static final String ADD_RESDEMAND_ID_NULL="crm.bas.resDemand.addResdemandByIdNullException";

	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public ResDemandException(String errCode){
		super();
		super.errCode = errCode;
	}

	public ResDemandException(String code, String msg) {
		super(code, msg);
	}
	
	public ResDemandException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
