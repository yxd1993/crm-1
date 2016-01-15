package com.hoau.crm.module.customer.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 上传EXCEPTION
 * @author: 何斌
 * @create: 2015年5月29日 上午8:38:02
 */
public class TableUploadException extends BusinessException{

	private static final long serialVersionUID = 2109004449154385280L;
	
	/**
	 * 上传表格记录为null
	 */
	public static final String TABLE_RECORD_NULL = "crm.customer.TableUpload.TableRecordNullException";
	
	/**
	 * 上传表格列数不对
	 */
	public static final String TABLE_COLUMN_ERROR = "crm.customer.TableUpload.TableColumnErrorException";

	/**
	 * 上传表格表名不对
	 */
	public static final String TABLE_SHEET_ERROR = "crm.customer.TableUpload.TableSheetErrorException";
	
	/**
	 * 	表格内字段值不能为空
	 */
	public static final String TABLE_COLUMN_RECORD_NULL = "crm.customer.TableUpload.TableColumnRecordNullException";

	public TableUploadException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public TableUploadException(String code, String msg) {
		super(code, msg);
	}

	public TableUploadException(String errCode) {
		super();
		this.errCode = errCode;
	}
}
