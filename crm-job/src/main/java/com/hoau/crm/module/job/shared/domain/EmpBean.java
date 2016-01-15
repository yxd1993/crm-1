package com.hoau.crm.module.job.shared.domain;

/**
 * OA人员信息实体
 *
 * @author 蒋落琛
 * @date 2015-5-18
 */
/**
 * @author: 何斌
 * @create: 2015年5月22日 上午11:12:14
 */
public class EmpBean {
	
	/**
	 * 工号
	 */
	private String workcode ;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String gender;
	
	/**
	 * 拼音名
	 */
	private String pinYinName;
	/**
	 * 拼音简写名称
	 */
	private String pinYinShortName;
	
	/**
	 * 账号
	 */
	private String account;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 部门名称
	 */
	private String deptname;
	
	/**
	 * 岗位名称
	 */
	private String jobname;
	
	/**
	 * 部门编码
	 */
	private String deptcode;
	
	/**
	 * 岗位编码
	 */
	private String jobcode;
	
	/**
	 * 直接上级
	 */
	private String managerCode;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 公司邮箱
	 */
	private String email;
	
	/**
	 * 座机
	 */
	private String telePhone;
	
	/**
	 * 人员状态
	 * 0 试用 
	 * 1 正式
	 * 2 实习
	 * 5离职
	 */
	private String status;

	public String getWorkcode() {
		return workcode;
	}

	public void setWorkcode(String workcode) {
		this.workcode = workcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPinYinName() {
		return pinYinName;
	}

	public void setPinYinName(String pinYinName) {
		this.pinYinName = pinYinName;
	}

	public String getPinYinShortName() {
		return pinYinShortName;
	}

	public void setPinYinShortName(String pinYinShortName) {
		this.pinYinShortName = pinYinShortName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getJobcode() {
		return jobcode;
	}

	public void setJobcode(String jobcode) {
		this.jobcode = jobcode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
