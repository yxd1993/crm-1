package com.hoau.crm.module.appcore.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.server.service.IUserAppService;
import com.hoau.crm.module.appcore.api.shared.domain.AppVersionEntity;
import com.hoau.crm.module.appcore.api.shared.domain.MobileInfoEntity;
import com.hoau.crm.module.appcore.api.shared.domain.PushUserEntity;
import com.hoau.crm.module.appcore.api.shared.exception.UserAppException;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.AppVersionVo;
import com.hoau.crm.module.appcore.api.shared.vo.FeedBackVo;
import com.hoau.crm.module.appcore.api.shared.vo.MobileInfoAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.PushUserVo;
import com.hoau.crm.module.appcore.api.shared.vo.UserUseMobileVo;
import com.hoau.crm.module.appcore.server.dao.CountAppUserMapper;
import com.hoau.crm.module.appcore.server.dao.FeedbackMapper;
import com.hoau.crm.module.appcore.server.dao.MobileInfoMapper;
import com.hoau.crm.module.appcore.server.dao.PushUserMapper;
import com.hoau.crm.module.appcore.server.dao.VersionUpdateMapper;
import com.hoau.crm.module.bse.api.server.service.IDataDictionaryValueService;
import com.hoau.crm.module.bse.api.server.service.IFunctionService;
import com.hoau.crm.module.bse.api.server.service.IOperationLogService;
import com.hoau.crm.module.bse.api.server.service.IUserService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.domain.OperationLogEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.domain.ValidateCodeEntity;
import com.hoau.crm.module.bse.server.dao.MessageInfoMapper;
import com.hoau.crm.module.bse.server.dao.UserUseMobileMapper;
import com.hoau.crm.module.bse.server.dao.VerificationCodeMapper;
import com.hoau.crm.module.common.server.util.SMSPlatformUtil;
import com.hoau.crm.module.common.server.util.ValidateCodeUtil;
import com.hoau.crm.module.common.shared.domain.SmsEntity;
import com.hoau.crm.module.login.server.util.MD5;
import com.hoau.crm.module.login.shared.exception.LoginException;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 用户管理模块RESTFUL接口实现
 * 
 * @author 蒋落琛
 * @date 2015-6-15
 */
@SuppressWarnings("rawtypes")
@Service
public class UserAppService implements IUserAppService {

	@Resource
	IUserService iUserService;

	@Resource
	IFunctionService iFunctionService;

	@Resource
	private PushUserMapper pushUserMapper;
	
	@Resource
	private MessageInfoMapper messageInfoMapper; 

	@Resource
	private VersionUpdateMapper versionUpdateMapper;

	@Resource
	private FeedbackMapper feedbackMapper;
	
	@Resource
	private MobileInfoMapper mobileInfoMapper;
	
	@Resource
	private IDataDictionaryValueService iDataDictionaryValueService;
	
	@Resource
	private IOperationLogService iOperationLogService;
	
	@Resource
	private UserUseMobileMapper userUseMobileMapper;
	
	@Resource
	private VerificationCodeMapper verificationCodeMapper;
	
	@Resource
	private CountAppUserMapper countAppUserMapper;

