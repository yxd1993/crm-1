package com.hoau.crm.module.bse.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

public class AttachmentException extends BusinessException {

	private static final long serialVersionUID = -1784238981012290116L;

	/**
	 * 附件所关联的id不能为空
	 */
	public static final String FILEID_NULL = "crm.bse.attachment.FileIdNullException";
	
	public AttachmentException(String errCode){
		super();
		super.errCode = errCode;
	}

	public AttachmentException(String code, String msg) {
		super(code, msg);
	}
	
	public AttachmentException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}
	
}
