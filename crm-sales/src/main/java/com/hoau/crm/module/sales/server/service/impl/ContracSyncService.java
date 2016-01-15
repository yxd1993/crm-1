package com.hoau.crm.module.sales.server.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.sales.api.server.service.ISaleContractService;
import com.hoau.crm.module.sales.api.shared.domain.ApprovalEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleContractEntity;
import com.hoau.crm.module.sales.api.shared.exception.SaleContractException;
import com.hoau.crm.module.sales.server.si.oa.OaSaleService;
import com.hoau.crm.module.sales.server.si.oa.OaSaleServicePortType;
import com.hoau.crm.module.sales.shared.domain.OaSalBean;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.JsonUtils;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * @author: 何斌
 * @create: 2015年6月16日 下午6:54:53
 */
@Service
public class ContracSyncService {

	private final String RESULT = "resulterror";
	@Resource
	private ISaleContractService saleContractService;
	
	/**
	 * 查看工作流当前审批信息
	 * 
	 * @param id
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月1日
	 * @update 
	 */
	public ApprovalEntity queryApprovalInfoById(String id){
		ApprovalEntity approvalEntity = new ApprovalEntity();
		if(StringUtil.isEmpty(id)){
			throw new SaleContractException(SaleContractException.QUERY_CONTRACT_ID_NULL);
		}
		//根据id查流水号
		SaleContractEntity saleContractEntity = saleContractService.getContractInfoById(id);
		if(saleContractEntity == null || StringUtil.isEmpty(saleContractEntity.getWorkflowCode()) ){
			throw new SaleContractException(SaleContractException.CONTRACT_NULL);
		}
		try {
			OaSaleService service 
				= new OaSaleService(new URL(ConfigFileLoadUtil
						.getPropertiesForClasspath("config.properties")
						.getProperty("oa.workflow.url")));
			OaSaleServicePortType port = service.getOaSaleServiceHttpPort();
			String response = port.getSalInfo(saleContractEntity.getWorkflowCode());
			//如果返回null,说明工作流被删除,更新合同状态
			if(RESULT.equals(response)){
				saleContractEntity.setStatus(BseConstants.CONTRACT_STATUS_DELETE);
				saleContractEntity.setModifyUser(BseConstants.CONTRACT_OA);
				saleContractService.updateContractDetailInfo(saleContractEntity);
				throw new SaleContractException(SaleContractException.CONTRACT_DELETED);
			}else{
				OaSalBean oaSalBean = JsonUtils.toList(response, OaSalBean.class).get(0);
				approvalEntity.setContractId(saleContractEntity.getWorkflowCode());
				approvalEntity.setApprovalDept(oaSalBean.getDepartmentname());
				approvalEntity.setApprovalJob(oaSalBean.getJobtitlename());
				approvalEntity.setApprovalEmpCode(oaSalBean.getWorkcode());
				approvalEntity.setApprovalEmpName(oaSalBean.getLastname());
				approvalEntity.setApprovalTelephone(oaSalBean.getMobile());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return approvalEntity;
	}
}
