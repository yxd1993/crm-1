package com.hoau.crm.module.bse.api.shared.domain;

import java.util.List;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 数据字典实体
 * @author 高佳
 * @date 2015年5月14日
 */
public class DataDictionaryEntity extends BaseEntity {
	
	/**
	 * 声明日志对象
	 */
	private static final long serialVersionUID = -3967231350441121031L;


	/**
	 * 词条代码
	 */
	private String termsCode;

	/**
	 * 词条名称
	 */
	private String termsName;

	/**
	 * 词条拼音
	 */
	private String termsPinyin;

	/**
	 * 备注
	 */
	private String notes;

	/**
	 * 是否启用
	 */
	private String active;

	/**
	 * 子词条的LIST
	 */
	private List<DataDictionaryValueEntity> dataDictionaryValueEntityList;


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
	 * @return termsPinyin
	 */
	public String getTermsPinyin() {
		return termsPinyin;
	}

	/**
	 * @param  termsPinyin  
	 */
	public void setTermsPinyin(String termsPinyin) {
		this.termsPinyin = termsPinyin;
	}

	/**
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param  notes  
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	 * @return dataDictionaryValueEntityList
	 */
	public List<DataDictionaryValueEntity> getDataDictionaryValueEntityList() {
		return dataDictionaryValueEntityList;
	}

	/**
	 * @param  dataDictionaryValueEntityList  
	 */
	public void setDataDictionaryValueEntityList(
			List<DataDictionaryValueEntity> dataDictionaryValueEntityList) {
		this.dataDictionaryValueEntityList = dataDictionaryValueEntityList;
	}
}