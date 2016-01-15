package com.hoau.crm.module.sales.api.server.service;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.hoau.crm.module.sales.api.shared.domain.SaleContractEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleContractVo;

/**
 * 销售合同SERVICE
 * @author: 何斌
 * @create: 2015年6月11日 下午4:56:00
 */
public interface ISaleContractService {
	/**
	 * 查询合同信息
	 * 
	 * @param saleContractVo
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	List<SaleContractVo> getContractInfo(SaleContractVo saleContractVo,RowBounds rb);
	
	/**
	 * 统计销售合同总数
	 * 
	 * @param saleContractVo
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	long countContract(SaleContractVo saleContractVo,RowBounds rb);
	
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
	 * 新增销售合同详细信息
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
	void deleteContract(List<String> ids);
	
	
	/**
	 * 根据DC账号或者CRM账号判断销售合同是否存在
	 * 
	 * @param workflowCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	boolean isExistContract(SaleContractVo saleContractVo);
	
	/**
	 * 根据条件查询附件图片的URL
	 * 
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月11日
	 * @update 
	 */
	SaleContractVo getAttachmentUrl(String id);
	
	/**
	 * 获得所有附件URL
	 * @param params
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月12日
	 * @update 
	 */
	List<String> getAllAttachmentUrl(List<String> ids);
	
	/**
	 * 修改合同时,更新附件URL
	 * 
	 * @param crmAccont
	 * @param attachmentUrl
	 * @author: 何斌
	 * @date: 2015年6月14日
	 * @update 
	 */
	void updateAttachmentUrl(SaleContractVo saleContractVo);
	
	/**
	 * 展示上传合同信息
	 * 
	 * @param in
	 * @author: 何斌
	 * @date: 2015年6月17日
	 * @update 
	 */
	void showAttachmentName(InputStream in);
	
	/**
	 * 根据DC账号查询合同基本信息
	 * 
	 * @param dcAccount
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月10日
	 * @update 
	 */
	List<SaleContractVo> getSaleContractVoByDcAccount(SaleContractVo saleContractVo);
	
	/**
	 * 根据DC账号判断是否已存在附件
	 * 
	 * @param saleContractVo
	 * @author: 何斌
	 * @date: 2015年7月13日
	 * @update 
	 */
	void isExistAttachment(String dcAccount);
	
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
