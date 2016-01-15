package com.hoau.crm.module.customer.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.customer.api.server.IQuerySqlService;
import com.hoau.crm.module.customer.api.shared.constants.Constant;
import com.hoau.crm.module.customer.api.shared.domain.QuerySql;
import com.hoau.crm.module.customer.api.shared.domain.RoleEntity;
import com.hoau.crm.module.customer.api.shared.domain.SqlRoleEntity;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.crm.module.customer.api.shared.vo.RoleThreeVo;
import com.hoau.crm.module.customer.server.dao.QuerySqlDaoMapper;
import com.hoau.crm.module.util.UUIDUtil;



@Service("querySqlService")
public class QuerySqlService implements IQuerySqlService {

	@Resource
	QuerySqlDaoMapper querySqlDao;
//	@Resource
//	ILog log;
	
	@Transactional
	@Override
	public void saveQuerySql(QuerySql querySql)throws BamSysException {
		// TODO Auto-generated method stub
		try{
			SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmssms"); 
			querySql.setId(UUIDUtil.getUUID());
			querySql.setNumber("SQL_"+time.format(new Date()));
			querySqlDao.insertQuerySql(querySql);
			addOrUpdateSqlRole(querySql);
		}catch(Exception e){
			e.printStackTrace();            
          //  log.saveExceptionLog("basefunction.impl.QuerySqlService.saveQuerySql",e.getMessage(),SessionUtil.getUserNo());
			throw new BamSysException(""+Constant.SYSTEM_EXCEPTION_CODE,"com.hoau.crm.module.customer.server.service.impl.QuerySqlService.saveQuerySql",e); 
		}
	}
	
	@Transactional
	@Override
	public void modifyQuerySql(QuerySql querySql)throws BamSysException {
		// TODO Auto-generated method stub
		try{
			querySqlDao.updateQuerySql(querySql);
			addOrUpdateSqlRole(querySql);
		}catch(Exception e){
			e.printStackTrace();            
        //    log.saveExceptionLog("basefunction.impl.QuerySqlService.modifyQuerySql",e.getMessage(),SessionUtil.getUserNo());
			throw new BamSysException(""+Constant.SYSTEM_EXCEPTION_CODE,"com.hoau.crm.module.customer.server.service.impl.QuerySqlService.modifyQuerySql",e);
		}
	}

	public void addOrUpdateSqlRole(QuerySql querySql){
	  
	    querySqlDao.deleteSqlRoleById(querySql.getId());
	    
	    List<SqlRoleEntity> sqlRoles = new ArrayList<SqlRoleEntity>();
	    String[] ids = querySql.getRoles().split(",");
        for (int i = 0; i < ids.length; i++) {
            String id = UUIDUtil.getUUID();
            SqlRoleEntity sqlRole = new SqlRoleEntity();
            sqlRole.setId(id);
            sqlRole.setRoleId(ids[i]);
            sqlRole.setSqlId(querySql.getId());
            sqlRoles.add(sqlRole);
        }
        if(querySql.getRoles().length() > 0){
            querySqlDao.addSqlRoles(sqlRoles);
        }
	}
	
	@Transactional
	@Override
	public int toVoidQuerySql(List<String> querySqlNos){
		// TODO Auto-generated method stub	
		//删除角色
	    querySqlDao.deleteSqlRoles(querySqlNos);
		int number = querySqlDao.deleteQuerySql(querySqlNos);
		
		return number;
	}

	@Override
	public List<QuerySql> showPageQuerySql(QuerySql querySql, int start,
			int limit) {
		RowBounds rb = new RowBounds(start,limit);
		List<QuerySql> querySqls = querySqlDao.selectPageQuerySql(querySql, rb);
		return querySqls;
	}

	@Override
	public Long totalQuerySqlCount(QuerySql querySql) {
		// TODO Auto-generated method stub
		return querySqlDao.totalQuerySqlCount(querySql);
	}

    @Override
    public List<RoleThreeVo> querySqlRoleList(String sqlCode) {
        List<RoleEntity> roles = querySqlDao.querySqlRoleList(sqlCode);
        List<RoleThreeVo> roleList = new ArrayList<RoleThreeVo>();
        for (int i = 0; i < roles.size(); i++) {
            RoleEntity entity = roles.get(i);
            RoleThreeVo vo = new RoleThreeVo();
            vo.setId(entity.getId());
            vo.setText(entity.getRoleName());
            vo.setLeaf(true);
            vo.setChecked(entity.getStatus().equals("1"));
            vo.setIconCls("treeNodeLeaf");
            roleList.add(vo);
        }
        return roleList;
    }

}
