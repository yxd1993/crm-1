package com.hoau.crm.module.appcore.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 *
 * @author 丁勇
 * @date 2015年11月23日
 */
public class AppSweepCardsException extends BusinessException {
	
	private static final long serialVersionUID = -4867956344313068446L;
	
	public static String SWEEP_INFO_ISNULL  = "crm.appcore.sweepCards.AppSweepCardsEntityIsNull";
	
	public AppSweepCardsException(String code) {
		super();
		super.errCode = code;
	}

	public AppSweepCardsException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public AppSweepCardsException(String code, String msg) {
		super(code, msg);
	}
}
