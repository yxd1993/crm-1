package com.hoau.crm.module.customer.server.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.customer.api.server.ITableUploadService;
import com.hoau.crm.module.customer.api.shared.vo.AttachResource;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.components.security.SecurityNonCheckRequired;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 上传Action
 * 
 * @author: 何斌
 * @create: 2015年5月25日 下午5:15:28
 */
@Controller
@Scope("prototype")
public class TableUploadAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6262787644853170743L;

	@Resource
	private ITableUploadService uploadService;

	/**
	 * 上传文件
	 */
	private File file;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 附件信息实体
	 */
	private AttachResource resource;

	/**
	 * 流传输
	 */
	private InputStream inputName;
	
	@SecurityNonCheckRequired
	public String toUpload() {
		return returnSuccess();
	};

	/**
	 * 
	 * 上传
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月26日
	 * @update
	 */
	public String tableUpload() {
		try {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
				uploadService.tableUpload(in);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch(BusinessException e){
			return returnError(e);
		}	
		return returnSuccess();
	}

	/**
	 * 附件下载
	 * 
	 * @return
	 * @throws IOException
	 * @author 蒋落琛
	 * @date 2015-5-27
	 * @update
	 */
	public String downloadFile() {
		// 首先判断，需要下载的附件名称是否为空，如果不为空则直接给filename赋值
		if (resource != null && !StringUtil.isEmpty(resource.getAttachname())) {
			fileName = "客户信息.xlsx";
			// 如果为空则自定义一个文件名称给这个附件
		} else {
			int index = resource.getAttachpath().lastIndexOf("/");
			fileName = resource.getAttachpath().substring(index + 1);
			resource.setAttachname(fileName);
		}
		try {
			resource.setAttachpath(new String(resource.getAttachpath()
					.getBytes("iso-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			fileName = encodeFilename(fileName);
		} catch (UnsupportedEncodingException e) {
			return "fileNotExist";
		}
		// 文件上传根目录
		String rootPath = AttachmentRootPath.getAttachRootPath();
		File file = new File(rootPath + resource.getAttachpath());
		if (file.isFile()) {
			try {
				// 把文件写入流，供客户端下载
				inputName = new FileInputStream(file);
				// 如果下载失败，则抛出异常
			} catch (FileNotFoundException e) {
				return "fileNotExist";
			}
		}
		return "download";
	}

	/**
	 * 为附件名称转码
	 * 
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author 蒋落琛
	 * @date 2015-5-27
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

	public String execute() {
		return returnSuccess();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public AttachResource getResource() {
		return resource;
	}

	public void setResource(AttachResource resource) {
		this.resource = resource;
	}

	public InputStream getInputStream() {
		return inputName;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputName = inputStream;
	}

}
