/**
 * 
 */
package com.hoau.crm.module.bse.server.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.server.service.IServerStatusService;
import com.hoau.crm.module.bse.shared.domain.ServerStatusEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 *
 * @author 丁勇
 * @date 2015年8月13日
 */
@Controller
@Scope("prototype")
public class ServerStatusAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 5594582030922947516L;
	
	@Resource
	IServerStatusService IserverStatusService;
	
	ServerStatusEntity serverStatusEntity = new ServerStatusEntity();
	/**
	 *连接数结果集
	 */
	List<Map<String,Object>> onlineConnects = new ArrayList<Map<String,Object>>();
	
	public String queryServerConnects(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			//查询结果集
			List<ServerStatusEntity> result = IserverStatusService.queryServerConnects(serverStatusEntity);
			//封装返回参数
			for (ServerStatusEntity model : result) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("createDate",sdf.format(model.getCreateDate()));
				map.put("activeConnections",Long.valueOf(model.getActiveConnections()));
				map.put("writing", Long.valueOf(model.getWriting()));
				onlineConnects.add(map);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}

	public List<Map<String, Object>> getOnlineConnects() {
		return onlineConnects;
	}

	public void setOnlineConnects(List<Map<String, Object>> onlineConnects) {
		this.onlineConnects = onlineConnects;
	}

	public ServerStatusEntity getServerStatusEntity() {
		return serverStatusEntity;
	}

	public void setServerStatusEntity(ServerStatusEntity serverStatusEntity) {
		this.serverStatusEntity = serverStatusEntity;
	}
	

}
