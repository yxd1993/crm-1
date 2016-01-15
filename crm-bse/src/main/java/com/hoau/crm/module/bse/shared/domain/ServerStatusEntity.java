/**
 * 
 */
package com.hoau.crm.module.bse.shared.domain;

import com.hoau.hbdp.framework.entity.BaseEntity;

/**
 * 记录Nginx服务器状态
 * 
 * @author 丁勇
 * @date 2015年8月12日
 */
public class ServerStatusEntity extends BaseEntity {

	@Override
	public String toString() {
		return "活跃的连接数量:" + this.activeConnections + "\n" + "总共处理连接:"
				+ this.handledConnections + "\n" + "成功创建握手数:"
				+ this.handledSuccess + "\n" + "总共处理请求:" + this.handledRequest
				+ "\n" + "读取客户端的连接数:" + this.reading + ",响应数据到客户端的数量:"
				+ this.writing + ",已经处理完正在等候下一次请求指令的驻留连接数:" + this.waiting;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -3787287608904344823L;

	/**
	 * 活跃的连接数量
	 */
	private String activeConnections;
	/**
	 * 总共处理了多少个连接
	 */
	private String handledConnections;
	/**
	 * 成功创建多少次握手
	 */
	private String handledSuccess;
	/**
	 * 总共处理了多少个请求
	 */
	private String handledRequest;
	/**
	 * 读取客户端的连接数
	 */
	private String reading;
	/**
	 * 响应数据到客户端的数量
	 */
	private String writing;
	/**
	 * 已经处理完正在等候下一次请求指令的驻留连接.
	 */
	private String waiting;

	public String getActiveConnections() {
		return activeConnections;
	}

	public void setActiveConnections(String activeConnections) {
		this.activeConnections = activeConnections;
	}

	public String getHandledConnections() {
		return handledConnections;
	}

	public void setHandledConnections(String handledConnections) {
		this.handledConnections = handledConnections;
	}

	public String getHandledSuccess() {
		return handledSuccess;
	}

	public void setHandledSuccess(String handledSuccess) {
		this.handledSuccess = handledSuccess;
	}

	public String getHandledRequest() {
		return handledRequest;
	}

	public void setHandledRequest(String handledRequest) {
		this.handledRequest = handledRequest;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}

	public String getWaiting() {
		return waiting;
	}

	public void setWaiting(String waiting) {
		this.waiting = waiting;
	}
}
