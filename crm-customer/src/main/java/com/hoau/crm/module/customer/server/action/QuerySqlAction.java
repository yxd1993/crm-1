package com.hoau.crm.module.customer.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.IQuerySqlService;
import com.hoau.crm.module.customer.api.shared.constants.Constant;
import com.hoau.crm.module.customer.api.shared.domain.ParamBean;
import com.hoau.crm.module.customer.api.shared.domain.QuerySql;
import com.hoau.crm.module.customer.api.shared.domain.ResultMessage;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.crm.module.customer.api.shared.vo.RoleThreeVo;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @description 
 * @version 1.0
 * @author cbb
 * @update 2013-6-26 下午6:23:03 
 */

@Controller
@Scope("prototype")
public class QuerySqlAction extends AbstractAction{

	private static final long serialVersionUID = -3908499821835965158L;
	
	private List<QuerySql> querySqls;
	private QuerySql querySql;
	private List<String> querySqlNos;
	private ResultMessage resultMessage;
    private String sqlCode;
    private List<RoleThreeVo> roleThreeVos;

//	@Resource
//	ILog log;
	
	@Resource
	IQuerySqlService querySqlService;
	
	 /**
     * 跳转到sql管理界面
     */
	 public String showQueryPage(){
	        return this.returnSuccess();
	    }
	
     /**
     * 分页查询所有sql
     */
	public String showPageQuerySql(){
		if(null == querySql)
			querySql = new QuerySql();
		querySqls = querySqlService.showPageQuerySql(querySql, this.getStart(), this.getLimit());
		Long totalCount=querySqlService.totalQuerySqlCount(querySql);
		this.setTotalCount(totalCount);
		return this.returnSuccess();
	}
	
	 /**
     * 保存或修改自定义查询SQL
     */
	public String saveorModifyQuerySql(){
	    	    
        if(querySql==null){
        	resultMessage=new ResultMessage(Constant.RETURN_FAIL,"传入的参数为空值！");
			return SUCCESS;
        }
               
        //添加修改人
//        ActionContext context = ActionContext.getContext();
//        Map<String, Object> sessions = context.getSession();
//        UserEntity user = (UserEntity) sessions.get("user");
//        querySql.setModifyUser(user.getEmpCode());
        UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
        querySql.setModifyUser(currUser.getUserName());
        
        String toUpperCaseSql = "";
        if(!"".equals(querySql.getSql())&&querySql.getSql()!=null){
        	toUpperCaseSql = querySql.getSql().toUpperCase();
        	if(!toUpperCaseSql.contains("SELECT")
        			   ||toUpperCaseSql.contains("DELETE")
        			   ||toUpperCaseSql.contains("UPDATE")
        			   ||toUpperCaseSql.contains("DELETE")){
        				resultMessage=new ResultMessage(Constant.RETURN_FAIL,"SQL不是查询语句，请确认后在保存！");
        				return SUCCESS;
        			}
        }
        if(!"".equals(querySql.getParam())&&querySql.getParam()!=null){
			ObjectMapper mapper = new ObjectMapper();
			try {
				//List<ParamBean> paramBeans = mapper.readValue(querySql.getParam(), new TypeReference<List<ParamBean>>(){});
				ParamBean[] paramBeans = mapper.readValue(querySql.getParam(), ParamBean[].class);
				for(ParamBean paramBean:paramBeans){
				    if(!querySql.getSql().contains(paramBean.getId())){
				        resultMessage=new ResultMessage(Constant.RETURN_FAIL,"参数ID与SQL中的变量不一致，请确认后在保存！");
		                return SUCCESS;
				    }
				}
			} catch (Exception e) {
				resultMessage=new ResultMessage(Constant.RETURN_FAIL,"输入参数不符合规定的json格式，请确认后在保存！");
				return SUCCESS;
			}
        }				
		if("".equals(querySql.getId())||null==querySql.getId()){
			//保存		    
		        querySql.setCreateUser(currUser.getUserName());
		        try{
    				querySqlService.saveQuerySql(querySql);
    				resultMessage=new ResultMessage(Constant.RETURN_SUCCESS,"保存成功！");
        		}catch(BamSysException e){
//                    try{
//                        log.saveExceptionLog(e.getErrPath(), e);
//                    }catch(Exception ef){
//                        ef.printStackTrace();
//                    }
                  resultMessage=new ResultMessage(Constant.RETURN_FAIL,"保存失败！");
                }
				
						
		}else{
    			//修改
    		    try{
    		    	querySql.setModifyUser(currUser.getUserName());
    				querySqlService.modifyQuerySql(querySql);
    				resultMessage=new ResultMessage(Constant.RETURN_SUCCESS,"修改成功！");
        		}catch(BamSysException e){
//                    try{
//                        log.saveExceptionLog(e.getErrPath(), e);
//                    }catch(Exception ef){
//                        ef.printStackTrace();
//                    }
                  resultMessage=new ResultMessage(Constant.RETURN_FAIL,"修改失败！");
                    
                }	
		}	
		return SUCCESS;
	}
	

	 /**
     * 删除
     */
	public String toVoidQuerySql(){
	
			querySqlService.toVoidQuerySql(querySqlNos);
			resultMessage=new ResultMessage(Constant.RETURN_SUCCESS,"删除成功！");
		
		return SUCCESS;
	}

	
	 /**
     * 用户角色信息集合查询
     */
    public String querySqlRoleList() {
        sqlCode = sqlCode == null ? "" : sqlCode;
        roleThreeVos = querySqlService.querySqlRoleList(sqlCode);
        return SUCCESS;
    }
	
	public List<QuerySql> getQuerySqls() {
		return querySqls;
	}

	public void setQuerySqls(List<QuerySql> querySqls) {
		this.querySqls = querySqls;
	}

	public QuerySql getQuerySql() {
		return querySql;
	}

	public void setQuerySql(QuerySql querySql) {
		this.querySql = querySql;
	}

	public List<String> getQuerySqlNos() {
		return querySqlNos;
	}

	public void setQuerySqlNos(List<String> querySqlNos) {
		this.querySqlNos = querySqlNos;
	}

	public ResultMessage getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(ResultMessage resultMessage) {
		this.resultMessage = resultMessage;
	}

    public String getSqlCode() {
        return sqlCode;
    }

    public void setSqlCode(String sqlCode) {
        this.sqlCode = sqlCode;
    }

    public List<RoleThreeVo> getRoleThreeVos() {
        return roleThreeVos;
    }

    public void setRoleThreeVos(List<RoleThreeVo> roleThreeVos) {
        this.roleThreeVos = roleThreeVos;
    }

}
