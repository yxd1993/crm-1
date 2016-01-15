package com.hoau.crm.module.sales.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.sales.api.server.service.IIntentionService;
import com.hoau.crm.module.sales.api.shared.domain.IntentionEntity;
import com.hoau.crm.module.sales.api.shared.exception.IntentionException;
import com.hoau.crm.module.sales.api.shared.vo.IntentionVo;
import com.hoau.crm.module.sales.server.dao.IntentionMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * @author 275636 意向列表SERVICE
 * @modifyuser 丁勇
 * @modifydate 2015年6月19日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class IntentionService implements IIntentionService {
	@Resource
	IntentionMapper intentionMapper;

	@Override
	public List<IntentionEntity> getIntentionInfo(IntentionVo intentionVo,
			RowBounds rb) {
		if (rb == null) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		// 判断查询条件，模糊查询的进行处理
		if (intentionVo != null) {
			if (intentionVo.getIntentionEntity() != null) {
				//判断客户
				if (intentionVo.getIntentionEntity().getCustomerEntity() != null) {
					if (!StringUtil.isEmpty(intentionVo.getIntentionEntity()
							.getCustomerEntity().getEnterpriseName())) {
						params.put("enterpriseName", "%"
								+ intentionVo.getIntentionEntity()
										.getCustomerEntity()
										.getEnterpriseName() + "%");
					}
				}
				//判断联系人
				if (intentionVo.getIntentionEntity().getContactEntity() != null) {
					if (!StringUtil.isEmpty(intentionVo.getIntentionEntity()
							.getContactEntity().getContactName())) {
						params.put("contactName", "%"
								+ intentionVo.getIntentionEntity()
										.getContactEntity().getContactName()
								+ "%");
					}
				}
				//意向查询信息
				if (!StringUtil.isEmpty(intentionVo.getIntentionEntity()
						.getCustomerScore())) {
					params.put("customerScore", "%"
							+ intentionVo.getIntentionEntity()
									.getCustomerScore() + "%");
				}
			}
		}
		return intentionMapper.getIntentionInfo(params, rb);
	}

	@Override
	@Transactional
	public void addIntentionInfo(IntentionEntity intentionEntity) {
		if (intentionEntity == null) {
			throw new IntentionException(IntentionException.ADD_INTENTION_NULL);
		}
		if (intentionEntity.getCustomerEntity() == null) {
			throw new IntentionException(
					IntentionException.ADD_INTENTION_CUSTOMER_NULL);
		}
		// // 判断是否重复
		// if(!this.isAllowCustomer(customerEntity)){
		// throw new CustomerException(CustomerException.CUSTOMERINF_REPEAT);
		// }
		// 判断是新增还是修改
		if (StringUtil.isEmpty(intentionEntity.getId())) {
			// 主键赋值
			String uuid = UUIDUtil.getUUID();
			intentionEntity.setId(uuid);
			// 用户信息
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			intentionEntity.setCreateUser(currUser.getEmpEntity().getEmpCode());
			// 新增意向信息
			intentionMapper.addIntentionInfo(intentionEntity);
		} else {
			// 用户信息
			UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
			intentionEntity.setModifyUser(currUser.getEmpEntity().getEmpCode());
			// 根据主键查询意向信息
			IntentionEntity intentionInfo = intentionMapper
					.getIntentionInfoById(intentionEntity.getId());
			// 判断原来的数据是否已经删除
			if (!StringUtil.isEmpty(intentionInfo.getActive())
					&& intentionInfo.getActive().equals(BseConstants.INACTIVE)) {
				throw new IntentionException(
						IntentionException.UPDATE_IS_DELETE);
			}
			// 修改意向信息
			intentionMapper.updateIntentionInfo(intentionEntity);
		}
	}

	@Override
	public IntentionEntity getIntentionInfoById(IntentionEntity intentionEntity) {
		if (intentionEntity == null
				|| StringUtil.isEmpty(intentionEntity.getId())) {
			throw new IntentionException(
					IntentionException.ADD_INTENTION_ID_NULL);
		}
		return intentionMapper.getIntentionInfoById(intentionEntity.getId());
	}

	@Override
	public void deleteIntentionInfo(Map<String,Object> maps) {
		if(StringUtils.isEmpty(maps) || maps.size() <=0){
			throw new IntentionException(IntentionException.ADD_INTENTION_NULL);
		}else{
			if (StringUtils.isEmpty(maps.get("ids"))) {
				throw new IntentionException(IntentionException.ADD_INTENTION_NULL);
			}
		}
		// 用户信息
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Map<String, Object> map = new HashMap<String, Object>();
		//封装参数
		map.put("ids", maps.get("ids"));
		map.put("username", currUser.getEmpEntity().getEmpCode());
		map.put("delDesc", maps.get("delDesc"));
		intentionMapper.deleteIntentionInfo(map);
	}

	@Override
	public long countIntention(IntentionVo intentionVo) {
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		// 判断查询条件，模糊查询的进行处理
		if (intentionVo != null) {
			if (intentionVo.getIntentionEntity() != null) {
				if (intentionVo.getIntentionEntity().getCustomerEntity() != null) {
					if (!StringUtil.isEmpty(intentionVo.getIntentionEntity()
							.getCustomerEntity().getEnterpriseName())) {
						params.put("enterpriseName", "%"
								+ intentionVo.getIntentionEntity()
										.getCustomerEntity()
										.getEnterpriseName() + "%");
					}
					if (!StringUtil.isEmpty(intentionVo.getIntentionEntity()
							.getContactEntity().getContactName())) {
						params.put("contacName", "%"
								+ intentionVo.getIntentionEntity()
										.getContactEntity().getContactName()
								+ "%");
					}
					if (!StringUtil.isEmpty(intentionVo.getIntentionEntity()
							.getCustomerScore())) {
						params.put("customerScore", "%"
								+ intentionVo.getIntentionEntity()
										.getCustomerScore() + "%");
					}
				}
			}
		}
		return intentionMapper.countIntention(params);
	}
}
