package com.hoau.crm.module.customer.api.server;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.QueryCreateSql;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.hbdp.framework.service.IService;

public interface IQueryCreateSqlService extends IService {
	public void saveQueryCreateSql(QueryCreateSql querySql)throws BamSysException;
	public void modifyQueryCreateSql(QueryCreateSql querySql)throws BamSysException;
	public int toVoidQueryCreateSql(List<QueryCreateSql> queryCreateSqls);
	public List<QueryCreateSql> showPageQueryCreateSql(QueryCreateSql querySql,
			int start, int limit);
	public Long totalQueryCreateSqlCount(QueryCreateSql querySql);
	
	public void deleteProcSql(List<QueryCreateSql> queryCreateSqls);
}
