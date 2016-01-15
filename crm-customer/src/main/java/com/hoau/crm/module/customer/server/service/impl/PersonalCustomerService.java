package com.hoau.crm.module.customer.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.IPersonalCustomerService;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.PersonalCustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.PersonalCustomerException;
import com.hoau.crm.module.customer.server.dao.PersonalCustomerMapper;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;


/**
 * 个人客户SERVICE
 * 
 * @author: 何斌
 * @create: 2015年6月21日 上午9:11:50
 */
@Service
public class PersonalCustomerService implements IPersonalCustomerService{

	@Resource
	private PersonalCustomerMapper personalCustomerMapper;
	
	@Override
	public void addPersonalCustomer(PersonalCustomerEntity personalCustomerEntity) {
		if(personalCustomerEntity == null){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_ADD_PARAM_NULL);
		}
		personalCustomerMapper.addPersonalCustomer(personalCustomerEntity);
	}

	@Override
	public void deletePersonalCustomer(List<String> ids) {
		if(ids == null || ids.size() == 0){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_DELETE_PARAM_NULL);
		}
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		params.put("username", currentUser.getUserName());
		personalCustomerMapper.deletePersonalCustomer(params);
	}

	@Override
	public void updatePersonalCustomerInfo(PersonalCustomerEntity personalCustomerEntity) {
		if(personalCustomerEntity == null){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_UPDATE_PARAM_NULL);
		}
		personalCustomerMapper.updatePersonalCustomerInfo(personalCustomerEntity);
	}

	@Override
	public List<PersonalCustomerEntity> queryPersonalCustomerInfo(PersonalCustomerEntity personalCustomerEntity, RowBounds rb) {
		if(rb == null){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_QUERY_RB_NULL);
		}
		if(personalCustomerEntity == null){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_QUERY_PARAM_NULL);
		}
		Map<String, String> params = this.getQueryParams(personalCustomerEntity);
		return personalCustomerMapper.getPersonalCustomerInfo(params, rb);
	}

	private Map<String, String> getQueryParams(PersonalCustomerEntity personalCustomerEntity) {
		Map<String, String> params = new HashMap<String, String>();
		if(!StringUtil.isEmpty(personalCustomerEntity.getUserName())){
			params.put("userName", "%"+personalCustomerEntity.getUserName()+"%");
		}
		if(!StringUtil.isEmpty(personalCustomerEntity.getEnterpriseName())){
			params.put("enterpriseName", "%"+personalCustomerEntity.getEnterpriseName()+"%");
		}
		if(!StringUtil.isEmpty(personalCustomerEntity.getContactName())){
			params.put("contactName", "%"+personalCustomerEntity.getContactName()+"%");
		}
		if(!StringUtil.isEmpty(personalCustomerEntity.getCellphone())){
			params.put("cellphone", "%"+personalCustomerEntity.getCellphone()+"%");
		}
		if(!StringUtil.isEmpty(personalCustomerEntity.getTelephone())){
			params.put("telephone", "%"+personalCustomerEntity.getTelephone()+"%");
		}
		if(!StringUtil.isEmpty(personalCustomerEntity.getDetailedAddress())){
			params.put("detailedAddress", "%"+personalCustomerEntity.getDetailedAddress()+"%");
		}
		if(!StringUtil.isEmpty(personalCustomerEntity.getEmail())){
			params.put("email", "%"+personalCustomerEntity.getEmail()+"%");
		}
		if(!StringUtil.isEmpty(personalCustomerEntity.getSource())){
			params.put("source", "%"+personalCustomerEntity.getSource()+"%");
		}
		if (!StringUtil.isEmpty(personalCustomerEntity.getIsTurnCustomer())) {
			params.put("isTurnCustomer", personalCustomerEntity.getIsTurnCustomer());
		}
		return params;
	}

	@Override
	public long countPersonalCustomer(
			PersonalCustomerEntity personalCustomerEntity) {
		if(personalCustomerEntity == null){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_QUERY_PARAM_NULL);
		}
		Map<String, String> params = this.getQueryParams(personalCustomerEntity);
		return personalCustomerMapper.countPersonalCustomer(params);
	}

	@Override
	public PersonalCustomerEntity queryPersonalCustomerInfoById(String id) {
		if(StringUtil.isEmpty(id)){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_QUERY_PARAM_NULL);
		}
		return personalCustomerMapper.getPersonalCustomerInfoById(id);
	}

	@Override
	public boolean isExistCustomerBySourceId(int sourceId) {
		if(sourceId == 0){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_QUERY_PARAM_NULL);
		}
		long count =  personalCustomerMapper.isExistBySourceId(sourceId);
		if(count > 0 ){
			return true;
		}
		return false;
	}

	@Override
	public void addPersonalCustoemrContact(
			PersonalCustomerContactEntity personalCustomerContactEntity) {
		personalCustomerMapper.addPersonalCustomerContact(personalCustomerContactEntity);
	}

	@Override
	public boolean isExistContactBySourceId(int sourceId) {
		if(sourceId == 0){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_QUERY_PARAM_NULL);
		}
		long count = personalCustomerMapper.isExistContactBySourceId(sourceId);
		if(count > 0){
			return true;
		}
		return false;
	}

	@Override
	public void updatePersonalCustomerContact(
			PersonalCustomerContactEntity personalCustomerContactEntity) {
		if(personalCustomerContactEntity == null || personalCustomerContactEntity.getSourceId() == 0){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_UPDATE_PARAM_NULL);
		}
		personalCustomerMapper.updatePersonalCustomerContact(personalCustomerContactEntity);
	}

	@Override
	public List<PersonalCustomerContactEntity> queryPersonalCustomerContactInfo(
			PersonalCustomerContactEntity personalCustomerContactEntity) {
		if(personalCustomerContactEntity == null || personalCustomerContactEntity.getUserId() == 0){
			throw new PersonalCustomerException(PersonalCustomerException.PERSONALCUSTOMER_QUERY_PARAM_NULL);
		}
		return personalCustomerMapper.getPersonalCustomerContactInfo(personalCustomerContactEntity);
	}

}
