package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IDailyProblemService;
import com.hoau.crm.module.bse.api.shared.domain.DailyProblemEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author 275636
 *每日问题Action
 */
@Controller
@Scope("prototype")
public class DailyProblemAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 资源需求集合
	 */
	private List<DailyProblemEntity> dailyProblemList;
	
	/**
	 * 资源需求实体
	 */
	private DailyProblemEntity dailyProblem;
	
	
	private List<String> ids;
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Resource
	private IDailyProblemService iDailyProblemService;
	
	
	public String queryDailyProblemAction()
	{
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			dailyProblemList = iDailyProblemService.queryDailyProblemList(dailyProblem, rb,currentUser);
			this.setTotalCount(iDailyProblemService.countqueryDailyProblemList(dailyProblem, rb,currentUser));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String deleteDailyProblem()
	{
		try {
			iDailyProblemService.deleteDailyProblem(ids);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String addDailyProblem()
	{
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			iDailyProblemService.addDailyProblem(dailyProblem,currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	} 

	public List<DailyProblemEntity> getDailyProblemList() {
		return dailyProblemList;
	}

	public void setDailyProblemList(List<DailyProblemEntity> dailyProblemList) {
		this.dailyProblemList = dailyProblemList;
	}

	public DailyProblemEntity getDailyProblem() {
		return dailyProblem;
	}

	public void setDailyProblem(DailyProblemEntity dailyProblem) {
		this.dailyProblem = dailyProblem;
	}
}
