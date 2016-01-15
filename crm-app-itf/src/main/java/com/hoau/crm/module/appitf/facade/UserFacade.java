package com.hoau.crm.module.appitf.facade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IUserAppService;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.AppVersionVo;
import com.hoau.crm.module.appcore.api.shared.vo.FeedBackVo;
import com.hoau.crm.module.appcore.api.shared.vo.MobileInfoAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.PushUserVo;
import com.hoau.crm.module.appcore.api.shared.vo.UserUseMobileVo;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserUseMobileEntity;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@SuppressWarnings("rawtypes")
@Service
@Path("/user")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class UserFacade extends AppBaseFacade {

	@Resource
	IUserAppService iUserAppService;

	/**
	 * 用户登录验证
	 * 
	 * @param currentUser
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-15
	 * @update
	 */
	@POST
	@Path("login")
	public ResponseBaseEntity login(UserEntity currentUser) {
		return iUserAppService.login(currentUser);
	}
	
	/**
	 * 用户登录验证
	 * 
	 * @param currentUser
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-15
	 * @update
	 */
	@POST
	@Path("verificationCodeLogin")
	public ResponseBaseEntity verificationCodeLogin(UserUseMobileVo userUseMobileVo) {
		// 用户信息赋值
		if(userUseMobileVo != null && userUseMobileVo.getUserEntity() != null  && !StringUtil.isEmpty(deviceId)){
			UserUseMobileEntity userUseMobileEntity = new UserUseMobileEntity();
			userUseMobileEntity.setUniqueId(deviceId);
			userUseMobileEntity.setUserCode(userUseMobileVo.getUserEntity().getUserName());
			userUseMobileEntity.setAppType(appType);
			userUseMobileEntity.setCreateUser(userUseMobileVo.getUserEntity().getUserName());
			userUseMobileVo.setUserUseMobileEntity(userUseMobileEntity);
		} else {
			ResponseBaseEntity responseBaseEntity = new ResponseBaseEntity();
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
			responseBaseEntity.setErrorMessage(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
			return responseBaseEntity;
		}
		return iUserAppService.verificationCodeLogin(userUseMobileVo.getUserEntity(), userUseMobileVo);
	}

	/**
	 * 用户登录百度推送信息处理
	 * 
	 * @param pusUserhVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-26
	 * @update
	 */
	@POST
	@Path("pushUserLogin")
	public ResponseBaseEntity pushUserLogin(PushUserVo pusUserhVo) {
		return iUserAppService.pushUserLogin(pusUserhVo);
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
	@POST
	@Path("pushUserLogout")
	public ResponseBaseEntity pushUserLogout(PushUserVo pusUserhVo) {
		return iUserAppService.pushUserLogout(pusUserhVo, loginName);
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
	@POST
	@Path("findPushState")
	public ResponseBaseEntity<PushUserVo> findPushState(PushUserVo pusUserhVo) {
		return iUserAppService.findPushState(pusUserhVo);
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
	@POST
	@Path("modifyPushSound")
	public ResponseBaseEntity modifyPushSound(PushUserVo pusUserhVo) {
		return iUserAppService.modifyPushSound(pusUserhVo);
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
	@POST
	@Path("modifyPushSendState")
	public ResponseBaseEntity modifyPushSendState(PushUserVo pusUserhVo) {
		return iUserAppService.modifyPushSendState(pusUserhVo);
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
	@GET
	@Path("getVersionInfo")
	public ResponseBaseEntity<AppVersionVo> getVersionInfo() {
		return iUserAppService.getVersionInfo();
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
	@POST
	@Path("feedbackInfo")
	public ResponseBaseEntity feedbackInfo(FeedBackVo feedBackVo) {
		return iUserAppService.feedbackInfo(feedBackVo, loginName);
	}
	
	/**
	 * 手机信息添加
	 * 
	 * @param mobileVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-22
	 * @update
	 */
	@POST
	@Path("mobileInfo")
	public ResponseBaseEntity mobileInfo(MobileInfoAppVo mobileVo) {
		return iUserAppService.mobileInfo(mobileVo);
	}
	
	/**
	 * 获取欢迎页图片路径
	 * 
	 * @param mobileVo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-22
	 * @update
	 */
	@GET
	@Path("getWelcomeImg")
	public ResponseBaseEntity getWelcomeImg() {
		return iUserAppService.getWelcomeImg();
	}
	
	/**
	 * 获取APP的平台用户数
	 * 
	 * @param callBackName  回调函数的方法名，由调用方传递
	 * @return
	 * @author 蒋落琛
	 * @date 2015-12-29
	 * @update
	 */
	@GET
	@Path("countAppUserInfo/{callBackName}")
	public String countAppUserInfo(@PathParam("callBackName")String callBackName){
		List<Map<String, String>> userInfoList = iUserAppService.countAppUserInfo();
		// ANDROID
		String aNum = "0";
		// IOS
		String iNum = "0";
		if(userInfoList != null){
			for(int i=0; i<userInfoList.size(); i++){
				// ANDROID
				if(userInfoList.get(i).get("apptype").equals(BseConstants.APP_TYPE_ANDROID)){
					aNum = String.valueOf(userInfoList.get(i).get("num"));
				}
				// IOS
				if(userInfoList.get(i).get("apptype").equals(BseConstants.APP_TYPE_IOS)){
					iNum = String.valueOf(userInfoList.get(i).get("num"));
				}
			}
		}
		// 返回回调方法的字符串
		String callFun = "var " + callBackName + " = function(){"
				+ "return ["+ aNum + "," + iNum +"];"
				+ "}";
		return callFun;
	}
}
