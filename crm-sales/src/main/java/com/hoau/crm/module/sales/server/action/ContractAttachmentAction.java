package com.hoau.crm.module.sales.server.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.sales.api.server.service.IContractAttachmentService;
import com.hoau.crm.module.sales.api.server.service.ISaleContractService;
import com.hoau.crm.module.sales.api.shared.vo.ImportParamsVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleContractVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.components.security.SecurityNonCheckRequired;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 合同附件ACTIVE
 * @author: 何斌
 * @create: 2015年6月12日 上午9:19:35
 */
/**
 * @author: 何斌
 * @create: 2015年6月12日 上午10:55:09
 */
@Controller
@Scope("prototype")
public class ContractAttachmentAction extends AbstractAction {

	private static final long serialVersionUID = 8475897897012426335L;

	/**
	 * 下载文件名
	 */
	private String fileName;
	
	/**
	 * 批量下载文件名
	 */
	private String batchFileName;
	
	/**
	 * 上传文件
	 */
	private File file;
	
	/**
	 * 上传真实文件名
	 */
	private String fileFileName;
	
	/**
	 * 上传参数VO 
	 */
	private ImportParamsVo importParamsVo;
	
	/**
	 * 合同主键
	 */
	private String id;
	
	/**
	 * 合同批量下载id集合
	 */
	private List<String> ids;
	
	private String exportIds;
	
	private SaleContractVo saleContractVo;
	
	private InputStream inputStream;
	
	private InputStream batchInputStream;

	@Resource
	private IContractAttachmentService contractAttachmentService;
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private ISaleContractService saleContractService;
	
	/**
	 * 
	 * 去上传页面(测试)
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String toUpload(){
		return returnSuccess();
	}
	
	/**
	 * 上传合同附件
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	public String importAttachment(){
		try {
			contractAttachmentService.attacmentImport(importParamsVo, file,fileFileName);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 下载合同附件
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	public String exportAttachment(){
		try {
			String url = saleContractService.getAttachmentUrl(id).getUrl();
			try {
				fileName = this.encodeFilename(url.substring(url.lastIndexOf("/")+1));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			inputStream = contractAttachmentService.attacmentExport(url);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 批量下载合同附件
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	public String batchExportAttachment(){
		try {
			ids = new ArrayList<String>();
			String[] strs = exportIds.split(",");
			for(int i=0;i < strs.length;i++){
				ids.add(strs[i]);
			}
			List<String> urls = saleContractService.getAllAttachmentUrl(ids);
			batchInputStream = contractAttachmentService.batchExport(urls);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	
	/**
	 * 为附件名称转码
	 * 
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author 何斌
	 * @date 2015-5-31
	 * @update
	 */
	private String encodeFilename(String fileName)
			throws UnsupportedEncodingException {
		try {
			// 支持谷歌
			fileName = URLEncoder.encode(fileName, "UTF8");
			// 如果在转换编码的时候出现异常了，则执行下面的转换操作。把UTF8的编码转换成iso-8859-1
		} catch (UnsupportedEncodingException e) {
			try {
				//
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
				// 抛出异常
			} catch (UnsupportedEncodingException e1) {
				throw new UnsupportedEncodingException();
			}
			throw new UnsupportedEncodingException();
		}
		return fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getBatchFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		batchFileName = (sf.format(new Date()).toString())
				+ "合同附件.zip";
		try {
			batchFileName = encodeFilename(batchFileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return batchFileName;
	}

	public void setBatchFileName(String batchFileName) {
		this.batchFileName = batchFileName;
	}

	public InputStream getBatchInputStream() {
		return batchInputStream;
	}

	public void setBatchInputStream(InputStream batchInputStream) {
		this.batchInputStream = batchInputStream;
	}

	public SaleContractVo getSaleContractVo() {
		return saleContractVo;
	}

	public void setSaleContractVo(SaleContractVo saleContractVo) {
		this.saleContractVo = saleContractVo;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public ImportParamsVo getImportParamsVo() {
		return importParamsVo;
	}

	public void setImportParamsVo(ImportParamsVo importParamsVo) {
		this.importParamsVo = importParamsVo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getExportIds() {
		return exportIds;
	}

	public void setExportIds(String exportIds) {
		this.exportIds = exportIds;
	}

}
