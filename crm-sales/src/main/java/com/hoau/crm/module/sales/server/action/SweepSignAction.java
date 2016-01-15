package com.hoau.crm.module.sales.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.sales.api.server.service.ISweepSignService;
import com.hoau.crm.module.sales.api.shared.domain.SweepSignEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年7月27日
 * @description：客户扫描签到action
 */
@Controller
@Scope("prototype")
public class SweepSignAction extends AbstractAction {

	private static final long serialVersionUID = 5265241863323458759L;

	/**
	 * 用来存放数据库中客户扫描签到信息数据
	 */
	private List<SweepSignEntity> sweepSignList;

	/**
	 * 用来存放前台传送过来的查询信息
	 */
	private SweepSignEntity sweepSign;

	@Resource
	private ISweepSignService sweepSignService;

	public String querySweepSignAction() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			sweepSignList = sweepSignService.getSweepSign(sweepSign, rb);
			this.setTotalCount(sweepSignService.countSweepSign(sweepSign, rb));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}

	public List<SweepSignEntity> getSweepSignList() {
		return sweepSignList;
	}

	public void setSweepSignList(List<SweepSignEntity> sweepSignList) {
		this.sweepSignList = sweepSignList;
	}

	public SweepSignEntity getSweepSign() {
		return sweepSign;
	}

	public void setSweepSign(SweepSignEntity sweepSign) {
		this.sweepSign = sweepSign;
	}

}
