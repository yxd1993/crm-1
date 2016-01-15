package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.appcore.api.shared.domain.FeedBackEntity;
import com.hoau.crm.module.sales.api.server.service.IFeedBackInfoService;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年7月14日
 * @description：用户反馈action
 */
@Controller
@Scope("prototype")
public class FeedBackInfoAction extends AbstractAction {

	private static final long serialVersionUID = 4891095776593813872L;
	
	/**
	 * FeedBackEntity集合对象，用于存放从数据库中查询到的用户反馈信息数据
	 */
	private List<FeedBackEntity> feedBackInfolist;

	/**
	 * 用户反馈信息实体对象，用于接收前台传过来的数据
	 */
	private FeedBackEntity feedBackInfo;

	@Resource
	private IFeedBackInfoService feedBackInfoService;

	/**
	 * 
	 * 分页查询用户反馈信息
	 * 
	 * @return
	 * @author: 潘强
	 * @date: 2015年7月15日
	 * @update
	 */
	public String queryFeedBackInfo() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			feedBackInfolist = feedBackInfoService.getFeedBackInfo(
					feedBackInfo, rb);
			this.setTotalCount(feedBackInfoService.countFeedBackInfo(
					feedBackInfo, rb));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();

	}

	public FeedBackEntity getFeedBackInfo() {
		return feedBackInfo;
	}

	public void setFeedBackInfo(FeedBackEntity feedBackInfo) {
		this.feedBackInfo = feedBackInfo;
	}

	public List<FeedBackEntity> getFeedBackInfolist() {
		return feedBackInfolist;
	}

	public void setFeedBackInfolist(List<FeedBackEntity> feedBackInfolist) {
		this.feedBackInfolist = feedBackInfolist;
	}

}
