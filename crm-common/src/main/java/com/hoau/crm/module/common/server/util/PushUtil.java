package com.hoau.crm.module.common.server.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

public class PushUtil {

	private static Properties loadConfig() {
		Properties pro;
		try {
			pro = ConfigFileLoadUtil
					.getPropertiesForClasspath("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			pro = new Properties();
		} catch (IOException e) {
			e.printStackTrace();
			pro = new Properties();
		}
		return pro;
	}

	private static Properties pro = loadConfig();
	private static String ANDROID_APIKEY = pro
			.getProperty("baidupush.apikey.android");
	private static String ANDROID_SECRETKEY = pro
			.getProperty("baidupush.secretkey.android");
	private static String IOS_APIKEY = pro.getProperty("baidupush.apikey.ios");
	private static String NEW_IOS_APIKEY = pro.getProperty("baidupush.apikey.new.ios");
	private static String IOS_SECRETKEY = pro
			.getProperty("baidupush.secretkey.ios");
	private static String NEW_IOS_SECRETKEY = pro
			.getProperty("baidupush.secretkey.new.ios");
	public static String TITLE = pro.getProperty("baidupush.titlemsg");
	public static int IOS_STATUS = Integer.parseInt(pro
			.getProperty("baidupush.state.ios"));
	public static int IOS_CLIENT = 4;
	public static int ANDROID_CLIENT = 3;
	public static int MESSAGETYPE_MESSAGE = 0;
	public static int MESSAGETYPE_NOTICE = 1;

	/**
	 * 推送广播消息或通知 （对具体用户发送）
	 * 
	 * @param apiKey
	 *            developer平台的ApiKey
	 * @param secretKey
	 *            developer平台的SecretKey
	 * @param message_type
	 *            推送广播类型： 0 消息，1通知
	 * @param msg
	 *            通知或消息 具体内容
	 * @param userid
	 *            具体用应用端userid
	 * @param devicetype
	 *            设备类型 3:android 4:ios
	 * @param deploy_status
	 *            部署状态。指定应用当前的部署状态，可取值：1：开发状态2：生产状态
	 *            若不指定，则默认设置为生产状态。特别提醒：该功能只支持ios设备类型。
	 */
	public static PushMsgToSingleDeviceResponse pushMsgToSingleAndroidDevice(
			int message_type, String msg, String channelId)
			throws PushClientException, PushServerException {
		
		// 响应
		PushMsgToSingleDeviceResponse response = null;
		
		// 1. get apiKey and secretKey from developer console
		PushKeyPair pair = new PushKeyPair(ANDROID_APIKEY, ANDROID_SECRETKEY);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// 创建 Android的通知
			/*JSONObject notification = new JSONObject();
			notification.put("title", "TEST");
			notification.put("description", "Hello Baidu Push");
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
			notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);*/

			// 组装参数
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(channelId)
					.addMsgExpires(new Integer(3600)). // message有效时间
					addMessageType(message_type).// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
					addMessage(msg).
					addDeviceType(3);// deviceType
									// =>
									// 3:android,
									// 4:ios
			// 5. http request
			response = pushClient
					.pushMsgToSingleDevice(request);
			
			// Http请求结果解析打印
			/*System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime());*/
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return response;
	}
	
	/**
	 * IOS单条发送
	 * 
	 * @param message_type
	 * @param msg
	 * @param channelId
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	public static PushMsgToSingleDeviceResponse pushMsgToSingleIosDevice(
			int message_type, String msg, String channelId)
			throws PushClientException, PushServerException {
		
		// 响应
		PushMsgToSingleDeviceResponse response = null;
		
		// 1. get apiKey and secretKey from developer console
		PushKeyPair pair = new PushKeyPair(IOS_APIKEY, IOS_SECRETKEY);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// make IOS Notification
			/*JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", "Hello Baidu Push");
			jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");*/

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(channelId)
					.addMsgExpires(new Integer(3600)). // 设置message的有效时间
					addMessageType(message_type).// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
					addMessage(msg).addDeployStatus(IOS_STATUS). // IOS,
														// DeployStatus
														// =>
														// 1:
														// Developer
														// 2:
														// Production.
					addDeviceType(4);// deviceType => 3:android, 4:ios
			
			// 5. http request
			response = pushClient
					.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
			/*System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime());*/
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println("------------>>>" + channelId);
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return response;
	}
	
