package com.hoau.crm.module.appcore.api.shared.domain;

import java.util.Date;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 用户登录注册百度账号信息
 *
 * @author 蒋落琛
 * @date 2015-6-25
 */
public class PushUserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userid;
	/**
	 * 用户工号
	 */
	private String empCode;
	/**
	 * 百度推送应用端用户ID
	 */
	private String baidu_userid;
	/**
	 * 百度推送通道ID
	 */
	private String channelid;
	/**
	 * 应用ID
	 */
	private String appid;
	/**
	 * API Key
	 */
	private String apikey;
	/**
	 * Secret Key
	 */
	private String secretkey;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 是否已注销
	 */
	private int cancel;

	/**
	 * android 自定义样式标识
	 */
	private int notification_builder_id;
	/**
	 * android 自定义铃声样式 //5：响铃 3:振动 1:无声音无振动 7:响铃加振动
	 */
	private int notification_basic_style;
							
	/**
	 * ios 音频文件名
	 */
	private String sound;
	/**
	 * ios 音频标识
	 */
	private int badge;
	/**
	 * 消息接收控制状态 1:全天接收 2:时间段接收 3:不接收
	 */
	private int sendstate;
	/**
	 * 接收时间段开始值
	 */
	private int beginhour;
	/**
	 * 接收时间段结束值
	 */
	private int endhour;

	public int getNotification_builder_id() {
		return notification_builder_id;
	}

	public void setNotification_builder_id(int notification_builder_id) {
		this.notification_builder_id = notification_builder_id;
	}

	public int getNotification_basic_style() {
		return notification_basic_style;
	}

	public void setNotification_basic_style(int notification_basic_style) {
		this.notification_basic_style = notification_basic_style;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public int getBadge() {
		return badge;
	}

	public void setBadge(int badge) {
		this.badge = badge;
	}

	public int getSendstate() {
		return sendstate;
	}

	public void setSendstate(int sendstate) {
		this.sendstate = sendstate;
	}

	public int getBeginhour() {
		return beginhour;
	}

	public void setBeginhour(int beginhour) {
		this.beginhour = beginhour;
	}

	public int getEndhour() {
		return endhour;
	}

	public void setEndhour(int endhour) {
		this.endhour = endhour;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getSecretkey() {
		return secretkey;
	}

	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBaidu_userid() {
		return baidu_userid;
	}

	public void setBaidu_userid(String baidu_userid) {
		this.baidu_userid = baidu_userid;
	}

	public String getChannelid() {
		return channelid;
	}

	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
}
