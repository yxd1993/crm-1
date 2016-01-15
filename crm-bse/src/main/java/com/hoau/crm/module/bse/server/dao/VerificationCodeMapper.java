package com.hoau.crm.module.bse.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.bse.api.shared.domain.ValidateCodeEntity;

/**
 * 验证码DAO
 * 
 * @author 蒋落琛
 * @date 2015-12-8
 */
@Repository
public interface VerificationCodeMapper {

	/**
	 * 查询某个手机号是否已生成验证码
	 * 
	 * @param phone
	 * @return
	 * @author 蒋落琛
	 * @date 2015-12-8
	 * @update
	 */
	public ValidateCodeEntity getValidateCodeByPhone(String phone);

	/**
	 * 创建验证码
	 * 
	 * @param validateCode
	 * @author 蒋落琛
	 * @date 2015-12-8
	 * @update
	 */
	public void createValidateCode(ValidateCodeEntity validateCode);

	/**
	 * 修改验证码
	 * 
	 * @param validateCode
	 * @author 蒋落琛
	 * @date 2015-12-8
	 * @update
	 */
	public void modifyValidateCode(ValidateCodeEntity validateCode);
}