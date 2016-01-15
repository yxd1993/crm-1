package com.hoau.crm.module.sales.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;
/**
 * 会议签到Exception
 * 
 * @author: 潘强
 * @create: 2015年9月9日 
*/
public class MeetingSignException extends BusinessException {

	private static final long serialVersionUID = -247373531099311176L;

	/**
	 *分页参数为空 
	 */
	public static final String QUERY_MEETINGSIGN_RB_NULL="crm.sales.meetingsign.queryMeetingSignRbNullException";
	
	/**
	 *查询参数为空 
	 */
	public static final String  QUERY_MEETINGSIGN_PARAM_NULL="crm.sales.meetingsign.queryMeetingSignParamNullException";

	/**
	 * 新增会议签到信息为空
	 */
	public static final String ADD_MEETINGSIGN_NULL = "crm.sales.meetingsign.addMeetingSignNullException";

	/**
	 * 新增会议签到时被扫描人信息为空
	 */
	public static final String ADD_MEETINGSIGN_MEETINGSIGNLIST_NULL = "crm.sales.meetingsign.addMeetingSignListNullException";
	
	/**
	 * 新增会议签到时附件信息为空
	 */
	public static final String ADD_MEETINGSIGN_ATTACHMENTLIST_NULL = "crm.sales.meetingsign.addMeetingSignAttachmentListNullException";

	/**
	 * 新增会议签到时附件保存地址为空
	 */
	public static final String ADD_IMGDIR_NULL = "crm.sales.meetingsign.addMeetingSignImgDirNullException";
	
	/**
	 * 新增会议签到时登录人姓名信息为空
	 */
	public static final String ADD_LOGINNAME_NULL="crm.sales.meetingsign.addMeetingSignLoginNameNullException";
	
	/**
	 * 扫描人信息为空
	 */
	public static final String ADD_SWEEPPEOP_NULL="crm.sales.meetingsign.addMeetingSignSweeppeopNullException";
	
	/**
	 * 会议类型为空
	 */
	public static final String MEETINGTYPE_NULL="crm.sales.meetingsign.meetingTypeNullException";
	
	/**
	  * 功能异常类定义
	  * @param errCode
	  * @since
	 */
	public MeetingSignException(String errCode){
		super();
		super.errCode = errCode;
	}

	public MeetingSignException(String code, String msg) {
		super(code, msg);
	}
	
	public MeetingSignException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
}
