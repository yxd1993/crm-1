package com.hoau.crm.module.appcore.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.server.service.IContractAppService;
import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.ContractAppVo;
import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.sales.api.server.service.ISaleContractService;
import com.hoau.crm.module.sales.api.shared.exception.SaleContractException;
import com.hoau.crm.module.sales.api.shared.vo.SaleContractVo;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 销售合同模块RESTFUL接口实现
 * 
 * @author: 何斌
 * @create: 2015年6月25日 下午5:29:10
 */
@SuppressWarnings("rawtypes")
@Service
public class ContractAppService implements IContractAppService{
	
	@Resource
	ISaleContractService saleContractService;
	
	@Resource
	ICustomerService customerService;
	
	@Resource
	IEmployeeService employeeService;
	
	@Resource
	IMessageInfoService messageInfoService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ResponseBaseEntity<ContractAppVo> updateContractInfo(
			ContractAppVo contractAppVo) {
		ResponseBaseEntity responseBaseEntity = new ResponseBaseEntity();
		try {
			if(contractAppVo == null 
					|| StringUtil.isEmpty(contractAppVo.getSaleContractEntity().getStatus())
					|| StringUtil.isEmpty(contractAppVo.getSaleContractEntity().getDcAccount())){
				throw new SaleContractException(SaleContractException.UPDATE_CONTRACT_PARAM_NULL);
			}
			//根据DC账号查询客户是否存在
			CustomerEntity customerEntity = customerService.queryCustomerInfoByDcAcconut(contractAppVo.getSaleContractEntity().getDcAccount());
			if(customerEntity == null){
				throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
			}
			//再判断是否已经存在除已过期的以外合同基本信息,OA推送数据时,CRM没有相关附件
			/*SaleContractVo saleContractVo = new SaleContractVo();
			saleContractVo.setStatus(BseConstants.CONTRACT_STATUS_OUTOFTIME);
			saleContractVo.setDcAccount(contractAppVo.getSaleContractEntity().getDcAccount());
			!saleContractService.isExistContract(saleContractVo) */
			EmployeeEntity emp = new EmployeeEntity();
			if(BseConstants.CONTRACT_STATUS_REVIEW.equals(contractAppVo.getSaleContractEntity().getStatus())){
				//清除上次已提交的数据,防止提交退回提交
				saleContractService.deleteByWorkflowCode(contractAppVo.getSaleContractEntity().getWorkflowCode());
				
				SaleContractVo saleContractVoNew = new SaleContractVo();
				saleContractVoNew.setCrmAccount(customerEntity.getAccountCode());
				saleContractVoNew.setDcAccount(customerEntity.getDcAccount());
				saleContractVoNew.setEnterpriseName(customerEntity.getEnterpriseName());
				saleContractVoNew.setStatus(BseConstants.CONTRACT_STATUS_UPLOADED);
				saleContractVoNew.setWorkflowCode(contractAppVo.getSaleContractEntity().getWorkflowCode());
				
				//创建人
				if(StringUtil.isEmpty(customerEntity.getManageEmpCode())){
					//负责人为空,该客户合同归属门店负责人
					String tierManager = customerService
							.queryTierManagerIdByName(contractAppVo.getSaleContractEntity().getApplyUserDeclareName().substring(1));
					emp.setEmpCode(tierManager);
				}else{
					emp.setEmpCode(customerEntity.getManageEmpCode());
				}
				emp = employeeService.queryEmployeeByEmpcode(emp);
				saleContractVoNew.setCreateUser(emp.getAccount());
				saleContractService.addContractBaseInfo(saleContractVoNew);
			}
			//替换申请说明中的HTML标签
            String subStr = contractAppVo.getSaleContractEntity().getApplyInstruction().replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "");
            contractAppVo.getSaleContractEntity().setApplyInstruction(subStr);
			saleContractService.updateContractDetailInfo(contractAppVo.getSaleContractEntity());
			//归档时,更新客户表里面的客户账期,客户性质(放入JOB中)
			if(BseConstants.CONTRACT_STATUS_FILE.equals(contractAppVo.getSaleContractEntity().getStatus())){
				//修改人
				if(StringUtil.isEmpty(customerEntity.getManageEmpCode())){
					//负责人为空,该客户合同归属门店负责人
					String tierManager = customerService.
							queryTierManagerIdByName(contractAppVo.getSaleContractEntity().getApplyUserDeclareName().substring(1));  
					customerEntity.setModifyUser(tierManager.substring(2));
				}else{
					customerEntity.setModifyUser(customerEntity.getCreateUser());
				}
				//客户账期
				customerEntity.setAccountPeriod(contractAppVo.getSaleContractEntity().getPaymentMethod()+" "+
						contractAppVo.getSaleContractEntity().getAccountDescribe());
				//合同开始时间,合同结束时间
				customerEntity.setContractStartTime(contractAppVo.getSaleContractEntity().getContractStartDate());
				customerEntity.setContractEndTime(contractAppVo.getSaleContractEntity().getContractEndDate());
				customerService.updateCustomerInfoInfoById(customerEntity);
				
				//DC账号负责人不为空,推送消息
				if(!StringUtil.isEmpty(customerEntity.getManageEmpCode())){
					//生成消息提醒
					MessageInfoEntity messageEntity = new MessageInfoEntity();
					//消息类型
					messageEntity.setMessageType(BseConstants.MESSAGE_TYPE_XSHTL);
					//接收人
					messageEntity.setReceiveUserId(customerEntity.getManageEmpCode());
					//工作流水号
					messageEntity.setCondition(contractAppVo.getSaleContractEntity().getWorkflowCode());
					//消息内容
					String messageContent = "您的客户："+contractAppVo.getSaleContractEntity().getEnterpriseName()
							+"的销售合同已经归档,您可以到合同列表查看客户合同详情。";
					messageEntity.setMessageContent(messageContent);
					messageInfoService.addMessage(messageEntity, null);
				}
				//更新归档时间
				saleContractService.updateFileDate(contractAppVo.getSaleContractEntity().getWorkflowCode());
			}
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseBaseEntity.setErrorCode(AppUtil.EXCEPTION_STATUS_SYSTEM_ERROR);
		}
		return responseBaseEntity;
	}
	 
}
