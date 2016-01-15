package com.hoau.crm.module.appitf.exception;

import java.util.Locale;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.message.IMessageBundle;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 接口返回异常处理类
 *
 * @author 蒋落琛
 * @date 2015-6-12
 */
@Provider
public class CXFExceptionHandler implements ExceptionMapper<Throwable> {
	
	/**
     * 用于国际化消息生成
     */
    @Resource
    protected IMessageBundle messageBundle;
    
	public Response toResponse(Throwable ex) {
		ResponseBuilder rb = Response
				.status(Response.Status.INTERNAL_SERVER_ERROR);
		rb.type("application/json;charset=UTF-8");
		@SuppressWarnings("rawtypes")
		ResponseBaseEntity entity = new ResponseBaseEntity();
		if (ex instanceof BusinessException) {// 自定义的异常类
			ex.printStackTrace();
			BusinessException e = (BusinessException) ex;
			entity.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
			entity.setErrorMessage(convert(messageBundle.getMessage(Locale.SIMPLIFIED_CHINESE, e.getErrorCode(), e.getErrorArguments())));
			rb.entity(entity);
		} else {
			ex.printStackTrace();
			entity.setErrorCode(AppUtil.EXCEPTION_STATUS_SYSTEM_ERROR);
			entity.setErrorMessage("系统异常");
			rb.entity(entity);
		}
		rb.language(Locale.SIMPLIFIED_CHINESE);
		Response r = rb.build();
		return r;
	}

	/**
	 * UNICODE转换成中文
	 * 
	 * @param utfString
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-18
	 * @update
	 */
	private String convert(String utfString){  
	    StringBuilder sb = new StringBuilder();  
	    int i = -1;  
	    int pos = 0;  
	    while((i=utfString.indexOf("\\u", pos)) != -1){  
	        sb.append(utfString.substring(pos, i));  
	        if(i+5 < utfString.length()){  
	            pos = i+6;  
	            sb.append((char)Integer.parseInt(utfString.substring(i+2, i+6), 16));  
	        }  
	    }  
	    return sb.toString();  
	}  
}
