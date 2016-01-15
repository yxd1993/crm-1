package com.hoau.crm.module.sales.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.WasMeetingSignEntityMapper;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.crm.module.sales.api.server.service.IMeetingSignService;
import com.hoau.crm.module.sales.api.shared.domain.MeetingSignEntity;
import com.hoau.crm.module.sales.api.shared.domain.WasMeetingSignEntity;
import com.hoau.crm.module.sales.server.dao.MeetingSignMapper;
import com.hoau.crm.module.sales.shared.exception.MeetingSignException;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 会议扫描签到SERVICE
 * 
 * @author 潘强
 * @date 2015-9-8
 */
@Service
public class MeetingSignService implements IMeetingSignService {

	@Resource
	private MeetingSignMapper meetingSignMapper;
	
	@Resource
	private IAttachmentService attachmentService;
	
	@Resource
	private WasMeetingSignEntityMapper wasMeetingSignEntityMapper;
	
	@Resource
	private IEmployeeService iEmployeeService;
	
	@Override
	//查询会议扫描签到信息
	public List<MeetingSignEntity> getMeetingSign(MeetingSignEntity meetingSign, RowBounds rb) {
		
		if (rb == null) {
			throw new MeetingSignException(MeetingSignException.QUERY_MEETINGSIGN_RB_NULL);
		}
		if (meetingSign == null) {
			throw new MeetingSignException(MeetingSignException.QUERY_MEETINGSIGN_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(meetingSign.getSweepPeopType())){
			params.put("sweepPeopType", meetingSign.getSweepPeopType());
		}
		if (!StringUtil.isEmpty(meetingSign.getSweepPeopName())) {
			params.put("sweepPeopName", "%" + meetingSign.getSweepPeopName() + "%");
		}
		/*if (meetingSign.getWasSweepPeopName().length!=0) {
			params.put("wasSweepPeopName", "%" + meetingSign.getWasSweepPeopName()[0] + "%");
		}*/
		if (!StringUtil.isEmpty(meetingSign.getSignAddress())) {
			params.put("signAddress", "%" + meetingSign.getSignAddress() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (meetingSign.getSweepTime() != null) {
			params.put("sweepTime", sdf.format(meetingSign.getSweepTime()));
		}
		if (meetingSign.getSweepEndTime() != null) {
			params.put("sweepEndTime", sdf.format(BseConstants.getEndDate(meetingSign.getSweepEndTime())));
		}
		if(rb != null){
			params.put("startNum", String.valueOf(rb.getOffset()));
			params.put("limitNum", String.valueOf(rb.getLimit()));
		}
		return meetingSignMapper.getMeetingSign(params);
	}

	@Override
	//统计会议扫描签到总数
	public Long countMeetingSign(MeetingSignEntity meetingSign, RowBounds rb) {
		
		if (rb == null) {
			throw new MeetingSignException(MeetingSignException.QUERY_MEETINGSIGN_RB_NULL);
		}
		if (meetingSign == null) {
			throw new MeetingSignException(MeetingSignException.QUERY_MEETINGSIGN_PARAM_NULL);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtil.isEmpty(meetingSign.getSweepPeopType())){
			params.put("sweepPeopType", meetingSign.getSweepPeopType());
		}
		if (!StringUtil.isEmpty(meetingSign.getSweepPeopName())) {
			params.put("sweepPeopName", "%" + meetingSign.getSweepPeopName() + "%");
		}
		/*if (meetingSign.getWasSweepPeopName().length!=0) {
			params.put("wasSweepPeopName", "%" + meetingSign.getWasSweepPeopName()[0] + "%");
		}*/
		if (!StringUtil.isEmpty(meetingSign.getSignAddress())) {
			params.put("signAddress", "%" + meetingSign.getSignAddress() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (meetingSign.getSweepTime() != null) {
			params.put("sweepTime", sdf.format(meetingSign.getSweepTime()));
		}
		if (meetingSign.getSweepEndTime() != null) {
			params.put("sweepEndTime", sdf.format(BseConstants.getEndDate(meetingSign.getSweepEndTime())));
		}
		return meetingSignMapper.countMeetingSign(params);
	}

	@Override
	@Transactional
	//新增会议签到实体
	public void addMeetingSignEntityInfo(MeetingSignEntity meetingSign,String imgDir,UserEntity currentUser) {
		//判断参数是否存在空值
		if(meetingSign==null){
			throw new MeetingSignException(MeetingSignException.ADD_MEETINGSIGN_NULL);
		}
		if(StringUtil.isEmpty(imgDir)){
			throw new MeetingSignException(MeetingSignException.ADD_IMGDIR_NULL);
		}
//		if(StringUtils.isEmpty(currentUser)){
//			throw new MeetingSignException(MeetingSignException.ADD_LOGINNAME_NULL);
//		}
		if(StringUtil.isEmpty(meetingSign.getSweepPeop())){
			throw new MeetingSignException(MeetingSignException.ADD_SWEEPPEOP_NULL);
		}
		if(meetingSign.getWasMeetingSignList()==null || meetingSign.getWasMeetingSignList().size() == 0){
			throw new MeetingSignException(MeetingSignException.ADD_MEETINGSIGN_MEETINGSIGNLIST_NULL);
		}
		if(meetingSign.getAttachmentList()==null || meetingSign.getAttachmentList().size() == 0){
			throw new MeetingSignException(MeetingSignException.ADD_MEETINGSIGN_ATTACHMENTLIST_NULL);
		}
		if(StringUtil.isEmpty(meetingSign.getId())){
			// 扫描人信息完善
			EmployeeEntity emp = new EmployeeEntity();
			emp.setEmpCode(meetingSign.getSweepPeop());
			EmployeeEntity result = iEmployeeService.queryEmployeeByEmpcode(emp);
			if(result != null){
				meetingSign.setSweepPeopName(result.getEmpName());
				meetingSign.setSweepPeopJobCode(result.getJobcode());
				meetingSign.setSweepPeopJobName(result.getJobname());
				meetingSign.setSweepPeopDeptCode(result.getDeptcode());
				meetingSign.setSweepPeopDeptName(result.getDeptname());
			}
			
			// 被扫描人信息完善
			for(int i=0; i<meetingSign.getWasMeetingSignList().size(); i++){
				WasMeetingSignEntity en = meetingSign.getWasMeetingSignList().get(i);
				emp.setEmpCode(en.getWasSweepPeop());
				result = iEmployeeService.queryEmployeeByEmpcode(emp);
				if(result != null){
					en.setWasSweepPeopName(result.getEmpName());
					en.setWasSweepPeopJobCode(result.getJobcode());
					en.setWasSweepPeopJobName(result.getJobname());
					en.setWasSweepPeopDeptCode(result.getDeptcode());
					en.setWasSweepPeopDeptName(result.getDeptname());
				}
			}
			
			// 主键
			String uuid=UUIDUtil.getUUID();
			meetingSign.setId(uuid);
			meetingSign.setCreateUser(currentUser.getUserName());
			meetingSign.setDeptCode(currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			//保存会议扫描签到实体信息
			meetingSignMapper.addMeetingSignEntityInfo(meetingSign);
			//保存会议扫描签到中的附件信息
			attachmentService.addAttachmentEntity(meetingSign.getAttachmentList(), uuid, imgDir, currentUser.getUserName());
			//保存会议扫描签到中被扫描人信息
			List<WasMeetingSignEntity> meetingSignEntityList=meetingSign.getWasMeetingSignList();
			for(int i=0;i<meetingSignEntityList.size();i++){
				WasMeetingSignEntity msn=meetingSignEntityList.get(i);
				msn.setId(UUIDUtil.getUUID());
				msn.setWasSweepPeopSignId(uuid);
				msn.setCreateUser(currentUser.getUserName());
				wasMeetingSignEntityMapper.addWasMeetingSignEntity(msn);
			}
		}
	}

	@Override
	public List<MeetingSignEntity> queryNoRelationMeetingSignList(
			Map<String, String> map) {
		return meetingSignMapper.queryNoRelationMeetingSignList(map);
	}

	@Override
	public void updateMeetingSignIsRelation(String signId, UserEntity user) {
		if(StringUtil.isEmpty(signId)){
			throw new MeetingSignException(MeetingSignException.ADD_MEETINGSIGN_NULL);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("signId", signId);
		map.put("loginName", user.getUserName());
		meetingSignMapper.updateMeetingSignIsRelation(map);
	}

}