	@Override
	public ResponseBaseEntity<UserEntity> login(UserEntity loginUser) {
		// 登录信息为空
		if (loginUser == null || StringUtil.isEmpty(loginUser.getUserName())
				|| StringUtil.isEmpty(loginUser.getPassword())) {
			throw new LoginException(LoginException.LOGININFO_NULL);
		}
		UserEntity user = iUserService.queryUserByUserName(loginUser
				.getUserName());
		// 数据库用户不存在
		if (user == null) {
			throw new LoginException(LoginException.USERINFO_NULL);
		}
		// 验证密码
		MD5 m = new MD5();
		String password = m.getMD5ofStr(loginUser.getPassword());
		if (!password.equals(user.getPassword())) {
			throw new LoginException(LoginException.USERPASSWORD_VERIFY);
		}
		// 判断用户是否有效
		if (StringUtil.isEmpty(user.getActive())
				|| user.getActive().equals(BseConstants.INACTIVE)) {
			throw new LoginException(LoginException.USER_ISACTIVE);
		}
		// 菜单初始化
		Set<String> urls = iFunctionService.queryTreeNodeListByUser(loginUser
				.getUserName());
		// 判断用户是否拥有菜单
		if (urls.size() == 0) {
			throw new LoginException(LoginException.USER_NOT_ROLE);
		}
		user.setFunctionCodes(urls);
		
		//记录登录操作日志
		OperationLogEntity operationLogEntity = new OperationLogEntity();
		operationLogEntity.setOperationType(BseConstants.OPERATION_TYPE_APP_LOGIN);
		//operationLogEntity.setOperationIp(GetHostAddressUtil.getIpAddr(ServletActionContext.getRequest()));
		operationLogEntity.setOperationTime(new Date());
		operationLogEntity.setOperationUser(user.getUserName());
		iOperationLogService.saveOperationLog(operationLogEntity);
		// 返回值
		ResponseBaseEntity<UserEntity> result = new ResponseBaseEntity<UserEntity>();
		result.setResult(user);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@Override
	public ResponseBaseEntity<UserUseMobileVo> verificationCodeLogin(UserEntity loginUser, UserUseMobileVo userUseMobileVo) {
		// 登录信息为空
		if (loginUser == null || StringUtil.isEmpty(loginUser.getUserName())
				|| StringUtil.isEmpty(loginUser.getPassword())) {
			throw new LoginException(LoginException.LOGININFO_NULL);
		}
		UserEntity user = iUserService.queryUserByUserName(loginUser
				.getUserName());
		// 数据库用户不存在
		if (user == null) {
			throw new LoginException(LoginException.USERINFO_NULL);
		}
		// 验证密码
		MD5 m = new MD5();
		String password = m.getMD5ofStr(loginUser.getPassword());
		if (!password.equals(user.getPassword())) {
			throw new LoginException(LoginException.USERPASSWORD_VERIFY);
		}
		// 判断用户是否有效
		if (StringUtil.isEmpty(user.getActive())
				|| user.getActive().equals(BseConstants.INACTIVE)) {
			throw new LoginException(LoginException.USER_ISACTIVE);
		}
		// 菜单初始化
		Set<String> urls = iFunctionService.queryTreeNodeListByUser(loginUser
				.getUserName());
		// 判断用户是否拥有菜单
		if (urls.size() == 0) {
			throw new LoginException(LoginException.USER_NOT_ROLE);
		}
		user.setFunctionCodes(urls);
		// 返回值
		// 用户与手机不为空
		if(userUseMobileVo != null){
			// 此手机与用户没有建立关系
			if(userUseMobileMapper.getUserMobileInfo(userUseMobileVo.getUserUseMobileEntity()) == null){
				// 判断此用户是否拥有手机号
				if(user.getEmpEntity() != null && !StringUtil.isEmpty(user.getEmpEntity().getMobile())){
					// 手机号赋值
					if(userUseMobileVo.getValidateCodeEntity() == null){
						ValidateCodeEntity validate = new ValidateCodeEntity();
						validate.setPhone(user.getEmpEntity().getMobile());
						userUseMobileVo.setValidateCodeEntity(validate);
					} else {
						userUseMobileVo.getValidateCodeEntity().setPhone(user.getEmpEntity().getMobile());
					}
					// 判断前台传的参数验证码是否有值，无值则为发送验证码
					if(StringUtil.isEmpty(userUseMobileVo.getValidateCodeEntity().getValidateCode())){
						if(StringUtil.isEmpty(userUseMobileVo.getValidateCodeEntity().getOperType()) || !userUseMobileVo.getValidateCodeEntity().getOperType().equals("1")){
							// 提示用户输入验证码
							ResponseBaseEntity<UserUseMobileVo> result = new ResponseBaseEntity<UserUseMobileVo>();
							UserUseMobileVo r = new UserUseMobileVo();
							r.setValidateCodeEntity(userUseMobileVo.getValidateCodeEntity());
							result.setResult(r);
							result.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
							result.setErrorMessage("当前用户在此手机上登录需要输入验证码_" + userUseMobileVo.getValidateCodeEntity().getPhone());
							return result;
						} else {
							// 查询此号码之前是否存在验证码
							ValidateCodeEntity validateinfo = verificationCodeMapper.getValidateCodeByPhone(userUseMobileVo.getValidateCodeEntity().getPhone());
							String valiCode = ValidateCodeUtil.genCode();
							// 发送验证码
							SmsEntity smsEntity = new SmsEntity();
							smsEntity.setContent(BseConstants.SMS_CONTENT_START + valiCode + BseConstants.SMS_CONTENT_END);
							smsEntity.setMobile(userUseMobileVo.getValidateCodeEntity().getPhone());
							smsEntity.setSearchID(UUIDUtil.getUUID());
							smsEntity.setSendTime(new Date());
							// 是否发送成功
							String isSend = BseConstants.ACTIVE;
							try {
								//SMSNetUtil.sendMsg(smsEntity);
								SMSPlatformUtil.sendMsg(smsEntity);
							} catch (Exception e) {
								isSend = BseConstants.INACTIVE;
							}
							//如果此号码不存在验证码，则新增
							if(validateinfo == null){
								ValidateCodeEntity validateCode = new ValidateCodeEntity();
								validateCode.setPhone(userUseMobileVo.getValidateCodeEntity().getPhone());
								validateCode.setValidateCode(valiCode);
								validateCode.setIsSend(isSend);
								verificationCodeMapper.createValidateCode(validateCode);
							//如果此号码存在验证码，则更新
							}else{
								validateinfo.setValidateCode(valiCode);
								validateinfo.setIsSend(isSend);
								verificationCodeMapper.modifyValidateCode(validateinfo);
							}
							ResponseBaseEntity result = new ResponseBaseEntity();
							result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
							return result;
						}
					// 判断前台传的参数验证码是否有值，有值则为验证验证码
					} else {
						ValidateCodeEntity validateinfo = verificationCodeMapper
								.getValidateCodeByPhone(userUseMobileVo.getValidateCodeEntity().getPhone());
						if (validateinfo == null
								|| validateinfo.getModifyDate() == null
								|| validateinfo.getValidateCode() == null
								|| !validateinfo.getValidateCode().equals(
										userUseMobileVo.getValidateCodeEntity().getValidateCode())) {
							throw new LoginException(LoginException.VERIFICATIONCODE_ERROR_EXCEPTION);
						} else {
							// 绑定当前用户与此手机关系
							userUseMobileVo.getUserUseMobileEntity().setId(UUIDUtil.getUUID());
							userUseMobileMapper.createUserMobile(userUseMobileVo.getUserUseMobileEntity());
							addUserLoginLog(user);
						}
					}
				} else {
					throw new LoginException(LoginException.MOBILE_ISNULL);
				}
			} else {
				addUserLoginLog(user);
			}
		} else {
			addUserLoginLog(user);
		}
		ResponseBaseEntity<UserUseMobileVo> result = new ResponseBaseEntity<UserUseMobileVo>();
		UserUseMobileVo r = new UserUseMobileVo();
		r.setUserEntity(user);
		result.setResult(r);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	/**
	 * 用户登录百度推送账号处理
	 * 
	 * @param pushuservo
	 */
	public ResponseBaseEntity pushUserLogin(PushUserVo pusUserhVo) {
		if (pusUserhVo == null
				|| pusUserhVo.getPushUserEntity() == null
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getUserid())
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getBaidu_userid())) {
			throw new UserAppException(
					UserAppException.BAIDU_PUSHUSERINIFO_NULL);
		}
		// 查询条件
		PushUserEntity searchEntity = new PushUserEntity();
		searchEntity.setBaidu_userid(pusUserhVo.getPushUserEntity()
				.getBaidu_userid());
		searchEntity.setCancel(3);
		List<PushUserEntity> pushlist = messageInfoMapper
				.findPushUser(searchEntity);
		// 当前推送账号信息
		PushUserEntity pushUserEntity = pusUserhVo.getPushUserEntity();
		boolean insert = true;
		for (int i = 0; i < pushlist.size(); i++) {
			PushUserEntity pv = pushlist.get(i);
			// 比较是否为当前用户ID绑定信息
			if (pv != null) {
				if (pushUserEntity.getUserid().equals(pv.getUserid())) {
					insert = false;
					// 是否已注销绑定
					if (pv.getCancel() != 0) {
						pv.setCancel(0);
						pushUserMapper.modifyPushUser(pv);
					}
				} else {
					// 是否其他用户已经绑定推送消息且没有注销
					if (pv.getCancel() == 0) {
						pv.setCancel(1);
						pushUserMapper.modifyPushUser(pv);
					}
				}
			}
		}
		// 如果没有绑定过推送消息则新增
		if (insert) {
			pushUserEntity.setId(UUIDUtil.getUUID());
			pushUserMapper.createPushUser(pushUserEntity);
		}
		// 返回值
		ResponseBaseEntity result = new ResponseBaseEntity();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	/**
	 * 注销百度推送用户信息绑定
	 * 
	 * @param pushuservo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-25
	 * @update
	 */
	public ResponseBaseEntity pushUserLogout(PushUserVo pusUserhVo, String loginName) {
		if (pusUserhVo == null
				|| pusUserhVo.getPushUserEntity() == null
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getUserid())
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getBaidu_userid())) {
			throw new UserAppException(
					UserAppException.BAIDU_PUSHUSERINIFO_NULL);
		}
		// 查询条件
		PushUserEntity searchEntity = new PushUserEntity();
		searchEntity.setCancel(0);
		searchEntity.setUserid(loginName);
		searchEntity.setBaidu_userid(pusUserhVo.getPushUserEntity()
				.getBaidu_userid());
		List<PushUserEntity> pushlist = messageInfoMapper
				.findPushUser(searchEntity);
		// 是否已有推送信息，且为未注销状态
		if (pushlist != null && pushlist.size() > 0) {
			PushUserEntity userEntity = pushlist.get(0);
			// 为注销状态需修改为 注销状态 1
			if (userEntity.getCancel() == 0) {
				userEntity.setCancel(1);
				pushUserMapper.modifyPushUser(userEntity);
			}
		}
		// 返回值
		ResponseBaseEntity result = new ResponseBaseEntity();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	/**
	 * 根据用户id和百度用户id 查询绑定消息
	 * 
	 * @param userid
	 * @param baiduuserid
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity<PushUserVo> findPushState(PushUserVo pusUserhVo) {
		if (pusUserhVo == null
				|| pusUserhVo.getPushUserEntity() == null
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getUserid())
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getBaidu_userid())) {
			throw new UserAppException(
					UserAppException.BAIDU_PUSHUSERINIFO_NULL);
		}
		// 查询条件
		PushUserEntity searchEntity = new PushUserEntity();
		searchEntity.setUserid(pusUserhVo.getPushUserEntity().getUserid());
		searchEntity.setBaidu_userid(pusUserhVo.getPushUserEntity()
				.getBaidu_userid());
		searchEntity.setCancel(3);
		// 查询
		PushUserEntity pushvo = null;
		List<PushUserEntity> listvo = messageInfoMapper
				.findPushUser(searchEntity);
		if (listvo.size() > 0) {
			pushvo = listvo.get(0);
		}
		// 返回值
		ResponseBaseEntity<PushUserVo> result = new ResponseBaseEntity<PushUserVo>();
		PushUserVo vo = new PushUserVo();
		vo.setPushUserEntity(pushvo);
		result.setResult(vo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	/**
	 * 修改消息声音状态
	 * 
	 * @param pushvo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity modifyPushSound(PushUserVo pusUserhVo) {
		if (pusUserhVo == null
				|| pusUserhVo.getPushUserEntity() == null
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getUserid())
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getBaidu_userid())) {
			throw new UserAppException(
					UserAppException.BAIDU_PUSHUSERINIFO_NULL);
		}
		// 当前推送账号信息
		PushUserEntity pushUserEntity = pusUserhVo.getPushUserEntity();
		if (AppUtil.IOS.equals(pushUserEntity.getAppid())) {
			pushUserEntity.setNotification_basic_style(7);
			if (!"msg.wav".equals(pushUserEntity.getSound())) {
				pushUserEntity.setSound("");
			}
		} else if (AppUtil.ANDROID.equals(pushUserEntity.getAppid())) {
			pushUserEntity.setSound("msg.wav");
			// 5：响铃 3:振动 1:无声音无振动 7:响铃加振动
			if (1 > pushUserEntity.getNotification_basic_style()
					|| 7 < pushUserEntity.getNotification_basic_style()) {
				pushUserEntity.setNotification_basic_style(7);
			}

		} else {
			throw new UserAppException(UserAppException.SYSTEM_INFO_ERROR);
		}
		pushUserMapper.modifyPushSound(pushUserEntity);
		// 返回值
		ResponseBaseEntity result = new ResponseBaseEntity();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	/**
	 * 修改接收消息状态
	 * 
	 * @param pushvo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity modifyPushSendState(PushUserVo pusUserhVo) {
		if (pusUserhVo == null
				|| pusUserhVo.getPushUserEntity() == null
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getUserid())
				|| StringUtil.isEmpty(pusUserhVo.getPushUserEntity()
						.getBaidu_userid())) {
			throw new UserAppException(
					UserAppException.BAIDU_PUSHUSERINIFO_NULL);
		}
		// 当前推送账号信息
		PushUserEntity pushUserEntity = pusUserhVo.getPushUserEntity();
		// 消息接收控制状态 1:全天接收 2:时间段接收 3:不接收
		if (pushUserEntity.getSendstate() < 1
				|| pushUserEntity.getSendstate() > 3) {
			throw new UserAppException(
					UserAppException.RECEIVE_STATUS_PARAMS_ERROR);
		}
		// 2:按时间段发送
		if (pushUserEntity.getSendstate() != 2) {
			pushUserEntity.setBeginhour(0);
			pushUserEntity.setEndhour(24);
		}
		pushUserMapper.modifyPushSendState(pushUserEntity);
		// 返回值
		ResponseBaseEntity result = new ResponseBaseEntity();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;

	}

	/**
	 * 获取APP版本信息
	 * 
	 * @param vo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity<AppVersionVo> getVersionInfo() {
		AppVersionEntity versionUpdateInfo = versionUpdateMapper
				.getVersionInfo();
		if (versionUpdateInfo == null) {
			versionUpdateInfo = new AppVersionEntity();
		} else {
			// 总裁与副总
			DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
			entity.setTermsCode("APP_APK_URL");
			List<DataDictionaryValueEntity> dataDictionaryEntity = iDataDictionaryValueService.queryDataDictionaryValueByEntity(entity, 0, 10);
			if(dataDictionaryEntity != null && dataDictionaryEntity.size() > 0){
				for(int i=0; i<dataDictionaryEntity.size(); i++){
					DataDictionaryValueEntity data = dataDictionaryEntity.get(i);
					if(data.getValueCode().equals("APK_URL")){
						versionUpdateInfo.setUrl(data.getValueName());
					}
				}
			}
		}
		// 返回值
		ResponseBaseEntity<AppVersionVo> result = new ResponseBaseEntity<AppVersionVo>();
		AppVersionVo vo = new AppVersionVo();
		vo.setAppVersionEntity(versionUpdateInfo);
		result.setResult(vo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	/**
	 * 反馈信息
	 * 
	 * @param feedBackVo
	 * @param loginName
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity feedbackInfo(FeedBackVo feedBackVo,
			String loginName) {
		if (feedBackVo == null || feedBackVo.getFeedBackEntity() == null) {
			throw new UserAppException(UserAppException.QUESTION_INFO_NULL);
		}
		feedBackVo.getFeedBackEntity().setId(UUIDUtil.getUUID());
		feedBackVo.getFeedBackEntity().setUserId(loginName);
		feedbackMapper.userFeedBack(feedBackVo.getFeedBackEntity());
		// 返回值
		ResponseBaseEntity result = new ResponseBaseEntity();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	/**
	 * 手机信息添加
	 * 
	 * @param mobilevo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-22
	 * @update
	 */
	@Transactional
	public ResponseBaseEntity mobileInfo(MobileInfoAppVo mobilevo) {
		if (mobilevo == null || mobilevo.getMobileInfoEntity() == null) {
			throw new UserAppException(UserAppException.MOBILE_INFO_NULL);
		}
		MobileInfoEntity mobileEntity = mobilevo.getMobileInfoEntity();
		MobileInfoEntity moinf = mobileInfoMapper.findMobileInfo(mobileEntity);
		if (moinf == null) {
			mobileInfoMapper.createMobileInfo(mobileEntity);
		}
		mobileInfoMapper.createMobileInfoEntry(mobileEntity);
		// 返回值
		ResponseBaseEntity result = new ResponseBaseEntity();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	/**
	 * 获取欢迎页图片路径
	 * 
	 * @param mobilevo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-22
	 * @update
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public ResponseBaseEntity getWelcomeImg() {
		Map<String, String> map = new HashMap<String, String>();
		// 总裁与副总
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("WELCOME_IMG");
		List<DataDictionaryValueEntity> dataDictionaryEntity = iDataDictionaryValueService.queryDataDictionaryValueByEntity(entity, 0, 10);
		if(dataDictionaryEntity != null && dataDictionaryEntity.size() > 0){
			for(int i=0; i<dataDictionaryEntity.size(); i++){
				DataDictionaryValueEntity data = dataDictionaryEntity.get(i);
				if(data.getValueCode().equals("IMGURL")){
					map.put("imgUrl", data.getValueName());
				}
				if(data.getValueCode().equals("IMGNAME")){
					map.put("imgName", data.getValueName());
				}
			}
		} else {
			map.put("imgUrl", "");
			map.put("imgName", "");
		}
		// 返回值
		ResponseBaseEntity result = new ResponseBaseEntity();
		result.setResult(map);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	/**
	 * 记录用户登录日志
	 * 
	 * @param user
	 * @author 蒋落琛
	 * @date 2015-12-9
	 * @update
	 */
	private void addUserLoginLog(UserEntity user){
		//记录登录操作日志
		OperationLogEntity operationLogEntity = new OperationLogEntity();
		operationLogEntity.setOperationType(BseConstants.OPERATION_TYPE_APP_LOGIN);
		//operationLogEntity.setOperationIp(GetHostAddressUtil.getIpAddr(ServletActionContext.getRequest()));
		operationLogEntity.setOperationTime(new Date());
		operationLogEntity.setOperationUser(user.getUserName());
		iOperationLogService.saveOperationLog(operationLogEntity);
	}

	@Override
	public List<Map<String, String>> countAppUserInfo() {
		return countAppUserMapper.countAppUserInfo();
	}
}
