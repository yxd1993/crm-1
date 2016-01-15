/**
 * 
 */
package com.hoau.crm.module.bse.api.server.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.vo.ReviewHistoryVo;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;

/**
 *
 * @author 丁勇
 * @date 2015年6月24日
 */
public interface IReviewHistoryService {
	/**
	 * 保存操作记录
	 * @param maps
	 * @return
	 * @author 丁勇
	 * @date 2015年6月25日
	 * @update 
	 */
	public void insertReviewOrHistory( ReviewHistoryEntity reviewhistoryEntity);
	
	/**
	 * app端 分页查询历史跟进信息
	 * @param accountId
	 * @return
	 * @author 丁勇
	 * @date 2015年6月29日
	 * @update 
	 */
	public List<ReviewHistoryVo> queryHistoryList(Map<String, Object> maps,RowBounds rb);
	
	/**
	 * web端 分页查询历史跟进信息
	 * @param accountId
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年9月8日
	 * @update 
	 */
	public List<Map<String,Object>> queryHistoryListByWeb(CustomerEntity customerEntity,RowBounds rb);
	/**
	 * app端 历史跟进操作总数
	 * 
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月14日
	 * @update 
	 */
	public long queryHistoryCount(Map<String,Object> map);
	/**
	 * app端 分页查询工作回顾信息
	 * @param maps
	 * @return
	 * @author 丁勇
	 * @date 2015年6月30日
	 * @update 
	 */
	public List<ReviewHistoryVo> queryReviewList(Map<String, Object> maps,RowBounds rb);
	/**
	 *  工作回顾信息总数
	 * 
	 * 
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月14日
	 * @update 
	 */
	public long queryReviewCount(Map<String,Object> map);
}
