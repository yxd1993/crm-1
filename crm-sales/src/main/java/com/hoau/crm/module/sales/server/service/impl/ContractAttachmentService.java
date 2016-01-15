package com.hoau.crm.module.sales.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.sales.api.server.service.IContractAttachmentService;
import com.hoau.crm.module.sales.api.server.service.ISaleContractService;
import com.hoau.crm.module.sales.api.shared.vo.ImportParamsVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleContractVo;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 合同附件SERVICE
 * 
 * @author: 何斌
 * @create: 2015年6月12日 上午8:41:39
 */
@Service
public class ContractAttachmentService implements IContractAttachmentService {

	@Resource
	private ISaleContractService saleContractService;
	
	@Resource
	private ICustomerService customerService;
	
	@Override
	public void attacmentImport(ImportParamsVo importParamsVo,File file,String fileName) {
		//当前用户
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		CustomerEntity customerEntity = customerService.queryCustomerInfoByDcAcconut(importParamsVo.getDcAccount());
		if(customerEntity == null){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		SaleContractVo saleContractVo = new SaleContractVo();
		if(BseConstants.YES.equals(importParamsVo.getIsCover())){
			//根据DC查询合同基本信息
			SaleContractVo sVo = new SaleContractVo();
			sVo.setDcAccount(importParamsVo.getDcAccount());
			sVo.setStatus(BseConstants.CONTRACT_STATUS_UPLOADED);
			saleContractVo = saleContractService.getSaleContractVoByDcAccount(sVo).get(0);
		}else{
			saleContractVo.setCrmAccount(customerEntity.getAccountCode());
			saleContractVo.setDcAccount(customerEntity.getDcAccount());
			saleContractVo.setEnterpriseName(customerEntity.getEnterpriseName());
			saleContractVo.setStatus(BseConstants.CONTRACT_STATUS_UPLOADED);
			saleContractVo.setId(importParamsVo.getId());
			saleContractVo.setCreateUser(currentUser.getUserName());
		}
		//判断是新增还是修改
		if(StringUtil.isEmpty(saleContractVo.getId())){
			//将上传的附件保存在服务器本地
			String attachmentUrl = write(saleContractVo, file,fileName);
			//新增销售合同基本信息
			saleContractVo.setUrl(attachmentUrl);
			//创建人
			saleContractService.addContractBaseInfo(saleContractVo);
		}else{
			//将原来的附件删除
			SaleContractVo saleContractVo2 = saleContractService.getAttachmentUrl(saleContractVo.getId());
			File oldFile = new File(saleContractVo2.getUrl());
			//删除原来的文件
			if(oldFile.exists()){
				oldFile.delete();
			}
			String attachmentUrl = write(saleContractVo, file,fileName);
			//更新销售合同基本信息
			saleContractVo.setUrl(attachmentUrl);
			//修改人
			saleContractVo.setModifyUser(currentUser.getUserName());
			saleContractService.updateAttachmentUrl(saleContractVo);
		}
	}

	/**
	 * 将上传的文件写到服务器,并返回附件路径
	 * @param saleContractVo
	 * @param file
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月14日
	 * @update 
	 */
	private String write(SaleContractVo saleContractVo, File file,String fileName) {
		InputStream is = null;
		OutputStream os = null;
		String attachmentUrl = "";
		try {
			//输入
			is = new FileInputStream(file);
			//输出
			try {
				attachmentUrl = AttachmentRootPath.getAttachRootPath()
						+ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("contract.attachment.import.dir")
						+fileName;
				os = new FileOutputStream(attachmentUrl);
				byte[] buffer = new byte[1024];
				int len = 0 ;
				while((len =is.read(buffer))!= -1){
					os.write(buffer,0,len);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return attachmentUrl;
	}

	@Override
	public InputStream attacmentExport(String attachmentUrl) {
		InputStream is = null;
		try {
			is = new FileInputStream(attachmentUrl);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return is;
	}

	@Override
	public InputStream batchExport(List<String> attachmentUrls) {
		String newFile = "";
		try {
			newFile = AttachmentRootPath.getAttachRootPath()
					+ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("contract.attachment.batchExport.dir")
					+"合同附件.zip";
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//输出
		ZipOutputStream zos = null ;
		try {
			zos = new ZipOutputStream(new FileOutputStream(newFile));
			for(String attachmentUrl : attachmentUrls){
				FileInputStream fis = null;
				try {
					//输入
					fis = new FileInputStream(attachmentUrl);
					String fileName = attachmentUrl.substring(attachmentUrl.lastIndexOf("/")+1);
					zos.setEncoding("UTF-8");
					zos.putNextEntry(new ZipEntry(fileName));
					byte[] buffer = new byte[1024];
					int len = 0;
					while((len = fis.read(buffer))!=-1){
						zos.write(buffer, 0, len);
					}
					zos.closeEntry();
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					if(fis!=null){
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} finally{
			if(zos!=null){
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		InputStream is = null;
		try {
			is = new FileInputStream(newFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return is;
	}
}
