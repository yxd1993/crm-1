package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;

/**
 * 数据字典信息RESTFUL接口返回VO
 * 
 * @author 蒋落琛
 * @date 2015-6-15
 */
public class DataDictionaryAppVo {

	/**
	 * 数据字典信息
	 */
	private List<DataDictionaryEntity> dataDictList;

	/**
	 * 数据字典版本号
	 */
	private long dataVersion;

	public List<DataDictionaryEntity> getDataDictList() {
		return dataDictList;
	}

	public void setDataDictList(List<DataDictionaryEntity> dataDictList) {
		this.dataDictList = dataDictList;
	}

	public long getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(long dataVersion) {
		this.dataVersion = dataVersion;
	}
}
