package com.hoau.crm.module.bse.api.shared.domain;


/**
 * 手机信息实体
 *
 * @author 蒋落琛
 * @date 2015-6-9
 */
public class PhoneInfoEntity{
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 手机号
	 */
	private String Mobile;
	
	/**
	 * 省
	 */
	private String Province;
	
	/**
	 * 市
	 */
	private String City;
	
	/**
	 * 区号
	 */
	private String AreaCode;
	
	/**
	 * 邮编
	 */
	private String PostCode;
	
	/**
	 * 服务商
	 */
	private String Operators;
	
	/**
	 * 卡类型
	 */
	private String Card;
	
	/**
	 * 地址
	 */
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getAreaCode() {
		return AreaCode;
	}

	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}

	public String getPostCode() {
		return PostCode;
	}

	public void setPostCode(String postCode) {
		PostCode = postCode;
	}

	public String getOperators() {
		return Operators;
	}

	public void setOperators(String operators) {
		Operators = operators;
	}

	public String getCard() {
		return Card;
	}

	public void setCard(String card) {
		Card = card;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}