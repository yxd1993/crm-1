package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IDailyTrainService;
import com.hoau.crm.module.bse.api.shared.domain.DailyTrainEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author 275636
 *每日培训Action
 */
@Controller
@Scope("prototype")
public class DailyTrainAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 资源需求集合
	 */
	private List<DailyTrainEntity> dailyTrainList;
	
	/**
	 * 资源需求实体
	 */
	private DailyTrainEntity dailyTrain;
	
	
	private List<String> ids;
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	@Resource
	private IDailyTrainService iDailyTrainService;
	
	
	public String queryDailyTrainAction()
	{
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			dailyTrainList = iDailyTrainService.queryDailyTrainList(dailyTrain, rb,currentUser);
			this.setTotalCount(iDailyTrainService.countqueryDailyTrainList(dailyTrain, rb,currentUser));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String deleteDailyTrain()
	{
		try {
			iDailyTrainService.deleteDailyTrain(ids);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public String addDailyTrain()
	{
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			iDailyTrainService.addDailyTrain(dailyTrain,currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	} 

	public List<DailyTrainEntity> getDailyTrainList() {
		return dailyTrainList;
	}

	public void setDailyTrainList(List<DailyTrainEntity> dailyTrainList) {
		this.dailyTrainList = dailyTrainList;
	}

	public DailyTrainEntity getDailyTrain() {
		return dailyTrain;
	}

	public void setDailyTrain(DailyTrainEntity dailyTrain) {
		this.dailyTrain = dailyTrain;
	}
}
