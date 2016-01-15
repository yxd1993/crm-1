package com.hoau.crm.module.sales.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.sales.api.server.service.IContractAttachmentService;
import com.hoau.crm.module.sales.api.server.service.ISaleContractService;
import com.hoau.crm.module.sales.api.shared.domain.ApprovalEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleContractEntity;
import com.hoau.crm.module.sales.api.shared.vo.ImportParamsVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleContractVo;
import com.hoau.crm.module.sales.server.service.impl.ContracSyncService;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 销售合同ACTION
 * @author: 何斌
 * @create: 2015年6月12日 下午3:48:51
 */
@Controller
@Scope("prototype")
public class SaleContractAction extends AbstractAction{

	private static final long serialVersionUID = -5537467279059253849L;
	
	private SaleContractVo saleContractVo;
	
	private List<SaleContractVo> saleContractList;
	
	private List<String> ids;
	
	/**
	 * 合同
	 */
	private SaleContractEntity saleContractEntity;
	
	/**
	 * 审批状态
	 */
	private ApprovalEntity approvalEntity;
	
	private String attachmentName;
	
	/**
	 * 上传参数
	 */
	private ImportParamsVo importParamsVo;
	
	@Resource
	private ISaleContractService saleContractService;
	
	@Resource
	private ContracSyncService contracSyncService;
	
	@Resource
	private IContractAttachmentService contractAttachmentService;
	
	/**
	 * 进入合同信息主界面
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月16日
	 * @update 
	 */
	public String main(){
		return returnSuccess();
	}
	
	/**
	 * 
	 * 分页查询合同信息
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月16日
	 * @update 
	 */
	public String querySaleContractInfo(){
		try {
			RowBounds rb = new RowBounds(this.start,this.limit);
			saleContractList = saleContractService.getContractInfo(saleContractVo, rb);
			this.setTotalCount(saleContractService.countContract(saleContractVo, rb));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 删除销售合同
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月16日
	 * @update 
	 */
	public String deleteContract(){
		try {
			saleContractService.deleteContract(ids);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 进入查看销售合同详情界面
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月16日
	 * @update 
	 */
	public String lookContract(){
		return returnSuccess();
	}

	/**
	 * 查看销售合同详细信息
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月16日
	 * @update 
	 */
	public String querySaleContractInfoById(){
		try {
			saleContractEntity = saleContractService.getContractInfoById(saleContractEntity.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 查询审批状态
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月16日
	 * @update 
	 */
	public String queryApprovalInfoById(){
		try {
			approvalEntity = contracSyncService.queryApprovalInfoById(saleContractEntity.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 展示上传合同信息
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月17日
	 * @update 
	 */
	public String showAttachment(){
		try {
			saleContractVo = saleContractService.getAttachmentUrl(saleContractEntity.getId());
		    if(!StringUtil.isEmpty(saleContractVo.getUrl())){
		    	attachmentName = saleContractVo.getUrl().substring(saleContractVo.getUrl().lastIndexOf("/")+1);
		    }
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 根据DC账号判断是否已存在附件
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月10日
	 * @update 
	 */
	public String isExistAttachment(){
		try {
			saleContractService.isExistAttachment(importParamsVo.getDcAccount());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public SaleContractVo getSaleContractVo() {
		return saleContractVo;
	}

	public void setSaleContractVo(SaleContractVo saleContractVo) {
		this.saleContractVo = saleContractVo;
	}

	public List<SaleContractVo> getSaleContractList() {
		return saleContractList;
	}

	public void setSaleContractList(List<SaleContractVo> saleContractList) {
		this.saleContractList = saleContractList;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public SaleContractEntity getSaleContractEntity() {
		return saleContractEntity;
	}

	public void setSaleContractEntity(SaleContractEntity saleContractEntity) {
		this.saleContractEntity = saleContractEntity;
	}

	public ApprovalEntity getApprovalEntity() {
		return approvalEntity;
	}

	public void setApprovalEntity(ApprovalEntity approvalEntity) {
		this.approvalEntity = approvalEntity;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public ImportParamsVo getImportParamsVo() {
		return importParamsVo;
	}

	public void setImportParamsVo(ImportParamsVo importParamsVo) {
		this.importParamsVo = importParamsVo;
	}
	
}
