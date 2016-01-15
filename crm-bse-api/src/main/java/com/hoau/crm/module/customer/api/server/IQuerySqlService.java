package com.hoau.crm.module.customer.api.server;
import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.QuerySql;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.crm.module.customer.api.shared.vo.RoleThreeVo;
import com.hoau.hbdp.framework.service.IService;

public interface IQuerySqlService extends IService{

	public void saveQuerySql(QuerySql querySql)throws BamSysException;
	public void modifyQuerySql(QuerySql querySql)throws BamSysException;
	public int toVoidQuerySql(List<String> querySqlNos);
	public List<QuerySql> showPageQuerySql(QuerySql querySql,
			int start, int limit);

	public Long totalQuerySqlCount(QuerySql querySql);
    public List<RoleThreeVo> querySqlRoleList(String sqlCode);
}
