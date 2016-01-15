package com.hoau.crm.module.customer.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.IQueryCreateSqlService;
import com.hoau.crm.module.customer.api.shared.constants.Constant;
import com.hoau.crm.module.customer.api.shared.domain.QueryCreateSql;
import com.hoau.crm.module.customer.api.shared.domain.ResultMessage;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
@Controller
@Scope("prototype")
public class QueryCreateSqlAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private QueryCreateSql queryCreateSql;
	
	private List<QueryCreateSql> queryCreateSqls;
	
	private ResultMessage resultMessage;
	
	private List<String> queryCreateSqlNos;
	
	@Resource
	IQueryCreateSqlService queryCreateSqlService;
	
	
	/**
     * 跳转到sql管理界面
     */
	 public String showQueryCreatePage(){
	        return this.returnSuccess();
	    }
	
     /**
     * 分页查询所有sql
     */
	public String showPagequeryCreateSql(){
		if(null == queryCreateSql)
			queryCreateSql = new QueryCreateSql();
		queryCreateSqls = queryCreateSqlService.showPageQueryCreateSql(queryCreateSql, this.getStart(), this.getLimit());
		Long totalCount=queryCreateSqlService.totalQueryCreateSqlCount(queryCreateSql);
		this.setTotalCount(totalCount);
		return this.returnSuccess();
	}
	
	 /**
     * 保存或修改自定义查询SQL
     */
	public String saveorModifyqueryCreateSql(){
        if(queryCreateSql==null){
        	resultMessage=new ResultMessage(Constant.RETURN_FAIL,"传入的参数为空值！");
			return SUCCESS;
        }
        UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
        queryCreateSql.setModifyUser(currUser.getUserName());
		if("".equals(queryCreateSql.getId())||null==queryCreateSql.getId()){
			//保存		    
		        queryCreateSql.setCreateUser(currUser.getUserName());
		        try{
    				queryCreateSqlService.saveQueryCreateSql(queryCreateSql);
    				resultMessage=new ResultMessage(Constant.RETURN_SUCCESS,"保存成功！");
        		}catch(BamSysException e){
        			resultMessage=new ResultMessage(Constant.RETURN_FAIL,"语法错误："+e.getMessage());
//                  resultMessage=new ResultMessage(Constant.RETURN_FAIL,"保存失败！");
                }
		}else{
    			//修改
    		    try{
    		    	queryCreateSql.setModifyUser(currUser.getUserName());
    				queryCreateSqlService.modifyQueryCreateSql(queryCreateSql);
    				resultMessage=new ResultMessage(Constant.RETURN_SUCCESS,"修改成功！");
        		}catch(BamSysException e){
        			resultMessage=new ResultMessage(Constant.RETURN_FAIL,"语法错误："+e.getMessage());
//                  resultMessage=new ResultMessage(Constant.RETURN_FAIL,"修改失败！");
                }	
		}	
		return SUCCESS;
	}
	 public QueryCreateSql getQueryCreateSql() {
		return queryCreateSql;
	}

	public void setQueryCreateSql(QueryCreateSql queryCreateSql) {
		this.queryCreateSql = queryCreateSql;
	}

	public List<QueryCreateSql> getQueryCreateSqls() {
		return queryCreateSqls;
	}

	public void setQueryCreateSqls(List<QueryCreateSql> queryCreateSqls) {
		this.queryCreateSqls = queryCreateSqls;
	}

	public ResultMessage getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(ResultMessage resultMessage) {
		this.resultMessage = resultMessage;
	}

	public List<String> getQueryCreateSqlNos() {
		return queryCreateSqlNos;
	}

	public void setQueryCreateSqlNos(List<String> queryCreateSqlNos) {
		this.queryCreateSqlNos = queryCreateSqlNos;
	}

	/**
     * 删除创建表
     */
	public String toVoidqueryCreateSql(){
			queryCreateSqlService.toVoidQueryCreateSql(queryCreateSqls);
			resultMessage=new ResultMessage(Constant.RETURN_SUCCESS,"删除成功！");
		
		return SUCCESS;
	}
	
	/**
     * 删除存储过程
     */
	public String deleteProcSql(){
			queryCreateSqlService.deleteProcSql(queryCreateSqls);
			resultMessage=new ResultMessage(Constant.RETURN_SUCCESS,"删除成功！");
		
		return SUCCESS;
	}
}
