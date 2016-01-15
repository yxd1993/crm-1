package com.hoau.crm.module.common.shared.domain;

/**
 * IOS消息实体
 *
 * @author 蒋落琛
 * @date 2015-6-16
 */
public class PushMessageIOSEntity extends PushMegBas {

	/**
	 * 音频对象
	 */
	private PushAPS aps;

	/**
	 * 消息ID
	 */
	private int ioskeyid;

	public PushAPS getAps() {
		return aps;
	}

	public void setAps(PushAPS aps) {
		this.aps = aps;
	}

	public int getIoskeyid() {
		return ioskeyid;
	}

	public void setIoskeyid(int ioskeyid) {
		this.ioskeyid = ioskeyid;
	}
}
