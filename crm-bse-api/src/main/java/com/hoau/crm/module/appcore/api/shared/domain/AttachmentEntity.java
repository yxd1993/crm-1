package com.hoau.crm.module.appcore.api.shared.domain;


import com.hoau.hbdp.framework.entity.BaseEntity;
/**
 * 附件信息实体
 * 
 * @author 潘强
 * @date 2015-9-11
 */
public class AttachmentEntity extends BaseEntity {

	private static final long serialVersionUID = 9210490877504693497L;
	
	/**
	 *附件所关联数据的id
	 */
	private String fileId;
	
	/**
	 * 附件描述
	 */
	private String descrip;
	/**
	 * 附件名称
	 */
	private String fileName;
	
	/**
	 * 附件上传地址
	 */
	private String fileUrl;
	
	/**
	 * 附件大小
	 */
	private int fileSize;
	
	/**
	 * 附件顺序
	 */
	private int reorder;
	
	/**
	 * 附件后缀
	 */
	private String postfix;
	
	/**
	 * 图片内容
	 */
	private byte[] imgContent;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}



	public int getReorder() {
		return reorder;
	}

	public void setReorder(int reorder) {
		this.reorder = reorder;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public byte[] getImgContent() {
		return imgContent;
	}

	public void setImgContent(byte[] imgContent) {
		this.imgContent = imgContent;
	}

}
