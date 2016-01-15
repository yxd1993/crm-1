/**
 * 
 */
package com.hoau.crm.module.sales.api.server.service;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatRandomEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleChatRandomVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleChatsVo;

/**
 *
 * @author 丁勇
 * @date 2015年6月9日
 */
public interface ISaleChatsService {
	/**
	 * 
	 * @param chatsVo
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update 
	 */
	public List<SaleChatsEntity>  getChatsInfoByPaging(SaleChatsVo chatsVo,RowBounds rb);
	/**
	 * 查询洽谈信息条数
	 * @param chatsVo
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update 
	 */
	public long chatsCount(SaleChatsVo chatsVo);
	/**
	 * 新增
	 * @param salechats
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update 
	 */
	public void saveOrUpdateSaleChat(SaleChatsEntity salechats,UserEntity user);
	
	/**
	 * 按id查询需要修改的信息
	 * @param id
	 * @author 丁勇
	 * @date 2015年6月9日
	 * @update 
	 */
	public SaleChatsEntity getChatsDetailByIdUseUpdate(SaleChatsEntity chats);
	
	/**
	 * 查看详情
	 * @param chats
	 * @return
	 * @author 丁勇
	 * @date 2015年6月18日
	 * @update 
	 */
	public SaleChatsVo getChatsDetailById(SaleChatsEntity chats);
	/**
	 * 删除洽谈
	 * @param map
	 * @author 丁勇
	 * @date 2015年6月16日
	 * @update 
	 */
	public void delChats(List<String> ids,String delDesc);
	
	/**
	 * 判断是否部门负责人
	 * 
	 * @param empCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月30日
	 * @update 
	 */
	public boolean isDeptManeger(String empCode);
	
	/**
	 * 洽谈记录导出
	 * 
	 * @param saleChatsVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月14日
	 * @update 
	 */
	InputStream exportChatsInfo(SaleChatsVo saleChatsVo);
	
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
	public List<SaleChatRandomEntity> getSaleChatRandomInfos(SaleChatRandomVo saleChatRandomVo,RowBounds rb);
	
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
	public long countSaleChatRandomInfos(SaleChatRandomVo saleChatRandomVo,RowBounds rb);
	
	/**
	 * 更新抽查结果
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public void updateCheckResult(SaleChatRandomVo saleChatRandomVo);
	
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
