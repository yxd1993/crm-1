package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;

import com.hoau.crm.module.login.shared.exception.LoginException;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.message.IMessageBundle;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.define.RestErrorCodeConstants;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * @author：高佳
 * @create：2015年6月1日 下午4:05:49
 * @description：App基础接口
 */
public class AppBaseFacade {
	/**
	 * 登录用户名
	 */
	@HeaderParam("loginName")
	protected String loginName;
	
	/**
	 * 登录用户部门编码
	 */
	@HeaderParam("deptCode")
	protected String deptCode;
	
	/**
	 * APP版本号
	 */
	@HeaderParam("appVersion")
	protected String appVersion;
	
	/**
	 * 设备信息id
	 */
	@HeaderParam("deviceId")
	protected String deviceId;
	
	/**
	 * APP类型
	 */
	@HeaderParam("appType")
	protected String appType;
	
	@Context  
    protected HttpServletRequest request;
	
	/**
     * 用于国际化消息生成
     */
    @Resource
    protected IMessageBundle messageBundle;
    
    @SuppressWarnings("rawtypes")
	protected ResponseBaseEntity returnBusinessError(BusinessException e) {
    	ResponseBaseEntity response = new ResponseBaseEntity();
    	response.setErrorCode(RestErrorCodeConstants.STATUS_BUSSINESS_ERROR);
    	if(StringUtils.isNotBlank(response.getErrorCode())) {
            response.setErrorMessage(messageBundle.getMessage(e.getErrorCode(), e.getErrorArguments()));
        }
		return response;
	}
	
    /**
     * 判断用户是否登录
     * 
     * @author 蒋落琛
     * @date 2015-9-11
     * @update
     */
    protected void isUserLogin(){
    	if(StringUtil.isEmpty(loginName)){
			throw new LoginException(LoginException.LOGININFO_NULL);
		}
    }
}
