package com.hoau.crm.module.sales.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.sales.api.server.service.IMeetingSignService;
import com.hoau.crm.module.sales.api.shared.domain.MeetingSignEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年9月8日
 * @description：会议签到action
 */
@Controller
@Scope("prototype")
public class MeetingSignAction extends AbstractAction {

	private static final long serialVersionUID = 2035830325122716890L;
	
	/**
	 * 用来存放数据库中客户扫描签到信息数据
	 */
	private List<MeetingSignEntity> meetingSignList;

	/**
	 * 用来存放前台传送过来的查询信息
	 */
	private MeetingSignEntity meetingSign;
	
	/**
	 * 附件上传地址
	 */
	private String imgDir;
	
	/**
	 * 上传人姓名
	 */
	private String loginName;
	@Resource
	private IMeetingSignService meetingSignService;
	
	/**
	 * 查询会议签到详细信息
	 */
	public String queryMeetingSignAction() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			meetingSignList = meetingSignService.getMeetingSign(meetingSign, rb);
			this.setTotalCount(meetingSignService.countMeetingSign(meetingSign, rb));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 新增会议扫描签到信息
	 */
	public String addMeetingSignEntityInfo(){
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		try {
			meetingSignService.addMeetingSignEntityInfo(meetingSign,imgDir,currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public List<MeetingSignEntity> getMeetingSignList() {
		return meetingSignList;
	}

	public void setMeetingSignList(List<MeetingSignEntity> meetingSignList) {
		this.meetingSignList = meetingSignList;
	}

	public MeetingSignEntity getMeetingSign() {
		return meetingSign;
	}

	public void setMeetingSign(MeetingSignEntity meetingSign) {
		this.meetingSign = meetingSign;
	}

	public String getImgDir() {
		return imgDir;
	}

	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
