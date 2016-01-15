package com.hoau.crm.module.appcore.api.server.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.shared.vo.AppVersionVo;
import com.hoau.crm.module.appcore.api.shared.vo.FeedBackVo;
import com.hoau.crm.module.appcore.api.shared.vo.MobileInfoAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.PushUserVo;
import com.hoau.crm.module.appcore.api.shared.vo.UserUseMobileVo;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;


/**
 * 用户信息RESTFUL接口
 *
 * @author 蒋落琛
 * @date 2015-6-15
 */
@SuppressWarnings("rawtypes")
public interface IUserAppService {

	/**
	 * 用户登录
	 * 
	 * @param currentUser
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-15
	 * @update
	 */
	public ResponseBaseEntity<UserEntity> login(UserEntity currentUser);
	
	/**
	 * 用户登录
	 * 
	 * @param currentUser
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-15
	 * @update
	 */
	public ResponseBaseEntity verificationCodeLogin(UserEntity currentUser, UserUseMobileVo userUseMobileVo);
	
	/**
	 * 用户登录百度推送信息处理
	 * 
	 * @param pushuservo
	 */
	public ResponseBaseEntity pushUserLogin(PushUserVo pusUserhVo);

	/**
	 * 注销百度推送用户信息绑定
	 * 
	 * @param pushuservo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-25
	 * @update
	 */
	public ResponseBaseEntity pushUserLogout(PushUserVo pusUserhVo, String loginName);

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
	public ResponseBaseEntity<PushUserVo> findPushState(PushUserVo pusUserhVo);
	
	/**
	 * 修改消息声音状态
	 * 
	 * @param pushvo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity modifyPushSound(PushUserVo pusUserhVo);

	/**
	 * 修改接收消息状态
	 * 
	 * @param pushvo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity modifyPushSendState(PushUserVo pusUserhVo);

	/**
	 * 获取APP版本信息
	 * 
	 * @param vo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	public ResponseBaseEntity<AppVersionVo> getVersionInfo();

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
			String loginName);
	
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
	public ResponseBaseEntity mobileInfo(MobileInfoAppVo mobilevo);
	
	/**
	 * 获取欢迎页图片路径
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-9-14
	 * @update
	 */
	public ResponseBaseEntity getWelcomeImg();
	
	/**
	 * 统计APP的用户信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-12-29
	 * @update
	 */
	public List<Map<String, String>> countAppUserInfo();
}
