package com.hoau.crm.module.appcore.server.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.ISignAppService;
import com.hoau.crm.module.appcore.api.shared.domain.AttachmentEntity;
import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.appcore.api.shared.exception.SignException;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.SignVo;
import com.hoau.crm.module.appcore.server.dao.SignAppMapper;
import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.server.util.BaiduApiUtil;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.domain.SignAccompanyEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.service.IAttachmentService;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.server.service.IMeetingSignService;
import com.hoau.crm.module.sales.api.server.service.ISweepSignService;
import com.hoau.crm.module.sales.api.shared.domain.MeetingSignEntity;
import com.hoau.crm.module.sales.api.shared.domain.SweepSignEntity;
import com.hoau.crm.module.sales.shared.exception.MeetingSignException;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 *  签到SERVICE
 * 
 * @author: 何斌
 * @create: 2015年7月3日 上午10:00:50
 */
@Service
@SuppressWarnings("rawtypes")
public class SignAppService implements ISignAppService {
	
	@Resource
	private SignAppMapper signAppMapper;

	@Resource
	private IReviewHistoryService reviewHistoryService;
	
	@Resource
	private IEmployeeService iEmployeeService;
	
	@Resource
	private ISweepSignService iSweepSignService;
	
	@Resource
	private IMeetingSignService iMeetingSignService;
	
	@Resource
	private ILoginService iLoginService;
	
