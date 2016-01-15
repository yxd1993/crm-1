package com.hoau.crm.module.customer.api.server;

import java.io.InputStream;

import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;

/**
 * excel导出SERVICE
 * @author: 何斌
 * @create: 2015年5月31日 下午12:03:04
 */
public interface IExcelExportService {
	/**
	 * 将记录转换成excel文件文件输出流
	 * 
	 * @param list
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月31日
	 * @update 
	 */
	InputStream export(CustomerInfoPoolVo customerInfoPoolVo);
}
