package com.hoau.crm.module.bse.api.server.service;

import java.util.Map;

import com.hoau.crm.module.bse.api.shared.domain.WayBillEntity;
import com.hoau.crm.module.bse.api.shared.vo.WayBillVo;

/**
 * 运单模块SERVICE
 * 
 * @author 蒋落琛
 * @date 2015-7-6
 */
public interface IWayBillService {
	
	/**
	 * 新增运单信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public void addWayBill(WayBillEntity wayBillInfo);

	/**
	 * 修改运单信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public void updateWayBill(WayBillEntity wayBillInfo);
	
	/**
	 * 修改运单信息,将是否生成消息置成'Y'
	 * 
	 * @param ids
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	void updateWayBillIsMessage(Map<String, Object> map);
	
	/**
	 * 删除运单信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-16
	 * @update
	 */
	public void deleteWayBill(WayBillEntity wayBillInfo);

	/**
	 * 修改运单金额
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public void updateWayBillAmount(WayBillEntity wayBillInfo);

	/**
	 * 根据ID查询运单信息
	 * 
	 * @param wayBillInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public long getWayBillByBillNum(String wId);

	/**
	 * 新增运单签收状态
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public void addWayBillSign(WayBillEntity wayBillInfo);
	
	/**
	 * 修改运单签收状态
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public void updateWayBillSign(WayBillEntity wayBillInfo);

	/**
	 * 根据ID查询签收信息
	 * 
	 * @param wayBillInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	public long getWayBillSignById(String signNo);
	
	/**
	 * 新增开单日志
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	public void addWayBillLog(Map<String, String> map);
	
	/**
	 * 修改开单日志
	 * 
	 * @param map
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	public void updateWayBillLog(Map<String, String> map);
	
	/**
	 * 根据DC账号查询开单日志
	 * 
	 * @param dcAccount
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	public long getWayBillLogByDcAccount(String dcAccount);
	
	/**
	 * 按客户id查询运单日志
	 * @param waybillVo
	 * @return
	 * @author 丁勇
	 * @date 2015年9月9日
	 * @update 
	 */
	public Map<String,Object> queryWayBillLogByAccountId(WayBillVo waybillVo);
}
