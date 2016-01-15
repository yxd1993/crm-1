/**
 * 
 */
package com.hoau.crm.module.appcore.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.IReviewHistoryAppService;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.ReviewHistoryAppVo;
import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.ReviewHistoryVo;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 历史和工作回顾 restful 接口实现
 * 
 * @author 丁勇
 * @date
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ReviewHistoryAppService implements IReviewHistoryAppService {
	@Resource
	IReviewHistoryService ireviewhistroyservice;
	@Resource
	ILoginService loginservice;

	@Override
	public ResponseBaseEntity<ReviewHistoryAppVo> queryHistoryList(
			ReviewHistoryAppVo rhaVo, String loginName) {
		// 参数map
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取创建人工号
//		UserEntity currUser = loginservice.getUserByLoginName(loginName);
//		map.put("createUser", currUser.getEmpEntity().getEmpCode());
		// 实体信息和客户id不能为空
		if (StringUtils.isEmpty(rhaVo.getAccountId())
				|| StringUtils.isEmpty(rhaVo)) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		map.put("accountId", rhaVo.getAccountId());
		// 判断客户端是否有分页条件
		if (rhaVo.getLimit() == 0) {
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		// 分页查询条件
		RowBounds rb = new RowBounds(rhaVo.getStart(), rhaVo.getLimit());
		// 查询结果
		List<ReviewHistoryVo> historylist = ireviewhistroyservice
				.queryHistoryList(map, rb);
		// 总数
		long count = ireviewhistroyservice.queryHistoryCount(map);
		ReviewHistoryAppVo rVo = new ReviewHistoryAppVo();
		rVo.setReviewhistoryvo(historylist);
		rVo.setTotalCount(count);
		// 返回值
		ResponseBaseEntity<ReviewHistoryAppVo> result = new ResponseBaseEntity<ReviewHistoryAppVo>();
		result.setResult(rVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	public ResponseBaseEntity<ReviewHistoryAppVo> queryReviewList(
			ReviewHistoryAppVo rhaVo, String loginName) {
		// 参数map
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取创建人工号
		UserEntity currUser = loginservice.getUserByLoginName(loginName);
		map.put("createUser", currUser.getEmpEntity().getEmpCode());
		// 实体信息不能为空
		if (StringUtils.isEmpty(rhaVo)) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		// 格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtils.isEmpty(rhaVo.getStartDate())) {
			map.put("startDate", sdf.format(rhaVo.getStartDate()));
		}
		if (!StringUtils.isEmpty(rhaVo.getEndDate())) {
			map.put("endDate", sdf.format(rhaVo.getEndDate()));
		}
		// 判断客户端是否有分页条件
		if (rhaVo.getLimit() == 0) {
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		// 分页查询条件
		RowBounds rb = new RowBounds(rhaVo.getStart(), rhaVo.getLimit());

		// 查询结果
		List<ReviewHistoryVo> historylist = ireviewhistroyservice
				.queryReviewList(map, rb);
		// 总数
		long count = ireviewhistroyservice.queryReviewCount(map);
		// 封装结果集
		ReviewHistoryAppVo rVo = new ReviewHistoryAppVo();
		rVo.setReviewhistoryvo(historylist);
		rVo.setTotalCount(count);
		// 返回值
		ResponseBaseEntity<ReviewHistoryAppVo> result = new ResponseBaseEntity<ReviewHistoryAppVo>();
		result.setResult(rVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

}
