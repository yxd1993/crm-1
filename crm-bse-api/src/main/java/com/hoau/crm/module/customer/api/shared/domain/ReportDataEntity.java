package com.hoau.crm.module.customer.api.shared.domain;


/**
 * 首页报表数据实体
 * 
 * @author: 何斌
 * @create: 2015年6月2日 下午5:32:34
 */
	
public class ReportDataEntity{
	
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 数据1
	 */
	private int dataOne;
	/**
	 * 数据2
	 */
	private int dataTwo;
	
	/**
	 * 数据3
	 */
	private double dataThree;
	
	/**
	 * 数据4
	 */
	private double dataFour;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDataOne() {
		return dataOne;
	}
	public void setDataOne(int dataOne) {
		this.dataOne = dataOne;
	}
	public int getDataTwo() {
		return dataTwo;
	}
	public void setDataTwo(int dataTwo) {
		this.dataTwo = dataTwo;
	}
	public double getDataThree() {
		return dataThree;
	}
	public void setDataThree(double dataThree) {
		this.dataThree = dataThree;
	}
	public double getDataFour() {
		return dataFour;
	}
	public void setDataFour(double dataFour) {
		this.dataFour = dataFour;
	}
	
}
