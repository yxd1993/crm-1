package com.hoau.crm.module.sales.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.sales.api.server.service.ISignService;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年7月20日
 * @description：客户签到信息action
 */

@Controller
@Scope("prototype")
public class SignAction extends AbstractAction {

	private static final long serialVersionUID = 3781622481942329754L;

	@Resource
	private ISignService signService;
	
	/**
	 * 用来存放前台传过来的查询数据条件
	 */
	private SignEntity sign;

	/**
	 * 用来存放数据库中客户签到信息数据
	 */
	private List<SignEntity> signList;
	
	/**
	 * 未绑定签到列表
	 */
	private List<SignEntity> signNoRelationList;

	public String querySignAction() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			signList = signService.getSign(sign, rb);
			this.setTotalCount(signService.countSign(sign));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 查询未关联的签到信息
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月14日
	 * @update 
	 */
	public String querySignInfoNoRelation(){
		try {
			RowBounds rb = new RowBounds(this.start,30);
			signNoRelationList = signService.getSignInfoNoRelation(sign, rb);
			this.setTotalCount(signService.countSignInfoNoRelation(sign));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public SignEntity getSign() {
		return sign;
	}

	public void setSign(SignEntity sign) {
		this.sign = sign;
	}
	public List<SignEntity> getSignList() {
		return signList;
	}

	public void setSignList(List<SignEntity> signList) {
		this.signList = signList;
	}

	public List<SignEntity> getSignNoRelationList() {
		return signNoRelationList;
	}
	
	public void setSignNoRelationList(List<SignEntity> signNoRelationList) {
		this.signNoRelationList = signNoRelationList;
	}


}
