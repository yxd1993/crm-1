/*
 * All rights Reserved, Designed By DINGYONG
 * Copyright: Copyright(C) 2015
 */
package com.hoau.crm.module.news.shared.domain;

import java.util.List;

/**
 * 
 * @author 丁勇
 * @date 2015年9月22日
 */
public class DataTableObject {
	/**
	 * 重绘请求的次数
	 */
	private int draw;
	/**
	 * 开始记录数
	 */
	private int start;
	/**
	 * 展示条数
	 */
	private int length;
	/**
	 * 总记录数
	 */
	private long recordsTotal;
	/**
	 * 过滤后的总记录数
	 */
	private long recordsFiltered;
	/**
	 * 数据源
	 */
	private List<?> data;
	/**
	 * 错误提示
	 */
	private String error;

	public DataTableObject() {
		super();
	}

	/**
	 * @param draw
	 * @param start
	 * @param length
	 * @param recordsTotal
	 * @param recordsFiltered
	 * @param data
	 */
	public DataTableObject(int draw, int start, int length, long recordsTotal,
			long recordsFiltered, List<?> data, String error) {
		super();
		this.draw = draw;
		this.start = start;
		this.length = length;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
		this.error = error;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
