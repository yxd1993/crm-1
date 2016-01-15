package com.hoau.crm.module.job.server.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.hoau.crm.module.job.server.dao.DistrictJobMapper;
import com.hoau.crm.module.job.server.service.IDistrictService;
import com.hoau.crm.module.job.shared.domain.DistrictBean;
import com.hoau.crm.module.job.shared.domain.DistrictInfoEntity;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 省市区service
 * @author 潘强
 * @create: 2015年7月8日 上午11:34:15
 */

@org.springframework.stereotype.Service
public class DistrictServiceImpl implements IDistrictService{
	
	private static Logger LOG =LoggerFactory.getLogger(DistrictServiceImpl.class);
	
	@Resource
    private DistrictJobMapper districtMapper;
	
	@Override
	@Transactional
	public void synJob(String url) {
		
		DistrictInfoEntity districtinfo=null;
		RestTemplate restTemplate=new RestTemplate();
	    //String rest=restTemplate.getForObject(url, String.class);
		if(!StringUtil.isEmpty(url)){
			//查询数据库中t_bse_district表中的数据版本号
			String s=districtMapper.selectVersionNo(); //"1432628893319";
			//与前面传过来的url合并组成完整的请求地址
			String rest=url+s;
			//接受从接口rest传过来的数据
			rest=restTemplate.getForObject(rest, String.class);
			//装换请求到的数据格式
			districtinfo = JSON.parseObject(rest, DistrictInfoEntity.class);
			LOG.info("districtinfo : " + rest);
			if(districtinfo.getResult()==null){
				LOG.info("省市区没有信息需要修改");
			}else{
				districtMapper.deleteAll();
				//遍历DistrictInfoEntity中用于存放DistrictBean对象的List集合，并存储到数据库中
				for (DistrictBean districtbean : districtinfo.getResult()) {
					districtMapper.insert(districtbean);
				}
				LOG.info("省市区信息修改成功");
			}
			
		}else{
			LOG.info("获取url失败");
			 }
		
	}

}
