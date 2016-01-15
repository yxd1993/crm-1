package com.hoau.crm.module.customer.server.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.customer.api.server.ICustomerInfoPoolService;
import com.hoau.crm.module.customer.api.server.IExcelExportService;
import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 客户信息池导出ACTION
 * 
 * @author: 何斌
 * @create: 2015年5月31日 下午1:10:00
 */
@Controller
@Scope("prototype")
public class CustomerInfoPoolExportAction extends AbstractAction {

	private static final long serialVersionUID = 5645116884832422689L;

	/**
	 * 文件名
	 */
	private String downloadFileName;

	/**
	 * 输入流变量
	 */
	private InputStream excelStream;
	
	/**
	 * 客户信息VO
	 */
	private CustomerInfoPoolVo customerInfoPoolVo;

	@Resource
	private IExcelExportService excelExportService;
	
	@Resource
	private ICustomerInfoPoolService iCustomerInfoPoolService;
	
	/**
	 * 获取下载文件名(中文处理)
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月31日
	 * @update 
	 */
	public String getDownloadFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		downloadFileName = (sf.format(new Date()).toString())
				+ "客户信息池.xls";
		try {
			downloadFileName = encodeFilename(downloadFileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	/**
	 * 获取下载文件流
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月31日
	 * @update 
	 */
	public String excelExport(){
		// 转码
		if(customerInfoPoolVo != null && customerInfoPoolVo.getCustomerInfoPoolEntity() != null){
			try {
				// 公司名称
				if (!StringUtil.isEmpty(customerInfoPoolVo.getCustomerInfoPoolEntity().getCompanyName())) {
					customerInfoPoolVo.getCustomerInfoPoolEntity().setCompanyName(new String(
							customerInfoPoolVo.getCustomerInfoPoolEntity().getCompanyName().getBytes(
									"iso-8859-1"), "UTF-8"));
				} 
				// 联系人
				if (!StringUtil.isEmpty(customerInfoPoolVo.getCustomerInfoPoolEntity().getContactPerson())) {
					customerInfoPoolVo.getCustomerInfoPoolEntity().setContactPerson(new String(
							customerInfoPoolVo.getCustomerInfoPoolEntity().getContactPerson().getBytes(
									"iso-8859-1"), "UTF-8"));
				} 
				// 联系方式
				if (!StringUtil.isEmpty(customerInfoPoolVo.getCustomerInfoPoolEntity().getContactWay())) {
					customerInfoPoolVo.getCustomerInfoPoolEntity().setContactWay(new String(
							customerInfoPoolVo.getCustomerInfoPoolEntity().getContactWay().getBytes(
									"iso-8859-1"), "UTF-8"));
				} 
				// 大区
				if (!StringUtil.isEmpty(customerInfoPoolVo.getCustomerInfoPoolEntity().getRegions())) {
					customerInfoPoolVo.getCustomerInfoPoolEntity().setRegions(new String(
							customerInfoPoolVo.getCustomerInfoPoolEntity().getRegions().getBytes(
									"iso-8859-1"), "UTF-8"));
				}
				// 负责人
				if (!StringUtil.isEmpty(customerInfoPoolVo.getCustomerInfoPoolEntity().getManagePerson())) {
					customerInfoPoolVo.getCustomerInfoPoolEntity().setManagePerson(new String(
							customerInfoPoolVo.getCustomerInfoPoolEntity().getManagePerson().getBytes(
									"iso-8859-1"), "UTF-8"));
				} 
				// 详细地址
				if (!StringUtil.isEmpty(customerInfoPoolVo.getCustomerInfoPoolEntity().getCompanyAddress())) {
					customerInfoPoolVo.getCustomerInfoPoolEntity().setCompanyAddress(new String(
							customerInfoPoolVo.getCustomerInfoPoolEntity().getCompanyAddress().getBytes(
									"iso-8859-1"), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try {
			excelStream = excelExportService.export(customerInfoPoolVo);
		} catch (BusinessException e) {
			return ERROR;
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
	
	public String execute(){
		return returnSuccess();
	}
	
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public CustomerInfoPoolVo getCustomerInfoPoolVo() {
		return customerInfoPoolVo;
	}

	public void setCustomerInfoPoolVo(CustomerInfoPoolVo customerInfoPoolVo) {
		this.customerInfoPoolVo = customerInfoPoolVo;
	}
	
}
