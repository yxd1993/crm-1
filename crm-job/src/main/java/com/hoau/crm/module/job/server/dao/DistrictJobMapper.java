package com.hoau.crm.module.job.server.dao;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.job.shared.domain.DistrictBean;
/**
 * 省市区信息DAO
 * @author 潘强
 * @create: 2015年7月8日 下午13:16:22
 */
@Repository
public interface DistrictJobMapper {
	//删除T_BSE_DISTRICT表中的所有数据
	void deleteAll();
	//插入省市区信息 
	void insert(DistrictBean dis);
	//查询T_BSE_DISTRICT表中selectVersionNo字段的最大值
	String selectVersionNo();
	
}
