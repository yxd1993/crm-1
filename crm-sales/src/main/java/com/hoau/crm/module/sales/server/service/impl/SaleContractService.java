package com.hoau.crm.module.sales.server.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.sales.api.server.service.ISaleContractService;
import com.hoau.crm.module.sales.api.shared.domain.SaleContractEntity;
import com.hoau.crm.module.sales.api.shared.exception.SaleContractException;
import com.hoau.crm.module.sales.api.shared.vo.SaleContractVo;
import com.hoau.crm.module.sales.server.dao.SaleContractMapper;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 销售合同SERVICE
 * 
 * @author: 何斌
 * @create: 2015年6月11日 下午7:53:45
 */
@Service
public class SaleContractService implements ISaleContractService {
	
	@Resource
	private SaleContractMapper saleContractMapper;
	
	@Resource
	private ICustomerService customerService;
	@Override
	public List<SaleContractVo> getContractInfo(SaleContractVo saleContractVo,
			RowBounds rb) {
		if(rb == null){
			throw new BusinessException(SaleContractException.QUERY_CONTRACT_RB_NULL);
		}
		if(saleContractVo == null){
			throw new BusinessException(SaleContractException.QUERY_CONTRACT_PARAM_NULL);
		}
		Map<String, String> params = new HashMap<String, String>();
		if(!StringUtil.isEmpty(saleContractVo.getStatus())){
			params.put("status", saleContractVo.getStatus());
		}
		if(!StringUtil.isEmpty(saleContractVo.getEnterpriseName())){
			params.put("enterpriseName", "%"+saleContractVo.getEnterpriseName()+"%");
		}
		if(!StringUtil.isEmpty(saleContractVo.getWorkflowCode())){
			params.put("workflowCode", "%"+saleContractVo.getWorkflowCode()+"%");
		}
		if(!StringUtil.isEmpty(saleContractVo.getCrmAccount())){
			params.put("crmAccount", "%"+saleContractVo.getCrmAccount()+"%");
		}
		if(!StringUtil.isEmpty(saleContractVo.getDcAccount())){
			params.put("dcAccount", "%"+saleContractVo.getDcAccount()+"%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if(saleContractVo.getFirstEndDate() != null){
			params.put("firstEndDate", sdf.format(BseConstants.getEndDate(saleContractVo.getFirstEndDate())));
		}
		if(saleContractVo.getFirstStartDate() != null){
			params.put("firstStartDate", sdf.format(saleContractVo.getFirstStartDate()));
		}
		if(saleContractVo.getSecondEndDate() != null ){
			params.put("secondEndDate", sdf.format(BseConstants.getEndDate(saleContractVo.getSecondEndDate())));
		}
		if(saleContractVo.getSecondStartDate() != null){
			params.put("secondStartDate", sdf.format(saleContractVo.getSecondStartDate()));
		}
		//当前用户
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		//当前用户组织
		Set<String> functionCodes  = currentUser.getFunctionCodes();
		if(!functionCodes.contains(BseConstants.ALLDATA_CONTRACT)){
			if(!StringUtil.isEmpty(currentUser.getUserName())){
				params.put("userName", currentUser.getUserName());
			}
			if(currentUser.getEmpEntity().getDeptEntity()!=null && !StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
				params.put("userDeptcode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			}
		}
		return saleContractMapper.getContractInfo(params, rb);
	}

	@Override
	public long countContract(SaleContractVo saleContractVo, RowBounds rb) {
		if(rb == null){
			throw new BusinessException(SaleContractException.QUERY_CONTRACT_RB_NULL);
		}
		if(saleContractVo == null){
			throw new BusinessException(SaleContractException.QUERY_CONTRACT_PARAM_NULL);
		}
		Map<String, String> params = new HashMap<String, String>();
		if(!StringUtil.isEmpty(saleContractVo.getStatus())){
			params.put("status", saleContractVo.getStatus());
		}
		if(!StringUtil.isEmpty(saleContractVo.getEnterpriseName())){
			params.put("enterpriseName", "%"+saleContractVo.getEnterpriseName()+"%");
		}
		if(!StringUtil.isEmpty(saleContractVo.getWorkflowCode())){
			params.put("workflowCode", "%"+saleContractVo.getWorkflowCode()+"%");
		}
		if(!StringUtil.isEmpty(saleContractVo.getCrmAccount())){
			params.put("crmAccount", "%"+saleContractVo.getCrmAccount()+"%");
		}
		if(!StringUtil.isEmpty(saleContractVo.getDcAccount())){
			params.put("dcAccount", "%"+saleContractVo.getDcAccount()+"%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if(saleContractVo.getFirstEndDate() != null && saleContractVo.getFirstStartDate() != null){
			params.put("firstStartDate", sdf.format(saleContractVo.getFirstStartDate()));
			params.put("firstEndDate", sdf.format(BseConstants.getEndDate(saleContractVo.getFirstEndDate())));
		}
		if(saleContractVo.getSecondEndDate() != null && saleContractVo.getSecondStartDate() != null){
			params.put("secondStartDate", sdf.format(saleContractVo.getSecondStartDate()));
			params.put("secondEndDate", sdf.format(BseConstants.getEndDate(saleContractVo.getSecondEndDate())));
		}
		//当前用户
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		//当前用户组织
		Set<String> functionCodes  = currentUser.getFunctionCodes();
		if(!functionCodes.contains(BseConstants.ALLDATA_CONTRACT)){
			if(!StringUtil.isEmpty(currentUser.getUserName())){
				params.put("userName", currentUser.getUserName());
			}
			if(currentUser.getEmpEntity().getDeptEntity()!=null && !StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
				params.put("userDeptcode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
			}
		}
		return saleContractMapper.countContract(params, rb);
	}

	@Override
	public SaleContractEntity getContractInfoById(String id) {
		if(StringUtil.isEmpty(id)){
			throw new BusinessException(SaleContractException.QUERY_CONTRACT_PARAM_NULL);
		}
		return saleContractMapper.getContractInfoById(id);
	}

	@Override
	@Transactional
	public void addContractBaseInfo(SaleContractVo saleContractVo) {
		if(saleContractVo == null){
			throw new BusinessException(SaleContractException.ADD_CONTRACT_PARAM_NULL);
		}
		saleContractMapper.addContractBaseInfo(saleContractVo);
	}

	@Override
	@Transactional
	public void updateContractDetailInfo(SaleContractEntity saleContractEntity) {
		if(saleContractEntity == null){
			throw new BusinessException(SaleContractException.ADD_CONTRACT_PARAM_NULL);
		}
		saleContractEntity.setModifyUser(BseConstants.CONTRACT_OA);
		saleContractMapper.updateContractDetailInfo(saleContractEntity);
	}

	@Override
	@Transactional
	public void deleteContract(List<String> ids) {
		if(ids == null){
			throw new BusinessException(SaleContractException.DELETE_CONTRACT_ID_NULL);
		}	
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids", ids);
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		params.put("username", currentUser.getUserName());
		saleContractMapper.deleteContract(params);
	}

	@Override
	public boolean isExistContract(SaleContractVo saleContractVo) {
		if(saleContractVo == null){
			throw new BusinessException(SaleContractException.QUERY_CONTRACT_PARAM_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		params.put("dcAccount", saleContractVo.getDcAccount());
		params.put("status", saleContractVo.getStatus());
		long count = saleContractMapper.isExistContract(params);
		if( count > 0){
			return true;
		}
		return false;
	}

	@Override
	public SaleContractVo getAttachmentUrl(String id) {
		if(StringUtil.isEmpty(id)){
			throw new BusinessException(SaleContractException.QUERY_CONTRACT_ID_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		params.put("id", id);
		return saleContractMapper.getAttachmentUrl(params);
	}

	@Override
	public List<String> getAllAttachmentUrl(List<String> ids) {
		if(ids == null){
			throw new SaleContractException(SaleContractException.QUERY_CONTRACT_PARAM_NULL);
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ids", ids);
		return saleContractMapper.getAllAttachmentUrl(params);
	}

	@Override
	@Transactional
	public void updateAttachmentUrl(SaleContractVo saleContractVo) {
		if(saleContractVo == null){
			throw new SaleContractException(SaleContractException.UPDATE_CONTRACT_PARAM_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		if(!StringUtil.isEmpty(saleContractVo.getCrmAccount())){
			params.put("crmAccount", saleContractVo.getCrmAccount());
		}
		if(!StringUtil.isEmpty(saleContractVo.getUrl())){
			params.put("url", saleContractVo.getUrl());
		}
		if(!StringUtil.isEmpty(saleContractVo.getStatus())){
			params.put("status", saleContractVo.getStatus());
		}
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		if(!StringUtil.isEmpty(currentUser.getUserName())){
			params.put("modifyUser", currentUser.getUserName());
		}
		saleContractMapper.updateAttachmentUrl(params);
	}

	@Override
	public void showAttachmentName(InputStream in) {
		//response
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = null;
		//文件大小
		try {
			int i = in.available();
			byte[] buffer = new byte[i];
			in.read(buffer);
			//设置返回的类型
			response.setContentType("application/zip");
			os = response.getOutputStream();
			os.write(buffer);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<SaleContractVo> getSaleContractVoByDcAccount(SaleContractVo saleContractVo) {
		if(saleContractVo == null ||StringUtil.isEmpty(saleContractVo.getDcAccount())){
			throw new SaleContractException(SaleContractException.QUERY_CONTRACT_PARAM_NULL);
		}
		Map<String,String> params = new HashMap<String,String>();
		params.put("dcAccount", saleContractVo.getDcAccount());
		params.put("status", saleContractVo.getStatus());
		List<SaleContractVo> sVos = saleContractMapper.getSaleContractVoByDcAccount(params);
		if(sVos == null){
			throw new SaleContractException(SaleContractException.CONTRACT_NULL);
		}
		return sVos;
	}

	@Override
	public void isExistAttachment(String dcAccount) {
		if(StringUtil.isEmpty(dcAccount)){
			throw new SaleContractException(SaleContractException.QUERY_CONTRACT_PARAM_NULL);
		}
		SaleContractVo saleContractVo = new SaleContractVo();
		saleContractVo.setDcAccount(dcAccount);
		saleContractVo.setStatus(BseConstants.CONTRACT_STATUS_OUTOFTIME);
		if(this.isExistContract(saleContractVo)){
			//若存在,查询客户状态
			saleContractVo.setStatus(null);
			List<SaleContractVo> sVos = this.getSaleContractVoByDcAccount(saleContractVo);
			for(SaleContractVo sVo :sVos){
				if(sVo != null && (!BseConstants.CONTRACT_STATUS_DELETE.equals(sVo.getStatus()) 
						|| !BseConstants.CONTRACT_STATUS_OUTOFTIME.equals(sVo.getStatus()))){
					//受理中
					if(BseConstants.CONTRACT_STATUS_REVIEW.equals(sVo.getStatus()) ){
						throw new SaleContractException(SaleContractException.ISEXIST_CONTRACT_YES_REVIEW);
					}else if(BseConstants.CONTRACT_STATUS_UPLOADED.endsWith(sVo.getStatus())){
						//已上传
						throw new SaleContractException(SaleContractException.ISEXIST_CONTRACT_YES_UPLOADED);
					}else if(BseConstants.CONTRACT_STATUS_FILE.equals(sVo.getStatus())){
						//已归档
						throw new SaleContractException(SaleContractException.ISEXIST_CONTRACT_YES_FILE);
					}
				}
			}
		}else{
			//上传的时候,判断该客户是不是自己的客户
			CustomerEntity customerEntity = customerService.queryCustomerInfoByDcAcconut(dcAccount);
			if(customerEntity  == null){
				throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
			}
			//当前用户
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			if(currentUser.getEmpEntity() != null){
				if(!currentUser.getEmpEntity().getEmpCode().equals(customerEntity.getManageEmpCode())){
					throw new SaleContractException(SaleContractException.CONTRACT_ADD_NOT_MY);
				}
			}
		}
	}

	@Override
	@Transactional
	public void updateFileDate(String workflowCode) {
		saleContractMapper.updateFileDate(workflowCode);
	}

	@Override
	@Transactional
	public void deleteByWorkflowCode(String workflowCode) {
		saleContractMapper.deleteByWorkflowCode(workflowCode);
	}
}
