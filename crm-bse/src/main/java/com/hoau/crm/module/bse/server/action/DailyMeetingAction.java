package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.bse.api.server.service.IDailyMeetingService;
import com.hoau.crm.module.bse.api.shared.domain.DailyMeetingEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author 275636
 *每日会议
 */
@Controller
@Scope("prototype")
public class DailyMeetingAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 每日会议实体集合
	 */
	private List<DailyMeetingEntity> dailyMeetingList;
	
	/**
	 * 每日会议实体
	 */
	private DailyMeetingEntity dailyMeeting;
	
	
	/**
	 * iD集合
	 */
	private List<String> ids;
	
	private String meetingSignId;
	
	/**
	 * 附件集合
	 */
	List<AttachmentEntity> attachmentEntities;
	
	public String getMeetingSignId() {
		return meetingSignId;
	}

	public void setMeetingSignId(String meetingSignId) {
		this.meetingSignId = meetingSignId;
	}
	
	
	public List<AttachmentEntity> getAttachmentEntities() {
		return attachmentEntities;
	}

	public void setAttachmentEntities(List<AttachmentEntity> attachmentEntities) {
		this.attachmentEntities = attachmentEntities;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Resource
	private IDailyMeetingService iDailyMeetingService;
	
	public String queryMeetingAttachAction(){
		try {
			attachmentEntities = iDailyMeetingService.queryMeetingAttachAction(meetingSignId);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	
	public String queryDailyMeetingAction()
	{
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			//当前用户
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			dailyMeetingList = iDailyMeetingService.queryDailyMeetingList(dailyMeeting, rb,currentUser);
			this.setTotalCount(iDailyMeetingService.countqueryDailyMeetingList(dailyMeeting, rb,currentUser));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String deleteDailyMeeting()
	{
		try {
			iDailyMeetingService.deleteDailyMeeting(ids);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String addDailyMeeting()
	{
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			iDailyMeetingService.addDailyMeeting(dailyMeeting,currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	} 

	public List<DailyMeetingEntity> getDailyMeetingList() {
		return dailyMeetingList;
	}

	public void setDailyMeetingList(List<DailyMeetingEntity> dailyMeetingList) {
		this.dailyMeetingList = dailyMeetingList;
	}

	public DailyMeetingEntity getDailyMeeting() {
		return dailyMeeting;
	}

	public void setDailyMeeting(DailyMeetingEntity dailyMeeting) {
		this.dailyMeeting = dailyMeeting;
	}
}
