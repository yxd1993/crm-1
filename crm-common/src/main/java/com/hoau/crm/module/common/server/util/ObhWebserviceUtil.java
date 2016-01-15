package com.hoau.crm.module.common.server.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import com.hoau.crm.module.common.server.wsdl.obh.ISyncFacadeService;
import com.hoau.crm.module.common.server.wsdl.obh.SyncService;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * 官网接口工具
 * 
 * @author: 何斌
 * @create: 2015年12月23日 上午10:48:55
 */
public class ObhWebserviceUtil {
	
	public static ISyncFacadeService PORT = ObhWebserviceUtil.getPort(); 
			
	private static ISyncFacadeService getPort(){
		String url = "";
		ISyncFacadeService port = null;
		try {
			url = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("obh.url");
			SyncService service = new SyncService(new URL(url));
			port = service.getSyncFacadeServiceImplPort();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return port;
	}
}
