package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IResDemandService;
import com.hoau.crm.module.bse.api.shared.domain.ResDemandEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author 275636
 *资源需求Action
 */
@Controller
@Scope("prototype")
public class ResDemandAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资源需求集合
	 */
	private List<ResDemandEntity> resDemandList;
	
	/**
	 * 资源需求实体
	 */
	private ResDemandEntity resDemand;
	
	
	private List<String> ids;
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Resource
	private IResDemandService iResDemandService;
	
	
	public String queryResDemandAction()
	{
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			resDemandList = iResDemandService.queryResDemandList(resDemand, rb,currentUser);
			this.setTotalCount(iResDemandService.countqueryResDemandList(resDemand, rb,currentUser));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String deleteResDemand()
	{
		try {
			iResDemandService.deleteResDemand(ids);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String addResDemand()
	{
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			iResDemandService.addResDemand(resDemand,currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	} 

	public List<ResDemandEntity> getResDemandList() {
		return resDemandList;
	}

	public void setResDemandList(List<ResDemandEntity> resDemandList) {
		this.resDemandList = resDemandList;
	}

	public ResDemandEntity getResDemand() {
		return resDemand;
	}

	public void setResDemand(ResDemandEntity resDemand) {
		this.resDemand = resDemand;
	}

}
