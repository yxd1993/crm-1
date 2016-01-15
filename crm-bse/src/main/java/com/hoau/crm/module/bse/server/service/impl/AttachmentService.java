package com.hoau.crm.module.bse.server.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.appcore.api.shared.util.AttachmentUtil;
import com.hoau.crm.module.bse.server.dao.AttachmentMapper;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.crm.module.bse.shared.exception.AttachmentException;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@Service
@SuppressWarnings("rawtypes")
public class AttachmentService implements IAttachmentService {
    @Resource
    private AttachmentMapper  attachmentMapper;
    
	@Override
	@Transactional
	public ResponseBaseEntity addAttachmentEntity(List<AttachmentEntity> attachmentEntityList,String fileId,String imgDir,String loginName) {
		// 返回值
		ResponseBaseEntity<AttachmentEntity> result=new ResponseBaseEntity<AttachmentEntity>();
		if(StringUtil.isEmpty(loginName)||attachmentEntityList==null||attachmentEntityList.size() == 0){
				result.setErrorCode(AttachmentUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
				result.setErrorMessage(AttachmentUtil.REQUEST_PARAMETERS_EXCEPTION);
		}else{
			//遍历list中的AttachmentEntity对象实现对其新增
			 for (int i=0;i<attachmentEntityList.size();i++) {
				 AttachmentEntity attachment = attachmentEntityList.get(i);
				//获取系统根目录
				 String imgName = attachment.getFileName();
				 String rootUrl = AttachmentRootPath.getAttachRootPath();
				 String uuidFileName = UUIDUtil.getUUID();
				 String postfix = imgName.indexOf(".")>0 ? (imgName.substring(imgName.lastIndexOf("."), imgName.length())) : "";
				 String imgUrl = "";
				 String filePath = "";
				 String newAttachPath = "";
				 try {
						imgUrl = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty(imgDir)
								+loginName;
						filePath = rootUrl + imgUrl;
						newAttachPath = filePath + "/" + uuidFileName + postfix;
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				 //保存附件内容
				 this.savaImg(attachment.getImgContent(), filePath,newAttachPath);
				//主键UUID
				 attachment.setId(UUIDUtil.getUUID());
				//新增附件信息
				 attachment.setFileUrl(imgUrl + "/" + uuidFileName + postfix);
				 attachment.setPostfix(postfix);
				 attachment.setReorder(i+1);
				 attachment.setCreateUser(loginName);
				 attachment.setFileId(fileId);
				 attachmentMapper.addAttachment(attachment);
			}
			 result.setErrorCode(AttachmentUtil.EXCEPTION_STATUS_SUCCESS);
		}
		return result;
	}


	@Override
	public List<AttachmentEntity> queryAttachmentByfileId(String fileId) {
		if (!StringUtil.isEmpty(fileId)) {
			return attachmentMapper.getAttachmentByfileId(fileId);
		} else {
			throw new AttachmentException(AttachmentException.FILEID_NULL);
		}
	}
	
	
	/**
	 * 保存图片
	 * @author: 何斌
	 * @date: 2015年9月14日
	 * @update 
	 */
	private void savaImg(byte[] imgContent,String filePath,String newAttachPath){
		OutputStream os = null;
		try {
			File file = new File(filePath);
			// 判断用户目录是否存在，没有则创建
			if(!file.exists()){
				file.mkdir();
			}
			os = new FileOutputStream(new File(newAttachPath));
			// 获取前台传输文件流并写入后台文件
			os.write(imgContent);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
