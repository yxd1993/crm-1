/**
 * 
 */
package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.sales.api.shared.domain.SaleChatRandomEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleChatsVo;

/**
 *销售管理 客户洽谈DAO
 * @author 丁勇
 * @date 2015年6月9日
 */
@Repository
public interface SaleChatsMapper {
	/**
	 * 查询洽谈信息列表 (分页)
	 * @param map
	 * @param rb
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update 
	 */
	public List<SaleChatsEntity> getChatsInfoByPaging(Map<String, Object> map, RowBounds rb);
	
	/**
	 * 查询洽谈信息总条数
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update 
	 */
	public long chatsCount(Map<String, Object> map);
	/**
	 * 新增洽谈信息
	 * @param salechats
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	public void addSaleChat(SaleChatsEntity salechats);
	
	/**
	 * 修改洽谈信息
	 * @param salechats
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	public void updateSaleChats(SaleChatsEntity salechats);
	
	/**
	 * 按id查询需要修改的信息
	 * @param id
	 * @author 丁勇
	 * @return 
	 * @date 2015年6月9日
	 * @update 
	 */
	public SaleChatsEntity getChatsDetailByIdUseUpdate(String id);
	
	/**
	 * 查看洽谈详情
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年6月18日
	 * @update 
	 */
	public SaleChatsVo getChatsDetailById(String id);
	/**
	 * 删除洽谈
	 * @param map
	 * @author 丁勇
	 * @date 2015年6月15日
	 * @update 
	 */
	public void deleteChats(Map<String,Object> map);
	
	/**
	 * 判断是否部门负责人
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月30日
	 * @update 
	 */
	public long isDeptManeger(String empCode);
	
	/**
	 * 查询洽谈信息用于记录导出
	 * 
	 * @param map
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月14日
	 * @update 
	 */
	public List<SaleChatsVo> getChatsInfoToExport(Map<String, Object> map);
	
	/**
	 * 统计总数(导出)
	 * 
	 * @param customerInfoPoolVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月15日
	 * @update 
	 */
	long countChatsInfoToExport(Map<String,Object> params);
	
	/**
	 * 更改客户性质
	 * @param map
	 * @author 丁勇
	 * @date 2015年8月5日
	 * @update 
	 */
	public void updateCustomerType(Map<String,Object> map);
	
	/**
	 * 分页查询随机洽谈记录
	 * 
	 * @param deptCode
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public List<SaleChatRandomEntity> getSaleChatRandomInfos(Map<String, Object> map,RowBounds rb);
	
	/**
	 * 分页查询随机洽谈记录总数
	 * 
	 * @param deptCode
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public long countSaleChatRandomInfos(Map<String, Object> map,RowBounds rb);
	
	/**
	 * 更新抽查结果
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public void updateCheckResult(Map<String,Object> params);
	
	/**
	 * 按id查询洽谈回访抽取的信息
	 * @param id
	 * @return
	 * @author 丁勇
	 * @date 2015年8月18日
	 * @update 
	 */
	public SaleChatRandomEntity queryChatsRandomById(String id);
}
