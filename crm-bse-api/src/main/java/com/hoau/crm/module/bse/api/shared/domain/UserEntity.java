package com.hoau.crm.module.bse.api.shared.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hoau.hbdp.framework.entity.BaseEntity;
import com.hoau.hbdp.framework.entity.IRole;
import com.hoau.hbdp.framework.entity.IUser;
import com.hoau.hbdp.framework.server.web.session.SessionValue;

/**
 * 用户信息实体
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
@SessionValue
public class UserEntity extends BaseEntity implements IUser {

	private static final long serialVersionUID = 7393897262091264640L;

	/**
	 *  用户登录名
	 */
	private String userName;

	/**
	 *  用户登录密码
	 */
	private String password;

	/**
	 *  用户最后登录时间
	 */
	private Date lastLogin;

	/**
	 *  用户状态
	 */
	private String active;

	/**
	 *  用户启用时间
	 */
	private Date beginTime;

	/**
	 *  用户禁用时间
	 */
	private Date endTime;

	/**
	 *  职员对象
	 */
	private EmployeeEntity empEntity;

	/**
	 *  用户所拥有的功能信息ID集合
	 */
	private Set<String> functionCodes;

	/**
	 *  存入用户uri地址信息
	 */
	private Set<String> functionUris;

	/**
	 *  用户所拥有的角色信息ID集合
	 */
	private Set<String> roleids;

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public Set<String> getFunctionCodes() {
		return functionCodes;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public Date getLastLogin() {
		return this.lastLogin;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public String getPassword() {
		return this.password;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	@Override
	public Set<String> getRoleids() {
		return roleids;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	@Override
	@Deprecated
	public List<IRole> getRoles() {
		return null;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	@Override
	public Set<String> queryAccessUris() {
		return this.functionUris;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public Set<String> getFunctionUris() {
		return functionUris;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setFunctionUris(Set<String> functionUris) {
		this.functionUris = functionUris;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setEmpEntity(EmployeeEntity empEntity) {
		this.empEntity = empEntity;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setFunctionCodes(Set<String> functionCodes) {
		this.functionCodes = functionCodes;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	@Override
	public void setRoleids(Set<String> roleids) {
		this.roleids = roleids;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	@Override
	@Deprecated
	public void setRoles(List<IRole> roles) {
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public String getActive() {
		return active;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * GET方法 获取数据 SET方法 设置数据
	 * */
	public EmployeeEntity getEmpEntity() {
		return empEntity;
	}

}
