package com.hoau.crm.module.customer.server.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.customer.api.shared.domain.SequenceNoEntity;
import com.hoau.crm.module.customer.server.dao.SequenceNoMapper;

/**
 * 序列号SERVICE
 * @author: 何斌
 * @create: 2015年5月28日 上午8:58:40
 */
@Service
public class SequenceNoService {
	
	@Resource
	private SequenceNoMapper sequenceNoMapper;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public synchronized String getSeqNo(String type){
		StringBuilder seqNo = new StringBuilder();
		//事务开始
		sequenceNoMapper.lockWfno(type);
		//查到当前的序列号信息
		SequenceNoEntity sequenceNoEntity = sequenceNoMapper.query(type);
		//生成序列号里面的时间
		seqNo.append(getCurrentDateStr());
		//判断当前序列号里面的时间和系统时间关系
		DecimalFormat df = new DecimalFormat("00000");
		if(checkDate(sequenceNoEntity.getTime())){
			//当天,时间一致,取数据库的序号,让后序列号加1更新到数据库
			Integer currentNo = sequenceNoEntity.getSeqNo();
			seqNo.append(df.format(currentNo));
		}else{
			//不是当天,时间不一致,更新数据库的时间,序列号从1开始
			sequenceNoEntity.setTime(new Date());
			//这次取一个,序列号从2开始
			sequenceNoEntity.setSeqNo(1);
			seqNo.append("00001");
			//更新
			sequenceNoMapper.updateSeqNo(sequenceNoEntity);
		}
		return seqNo.toString();
	};
	
	private String getCurrentDateStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
	
	private boolean checkDate(Date date){
		//先获得当前系统时间
		String currentDate = getCurrentDateStr();
		//获取序列号里面的时间
		String seqDate = new SimpleDateFormat("yyyyMMdd").format(date);
		return currentDate.equals(seqDate);
	}
}
