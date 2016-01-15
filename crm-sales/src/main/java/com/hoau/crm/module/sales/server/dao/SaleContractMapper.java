package com.hoau.crm.module.sales.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.sales.api.shared.domain.SaleContractEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleContractVo;

/**
 * 销售合同DAO
 * @author: 何斌
 * @create: 2015年6月11日 下午4:56:46
 */
@Repository
public interface SaleContractMapper {
	/**
	 * 查询合同信息
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	List<SaleContractVo> getContractInfo(Map<String,String> params,RowBounds rb);
	
	/**
	 * 统计销售合同总数
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	long countContract(Map<String,String> params,RowBounds rb);
	
	/**
	 * 根据主键查询合同信息
	 * 
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	SaleContractEntity getContractInfoById(String id);
	
	/**
	 * 新增销售合同基本信息
	 * 
	 * @param saleContractVo
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	void addContractBaseInfo(SaleContractVo saleContractVo);
	
	/**
	 * 更新销售合同详细信息
	 * 
	 * @param saleContractEntity
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	void updateContractDetailInfo(SaleContractEntity saleContractEntity);
	
	
	/**
	 * 删除销售合同
	 * 
	 * @param params
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	void deleteContract(Map<String,Object> params);
	
	
	/**
	 * 根据DC账号或者CRM账号判断销售合同是否存在
	 * 
	 * @param workflowCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	long isExistContract(Map<String,String> params);
	
	/**
	 * 根据条件查询附件URL
	 * 
	 * @param ids
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	SaleContractVo getAttachmentUrl(Map<String,String> params);
	
	/**
	 * 获得所有附件URL
	 * @param params
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	List<String> getAllAttachmentUrl(Map<String,Object> params);
	
	/**
	 * 修改合同时,更新附件的URL
	 * 
	 * @param crmAccount
	 * @author: 何斌
	 * @date: 2015年6月14日
	 * @update 
	 */
	void updateAttachmentUrl(Map<String,String> params);
	
	/**
	 * 根据DC账号查询合同基本信息
	 * 
	 * @param dcAccount
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月10日
	 * @update 
	 */
	List<SaleContractVo> getSaleContractVoByDcAccount(Map<String,String> params);
	
	/**
	 * 更新归档时间
	 * 
	 * @param workflowCode
	 * @author: 何斌
	 * @date: 2015年8月6日
	 * @update 
	 */
	void updateFileDate(String workflowCode);
	
	/**
	 * 根据流水号删除记录
	 * 
	 * @param workflowCode
	 * @author: 何斌
	 * @date: 2015年10月8日
	 * @update 
	 */
	void deleteByWorkflowCode(String workflowCode);
}
