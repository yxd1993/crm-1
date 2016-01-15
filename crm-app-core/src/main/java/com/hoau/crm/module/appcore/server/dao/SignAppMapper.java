package com.hoau.crm.module.appcore.server.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;

/**
 * 签到DAO
 * 
 * @author: 何斌
 * @create: 2015年7月3日 上午9:51:53
 */
@Repository
public interface SignAppMapper {
	/**
	 * 新增签到记录
	 * 
	 * @param signEntity
	 * @author: 何斌
	 * @date: 2015年7月3日
	 * @update 
	 */
	void addSignInfo(SignEntity signEntity);
	
	/**
	 * 根据ID查询签到信息
	 * 
	 * @param signEntity
	 * @author 蒋落琛
	 * @date 2015-10-14
	 * @update
	 */
	SignEntity isHasThereSign(SignEntity signEntity);
	
	/**
	 * 查询未被关联的签到地址
	 * @param params
	 * @return
	 * @author 丁勇
	 * @date 2015年11月5日
	 * @update 
	 */
	List<SignEntity> queryNoRelationSignList(Map<String,String> params);
	
	/**
	 *  查询未被关联的签到地址数量
	 * @param params
	 * @return
	 * @author 丁勇
	 * @date 2015年8月31日
	 * @update 
	 */
	long countQueryNoRelationSignList(Map<String,String> params);
	
	/**
	 * 更新绑定的签到id
	 * 
	 * @param signId
	 * @author: 何斌
	 * @date: 2015年8月14日
	 * @update 
	 */
	void updateRelationChatStatus(String signId);
	/**
	 * 新增扫描陪同人记录
	 * @param signVo
	 * @param loginName
	 * @return
	 * @author 丁勇
	 * @date 2015年11月4日
	 * @update 
	 */
	void  addScanAccompany(Map<String,Object> map);
}
