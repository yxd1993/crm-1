
/**   
 * @title Exceptionlog.java
 * @package com.deppon.ump.module.platform.server.util
 * @description 
 * @author cbb   
 * @update 2012-10-12 上午8:34:59
 * @version V1.0   
 */
 
package com.hoau.crm.module.customer.api.shared.util;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @description 
 * @version 1.0
 * @author wwn
 * @update 2013-06-11 上午8:34:59 
 */

public class ExceptionLog implements Serializable{
	
    
	/** 
	 * @fields serialVersionUID 
	 */ 
	
	private static final long serialVersionUID = -1894943484680495986L;
	//ID
	private String id;
	//异常位置
	private String exceptionPosition;
	//异常描述
	private String exceptionDescrip;
	//操作人
	private String operator;
	//异常时间
	private Timestamp exceptionTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExceptionPosition() {
		return exceptionPosition;
	}
	public void setExceptionPosition(String exceptionPosition) {
		this.exceptionPosition = exceptionPosition;
	}
	public String getExceptionDescrip() {
		return exceptionDescrip;
	}
	public void setExceptionDescrip(String exceptionDescrip) {
		this.exceptionDescrip = exceptionDescrip;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Timestamp getExceptionTime() {
		return exceptionTime;
	}
	public void setExceptionTime(Timestamp exceptionTime) {
		this.exceptionTime = exceptionTime;
	}			
	
	 
	public ExceptionLog() {
		super();
	}
	
	/**
	 *
	 * @param exceptionPosition
	 * @param exceptionDescrip
	 * @param operator
	 */
	//带参数的构造方法 
	public ExceptionLog(String exceptionPosition, String exceptionDescrip,
			String operator) {
		super();
		this.exceptionPosition = exceptionPosition;
		this.exceptionDescrip = exceptionDescrip;
		this.operator = operator;
	}
}
