package com.hoau.crm.module.bse.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;

/**
 * @author：潘强
 * @create：2015年9月14日
 * @description：
 */
@Repository
public interface AttachmentMapper {
	/**
	 * 新增附件信息
	 * @param attachmentEntity
	 * @author 潘强
	 * @date 2015年9月14日
	 * @update 
	 */
	void addAttachment(AttachmentEntity attachmentEntity);
	
	/**
	 * 通过fileId查询附件信息
	 * @param fileId
	 * @author 潘强
	 * @date 2015年9月15日
	 * @update 
	 */
	public List<AttachmentEntity> getAttachmentByfileId(String fileId);
}
