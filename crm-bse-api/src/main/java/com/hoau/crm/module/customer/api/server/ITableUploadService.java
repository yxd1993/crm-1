package com.hoau.crm.module.customer.api.server;

import java.io.InputStream;

import com.hoau.hbdp.framework.service.IService;

/**
 * 上传SERVICE
 * @author: 何斌
 * @create: 2015年5月25日 下午2:56:01
 */
public interface ITableUploadService extends IService {
	
	/**
	 * 批量上传处理
	 * @param in
	 * @author: 何斌
	 * @date: 2015年5月25日
	 * @update 
	 */
	void tableUpload(InputStream in);

}
