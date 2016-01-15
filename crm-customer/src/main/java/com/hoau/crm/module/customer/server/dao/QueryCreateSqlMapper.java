package com.hoau.crm.module.customer.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.QueryCreateSql;

@Repository
public interface QueryCreateSqlMapper {
	public void insertQuerySql(QueryCreateSql querySql);
	public void updateQuerySql(QueryCreateSql querySql);
	public int deleteQuerySql(List<String> querySqlNos);
	public List<QueryCreateSql> selectPageQuerySql(QueryCreateSql querySql,RowBounds rb);

	public Long totalQuerySqlCount(QueryCreateSql querySql);
	
	public void updateCreateSql(Map map);
	
	public void updateCreateSqlInfo(QueryCreateSql querySql);
}
