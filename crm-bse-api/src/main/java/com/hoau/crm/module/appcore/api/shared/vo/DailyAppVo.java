package com.hoau.crm.module.appcore.api.shared.vo;

import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.DailyMeetingEntity;
import com.hoau.crm.module.bse.api.shared.domain.DailyProblemEntity;
import com.hoau.crm.module.bse.api.shared.domain.DailyTrainEntity;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;

/**
 * @author 275636
 *每日会议  问题 培训VO
 */
public class DailyAppVo {
	/**
	 * 每日会议实体
	 */
	private DailyMeetingEntity meetingEntity;
	/**
	 * 每日问题实体
	 */
	private DailyProblemEntity problemEntity;
	/**
	 * 每日培训实体
	 */
	private DailyTrainEntity trainEntity;
	/**
	 * 每日会议实体集合
	 */
	private List<DailyMeetingEntity> meetingList;
	/**
	 * 每日问题实体集合
	 */
	private List<DailyProblemEntity> problemList;
	/**
	 * 每日培训实体集合
	 */
	private List<DailyTrainEntity> trainList;
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
	/**
	 * 图片内容
	 */
	private List<AttachmentEntity> attachmentEntityList;
	/**
	 * 参与人员集合
	 * */
	private List<EmployeeEntity> empList;
	
	/**
	 * 查询参数
	 */
	private String selectorParam;
	
	/**
	 * 查询结果集
	 */
	private List<String> empAndDeptNames;
	
	public List<AttachmentEntity> getAttachmentEntityList() {
		return attachmentEntityList;
	}

	public void setAttachmentEntityList(List<AttachmentEntity> attachmentEntityList) {
		this.attachmentEntityList = attachmentEntityList;
	}

	public List<EmployeeEntity> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeEntity> empList) {
		this.empList = empList;
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
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
	public List<DailyMeetingEntity> getMeetingList() {
		return meetingList;
	}

	public void setMeetingList(List<DailyMeetingEntity> meetingList) {
		this.meetingList = meetingList;
	}

	public List<DailyProblemEntity> getProblemList() {
		return problemList;
	}

	public void setProblemList(List<DailyProblemEntity> problemList) {
		this.problemList = problemList;
	}

	public List<DailyTrainEntity> getTrainList() {
		return trainList;
	}

	public void setTrainList(List<DailyTrainEntity> trainList) {
		this.trainList = trainList;
	}
	
	public DailyMeetingEntity getMeetingEntity() {
		return meetingEntity;
	}

	public void setMeetingEntity(DailyMeetingEntity meetingEntity) {
		this.meetingEntity = meetingEntity;
	}

	public DailyProblemEntity getProblemEntity() {
		return problemEntity;
	}

	public void setProblemEntity(DailyProblemEntity problemEntity) {
		this.problemEntity = problemEntity;
	}

	public DailyTrainEntity getTrainEntity() {
		return trainEntity;
	}

	public void setTrainEntity(DailyTrainEntity trainEntity) {
		this.trainEntity = trainEntity;
	}

	public String getSelectorParam() {
		return selectorParam;
	}

	public void setSelectorParam(String selectorParam) {
		this.selectorParam = selectorParam;
	}

	public List<String> getEmpAndDeptNames() {
		return empAndDeptNames;
	}

	public void setEmpAndDeptNames(List<String> empAndDeptNames) {
		this.empAndDeptNames = empAndDeptNames;
	}
	
}
