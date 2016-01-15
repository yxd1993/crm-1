package com.hoau.crm.module.bse.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.WayBillEntity;

/**
 * 运单信息
 * 
 * @author 蒋落琛
 * @date 2015-7-6
 */
@Repository
public interface WayBillMapper {

	/**
	 * 新增运单信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	void addWayBill(WayBillEntity wayBillInfo);
	
	/**
	 * 查询未生成消息的运单
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	List<WayBillEntity> getNotCreateMessageWayBill(RowBounds rb);

	/**
	 * 查询未生成消息的签收
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	List<WayBillEntity> getNotCreateMessageSign(RowBounds rb);
	
	/**
	 * 修改运单信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	void updateWayBill(WayBillEntity wayBillInfo);
	
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
	 * 修改运单签收信息,将是否生成消息置成'Y'
	 * 
	 * @param ids
	 * @author 蒋落琛
	 * @date 2015-7-27
	 * @update
	 */
	void updateSignIsMessage(Map<String, Object> map);
	
	/**
	 * 删除运单信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-16
	 * @update
	 */
	void deleteWayBill(WayBillEntity wayBillInfo);
	
	/**
	 * 修改运单金额
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	void updateWayBillAmount(WayBillEntity wayBillInfo);

	/**
	 * 根据运单号查询运单信息
	 * 
	 * @param wayBillInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	long getWayBillByBillNum(String wId);

	/**
	 * 新增运单异常上报信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//void addWayBillAbnormal(WayBillEntity wayBillInfo);

	/**
	 * 修改运单异常上报信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//void updateWayBillAbnormal(WayBillEntity wayBillInfo);

	/**
	 * 根据ID查询运单异常上报信息
	 * 
	 * @param wayBillInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//WayBillEntity getWayBillAbnormalById(WayBillEntity wayBillInfo);

	/**
	 * 新增运单理赔信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//void addWayBillCompensation(WayBillEntity wayBillInfo);

	/**
	 * 修改运单理赔信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//void updateWayBillCompensation(WayBillEntity wayBillInfo);

	/**
	 * 根据ID查询理赔信息
	 * 
	 * @param wayBillInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//WayBillEntity getWayBillCompensationById(WayBillEntity wayBillInfo);

	/**
	 * 新增运单投诉信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//void addWayBillComplaint(WayBillEntity wayBillInfo);

	/**
	 * 修改运单投诉信息
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//void updateWayBillComplaint(WayBillEntity wayBillInfo);

	/**
	 * 根据ID查询投诉信息
	 * 
	 * @param wayBillInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	//WayBillEntity getWayBillComplaintById(WayBillEntity wayBillInfo);

	/**
	 * 新增运单签收状态
	 * 
	 * @param wayBillInfo
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	void addWayBillSign(WayBillEntity wayBillInfo);

	/**
	 * 根据ID查询签收信息
	 * 
	 * @param wayBillInfo
	 * @return
	 * @author 蒋落琛
	 * @date 2015-7-6
	 * @update
	 */
	long getWayBillSignById(String signNo);
	
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
	 * 按客户id查询该客户的运单日志
	 * @param accountId
	 * @return
	 * @author 丁勇
	 * @date 2015年9月9日
	 * @update 
	 */
	public Map<String,Object> queryWayBillLogByAccountId(String accountId);
}
