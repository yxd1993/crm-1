package com.hoau.crm.module.customer.server.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.customer.api.shared.domain.QuerySql;
import com.hoau.crm.module.customer.api.shared.domain.RoleEntity;
import com.hoau.crm.module.customer.api.shared.domain.SqlRoleEntity;
@Repository
public interface QuerySqlDaoMapper {
	public void insertQuerySql(QuerySql querySql);
	public void updateQuerySql(QuerySql querySql);
	public int deleteQuerySql(List<String> querySqlNos);
	public List<QuerySql> selectPageQuerySql(QuerySql querySql,RowBounds rb);

	public Long totalQuerySqlCount(QuerySql querySql);
    public List<RoleEntity> querySqlRoleList(String sqlCode);
    public int deleteSqlRoles(List<String> sqlIds);
    public void addSqlRoles(List<SqlRoleEntity> sqlRoles);
    public int deleteSqlRoleById(String sqlId);
}
