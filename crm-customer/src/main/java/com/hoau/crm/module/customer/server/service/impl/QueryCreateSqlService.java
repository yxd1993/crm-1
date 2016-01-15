package com.hoau.crm.module.customer.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.customer.api.server.IQueryCreateSqlService;
import com.hoau.crm.module.customer.api.shared.domain.QueryCreateSql;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.crm.module.customer.server.dao.QueryCreateSqlMapper;
import com.hoau.crm.module.util.UUIDUtil;

@Service
public class QueryCreateSqlService implements IQueryCreateSqlService {
	@Resource
	QueryCreateSqlMapper queryCreateSqlMapper;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void saveQueryCreateSql(QueryCreateSql querySql)
			throws BamSysException {
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmssms"); 
		querySql.setId(UUIDUtil.getUUID());
		if(querySql.getCreateType().equals("1"))
			querySql.setNum("CREATE_"+time.format(new Date()));
		else
			querySql.setNum("PROCEDURE_"+time.format(new Date()));
		try{
			Map map = new HashMap();
			map.put("sql", querySql.getCreateSql());
			queryCreateSqlMapper.updateCreateSql(map);
		}catch(Exception e){
			throw new BamSysException(null, null,e);
		}
		queryCreateSqlMapper.insertQuerySql(querySql);
	}

    @Transactional
	@Override
	public void modifyQueryCreateSql(QueryCreateSql querySql)
			throws BamSysException {
		String sql = "";
		if(querySql.getCreateType().equals("1")){
			sql = "show create table "+querySql.getTablenum();
			try{
				Map map = new HashMap();
				map.put("sql", querySql.getAlter());
				queryCreateSqlMapper.updateCreateSql(map);
			}catch(Exception e){
				throw new BamSysException(null, null,e);
			}
		}
		else{
			sql = "show CREATE PROCEDURE "+querySql.getTablenum();
			String[] sqls = querySql.getAlter().split(";");
			if(sqls.length > 0){
				for(int i =0;i<sqls.length;i++){
					try{
						Map map = new HashMap();
						map.put("sql", sqls[i]);
						queryCreateSqlMapper.updateCreateSql(map);
					}catch(Exception e){
						throw new BamSysException(null, null,e);
					}
				}
			}
		}
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
		if (null != rs) {
			while (rs.next()) {
				String createSql = rs.getString(2);
				querySql.setCreateSql(createSql);
			}
		}
		queryCreateSqlMapper.updateCreateSqlInfo(querySql);
	}

    @Transactional
	@Override
	public int toVoidQueryCreateSql(List<QueryCreateSql> queryCreateSqls) {
		
		List<String> querySqlNos = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("drop table ").append(queryCreateSqls.get(0).getTablenum());
		querySqlNos.add(queryCreateSqls.get(0).getId());
		for(int index = 1,len=queryCreateSqls.size();index<len;index++){
			QueryCreateSql queryInfo = queryCreateSqls.get(index);
			querySqlNos.add(queryInfo.getId());
			sql.append(",").append(queryInfo.getTablenum());
		}
		int number = queryCreateSqlMapper.deleteQuerySql(querySqlNos);
		Map map = new HashMap();
		map.put("sql", sql.toString());
		queryCreateSqlMapper.updateCreateSql(map);
		return number;
	}

	@Override
	public List<QueryCreateSql> showPageQueryCreateSql(QueryCreateSql querySql,
			int start, int limit) {
		RowBounds rb = new RowBounds(start,limit);
		List<QueryCreateSql> querySqls = queryCreateSqlMapper.selectPageQuerySql(querySql, rb);
		return querySqls;
	}
	@Transactional
	@Override
	public Long totalQueryCreateSqlCount(QueryCreateSql querySql) {
		// TODO Auto-generated method stub
		return queryCreateSqlMapper.totalQuerySqlCount(querySql);
	}
	
	 @Transactional
	@Override
	public void deleteProcSql(List<QueryCreateSql> queryCreateSqls) {
		 List<String> querySqlNos = new ArrayList<String>();
		for(int index = 0,len=queryCreateSqls.size();index<len;index++){
			QueryCreateSql queryInfo = queryCreateSqls.get(index);
			querySqlNos.add(queryInfo.getId());
			StringBuffer sql = new StringBuffer();
			sql.append("DROP PROCEDURE IF EXISTS ").append(queryInfo.getTablenum());
			jdbcTemplate.execute(sql.toString());
		}
		queryCreateSqlMapper.deleteQuerySql(querySqlNos);
	}
}
