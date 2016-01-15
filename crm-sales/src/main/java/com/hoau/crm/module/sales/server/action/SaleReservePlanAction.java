/**
 * 
 */
package com.hoau.crm.module.sales.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.sales.api.server.service.ISaleReserveService;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * 
 * @author 丁勇
 * @date 2015年6月10日
 */
@Controller
@Scope("prototype")
public class SaleReservePlanAction extends AbstractAction {
	/**
	 *
	 */
	private static final long serialVersionUID = 7866076052349852335L;
	/**
	 * 预约信息service
	 */
	@Resource
	ISaleReserveService iReservesevice;
	/**
	 * 预约列表
	 */
	private List<SaleReserveEntity> reserveList;
	/**
	 * 预约实体
	 */
	private SaleReserveEntity reserveEntity;
	/**
	 * 预约vo对象
	 */
	private SaleReserveVo reserveVo;
	/**
	 * 预约id集合
	 */
	private List<String> ids;
	/**
	 *预约vo列表
	 */
	private List<SaleReserveVo> reserveVoList;
	
	/**
	 * 分页查询
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月10日
	 * @update
	 */
	public String queryReserveByPaging() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			reserveList = iReservesevice.getReserveByPaging(reserveVo, rb);
			this.setTotalCount(iReservesevice.reserveCount(reserveVo));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 修改 按id查询预约信息
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update
	 */
	public String queryReserveByIdGetEntity() {
		try {
			//获取当前用户
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			reserveEntity = iReservesevice.getReserveDetailByIdUseUpdate(reserveEntity,currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 只显示预约详情 不做修改
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月18日
	 * @update
	 */
	public String queryReserveByIdGetVo() {
		try {
			reserveVo = iReservesevice.getReserveDetailById(reserveEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 新增,修改预约
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update
	 */
	public String saveOrUpdateReserve() {
		 //获取当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		try {
			iReservesevice.saveOrUpdateReservePlan(reserveEntity,currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 删除预约信息
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月15日
	 * @update
	 */
	public String delReserve() {
		 //获取当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		try {
			iReservesevice.delete(ids, reserveEntity.getDelDesc(),currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 查询 未赴约信息
	 * @return
	 * @author 丁勇
	 * @date 2015年7月1日
	 * @update 
	 */
	public String queryNoReserve() {
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		try {
			reserveVoList = iReservesevice.queryNotReserve(reserveVo,currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * @return the reserveEntity
	 */
	public SaleReserveEntity getReserveEntity() {
		return reserveEntity;
	}

	/**
	 * @return the ids
	 */
	public List<String> getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	/**
	 * @return the reserveList
	 */
	public List<SaleReserveEntity> getReserveList() {
		return reserveList;
	}

	/**
	 * @return the reserveVo
	 */
	public SaleReserveVo getReserveVo() {
		return reserveVo;
	}

	/**
	 * @param reserveEntity
	 *            the reserveEntity to set
	 */
	public void setReserveEntity(SaleReserveEntity reserveEntity) {
		this.reserveEntity = reserveEntity;
	}

	/**
	 * @param reserveList
	 *            the reserveList to set
	 */
	public void setReserveList(List<SaleReserveEntity> reserveList) {
		this.reserveList = reserveList;
	}

	/**
	 * @param reserveVo
	 *            the reserveVo to set
	 */
	public void setReserveVo(SaleReserveVo reserveVo) {
		this.reserveVo = reserveVo;
	}

	/**
	 * @return the reserveVoList
	 */
	public List<SaleReserveVo> getReserveVoList() {
		return reserveVoList;
	}

	/**
	 * @param reserveVoList
	 *            the reserveVoList to set
	 */
	public void setReserveVoList(List<SaleReserveVo> reserveVoList) {
		this.reserveVoList = reserveVoList;
	}
}