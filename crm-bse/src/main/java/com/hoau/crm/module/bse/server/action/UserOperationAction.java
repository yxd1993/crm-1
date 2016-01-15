package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import com.hoau.crm.module.appcore.api.shared.domain.UserOperationEntity;
import com.hoau.crm.module.sales.api.server.service.IUserOperationService;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年7月20日
 * @description：客户操作信息action
 */
@Controller
@Scope("prototype")

public class UserOperationAction extends AbstractAction {

	
	private static final long serialVersionUID = 218667811289537544L;

	/**
	 * UserOperationEntity集合对象，用于存放从数据库中查询到的客户操作信息数据
	 */
	private List<UserOperationEntity> userOperationList;

	/**
	 * 客户操作信息实体对象，用于接收前台传过来的数据
	 */
	private UserOperationEntity userOperation;

	@Resource
	private IUserOperationService userOperationService;

	/**
	 * 
	 * 分页查询客户操作信息
	 * 
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月20日
	 * @update
	 * 
	 */
	public String queryUserOperationAction() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			userOperationList = userOperationService.getUserOperation(
					userOperation, rb);
			this.setTotalCount(userOperationService.countUserOperation(
					userOperation, rb));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();

	}

	public List<UserOperationEntity> getUserOperationList() {
		return userOperationList;
	}

	public void setUserOperationlist(List<UserOperationEntity> userOperationList) {
		this.userOperationList = userOperationList;
	}

	public UserOperationEntity getUserOperation() {
		return userOperation;
	}

	public void setUserOperation(UserOperationEntity userOperation) {
		this.userOperation = userOperation;
	}
	
}
