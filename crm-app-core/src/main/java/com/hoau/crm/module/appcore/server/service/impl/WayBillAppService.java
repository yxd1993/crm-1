package com.hoau.crm.module.appcore.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.bse.api.server.service.IWayBillAppService;
import com.hoau.crm.module.bse.api.server.service.IWayBillService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.WayBillEntity;
import com.hoau.crm.module.bse.api.shared.exception.WayBillException;
import com.hoau.crm.module.bse.api.shared.vo.WayBillVo;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 消息 APP SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-6-29
 */
@Service
public class WayBillAppService implements IWayBillAppService {

	@Resource
	private IWayBillService iWayBillService;

	@Override
	@Transactional
	public ResponseBaseEntity<WayBillVo> addWayBillInfo(WayBillVo waybillVo) {
		// 判断信息是否为空
		if (waybillVo == null || waybillVo.getWayBillList() == null
				|| waybillVo.getWayBillList().size() == 0) {
			throw new WayBillException(WayBillException.Way_Bill_NULL);
		}
		// 处理成功或失败的信息ID集合
		List<String> successIdList = new ArrayList<String>();
		List<String> failureIdList = new ArrayList<String>();
		// 循环处理运单信息
		for (int i = 0; i < waybillVo.getWayBillList().size(); i++) {
			WayBillEntity waybillEntity = waybillVo.getWayBillList().get(i);
			try {
				// 判断是否存在主键
				if (!StringUtil.isEmpty(waybillEntity.getId())
						&& !StringUtil.isEmpty(waybillEntity.getDataType())) {
					// 新增运单信息
					if (waybillEntity.getDataType().equals(
							BseConstants.WAY_BILL)) {
						// 查询运单是否存在
						if (iWayBillService.getWayBillByBillNum(waybillEntity
								.getBillNum()) == 0) {
							// 新增运单信息
							iWayBillService.addWayBill(waybillEntity);
						} else {
							iWayBillService.updateWayBill(waybillEntity);
						}
						// 修改金额
					} else if (waybillEntity.getDataType().equals(
							BseConstants.WAY_BILL_MONEY)) {
						// 查询运单是否存在
						if (iWayBillService.getWayBillByBillNum(waybillEntity
								.getBillNum()) > 0) {
							iWayBillService.updateWayBillAmount(waybillEntity);
						}
						// 删除运单
					} else if (waybillEntity.getDataType().equals(
							BseConstants.WAY_BILL_DELETE)) {
						// 查询运单是否存在
						if (iWayBillService.getWayBillByBillNum(waybillEntity
								.getBillNum()) > 0) {
							iWayBillService.deleteWayBill(waybillEntity);
						}
						// 签收
					}else if (waybillEntity.getDataType().equals(
							BseConstants.WAY_BILL_SIGN)) {
						// 查询运单是否存在
						if (iWayBillService.getWayBillSignById(waybillEntity
								.getSignNo()) == 0) {
							iWayBillService.addWayBillSign(waybillEntity);
						} else {
							iWayBillService.updateWayBillSign(waybillEntity);
						}
					}
					successIdList.add(waybillEntity.getId());
				} else {
					failureIdList.add(waybillEntity.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
				failureIdList.add(waybillEntity.getId());
			}
		}
		// 返回值
		ResponseBaseEntity<WayBillVo> result = new ResponseBaseEntity<WayBillVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		WayBillVo resultVo = new WayBillVo();
		resultVo.setSuccessIdList(successIdList);
		resultVo.setFailureIdList(failureIdList);
		result.setResult(resultVo);
		return result;
	}

	@Override
	public ResponseBaseEntity<WayBillVo> queryWayBillLogByAccountId(WayBillVo waybillVo) {
		WayBillVo resultVo = new WayBillVo();
		// 判断信息是否为空
		if (StringUtils.isEmpty(waybillVo)||StringUtils.isEmpty(waybillVo.getAccountId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		Map<String,Object> waybilllog =  iWayBillService.queryWayBillLogByAccountId(waybillVo);
		//分割运单信息
		String [] waybilllogs = waybilllog.get("waybilllog").toString().split("\\r\\n");
		//结果集
		List<String> res = new ArrayList<String>();
		// 返回值
		ResponseBaseEntity<WayBillVo> result = new ResponseBaseEntity<WayBillVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		for (int i = 0; i < waybilllogs.length; i++) {
			res.add(waybilllogs[i]);
		}
		resultVo.setWayBillLogList(res);
		result.setResult(resultVo);
		return result;
	}
}
