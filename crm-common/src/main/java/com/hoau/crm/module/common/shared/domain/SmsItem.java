package com.hoau.crm.module.common.shared.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 短信Item
 *
 * @author 蒋落琛
 * @date 2015-12-8
 */
@XmlRootElement(name = "Item")
public class SmsItem {

	private List<SmsTask> tasks;

	public List<SmsTask> getTasks() {
		return tasks;
	}

	@XmlElement(name = "Task")
	public void setTasks(List<SmsTask> tasks) {
		this.tasks = tasks;
	}

}