	/**
	 * IOS单条发送
	 * 
	 * @param message_type
	 * @param msg
	 * @param channelId
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	public static PushMsgToSingleDeviceResponse pushMsgToSingleIosDeviceByNew(
			int message_type, String msg, String channelId)
			throws PushClientException, PushServerException {
		
		// 响应
		PushMsgToSingleDeviceResponse response = null;
		
		// 1. get apiKey and secretKey from developer console
		PushKeyPair pair = new PushKeyPair(NEW_IOS_APIKEY, NEW_IOS_SECRETKEY);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// make IOS Notification
			/*JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", "Hello Baidu Push");
			jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");*/

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(channelId)
					.addMsgExpires(new Integer(3600)). // 设置message的有效时间
					addMessageType(message_type).// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
					addMessage(msg).addDeployStatus(IOS_STATUS). // IOS,
														// DeployStatus
														// =>
														// 1:
														// Developer
														// 2:
														// Production.
					addDeviceType(4);// deviceType => 3:android, 4:ios
			
			// 5. http request
			response = pushClient
					.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
			/*System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime());*/
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println("------------>>>" + channelId);
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return response;
	}

	/**
	 * ANDROID广播
	 * 
	 * @param msg
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	public static PushMsgToAllResponse pushMsgToAllAndroidDevice(String msg)
			throws PushClientException, PushServerException {
		
		// 响应
		PushMsgToAllResponse response = null;
		
		// 1. get apiKey and secretKey from developer console
		PushKeyPair pair = new PushKeyPair(ANDROID_APIKEY, ANDROID_SECRETKEY);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(0)
					.addMessage(msg) //添加透传消息
					.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			
			// 5. http request
			response = pushClient.pushMsgToAll(request);
			
			// Http请求结果解析打印
			/*System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());*/
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return response;
	}

	/**
	 * IOS广播
	 * 
	 * @param message_type
	 * @param msg
	 * @param channelId
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	public static PushMsgToAllResponse pushMsgToAllIosDevice(String msg)
			throws PushClientException, PushServerException {
		
		// 响应
		PushMsgToAllResponse response = null;
		
		// 1. get apiKey and secretKey from developer console
		PushKeyPair pair = new PushKeyPair(IOS_APIKEY, IOS_SECRETKEY);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// 创建IOS通知
			/*JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", "Hello Baidu Push");
			jsonAPS.put("sound", "ttt"); // 设置通知铃声样式,例如"ttt"，用户自定义。
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");*/
			
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(msg)
					//.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDepolyStatus(IOS_STATUS).addDeviceType(4);
			// 5. http request
			response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			/*System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());*/
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return response;
	}
	
	/**
	 * IOS广播
	 * 
	 * @param message_type
	 * @param msg
	 * @param channelId
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 * @author 蒋落琛
	 * @date 2015-7-1
	 * @update
	 */
	public static PushMsgToAllResponse pushMsgToAllIosDeviceByNew(String msg)
			throws PushClientException, PushServerException {
		
		// 响应
		PushMsgToAllResponse response = null;
		
		// 1. get apiKey and secretKey from developer console
		PushKeyPair pair = new PushKeyPair(NEW_IOS_APIKEY, NEW_IOS_SECRETKEY);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// 创建IOS通知
			/*JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", "Hello Baidu Push");
			jsonAPS.put("sound", "ttt"); // 设置通知铃声样式,例如"ttt"，用户自定义。
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");*/
			
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(msg)
					//.addSendTime(System.currentTimeMillis() / 1000 + 120) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDepolyStatus(IOS_STATUS).addDeviceType(4);
			// 5. http request
			response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			/*System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime() + ",timerId: "
					+ response.getTimerId());*/
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return response;
	}
}
