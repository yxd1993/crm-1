package com.hoau.crm.module.common.shared.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 承载短信的实体
 * 
 * @author 蒋落琛
 * @date 2015-12-2
 */
@XmlRootElement(name = "Group")
@XmlType(propOrder = { "eTime", "item" })
public class SmsGroup {

	/**
	 * @Fields loginName : 登陆名称
	 */
	private String loginName;
	/**
	 * @Fields loginPwd : 登陆密码
	 */
	private String loginPwd;
	/**
	 * @Fields opKind : 业务类型，0标示短信下发业务
	 */
	private String opKind;
	/**
	 * @Fields interFaceID : 调用方辅助标示，默认为空
	 */
	private String interFaceID;
	/**
	 * @Fields serType : 短信业务类型
	 */
	private String serType;

	/**
	 * @Fields eTime : 短信发送时间
	 */
	private String eTime;
	/**
	 * @Fields items : 多条短信消息 ,List 的长度小于10
	 */
	private SmsItem item;

	public String getLoginName() {
		return loginName;
	}

	@XmlAttribute(name = "Login_Name")
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	@XmlAttribute(name = "Login_Pwd")
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getOpKind() {
		return opKind;
	}

	@XmlAttribute(name = "OpKind")
	public void setOpKind(String opKind) {
		this.opKind = opKind;
	}

	public String getInterFaceID() {
		return interFaceID;
	}

	@XmlAttribute(name = "InterFaceID")
	public void setInterFaceID(String interFaceID) {
		this.interFaceID = interFaceID;
	}

	public String getSerType() {
		return serType;
	}

	@XmlAttribute(name = "SerType")
	public void setSerType(String serType) {
		this.serType = serType;
	}

	public String geteTime() {
		return eTime;
	}

	@XmlElement(name = "E_Time")
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public SmsItem getItem() {
		return item;
	}

	@XmlElement(name = "Item")
	public void setItem(SmsItem item) {
		this.item = item;
	}

	/*
	 * public static void main(String[] args) throws JAXBException,
	 * ClientProtocolException, IOException { SmsGroup t = new SmsGroup();
	 * t.seteTime("2014-08-05 18:00:00"); t.setInterFaceID("");
	 * t.setLoginName("hoau_wx");
	 * t.setLoginPwd("182FE825DEB0437931A848578CE853CE"); t.setOpKind("0");
	 * t.setSerType("imt");
	 * 
	 * SmsTask smsTask = new SmsTask(); smsTask.setContent("testContent");
	 * smsTask.setRecivePhoneNumber("13817834363");
	 * smsTask.setSearchID("123456123123");
	 * 
	 * SmsTask smsTask1 = new SmsTask(); smsTask1.setContent("testContent");
	 * smsTask1.setRecivePhoneNumber("13817834363");
	 * smsTask1.setSearchID("123456123123");
	 * 
	 * SmsItem item = new SmsItem();
	 * 
	 * List<SmsTask> items = new ArrayList<SmsTask>(); items.add(smsTask);
	 * items.add(smsTask1); item.setTasks(items); t.setItem(item);
	 * 
	 * String xml = JaxbUtil.marshToXmlBinding(SmsGroup.class, t);
	 * System.out.println(xml);
	 * 
	 * CloseableHttpClient client = HttpClients.createDefault(); HttpPost
	 * httpPost = new HttpPost( "http://fzif.chinavcom.cn/Opration.aspx");
	 * httpPost.setHeader("Content-Type", "application/xml;charset=utf-8");
	 * StringEntity entity = new StringEntity(xml); httpPost.setEntity(entity);
	 * 
	 * CloseableHttpResponse response = client.execute(httpPost); HttpEntity
	 * entity2 = response.getEntity(); InputStream is = entity2.getContent();
	 * 
	 * BufferedReader br = new BufferedReader(new InputStreamReader(is)); String
	 * line = null; while ((line = br.readLine()) != null) {
	 * System.out.println(line); } }
	 */
}
