package com.hoau.crm.module.bse.api.shared.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;

/**
 * 
 * @author 丁勇
 * @date 2015年6月26日
 */
public class ReviewHistoryVo implements Serializable {

	private static final long serialVersionUID = -9131135406868765966L;
	
	/**
	 * 历史回顾创建时间
	 */
	private Date createDate;
	/**
	 *用于历史跟进显示的企业名称
	 */
	private String enterpriseName;
	/**
	 * 明细的集合
	 */
	private List<ReviewHistoryEntity> reviewHistoryEntityList;
	/**
	 * 预约创建时间
	 */
	private Date reserveTime;
	/**
	 * 工作日历集合
	 */
	private List<SaleReserveEntity> reserveList;
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<ReviewHistoryEntity> getReviewHistoryEntityList() {
		return reviewHistoryEntityList;
	}

	public void setReviewHistoryEntityList(
			List<ReviewHistoryEntity> reviewHistoryEntityList) {
		this.reviewHistoryEntityList = reviewHistoryEntityList;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public List<SaleReserveEntity> getReserveList() {
		return reserveList;
	}

	public void setReserveList(List<SaleReserveEntity> reserveList) {
		this.reserveList = reserveList;
	}
}
