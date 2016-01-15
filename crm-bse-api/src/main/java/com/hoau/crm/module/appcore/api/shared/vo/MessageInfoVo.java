package com.hoau.crm.module.appcore.api.shared.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;


/**
 * 消息信息VO
 *
 * @author 蒋落琛
 * @date 2015-6-29
 */
public class MessageInfoVo implements Serializable {

	private static final long serialVersionUID = 7163336508919159546L;

	/**
	 * 消息信息
	 */
	private MessageInfoEntity messageInfoEntity;
	
	/**
	 * 消息信息集合
	 */
	private List<MessageInfoEntity> messageList;
	
	/**
	 * 消息VO的集合
	 */
	private List<MessageInfoVo> messageVoList;
	
	/**
	 * 消息的业务类型
	 */
	private String busType;
	
	/**
	 * 当前类型的未读条数
	 */
	private int countMessage;
	
	/**
	 * 当前类型的所有消息条数
	 */
	private int allCountMessage;
	
	/**
     * 分页数据总长度
     */
	private Long totalCount;
    
    /**
     * 客户信息ID集合
     */
	private List<String> ids;
	
	/**
	 * 分页开始
	 */
	private int start;
	
	/**
	 * 分页结束
	 */
	private int limit;
	
	/**
	 * 开始时间
	 */
	private Date startDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 排序方式
	 */
	private String sortType;
	
	/**
	 * 角色 ID
	 */
	private String roleId;

	public MessageInfoEntity getMessageInfoEntity() {
		return messageInfoEntity;
	}

	public void setMessageInfoEntity(MessageInfoEntity messageInfoEntity) {
		this.messageInfoEntity = messageInfoEntity;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public List<MessageInfoEntity> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<MessageInfoEntity> messageList) {
		this.messageList = messageList;
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

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public int getCountMessage() {
		return countMessage;
	}

	public void setCountMessage(int countMessage) {
		this.countMessage = countMessage;
	}

	public List<MessageInfoVo> getMessageVoList() {
		return messageVoList;
	}

	public void setMessageVoList(List<MessageInfoVo> messageVoList) {
		this.messageVoList = messageVoList;
	}

	public int getAllCountMessage() {
		return allCountMessage;
	}

	public void setAllCountMessage(int allCountMessage) {
		this.allCountMessage = allCountMessage;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
