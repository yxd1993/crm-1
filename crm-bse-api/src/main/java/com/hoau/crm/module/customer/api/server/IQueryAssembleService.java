package com.hoau.crm.module.customer.api.server;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.domain.ExcelReturn;
import com.hoau.crm.module.customer.api.shared.domain.ExportQuery;
import com.hoau.crm.module.customer.api.shared.domain.Field;
import com.hoau.crm.module.customer.api.shared.domain.NameValue;
import com.hoau.crm.module.customer.api.shared.domain.QuerySql;
import com.hoau.crm.module.customer.api.shared.domain.TableHead;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.hbdp.framework.service.IService;



/**
 * @description 
 * @version 1.0
 * @author zzx
 * @update 2012-10-9 上午10:16:00 
 */

public interface IQueryAssembleService extends IService{

   public Long totalshowQuerySql(QuerySql queryAssemble);

   public List<QuerySql> showQuerySql(QuerySql queryAssemble, int start, int limit)throws BamSysException;

   public List<ExportQuery>  execSqlAll(String sql,String queryParam,int length,int start, int limit);

   public List<TableHead> queryTableHeads(String [] head);
   
   public List<Field> queryField(int length);

   public ExcelReturn toExcelPort(String sql, String queryParam, int length, String[] head,int startNumber,int limitNumber);

   public long checkMaxNumber(String sql, String queryParam);

   public List<NameValue> queryCombo(String queryParam, String sql);
   
   public QuerySql querySqlById(String id);
		   
}
