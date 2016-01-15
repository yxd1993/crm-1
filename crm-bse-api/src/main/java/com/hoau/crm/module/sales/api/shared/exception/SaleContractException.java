package com.hoau.crm.module.sales.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 销售合同Exception
 * 
 * @author: 何斌
 * @create: 2015年6月11日 下午8:24:45
 */
public class SaleContractException extends BusinessException {

	private static final long serialVersionUID = -3192275762064103282L;

	/**
	 * 合同主键为空
	 */
	public static final String QUERY_CONTRACT_ID_NULL = "crm.sale.contract.queryContractIdNullException";
	/**
	 * 合同主键为空
	 */
	public static final String DELETE_CONTRACT_ID_NULL = "crm.sale.contract.deleleContractIdNullException";

	/**
	 * 分页参数为空
	 */
	public static final String QUERY_CONTRACT_RB_NULL = "crm.sale.contract.queryContractRbNullException";

	/**
	 * 查询参数为空
	 */
	public static final String QUERY_CONTRACT_PARAM_NULL = "crm.sale.contract.queryContractParamNullException";

	/**
	 * 新增参数为空
	 */
	public static final String ADD_CONTRACT_PARAM_NULL = "crm.sale.contract.addContractParamNullException";

	/**
	 * 修改参数为空
	 */
	public static final String UPDATE_CONTRACT_PARAM_NULL = "crm.sale.contract.updateContractParamNullException";

	/**
	 * 存在上传状态的合同
	 */
	public static final String ISEXIST_CONTRACT_YES_UPLOADED = "crm.sale.contract.isExistUploadedException";
	
	/**
	 * 存在受理中的合同信息
	 */
	public static final String ISEXIST_CONTRACT_YES_REVIEW = "crm.sale.contract.isExistReviewException";
	
	/**
	 * 存在已归档且未过期的合同信息
	 */
	public static final String ISEXIST_CONTRACT_YES_FILE = "crm.sale.contract.isExistFileException";
	
	
	/**
	 * 存在已删除的合同信息
	 */
	public static final String ISEXIST_CONTRACT_YES_DELETED = "crm.sale.contract.isExistDeletedException";
	
	/**
	 * 合同不存在
	 */
	public static final String CONTRACT_NULL = "crm.sale.contract.contractNullException";
	
	/**
	 * 合同已删除
	 */
	public static final String CONTRACT_DELETED = "crm.sale.contract.contractDeletedException";
	
	/**
	 * 不能新增负责人不为自己的客户的合同
	 */
	public static final String CONTRACT_ADD_NOT_MY = "crm.sale.contract.contractAddNotMyException";

	
	public SaleContractException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public SaleContractException(String code, String msg) {
		super(code, msg);
	}

	public SaleContractException(String errCode) {
		super();
		super.errCode = errCode;
	}
}
