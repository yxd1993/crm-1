package com.hoau.crm.module.sales.api.server.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.hoau.crm.module.sales.api.shared.vo.ImportParamsVo;

/**
 * 合同附件SERVICE
 * 
 * @author: 何斌
 * @create: 2015年6月12日 上午8:31:40
 */
public interface IContractAttachmentService {
	/**
	 * 合同附件上传
	 * 
	 * @param saleContractVo
	 * @param in
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	void attacmentImport(ImportParamsVo importParamsVo,File file,String fileName);
	
	/**
	 * 合同附件下载
	 * 
	 * @param attachmentUrl
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	InputStream attacmentExport(String attachmentUrl);
	
	/**
	 * 附件批量下载
	 * 
	 * @param attachmentUrls
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	InputStream batchExport(List<String> attachmentUrls);
}
