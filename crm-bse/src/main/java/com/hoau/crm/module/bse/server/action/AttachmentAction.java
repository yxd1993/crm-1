package com.hoau.crm.module.bse.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年9月14日
 * @description：附件信息action
 */
@Controller
@Scope("prototype")
public class AttachmentAction extends AbstractAction {

	private static final long serialVersionUID = 7622012056108039005L;

	@Resource
	private IAttachmentService attachmentService;
	/**
	 * 附件实体信息
	 */
	private AttachmentEntity attachmentEntity;
	/**
	 * 附件实体信息集合
	 */
	private List<AttachmentEntity> attachmentList;
	/**
	 * 上传附件用户姓名
	 */
	private String loginName;
	/**
	 * 与附件关联的实体id
	 */
	private String fileId;
	/**
	 * 附件上传地址
	 */
	private String imgDir;
	/**
	 * @author：潘强
	 * @create：2015年9月15日
	 * @description：新增附件信息
	 */
	public String addAttachment()
	{
		try {
			List<AttachmentEntity> attList = new ArrayList<AttachmentEntity>();
			attList.add(attachmentEntity);
			//添加附件
			attachmentService.addAttachmentEntity(attList,fileId,imgDir,loginName);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * @author：潘强
	 * @create：2015年9月15日
	 * @description：通过fileId信息查询附件信息
	 */
	public String queryAttachmentList(){
		try {
			attachmentList =attachmentService.queryAttachmentByfileId(fileId) ;
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	
	}
	
	
	public AttachmentEntity getAttachmentEntity() {
		return attachmentEntity;
	}
	public void setAttachmentEntity(AttachmentEntity attachmentEntity) {
		this.attachmentEntity = attachmentEntity;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getImgDir() {
		return imgDir;
	}
	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}

	public List<AttachmentEntity> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AttachmentEntity> attachmentList) {
		this.attachmentList = attachmentList;
	} 
	
}
