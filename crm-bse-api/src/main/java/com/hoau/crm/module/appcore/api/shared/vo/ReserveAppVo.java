/**
 * 
 */
package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.Date;
import java.util.List;

import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;

/**
 * 预约 restful 接口vo
 * 
 * @author 丁勇
 * @date 2015年7月7日
 */
public class ReserveAppVo {
	/**
	 * 预约实体
	 */
	private SaleReserveEntity reserveEntity;
	/**
	 * 门店负责人姓名
	 */
	private String tierEmpName;
	/**
	 * 路区负责人名称
	 */
	private String roadEmpName;
	/**
	 * 大区负责人名称
	 */
	private String regionsEmpName;
	/**
	 * 事业部负责人名称
	 */
	private String businessUnitEmpName;
	/**
	 * 营运副总名称
	 */
	private String odEmpName;
	/**
	 * 总裁名称
	 */
	private String ceoEmpName;
	/**
	 * 团队经理名称
	 */
	private String teamManagerEmpName;
	/**
	 * 客户经理名称
	 */
	private String saleManEmpName;
	/**
	 * 预约时长
	 */
	private String reserveTime;
	/**
	 * 预约开始时间
	 */
	private Date startDate;
	/**
	 * 预约结束时间
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
	private long totalCount;
	/**
	 * 预约id集合
	 */
	private List<String> ids;
	/**
	 * 预约列表集合
	 */
	private List<SaleReserveEntity> reserveDayList;

	/**
	 * 返回未预约的列表
	 */
	private List<SaleReserveVo> noReserveList;

	public SaleReserveEntity getReserveEntity() {
		return reserveEntity;
	}

	public void setReserveEntity(SaleReserveEntity reserveEntity) {
		this.reserveEntity = reserveEntity;
	}

	public String getTierEmpName() {
		return tierEmpName;
	}

	public void setTierEmpName(String tierEmpName) {
		this.tierEmpName = tierEmpName;
	}

	public String getTeamManagerEmpName() {
		return teamManagerEmpName;
	}

	public void setTeamManagerEmpName(String teamManagerEmpName) {
		this.teamManagerEmpName = teamManagerEmpName;
	}

	public String getRoadEmpName() {
		return roadEmpName;
	}

	public void setRoadEmpName(String roadEmpName) {
		this.roadEmpName = roadEmpName;
	}

	public String getRegionsEmpName() {
		return regionsEmpName;
	}

	public void setRegionsEmpName(String regionsEmpName) {
		this.regionsEmpName = regionsEmpName;
	}

	public String getBusinessUnitEmpName() {
		return businessUnitEmpName;
	}

	public void setBusinessUnitEmpName(String businessUnitEmpName) {
		this.businessUnitEmpName = businessUnitEmpName;
	}

	public String getOdEmpName() {
		return odEmpName;
	}

	public void setOdEmpName(String odEmpName) {
		this.odEmpName = odEmpName;
	}

	public String getCeoEmpName() {
		return ceoEmpName;
	}

	public void setCeoEmpName(String ceoEmpName) {
		this.ceoEmpName = ceoEmpName;
	}

	public String getSaleManEmpName() {
		return saleManEmpName;
	}

	public void setSaleManEmpName(String saleManEmpName) {
		this.saleManEmpName = saleManEmpName;
	}

	public String getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<SaleReserveEntity> getReserveDayList() {
		return reserveDayList;
	}

	public void setReserveDayList(List<SaleReserveEntity> reserveDayList) {
		this.reserveDayList = reserveDayList;
	}

	public List<SaleReserveVo> getNoReserveList() {
		return noReserveList;
	}

	public void setNoReserveList(List<SaleReserveVo> noReserveList) {
		this.noReserveList = noReserveList;
	}
	

}
