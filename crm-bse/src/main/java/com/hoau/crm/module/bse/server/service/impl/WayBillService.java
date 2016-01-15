package com.hoau.crm.module.bse.server.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.service.IWayBillService;
import com.hoau.crm.module.bse.api.shared.domain.WayBillEntity;
import com.hoau.crm.module.bse.api.shared.vo.WayBillVo;
import com.hoau.crm.module.bse.server.dao.WayBillMapper;

/**
 * 消息SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-6-29
 */
@Service
public class WayBillService implements IWayBillService {

	@Resource
	private WayBillMapper wayBillMapper;

	@Override
	public void addWayBill(WayBillEntity wayBillInfo) {
		wayBillMapper.addWayBill(wayBillInfo);
	}
	
	@Override
	public void updateWayBill(WayBillEntity wayBillInfo) {
		wayBillMapper.updateWayBill(wayBillInfo);
	}
	
	@Override
	public void updateWayBillIsMessage(Map<String, Object> map){
		wayBillMapper.updateWayBillIsMessage(map);
	}
	
	@Override
	public void deleteWayBill(WayBillEntity wayBillInfo) {
		wayBillMapper.deleteWayBill(wayBillInfo);
	}

	@Override
	public void updateWayBillAmount(WayBillEntity wayBillInfo) {
		wayBillMapper.updateWayBillAmount(wayBillInfo);
	}

	@Override
	public long getWayBillByBillNum(String wId) {
		return wayBillMapper.getWayBillByBillNum(wId);
	}

	@Override
	public void addWayBillSign(WayBillEntity wayBillInfo) {
		wayBillMapper.addWayBillSign(wayBillInfo);
	}

	@Override
	public long getWayBillSignById(String signNo) {
		return wayBillMapper.getWayBillSignById(signNo);
	}

	@Override
	public void updateWayBillSign(WayBillEntity wayBillInfo) {
		wayBillMapper.updateWayBillSign(wayBillInfo);
	}

	@Override
	public void addWayBillLog(Map<String, String> map) {
		wayBillMapper.addWayBillLog(map);
	}
	
	@Override
	public void updateWayBillLog(Map<String, String> map){
		wayBillMapper.updateWayBillLog(map);
	}
	
	@Override
	public long getWayBillLogByDcAccount(String dcAccount) {
		return wayBillMapper.getWayBillLogByDcAccount(dcAccount);
	}

	@Override
	public Map<String, Object> queryWayBillLogByAccountId(WayBillVo waybillVo) {
		return wayBillMapper.queryWayBillLogByAccountId(waybillVo.getAccountId());
	}
}
