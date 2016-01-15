package com.hoau.crm.module.common.server.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hoau.crm.module.common.shared.domain.SmsConfig;
import com.hoau.crm.module.common.shared.domain.SmsEntity;
import com.hoau.crm.module.common.shared.domain.SmsGroup;
import com.hoau.crm.module.common.shared.domain.SmsItem;
import com.hoau.crm.module.common.shared.domain.SmsTask;

/**
 * 短信发送
 * 
 * @author 蒋落琛
 * @date 2015-12-8
 */
@Component
public class SMSNetUtil implements ApplicationContextAware {

	private static Log log = LogFactory.getLog(SMSNetUtil.class);

	private static final DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * @Title: sendMsg
	 * @Description: 发送短信
	 * @param @param smsEntity 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void sendMsg(SmsEntity smsEntity) {
		SmsGroup t = new SmsGroup();
		t.seteTime(df.format(smsEntity.getSendTime()));
		t.setInterFaceID(SmsConfig.INTERFACEID);
		t.setLoginName(SmsConfig.LOGIN_NAME);
		t.setLoginPwd(SmsConfig.LOGIN_PWD);
		t.setOpKind(SmsConfig.OPKIND);
		t.setSerType(SmsConfig.SERTYPE);

		SmsTask smsTask = new SmsTask();
		smsTask.setContent(smsEntity.getContent());
		smsTask.setRecivePhoneNumber(smsEntity.getMobile());
		smsTask.setSearchID(smsEntity.getSearchID());

		SmsItem item = new SmsItem();

		List<SmsTask> items = new ArrayList<SmsTask>();
		items.add(smsTask);
		item.setTasks(items);
		t.setItem(item);

		String xml;
		CloseableHttpClient client = null;
		try {
			xml = JaxbUtil.marshToXmlBinding(SmsGroup.class, t);
			client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(SmsConfig.URL);
			StringEntity requestEntity = new StringEntity(xml, "GB2312");
			httpPost.setEntity(requestEntity);
			CloseableHttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				log.info("entity:" + EntityUtils.toString(response.getEntity()));
				log.info("message send sucess:mobile->" + smsEntity.getMobile()
						+ ",content->" + smsEntity.getContent());
			} else {
				throw new RuntimeException("短信发送失败！");
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
	}
}
