package com.hoau.crm.module.bse.server.service;



import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 附件SERVICE接口
 * 
 * @author: 潘强
 * @create: 2015年9月14日 
 */
@SuppressWarnings("rawtypes")
public interface IAttachmentService {
	/**
	 * 新增附件
	 * 
	 * @param attachmentEntityList,fileId,imgDir,loginName,
	 * @author: 潘强
	 * @date: 2015年9月14日
	 * @update 
	 */
	ResponseBaseEntity addAttachmentEntity(List<AttachmentEntity> attachmentEntityList,String fileId,String imgDir,String loginName);
	
	/**
	 * 根据关联的fileId查询附件信息
	 * 
	 * @param fileId
	 * @return
	 * @author 潘强
	 * @date 2015-9-15
	 * @update
	 */
	public List<AttachmentEntity> queryAttachmentByfileId(String fileId);
}
