package com.hoau.crm.module.appitf.interceptor;


import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Component;

import com.hoau.crm.module.appcore.api.shared.domain.OperateRecord;
import com.hoau.crm.module.appcore.server.dao.OperateRecordMapper;
import com.hoau.crm.module.bse.api.server.service.IAuthorizationService;
import com.hoau.crm.module.bse.api.shared.domain.AuthorizationEntity;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 拦截器，记录调用日志
 *
 * @author 蒋落琛
 * @date 2015-6-12
 */
@Component
public class OperateRecordsInterceptor extends AbstractPhaseInterceptor<Message> {

	private static final Log LOG = LogFactory.getLog(OperateRecordsInterceptor.class);
	
	@Resource
	private OperateRecordMapper operateRecordMapper;
	
	@Resource
	private IAuthorizationService iAuthorizationService;
	
	public OperateRecordsInterceptor() {
		this(Phase.PRE_STREAM);
	}

	public OperateRecordsInterceptor(String phase) {
		super(phase);
	}

	@SuppressWarnings("unchecked")
	public void handleMessage(Message paramT) throws Fault {
		HttpServletRequest request = (HttpServletRequest) paramT.get(AbstractHTTPDestination.HTTP_REQUEST);
		String loginName = request.getHeader("loginname");
		String uri = request.getRequestURI();
		OperateRecord record = new OperateRecord();
		String reqMethod = request.getMethod();
		record.setUri(uri);
		record.setRequestMethod(reqMethod);
		record.setUserCode(loginName);
		record.setAppVersion(request.getHeader("appversion"));
		record.setDeviceId(request.getHeader("deviceid"));
		record.setAppType(request.getHeader("apptype"));
		try{
			// 记录用户授权
			if(!StringUtil.isEmpty(loginName)){
				AuthorizationEntity auth = iAuthorizationService.getAuthrizationByLoginUser(loginName);
				if(auth != null){
					TreeMap<String, Object> o = (TreeMap<String, Object>)paramT.get(Message.PROTOCOL_HEADERS);
					List<String> l = (List<String>)o.get("loginname");
					l.set(0, auth.getAuthorizedPerson());
					o.put("loginname",l);
					// 授权人与被授权人
					record.setUserCode(auth.getAuthorizedPerson());
					record.setWasAuthorizedPerson(auth.getWasAuthorizedPerson());
				}
			}
			operateRecordMapper.saveRecord(record);
		}catch(Exception e){
			LOG.error("日志记录异常");
			e.printStackTrace();
		}
	}
	
}
