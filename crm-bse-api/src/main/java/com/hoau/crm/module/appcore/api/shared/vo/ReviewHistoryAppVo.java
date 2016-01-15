/**
 * 
 */
package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.Date;
import java.util.List;

import com.hoau.crm.module.bse.api.shared.vo.ReviewHistoryVo;

/**
 * 
 * @author 丁勇
 * @date 2015-07-15
 */
public class ReviewHistoryAppVo {
	/**
	 * 客户id
	 */
	private String accountId;
	/**
	 * 开始时间
	 */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 每页数据量
	 */
	private int limit;
	/**
	 * 分布起始位置
	 */
	private int start;

	/**
	 * 分页数据总长度
	 */
	private Long totalCount;
	/**
	 * 结果集合
	 */
	private List<ReviewHistoryVo> reviewhistoryvo;

	public String getAccountId() {
		return accountId;
	}

	public Date getEndDate() {
		return endDate;
	}

	public List<ReviewHistoryVo> getReviewhistoryvo() {
		return reviewhistoryvo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setReviewhistoryvo(List<ReviewHistoryVo> reviewhistoryvo) {
		this.reviewhistoryvo = reviewhistoryvo;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

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
}
