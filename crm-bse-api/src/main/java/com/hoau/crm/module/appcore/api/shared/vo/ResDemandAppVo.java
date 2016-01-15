package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.ResDemandEntity;

/**
 * @author 275636
 *资源需求实体
 */
public class ResDemandAppVo {
	/**
	 * 资源需求实体
	 */
	private ResDemandEntity resDemandEntity;
	
	/**
	 * 资源需求集合
	 */
	private List<ResDemandEntity> resDemandList;
	/**
     * 分布起始位置
     */
	private int start;
    
    /**
     * 分页数据总长度
     */
	private Long totalCount;
	
	/**
     * 每页数据量
     */
	private int limit;
	
	/**
     * ID集合
     */
	private List<String> ids;
    
    public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	
	public List<ResDemandEntity> getResDemandList() {
		return resDemandList;
	}

	public void setResDemandList(List<ResDemandEntity> resDemandList) {
		this.resDemandList = resDemandList;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public ResDemandEntity getResDemandEntity() {
		return resDemandEntity;
	}

	public void setResDemandEntity(ResDemandEntity resDemandEntity) {
		this.resDemandEntity = resDemandEntity;
	}
	
}
