package com.hoau.crm.module.bse.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.vo.ReviewHistoryVo;
import com.hoau.crm.module.bse.server.dao.ReviewHistoryMapper;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.util.UUIDUtil;

/**
 * 
 * @author 丁勇
 * @date 2015年6月24日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ReviewHistoryServiceImpl implements IReviewHistoryService {
	@Resource
	ReviewHistoryMapper reviewhistoryMapper;
	
	@Override
	@Transactional
	public void insertReviewOrHistory(ReviewHistoryEntity reviewhistoryEntity) {
		//判断是否是null
		if (!StringUtils.isEmpty(reviewhistoryEntity)) {
			//主键赋值
			String uuid = UUIDUtil.getUUID();
			reviewhistoryEntity.setId(uuid);
			//预约操作
			if (!StringUtils.isEmpty(reviewhistoryEntity.getReserveEntity())) {
				reviewhistoryEntity.setCustomerEntity(reviewhistoryEntity
						.getReserveEntity().getCustomerEntity());
				reviewhistoryEntity.setContactEntity(reviewhistoryEntity
						.getReserveEntity().getContactEntity());
			//洽谈操作
			}else if (!StringUtils.isEmpty(reviewhistoryEntity.getChatsEntity())) {
				reviewhistoryEntity.setCustomerEntity(reviewhistoryEntity
						.getChatsEntity().getCustomerEntity());
				reviewhistoryEntity.setContactEntity(reviewhistoryEntity
						.getChatsEntity().getContactEntity());
			//签到记录操作
			}else if (!StringUtils.isEmpty(reviewhistoryEntity.getSignEntity())) {
				reviewhistoryEntity.setSignEntity(reviewhistoryEntity.getSignEntity());
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setId(reviewhistoryEntity.getSignEntity().getCustomerId());
				reviewhistoryEntity.setCustomerEntity(customerEntity);
				reviewhistoryEntity.setCreateDate(reviewhistoryEntity.getSignEntity().getCreateDate());
			//客户操作 
			} else if (!StringUtils.isEmpty(reviewhistoryEntity  
					.getCustomerEntity())) {
				reviewhistoryEntity.setCustomerEntity(reviewhistoryEntity
						.getCustomerEntity());
				reviewhistoryEntity.setContactEntity(reviewhistoryEntity
						.getCustomerEntity().getContactEntity());
			//联系人操作
			} else if (!StringUtils.isEmpty(reviewhistoryEntity
					.getContactEntity())) {
				reviewhistoryEntity.setCustomerEntity(new CustomerEntity(reviewhistoryEntity
						.getContactEntity().getAccountId()));
				reviewhistoryEntity.setContactEntity(reviewhistoryEntity
						.getContactEntity());
			//门店走访,扫描签到
			}else if(!StringUtils.isEmpty(reviewhistoryEntity.getSweepSignEntity())){
				reviewhistoryEntity.setSweepSignEntity(reviewhistoryEntity.getSweepSignEntity());
			}
		}
		// 初始化时间
		if(reviewhistoryEntity.getCreateDate() == null){
			reviewhistoryEntity.setCreateDate(new Date());
		}
		reviewhistoryMapper.insertReviewOrHistory(reviewhistoryEntity);
	}

	@Override
	public List<ReviewHistoryVo> queryHistoryList(Map<String, Object> maps,RowBounds rb) {
		return reviewhistoryMapper.queryHistoryList(maps,rb);
	}
	
	@Override
	public List<ReviewHistoryVo> queryReviewList(Map<String, Object> maps,RowBounds rb) {
		return reviewhistoryMapper.queryReviewList(maps,rb);
	}

	@Override
	public long queryHistoryCount(Map<String, Object> map) {
		return reviewhistoryMapper.queryHistoryCount(map);
	}
	@Override
	public long queryReviewCount(Map<String, Object> map) {
		return reviewhistoryMapper.queryReviewCount(map);
	}

	@Override
	public List<Map<String, Object>> queryHistoryListByWeb(CustomerEntity customerEntity,
			RowBounds rb) {
		if (rb == null) {
			throw new CustomerException(CustomerException.RB_NULL);
		}
		return reviewhistoryMapper.queryHistoryListByWeb(customerEntity.getId(), rb);
	}
	
}
