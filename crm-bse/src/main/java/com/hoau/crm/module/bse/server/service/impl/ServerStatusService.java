/**
 * 
 */
package com.hoau.crm.module.bse.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.bse.server.dao.ServerStatusMapper;
import com.hoau.crm.module.bse.server.service.IServerStatusService;
import com.hoau.crm.module.bse.shared.domain.ServerStatusEntity;

/**
 * nginx 服务器状态接口实现
 * @author 丁勇
 * @date 2015年8月13日
 */
@Service
public class ServerStatusService implements IServerStatusService {
	@Resource
	ServerStatusMapper ssm;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public List<ServerStatusEntity> queryServerConnects(ServerStatusEntity sse) {
		//查询时间
		Map<String,Object> map = new HashMap<String, Object>();
		//判断时间是否为空
		if(StringUtils.isEmpty(sse.getCreateDate())){
			map.put("createDate",sdf.format(new Date()));
		}else{
			map.put("createDate",sdf.format(sse.getCreateDate()));
		}
		return ssm.queryServerConnects(map);
	}
}
