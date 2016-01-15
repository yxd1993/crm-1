package com.hoau.crm.module.customer.api.shared.domain;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class ExcelReturn {
	private String fileName;
	private InputStream inputStream;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName)
			throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String agent = request.getHeader("User-Agent");
		if (null != agent) {
			agent = agent.toLowerCase();
			if (agent.indexOf("firefox") != -1) {
				this.fileName = new String(URLDecoder.decode(fileName, "UTF-8")
						.getBytes(), "ISO8859-1");
			} else if (agent.indexOf("chrome") != -1) {
				this.fileName = new String(URLDecoder.decode(fileName, "UTF-8")
						.getBytes(), "ISO8859-1");
			}
		}

	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
