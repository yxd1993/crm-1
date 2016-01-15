package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 运单EXCEPTION
 *
 * @author 蒋落琛
 * @date 2015-7-6
 */
public class WayBillException extends BusinessException {

	private static final long serialVersionUID = -1982586715098015347L;

	/**
	 * 运单信息不能为空
	 */
	public static final String Way_Bill_NULL = "com.crm.bse.waybill.WayBillNullException";

	public WayBillException(String code) {
		super();
		super.errCode = code;
	}

	public WayBillException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public WayBillException(String code, String msg) {
		super(code, msg);
	}
}
