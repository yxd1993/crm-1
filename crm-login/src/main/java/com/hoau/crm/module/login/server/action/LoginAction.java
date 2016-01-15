package com.hoau.crm.module.login.server.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.IReportDataService;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.components.security.SecurityNonCheckRequired;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 
 * @author 高佳
 * @date 2015年5月13日
 */
@Controller
@Scope("prototype")
public class LoginAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ILoginService loginService;
	
	@Resource
	private IReportDataService reportDataService;

	/**
	 * 当前用户
	 */
	private UserEntity currentUser;
	
	/**
	 *当前服务器时间
	 */
	private Date currentServerTime;
	
	/**
	 * 跳转登录界面
	 * @return
	 * @author 高佳
	 * @date 2015年5月13日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String index() {
		//当前用户组织
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		if(currentUser == null){
			return returnError("");
		} else {
			return returnSuccess();
		}
	}
	
	/**
	 * 进入CRM系统宣传页
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-8-31
	 * @update
	 */
	@SecurityNonCheckRequired
	public String toPropagandaPage(){
		return returnSuccess();
	}
	
	/**
	 * 跳转谷歌浏览器下载界面
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-6-12
	 * @update
	 */
	@SecurityNonCheckRequired
	public String toDownChrome(){
		return returnSuccess();
	}
	
	/**
	 * 退出系统
	 */
	public String userLogout() {
		loginService.userLogout();
		return returnSuccess();
	}
	
	/**
	 * 跳转主界面
	 * @return
	 * @author 高佳
	 * @date 2015年5月13日
	 * @update 
	 */
	public String main() {
		return returnSuccess();
	}
	
	/**
	 * 顶部界面
	 * @return
	 * @author 莫涛
	 * @date 2015年6月3日
	 * @update
	 */
	public String top(){
		long count = reportDataService.countAllCustomer();
		ServletActionContext.getRequest().setAttribute("countCustomer", count);
		return returnSuccess();
	}
	
	/**
	 * 登录验证
	 * @return
	 * @author 高佳
	 * @date 2015年5月13日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String login(){
		try {
			loginService.verifyPassword(currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String currentLoginUserInfo(){
		currentUser = (UserEntity) UserContext.getCurrentUser();
		currentServerTime = new Date();
		return returnSuccess();
	}
	public UserEntity getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserEntity currentUser) {
		this.currentUser = currentUser;
	}

	public Date getCurrentServerTime() {
		return currentServerTime;
	}

	public void setCurrentServerTime(Date currentServerTime) {
		this.currentServerTime = currentServerTime;
	}
	
}
