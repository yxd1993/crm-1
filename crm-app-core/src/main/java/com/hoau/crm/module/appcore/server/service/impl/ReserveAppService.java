package com.hoau.crm.module.appcore.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.IReserveAppService;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.ReserveAppVo;
import com.hoau.crm.module.appcore.server.dao.ReserveAppMapper;
import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.server.service.ISaleReserveService;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 预约功能 restful 接口实现
 * 
 * @author 丁勇
 * @date 2015年7月6日
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ReserveAppService implements IReserveAppService {
	// 注入相应的资源
	@Resource
	ReserveAppMapper reserveAppMapper;
	@Resource
	ILoginService iLoginService;
	@Resource
	ISaleReserveService iReservesevice;
	@Resource
	IReviewHistoryService iReviewHistoryService;

	@Override
	@Transactional
	public ResponseBaseEntity<ReserveAppVo> saveOrUpdate(
			ReserveAppVo reserveVo, String loginName) {
		// 实体信息不能为空
		if (StringUtils.isEmpty(reserveVo.getReserveEntity())) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		// 获取用户信息
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		iReservesevice.saveOrUpdateReservePlan(reserveVo.getReserveEntity(),
				user);
		// 返回值
		ResponseBaseEntity<ReserveAppVo> result = new ResponseBaseEntity<ReserveAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ReserveAppVo> queryReserveById(
			ReserveAppVo reserveVo, String loginName) {
		// 实体信息和id都不能为空
		if (StringUtils.isEmpty(reserveVo.getReserveEntity())
				|| StringUtils.isEmpty(reserveVo.getReserveEntity().getId())) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		reserveVo = reserveAppMapper.queryReserveById(reserveVo
				.getReserveEntity().getId());
		// // 时间计算
		// if (!StringUtils.isEmpty(reserveVo.getReserveEntity()
		// .getReserveEndTime())
		// && !StringUtils.isEmpty(reserveVo.getReserveEntity()
		// .getReserveEndTime())
		// || !StringUtils.isEmpty(reserveVo.getReserveEntity())) {
		// //时间差赋值
		// reserveVo.setReserveTime(getTimeDifference(reserveVo
		// .getReserveEntity().getReserveEndTime(), reserveVo
		// .getReserveEntity().getReserveStartTime()));
		// }
		// 返回值
		ResponseBaseEntity<ReserveAppVo> result = new ResponseBaseEntity<ReserveAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		result.setResult(reserveVo);
		return result;
	}

	@Override
	public ResponseBaseEntity<ReserveAppVo> delReserve(ReserveAppVo reserveVo,
			String loginName) {
		// 实体信息和id都不能为空
		if (StringUtils.isEmpty(reserveVo.getReserveEntity())
				|| StringUtils.isEmpty(reserveVo.getIds())
				&& reserveVo.getIds().size() < 0) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		// 获取用户
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		// 删除的必须填写原因
		if (StringUtils.isEmpty(reserveVo.getReserveEntity().getDelDesc())) {
			throw new SalesCommonException(SalesCommonException.DEL_DESC_NULL);
		}
		// 执行删除
		iReservesevice.delete(reserveVo.getIds(), reserveVo.getReserveEntity()
				.getDelDesc(), user);
		// 返回值
		ResponseBaseEntity<ReserveAppVo> result = new ResponseBaseEntity<ReserveAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ReserveAppVo> getReserveByPaging(
			ReserveAppVo reserveVo, String loginName) {
		// 实体信息不能为空
		if (StringUtils.isEmpty(reserveVo.getStartDate())
				|| StringUtils.isEmpty(reserveVo.getEndDate())
				|| StringUtils.isEmpty(reserveVo)) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		ReserveAppVo resultVo = new ReserveAppVo();
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		// 分页查询条件
		RowBounds rb;
		// 创建存储map
		Map<String, Object> map = new HashMap<String, Object>();
		// 格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 查询预约时间
		if (!StringUtils.isEmpty(reserveVo.getStartDate())) {
			map.put("reserveStartTime", sdf.format(reserveVo.getStartDate()));
		}
		if (!StringUtils.isEmpty(reserveVo.getEndDate())) {
			map.put("reserveEndTime", sdf.format(reserveVo.getEndDate()));
		}
		// 查看权限用户
		map.put("userCode", currentUser.getEmpEntity().getEmpCode());
		if (reserveVo.getLimit() == 0) {
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		rb = new RowBounds(reserveVo.getStart(), reserveVo.getLimit());
		// 执行查询
		List<SaleReserveEntity> reserveList = reserveAppMapper.getReserveByPaging(
				map, rb);
		// 总数
		long count = reserveAppMapper.reserveCount(map);
		// 预约列表结果
		resultVo.setReserveDayList(reserveList);
		resultVo.setTotalCount(count);
		// 返回值
		ResponseBaseEntity<ReserveAppVo> result = new ResponseBaseEntity<ReserveAppVo>();
		result.setResult(resultVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ReserveAppVo> queryNoReserve(
			SaleReserveVo reserveVo, String loginName) {
		ReserveAppVo appvo = new ReserveAppVo();
		// 当前用户
		UserEntity currentUser = iLoginService.getUserByLoginName(loginName);
		// 判断客户主键
		if (StringUtils.isEmpty(reserveVo.getAccountId())
				|| StringUtils.isEmpty(reserveVo)) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 获取未赴约的预约列表
		List<SaleReserveVo> noReserveList = iReservesevice.queryNotReserve(
				reserveVo, currentUser);
		// 返回list
		appvo.setNoReserveList(noReserveList);
		// 返回值
		ResponseBaseEntity<ReserveAppVo> result = new ResponseBaseEntity<ReserveAppVo>();
		result.setResult(appvo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	/**
	 * 计算时间差
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 * @author 丁勇
	 * @date 2015年7月9日
	 * @update
	 */
	public String getTimeDifference(Date startTime, Date endTime) {
		// 换算时,分
		StringBuffer sb = new StringBuffer();
		long reserveTime = endTime.getTime() - startTime.getTime();
		long hour = (reserveTime / (60 * 60 * 1000));
		long min = (reserveTime / (60 * 1000)) % 60;
		sb.append(hour + "小时" + min + "分");
		return sb.toString();
	}
}