/**
 * 
 */
package com.hoau.crm.module.sales.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 *
 * @author 丁勇
 * @date 2015年6月11日
 */
public class SalesCommonException extends BusinessException{

	/**
	 *
	 */
	private static final long serialVersionUID = 2358855313005746211L;
	/**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.salesCommon.RbNullException";
	/**
	 *时间参数为空
	 */
	public static final String ADD_DATETIME_ISNULL = "crm.salesCommon.DateTimeIsNullException";
	/**
	 *预约和洽谈时间有误
	 */
	public static final String ADD_DATETIME_INVALID = "crm.saleCommon.saleCommonDateTimeInvalidException";
	/**
	 * 预约信息
	 */
	public static final String ADD_RESVERVER_NULL = "crm.saleReserve.saleReserveNullException";
	/**
	 *预约开始时间有误
	 */
	public static final String ADD_STARTTIME_INVALID = "crm.saleReserve.saleReserveStartTimeInvalidExcepition";
	/**
	 * 删除条件
	 */
	public static final String DEL_DESC_NULL = "crm.salesCommon.delDescNullException";
	/**
	 * 洽谈信息
	 */
	public static final String ADD_CHATS_NULL = "crm.saleChats.saleChatsNullException";
	/**
	 * 签到信息为空
	 */
	public static final String ADD_CHATS_SIGN_NULL = "crm.saleChats.saleChatsSignNullException";
	/**
	 * 此签到信息已经关联洽谈
	 */
	public static final String ADD_CHATS_THERE_SIGN = "crm.saleChats.saleChatsThereSignException";
	/**
	 *新增洽谈时间有误不得大于当前时间
	 */
	public static final String ADD_CHATS_DATETIME_INVALID = "crm.saleCommon.addSaleChats.DateTimeInvalidException";
	/**
	 * 此数据已经删除
	 */
	public static final String UPDATE_IS_DELETE = "crm.salesCommon.updateIsDeleteException";
	/**
	 * 参数为空
	 */
	public static final String PARAM_NULL = "crm.salesCommon.ParamNullException";
	
	/**
	 * 报表数据为空
	 */
	public static final String REPORT_DATA_NULL = "crm.salesCommon.ReportDataNull";
	
	/**
	 * 数据量太大
	 */
	public static final String DATA_TOO_BIG = "crm.customer.customerInfoPool.DataTooBigException";
	
    public SalesCommonException(String code, String msg, Throwable cause) {
    	super(code, msg, cause);
    }

    public SalesCommonException(String code, String msg) {
    	super(code, msg);
    }

    public SalesCommonException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
