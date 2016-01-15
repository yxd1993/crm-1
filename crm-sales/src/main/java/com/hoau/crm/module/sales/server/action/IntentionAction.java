package com.hoau.crm.module.sales.server.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.sales.api.server.service.IIntentionService;
import com.hoau.crm.module.sales.api.shared.domain.IntentionEntity;
import com.hoau.crm.module.sales.api.shared.vo.IntentionVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author 275636
 * 客户意向列表控制器
 * @modifyuser 丁勇
 * @modifydate 2015年6月19日
 */
@Controller
@Scope("prototype")
public class IntentionAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 意向Service
	 * */
	@Resource
	private IIntentionService iIntentionService;
	
	/**
	 * 意向查询实体
	 * */
	private IntentionVo intentionVo;

	/**
	 * 意向实体集合
	 */
	private List<IntentionEntity> intentionList;

	/**
	 * 意向实体
	 */
	private IntentionEntity intentionEntity;
	
	/**
	 * 意向实体ID集合
	 */
	private List<String> ids;
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public IntentionEntity getIntentionEntity() {
		return intentionEntity;
	}

	public void setIntentionEntity(IntentionEntity intentionEntity) {
		this.intentionEntity = intentionEntity;
	}
	
//	/**
//	 * 进入意向查看页面
//	 * 
//	 * @return
//	 * @author 275636
//	 * @date 2015-6-12
//	 * @update
//	 */
//	public String index() {
//		return returnSuccess();
//	}

	/**
	 * 分页查询意向信息
	 * 
	 * @return
	 * @author 275636
	 * @date 2015-6-12
	 * @update
	 */
	public String queryIntentionInfo() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			intentionList = iIntentionService.getIntentionInfo(intentionVo, rb);
			this.setTotalCount(iIntentionService.countIntention(intentionVo));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据主键查询意向信息
	 * 
	 * @return
	 * @author 275636	
	 * @date 2015-6-12
	 * @update
	 */
	public String queryIntentionInfoById(){
		try {
			intentionEntity = iIntentionService.getIntentionInfoById(intentionEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 新增意向信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2016-6-10
	 * @update
	 */
	public String addIntentionInfo() {
		try {
			iIntentionService.addIntentionInfo(intentionEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 删除意向信息
	 * 
	 * @return
	 * @author 275636
	 * @date 2015-6-12
	 * @update
	 */
	public String deleteIntention(){
		try {
			Map<String,Object> maps = new HashMap<String, Object>();
			maps.put("ids", ids);
			maps.put("delDesc", intentionEntity.getDelDesc());
			iIntentionService.deleteIntentionInfo(maps);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	public IntentionVo getIntentionVo() {
		return intentionVo;
	}

	public void setIntentionVo(IntentionVo intentionVo) {
		this.intentionVo = intentionVo;
	}

	public List<IntentionEntity> getIntentionList() {
		return intentionList;
	}

	public void setIntentionList(List<IntentionEntity> intentionList) {
		this.intentionList = intentionList;
	}
}