	@Resource
	private IAttachmentService iAttachmentService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseBaseEntity addSignInfo(SignVo signVo,String loginName) {
		// 返回值
		ResponseBaseEntity<SignVo> result = new ResponseBaseEntity<SignVo>();
		try {
			if(StringUtil.isEmpty(loginName) || signVo == null || signVo.getSignEntity() == null 
					|| signVo.getImgContent() == null || signVo.getImgContent().length <= 0
					|| StringUtil.isEmpty(signVo.getSignEntity().getCustomerId())
					|| StringUtils.isEmpty(signVo.getSignEntity().getLongitude()) 
					|| StringUtils.isEmpty(signVo.getSignEntity().getLatitude())
						//|| StringUtil.isEmpty(signVo.getSignEntity().getSignAddress())
						|| StringUtil.isEmpty(signVo.getSignEntity().getImgName())){
				result.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
				result.setErrorMessage(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
			}else{
				SignEntity signEntity = signVo.getSignEntity();
				// 如果地址为空
				if(StringUtils.isEmpty(signEntity.getSignAddress())){
					signEntity.setSignAddress(BaiduApiUtil.getAddressByLL(signEntity.getLongitude().toString(), signEntity.getLatitude().toString()));
				}
				//获取系统根目录
				String imgName = signEntity.getImgName();
				String rootUrl = AttachmentRootPath.getAttachRootPath();
				String uuidFileName = UUIDUtil.getUUID();
				String postfix = imgName.indexOf(".")>0 ? (imgName.substring(imgName.lastIndexOf("."), imgName.length())) : "";
				String imgUrl = "";
				String filePath = "";
				String newAttachPath = "";
				try {
					imgUrl = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("app.sign.dir")
							+loginName;
					filePath = rootUrl + imgUrl;
					newAttachPath = filePath + "/" + uuidFileName + postfix;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.savaImg(signVo.getImgContent(), filePath,newAttachPath);
				signVo.getSignEntity().setImgUrl(imgUrl + "/" + uuidFileName + postfix);
				signVo.getSignEntity().setCreateUser(loginName);
				//主键UUID
				if(StringUtil.isEmpty(signVo.getSignEntity().getId())){
					signVo.getSignEntity().setId(UUIDUtil.getUUID());
				}
				
				//新增签到记录
				// 初始化时间
				if(signVo.getSignEntity().getCreateDate() == null){
					signVo.getSignEntity().setCreateDate(new Date());
				}
				signAppMapper.addSignInfo(signVo.getSignEntity());
				result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
				signVo.setImgContent(null);
				signVo.setSweepSignEntity(null);
				result.setResult(signVo);
				// 当前用户
				UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
				String empCode = "";
				if(currentUser.getEmpEntity() != null){
					empCode = currentUser.getEmpEntity().getEmpCode();
				}
				//新增签到操作记录
				reviewHistoryService.insertReviewOrHistory(
						new ReviewHistoryEntity(null, null, null, null,signVo.getSignEntity(),
								BseConstants.OPER_SIGNIN, empCode));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(AppUtil.EXCEPTION_STATUS_SYSTEM_ERROR);
		}
		return result;
	}
	
	@Override
	@Transactional
	public ResponseBaseEntity addSweepSignInfo(SignVo signVo,String loginName) {
		ResponseBaseEntity responseBaseEntity = new ResponseBaseEntity();
		if(signVo == null || signVo.getSweepSignEntity() == null 
				|| StringUtil.isEmpty(loginName)
				|| StringUtil.isEmpty(signVo.getSweepSignEntity().getSweepPeop()) || StringUtil.isEmpty(signVo.getSweepSignEntity().getWasSweepPeop())
				|| signVo.getImgContent() == null || signVo.getImgContent().length <= 0
				|| StringUtil.isEmpty(signVo.getSweepSignEntity().getSignAddress())){
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
			responseBaseEntity.setErrorMessage(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
		}else{
			SweepSignEntity sweepSignEntity = signVo.getSweepSignEntity();
			//获取系统根目录
			/*String imgName = sweepSignEntity.getImgName();
			String rootUrl = AttachmentRootPath.getAttachRootPath();
			String uuidFileName = UUIDUtil.getUUID();
			String postfix = imgName.indexOf(".")>0 ? (imgName.substring(imgName.lastIndexOf("."), imgName.length())) : "";
			String imgUrl = "";
			String filePath = "";
			String newAttachPath = "";
			try {
				imgUrl = ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("app.sweepsign.dir")
						+loginName;
				filePath = rootUrl + imgUrl;
				newAttachPath = filePath + "/" + uuidFileName + postfix;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.savaImg(signVo.getImgContent(), filePath, newAttachPath);
			sweepSignEntity.setImgUrl(imgUrl + "/" + uuidFileName + postfix);*/
			sweepSignEntity.setCreateUser(loginName);
			// 新增签到记录
			// 扫描人信息
			if(!StringUtil.isEmpty(sweepSignEntity.getSweepPeop())){
				EmployeeEntity emp = new EmployeeEntity();
				emp.setEmpCode(sweepSignEntity.getSweepPeop());
				EmployeeEntity result = iEmployeeService.queryEmployeeByEmpcode(emp);
				if(result != null){
					sweepSignEntity.setSweepPeopName(result.getEmpName());
					sweepSignEntity.setSweepPeopJobCode(result.getJobcode());
					sweepSignEntity.setSweepPeopJobName(result.getJobname());
					sweepSignEntity.setSweepPeopDeptCode(result.getDeptcode());
					sweepSignEntity.setSweepPeopDeptName(result.getDeptname());
				}
			}
			// 被扫描人信息
			if(!StringUtil.isEmpty(sweepSignEntity.getWasSweepPeop())){
				EmployeeEntity emp = new EmployeeEntity();
				emp.setEmpCode(sweepSignEntity.getWasSweepPeop());
				EmployeeEntity result = iEmployeeService.queryEmployeeByEmpcode(emp);
				if(result != null){
					sweepSignEntity.setWasSweepPeopName(result.getEmpName());
					sweepSignEntity.setWasSweepPeopJobCode(result.getJobcode());
					sweepSignEntity.setWasSweepPeopJobName(result.getJobname());
					sweepSignEntity.setWasSweepPeopDeptCode(result.getDeptcode());
					sweepSignEntity.setWasSweepPeopDeptName(result.getDeptname());
				}
			}
			iSweepSignService.addSweepSignInfo(sweepSignEntity);
			// 保存图片信息
			AttachmentEntity attachment = new AttachmentEntity();
			attachment.setFileName(signVo.getSweepSignEntity().getImgName());
			attachment.setImgContent(signVo.getImgContent());
			List<AttachmentEntity> attList = new ArrayList<AttachmentEntity>();
			attList.add(attachment);
			iAttachmentService.addAttachmentEntity(attList, sweepSignEntity.getId(), "app.sweepsign.dir", loginName);
			//门店走访新增扫描签到操作记录
			ReviewHistoryEntity log = new ReviewHistoryEntity();
			log.setSweepSignEntity(sweepSignEntity);
			log.setOperateType(BseConstants.OPER_SWEEPSIGN);
			// 当前用户
			UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
			log.setCreateUser(currentUser.getEmpEntity().getEmpCode());
			reviewHistoryService.insertReviewOrHistory(log);
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		}
		return responseBaseEntity;
	}
	
	@Override
	@Transactional
	public ResponseBaseEntity addMoreImgSweepSignInfo(SignVo signVo,String loginName) {
		ResponseBaseEntity responseBaseEntity = new ResponseBaseEntity();
		if(signVo == null || signVo.getSweepSignEntity() == null 
				|| StringUtil.isEmpty(loginName)
				|| StringUtil.isEmpty(signVo.getSweepSignEntity().getSweepPeop()) || StringUtil.isEmpty(signVo.getSweepSignEntity().getWasSweepPeop())
				|| StringUtil.isEmpty(signVo.getSweepSignEntity().getSignAddress())
				|| signVo.getSweepSignEntity().getAttachmentList() == null || signVo.getSweepSignEntity().getAttachmentList().size() == 0){
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
			responseBaseEntity.setErrorMessage(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
		}else{
			SweepSignEntity sweepSignEntity = signVo.getSweepSignEntity();
			sweepSignEntity.setCreateUser(loginName);
			// 新增签到记录
			// 扫描人信息
			if(!StringUtil.isEmpty(sweepSignEntity.getSweepPeop())){
				EmployeeEntity emp = new EmployeeEntity();
				emp.setEmpCode(sweepSignEntity.getSweepPeop());
				EmployeeEntity result = iEmployeeService.queryEmployeeByEmpcode(emp);
				if(result != null){
					sweepSignEntity.setSweepPeopName(result.getEmpName());
					sweepSignEntity.setSweepPeopJobCode(result.getJobcode());
					sweepSignEntity.setSweepPeopJobName(result.getJobname());
					sweepSignEntity.setSweepPeopDeptCode(result.getDeptcode());
					sweepSignEntity.setSweepPeopDeptName(result.getDeptname());
				}
			}
			// 被扫描人信息
			if(!StringUtil.isEmpty(sweepSignEntity.getWasSweepPeop())){
				EmployeeEntity emp = new EmployeeEntity();
				emp.setEmpCode(sweepSignEntity.getWasSweepPeop());
				EmployeeEntity result = iEmployeeService.queryEmployeeByEmpcode(emp);
				if(result != null){
					sweepSignEntity.setWasSweepPeopName(result.getEmpName());
					sweepSignEntity.setWasSweepPeopJobCode(result.getJobcode());
					sweepSignEntity.setWasSweepPeopJobName(result.getJobname());
					sweepSignEntity.setWasSweepPeopDeptCode(result.getDeptcode());
					sweepSignEntity.setWasSweepPeopDeptName(result.getDeptname());
				}
			}
			// 保存签到信息
			iSweepSignService.addSweepSignInfo(sweepSignEntity);
			// 保存图片信息
			iAttachmentService.addAttachmentEntity(signVo.getSweepSignEntity().getAttachmentList(), sweepSignEntity.getId(), "app.sweepsign.dir", loginName);
			//门店走访新增扫描签到操作记录
			ReviewHistoryEntity log = new ReviewHistoryEntity();
			log.setSweepSignEntity(sweepSignEntity);
			log.setOperateType(BseConstants.OPER_SWEEPSIGN);
			// 当前用户
			UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
			log.setCreateUser(currentUser.getEmpEntity().getEmpCode());
			reviewHistoryService.insertReviewOrHistory(log);
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		}
		return responseBaseEntity;
	}
	
	@Override
	@Transactional
	public ResponseBaseEntity addMeetingSignInfo(SignVo signVo,String loginName) {
		// 用户
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		if(user != null && user.getEmpEntity() != null){
			signVo.getMeetingSignEntity().setSweepPeop(user.getEmpEntity().getEmpCode());
		}
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		iMeetingSignService.addMeetingSignEntityInfo(signVo.getMeetingSignEntity(), "app.meetingsign.dir", currentUser);
		ResponseBaseEntity responseBaseEntity = new ResponseBaseEntity();
		responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return responseBaseEntity;
	}
	
	/**
	 * 保存图片
	 * @author: 何斌
	 * @date: 2015年7月3日
	 * @update 
	 */
	private void savaImg(byte[] imgContent,String filePath,String newAttachPath){
		OutputStream os = null;
		try {
			File file = new File(filePath);
			// 判断用户目录是否存在，没有则创建
			if(!file.exists()){
				file.mkdir();
			}
			os = new FileOutputStream(new File(newAttachPath));
			// 获取前台传输文件流并写入后台文件
			os.write(imgContent);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ResponseBaseEntity queryNoRelationSignList(SignVo signVo,
			String loginName) {
		//客户id是否为空
		if(StringUtils.isEmpty(signVo)){
			throw new CustomerException(
					CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		params.put("accountId", signVo.getAccountId());
		params.put("loginName", loginName);
		//获取未关联的预约信息
		List<SignEntity> noRelationSignList = signAppMapper.queryNoRelationSignList(params);
		signVo.setNoRelationSignList(noRelationSignList);
		ResponseBaseEntity<SignVo> result = new ResponseBaseEntity<SignVo>();
		result.setResult(signVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@Override
	public ResponseBaseEntity queryNoRelationMeetingSignList(SignVo signVo,
			String loginName) {
		//会议类型是否为空
		if(signVo == null || StringUtil.isEmpty(signVo.getMeetingType())){
			throw new MeetingSignException(MeetingSignException.MEETINGTYPE_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		params.put("meetingType", signVo.getMeetingType());
		params.put("loginName", loginName);
		//获取未关联的预约信息
		List<MeetingSignEntity> noRelationSignList = iMeetingSignService.queryNoRelationMeetingSignList(params);
		signVo.setMeetingSignList(noRelationSignList);
		ResponseBaseEntity<SignVo> result = new ResponseBaseEntity<SignVo>();
		result.setResult(signVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@Override
	public SignEntity isHasThereSign(SignEntity signEntity) {
		SignEntity sign = signAppMapper.isHasThereSign(signEntity);
		if(sign != null){
			return sign;
		} else {
			return null;
		}
	}

	@Override
	public ResponseBaseEntity addSignNewMethod(List<SignVo> signList,String loginName) {
		ResponseBaseEntity result = new ResponseBaseEntity();
		if(signList.size()<0){
			throw new SignException(SignException.PARAM_NULL);
		}
		//签到列表保存
		for (SignVo signVo : signList) {
			//判断签到是否已经上传
			if(isHasThereSign(signVo.getSignEntity())!=null){
				throw new SignException(SignException.ADD_SIGN_IS_EXIST);
			}
			//保存签到信息
			result  = this.addSignInfo(signVo, loginName);
			if(!result.getErrorCode().equals("0")){
				throw new SignException(SignException.SAVE_SIGN_FAILURE_NULL);
			}
			//保存签到扫描陪同人
			if(!StringUtils.isEmpty(signVo.getEmpCodeList())&&signVo.getEmpCodeList().size()>0){
				//工号集合
				List<Map<String,Object>> empCodeList = signVo.getEmpCodeList();
				//创建保存扫描人的集合
				List<SignAccompanyEntity> signAccompanyList = new ArrayList<SignAccompanyEntity>();
				//根据被扫描工号集合查询员工信息
				for (Map<String,Object> empCodelist: empCodeList) {
					//判断工号
					if(!StringUtils.isEmpty(empCodelist.get("empCode"))){
						EmployeeEntity emp = new EmployeeEntity();
						emp.setEmpCode(empCodelist.get("empCode").toString());
						emp = iEmployeeService.queryEmployeeByEmpcode(emp);
						if(emp!=null){
							//被扫描人信息
							SignAccompanyEntity signAccompanyEntity = new SignAccompanyEntity();
							signAccompanyEntity.setSignId(signVo.getSignEntity().getId());
							signAccompanyEntity.setScanAccompanyEmpCode(emp.getEmpCode());
							signAccompanyEntity.setScanAccompanyEmpName(emp.getEmpName());
							signAccompanyEntity.setScanAccompanyJobCode(emp.getJobcode());
							signAccompanyEntity.setScanAccompanyJobName(emp.getJobname());
							signAccompanyEntity.setScanAccompanyDeptCode(emp.getDeptcode());
							signAccompanyEntity.setScanAccompanyDeptName(emp.getDeptname());
							signAccompanyEntity.setCreateUser(loginName);
							signAccompanyList.add(signAccompanyEntity);
						}
					}
				}
				if(signAccompanyList.size()>0){
					//执行保存
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("signAccompanyList", signAccompanyList);
					signAppMapper.addScanAccompany(map);
				}
			}
		}
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
}
