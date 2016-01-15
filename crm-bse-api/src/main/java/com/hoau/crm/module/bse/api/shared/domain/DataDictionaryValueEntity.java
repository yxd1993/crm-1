package com.hoau.crm.module.bse.api.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 数据字典实体类
 * @author 高佳
 * @date 2015年5月14日
 */
public class DataDictionaryValueEntity  extends BaseEntity {
	
	/**
	 * 声明日志对象
	 */
    private static final long serialVersionUID = -3967231350458507218L;


    /**
    *上级词条
    */	
    private String termsCode;
    
    /**
    *上级词条名称-数据字典值不记录，用于显示到前台界面
    */	
    private String termsName;

    /**
    *序号
    */	
    private String valueSeq;

    /**
    *值名称
    */	
    private String valueName;

    /**
    *值代码
    */	
    private String valueCode;

    /**
    *语言
    */	
    private String language;

    /**
    * 数据版本号
    */	
    private Long versionNo;
    
    /**
    *是否启用
    */	
    private String active;
    
    /**
     * 备注信息
     */
    private String noteInfo;

	


	/**
	 * @return termsCode
	 */
	public String getTermsCode() {
		return termsCode;
	}

	/**
	 * @param  termsCode  
	 */
	public void setTermsCode(String termsCode) {
		this.termsCode = termsCode;
	}

	/**
	 * @return termsName
	 */
	public String getTermsName() {
		return termsName;
	}

	/**
	 * @param  termsName  
	 */
	public void setTermsName(String termsName) {
		this.termsName = termsName;
	}

	/**
	 * @return valueSeq
	 */
	public String getValueSeq() {
		return valueSeq;
	}

	/**
	 * @param  valueSeq  
	 */
	public void setValueSeq(String valueSeq) {
		this.valueSeq = valueSeq;
	}

	/**
	 * @return valueName
	 */
	public String getValueName() {
		return valueName;
	}

	/**
	 * @param  valueName  
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}

	/**
	 * @return valueCode
	 */
	public String getValueCode() {
		return valueCode;
	}

	/**
	 * @param  valueCode  
	 */
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	/**
	 * @return language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param  language  
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return versionNo
	 */
	public Long getVersionNo() {
		return versionNo;
	}

	/**
	 * @param  versionNo  
	 */
	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * @return active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @param  active  
	 */
	public void setActive(String active) {
		this.active = active;
	}


	/**
	 * @return noteInfo
	 */
	public String getNoteInfo() {
		return noteInfo;
	}

	/**
	 * @param  noteInfo  
	 */
	public void setNoteInfo(String noteInfo) {
		this.noteInfo = noteInfo;
	}
	
}