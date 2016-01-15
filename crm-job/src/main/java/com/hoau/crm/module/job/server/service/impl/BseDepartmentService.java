/**
 * 
 */
package com.hoau.crm.module.job.server.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.hoau.crm.module.job.server.dao.BseDepartmentJobMapper;
import com.hoau.crm.module.job.server.service.IBseDepartmentService;
import com.hoau.crm.module.job.shared.domain.BseDepartmentBean;
import com.hoau.crm.module.job.shared.domain.BseDepartmentResEntity;
import com.hoau.crm.module.job.shared.util.BseDepartmentConstants;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 
 * @author 丁勇
 * @date 2015年8月5日
 */
@org.springframework.stereotype.Service
public class BseDepartmentService implements IBseDepartmentService {
	private static Logger LOG = LoggerFactory
			.getLogger(BseDepartmentService.class);
	
	private final String YES ="Y";
	private final String NO ="N";
	@Resource
	private BseDepartmentJobMapper bsedeptJobMapper;

	@Override
	@Transactional
	public void synJobBseDept(String url) {
		// 创建客户端请求
		RestTemplate restTemplate = new RestTemplate();
		// 返回数据接收实体
		BseDepartmentResEntity bseDepartmentResEntity = new BseDepartmentResEntity();
		if (!StringUtil.isEmpty(url)) {
			// 查询数据库中表中的数据版本号
			String s = bsedeptJobMapper.queryBseDeptByVersionNo();
			// 请求地址,获取结果
			String rest = restTemplate.getForObject(url + s, String.class);
			// 将获取的string json文本转换为相应实体
			bseDepartmentResEntity = JSON.parseObject(rest,
					BseDepartmentResEntity.class);
			LOG.info("组织信息同步数据 : " + rest);
			if (bseDepartmentResEntity.getResult() == null
					&& bseDepartmentResEntity.getResult().size() <= 0) {
				LOG.info("没有需要同步的部门信息");
			} else {
				int count = 0;
				for (BseDepartmentBean bseDept : bseDepartmentResEntity
						.getResult()) {
					//ID赋值
					String uuid = UUIDUtil.getUUID();
					//默认值设置
					bseDept.setId(uuid);
					bseDept.setIsStore(NO);
					bseDept.setIsRoad(NO);
					bseDept.setIsRegion(NO);
					bseDept.setIsBusiness(NO);
					//判断性质
					if (BseDepartmentConstants.DEPT_NATURE_SALES_DEPARTMENT == (bseDept
							.getDeptNature() & BseDepartmentConstants.DEPT_NATURE_SALES_DEPARTMENT)) {
						bseDept.setDeptNature(BseDepartmentConstants.DEPT_NATURE_SALES_DEPARTMENT);
						bseDept.setIsStore(YES);
						bseDept.setDeptNatureName(BseDepartmentConstants.DEPT_OPERATION_DEPARTMENT);
					}
					
//					if (BseDepartmentConstants.DEPT_NATURE_PLATFORM == (bseDept
//							.getDeptNature() & BseDepartmentConstants.DEPT_NATURE_PLATFORM)) {
//						bseDept.setDeptNature(BseDepartmentConstants.DEPT_NATURE_PLATFORM);
//						
//						bseDept.setDeptNatureName(BseDepartmentConstants.DEPT_OPERATION_DEPARTMENT);
//					}
					if (BseDepartmentConstants.DEPT_NATURE_ROAD_AREA == (bseDept
							.getDeptNature() & BseDepartmentConstants.DEPT_NATURE_ROAD_AREA)) {
						bseDept.setIsRoad(YES);
						bseDept.setDeptNature(BseDepartmentConstants.DEPT_NATURE_ROAD_AREA);
						bseDept.setDeptNatureName(BseDepartmentConstants.DEPT_ROAD_AREA);
					}
//					if (BseDepartmentConstants.DEPT_NATURE_FLEET == (bseDept
//							.getDeptNature() & BseDepartmentConstants.DEPT_NATURE_FLEET)) {
//						bseDept.setDeptNature(BseDepartmentConstants.DEPT_NATURE_FLEET);
//						bseDept.setDeptNatureName(BseDepartmentConstants.DEPT_FLEET);
//					}
//					if (BseDepartmentConstants.DEPT_NATURE_TRANSFER_CENTER == (bseDept
//							.getDeptNature() & BseDepartmentConstants.DEPT_NATURE_TRANSFER_CENTER)) {
//						bseDept.setDeptNature(BseDepartmentConstants.DEPT_NATURE_TRANSFER_CENTER);
//						bseDept.setDeptNatureName(BseDepartmentConstants.DEPT_TRANSFER_CENTER);
//					}
					if (BseDepartmentConstants.DEPT_NATURE_BIG_REGION == (bseDept
							.getDeptNature() & BseDepartmentConstants.DEPT_NATURE_BIG_REGION)) {
						bseDept.setIsRegion(YES);
						bseDept.setDeptNature(BseDepartmentConstants.DEPT_NATURE_BIG_REGION);
						bseDept.setDeptNatureName(BseDepartmentConstants.DEPT_BIG_REGION);
					}
					if (BseDepartmentConstants.DEPT_NATURE_DIVISION == (bseDept
							.getDeptNature() & BseDepartmentConstants.DEPT_NATURE_DIVISION)) {
						bseDept.setIsBusiness(YES);
						bseDept.setDeptNature(BseDepartmentConstants.DEPT_NATURE_DIVISION);
						bseDept.setDeptNatureName(BseDepartmentConstants.DEPT_DIVISION);
					}

					 // 从现有数据库中按部门编号获取部门信息
					 BseDepartmentBean localbseDept = bsedeptJobMapper.queryBseDetptByDeptCode(bseDept.getDeptCode());
					 if (!StringUtils.isEmpty(localbseDept)) {
						 bsedeptJobMapper.delBaseDeptByDeptCode(bseDept.getDeptCode());
					 }
					 bsedeptJobMapper.insertBaseDept(bseDept);
					 count++;
				}
				LOG.info("部门信息同步成功" + count);
			}

		} else {
			LOG.info("获取url失败");
		}
	}
}
