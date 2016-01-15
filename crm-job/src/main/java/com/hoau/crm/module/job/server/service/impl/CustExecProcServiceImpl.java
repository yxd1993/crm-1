package com.hoau.crm.module.job.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.customer.api.shared.domain.QueryCreateSql;
import com.hoau.crm.module.job.server.dao.CustExecProcJobMapper;
import com.hoau.crm.module.job.server.service.ICustExecProcService;

@org.springframework.stereotype.Service
public class CustExecProcServiceImpl implements ICustExecProcService {

	@Resource
	CustExecProcJobMapper custExecProcJobMapper;
	
	@Transactional
	@Override
	public void execProc() {
		List<QueryCreateSql> createSqls = custExecProcJobMapper.selectPageQuerySql();
		List<QueryCreateSql> queryCreateSqls = new ArrayList<QueryCreateSql>();
		if(!createSqls.isEmpty() && createSqls.size() > 0){
			for(int index = 0,len=createSqls.size();index< len;index++){
				QueryCreateSql createSql = createSqls.get(index);
				//获取存储过程名字
				if(createSql.getTablenum() == null || "".equals(createSql.getTablenum()))
					continue;
				long beginTime = System.currentTimeMillis();
				Map<String,String> map = new HashMap<String,String>();
				map.put("procName", createSql.getTablenum());
				custExecProcJobMapper.execCustProc(map);
				//先设置上次执行时间
				createSql.setLastTime(createSql.getExecTime());
				//设置执行时间
				String execDate = getHHMMSSstr(System.currentTimeMillis() - beginTime);
				createSql.setExecTime(execDate);
				queryCreateSqls.add(createSql);
			}
		}
		//执行更新
		if(!queryCreateSqls.isEmpty() && queryCreateSqls.size() > 0)
			custExecProcJobMapper.updateBatchProcDate(queryCreateSqls);
	}
	
	public static String getHHMMSSstr(long timeLong) {
		long h = 60 * 60 * 1000;
		long m = 60 * 1000;
		long s = 1000;

		long hh = timeLong / h;
		long mm = (timeLong % h) / m;
		long ss = ((timeLong % h) % m) / s;
		long mill = ((timeLong % h) % m) % s;

		String msg = String.valueOf(hh) + ":" + String.valueOf(mm) + ":"
				+ String.valueOf(ss) + "   " + String.valueOf(mill);
		return msg;
	}
}
